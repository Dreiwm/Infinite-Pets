/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Servicetype;

/**
 * Responsible for interacting with Servicetype table in the database
 * @author Riley
 */
public class ServiceTypeDB {
    /**
     * Inserts the Servicetype into the database.
     * @param serviceType Servicetype to be inserted into the database
     * @return returns true if successfully inserted.
     * @throws Exception if something went wrong in process of inserting into the database.
     */
    public boolean insert(Servicetype serviceType) throws Exception {
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
       EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.persist(serviceType);
            tr.commit();
            return true;
        } catch (Exception e) {
            // Only rollback if transaction is active.
            if (tr.isActive()) {
                tr.rollback();
            }
            Logger.getLogger(Servicetype.class.getName()).log(Level.SEVERE, "Cannot insert " + serviceType.toString(), e); 

        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Updates the given Servicetype on the Servicetype table.
     * @param serviceType the Servicetype to be updated.
     * @return returns true if successfully updated.
     * @throws Exception if something went wrong in process of updating object in the database.
     */
    public boolean update(Servicetype serviceType) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.merge(serviceType);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            Logger.getLogger(Servicetype.class.getName()).log(Level.SEVERE, "Cannot update " + serviceType.toString(), e); 
        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Deletes the Servicetype from the database.
     * @param serviceType the object to be deleted from the database.
     * @return if successfully deleted from the database.
     * @throws Exception  if something went with accessing the database.
     */
    public boolean delete(Servicetype serviceType) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
       EntityTransaction tr = em.getTransaction();
       try{
           tr.begin();
           em.remove(em.merge(serviceType));
           tr.commit();
       } catch (Exception e){
           if (tr.isActive())
               tr.rollback();
            Logger.getLogger(Servicetype.class.getName()).log(Level.SEVERE, "Cannot delete " + serviceType.toString(), e); 
           
       }
       finally {
           em.close();
       }
        return false;
    }
    
    /**
     * Returns the Servicetype from the database with given ID.
     * @param id id to be used to access information about Servicetype
     * @return returns Servicetype if found on the database. 
     * @throws Exception if something went wrong with process of accessing database.
     */
    public Servicetype get(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.find(Servicetype.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Returns all Servicetype.
     * @return List of the Servicetype.
     * @throws Exception if something went wrong with process of accessing the database.
     */
    public List<Servicetype> getAllServiceTypes() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("Servicetype.findAll", Servicetype.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    // test
    public static void main(String[] args) throws Exception {
        ServiceTypeDB sTDB = new ServiceTypeDB();
        // test insert twice
        Servicetype catGrooming = new Servicetype(0, "Cat Grooming");
        Servicetype dogGrooming = new Servicetype(0, "Dog Grooming");
//        
        sTDB.insert(catGrooming);
        sTDB.insert(dogGrooming);
        
        // test get
        System.out.println(sTDB.get(1));
        System.out.println(sTDB.get(2));
        
        // test get all
        List<Servicetype> list = sTDB.getAllServiceTypes();
        for (Servicetype st : list) {
            System.out.println(st.getServiceTypeID() + " " + st.getServiceType());
        }
        
        // test update
        dogGrooming = sTDB.get(2);
        dogGrooming.setServiceType("Long Haired Dog Griooming");

        sTDB.update(dogGrooming);
        
        // test get updated obj
        System.out.println(sTDB.get(2).getServiceType());
    }
}