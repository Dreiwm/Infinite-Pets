/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.InfinitePetsServlets.employeeAppointments;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Account;
import models.Appointment;
import models.Employee;
import services.AccountServices;
import services.ScheduleServices;
import servlets.InfinitePetsServlets.employeePreferences.EmployeePreferencesServlet;
import servlets.InfinitePetsServlets.promotions.PromotionsServlet;

/**
 *
 * @author Riley
 */
public class AvailableAppointmentsServlet extends HttpServlet {

    private final String path = "/WEB-INF/AvailableAppointments.jsp";

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
        // This page is only for staff. So, first we need to check if it is in session,
        // if yes, check is this account is associated with employee obj?
        // If yes, serve the page. Otherwise, redirect to login page. 
        String email = (String) request.getSession().getAttribute("email");

        AccountServices acs = new AccountServices();
        Account acc = null;
        Employee emp = null;
        try {
            acc = acs.getAccount(email);
        } catch (Exception ex) {
            Logger.getLogger(PromotionsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (acc == null) {
            // Can't find account in DB, kick out.
            System.out.println("account null, going to login page...");
            response.sendRedirect(response.encodeRedirectURL("Login"));
            return;
        } 
        else {
            try {
                // Ok so account is not null, is this account associated with EMployee
                // object? If so, allow access. Otherwise, kick.
                emp = acs.getEmployeeAccount(email);
                if (emp == null) {
                    // kick out
                    response.sendRedirect(response.encodeURL("Login"));
                    return; // to get out of here.
                }
                
            } catch (Exception ex) {
                Logger.getLogger(AvailableAppointmentsServlet.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect(response.encodeURL("Login"));
                return; // to get out of here.
                // Don't care if there is an error in try block, kick out just in case.
            }
        }

        try {
            System.out.println(emp.getUserID().getFirstName());
            setAttributes(request, response, emp);
        } catch (Exception ex) {
            Logger.getLogger(AvailableAppointmentsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        // if tests were passed, this section will be read.
        getServletContext().getRequestDispatcher(path).forward(request, response);
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

    private void setAttributes(HttpServletRequest request, HttpServletResponse response, Employee emp) throws ParseException {
        // get all services
        ScheduleServices schs = new ScheduleServices();
//        schs.getAllAvailableAppointmentsByPreferences(emp);
            request.setAttribute("appts", schs.getAllAppointments(emp));
        
    }

}
