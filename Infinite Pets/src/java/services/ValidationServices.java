/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.AnimalType;
import models.Breed;
import servlets.petServlets.AddPetServlet;

/**
 *
 * @author Chris
 */
public class ValidationServices {
 
    public String checkInput(String petName, String type, String breed, String birthday, String vet, String info, String sex, String owner){
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
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.WARNING, null, e);
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
    
    public List getAnimalType() {
        try {
            AddPetServices aps = new AddPetServices();
            List<AnimalType> animalList = aps.getAnimals();
            return animalList;  
        } catch (Exception ex) {
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.WARNING, null, ex);
        }
        return null;
    }
    
    public List getBreedList() {
        try {
            AddPetServices aps = new AddPetServices(); 
            List<Breed> breedList = aps.getAllAnimalBreeds();
            return breedList;
        } catch (Exception ex) {
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.WARNING, null, ex);
        }
        return null;
    }

    //checks information before signing up
    public boolean verifyInfo(String firstName, String lastName, String street, String city, String prov, String country, String postalCode, String area, String email, String emailConf, String pass, String passConf) {
        boolean checked = false;
        try {
            System.out.println("Verifying info");
            if (!firstName.equals("") && !lastName.equals("") && locationChecked(street, city, prov, country, postalCode, area) && checkEmailPass(email, emailConf, pass, passConf)) {
                    System.out.println("checked confirmed");
                    checked = true;                
            }
            else {
                System.out.println("FAILED");
            }
        } catch(Exception e) {
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.WARNING, null, e);
        }
        return checked;
    }
    
    //checks if the address information is valid
    private boolean locationChecked(String street, String city, String prov, String country, String postalCode, String area){
        boolean checked = false;
        System.out.println("checking location");
        if (!street.equals("") && !city.equals("") && !prov.equals("") && !country.equals("") && !postalCode.equals("") && !area.equals("")){
            System.out.println("location cleared");
            checked = true;
        }
        else {
            System.out.println("location failed");
        }
        return checked;
    }
    
    //checks if the sign up email and password match
    private boolean checkEmailPass(String email, String email2, String pass, String pass2) {
        boolean checked = false;
        if (email.equals(email2) && pass.equals(pass2)) {
            System.out.println("maching cleared");
            checked = true;
        }
        else {
            System.out.println("matching failed");
        }
        return checked;
    }

    //checks info when editing MyProfile
    public boolean verifyInfo(String firstName, String lastName, String address, String city, String prov, String country, String postal, String area, String email, String pass) {
        boolean checked = false;
        try {
            System.out.println("Verifying info");
            if (!firstName.equals("") && !lastName.equals("") && locationChecked(address, city, prov, country, postal, area)) {
                    System.out.println("checked confirmed");
                    checked = true;                
            }
            else {
                System.out.println("FAILED");
            }
        } catch(Exception e) {
            Logger.getLogger(AddPetServlet.class.getName()).log(Level.WARNING, null, e);
        }
        return checked;
    }
}
