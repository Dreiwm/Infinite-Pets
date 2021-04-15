/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.petServlets;

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
import models.Pet;
import services.AccountServices;
import services.AddPetServices;

/**
 *
 * @author Chris
 */
public class MyPetsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        if (email.equals("") || email == null){
            session.invalidate();
            response.sendRedirect("Login");
        }
       
        List<Pet> petList = new ArrayList<Pet>();
        AccountServices acs = new AccountServices();
        try {
            Account user = acs.getAccount(email);
            petList = user.getPetList();
            
        } catch (Exception ex) {
            Logger.getLogger(MyPetsServlet.class.getName()).log(Level.WARNING, null, ex);
        }
        
        request.setAttribute("pets", petList);
        
        getServletContext().getRequestDispatcher("/WEB-INF/MyPets.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
    }
}
