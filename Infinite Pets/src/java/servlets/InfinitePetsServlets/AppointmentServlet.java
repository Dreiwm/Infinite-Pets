/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.InfinitePetsServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Appointment;
import models.Employee;
import models.Pet;
import models.Service;
import services.AccountServices;
import services.EmailService;
import services.PasswordServices;
import services.ScheduleServices;
import services.ValidationServices;
import services.exceptions.AppointmentException;
import utilities.CalendarUtilities;

/**
 *
 * @author Riley
 */
public class AppointmentServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        HttpSession ses = request.getSession();
        

        Enumeration<String> e = ses.getAttributeNames();
        System.out.println("stuff in session");
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
        
        String email = (String) ses.getAttribute("email");
        System.out.println(email);
        AccountServices acs = new AccountServices();
        Account acc = null;
        try {
            acc = acs.getAccount(email);
        } catch (Exception ex) {
            Logger.getLogger(DeleteAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (acc != null) {
            // Account not null meaning session is still in.

            // get appointment info using apptID
            ScheduleServices schs = new ScheduleServices();
            // get ID for appt
            Appointment appt = null;
            int apptId;
            try {
                apptId = Integer.parseInt(request.getParameter("apptId"));
                appt = schs.getAppointmentById(apptId);

            } catch (NumberFormatException e1) {
                apptId = Integer.parseInt((String) request.getAttribute("apptId"));
            }
            
               
            System.out.println("appt date: " + appt.getAppointmentDate());
            System.out.println("appt time: " + appt.getAppointmentTime());
           

        /**
        * **************************************
        * Start Date Setting Attributes for presenting
        **************************************
        */
            setDateAttributes(appt, request, response);
            setAppointmentAttribute(appt, request, response);

            getServletContext().getRequestDispatcher("/WEB-INF/Appointment.jsp").forward(request, response);
        } else {
            // send user to login page
            ses.invalidate();
            response.sendRedirect("Login");
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
        
        HttpSession ses = request.getSession();
        String email = (String) ses.getAttribute("email");
        
        AccountServices acs = new AccountServices();
        ScheduleServices schs = new ScheduleServices();
        
        Account acc = null;
        try {
            acc = acs.getAccount(email);
        } catch (Exception ex) {
            Logger.getLogger(AppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /***************************
         * TEMP ONLY - REMOVE ME
         **************************/
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            
//
//        Appointment appt = null;
//        try {
//            appt = new Appointment(1, sdf.parse("2021-03-30 06:00"), true, true, true);
//        } catch (ParseException ex) {
//            Logger.getLogger(AppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        appt.setEndDate(new Date());
//        appt.setPetID(new Pet(1, 'M', "Dog", "lab", "Eileen", new Date()));
//        appt.setServiceID(new Service(1, "test", new BigDecimal(12.0), true, true, true));
//        Employee emp = new Employee(1, false, false, true);
//        emp.setUserID(acc);
//        appt.setEmployeeID(emp);

        int apptId = Integer.parseInt(request.getParameter("apptId"));
        Appointment appt = schs.getAppointmentById(apptId);

        String action = (String) request.getParameter("action");
        
        // Update appt
        // execute if attribute of updateAppt is not null

        // Only exexcute if action is not null.
        if (action != null) { 
            // now check for update
            if (action.equals("updateAppt")) {
                System.out.println("updating appointment...");
                // Create appointment object and send email to staff (if not null)
                
                // set new info to appointment from parameters
                String month, day, year, schBlock;
                int hour;
                SimpleDateFormat sdf = new SimpleDateFormat();
                sdf.applyPattern("MMM-dd-yyyy");
                sdf.setLenient(false); // so it'll throw error if date is wrong
                
                month = request.getParameter("selectMonth");
                day = request.getParameter("selectDayOfMonth");
                year = request.getParameter("selectYear");
                schBlock = request.getParameter("selectScheduleBlock");
                
                hour = ScheduleServices.getScheduleBlock(schBlock);
                Date newDate = null;
                try {
                    // For appointmentDate only
                    newDate = sdf.parse(month + "-" + day + "-" + year); 
                    
                    sdf.applyPattern("hh");
                    appt.setAppointmentTime(sdf.parse(Integer.toString(hour)));
                    appt.setAppointmentDate(newDate);
                    
                    schs.updateAppointment(appt);
                    
                } catch (ParseException ex) {
                    request.setAttribute("errorMsg", "Something is wrong with date, please try again.");
                    Logger.getLogger(AppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
//                schs.updateAppointment(apptId, newDate, appt.getConfirmed(), appt.getPaid(), appt.getPaid(), appt.getPetID().getPetID(), acc.getUserId());
                    

                
                
                appt = schs.getAppointmentById(apptId);
                setAppointmentAttribute(appt, request, response);
                setDateAttributes(appt, request, response);
                

                getServletContext().getRequestDispatcher("/WEB-INF/Appointment.jsp").forward(request, response);
            } else if (action.equals("reqCancelAppt")) {
                EmailService ems = new EmailService();
                String path = getServletContext().getRealPath("/assets");
                
                System.out.println("cancelling appointment...");
                               
                try {
                    schs.cancelAppointment(apptId);
//                    ems.sendCancellationNotification(appt, new Date(), path);
                    getServletContext().getRequestDispatcher("/WEB-INF/Appointment.jsp").forward(request, response);
                } catch (NullPointerException e1) {
                    request.setAttribute("errorMsg", "Uh oh! Something went wrong. Please try again.");
                    Logger.getLogger(AppointmentServlet.class.getName()).log(Level.SEVERE, null, e1);
                    getServletContext().getRequestDispatcher("/WEB-INF/Appointment.jsp").forward(request, response);
                } catch (AppointmentException e1) {
                    request.setAttribute("errorMsg", e1.getMessage());
                    getServletContext().getRequestDispatcher("/WEB-INF/Appointment.jsp").forward(request, response);
                } catch (Exception ex) {
                    Logger.getLogger(AppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
                    getServletContext().getRequestDispatcher("/WEB-INF/Appointment.jsp").forward(request, response);
                }            
            }
        }
        
        
        // request cancel appointment
        // executes if the reqCancelAppt is not null.
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * Sets the appointment object to attribute called "appt".
     * @param appt Appointment object to be used.
     * @param request
     * @param response 
     */
    private void setAppointmentAttribute(Appointment appt, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("appt", appt);
    }
    
    /**
     * Sets all necessary attributes every time page loads 
     * @param appt Appointment object to be used
     * @param request
     * @param response 
     */
    private void setDateAttributes(Appointment appt, HttpServletRequest request, HttpServletResponse response) {
        // Start date
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(appt.getAppointmentDate());
        
        // Time
        
        
            request.setAttribute("startYear", calStart.get(Calendar.YEAR));
        
        
            request.setAttribute("startMonth", CalendarUtilities.getMonthName(calStart.get(Calendar.MONTH)));

            request.setAttribute("startDayOfMonth", calStart.get(Calendar.DAY_OF_MONTH));
        
        /*
         * IMPORTANT: this part determines the max numbeer of days 
         * Used for select/option element in JSP.
         * Determined by using year and month - gleaned from attributes (NOT parameters!, since we already set attributes above)
        */
        try {
            int maxNumOfDays = getNumMaxOfDays((String) request.getAttribute("startMonth"), request.getAttribute("startYear").toString());
            request.setAttribute("maxNumOfDays", maxNumOfDays);
        } catch (ParseException ex) {
            Logger.getLogger(AppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // get year from appt for minimum value user can set
        request.setAttribute("minYearFromAppt", calStart.get(Calendar.YEAR));
        
        // list of schedule blocks
        request.setAttribute("schBlocks", ScheduleServices.getScheduleBlockList());
        
            request.setAttribute("schBlock", ScheduleServices.getScheduleBlock(appt));

        
        // for months selection
        request.setAttribute("months", CalendarUtilities.getMonths());
    }

    /**
     * Sets number of days based on given month
     * @param month the month to be used to determine number of days
     * @return the integer representing max num of days.
     */
    private int getNumMaxOfDays(String month, String year) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMMM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(year + "-" + month));
        
//        System.out.println(sdf.parse(year + "-" + month));
        
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        
    }
    
    /**
     * Initializes the appointment from the form submission.
     * @param request
     * @param response
     * @return returns Appointment object, which would be used to update an appointment.
     */
    private Appointment getAppointmentDateFromParameters(HttpServletRequest request, HttpServletResponse response) {
        /*************
         * Date
        *************/
        String year, month, dayOfMonth, scheduleBlock, hour;
        
        year = request.getParameter("selectYear");
        month = request.getParameter("selectMonth");
        dayOfMonth = request.getParameter("selectDayOfMonth");
        
        // hour/min
        scheduleBlock = request.getParameter("selectScheduleBlock");
        hour = Integer.toString(ScheduleServices.getScheduleBlock(scheduleBlock));
        
        // get appt id
        int apptId = Integer.parseInt(request.getParameter("apptId"));
        
        
        // get Appt from DB
        ScheduleServices schs = new ScheduleServices();
        Appointment appt = schs.getAppointmentById(apptId);
        if (appt == null) {
            request.setAttribute("errorMsg", "Uh oh! Something went wrong with udpating your appointment.");
        }
        
        return null;
        
    }

    /**
     * Sets attributes related to AppointmentrServices with given Appointment object.
     * WIll be using appt ID from said object to determine which servies and pets belong to which appointment.
     * @param appt the Appointment object used to return appoinment services/perts belong to given appt.
     * @param request
     * @param response 
     */
    private void setAppointmentServiceAttributes(Appointment appt, HttpServletRequest request, HttpServletResponse response) {
        ScheduleServices ss = new ScheduleServices();
        
        request.setAttribute("apptServices", ss.getAllAppointmentServices(appt));
    }
    


}
