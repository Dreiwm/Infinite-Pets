/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Discount;
import models.Promotion;

/**
 * Responsible for interacting with discount table in the database.
 * @author Riley
 */
public class DiscountDB {
    
    /**
     * Inserts the Discount into the discount table in the database.
     * @param disc Discount to be inserted into.
     * @return returns true if successfully inserted into.
     * @throws Exception if something went wrong with process of inserting into database.
     */
    public boolean insert(Discount disc) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
       EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.persist(disc);
            tr.commit();
            return true;
        } catch (Exception e) {
            // Only rollback if transaction is active.
            if (tr.isActive()) {
                tr.rollback();
            }
            Logger.getLogger(Discount.class.getName()).log(Level.SEVERE, "Cannot insert " + disc.toString(), e); 

        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Updates given Discount object in the database.
     * @param disc the Discount object to be updated.
     * @return true if Discount was successfully persisted.
     * @throws Exception if something went wrong with process of updating the object in the database.
     */
    public boolean update(Discount disc) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.merge(disc);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            Logger.getLogger(Discount.class.getName()).log(Level.SEVERE, "Cannot update " + disc.toString(), e); 
        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Deleted a row with given Discount object from the database.
     * @param disc the Discount object to be deleted from the database.
     * @return true if successfully removed.
     * @throws Exception if something went wrong with process of deleting ab object from database.
     */
    public boolean delete(Discount disc) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
           tr.begin();
           em.remove(em.merge(disc));
           tr.commit();
       } catch (Exception e){
           if (tr.isActive())
               tr.rollback();
            Logger.getLogger(Discount.class.getName()).log(Level.SEVERE, "Cannot delete " + disc.toString(), e); 
           
       }
       finally {
           em.close();
       }
        return false;
    }
    
    /**
     * Returns the Discount object with given ID.
     * @param id the id to be used to access a specific row in the Discount table.
     * @return returns the Discount object with given ID.
     * @throws Exception if something went wrong with process of retrieving given ID from database.
     */
    public Discount get(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.find(Discount.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Returns List of all Discount objects
     * @return the List of Discount objects from the discount table.
     * @throws Exception if something went wrong with the process of retrieving all Discounts from the database.
     */
    public List<Discount> getAllDiscounts() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("Discount.findAll", Discount.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    // test 
    public static void main(String[] args) throws Exception {
        DiscountDB dDB = new DiscountDB();
        
        // test insert twice
        Discount disc1 = new Discount(0, new BigDecimal(12.00), 'a');
        Discount disc2 = new Discount(0, new BigDecimal(11.00), 'b');
        
        Promotion pr = new Promotion(2, "asdf", "Some cool promo", new Date(), new Date(), true);
        
        disc1.setPromoID(pr);
        disc2.setPromoID(pr);
        
        dDB.insert(disc1);
        dDB.insert(disc2);
        
        
        // test get all
        List<Discount> l = dDB.getAllDiscounts();
        for (Discount d : l) {
            System.out.println(d.getDiscountID() + " " + d.getDiscountType());
        }
        
        
        // test get by id
        System.out.println(dDB.get(2));
        
        
        // test update
        disc2 = dDB.get(2);
        disc2.setDiscount(new BigDecimal(20.00));
        dDB.update(disc2);

        
        // test get updated object
        System.out.println(dDB.get(2));
        
        // test delete first item.
        dDB.delete(disc1);
        
        // last test get all (should contain only one item)
        l = dDB.getAllDiscounts();
        for (Discount d : l) {
            System.out.println(d.getDiscountID() + " " + d.getDiscountType());
        }
    }
}
