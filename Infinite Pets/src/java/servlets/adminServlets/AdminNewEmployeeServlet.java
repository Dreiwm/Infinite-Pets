/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.adminServlets;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Boolean.getBoolean;
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
import models.Service;
import services.AccountServices;
import services.PetServicesServices;
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
            PetServicesServices pss = new PetServicesServices();
            List<Service> services = pss.getAllServices();
            if ((!action.equals("") || action != null) && action.equals("edit")){
            String empEmail = request.getParameter("empEmail");
            AccountServices as = new AccountServices();
            Account empAccount = as.getAccount(empEmail);
            Location empAddress = empAccount.getAddress();
            request.setAttribute("empAddress", empAddress);
            request.setAttribute("empAccount", empAccount);
            request.setAttribute("action", "update");
            }
            else if ((!action.equals("") || action != null) && action.equals("create")){
                request.setAttribute("action", "create");
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
            boolean isEmployee = getBoolean(request.getParameter("isEmployee"));
            boolean isConfirmed = getBoolean(request.getParameter("isConfirmed"));
            AccountServices as = new AccountServices();
            ValidationServices vs = new ValidationServices();
            System.out.println("Print Employee Info");
            System.out.printf("Email: %s, First: %s, Last: %s, Pass: %s, Address: %s, Area: %s, City: %s, Country: %s, Prov: %s, Postal: %s, IsEmployee: %s, IsConfirmed: %s, Action: %s%n", email, firstName, lastName, password, address, area, city, country, prov, postal, isEmployee, isConfirmed, action);
            //check the action being performed
            if ((!action.equals("") || action != null) && vs.verifyInfo(firstName, lastName, address, city, prov, country, postal, area, email, prov)) {
                if (action.equals("create")){          
             
                    Location location = as.createAddress(postal, address, city, country, prov, area);
                    List<Empqualificationtype> empqualificationtypeList = null;
                    as.createStaffAccount(password, email, firstName, location, lastName, empqualificationtypeList);                    
                }
                else if ((!action.equals("") || action != null) && action.equals("update")){
                    as.updateStaffAccount(password, email, firstName, lastName, address, city, prov, country, postal, area, isEmployee, isConfirmed);
                }
                else {
                    getServletContext().getRequestDispatcher("/WEB-INF/Employee.jsp").forward(request,response);
                }
            }
             
        } catch(Exception e) {
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("message", "Something went wrong :"+action);
        }
        response.sendRedirect("Employment");
    }
}
