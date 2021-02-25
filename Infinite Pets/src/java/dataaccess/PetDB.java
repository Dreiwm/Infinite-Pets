/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Pet;
import models.Account;
/**
 *
 * @author Chris
 */
public class PetDB {
    public Pet getItemById(int petID)throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            Pet item = em.find(Pet.class, petID);
            return item;
        }
        finally{
            em.close();
        }
    }
    
    public List<Pet> getAllPetsFromOwner(String owner){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            Account user = em.find(Account.class, owner);
            return user.getPetCollection();
        }
        finally{
            em.close();
        }
    }
    
    public void insertPet (Pet pet) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            Account user = pet.getOwner();
            user.getPetCollection().add(pet);
            trans.begin();
            em.persist(pet);
            em.merge(user);
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
        
    }
    
    public void updatePet (Pet pet) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.merge(pet);
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }
    
    public void deletePet (Pet pet) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            Account user = pet.getOwner();
            user.getPetCollection().remove(pet);
            trans.begin();
            em.remove(em.merge(pet));
            em.merge(user);
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }    
}
