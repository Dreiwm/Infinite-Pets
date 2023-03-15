/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.scheduleServlets;

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
import javax.servlet.http.HttpSession;
import models.Account;
import models.Appointment;
import services.AccountServices;

/**
 *
 * @author Chris
 */
public class MyAppointmentServlet extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String systemMsg = (String) session.getAttribute("sysMsg");
        request.setAttribute("mgs", systemMsg);
        if (email.equals("") || email == null){
            session.invalidate();
            response.sendRedirect("Login");
        }
        AccountServices as = new AccountServices();
        
        try {
            Account user = as.getAccountEmail(email);
            List<Appointment> appointments = new ArrayList();
            appointments = user.getAppointmentList();
            request.setAttribute("appts", appointments);
        } catch (Exception ex) {
            Logger.getLogger(MyAppointmentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         getServletContext().getRequestDispatcher("/WEB-INF/MyAppointments.jsp").forward(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
