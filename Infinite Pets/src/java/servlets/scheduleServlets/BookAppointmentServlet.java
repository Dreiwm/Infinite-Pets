/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.scheduleServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Service;
import services.PetServicesServices;
import services.ScheduleServices;

/**
 *
 * @author BTran
 */
public class BookAppointmentServlet extends HttpServlet {
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
                getServletContext().getRequestDispatcher("/WEB-INF/BookAppointment.jsp").forward(request,response);

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
        ArrayList services;
        ScheduleServices serv = new ScheduleServices();
        PetServicesServices pss = new PetServicesServices();
        String date = (String)request.getParameter("setDate");
        String time = (String)request.getParameter("setTime");
        
        if((request.getParameterValues("petName")!=null)&&
                (time!=null)&&(date!=null)){            
            try {
                Date appointmentDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
              
                for(String id : request.getParameterValues("petName")){
                    for(String service: request.getParameterValues("service"+id)){
                        
//                        services.add(service);
                        Service currService = pss.getService(service);    
                        serv.createAppointment(currService.getServiceID(), appointmentDate, true, true, true, 0, 0);
                    }
                }
            } catch (Exception ex) {
                //Logger.getLogger(BookAppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
                //Book the appointment
            
        }
        //Check for errors 
        //if none redirect to appointment page
        //else back to booking with error shown
    }
}
