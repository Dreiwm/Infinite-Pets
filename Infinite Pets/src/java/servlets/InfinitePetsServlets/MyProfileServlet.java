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
import services.AccountServices;
import services.ValidationServices;

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
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            if (email.equals("") || email == null){
                response.sendRedirect("Login");
            }
//            String email = "cprg352+anne@gmail.com"; //REMOVE THIS PART AND UNCOMMENT ABOVE
//            session.setAttribute("email", email);  //REMOVE THIS AS WELL SINCE SESSION SHOULD ALREADY BE SET FROM LOGIN
            AccountDB accDB = new AccountDB();
            Account account =  accDB.getAccountByEmail(email);
            System.out.println("getting account information");
            //get all account info for logged in user TEST PURPOSES
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
            //FOR TESTING
            System.out.printf("Email: %s, First: %s, Last: %s, Pass: %s, Address: %s, Area: %s, City: %s, Country: %s, Prov: %s, Postal: %s%n", email, firstName, lastName, password, address, area, city, country, prov, postal);
            
            System.out.println("setting servlet attribute stuff");
            //set the form with info
            request.setAttribute("firstName", account.getFirstName());
            request.setAttribute("lastName", account.getLastName());
            request.setAttribute("address", location.getAddress());
            request.setAttribute("area", location.getArea().toUpperCase());
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
        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("user");
            
            if (email.equals("") || email == null){
                response.sendRedirect("Login");
            }
            String action = request.getParameter("action");
            if (action.equals("save")){
                System.out.println("action is on save");
                System.out.println("System getting info");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String address = request.getParameter("address");
                String area = request.getParameter("area");
                String city = request.getParameter("city");
                String prov = request.getParameter("prov");
                String country = request.getParameter("country");
                String postal = request.getParameter("postal");
//                String email = request.getParameter("email");
                String pass = request.getParameter("password");
                
                ValidationServices vs = new ValidationServices();
                System.out.println(session);
                System.out.printf("Email1: %s, Pass1: %s, First: %s, Last: %s, Pass: %s, Address: %s, City: %s, Country: %s, Prov: %s, Postal: %s%n", email, pass, firstName, lastName, pass, address, city, country, prov, postal);
            
            //will create an account if info is all valid
            System.out.println("System verifying info");
            if (vs.verifyInfo(firstName, lastName, address, city, prov, country, postal, area, email, pass)) {
                AccountServices accs = new AccountServices();
                accs.updateUserAccount(pass, email, firstName, lastName, true, address, city, prov, country, postal, area);
            }
            else if (action.equals("edit")){
                System.out.println("action is on edit");
            }
            }
            getServletContext().getRequestDispatcher("/WEB-INF/MyProfile.jsp").forward(request,response);
        } catch(Exception e) {
            Logger.getLogger(DeleteAccountServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
