/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import models.AnimalType;
import services.AddPetServices;

/**
 *
 * @author Chris
 */
public class AddPetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                HttpSession session = request.getSession();
               AddPetServices aps = new AddPetServices();
        try {
            List animalList = aps.getAnimals();  
            List breedList = aps.getAllAnimalBreeds();
                
            request.setAttribute("animalList", animalList);
            request.setAttribute("breedList", breedList);
                
        } catch (Exception ex) {
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
             
                getServletContext().getRequestDispatcher("/WEB-INF/AddAPet.jsp").forward(request,response);
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
       HttpSession session = request.getSession();
        
       String petName = request.getParameter("petName");
       String sex = request.getParameter("sex");
       String type = request.getParameter("animal");
       String breed = request.getParameter("breed");
       String birthday = request.getParameter("birthday");
       String info = request.getParameter("medical");
       String vet = request.getParameter("vet");
       
       
       String pet = petName +" "+ sex+" "+type+" "+breed +" "+ birthday +" "+ info +" " +vet +"added";
       testWritePet(request, pet);
       
       
       request.setAttribute("currentPets", testBreeds("pets"));
       
       getServletContext().getRequestDispatcher("/WEB-INF/AddAPet.jsp").forward(request,response);
    }

    
    /*
    Test Methods
    */
    private List testBreeds (String type) throws FileNotFoundException{
          List<String> breedList = new ArrayList();
          
          try {
            String path = getServletContext().getRealPath("/WEB-INF/testFiles/"+type+".txt");
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String currentLine;
            
            while((currentLine = br.readLine()) != null){
                breedList.add(currentLine);
            }
            br.close();            
            } 
          catch (IOException ex) {
            
        }
        return breedList;
    }
    
    
    private void testWritePet (HttpServletRequest request, String pet) throws IOException{
        
        String path = getServletContext().getRealPath("/WEB-INF/testFiles/pets.txt");
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path,true)))) {
            pw.print(pet);
            pw.println();
        }
        
    }

  
}
