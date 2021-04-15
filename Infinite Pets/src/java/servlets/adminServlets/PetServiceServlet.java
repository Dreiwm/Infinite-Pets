/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.adminServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Service;
import services.PetServicesServices;

/**
 *
 * @author BTran
 */
public class PetServiceServlet extends HttpServlet {
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
        //Validate session
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        if (email.equals("") || email == null){
            session.invalidate();
            response.sendRedirect("Login");
        }
        try {
            //get serviceID then populate fields for editing
            int serviceID = Integer.parseInt(request.getParameter("serviceID"));
            PetServicesServices pss = new PetServicesServices();
            Service service = pss.getService(serviceID);
            request.setAttribute("service", service);
        } catch(Exception e){
            Logger.getLogger(PetServiceServlet.class.getName()).log(Level.WARNING, null, e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Service.jsp").forward(request,response);
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
        //retrieve entered info upon save button, update service object then redirect back to AdminPetServices.jsp
        try {
            int serviceID = Integer.parseInt(request.getParameter("serviceID"));
            String serviceName = request.getParameter("serviceName");
            String bPrice = request.getParameter("basePrice");
            BigDecimal basePrice = new BigDecimal(bPrice);
            String active = request.getParameter("active");
            String dateRange = request.getParameter("dateRange");
            
            System.out.printf("ServiceID: %d, Name: %s, Price: %d, Active: %s, Date: %s%n", serviceID, serviceName, basePrice, active, dateRange);
            
        }catch(Exception e){
            Logger.getLogger(PetServiceServlet.class.getName()).log(Level.WARNING, null, e);
        }
    }
}
