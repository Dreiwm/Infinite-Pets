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
                getServletContext().getRequestDispatcher("/WEB-INF/DeleteAccount.jsp").forward(request,response);
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
                    
                    
                    String path = getServletContext().getRealPath("/WEB-INF");
                    String url =  request.getScheme() + "://" + request.getServerName();
                    // Create random token...
                    String delConfirmToken = UUID.randomUUID().toString();
//                    acc.setDeleteAccountCode(delConfirmToken);
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
