/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.InfinitePetsServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import services.AccountServices;
import services.EmailService;

/**
 *
 * @author Riley
 */
public class DeleteAccountServlet extends HttpServlet {

   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession ses = request.getSession();
        
        
        /*******************************
         * 
         * ***********
         * REMOVE ME *
         * ***********
         * 
         ******************************/
        ses.setAttribute("email", "bccrs.test@gmail.com"); 
        // Pretend that session is valid. Remove above when session is working properly.
        
        String email = (String) ses.getAttribute("email");
        System.out.println(email);
        AccountServices acs = new AccountServices();
        Account acc = null;
        try {
            acc = acs.getAccount(email);
        } catch (Exception ex) {
            Logger.getLogger(DeleteAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
;
        if (acc != null) {
            // if account is not null, means email in session matches from DB
            
            // Set attribute for deleteAccountVerified on front end to present 
            // form to delete account by default.
            request.setAttribute("deleteAccountVerified", false);
            
            // Check if the deleteAccount is not null, if its not, then the link
        // is coming from an email that was sent.
        String deleteAccount = request.getParameter("delToken");
        
        
        
        if (deleteAccount != null) {
            // Before we actually delete the account, we need to verify that
            // the token matches the token on account.
            
            // get token
            String tokenFromAcc = acc.getDeleteAccountCode();
            System.out.println(tokenFromAcc);
            // Check if token from account is valid.
            if (tokenFromAcc != null && deleteAccount.equals(tokenFromAcc)) {
                // actually delete now.
                // actually delete account 
                 // invalidate the session
                 

                try {
                    
                    acs.deleteAccount(acc.getEmail());
                } catch (Exception ex) {
                    Logger.getLogger(DeleteAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
    
                request.setAttribute("deleteAccountVerified", true);
                ses.invalidate();
            }
        }
            
        getServletContext().getRequestDispatcher("/WEB-INF/DeleteAccount.jsp").forward(request,response);
        } else {
            ses.invalidate();
            response.sendRedirect("Login");
        }

        
        
        
         
    }
        
        

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // if delete button is not null, then send to login page
        // if cancel button is not null, then go back to MyProfile page.
        String action = request.getParameter("action");
        
        if (action != null) {
            if (action.equals("deleteAccount")) {
                try {
                    System.out.println("Sending email to delete account");
                    
                    // Get email from session and then get Account
                    HttpSession ses = request.getSession();
                    // Get account using AccountService
                    AccountServices acs = new AccountServices();
                    Account acc = acs.getAccount((String) ses.getAttribute("email"));
                    System.out.println("email: " + acc.getEmail());
                    // Send email to user to confirm deletion...
                    // Use EmailService
                    EmailService es = new EmailService();
                    
                    
                    String path = getServletContext().getRealPath("/assets");
                    String url =  request.getScheme() + "://" + request.getServerName();
                    
//                    String path = getServletContext().getRealPath("/WEB-INF");
//                    String url =  request.getScheme() + "://" + request.getServerName();
                    // Create random token...
                    String delConfirmToken = UUID.randomUUID().toString();
                    acc.setDeleteAccountCode(delConfirmToken);
                    acs.updateDeleteToken(delConfirmToken, acc.getEmail());
                    System.out.println("path: " + path + " url: " + url + "token: " + delConfirmToken);
                    es.sendDeletionConfirm(acc, path, url, delConfirmToken);
//                        es.sendRecoveryPassword(to, path, url, action);
                    
                } catch (Exception ex) {
                    Logger.getLogger(DeleteAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect("Login");
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/MyProfile.jsp").forward(request,response);
            }
        }
                
        
        
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
