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
public class LoginServlet extends HttpServlet {
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
        getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request,response);
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
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
        
            AccountServices as = new AccountServices();
            Account user = as.login(email, password);

            if (user == null) {
                request.setAttribute("errorMsg", "Incorrect Email or Password");
                getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
                return;
            }
            else {
//                //TODO: ADD SESSIONS
//                //Set their session ID for a valid login
//                HttpSession session = request.getSession();
//                session.setAttribute("email", email);    
//                if (user.getIsEmployee()){
//                    response.sendRedirect("");  //Send to employee page
//                }
//                else if (user.getIsAdmin()) 
//                    response.sendRedirect("Admin");  //Send to admin page
//                else
                    response.sendRedirect("MyPets");  //Sends to pet list, but will be changed to homepage
            }
        } catch (Exception e) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.WARNING, null, e);
        }
    }
}
