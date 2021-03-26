/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AccountDB;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;

/**
 *
 * @author Chris
 */
public class AccountServices {
        //Retrieves an account by the email
    /**
     * Returns an account from DB using email, usually retrieved from session.
     * @param email the email to used to retrieve an account from DB.
     * @return returns Account 
     * @throws Exception if something went wrong.
     */
    public Account getAccount(String email) throws Exception{
        AccountDB accountDB = new AccountDB();
        Account account = new Account();
        account = accountDB.getAccountByEmail(email);
        return account;
    }
    
    //Retreave account by reset token
    public Account getAccountResetToken(String resetToken) throws Exception{
        AccountDB accountDB = new AccountDB();
        Account account = new Account();
        //account = accountDB.getAccountByResetToken(resetToken);
        return account;
    }
    
    //Creates a Customer account and sends it to the database to be added
    public void createUserAccount(String username, String password, String email, String firstName, 
            String lastName)throws Exception{
        AccountDB accountDB = new AccountDB();
        Account account = new Account(0, username, password, email, firstName, lastName, false, false);
        accountDB.insertAccount(account);
    }
    
    //Create a staff Account and add it to the Database
     public void createStaffAccount(String username, String password, String email, String firstName, 
            String lastName)throws Exception{
        AccountDB accountDB = new AccountDB();
        Account account = new Account(0, username, password, email, firstName, lastName, true, true);
        accountDB.insertAccount(account);
    }   
    
    /**
     * Updates an User Level Account with provided info.
     * To insure security a new Account object is created using inputed info and
     * lists are added after
     */
    public void updateUserAccount(String username, String password, String email, String firstName, 
            String lastName, Boolean isConfermed) throws Exception{
        AccountDB accountDB = new AccountDB();
        try{
            Account account = accountDB.getAccountByEmail(email);
            Account tempAccount = account;
            account = new Account(account.getUserId(), username, password, email, firstName, lastName, false, isConfermed);
            account.setAppointmentList(tempAccount.getAppointmentList());
            account.setEmployeeList(tempAccount.getEmployeeList());
            account.setPetList(tempAccount.getPetList());
            
            accountDB.updateAccount(account);
        }
        catch(Exception e){
            
        }   
    }
    
    /**
     * Updates an account with newly created DeleteAccountCode
     * @param deleteToken randomized token generated when submitting form to send an account deletion request. 
     * @param email email to iedentify which account to be updated with new deleteAccountcCode.
     */
    public void updateDeleteToken(String deleteToken, String email) {
        try {
            AccountDB acDB = new AccountDB();
            Account acc = acDB.getAccountByEmail(email);
            
            // now we have account to work with, update Token
            acc.setDeleteAccountCode(deleteToken);
            acDB.updateAccount(acc);
            
            
        } catch (Exception ex) {
            Logger.getLogger(AccountServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Updates an Staff Level Account with provided info.
     * To insure security a new Account object is created using inputed info and
     * lists are added after
     */
    public void updateStaffAccount(String username, String password, String email, String firstName, 
            String lastName, boolean isStaff)throws Exception{
                AccountDB accountDB = new AccountDB();
        try{
            Account account = accountDB.getAccountByEmail(email);
            Account tempAccount = account;
            account = new Account(account.getUserId(), username, password, email, firstName, lastName, isStaff, true);
            account.setAppointmentList(tempAccount.getAppointmentList());
            account.setEmployeeList(tempAccount.getEmployeeList());
            account.setPetList(tempAccount.getPetList());
            accountDB.updateAccount(account);
        }
        catch(Exception e){
            
        }
    }
    
    //Removes an existing account
    public void deleteAccount(String email)throws Exception{
        AccountDB accountDB = new AccountDB();
        Account toDelete = accountDB.getAccountByEmail(email);
        accountDB.deleteAccount(toDelete);
    }
}
