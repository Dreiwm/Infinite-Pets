/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.scheduleServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Appointmentservice;
import models.Pet;
import models.Service;
import services.AccountServices;
import services.AddPetServices;
import services.PetServicesServices;
import services.ScheduleServices;
import services.ServiceServices;

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
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        if (email==null||email.equals("")){
            session.invalidate();
            response.sendRedirect("Login");
        }
        
        request = setPage(request);
        
        getServletContext().getRequestDispatcher("/WEB-INF/BookAppointment.jsp").forward(request,response);

    }

    public HttpServletRequest setPage(HttpServletRequest request){
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        ScheduleServices ss = new ScheduleServices();
        
        List<Pet> petList = new ArrayList<Pet>();
        List<Service> serviceList = new ArrayList<Service>();
        List<String> timeList = ss.getScheduleBlockList();
        try {
            PetServicesServices pss = new PetServicesServices();
            AccountServices as  = new AccountServices();
            Account user = as.getAccount(email);
            petList = user.getPetList();
            serviceList = pss.getAllServices();
            
        } catch (Exception ex) {
            Logger.getLogger(BookAppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("petList", petList);
        request.setAttribute("serviceList", serviceList);
        request.setAttribute("timeList", timeList);
        return request;
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
        AccountServices as = new AccountServices();
        
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        
        String date = (String)request.getParameter("setDate");
        String time = (String)request.getParameter("setTime");
        boolean error = false;
        
        
        if((request.getParameterValues("petName")!=null)&&
                (time!=null)&&(date!=null)&&
                (request.getParameterValues("serviceName")!=null)){            
            try {
               
                SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
                Date appointmentDate = formater.parse(date);

                //Get time integer from the ScheduleServices 
                int timeInt = 0;
                timeInt = ScheduleServices.getScheduleBlock(time);
                //Convert the time int into a string and format into a localtime string 
                if(timeInt<10)
                    time = "0"+String.valueOf(timeInt)+":00";
                else
                    time = String.valueOf(timeInt)+":00";
                //create the localTime object and then convert it to a sql time object
                LocalTime localTime = LocalTime.parse(time);
                Time appointmentTime = Time.valueOf(localTime);
                //get the full list of pets from the user
                Account user = as.getAccount(email);
                List<Pet> pets = user.getPetList();
                List<Appointmentservice> contents = new ArrayList<Appointmentservice>();
                Pet pet = null;    
                
       
                
                for(String id : request.getParameterValues("petName")){
                    pet = getPetFromUser(id, pets);
                    if(pet==null)
                    {
                        break;
                    }
                    for(String service: request.getParameterValues("serviceName")){
                        System.out.println("Pet: " +pet.getPetName()+" service: "+service);
                        Service currService = pss.getServiceByName(service); 
                        Appointmentservice apptServ = new Appointmentservice(0);
                        apptServ.setPetID(pet);
                        apptServ.setServiceID(currService);
                        contents.add(apptServ);
                    }
                }
                if(pet==null){
                    System.out.println("error in making appointemtn");
                    error=true;
                }
                else{
                    System.out.println("going to service");
                    serv.createAppointment(appointmentDate, appointmentTime, user, contents);
                    response.sendRedirect("MyAppointments.jsp");
                    return;
                    
                }
            } catch (Exception ex) {
                Logger.getLogger(BookAppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                if(error==true){
                    request.setAttribute("message", "Error in Pet list");
                    request = setPage(request);
                    getServletContext().getRequestDispatcher("/WEB-INF/BookAppointment.jsp").forward(request,response);
                    return;
                }

            }

        }
        request.setAttribute("message", "Error In Entries");
        request = setPage(request);
        getServletContext().getRequestDispatcher("/WEB-INF/BookAppointment.jsp").forward(request,response);
        //Check for errors 
        //if none redirect to appointment page
        //else back to booking with error shown
    }
    
    private Pet getPetFromUser(String name, List<Pet> pets){
        
        for(Pet pet: pets){
            if(pet.getPetName().equalsIgnoreCase(name))
                return pet;
        }
        return null;
    }
    
}
