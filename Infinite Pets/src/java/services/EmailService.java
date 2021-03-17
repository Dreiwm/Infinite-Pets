/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
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
            while (line != null) {
                body += line;
                line = br.readLine();
            }

            // replace all {{variable}} with the actual values
            for (String key : tags.keySet()) {
                body = body.replace("{{" + key + "}}", tags.get(key));
            }

        } catch (Exception e) {
           // Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, e);
        }

        sendMail(to, subject, body, true);
    }
    
    
    public static void sendMail(String to, String subject, String body, Boolean bodyIsHTML) throws NamingException, MessagingException{
        Context env = (Context)new InitialContext().lookup("java:comp/env");
        String username = (String)env.lookup("webmail-username");
        String password = (String)env.lookup("webmail-password");
        
        Properties props = new Properties();
        props.put("mai.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.prot",465);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
        //session.setDebug(true);
        
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
    public void sendRecoveryPassword(Account to, String path){
        
        try{
            String subject = "Infinite Pets Password Recovery";
            String template = path + "assets/emailTemplates/ResetTemplate";
            
            HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", to.getFirstName());
                tags.put("lastname", to.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                sendMail(to.getEmail(), subject, template, tags);
            
        }
        catch(Exception e){
            
        }
        
    }
    
    
}
