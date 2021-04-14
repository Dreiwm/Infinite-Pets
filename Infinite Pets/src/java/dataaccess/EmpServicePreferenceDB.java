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
import models.EmpServicePreference;

/**
 *
 * @author Riley
 */
public class EmpServicePreferenceDB {
    /**
     * Returns all of the EmpServicePreference from DB
     * @return list of EmpServicePreference
     */
    public List<EmpServicePreference> getAllEmpServicePreferences() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("EmpServicePreference.findAll", EmpServicePreference.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Returns the EmpServicePreference from DB. 
     * @param id the id of EmpServicePreference to retrieve from DB
     * @return the EmpServicePreference.
     */
    public EmpServicePreference get(int id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            
            return em.find(EmpServicePreference.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Inserts the new EmpServicePreference into the DB. 
     * @param empSP the EmpServicePreference to be inserted into.
     * @return true if successfully inserted into.
    * @throws java.lang.Exception if something went wrong with transaction.

     */
    public boolean insert(EmpServicePreference empSP) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.persist(empSP);
            tr.commit();
            return true;
        } catch (Exception e) {
            // Only rollback if transaction is active.
            if (tr.isActive()) {
                tr.rollback();
            }
            Logger.getLogger(EmpServicePreference.class.getName()).log(Level.SEVERE, "Cannot insert " + empSP.toString(), e); 
            throw new Exception(e);
        }
        finally {
            em.close();
        }
    }
    
    /**
     * Updates the EmpServicePreference.
     * @param empSP the EmpServicePreference to be updated.
     * @return returns true if successfully updated.
     * @throws java.lang.Exception if something went wrong with transaction.
     */
    public boolean update(EmpServicePreference empSP) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.merge(empSP);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            Logger.getLogger(EmpServicePreference.class.getName()).log(Level.SEVERE, "Cannot update " + empSP.toString(), e); 
        } finally {
            em.close();
        }
        return false;
    }
    
     /**
     * Deletes the EmpServicePreference from the database.
     * @param empSP the object to be deleted from the database.
     * @return if successfully deleted from the database.
     * @throws Exception  if something went with accessing the database.
     */
    public boolean delete(EmpServicePreference empSP) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try{
           tr.begin();
           em.remove(em.merge(empSP));
           tr.commit();
           return true;
       } catch (Exception e){
           if (tr.isActive())
               tr.rollback();
            Logger.getLogger(EmpServicePreference.class.getName()).log(Level.SEVERE, "Cannot delete " + empSP.toString(), e); 
           
       }
       finally {
           em.close();
       }
        return false;
    }
}
