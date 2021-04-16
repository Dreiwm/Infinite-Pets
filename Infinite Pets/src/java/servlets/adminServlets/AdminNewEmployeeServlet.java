/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.adminServlets;

import dataaccess.EmployeeDB;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Boolean.getBoolean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.Employee;
import models.Empqualification;
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
            //request.setAttribute("services", services);
            if ((!action.equals("") || action != null) && action.equals("edit")){
            String empEmail = request.getParameter("empEmail");
            AccountServices as = new AccountServices();
            Account empAccount = as.getAccount(empEmail);
            Location empAddress = empAccount.getAddress();
            EmployeeDB empDB = new EmployeeDB();
            Employee employee = empDB.getByUserId(empAccount);
            List<Service> empList = employee.getServiceList();
            
            Map<String,Boolean> map = new HashMap<String,Boolean>();
            for (int i = 0; i < services.size(); i++){                
                boolean check = false;
                for (int j = 0; j < empList.size(); j++){
                    if (services.get(i).getServiceName().equals(empList.get(j).getServiceName())){
                        check = true;
                    }
                    map.put(services.get(i).getServiceName(), check);
                }
            }
            request.setAttribute("oldEmail", empAccount.getEmail());
            request.setAttribute("services", map);
            request.setAttribute("empAddress", empAddress);
            request.setAttribute("empAccount", empAccount);
            request.setAttribute("action", "update");
            }
            else if ((!action.equals("") || action != null) && action.equals("create")){
                request.setAttribute("action", "create");  
//                
                Map<String,Boolean> newMap = new HashMap<String,Boolean>();
                for (int i = 0; i < services.size(); i++){                
                    newMap.put(services.get(i).getServiceName(), false);

                }
            
                request.setAttribute("services", newMap);
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
            String oldEmail = request.getParameter("oldEmail");
            
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
            boolean isEmployee = Boolean.parseBoolean(request.getParameter("isEmployee"));
            boolean isConfirmed = Boolean.parseBoolean(request.getParameter("isConfirmed"));
            AccountServices as = new AccountServices();
            ValidationServices vs = new ValidationServices();
            System.out.println("Print Employee Info");
            System.out.printf("Email: %s, First: %s, Last: %s, Pass: %s, Address: %s, Area: %s, City: %s, Country: %s, Prov: %s, Postal: %s, IsEmployee: %s, IsConfirmed: %s, Action: %s%n", email, firstName, lastName, password, address, area, city, country, prov, postal, isEmployee, isConfirmed, action);
            
            //for loop to get the selected services available to the employee
            PetServicesServices pss = new PetServicesServices();
            List<Service> allServices = pss.getAllServices();   
            List<Service> qList = new ArrayList<>();    
            
            for (int i = 0; i < allServices.size(); i++){
                if (Boolean.parseBoolean(request.getParameter(allServices.get(i).getServiceName()))){
                    System.out.println("Service Name: "+allServices.get(i).getServiceName());
                    System.out.println("Service available: "+Boolean.parseBoolean(request.getParameter(allServices.get(i).getServiceName())));
                    qList.add(allServices.get(i));
                    System.out.println(qList);
                }
            }
            
            //check the action being performed
            if ((!action.equals("") || action != null) && vs.verifyInfo(firstName, lastName, address, city, prov, country, postal, area, email, prov)) {
                if (action.equals("create")){                       
                    Location location = as.createAddress(postal, address, city, country, prov, area);
                    as.createStaffAccount(password, email, firstName, location, lastName, qList);   //check this method                 
                }
                else if ((!action.equals("") || action != null) && action.equals("update")){
                    as.updateStaffAccount(oldEmail ,password, email, firstName, lastName, address, city, prov, country, postal, area, isEmployee, isConfirmed, qList);
                }
                else {
                    getServletContext().getRequestDispatcher("/WEB-INF/NewEmployee.jsp").forward(request,response);
                }
            }
             
        } catch(Exception e) {
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.SEVERE, null, e);
            request.setAttribute("message", "Something went wrong :"+action);
        }
        response.sendRedirect("Employment");
    }
}

