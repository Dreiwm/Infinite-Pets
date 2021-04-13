/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.adminServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Empqualificationtype;
import models.Location;
import services.AccountServices;
import services.ValidationServices;
import servlets.AddPetServlet;

/**
 *
 * @author BTran
 */
public class AdminNewEmployeeServlet extends HttpServlet {
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
        if (email.equals("") || email == null){
            session.invalidate();
            response.sendRedirect("Login");
        }
        String action = request.getParameter("action");
        try {
            if ((!action.equals("") || action != null) && action.equals("edit")){
            String empEmail = request.getParameter("empEmail");
            AccountServices as = new AccountServices();
            Account empAccount = as.getAccount(empEmail);
            Location empAddress = empAccount.getAddress();
            request.setAttribute("empAddress", empAddress);
            request.setAttribute("empAccount", empAccount);
            }
        } catch(Exception e) {
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/NewEmployee.jsp").forward(request,response);
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
        try {
            if ((!action.equals("") || action != null) && action.equals("save")){
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String address = request.getParameter("address");
                String area = request.getParameter("area");
                String city = request.getParameter("city");
                String prov = request.getParameter("prov");
                String country = request.getParameter("country");
                String postal = request.getParameter("postal");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                AccountServices as = new AccountServices();
                ValidationServices vs = new ValidationServices();
                if(vs.verifyInfo(firstName, lastName, address, city, prov, country, postal, area, email, prov)){
                    Location location = as.createAddress(postal, area, city, country, prov, area);
                    List<Empqualificationtype> empqualificationtypeList = null;
                    as.createStaffAccount(password, email, firstName, location, lastName, empqualificationtypeList);
                }
            }
        } catch(Exception e) {
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("message", "Something went wrong :"+action);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/NewEmployee.jsp").forward(request,response);
    }
}
