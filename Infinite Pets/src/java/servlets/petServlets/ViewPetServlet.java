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
import services.AddPetServices;
import services.ValidationServices;

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
       
        //REMOVE ME ONLY FOR TESTING
        session.setAttribute("viewPetId", 1);
        
        try {
            //get the pet by the ID stored in the session.
            targetPet = petServ.getPetById((Integer)session.getAttribute("viewPetId"));
        } catch (Exception ex) {
            //Logger.getLogger(ViewPetServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //This sets all the text and combo boxes in the jsp
            request.setAttribute("owner", (String)session.getAttribute("owner"));
            request.setAttribute("petName", targetPet.getPetName());
            request.setAttribute("sex", this.determineSex(targetPet.getBreed()));
            request.setAttribute("animal", targetPet.getSpecies());
            request.setAttribute("breed", targetPet.getBreed());
            request.setAttribute("birthday",targetPet.getBirthday());
            request.setAttribute("medical", targetPet.getMedicalInfo());
            request.setAttribute("vet", targetPet.getPreferredVet());

        
        getServletContext().getRequestDispatcher("/WEB-INF/ViewPet.jsp").forward(request,response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AddPetServices petServ = new AddPetServices();
        ValidationServices vs = new ValidationServices();
        if(request.getParameter("action").toString().equals("save"))
        {
            Pet targetPet = new Pet();
            try {
                //get the pet by the ID stored in the session.
                targetPet = petServ.getPetById((Integer)session.getAttribute("viewPetId"));
            } catch (Exception ex) {
                //Logger.getLogger(ViewPetServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

                String owner = (String)session.getAttribute("owner");
                String petName = request.getParameter("petName");
                String sex = request.getParameter("sex");
                String type = request.getParameter("animal");
                String breed = request.getParameter("breed");
                String birthday = request.getParameter("birthday");
                String info = request.getParameter("medical");
                String vet = request.getParameter("vet");
                
            try{
                String msg = vs.checkInput(petName, type, breed, birthday, vet, info, sex, owner);
                if (msg.equals("Checked")){
              
                    petServ.createPet(petName, type, breed, birthday, vet, info, sex, owner);
                    response.sendRedirect("MyPets");
                }
                else {
                    session.setAttribute("errorMsg", msg);
                    System.out.println(msg);
                    getServletContext().getRequestDispatcher("/WEB-INF/addAPet.jsp").forward(request,response);
//                    response.sendRedirect("AddPet");
                }
            }
            catch(Exception e){
                Logger.getLogger(AddPetServlet.class.getName()).log(Level.SEVERE, null, e);
            }
                
        } 
     
        response.sendRedirect("MyPets");
    }

    /**
     * This function determines the sex of the animal from a single char
     * @param sex
     * @return String that is the correct sex
     */
    private List determineSex(String sex){
        List temp = new ArrayList();
        if(sex.equalsIgnoreCase("M")){
            temp.add("Male");
            temp.add("Neutered");
        }
        else if (sex.equalsIgnoreCase("F")){
           temp.add("Female");
           temp.add("Spayed");
           
        }
        else if (sex.equalsIgnoreCase("N")){
           temp.add("Neutered");
         
        }
        else if (sex.equalsIgnoreCase("S")){
            temp.add("Spayed");  
        }
        else
           temp.add("Altered");
       
        return temp;
    }
}
