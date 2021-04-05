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
import models.Management;

/**
 * Responsible with interacting management table in the database.
 * @author Riley
 */
public class ManagementDB {
    /**
     * Inserts the Management into the management table in the database.
     * @param mgnt Management to be inserted into.
     * @return returns true if successfully inserted into.
     * @throws Exception if something went wrong with process of inserting into database.
     */
    public boolean insert(Management mgnt) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.persist(mgnt);
            tr.commit();
            return true;
        } catch (Exception e) {
            // Only rollback if transaction is active.
            if (tr.isActive()) {
                tr.rollback();
            }
            Logger.getLogger(Management.class.getName()).log(Level.SEVERE, "Cannot insert " + mgnt.toString(), e); 

        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Updates given Management object in the database.
     * @param mgnt the Management object to be updated.
     * @return true if Management was successfully persisted.
     * @throws Exception if something went wrong with process of updating the object in the database.
     */
    public boolean update(Management mgnt) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.merge(mgnt);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            Logger.getLogger(Management.class.getName()).log(Level.SEVERE, "Cannot update " + mgnt.toString(), e); 
        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Delete a row with given Management object from the database.
     * @param mgnt the Management object to be deleted from the database.
     * @return true if successfully removed.
     * @throws Exception if something went wrong with process of deleting ab object from database.
     */
    public boolean delete(Management mgnt) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
           tr.begin();
           em.remove(em.merge(mgnt));
           tr.commit();
       } catch (Exception e){
           if (tr.isActive())
               tr.rollback();
            Logger.getLogger(Management.class.getName()).log(Level.SEVERE, "Cannot delete " + mgnt.toString(), e); 
           
       }
       finally {
           em.close();
       }
        return false;
    }
    
    /**
     * Returns the Management object with given ID.
     * @param id the id to be used to access a specific row in the Management table.
     * @return returns the Management object with given ID.
     * @throws Exception if something went wrong with process of retrieving given ID from database.
     */
    public Management get(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.find(Management.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Returns List of all Management objects
     * @return the List of Management objects from the Employee table.
     * @throws Exception if something went wrong with the process of retrieving all Management objects from the database.
     */
    public List<Management> getAllManagement() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("Management.findAll", Management.class).getResultList();
        } finally {
            em.close();
        }
    }
}
