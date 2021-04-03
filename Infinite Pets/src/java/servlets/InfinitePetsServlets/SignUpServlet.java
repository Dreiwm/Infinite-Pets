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
        System.out.println("GET");
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
            System.out.println("System getting info");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String address = request.getParameter("address");
            String area = request.getParameter("area");
            String city = request.getParameter("city");
            String prov = request.getParameter("prov");
            String country = request.getParameter("country");
            String postal = request.getParameter("postal");
            String email = request.getParameter("email");
            String emailConf = request.getParameter("emailConf");
            String pass = request.getParameter("password");
            String passConf = request.getParameter("passwordConf");
            
            System.out.printf("Email1: %s, Email2: %s, Pass1: %s, Pass2: %s, First: %s, Last: %s, Pass: %s, Address: %s, City: %s, Country: %s, Prov: %s, Postal: %s%n", email, emailConf, pass, passConf, firstName, lastName, pass, address, city, country, prov, postal);

            
            //will create an account if info is all valid
            System.out.println("System verifying info");
            if (vs.verifyInfo(firstName, lastName, address, city, prov, country, postal, area, email, emailConf, pass, passConf)) {
                System.out.println("creating location");
                Location location = as.createAddress(postal, address, city, country, prov, area);
                System.out.println("creating account");
                as.createUserAccount(firstName, lastName, email, pass, location);
                System.out.println("re-direct to Login");
                response.sendRedirect("Login");
            }
            else {
                System.out.println("SignUp");
                getServletContext().getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(request, response);
            }
        } catch (Exception e) {
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.WARNING, null, e);
        }
    }
}
