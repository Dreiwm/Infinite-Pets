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
import models.Service;
import models.Servicetype;
import services.AdminPetServices;
import services.PetServicesServices;

/**
 *
 * @author BTran
 */
public class AdminPetServicesServlet extends HttpServlet {
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
            session.invalidate();
            response.sendRedirect("Login");
        }
        try {
            String action = request.getParameter("action");            
            if (action.equals("edit")){
                response.sendRedirect("Service");
            }
            else if(action.equals("delete")){
                String serviceID = request.getParameter("serviceID");
                AdminPetServices aps = new AdminPetServices();
                aps.deleteService(serviceID);
            }
            
            PetServicesServices petSS = new PetServicesServices();
            List<Service> services = petSS.getAllServices();
            List<Servicetype> serviceTypes = petSS.getAllServiceTypes();
            System.out.println("Setting services");
            request.setAttribute("services", services);
        } catch(Exception e) {
            Logger.getLogger(AdminPetServicesServlet.class.getName()).log(Level.WARNING, null, e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/AdminPetServices.jsp").forward(request,response);
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
