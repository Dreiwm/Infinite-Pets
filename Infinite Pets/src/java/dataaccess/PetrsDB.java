/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;
import dataaccess.exception.PetsDBException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import model.Pets;
/**
 *
 * @author Riley
 */
public class PetrsDB {
    public int insert(Pets pet) throws PetsDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tr = em.getTransaction();
        
        try {
            tr.begin();
            em.persist(pet);
            tr.commit();
            return 1;
        } catch (Exception e) {
            if (tr.isActive()) {
               tr.rollback();
            }
            Logger.getLogger(Pets.class.getName()).log(Level.SEVERE, "cannot insert " + pet.toString(), e);
            throw new PetsDBException("Cannot insert a new pet");
        } finally {
            em.close();
        }
        
    }

  
}
