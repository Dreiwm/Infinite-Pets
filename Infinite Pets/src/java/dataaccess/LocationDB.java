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
import models.Location;

/**
 * Responsible for interacting with location table in the database.
 * @author Riley
 */
public class LocationDB {
    /**
     * Returns all of the locations from DB
     * @return list of locations
     */
    public List<Location> getAllLocations() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("Location.findAll", Location.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Returns the location from DB. 
     * @param id the id of location to retrieve from DB
     * @return the Location.
     */
    public Location get(int id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.find(Location.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Inserts the new location into the DB. 
     * @param l the location to be inserted into.
     * @return true if successfully inserted into.
    * @throws java.lang.Exception if something went wrong with transaction.

     */
    public boolean insert(Location l) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.persist(l);
            tr.commit();
            return true;
        } catch (Exception e) {
            // Only rollback if transaction is active.
            if (tr.isActive()) {
                tr.rollback();
            }
            Logger.getLogger(Location.class.getName()).log(Level.SEVERE, "Cannot insert " + l.toString(), e); 

        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Updates the location.
     * @param l the service to be updated.
     * @throws java.lang.Exception if something went wrong with transaction.
     */
    public boolean update(Location l) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.merge(l);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            Logger.getLogger(Location.class.getName()).log(Level.SEVERE, "Cannot update " + l.toString(), e); 
        } finally {
            em.close();
        }
        return false;
    }
    
     /**
     * Deletes the location from the database.
     * @param l the object to be deleted from the database.
     * @return if successfully deleted from the database.
     * @throws Exception  if something went with accessing the database.
     */
    public boolean delete(Location l) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try{
           tr.begin();
           em.remove(em.merge(l));
           tr.commit();
       } catch (Exception e){
           if (tr.isActive())
               tr.rollback();
            Logger.getLogger(Location.class.getName()).log(Level.SEVERE, "Cannot delete " + l.toString(), e); 
           
       }
       finally {
           em.close();
       }
        return false;
    }
    
    // test
    public static void main(String[] args) throws Exception {
        LocationDB lDB = new LocationDB();
//        Location l0 = new Location(0, 'R', "A1A1A1", "123 ST N.W", "Calgary", "Canada", "Alberta", 'SW');
//        new Location(Integer.SIZE, Character.MAX_VALUE, postalCode, address, city, country, province, Character.MAX_VALUE)
        // test insert twice
        
        // test get all
        
        // test update last item
        
        // test get result
        
        // delete last item
        
        // see results
    }
}
