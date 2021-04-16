/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.adminServlets;

import dataaccess.EmployeeDB;
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
import models.Employee;
import models.Location;
import models.Service;
import services.AccountServices;
import services.AdminPetServices;
import services.ServiceServices;
import services.ValidationServices;
import servlets.petServlets.AddPetServlet;

/**
 *
 * @author Chris
 */
public class AdminNewServiceServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
        String action = request.getParameter("action");
        try {
            if ((!action.equals("") || action != null) && action.equals("edit")){
                request.setAttribute("action", "update");  
                int serviceID = Integer.parseInt(request.getParameter("serviceID"));

                ServiceServices ss = new ServiceServices();
                Service service = new Service();

                service = ss.getServiceById(serviceID);
                request.setAttribute("service", service);
                request.setAttribute("serviceID", serviceID);
            }
            else if ((!action.equals("") || action != null) && action.equals("create")){
                request.setAttribute("action", "create");  
            }
        } catch (Exception ex) {
                Logger.getLogger(AdminNewServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/NewServices.jsp").forward(request,response);
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
       String action = request.getParameter("action");
       String name = request.getParameter("name");
       double price = Double.parseDouble(request.getParameter("price"));
       String des = request.getParameter("description");
       boolean active = Boolean.parseBoolean(request.getParameter("active"));
       
       try{
            ValidationServices vs = new ValidationServices();
            AdminPetServices aps = new AdminPetServices();
            ServiceServices ss = new ServiceServices();
            Service service = new Service();

            if((!action.equals("") || action != null) && aps.checkServiceInfo(name, price, des)){
                if (action.equals("create")){                       
                    aps.createService(name, price, des, active);

                }
                else if ((!action.equals("") || action != null) && action.equals("update")){
                    int serviceID = Integer.parseInt(request.getParameter("serviceID"));
                    service = ss.getServiceById(serviceID);
                    aps.updateService(service, name, price, des, active);
                }
                else {
                    getServletContext().getRequestDispatcher("/WEB-INF/NewServices.jsp").forward(request,response);
                }
            }
        } catch(Exception e) {
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("message", "Something went wrong :"+action);
        }
        response.sendRedirect("Service");
       
    }
}
