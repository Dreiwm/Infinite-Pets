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
import models.Appointment;
import models.AppointmentService;

/**
 *
 * @author Riley
 */
public class AppointmentServiceDB {
    
    /**
     * Returns an AppointmentService by ID.
     * @param id to be found on DB
     * @return returns AppointmentService. Null if cannot be found.
     */
    public AppointmentService getAppointmentById(int id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return em.find(AppointmentService.class, id);
        } finally {
            em.close();
        }
//        return null;
    }
    
    /**
     * Inserts an AppointmentService into DB
     * @param apptService AppointmentService to be inserted into.
     * @return true if successfully inserted.
     */
    public boolean insert(AppointmentService apptService) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.persist(apptService); // persist appt
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            
            Logger.getLogger(AppointmentService.class.getName()).log(Level.SEVERE, "Cannot insert " + apptService.toString(), e); 
        } finally {
            em.close();
        }
        return false; 
    }
    
    public boolean update(AppointmentService apptService) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        
        try {
            tr.begin();
            em.merge(apptService);
            tr.commit();
            return true;
        }  catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            
            Logger.getLogger(AppointmentService.class.getName()).log(Level.SEVERE, "Cannot update " + apptService.toString(), e); 
        } finally {
            em.close();
        }
        return false;
    }
    
    public boolean delete(AppointmentService apptService) {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
       EntityTransaction trans = em.getTransaction();
       try{
           trans.begin();
           em.remove(apptService);
           trans.commit();
           return true;
       } catch (Exception e){
           trans.rollback();
       }
       finally{
           em.close();
       }
        
        return false;
    }

    
    /**
     * Returns list all of the appointment services (for all users)
     * @return list of the appointmentServices.
     */
    public List<AppointmentService> getAllAppointmentServices() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
           return em.createNamedQuery("AppointmentService.findAll", AppointmentService.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<AppointmentService> getAllAppointmentServicesByAppointmentId(int apptId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
           return em.createNamedQuery("AppointmentService.", AppointmentService.class).getResultList();
        } finally {
            em.close();
        }
    }
}
