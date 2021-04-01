/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.InfinitePetsServlets;

import dataaccess.AccountDB;
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
import models.Location;

/**
 *
 * @author BTran
 */
public class MyProfileServlet extends HttpServlet {
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
        //HttpSession session = request.getSession();
        try {
            //get session information
            String email = "cprg352+anne@gmail.com";
            AccountDB accDB = new AccountDB();
            Account account =  accDB.getAccountByEmail(email);
            //get all account info for logged in user
            String firstName = account.getFirstName();
            String lastName = account.getLastName();
            String password = account.getPassword();
            Location location = account.getAddress();
            String address = location.getAddress();
            String city = location.getCity();
            String country = location.getCountry();
            String area = location.getArea();
            String prov = location.getProvince();
            String postal = location.getPostalCode();
            
            System.out.printf("Email: %s, First: %s, Last: %s, Pass: %s, Address: %s, City: %s, Country: %s, Prov: %s, Postal: %s", email, firstName, lastName, password, address, city, country, prov, postal);
            //set the form with info
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("address", address);
            request.setAttribute("area", area);
            request.setAttribute("city", city);
            request.setAttribute("prov", prov);
            request.setAttribute("country", country);
            request.setAttribute("postal", postal);
            request.setAttribute("email", email);
            request.setAttribute("password", password);

        } catch (Exception e) {
            Logger.getLogger(DeleteAccountServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/MyProfile.jsp").forward(request,response);
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
