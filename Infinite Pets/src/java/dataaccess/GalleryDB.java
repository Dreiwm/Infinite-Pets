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
import models.Gallery;

/**
 * Responsible for communicating with Gallery table in the database.
 * @author Riley
 */
public class GalleryDB {
   /**
     * Inserts the Gallery into the gallery table in the database.
     * @param gallery Gallery to be inserted into.
     * @return returns true if successfully inserted into.
     * @throws Exception if something went wrong with process of inserting into database.
     */
    public boolean insert(Gallery gallery) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.persist(gallery);
            tr.commit();
            return true;
        } catch (Exception e) {
            // Only rollback if transaction is active.
            if (tr.isActive()) {
                tr.rollback();
            }
            Logger.getLogger(Gallery.class.getName()).log(Level.SEVERE, "Cannot insert " + gallery.toString(), e); 

        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Updates given Gallery object in the database.
     * @param gallery the Gallery object to be updated.
     * @return true if Gallery was successfully persisted.
     * @throws Exception if something went wrong with process of updating the object in the database.
     */
    public boolean update(Gallery gallery) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.merge(gallery);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            Logger.getLogger(Gallery.class.getName()).log(Level.SEVERE, "Cannot update " + gallery.toString(), e); 
        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Delete a row with given Gallery object from the database.
     * @param gallery the Gallery object to be deleted from the database.
     * @return true if successfully removed.
     * @throws Exception if something went wrong with process of deleting ab object from database.
     */
    public boolean delete(Gallery gallery) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
           tr.begin();
           em.remove(em.merge(gallery));
           tr.commit();
       } catch (Exception e){
           if (tr.isActive())
               tr.rollback();
            Logger.getLogger(Gallery.class.getName()).log(Level.SEVERE, "Cannot delete " + gallery.toString(), e); 
           
       }
       finally {
           em.close();
       }
        return false;
    }
    
    /**
     * Returns the Gallery object with given ID.
     * @param id the id to be used to access a specific row in the Gallery table.
     * @return returns the Gallery object with given ID.
     * @throws Exception if something went wrong with process of retrieving given ID from database.
     */
    public Gallery get(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.find(Gallery.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Returns List of all Gallery objects
     * @return the List of Gallery objects from the gallery table.
     * @throws Exception if something went wrong with the process of retrieving all Gallery from the database.
     */
    public List<Gallery> getAllGalleries() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("Gallery.findAll", Gallery.class).getResultList();
        } finally {
            em.close();
        }
    } 
}
