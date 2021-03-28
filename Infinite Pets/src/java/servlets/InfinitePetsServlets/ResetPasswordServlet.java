/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.InfinitePetsServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Account;
import services.AccountSerrvices;
import services.AddPetServices;
import services.EmailService;
import services.PetServicesServices;

/**
 *
 * @author Chris
 */
public class ResetPasswordServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/ResetPassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        Account to = new Account();
        AccountSerrvices as = new AccountSerrvices();
        boolean found = false;
        //connect to the database to see if the email is a valid on if so then call the email service to send the reset link
        try { 
            to = as.getAcocuntEmail(email);
            if(to!=null){
                found = true;
                //get the path, and create a reset token 
                EmailService gmail = new EmailService();
                String path = getServletContext().getRealPath("/WEB-INF");
                String url =  request.getScheme() + "://" + request.getServerName();
                String resetToken = UUID.randomUUID().toString();
                
                //update the user account with the reset token
                //to.setResetToken(resetToken);
                //as.updateAccount(to);
                
                //gmail.sendRecoveryPassword(to, path, url);
                gmail.sendRecoveryPassword(to, path, url, resetToken);
                
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if (found){
                 response.sendRedirect("Login");
            }  
            else{
                request.setAttribute("message", "Error Email Not Found");
                request.getRequestDispatcher("/WEB-INF/ResetPassword.jsp").forward(request, response);
            }  
        }
        
               
    }

  
}
