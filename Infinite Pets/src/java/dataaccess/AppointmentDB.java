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
import models.Account;

/**
 *
 * @author Riley
 * AppointmentDB is responsible for communicating with DB - mostly with appointments related tables
 */
public class AppointmentDB {
    /**
     * Returns List of the appointments that belongs to an account
     * @param accountId accountId to be used to return list of appointments
     * @return list of Appointment
     */
    public List<Appointment> getAllAppointments(int accountId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Account account = em.find(Account.class, accountId);
            account.getAppointments();
        } finally {
            em.close();
        }
    }
    
    /**
     * Inserts an appointment into DB
     * @param appointment appointment to be inserted into.
     * @return true if successfully inserted.
     */
    public boolean insert(Appointment appointment) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            
            // get account from appointment
            AccountDB accDB = new AccountDB();
            Account acc = accDB.getAccountByUsername(appointment.getUsername());
            acc.getAppointmentList().add(appointment);
            
            tr.begin();
            em.persist(appointment); // persist appt
            em.merge(acc); // merge account with newly added appointment
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            
            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, "Cannot insert " + appointment.toString(), e); 
        } finally {
            em.close();
        }
    }
}
