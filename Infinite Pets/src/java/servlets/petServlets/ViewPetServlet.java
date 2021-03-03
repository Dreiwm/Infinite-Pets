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
import models.Pet;
import services.AddPetServices;

/**
 *
 * @author Chris
 */
public class ViewPetServlet extends HttpServlet {

 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AddPetServices petServ = new AddPetServices();
        Pet targetPet = new Pet();
        try {
            Account owner = petServ.getAccount((String)session.getAttribute("owner"));
            //go though all of the owners pet to find the correct one. 
            for(Pet temp : owner.getPetList()){
                if(temp.getPetName().equalsIgnoreCase((String)session.getAttribute("viewPetName")))
                    targetPet = temp;
            }
        } catch (Exception ex) {
            //Logger.getLogger(ViewPetServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        request.setAttribute("owner", (String)session.getAttribute("owner"));
        request.setAttribute("petName", targetPet.getPetName());
        request.setAttribute("sex", this.determineSex(targetPet.getSex()));
        request.setAttribute("animal", targetPet.getSpecies());
        request.setAttribute("breed", targetPet.getBreed());
        request.setAttribute("birthday",targetPet.getBirthday());
        request.setAttribute("medical", targetPet.getMedicalInfo());
        request.setAttribute("vet", targetPet.getPreferredVet());
        
        getServletContext().getRequestDispatcher("/WEB-INF/ViewPets.jsp").forward(request,response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    /**
     * This function determines the sex of the animal from a single char
     * @param sex
     * @return String that is the correct sex
     */
    private String determineSex(Character sex){
        if(sex.equals("M"))
           return "Male";
        else if (sex.equals("F"))
           return "Female";
        else if (sex.equals("N"))
           return "Neutered";
        else if (sex.equals("S"))
           return "Spayed";
        else
           return "Altered";
    }
}
