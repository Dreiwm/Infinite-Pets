/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.InfinitePetsServlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import models.Appointmentservice;
import models.Pet;
import models.Service;
import services.AccountServices;
import services.AddPetServices;
import services.EmailService;
import services.ScheduleServices;
import services.ServiceServices;

/**
 *
 * @author Riley
 */
public class AddAppointmentServiceServlet extends HttpServlet {

    private final String path = "/WEB-INF/AddAppointmentService.jsp";

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
        System.out.println("email: " + email);
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
            // all tests passed, display contents 
            // Since this page expects a paramater to be given from Appointment page,
            // we will be working with that. If something went wrong, like can't find
            // an appointment or null, simply present an error with a link to MyAppointments.

            // set appt to attribute
            request.setAttribute("appt", appt);

            // Set list of Service Types
            ServiceServices ss = new ServiceServices();
            request.setAttribute("serviceTypesList", ss.getAllServiceTypes());

            // Set options for services with given service type parameter (if not null)
            String selectedServiceTypeStr = request.getParameter("selectedServiceType");
            if (selectedServiceTypeStr != null) {
                int selectedServiceTypeId = Integer.parseInt(selectedServiceTypeStr);

                // all services matching the service type
                System.out.println("serviceTypeID: " + selectedServiceTypeId);

                request.setAttribute("selectedServiceTypeID", selectedServiceTypeId);
                request.setAttribute("services", ss.getAllServicesByServiceTypeID(selectedServiceTypeId));
            } else {
                // choose first one
                int selectedServiceTypeId = 1;

                request.setAttribute("selectedServiceTypeID", selectedServiceTypeId);
                request.setAttribute("services", ss.getAllServicesByServiceTypeID(selectedServiceTypeId));

            }

            // Set pets attribute
            System.out.println(acc.getPetList() == null);
            request.setAttribute("pets", acc.getPetList());

            getServletContext().getRequestDispatcher(path).forward(request, response);
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
        // get appt id
        int apptId = Integer.parseInt(request.getParameter("apptId"));

        // get parameter 
        String action = request.getParameter("action");

        // check if not null, if not null then act.
        if (action != null && !action.equals("")) {

            // for ok (add service)
            if (action.equals("Ok")) {
                System.out.println("processing POST form..");
                // get appt obj
                ScheduleServices schs = new ScheduleServices();
                ServiceServices ss = new ServiceServices();
                // get appointment obj
                Appointment appt = schs.getAppointmentById(apptId);

                // set appt to apptService
                Appointmentservice apptService = new Appointmentservice(0);

                try {
                    // get selected pet
                    int petId = Integer.parseInt(request.getParameter("selectedPetId"));

                    // get service id
                    int serviceId = Integer.parseInt(request.getParameter("selectedServiceId"));

                    apptService.setAppointmentID(appt);

                    // get service
                    Service service = ss.getServiceById(serviceId);

                    // get pet
                    AddPetServices apDB = new AddPetServices();
                    Pet pet = apDB.getPetById(petId);

                    // add service to apptService
                    apptService.setServiceID(service);

                    // add pet to apptService
                    apptService.setPetID(pet);

                    System.out.println("add appointmentService...");
                    if (ss.insertAppointmentService(apptService)) {
                        System.out.println("inserted into.");
                        // Send email to client and staff
                        EmailService ems = new EmailService();
                        String path = getServletContext().getRealPath("/assets");
//                    
                        ems.sendAppointmentUpdateNotification(appt, new Date(), path);
                    } else {
                        System.out.println("failed to be inserted into");
                    }

                    request.setAttribute("apptId", apptId);
                    response.sendRedirect(response.encodeRedirectURL("Appointment?apptId=" + apptId));
                    return;

                } catch (NumberFormatException e) {
                    request.setAttribute("errorMsg", "Sorry, there was an error. Please try again.");
                    System.out.println("Error with parsing int" + e.getMessage());
                    request.setAttribute("apptId", apptId);
                } catch (Exception ex) {
                    request.setAttribute("errorMsg", "Sorry, there was an error. Please try again.");
                    request.setAttribute("apptId", apptId);
                    Logger.getLogger(AddAppointmentServiceServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                getServletContext().getRequestDispatcher(path).forward(request, response);
            } else if (action.equals("Cancel")) {
                System.out.println("going back..");
                System.out.println("apptId: " + apptId);
                request.setAttribute("apptId", apptId);
//                getServletContext().getRequestDispatcher("/WEB-INF/Appointment.jsp").forward(request, response);
                response.sendRedirect(response.encodeRedirectURL("Appointment?apptId=" + apptId));

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

}
