/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import models.Account;
import models.Appointment;
import models.Appointmentservice;

/**
 *
 * @author Chris
 */
public class EmailService {
    
    private final String ownerEmail = "bccrsb@gmail.com"; // temporary email for owner.
    
    public static void sendMail(String to, String subject, String template, HashMap<String, String> tags) throws Exception {
        // {{firstname}} -> Anne
        // {{date}} -> Oct. 28
        String body = "";
        try {
            // read whole template into a single variable (body)
            BufferedReader br = new BufferedReader(new FileReader(new File(template)));

            String line = br.readLine();
            System.out.println("printing the template");
            while (line != null) {
                System.out.println(line);
                body += line;
                line = br.readLine();
            }

            // replace all {{variable}} with the actual values
            for (String key : tags.keySet()) {
                body = body.replace("{{" + key + "}}", tags.get(key));
            }

        } catch (Exception e) {
//            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, e);
           System.out.println(e);
        }

        sendMail(to, subject, body, true);
    }
    
    
    public static void sendMail(String to, String subject, String body, Boolean bodyIsHTML) throws NamingException, MessagingException{
        Context env = (Context)new InitialContext().lookup("java:comp/env");
        String username = (String)env.lookup("webmail-username");
        String password = (String)env.lookup("webmail-password");

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port",465);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
//        session.setDebug(true);
        
        //Create Message
        Message msg = new MimeMessage(session);
        msg.setSubject(subject);
        if(bodyIsHTML){
            msg.setContent(body, "text/html");
        }
        else{
            msg.setText(body);
        }
        
        //address the message
        Address fromAddress = new InternetAddress(username);
        Address toAddress = new InternetAddress(to);
        msg.setFrom(fromAddress);
        msg.setRecipient(Message.RecipientType.TO, toAddress);
  
        //Send message
        Transport transport = session.getTransport();
        transport.connect(username, password);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
    }
    
    
    /**
     * Sends an email to user to confirm account deletion.
     * @param acc Account to be deleted.
     * @param path the path 
     * @param url the URL
     * @param delConfirmToken a randomized string.
     */
    public void sendDeletionConfirm(Account acc, String path, String url, String delConfirmToken) {
        try {
            String subj = "Infinite Pets - Confirm Account Deletion";
            String template = path + "/emailTemplates/AccountDeletionConfirmTemplate.html";
     
            // Tags to be used in the sendEmail method
            HashMap<String, String> tags = new HashMap<>();
            tags.put("fName", acc.getFirstName());
            tags.put("lName", acc.getLastName());
            
            // finally, deletion token itself
            tags.put("deleteAccount", url + "/DeleteAccount?delToken=" + delConfirmToken);
            
            // finally, send email
            sendMail(acc.getEmail(), subj, template, tags);
        } catch (Exception ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  /*
    To Move Later added here so to test code
    */
    public void sendRecoveryPassword(Account to, String path, String url, String resetToken){
        
        try{
            String subject = "Infinite Pets Password Recovery";
            String template = path + "/emailTemplates/ResetTemplate.html";
            
            HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", to.getFirstName());
                tags.put("lastname", to.getLastName());
                //tags.put("reset", url + "/reset?token="+to.getReset());
                tags.put("reset", url + "/reset?token="+resetToken);
                
            sendMail(to.getEmail(), subject, template, tags);
            
        }
        catch(Exception e){
            
        }
        
    }   

    /**
     * Email the appointment cancellation notification to staff and owner
     * @param appt the appointment service that bad been cancelled by client.
     * @param today the date when the client requested cancellation.
     * @param path the path
     */
    public void sendCancellationNotification(Appointment appt, Date today, String path) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
                
        // Staff account associated with appointment (if any)
        Account acc = appt.getClientID();
        Account empAcc = appt.getEmployeeID().getUserID();
        
        String subj = "Infinite Pets - Client Cancelled An Appointment";
        String template = path + "/emailTemplates/AppointmentCancellationNotificationTemplate.html";

        
        
        // Tags to be used in the sendEmail method
        HashMap<String, String> tags = new HashMap<>();
        tags.put("clientFirstName", acc.getFirstName());
        tags.put("clientLastName", acc.getLastName());
        
        
        // Cancellation date
        tags.put("cancellationDate", sdf.format(today));
        
        // finally, send email
        sendMail(empAcc.getEmail(), subj, template, tags);
        // to owner
        sendMail(ownerEmail, subj, template, tags);
    }
    
    /**
     * Email the appointment update notification to staff and client.
     * @param appt the appointment service that bad been updated by client.
     * @param today the date when the client updated the appointment.
     * @param path the path
     */
    public void sendAppointmentUpdateNotification(Appointment appt, Date today, String path) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
                
        // Staff account associated with appointment (if any)
        Account empAcc = appt.getEmployeeID().getUserID();
        
        // client account associated with appointment
        Account acc = appt.getClientID();
        
        String subj = "Infinite Pets - Client Cancelled An Appointment";
        String template = path + "/emailTemplates/AppointmentUpdateNotificationTemplate.html";

        
        
        // Tags to be used in the sendEmail method
        HashMap<String, String> tags = new HashMap<>();
        tags.put("clientFirstName", acc.getFirstName());
        
        
        // Update date
        tags.put("updateDate", sdf.format(today));
        
        // appointment date 
//        sdf.applyPattern("hh:mm:a");
        String apptDateStr = sdf.format(appt.getAppointmentDate());
        sdf.applyPattern("hh:mm:a");

        apptDateStr += " at " + sdf.format(appt.getAppointmentTime());
        
        tags.put("apptDate", apptDateStr);
        
        // services
        String services = "";
        List<Appointmentservice> servicesList = appt.getAppointmentserviceList();
        
        for (Appointmentservice apptS : servicesList) {
            services += "<li>";
            services += apptS.getServiceID().getServiceName() + " - $" + apptS.getServiceID().getBasePrice();
            services += " with " + apptS.getPetID().getPetName();
            services += "</li>";
        }
        tags.put("services", services);
        
        
        // finally, send email
        sendMail(empAcc.getEmail(), subj, template, tags);
        // to client
        sendMail(acc.getEmail(), subj, template, tags);
    }
}
