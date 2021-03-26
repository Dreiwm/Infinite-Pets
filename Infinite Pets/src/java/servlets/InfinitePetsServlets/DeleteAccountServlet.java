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
                        System.out.println("loading page");
//                        

        // ensure that the user is still in session. If so, dispatch to JSP.
        // Otherwise, send the user to login page
        HttpSession ses = request.getSession();
        ses.setAttribute("email", "bccrs.test@gmail.com"); 
        // Pretend that session is valid. Remove above when session is working properly.
        
        // Check if the deleteAccount is not null, if its not, then the link
        // is coming from an email that was sent.
        String deleteAccount = request.getParameter("deleteAccount");
        
        
        // test
        request.setAttribute("deleteAccount", "asdf");
        
        if (deleteAccount != null) {
            // actually delete account 
            // invalidate the session
            
            AccountServices acs = new AccountServices();
                            try {
                                acs.deleteAccount((String) ses.getAttribute("email"));
                            } catch (Exception ex) {
                                Logger.getLogger(DeleteAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
;
            
            ses.invalidate();
            
        } 
        getServletContext().getRequestDispatcher("/WEB-INF/DeleteAccount.jsp").forward(request,response);
        
        
        
//        String email = (String) ses.getAttribute("owner");
//        System.out.println(email);
//        AccountServices acs = new AccountServices();
//        if (email != null) {
//            try {
//                if (acs.getAccount(email) != null)
//                    getServletContext().getRequestDispatcher("/WEB-INF/DeleteAccount.jsp").forward(request,response);
//            } catch (Exception ex) {
//                Logger.getLogger(DeleteAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else {
//            ses.invalidate();
//            response.sendRedirect("Login");
//        }
        
        
        
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
                    System.out.println("Deleting account");
                    
                    // Get email from session and then get Account
                    HttpSession ses = request.getSession();
                    // Get account using AccountService
                    AccountServices acs = new AccountServices();
                    Account acc = acs.getAccount((String) ses.getAttribute("email"));
                    
                    // Send email to user to confirm deletion...
                    // Use EmailService
                    EmailService es = new EmailService();
                    
                    
                    String path = getServletContext().getRealPath("/WEB-INF");
                    String url =  request.getScheme() + "://" + request.getServerName();
                    // Create random token...
                    String delConfirmToken = UUID.randomUUID().toString();
                    
//                    es.sendDeletionConfirm(acc, path, url, delConfirmToken);
//                        es.sendRecoveryPassword(to, path, url, action);
                    
                } catch (Exception ex) {
                    Logger.getLogger(DeleteAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
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
