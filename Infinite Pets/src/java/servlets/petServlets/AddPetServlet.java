/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.petServlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import models.AnimalType;
import models.Breed;
import services.AddPetServices;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

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
        session.setAttribute("owner", "anne");
        request.setAttribute("animalList", getAnimalType());
        request.setAttribute("breedList", getBreedList());        
       
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
        request.setAttribute("animalList", getAnimalType());
        request.setAttribute("breedList", getBreedList());
       
        if(request.getParameter("action").toString().equals("cancel")){
            response.sendRedirect("MyPets");
        }
        else if(request.getParameter("action").toString().equals("save")){
            AddPetServices aps = new AddPetServices();
            
            System.out.println("making pet");
            String owner = (String)session.getAttribute("owner");
            String petName = request.getParameter("petName");
            String sex = request.getParameter("sex");
            String type = request.getParameter("animal");
            String breed = request.getParameter("breed");
            String birthday = request.getParameter("birthday");
            String info = request.getParameter("medical");
            String vet = request.getParameter("vet");
            
            System.out.println("INPUT: "+petName+" "+type+" "+breed+" "+birthday+" "+vet+" "+info+" "+sex+" "+owner);

            try{
                String msg = checkInput(petName, type, breed, birthday, vet, info, sex, owner);
                if (msg.equals("Checked")){
                    System.out.println("going to aps");
                    aps.createPet(petName, type, breed, birthday, vet, info, sex, owner);
                    response.sendRedirect("MyPets");
                }
                else {
                    session.setAttribute("errorMsg", msg);
                    System.out.println(msg);
                    getServletContext().getRequestDispatcher("/WEB-INF/AddAPet.jsp").forward(request,response);
//                    response.sendRedirect("AddPet");
                }
            }
            catch(Exception e){
                Logger.getLogger(AddPetServlet.class.getName()).log(Level.SEVERE, null, e);
            }
        }    
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
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private String checkInput(String petName, String type, String breed, String birthday, String vet, String info, String sex, String owner){
        String msg = "";
        boolean found = false;
        try {
            List<AnimalType> animalList = getAnimalType();
            List<Breed> breedList = getBreedList();
            System.out.println("checking input");
            
            //Check Pet Name
            if (petName.equals("") || petName == null)
                msg += "Pet Name is invalid.";
            
            //checking Animal Type
            if (!type.equals("") && type != null){
                for (int i = 0; i < animalList.size(); i++){
//                    System.out.println(animalList.get(i).getAnimalType());
                    if (type.equals(animalList.get(i).getAnimalType()))
                        found = true;
                }
            }
            else
                msg += "Type is invalid.";
            
            //checking Breed
            if (!breed.equals("") && breed != null){
                for (int i = 0; i < breedList.size(); i++){
                    if (breed.equals(breedList.get(i).getBreedName()))
                        found = true;
                }
            }
            else
                msg += "Breed is invalid.";
            
            //Check valid sex
            if (!sex.equals("") && sex != null){
                if (!sex.equals("Male") && !sex.equals("Female") && !sex.equals("Neutered") && !sex.equals("Spaded"))
                    msg += "Not a Proper Selection.";
            }
            
            //Check for a valid bday
            if (!birthday.equals("") && birthday != null){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date bday = format.parse(birthday);
                if (bday.after(format.parse(getToday())))
                    msg += ("Date doesn't exist yet.");
            }
            
        } catch(Exception e){
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.SEVERE, null, e);
        }
        if (msg.equals(""))
            msg = "Checked";
        return msg;
    }
    
    private String getToday(){
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return format.format(date);
    }
    
    private List getAnimalType() {
        try {
            AddPetServices aps = new AddPetServices();
            List<AnimalType> animalList = aps.getAnimals();
            return animalList;  
        } catch (Exception ex) {
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private List getBreedList() {
        try {
            AddPetServices aps = new AddPetServices(); 
            List<Breed> breedList = aps.getAllAnimalBreeds();
            return breedList;
        } catch (Exception ex) {
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
