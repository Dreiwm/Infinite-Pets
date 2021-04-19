/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AccountDB;
import dataaccess.AnimalDB;
import dataaccess.BreedDB;
import dataaccess.PetDB;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import models.Account;
import models.Pet;
import models.AnimalType;
import models.Breed;

/**
 *
 * @author BTran, Chris
 */
public class AddPetServices {
    
    //Retrieves a list of all the animal types
    public List<AnimalType> getAnimals() throws Exception{
        AnimalDB animalsdb = new AnimalDB();
        return animalsdb.getAllAnimals();
    }
      
    //Retrieves a list of breeds for an animal type
    public List<Breed> getDogBreeds() throws Exception{
        BreedDB breedDB = new BreedDB();       
        return breedDB.getBreedByAnimalId(1);
    }
    
    //Retrieves a list of breeds for an animal type
    public List<Breed> getCatBreeds() throws Exception{
        BreedDB breedDB = new BreedDB();       
        return breedDB.getBreedByAnimalId(2);
    }
    
        //Retrieves a list of breeds for an animal type
    public List<Breed> getExoticBreeds() throws Exception{
        BreedDB breedDB = new BreedDB();       
        return breedDB.getBreedByAnimalId(3);
    }
    
    //DO WE NEED THIS????
    //Retrieves a list of all animal breeds
    public List getAllAnimalBreeds()throws Exception{
        BreedDB breedDB = new BreedDB();
        return breedDB.getAllAnimalBreeds();
    }
    
    public Pet getPetById(Integer id) throws Exception{
        PetDB ptdb = new PetDB();
        Pet pet = new Pet(); 
        
        pet = ptdb.getItemById(id);
        
        return pet;
    }
    
    public void createPet(String name, String animal, String breed, String birthday, String vet, String medical, String sex, String owner, String url)throws Exception{
        AccountDB accountDB = new AccountDB();
        PetDB petDB = new PetDB();
        char sx = sex.charAt(0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date bday = format.parse(birthday);
        Pet newPet = new Pet(0, sx, animal, breed, name, bday);
        newPet.setPreferredVet(vet);
        newPet.setMedicalInfo(medical);
        //newPet.setImagePath(url);
        newPet.setOwner(accountDB.getAccountByEmail(owner));
                
        System.out.println(newPet.getBreed()+"\n"+newPet.getPetName());
        petDB.insertPet(newPet);
        System.out.println("set pet");
    }
    

    public void updatePet(int petID, String name, String vet, String medical, String sex) throws Exception{
        AccountDB accountDB = new AccountDB();
        PetDB petDB = new PetDB();
        char sx = sex.charAt(0);
        Pet tempPet = petDB.getItemById(petID);
        
        Pet newPet = new Pet(tempPet.getPetID(), sx, tempPet.getSpecies(), tempPet.getBreed(), name, tempPet.getBirthday());
        newPet.setPreferredVet(vet);
        newPet.setMedicalInfo(medical);
        //newPet.setImagePath(url);
        newPet.setOwner(tempPet.getOwner());        
        
        System.out.println(newPet.getBreed()+"\n"+newPet.getPetName());
        petDB.updatePet(newPet);
        System.out.println("pet updated");
    }
    
    
   
    public void deletePet(){
    
    }
}
