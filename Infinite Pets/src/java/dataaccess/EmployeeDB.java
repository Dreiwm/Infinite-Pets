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
import models.Employee;

/**
 *
 * @author Riley
 */
public class EmployeeDB {
    /**
     * Inserts the EMployee into the Employee table in the database.
     * @param emp Employee to be inserted into.
     * @return returns true if successfully inserted into.
     * @throws Exception if something went wrong with process of inserting into database.
     */
    public boolean insert(Employee emp) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.persist(emp);
            tr.commit();
            return true;
        } catch (Exception e) {
            // Only rollback if transaction is active.
            if (tr.isActive()) {
                tr.rollback();
            }
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, "Cannot insert " + emp.toString(), e); 

        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Updates given Employee object in the database.
     * @param emp the EMployee object to be updated.
     * @return true if Employee was successfully persisted.
     * @throws Exception if something went wrong with process of updating the object in the database.
     */
    public boolean update(Employee emp) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.merge(emp);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, "Cannot update " + emp.toString(), e); 
        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Delete a row with given Employee object from the database.
     * @param emp the Employee object to be deleted from the database.
     * @return true if successfully removed.
     * @throws Exception if something went wrong with process of deleting ab object from database.
     */
    public boolean delete(Employee emp) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
           tr.begin();
           em.remove(em.merge(emp));
           tr.commit();
       } catch (Exception e){
           if (tr.isActive())
               tr.rollback();
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, "Cannot delete " + emp.toString(), e); 
           
       }
       finally {
           em.close();
       }
        return false;
    }
    
    /**
     * Returns the Employee object with given ID.
     * @param id the id to be used to access a specific row in the Employee table.
     * @return returns the Employee object with given ID.
     * @throws Exception if something went wrong with process of retrieving given ID from database.
     */
    public Employee get(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }
    
    public Employee getByUserId(int userId)throws Exception{
          EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("Employee.findByUserID", Employee.class).setParameter("userID", userId).getSingleResult();
        } finally {
            em.close();
        }
    }
    
    /**
     * Returns List of all Employee objects
     * @return the List of Employee objects from the Employee table.
     * @throws Exception if something went wrong with the process of retrieving all Employee from the database.
     */
    public List<Employee> getAllEmployees() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
        } finally {
            em.close();
        }
    }
}
