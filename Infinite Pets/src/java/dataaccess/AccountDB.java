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
    
    /**
     * Retrieves account using email
     * @param email the email to be found from DB.
     * @return returns Account
     * @throws Exception  if something went wrong.
     */
    public Account getAccountByEmail(String email) throws Exception{
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
       try{
           Account user = em.createNamedQuery("Account.findByEmail",Account.class).setParameter("email", email).getSingleResult();
//           
           return user;
       }
       finally{
           em.close();
       }
    }
    
    public List<Account> getAllIsEmployee(){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        System.out.println("Getting IsEmployeeList");
        try{
            List<Account> employees = em.createNamedQuery("Account.findByIsEmployee", Account.class).setParameter("isEmployee", true).getResultList();
            return employees;
        } finally {
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
    
    public static void main(String[] args) throws Exception {
        AccountDB acDB = new AccountDB();
        
        System.out.println(acDB.getAccountByEmail("bccrs.test@gmail.com"));
        
    }
}
