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
        account = accountDB.getAccountById(username);
        return account;
    }
    
    public void setPet(String name, String animal, String breed, String bday, String vet, String medical, String sex, String owner)throws Exception{
        AccountDB accountDB = new AccountDB();
        PetDB petDB = new PetDB();
        AnimalDB animalDB = new AnimalDB();
        BreedDB breedDB = new BreedDB();
        char sx = sex.charAt(0);
        
        Pet newPet = new Pet(0,sx,animal,breed,name);
        newPet.setOwner(accountDB.getAccountById(owner));
        
        petDB.insertPet(newPet);
    }
    
    
}
