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
    
    public List<AnimalType> getAnimals() throws Exception{
        AnimalDB animalsdb = new AnimalDB();
        return animalsdb.getAllAnimals();
    }
   
    public List getBreeds(String animal)throws Exception{
        BreedDB breedDB = new BreedDB();
        return breedDB.getBreedByAnimalId(animal);
    }
    
    public List getAllAnimalBreeds()throws Exception{
        BreedDB breedDB = new BreedDB();
        return breedDB.getAllAnimalBreeds();
    }
    
    public Account getAccount(String username)throws Exception{
        AccountDB accountDB = new AccountDB();
        Account account = new Account();
        account = accountDB.getAccountByUsername(username);
        return account;
    }
    
    public Pet getPetById(Integer id) throws Exception{
        PetDB ptdb = new PetDB();
        Pet pet = new Pet(); 
        
        pet = ptdb.getItemById(id);
        
        return pet;
    }
    
    public void createPet(String name, String animal, String breed, String birthday, String vet, String medical, String sex, String owner)throws Exception{
        AccountDB accountDB = new AccountDB();
        PetDB petDB = new PetDB();
        char sx = sex.charAt(0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date bday = format.parse(birthday);
        Pet newPet = new Pet(0, sx, animal, breed, name, bday);
        newPet.setPreferredVet(vet);
        newPet.setMedicalInfo(medical);
        
        
        newPet.setOwner(accountDB.getAccountByUsername(owner));
                
        System.out.println(newPet.getBreed()+"\n"+newPet.getPetName());
        petDB.insertPet(newPet);
        System.out.println("set pet");
    }
    
    public void updatePet(Pet pet, String birthday, String vet, String medical, String sex){
        
    }
    
    
    
    
    
}
