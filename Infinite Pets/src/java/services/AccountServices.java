/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AccountDB;
import dataaccess.LocationDB;
import models.Account;
import models.Location;

/**
 *
 * @author Chris
 */
public class AccountServices {
        //Retrieves an account by the emil
    public Account getAccount(String username)throws Exception{
        AccountDB accountDB = new AccountDB();
        Account account = new Account();
        account = accountDB.getAccountByUsername(username);
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
    public void createUserAccount(String firstName, String lastName, String email, String pass, Location address)throws Exception{
        AccountDB accountDB = new AccountDB();
        Account account = new Account(0, pass, email, firstName, lastName, false, false);
        account.setAddress(address);
        accountDB.insertAccount(account);
    }
    
    //Create a staff Account and add it to the Database
     public void createStaffAccount(String password, String email, String firstName, 
            String lastName)throws Exception{
        AccountDB accountDB = new AccountDB();
        Account account = new Account(0, password, email, firstName, lastName, true, true);
        accountDB.insertAccount(account);
    }   
     
    //Create an adress object for an account
     public Location createAddress(String postalCode, String address, String city, String country, String province, String area) throws Exception{
        LocationDB locDB = new LocationDB();
        Location location = new Location (0, 'R', postalCode, address, city, country, province, area);
        locDB.insert(location);
        return location;
     }
    
    /**
     * Updates an User Level Account with provided info.
     * To insure security a new Account object is created using inputed info and
     * lists are added after
     */
    public void updateUserAccount(String password, String email, String firstName, 
            String lastName, Boolean isConfermed)throws Exception{
        AccountDB accountDB = new AccountDB();
        try{
            Account account = accountDB.getAccountByUsername(email);
            Account tempAccount = account;
            account = new Account(account.getUserId(), password, email, firstName, lastName, false, isConfermed);
            account.setAppointmentList(tempAccount.getAppointmentList());
            account.setEmployeeList(tempAccount.getEmployeeList());
            account.setPetList(tempAccount.getPetList());
            accountDB.updateAccount(account);
        }
        catch(Exception e){
            
        }   
    }
    
    /**
     * Updates an Staff Level Account with provided info.
     * To insure security a new Account object is created using inputed info and
     * lists are added after
     */
    public void updateStaffAccount(String password, String email, String firstName, 
            String lastName, boolean isStaff)throws Exception{
                AccountDB accountDB = new AccountDB();
        try{
            Account account = accountDB.getAccountByUsername(email);
            Account tempAccount = account;
            account = new Account(account.getUserId(), password, email, firstName, lastName, isStaff, true);
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
        Account toDelete = accountDB.getAccountByUsername(email);
        accountDB.deleteAccount(toDelete);
    }
}
