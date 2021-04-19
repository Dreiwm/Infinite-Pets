/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.InfinitePetsServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Appointment;
import models.Appointmentservice;
import models.Employee;
import services.AccountServices;
import services.EmailService;
import services.ScheduleServices;
import services.ServiceServices;
import services.exceptions.AppointmentException;
import utilities.CalendarUtilities;
import services.exceptions.AppointmentException;

/**
 *
 * @author Riley
 */
@WebServlet(name = "AppointmentServlet", urlPatterns = {"/Appointment"})
public class AppointmentServlet extends HttpServlet {

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
        String email = (String) ses.getAttribute("email");
        if (email.equals("") || email == null){
            ses.invalidate();
            response.sendRedirect("Login");
        }
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
            Appointment appt = null;
            int apptId;

            String reqParamForId = request.getParameter("apptId");
            System.out.println(reqParamForId);
            if (reqParamForId != null) {
                apptId = Integer.parseInt(request.getParameter("apptId"));
                appt = schs.getAppointmentById(apptId);
            } else {
                apptId = Integer.parseInt((String) request.getAttribute("apptId"));
                appt = schs.getAppointmentById(apptId);
            }
            // Check if this appt doesn't beong to owner
            // if not, redirect to login page.
            if (!appt.getClientID().equals(acc)) {
                System.out.println("This appointment don't belong to associated account!");
                ses.invalidate();
                response.sendRedirect("Login");
                return;
            }

//            LocalTime localTime = LocalTime.parse(time);
//                Time appointmentTime = Time.valueOf(localTime);
            System.out.println("appt date: " + appt.getAppointmentDate());
            System.out.println("appt time:" + appt.getAppointmentTime());
//            SimpleDateFormat sdf0 = new SimpleDateFormat("hh:mm:ss");
//            System.out.println("appt time: " + sdf0.format(appt.getAppointmentTime()));
//            System.out.println("local time: " + LocalTime.parse(sdf0.format(appt.getAppointmentTime())));

            // check if action is deleteService
            String action = request.getParameter("action");
            System.out.println("action = " + action);
            if (action != null) {
                if (action.equals("deleteService")) {
                    String apptServiceIdStr = request.getParameter("apptServiceId");
                    if (apptServiceIdStr != null) {
                        int apptServiceId = Integer.parseInt(apptServiceIdStr);

                        // now delete service
                        ServiceServices ss = new ServiceServices();
                        if (!ss.deleteAppointmentService(apptServiceId)) {
                            request.setAttribute("errorMsg", "Something went wrong while attempting to delete service from appointment. <br/> Please do not refresh the page.");
                        }

                        setDateAttributes(appt, request, response);
                        setAppointmentAttribute(appt, request, response);

                        List<Appointmentservice> apptServices = schs.getAllAppointmentServices(appt);
//                        apptServices.forEach(apptService -> {
//                            System.out.println(apptService.getServiceID().getServiceName());
//                        });
                        // don't trust using appt.getService etc. use this method isntead, idk why it isn't working 
                        request.setAttribute("apptServices", apptServices);

                    }
                }
            }

            /**
             * **************************************
             * Start Date Setting Attributes for presenting
             * *************************************
             */
            setDateAttributes(appt, request, response);
            setAppointmentAttribute(appt, request, response);

            List<Appointmentservice> apptServices = schs.getAllAppointmentServices(appt);
            apptServices.forEach(apptService -> {
                System.out.println(apptService.getServiceID().getServiceName());
            });
            // don't trust using appt.getService etc. use this method isntead, idk why it isn't working 
            request.setAttribute("apptServices", apptServices);
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

        /**
         * *************************
         * TEMP ONLY - REMOVE ME ************************
         */
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Appointment appt = null;

        int apptId = Integer.parseInt(request.getParameter("apptId"));
//        Appointment appt;
        appt = schs.getAppointmentById(apptId);

