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
import services.ValidationServices;
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
//        ses.setAttribute("email", "bccrs.test@gmail.com");
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
            emp.setUserID(new Account(1, "asdf", "asdf@asdf.com", "Jennifer", "Lovegreen", true, true));
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
        
        
        /***************************
         * TEMP ONLY - REMOVE ME
         **************************/
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
        emp.setUserID(new Account(1, "asdf", "asdf@asdf.com", "Jennifer", "Lovegreen", true, true));
        appt.setEmployeeID(emp);


        String action = (String) request.getParameter("action");
        
        // Update appt
        // execute if attribute of updateAppt is not null

        // Only exexcute if action is not null.
        if (action != null) {
            // now check for update
            if (action.equals("updateAppt")) {
                System.out.println("updating appointment...");
                
                
                // Create appointment object and send email to staff (if not null)
                int apptId = Integer.parseInt(request.getParameter("apptId"));
                
                  Appointment apptToUpdate = getAppointmentDateFromParameters(request, response);

//                setDateAttributes(appt, request, response);
                setAppointmentAttribute(appt, request, response);
                setDateAttributes(appt, request, response);
                getServletContext().getRequestDispatcher("/WEB-INF/Appointment.jsp").forward(request, response);
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
     * Sets the temporary Appointment object constructed from inputs via doGet method.
     * @param appt Appointment object to be used.
     * @param request
     * @param response 
     */
    private void setTempAppointmentAttribute(Appointment appt, HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(appt.getAppointmentDate());
        
        
        String selectMonth = (String) request.getParameter("selectMonth");
        String selectDayOfMonth = request.getParameter("selectDayOfMonth");
        String selectStartYear = request.getParameter("selectYear");
        String selectScheduleBlock = request.getParameter("selectScheduleBlock");
        
        // Now we need to test to see if either of them is not null, if not null
        // means that part needs to be "updated".
        
        // We need to build string date using SDF (SimpleDateFormat)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMMM-dd hh");
        String dateString = "";
        
        // year null?
        if (selectStartYear != null) {
            dateString += selectStartYear;
        } else {
            dateString += cal.get(Calendar.YEAR);
        }
        
        // month null?
        if (selectMonth != null) {
            dateString += "-" + selectMonth;
        } else {
            dateString +=  "-" + CalendarUtilities.getMonthName(cal.get(Calendar.MONTH));
        }
        
        // day null? 
        if (selectDayOfMonth != null) {
            dateString += "-" + selectDayOfMonth;
        } else {
            SimpleDateFormat sdfTemp = new SimpleDateFormat("MMM");
            // test to see if days > allowed days
            // we also need to see if month is not null or null.
            // if null, use from appt
            // if not null, use from selectDayOfMonth
            if (selectMonth != null) {
                // now test if day > allowed days
//                int daySelected = Integer.parseInt(sdf);
            }
            
            
            dateString += "-" + cal.get(Calendar.DAY_OF_MONTH);
        }
        
        if (selectScheduleBlock != null) {
            ArrayList<String> list = (ArrayList) ScheduleServices.getScheduleBlockList();
            
            for (String schBlock : list) {
                if (schBlock.equals(selectScheduleBlock)) {
                    dateString += " " + ScheduleServices.getScheduleBlock(schBlock);
                    break;
                } 
            } 
        } else {
            dateString += " " + ScheduleServices.getScheduleBLockStartTime(appt);
        }
        
        Appointment tempAppt = appt;
        appt.setAppointmentDate(sdf.parse(dateString));
        request.setAttribute("tempAppt", tempAppt);
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
        
        // for months selection
        request.setAttribute("months", CalendarUtilities.getMonths());
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
        Appointment appt = null;
        
        
//        appt = schs.getAppointmentById(apptId);
//        if (appt == null) {
//            request.setAttribute("errorMsg", "Uh oh! Something went wrong with udpating your appointment.");
//        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd-hh");
        sdf.setLenient(false); // so that February 31 2021 will throw an error.
        
        try {
            sdf.parse(year + "-" + month + "-" + dayOfMonth + "-" + hour);
        } catch (ParseException e1) {
            request.setAttribute("errorMsg", "An error in dates was found, please check again.");
        }
        return null;
    }
    


}
