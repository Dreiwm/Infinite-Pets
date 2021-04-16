/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Breed;

/**
 *
 * @author Chris
 */
public class BreedDB {
    
    public List<Breed> getBreedByAnimalId(int animalType) throws Exception{
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
       try{
           List<Breed> allBreeds = em.createNamedQuery("Breed.findByAnimalTypeId", Breed.class).setParameter("animalTypeId", animalType).getResultList();
           return allBreeds;
       }
       finally{
           em.close();
       }
    }
    
    public List<Breed> getAllAnimalBreeds(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            List<Breed> allBreeds = em.createNamedQuery("Breed.findAll", Breed.class).getResultList();
            return allBreeds;
        }
        finally{
            em.close();
        }
    }
        
    public void insertBreed(Breed breed){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
           trans.begin();
           em.persist(breed);
           trans.commit();
       } catch (Exception e){
           trans.rollback();
       }
       finally{
           em.close();
       }
    }
    
    public void updateBreed(Breed breed){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            trans.begin();
            em.merge(breed);
            trans.commit();
        }catch(Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
    }        
}