        String action = (String) request.getParameter("action");

        // Update appt
        // execute if attribute of updateAppt is not null
        // Only exexcute if action is not null.
        if (action != null) {
            // now check for update
            if (action.equals("updateAppt")) {
                System.out.println("updating appointment... ...");
                // Create appointment object and send email to staff (if not null)

                // set new info to appointment from parameters
                String month, day, year, schBlock;
                String hour;
                sdf.applyLocalizedPattern("MMM-dd-yyyy");
                sdf.setLenient(false); // so it'll throw error if date is wrong

                month = request.getParameter("selectMonth");
                day = request.getParameter("selectDayOfMonth");
                year = request.getParameter("selectYear");
                schBlock = request.getParameter("selectScheduleBlock");

                hour = ScheduleServices.getScheduleBlockInString(schBlock);
                Date newDate = null;
                try {
                    // For appointmentDate only
                    newDate = sdf.parse(month + "-" + day + "-" + year);
                    
                    sdf.applyLocalizedPattern("kk");

                    
//                    LocalTime localTime = LocalTime.parse(sdf.format(sdf.parse(hour)) + ":00:00");
//                    Time apptTime = Time.valueOf(localTime);
                   // System.out.println(sdf.parse("date: " + hour));
                    appt.setAppointmentTime(sdf.parse(hour));

                    // Make local time 
                    appt.setAppointmentDate(newDate);

                    System.out.println("successfully updated? " + schs.updateAppointment(appt));

                } catch (ParseException ex) {
                    System.out.println("uh oh seomethign wengt wrong? " + ex.getMessage());
                    request.setAttribute("errorMsg", "Something is wrong with date, please try again.");
                    Logger.getLogger(AppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    System.out.println("uh oh seomethign wengt wrong? " + ex.getMessage());
                    request.setAttribute("errorMsg", "Something is wrong with date, please try again.");
                    Logger.getLogger(AppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

//                schs.updateAppointment(apptId, newDate, appt.getConfirmed(), appt.getPaid(), appt.getPaid(), appt.getPetID().getPetID(), acc.getUserId());
                appt = schs.getAppointmentById(apptId);
                setAppointmentAttribute(appt, request, response);
                setDateAttributes(appt, request, response);

                List<Appointmentservice> apptServices = schs.getAllAppointmentServices(appt);

                // don't trust using appt.getService etc. use this method isntead, idk why it isn't working 
                System.out.println("setting apptServices attributes..");
                request.setAttribute("apptServices", apptServices);

                response.sendRedirect("MyAppointment");
            } else if (action.equals("reqCancelAppt")) {
                EmailService ems = new EmailService();
                String path = getServletContext().getRealPath("/assets");

                

                try {
                    System.out.println("Cancelling appointment..");
                    schs.cancelAppointment(apptId);
//                    ems.sendCancellationNotification(appt, new Date(), path);
//                    request.setAttribute("errorMsg", "We have sent you a confirmation email.");
//                    getServletContext().getRequestDispatcher("/WEB-INF/Appointment.jsp").forward(request, response);
                    response.sendRedirect("MyAppointment");
                    return;
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
     *
     * @param appt Appointment object to be used.
     * @param request
     * @param response
     */
    private void setAppointmentAttribute(Appointment appt, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("appt", appt);
    }

    /**
     * Sets all necessary attributes every time page loads
     *
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
     *
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
     * Sets attributes related to AppointmentrServices with given Appointment
     * object. WIll be using appt ID from said object to determine which servies
     * and pets belong to which appointment.
     *
     * @param appt the Appointment object used to return appoinment
     * services/perts belong to given appt.
     * @param request
     * @param response
     */
    private void setAppointmentServiceAttributes(Appointment appt, HttpServletRequest request, HttpServletResponse response) {
        ScheduleServices ss = new ScheduleServices();

        request.setAttribute("apptServices", ss.getAllAppointmentServices(appt));
    }

}
