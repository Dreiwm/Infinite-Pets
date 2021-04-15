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
import models.Empqualification;

/**
 * Responsible for interacting with Empqualification table in the database.
 * @author Riley
 */
public class EmpQualificationDB {
    /**
     * Returns all of the EmpQualificationTypes from DB
     * @return list of EmpQualificationTypes
     */
    public List<Empqualification> getAllEmployeeQualifications(int employeeID) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("Empqualification.findByEmployeeID", Empqualification.class).setParameter("EmployeeID", employeeID).getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Returns the EmpQualificationType from DB. 
     * @param id the id of location to retrieve from DB
     * @return the EmpQualificationType.
     */
    public Empqualification get(int id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.find(Empqualification.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Inserts the new EmpQualifiactionType into the DB. 
     * @param empQType the EmpQualicationTType to be inserted into.
     * @return true if successfully inserted into.
    * @throws java.lang.Exception if something went wrong with transaction.

     */
    public boolean insert(Empqualification empQType) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.persist(empQType);
            tr.commit();
            return true;
        } catch (Exception e) {
            // Only rollback if transaction is active.
            if (tr.isActive()) {
                tr.rollback();
            }
            Logger.getLogger(Empqualification.class.getName()).log(Level.SEVERE, "Cannot insert " + empQType.toString(), e); 

        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Updates the EmpQualifactionType.
     * @param empQType the EmpQualifcationType to be updated.
     * @return returns true if successfully updated.
     * @throws java.lang.Exception if something went wrong with transaction.
     */
    public boolean update(Empqualification empQType) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.merge(empQType);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            Logger.getLogger(Empqualification.class.getName()).log(Level.SEVERE, "Cannot update " + empQType.toString(), e); 
        } finally {
            em.close();
        }
        return false;
    }
    
     /**
     * Deletes the EmpQualificationType from the database.
     * @param empQType the object to be deleted from the database.
     * @return if successfully deleted from the database.
     * @throws Exception  if something went with accessing the database.
     */
    public boolean delete(Empqualification empQType) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try{
           tr.begin();
           em.remove(em.merge(empQType));
           tr.commit();
           return true;
       } catch (Exception e){
           if (tr.isActive())
               tr.rollback();
            Logger.getLogger(Empqualification.class.getName()).log(Level.SEVERE, "Cannot delete " + empQType.toString(), e); 
           
       }
       finally {
           em.close();
       }
        return false;
    }
    
    // test
//    public static void main(String[] args) throws Exception {
//        EmpQualificationDB empQTDB = new EmpQualificationDB();
//        
//        Empqualification empQType0 = new Empqualification(0, "Dog Nail Clipping", "Skilled in nail clipping on the dogs");
//        Empqualification empQType1 = new Empqualification(0, "Puppy Trainer", "Skilled in training the puppies.");
//        
//        
//        // test insert two items
//        System.out.println("Inserted in first item: " + empQTDB.insert(empQType0));
//        System.out.println("Inserted in second item: " + empQTDB.insert(empQType1));
//        
//        
//        // test get all
//        System.out.println("Results after inserting...");
//        empQTDB.getAllEmployeeQualificationTypes().forEach(empQType -> {
//            System.out.println(empQType.Empqualification() + empQType.getQualificationDescription());
//        });
//        
//        // test update
//        empQType0 = empQTDB.get(1);
//        empQType0.setQualificationDescription("Skilled in nail clipping on the dogs - varying sizes small to large.");
//        System.out.println("Updated first item: " + empQTDB.update(empQType0));
//        
//        // test see result after updating
//        System.out.println(empQTDB.get(1).getQualificationDescription());
//        
//        // remove first item
//        System.out.println("Removed first item: " + empQTDB.delete(empQType0));
//        
//        // test see all items, sb be only one item in db (if you run this app once).
//        System.out.println("Results after inserting...");
//        empQTDB.getAllEmployeeQualificationTypes().forEach(empQType -> {
//            System.out.println(empQType.getQualificationName() + empQType.getQualificationDescription());
//        });
//    }
}
