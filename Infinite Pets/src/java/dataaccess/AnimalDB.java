/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.AnimalType;

/**
 *
 * @author Chris
 */
public class AnimalDB {
    
    public AnimalType getAnimalById(String username) throws Exception{
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
       try{
           AnimalType animal = em.find(AnimalType.class, username);
           return animal;
       }
       finally{
           em.close();
       }
    }
    
    public List<AnimalType> getAllAnimals() throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            List<AnimalType> allAnimals = em.createNamedQuery("AnimalType.findAll", AnimalType.class).getResultList();
            return allAnimals;
        }
        finally{
            em.close();
        }
    }
    
    public void insertAnimalType(AnimalType animal){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
           trans.begin();
           em.persist(animal);
           trans.commit();
       } catch (Exception e){
           trans.rollback();
       }
       finally{
           em.close();
       }
    }
    
    public void updateAnimalType(AnimalType animal){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.merge(animal);
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }  
    
    
}
