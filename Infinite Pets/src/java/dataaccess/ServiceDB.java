/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Service;

/**
 *
 * @author Riley
 * ServiceDB is responsible for communicating with database.
 */
public class ServiceDB {
    /**
     * Returns all of the services from DB
     * @return list of services
     */
    public List<Service> getAllServices() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.createNamedQuery("Service.findAll", Service.class).getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Returns the service from DB. 
     * @param serviceId the id of service to retrieve from DB
     * @return the Service.
     */
    public Service getServiceById(int serviceId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.find(Service.class, serviceId);
        } finally {
            em.close();
        }
    }
    
        /**
     * Returns the service from DB. 
     * @param serviceName the name of service to retrieve from DB
     * @return the Service.
     */
    public Service getServiceByName(String serviceName) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            return em.find(Service.class, serviceName);
        } finally {
            em.close();
        }
    }
    
    /*********************************
     * Admin Use only
     ********************************/
    /**
     * Inserts the new service into the DB. For admin use only.
     * @param service the service to be inserted into.
     * @return true if successfully inserted into.
    * @throws java.lang.Exception if somethign went wrong with transaction.

     */
    public boolean insert(Service service) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.persist(service);
            tr.commit();
            return true;
        } catch (Exception e) {
            // Only rollback if transaction is active.
            if (tr.isActive()) {
                tr.rollback();
            }
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, "Cannot insert " + service.toString(), e); 

        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * Updates the service.For Admin use.
     * @param service the service to be updated.
     * @throws java.lang.Exception if somethign went wrong with transaction.
     */
    public boolean update(Service service) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.merge(service);
            tr.commit();
            return true;
        } catch (Exception e) {
            if (tr.isActive())
                tr.rollback();
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, "Cannot update " + service.toString(), e); 
        } finally {
            em.close();
        }
        return false;
    }
    
    /**
     * For testing purposes only
     * @param args 
     */
    public static void main(String[] args) throws Exception {
        ServiceDB sb = new ServiceDB();
        
        // Test insert
//        Service service = new Service(0, "test", new BigDecimal(11.00), true, true, true);
//        sb.insert(service);
        
        // test get service by id
        System.out.println(sb.getServiceById(1).getServiceName());
    }
}
