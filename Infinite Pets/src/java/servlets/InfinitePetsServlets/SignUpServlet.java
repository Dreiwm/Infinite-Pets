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
import models.Location;
import services.AccountServices;
import services.ValidationServices;
import servlets.petServlets.AddPetServlet;

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
        getServletContext().getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(request, response);
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
        ValidationServices vs = new ValidationServices();
        AccountServices as = new AccountServices();
        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String street = request.getParameter("address");
            String area = request.getParameter("area");
            String city = request.getParameter("city");
            String prov = request.getParameter("prov");
            String country = request.getParameter("country");
            String postalCode = request.getParameter("postal");
            String email = request.getParameter("email");
            String emailConf = request.getParameter("emailConfirm");
            String pass = request.getParameter("password");
            String passConf = request.getParameter("passwordConfirm");
            //will create an account if info is all valid
            if (vs.verifyLogin(firstName, lastName, street, city, prov, country, postalCode, area, email, emailConf, pass, passConf)) {
                Location address = as.createAddress(postalCode, street, city, country, prov, area);
                as.createUserAccount(firstName, lastName, email, pass, address);
                response.sendRedirect("Login");
            }
            else {
                getServletContext().getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(request, response);
            }
        } catch (Exception e) {
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.WARNING, null, e);
        }
    }
}
