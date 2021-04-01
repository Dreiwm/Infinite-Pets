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
import java.util.HashMap;
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

/**
 *
 * @author Chris
 */
public class EmailService {
    
    
    
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

//        sendMail(to, subject, body, true);
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
    
    
    
    /*
    To Move Later added here so to test code
    */
    public void sendRecoveryPassword(Account to, String path, String url, String resetToken){
        
        try{
            String subject = "Infinite Pets Password Recovery";
            String template = path + "assets/emailTemplates/ResetTemplate";
            
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
     * Sends an email to user to confirm account deletion.
     * @param acc Account to be deleted.
     * @param path the path 
     * @param url the URL
     * @param delConfirmToken a randomized string.
     */
    public void sendDeletionConfirm(Account acc, String path, String url, String delConfirmToken) {
        try {
            String subj = "Infinite Pets - Confirm Account Deletion";
            String template = "web/assets/emailTemplates/AccountDeletionConfirmTemplate.html";
//            String template = "web/assets/emailTemplates/AccountDeletionConfirmTemplate.txt";
            
        System.out.println(Paths.get("/web/assets/emailTemplates/AccountDeletionConfirmTemplate.html").toFile().isFile());

            // web/assets/emailTemplates/AccountDeletionConfirmTemplate.html
            
            
            // Tags to be used in the sendEmail method
            HashMap<String, String> tags = new HashMap<>();
            tags.put("fName", acc.getFirstName());
            tags.put("lName", acc.getLastName());
            
            // finally, deletion token itself
            tags.put("deleteAccount", url + "/deleteAccount?delToken=" + delConfirmToken);
            
            // finally, send email
            sendMail(acc.getEmail(), subj, template, tags);
        } catch (Exception ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
