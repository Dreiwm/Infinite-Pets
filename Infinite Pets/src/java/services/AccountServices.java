/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AccountDB;
import dataaccess.EmpQualificationDB;
import dataaccess.EmployeeDB;
import dataaccess.LocationDB;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;
import models.Employee;
import models.Empqualification;
import models.Location;
import models.Service;

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
    public void createUserAccount(String firstName, String lastName, String email, String pass, Location address)throws Exception{
        AccountDB accountDB = new AccountDB();
        PasswordServices pServ = new PasswordServices();
        String passwordSalt = pServ.getRandomSalt();
        String passwordHash = pServ.generatePasswordHash(pass, passwordSalt);
        Account account = new Account(0, passwordHash, passwordSalt, email, firstName, lastName, false, false);
        account.setAddress(address);
        accountDB.insertAccount(account);
    }
    
    //Create a staff Account and add it to the Database
     public void createStaffAccount(String password, String email, String firstName, Location address,
            String lastName, List<Service> qList)throws Exception{
        //Create an account
        AccountDB accountDB = new AccountDB();
        PasswordServices pServ = new PasswordServices();
        String passwordSalt = pServ.getRandomSalt();
        String passwordHash = pServ.generatePasswordHash(password, passwordSalt);
        Account account = new Account(0, passwordHash, passwordSalt, email, firstName, lastName, true, true);
        account.setAddress(address);
        accountDB.insertAccount(account);
        //Set employee with an acount
        Employee employee = new Employee(0, false, false, true);
        employee.setUserID(account);
        //Set employee qualifications
        employee.setServiceList(qList);
//        EmpQualificationDB eqDB = new EmpQualificationDB();
//        for (int i = 0; i < qList.size(); i++){
//            Empqualification eq = new Empqualification(0, employee.getEmployeeID(), qList.get(i).getServiceID());
//            eqDB.insert(eq);
//        }
        EmployeeDB empDB = new EmployeeDB();
        empDB.insert(employee);
    }   
     
    //Create an adress object for an account 
    //Note: locationType is (R)esident and will need to be set to (E)mployee
     public Location createAddress(String postalCode, String address, String city, String country, String province, String area) throws Exception{
        LocationDB locDB = new LocationDB();
        Location location = new Location (0, 'R', postalCode, address, city, country, province, area);
        locDB.insert(location);
        return location;
     }
    
    /**
     * Updates an User Level Account and Address with provided info.
     * To insure security a new Account object is created using inputed info and
     * lists are added after
     * NOTE: NEED TO CHECK IF NEW EMAIL == CURRENT OR SHOULD REPLACE WHICH MEANS YOU CAN SEARCH BY EMAIL BUT BY ID
     */
    public void updateUserAccount(String password, String email, String firstName, 
            String lastName, Boolean isConfermed, String address, String city, String prov, String country, String postal, String area)throws Exception{
        AccountDB accountDB = new AccountDB();
        try{
            Account account = accountDB.getAccountByEmail(email);
            Account tempAccount = account;
            Location location = account.getAddress();
            Location tempLocation = location;
            location = new Location(tempLocation.getLocationID(), tempLocation.getLocationType(), postal, address, city, country, prov, area);
            PasswordServices pServ = new PasswordServices();
            String passwordHash = null;
            String passwordSalt = null;
            if (pServ.doesMatchHash(password, account.getPasswordSalt(), account.getPasswordHash())){
                passwordHash = account.getPasswordHash();
                passwordSalt = account.getPasswordSalt();
            }
            else{
                passwordSalt = pServ.getRandomSalt();
                passwordHash = pServ.generatePasswordHash(password, passwordSalt);
            }
            account = new Account(0, passwordHash, passwordSalt, email, firstName, lastName, false, false);
            account.setAppointmentList(tempAccount.getAppointmentList());
            account.setEmployeeList(tempAccount.getEmployeeList());
            account.setPetList(tempAccount.getPetList());
            account.setAddress(location);
            LocationDB locDB = new LocationDB();
            locDB.update(location);
            accountDB.updateAccount(account);
        }
        catch(Exception ex){
            Logger.getLogger(AccountServices.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    /**
     * Updates an account with newly created DeleteAccountCode
     * @param deleteToken randomized token generated when submitting form to send an account deletion request. 
     * @param email email to identify which account to be updated with new deleteAccountcCode.
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
    public void updateStaffAccount(String password, String email, String firstName, 
            String lastName, String address, String city, String prov, String country, String postal, String area, boolean isEmployee, boolean isConfirmed)throws Exception{
                AccountDB accountDB = new AccountDB();
        try{
            Account account = accountDB.getAccountByEmail(email);
            Account tempAccount = account;
            //check if old password and new passwords are the same if so use old password and salt else creat new hash and salt
            PasswordServices pServ = new PasswordServices();
            String passwordHash = null;
            String passwordSalt = null;
            if (pServ.doesMatchHash(password, account.getPasswordSalt(), account.getPasswordHash())){
                passwordHash = account.getPasswordHash();
                passwordSalt = account.getPasswordSalt();
            }
            else{
                passwordSalt = pServ.getRandomSalt();
                passwordHash = pServ.generatePasswordHash(password, passwordSalt);
            }
            account = new Account(0, passwordHash, passwordSalt, email, firstName, lastName, isEmployee, isConfirmed);
            account.setAppointmentList(tempAccount.getAppointmentList());
            account.setEmployeeList(tempAccount.getEmployeeList());
            account.setPetList(tempAccount.getPetList());
            accountDB.updateAccount(account);
        }
        catch(Exception e){
            Logger.getLogger(AccountServices.class.getName()).log(Level.WARNING, null, e);
        }
    }
    
    //Removes an existing account
    public void deleteAccount(String email)throws Exception{
        AccountDB accountDB = new AccountDB();
        Account toDelete = accountDB.getAccountByEmail(email);
        
        accountDB.deleteAccount(toDelete);
    }
    
    
    //Checks login information to check if the password and email match the account/user logging in
    public Account checkCreds(String email, String password){
        Account account = null;
        try {
            AccountDB accDB = new AccountDB();
            List<Account> accounts = accDB.getAllAccount();
            PasswordServices pServ = new PasswordServices();
            for (int i = 0; i < accounts.size(); i++){
                if (accounts.get(i).getEmail().equals(email) && pServ.doesMatchHash(password, accounts.get(i).getPasswordSalt(), accounts.get(i).getPasswordHash())){
                    account = accounts.get(i);
                }
            }
        } catch(Exception e) {
            Logger.getLogger(AccountServices.class.getName()).log(Level.WARNING, null, e);
        }
        return account;
    } 
    
    /**
     * Returns the Employee model that is attached to the userID
     * @param userId
     * @return
     * @throws Exception 
     */
    public Employee getEmployeeByUserId(int userId)throws Exception{
        EmployeeDB empdb = new EmployeeDB();
        return empdb.getByUserId(userId);
    }
  
     /* Returns whether given email is associated with is an admin.
     * @param email the email to be used to query the DB.
     * @return true the email associated is an admin. Otherwise, false.
     * @throws Exception if somethign went wrong with querying the DB.
     */
    public boolean isEmployee(String email) throws Exception {
        AccountDB acDB = new AccountDB();
        EmployeeDB eDB = new EmployeeDB();
        
        Account acc = acDB.getAccountByEmail(email);
        
        
        if (acc != null) {
            // check if is admin
            return acc.getIsEmployee();
        }
        
        
        return false;
    }
    
    
    /**
     * Returns the employee account based on Account object.
     * @param email the email to get an Employee object.
     * @return the Employee object. Null if not found.
     */
    public Employee getEmployeeAccount(String email) throws Exception {
        try {
            if (!isEmployee(email)) return null;
        } catch (Exception ex) {
            Logger.getLogger(AccountServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        AccountDB aDB = new AccountDB();
        EmployeeDB eDB = new EmployeeDB();
        Account acc = aDB.getAccountByEmail(email);
        
        List<Employee> eList = eDB.getAllEmployees();
        
        
        for (Employee e : eList) {
            if (e.getUserID().getUserId() == acc.getUserId()) {
                return e;
            }
        }
        
        return null;
    }
    
    //Retreive account by email
    public Account getAccountEmail(String email)throws Exception{
        AccountDB accountDB = new AccountDB();
        Account account = new Account();
        account = accountDB.getAccountByEmail(email);
        return account;
    } 

    public List<Account> getEmployees() {
        List<Account> employees = null;
        try {
            AccountDB accntDB = new AccountDB();
            employees = accntDB.getAllIsEmployee();
        } catch(Exception e) {
            Logger.getLogger(AccountServices.class.getName()).log(Level.WARNING, null, e);
            System.out.println("couldn't get employee list");
        }
        return employees;
    }
    
    /**
     * Updates an Employee account in the DB.
     * @param e the Employee object to be updated.
     * @return returns true if successfully updated.
     * @throws Exception would be thrown if something went wrong.
     */
    public boolean updateEmployeeAccount(Employee e) throws Exception {
        EmployeeDB eDB = new EmployeeDB();
        return eDB.update(e);    
    }
}
