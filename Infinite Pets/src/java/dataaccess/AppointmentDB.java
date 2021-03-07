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
import models.Account;
import models.Appointment;
import models.Service;

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
    public List<Appointment> getAllAppointmentsByUserId(int accountId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Account account = em.find(Account.class, accountId);
            return account.getAppointmentList();
        } finally {
            em.close();
        }
    }
    
    
    public Appointment getAppointmentById(int id) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            return em.find(Appointment.class, id);
        } finally {
            em.close();
        }
//        return null;
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
            
            Account acc = em.find(Account.class, appointment.getClientID().getUserId());
            
//            Account acc = accDB.getAccountByClientID(appointment.getClientID());
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
        return false; 
    }
    
    public boolean update(Appointment appointment) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        
        try {
            tr.begin();
            em.merge(appointment);
            tr.commit();
            return true;
        }  catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            
            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, "Cannot update " + appointment.toString(), e); 
        } finally {
            em.close();
        }
        return false;
    }
    
    
        /*********************************
     * Admin Use only
     ********************************/
    
    /**
     * Returns list all of the appointments (for all users)
     * @return list of the appointments.
     */
    public List<Appointment> getAllAppointments() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
           return em.createNamedQuery("Appointment.findAll", Appointment.class).getResultList();
        } finally {
            em.close();
        }
    } 
    
//    public static void main(String[] args) throws Exception {
//        AppointmentDB ap = new AppointmentDB();
//        
//        
//        // Test get all appointments 
////        List<Appointment> list = ap.getAllAppointments(2);
////        for (Appointment a: list) {
////            System.out.println(a);
////        }
//        
//        // Test adding new appointment
////        Appointment appt = new Appointment(0, new Date(), false, false, false);
////        
////        // Find account
//        AccountDB acDB = new AccountDB();
////        Account acc = acDB.getAccountByUsername("anne");
//        
////        appt.setClientID(acc);
////        appt.setClientID(acDB.getAccountByUsername("employee"));
////        appt.setServiceID(new Service(1, "test", new BigDecimal(10.00), true, true, true));
////        ap.insert(appt);
//        
////        // Test get all appointments 
////        list = ap.getAllAppointments(2);  
////        for (Appointment a: list) {
////                System.out.println(a);
////        }
//        
//        // test get by id
//        System.out.println(ap.getAppointmentById(1));
//        
//        // Test update
//        Appointment appt = ap.getAppointmentById(1);
//        appt.setConfirmed(true);
//        ap.update(appt);
    
//        AccountDB acDB = new AccountDB();
//        Account acc = acDB.getAccountByUsername("anne");
//        
//        Appointment appt = new Appointment(0, new Date(), false, false, false);
//        appt.setClientID(acc);
//        appt.setServiceID(new Service(1, "test", new BigDecimal(10.00), true, true, true));
//        ap.insert(appt);
//        
//        List<Appointment> allAppts = ap.getAllAppointments();
//        for (Appointment a : allAppts) {
//            System.out.println(a.getClientID().getUsername() + " " + a.getServiceID().getServiceName());
//        }
//    }
    
}

