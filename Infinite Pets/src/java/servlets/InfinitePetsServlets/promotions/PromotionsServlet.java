/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.InfinitePetsServlets.promotions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Account;
import models.Employee;
import services.AccountServices;

/**
 *
 * @author Riley
 */
public class PromotionsServlet extends HttpServlet {
    private final String path = "/WEB-INF/promotions/Promotions.jsp";

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
        
        // This page is only for admin. So, first we need to check if it is in session,
        // if yes, check is this account an admin?
        // If yes, serve the page. Otherwise, redirect to login page.
        String email = (String) request.getSession().getAttribute("email");
        
        AccountServices acs = new AccountServices();
        Account acc = null;
        Employee emp = null;
        try {
            acc = acs.getAccount(email);
        } catch (Exception ex) {
            Logger.getLogger(PromotionsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (acc == null) {
            System.out.println("account null, going to login page...");
            response.sendRedirect(response.encodeRedirectURL("/WEB-INF/Login.jsp"));        
        } else {
            // check if admin
            if (acc.getIsEmployee()) {
                System.out.println("Account is not null, checking is admin?");
                System.out.println("name: " + acc.getFirstName() + "userID: " + acc.getUserId());
                try {
                    emp = acs.getEmployeeAccount(email);
                } catch (Exception ex) {
                    Logger.getLogger(PromotionsServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                // now check if is admin
                if (emp != null &&  emp.getIsAdmin()) {
                    System.out.println("Eomployee is an admin");
                    getServletContext().getRequestDispatcher(path).forward(request, response);
                } else {
                    System.out.println("Employee is not an admin");
                    response.sendRedirect(response.encodeRedirectURL("Login"));        
                }
                
            } else {
                System.out.println("emp obj is null");
                response.sendRedirect(response.encodeRedirectURL("Login"));
            }
        
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
