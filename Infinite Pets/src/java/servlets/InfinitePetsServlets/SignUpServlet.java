/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.InfinitePetsServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountServices;

/**
 *
 * @author BTran
 */
public class SignUpServlet extends HttpServlet {
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
        session.invalidate(); // just by going to the login page the user is logged out
        
        getServletContext().getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(request,response);
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
            AccountServices as = new AccountServices();
            //request the action being done
            String action = request.getParameter("action");
            //get all the form input
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String emailConf = request.getParameter("emailConfirm");
            String password = request.getParameter("password");
            String passwordConf = request.getParameter("passwordConf");
            String address = request.getParameter("address");
            String postalCode = request.getParameter("postalCode");
                        
            if (as.checkEmail(email)) {
                ArrayList<String> messages = as.insert(firstName, lastName, email, emailConf, password, passwordConf, address, postalCode); //get and error message
                if (messages.size() != 0) {
                    for (int i = 0; i < messages.size(); i++){
                        if (messages.get(i).equals("firstNull")){
                            request.setAttribute("errorFirst", "First name is required");
                        }
                        if (messages.get(i).equals("lastNull")){
                            request.setAttribute("errorLast", "Last name is required");
                        }
                        if (messages.get(i).equals("emailUnmatch")){
                            request.setAttribute("errorEmail", "Email do not match");
                        }
                        if (messages.get(i).equals("passUnmatch")){
                            request.setAttribute("errorPass", "Password do not match");
                        }
                        if (messages.get(i).equals("addressInvalid")){
                            request.setAttribute("errorAddress", "Address is invalid");
                        }
                        if (messages.get(i).equals("passUnmatch")){
                            request.setAttribute("errorPostal", "Follow format A1A 1A1");
                        }
                    }
                    getServletContext().getRequestDispatcher("/WEB-INF/SignUp.jsp");
                    return;
                }
                else {
                    response.sendRedirect("Login.jsp");
                    return;
                }
            }
            else {
                request.setAttribute("message", "Email already in use.");
            }
        } catch(Exception e) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.WARNING, null, e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(request,response);
    }
}
