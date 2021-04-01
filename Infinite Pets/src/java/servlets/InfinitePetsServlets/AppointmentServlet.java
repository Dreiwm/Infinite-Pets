/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.InfinitePetsServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import services.ScheduleServices;
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
            
        
        
        
        
        
        /**
         * *****************************
         *
         * ***********
         * REMOVE ME * 
         *************
         *
         *****************************
         */
        ses.setAttribute("email", "bccrs.test@gmail.com");
        // Pretend that session is valid. Remove above when session is working properly.

        String email = (String) ses.getAttribute("email");
//        System.out.println(email);
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
//            int apptId = Integer.parseInt(request.getParameter("apptID"));
//            Appointment appt = schs.getAppointmentById(apptId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            

            Appointment appt = null;
            try {
                appt = new Appointment(1, sdf.parse("2021-03-30 06:00"), true, true, true);
            } catch (ParseException ex) {
                Logger.getLogger(AppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            appt.setEndDate(new Date());
            appt.setPetID(new Pet(1, 'M', "Dog", "lab", "Eileen", new Date()));
            appt.setServiceID(new Service(1, "test", new BigDecimal(12.0), true, true, true));
            Employee emp = new Employee(1, false, false, true);
//            emp.setUserID(new Account(1, "asdf", "asdf", "asdf@asdf.com", "Jennifer", "Lovegreen", true, true));
            appt.setEmployeeID(emp);

            

            /*
            1. We need to parse Dates to string for presenting
                - We'll need to format it to what we need using CalendarUtilities
                - For presenting purposes, we don't need to be converned about
                  time range, because when booking an appointment, it will be determined
                  how long and such.
                - however, in doPost part (where you update your dates), we'll need to construct
                  a string to be parsed into Date, disgarding the day/year/month, only get hours and minutes values.
             */
//            String startYear, startMonth, startDay, startHour, startMin;
//            String endYear, endMonth, endDay, endHour, endMin;
            /**
             * **************************************
             * Start Date Setting Attributes for presenting
             **************************************
             */
            setDateAttributes(appt, request, response);
            
            
            
            
            // for months selection
            request.setAttribute("months", CalendarUtilities.getMonths());
            
            
            /**
             * **************************************
             * End Date Setting Attributes for presenting
             * ABORTED - only use schedule blocks
             **************************************
             */
//            setEndAttributes(appt, request, response);
            

            // We need to get all attributes, add to URL
            String paramBuilder = "";
            if (request.getParameter("selectYear") == null) {
//                paramBuilder += "?selectYear=" + request.getAttribute("startYear");
//                getServletContext().setInitParameter("selectYear", request.getAttribute("startYear").toString());
            }
            if (request.getParameter("selectMonth") == null) {
//                paramBuilder += "?selectMonth" + request.getAttribute("startMonth");
//                getServletContext().setInitParameter("selectMonth", (String) request.getAttribute("startMonth"));
                
            }
            
           

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

    private void setDateAttributes(Appointment appt, HttpServletRequest request, HttpServletResponse response) {
        // Start date
        Calendar calStart = Calendar.getInstance();
        calStart.setTime(appt.getAppointmentDate());

        SimpleDateFormat sdf = new SimpleDateFormat("hh");        
        // Start date - year, month, day
        
        String selectMonth = request.getParameter("selectMonth");
        String selectYear = request.getParameter("selectYear");
        String selectDayOfMonth = request.getParameter("selectDayOfMonth");
        // If selectMonth or selectYear parameter is not null, set attributes from parameters
        // otherwise, get from appointment.
        if (selectYear != null) {
            request.setAttribute("startYear", selectYear);
        } else {
            request.setAttribute("startYear", calStart.get(Calendar.YEAR));
        }
        
        if (selectMonth != null) {
            request.setAttribute("startMonth", selectMonth);
        } else {
            request.setAttribute("startMonth", CalendarUtilities.getMonthName(calStart.get(Calendar.MONTH)));
        }
        
        // Similar vein, if selectDayOfDay is not null, set attribute from parameter
        // otherwise, get from appt
        if (selectDayOfMonth != null) {
            request.setAttribute("startDayOfMonth", selectDayOfMonth);
        } else {
            request.setAttribute("startDayOfMonth", calStart.get(Calendar.DAY_OF_MONTH));

        }
        
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
        
        // Start Time related
        // Only concenred with schedule blocks
        String selectScheduleBlock = request.getParameter("selectScheduleBlock");
        
        
        // list of schedule blocks
        request.setAttribute("schBlocks", ScheduleServices.getScheduleBlockList());
        
        if (selectScheduleBlock != null) {
            request.setAttribute("schBlock", selectScheduleBlock);
        } else {
            request.setAttribute("schBlock", ScheduleServices.getScheduleBlock(appt));

        }
    }
    
    
    private String getMonthFromParameter(HttpServletRequest request) {
        return (String) request.getParameter("selectMonth)");
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
    


}
