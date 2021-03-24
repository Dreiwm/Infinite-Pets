/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Account;

/**
 * 
 * @author Chris
 */
public class AccountDB {
    
    public Account getAccountByUsername(String username) throws Exception{
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
       try{
           Account user = em.createNamedQuery("Account.findByUsername",Account.class).setParameter("username", username).getSingleResult();
           
           return user;
       }
       finally{
           em.close();
       }
    }
  
    public List<Account> getAllAccount(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();  
        try{
            List<Account> users = em.createNamedQuery("Account.findAll", Account.class).getResultList();
            return users;
        }
        finally{
            em.close();
        }
    }
    
    public void insertAccount(Account user) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
           trans.begin();
           em.persist(user);
           trans.commit();
       } catch (Exception e){
           trans.rollback();
       }
       finally{
           em.close();
       }
    }

    public void deleteAccount(Account user) throws Exception{
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
       EntityTransaction trans = em.getTransaction();
       try{
           trans.begin();
           em.remove(em.merge(user));
           trans.commit();
       } catch (Exception e){
           trans.rollback();
       }
       finally{
           em.close();
       }
    }
    
    public void updateAccount(Account user) throws Exception{
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
       EntityTransaction trans = em.getTransaction();
       try{
           trans.begin();
           em.merge(user);
           trans.commit();
       } catch (Exception e){
           trans.rollback();
       }
       finally{
           em.close();
       }
    }     
}
