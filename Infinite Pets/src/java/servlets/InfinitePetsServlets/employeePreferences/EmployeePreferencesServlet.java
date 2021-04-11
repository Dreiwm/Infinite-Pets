/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.InfinitePetsServlets.employeePreferences;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Account;
import models.EmpServicePreference;
import models.Employee;
import models.ServiceType;
import services.AccountServices;
import services.ServiceServices;
import servlets.InfinitePetsServlets.promotions.PromotionsServlet;

/**
 *
 * @author Riley
 */
public class EmployeePreferencesServlet extends HttpServlet {

    private final String path = "/WEB-INF/employeePreferences/EmployeePreferences.jsp";

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
        } else {
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
                Logger.getLogger(EmployeePreferencesServlet.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect(response.encodeURL("Login"));
                return; // to get out of here.
                // Don't care if there is an error in try block, kick out just in case.
            }
        }

        // if tests were passed, this section will be read.
        // Sets attribute for employee's service preference
        ServiceServices ss = new ServiceServices();
        List<EmpServicePreference> list = ss.getAllEmpServicePreferences();
        for (EmpServicePreference esp : list) {
            if (esp.getEmployeeID().equals(emp)) {
                System.out.println(esp.getServiceTypeID().getServiceType());
            }
        }

//        EmpServicePreference empS = new EmpServicePreference();
        // Sets attribute if action=add
        // Delete emp preference if action=delete
        // using if and else if to be safe, because I don't want to do two things at the same time.
        String action = request.getParameter("action");

        // as always, check if action is not null before proceeding...
        if (action != null) {
            if (action.equals("Delete")) {
                // delete emp preference
                int empPId = Integer.parseInt(request.getParameter("empPrefID"));

                ss.deleteEmpServicePreference(empPId);

            }

        }
        setAttributes(request, response, emp);

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

        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("add")) {
                // get email from session
                String email = (String) request.getSession().getAttribute("email");

                // Add work preference
                // get selectServiceType
                int serviceTypeId = Integer.parseInt(request.getParameter("selectServiceType"));

                // get employee account
                AccountServices acs = new AccountServices();
                Employee e = null;
                try {
                    e = acs.getEmployeeAccount(email);
                } catch (Exception ex) {
                    Logger.getLogger(EmployeePreferencesServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                ServiceServices ss = new ServiceServices();

                EmpServicePreference empSP = new EmpServicePreference(0);
                empSP.setServiceTypeID(ss.getServiceType(serviceTypeId));
                empSP.setEmployeeID(e);
                boolean success = false;
                try {
                    ss.insertEmpServicePreference(empSP);
                } catch (Exception ex) {
//                    Logger.getLogger(EmployeePreferencesServlet.class.getName()).log(Level.SEVERE, null, ex);
                        String str = "An error has occured. Please try again.";
                        str += "<br/>One of possible reason(s):";
                        
                        str += "<ul>";
                        str += "<li>Work Preferences cannot be duplicated</li>";
                        str += "</ul>";
                    request.setAttribute("errorMsg", str);
                }

                
                setAttributes(request, response, e);
                getServletContext().getRequestDispatcher(path).forward(request, response);

            }
        }
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

    private void setAttributes(HttpServletRequest request, HttpServletResponse response, Employee e) {
        ServiceServices sS = new ServiceServices();
        List<ServiceType> sTList = sS.getAllServiceTypes();
        request.setAttribute("serviceTypes", sTList);

        request.setAttribute("empPreferenceList", sS.getAllEmpServicePreferencesBelongToEmp(e));

    }

}
