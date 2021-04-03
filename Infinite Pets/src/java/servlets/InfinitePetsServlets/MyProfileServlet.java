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
            System.out.println("My profile servlet");
            //get session information
            String email = "cprg352+anne@gmail.com";
            AccountDB accDB = new AccountDB();
            Account account =  accDB.getAccountByEmail(email);
            System.out.println("getting account information");
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
            
            System.out.printf("Email: %s, First: %s, Last: %s, Pass: %s, Address: %s, Area: %s, City: %s, Country: %s, Prov: %s, Postal: %s%n", email, firstName, lastName, password, address, area, city, country, prov, postal);
            System.out.println("setting servlet attribute stuff");
            //set the form with info
            request.setAttribute("firstName", account.getFirstName());
            request.setAttribute("lastName", account.getLastName());
            request.setAttribute("address", location.getAddress());
            request.setAttribute("area", location.getArea());
            request.setAttribute("city", location.getCity());
            request.setAttribute("prov", location.getProvince());
            request.setAttribute("country", location.getCountry());
            request.setAttribute("postal", location.getPostalCode());
            request.setAttribute("email", account.getEmail());
            request.setAttribute("password", account.getPassword());
;
            getServletContext().getRequestDispatcher("/WEB-INF/MyProfile.jsp").forward(request,response);
        } catch (Exception e) {
            Logger.getLogger(DeleteAccountServlet.class.getName()).log(Level.SEVERE, null, e);
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
}
