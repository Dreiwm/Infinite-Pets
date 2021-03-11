/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Promotion;

/**
 * Responsible for interacting Promotion table in the database.
 * @author Riley
 */
public class PromotionDB {
    /**
     * Inserts the Discount into the promotion table in the database.
     * @param p Promotion to be inserted into.
     * @return returns true if successfully inserted into.
     * @throws Exception if something went wrong with process of inserting into database.
     */
    public boolean insert(Promotion p) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
       EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.persist(p);
            tr.commit();
            return true;
        } catch (Exception e) {
            // Only rollback if transaction is active.
            if (tr.isActive()) {
                tr.rollback();
            }
            Logger.getLogger(Promotion.class.getName()).log(Level.SEVERE, "Cannot insert " + p.toString(), e); 

        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Updates given Promotion object in the database.
     * @param p the Promotion object to be updated.
     * @return true if Promotion was successfully persisted.
     * @throws Exception if something went wrong with process of updating the object in the database.
     */
    public boolean update(Promotion p) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.merge(p);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            Logger.getLogger(Promotion.class.getName()).log(Level.SEVERE, "Cannot update " + p.toString(), e); 
        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Delete a row with given Promethean object from the database.
     * @param p the Promethean object to be deleted from the database.
     * @return true if successfully removed.
     * @throws Exception if something went wrong with process of deleting ab object from database.
     */
    public boolean delete(Promotion p) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
           tr.begin();
           em.remove(em.merge(p));
           tr.commit();
       } catch (Exception e){
           if (tr.isActive())
               tr.rollback();
            Logger.getLogger(Process.class.getName()).log(Level.SEVERE, "Cannot delete " + p.toString(), e); 
           
       }
       finally {
           em.close();
       }
        return false;
    }
    
    /**
     * Returns the Promotion object with given ID.
     * @param id the id to be used to access a specific row in the Discount table.
     * @return returns the Promotion object with given ID.
     * @throws Exception if something went wrong with process of retrieving given ID from database.
     */
    public Promotion get(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.find(Promotion.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Returns List of all Promotion objects
     * @return the List of Promotion objects from the promotion table.
     * @throws Exception if something went wrong with the process of retrieving all Discounts from the database.
     */
    public List<Promotion> getAllPromotions() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("Promotion.findAll", Promotion.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args) throws Exception {
        PromotionDB pDB = new PromotionDB();
        
        Promotion pr0 = new Promotion(0, "Some cool Promo", "Some promo description", new Date(), new Date(), true);
        Promotion pr1 = new Promotion(0, "zzzSome cool Promo", "Some promo description 1234", new Date(), new Date(), false);
        
        // test insert twice
//        pDB.insert(pr0);
//        pDB.insert(pr1);
        
        
        // test get all
        List<Promotion> l =  pDB.getAllPromotions();
        for (Promotion p : l) {
            System.out.println(p.getPromotionName() + " " + p.getPromoDescription());
        }
        
        // test update (first item)
        pr0 = pDB.get(1);
        pr0.setPromoDescription("Updated description. Seemignly another yet useless description");
        pDB.update(pr0);
        
        // test get newly updated item
        System.out.println(pDB.get(1).getPromoDescription());
        
        // Test delete promo
        pDB.delete(pr1);
        
        // See result
        l =  pDB.getAllPromotions();
        for (Promotion p : l) {
            System.out.println(p.getPromotionName() + " " + p.getPromoDescription());
        }
    }
    
}
