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
import models.Schedule;

/**
 * Responsible for communicating with schedule table in the database
 * @author Riley
 */
public class ScheduleDB {
    /**
     * Inserts the Schedule into the schedule table in the database.
     * @param schedule Schedule to be inserted into.
     * @return returns true if successfully inserted into.
     * @throws Exception if something went wrong with process of inserting into database.
     */
    public boolean insert(Schedule schedule) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.persist(schedule);
            tr.commit();
            return true;
        } catch (Exception e) {
            // Only rollback if transaction is active.
            if (tr.isActive()) {
                tr.rollback();
            }
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, "Cannot insert " + schedule.toString(), e); 

        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Updates given Schedule object in the database.
     * @param schedule the Schedule object to be updated.
     * @return true if Schedule was successfully persisted.
     * @throws Exception if something went wrong with process of updating the object in the database.
     */
    public boolean update(Schedule schedule) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.merge(schedule);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, "Cannot update " + schedule.toString(), e); 
        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Delete a row with given Schedule object from the database.
     * @param schedule the Schedule object to be deleted from the database.
     * @return true if successfully removed.
     * @throws Exception if something went wrong with process of deleting ab object from database.
     */
    public boolean delete(Schedule schedule) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        try {
           tr.begin();
           em.remove(em.merge(schedule));
           tr.commit();
       } catch (Exception e){
           if (tr.isActive())
               tr.rollback();
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, "Cannot delete " + schedule.toString(), e); 
           
       }
       finally {
           em.close();
       }
        return false;
    }
    
    /**
     * Returns the Schedule object with given ID.
     * @param id the id to be used to access a specific row in the Schedule table.
     * @return returns the Schedule object with given ID.
     * @throws Exception if something went wrong with process of retrieving given ID from database.
     */
    public Schedule get(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.find(Schedule.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Returns List of all Schedule objects
     * @return the List of Schedule objects from the discount table.
     * @throws Exception if something went wrong with the process of retrieving all Discounts from the database.
     */
    public List<Schedule> getAllSchedules() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("Schedule.findAll", Schedule.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    // test
    public  static void main(String[] args) throws Exception {
        EmployeeDB empDB = new EmployeeDB();
        
        Employee emp = empDB.get(1);
        emp.getScheduleList().forEach(s -> {
            System.out.println(s.getStartTime() + " to " + s.getEndTime());
        });
    }
}
