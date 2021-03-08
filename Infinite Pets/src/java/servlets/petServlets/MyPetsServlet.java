/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.petServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
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
        
//REMOVE ME LATER!!!!!!!!!!!!!!
        session.setAttribute("owner", "anne");
//REMOVE THE ABOVE LINE LATER!!!!!!!!!!!!      
        Account owner = new Account();
        AddPetServices aps = new AddPetServices();
        try {
            owner = aps.getAccount((String)session.getAttribute("owner"));
        } catch (Exception ex) {
            Logger.getLogger(MyPetsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("pets", owner.getPetList());
        
        getServletContext().getRequestDispatcher("/WEB-INF/MyPets.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        
        
        
    }


}
