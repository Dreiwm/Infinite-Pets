/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.adminServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import services.AccountServices;

/**
 *
 * @author BTran
 */
public class AdminAccntServlet extends HttpServlet {
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
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        if (email.equals("") || email == null){
            response.sendRedirect("Login");
        }
        String action = request.getParameter("action");
        String empEmail = request.getParameter("email");
        System.out.println("Action: "+action);
        try {
            if (!action.equals("") && action != null){
                switch (action) {
                case "edit":
                    response.sendRedirect("NewEmployee");
                    break;
                case "delete":
                    AccountServices as = new AccountServices();
                    as.deleteAccount(empEmail);
                    break;
                default:
                    break;
                }
            }           
        } catch(Exception e) {
            Logger.getLogger(AdminPetServicesServlet.class.getName()).log(Level.WARNING, null, e);
            System.out.println("Action not processable: "+action);
        }
        
        try {
            AccountServices as = new AccountServices();
            List<Account> employees = as.getEmployees();
            System.out.println("Setting employees");
            request.setAttribute("employees", employees);
        } catch (Exception e){
            Logger.getLogger(AdminPetServicesServlet.class.getName()).log(Level.WARNING, null, e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/AdminAccntServices.jsp").forward(request,response);
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
}
