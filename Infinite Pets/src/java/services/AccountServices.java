/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AccountDB;
import dataaccess.PassUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Account;

/**
 *
 * @author BTran
 */
public class AccountServices {

    //check if the email already exists in the database
    public boolean checkEmail(String email) {
        boolean checked = false;
        try {
            AccountDB accDB = new AccountDB();
            List<Account> accounts = accDB.getAllAccount();
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getEmail().equals("email"))
                    checked = true;
            }
        } catch (Exception e) {
            Logger.getLogger(AccountServices.class.getName()).log(Level.WARNING, null, e);
        }
        return checked; 
    }

    //passes infor to a check moethod before creating aa new account
    public ArrayList<String> insert(String firstName, String lastName, String email, String emailConf, String password, String passwordConf, String address, String postalCode) {
        ArrayList<String> errorMsgs = new ArrayList();
        errorMsgs.add("hi");
        return errorMsgs;
    }

    public Account login(String email, String password) {
       AccountDB acctDB = new AccountDB();
        
        try {
            if (checkEmail(email)){
                Account userAccount = acctDB.getAccountByEmail(email);
//                //TODO: Should be used to get salted information
//                String hashed = PassUtil.hashAndSaltPassword(password, userAccount.getSalt());
//                if (hashed.equals(userAccount.getPassword()))
//                    return userAccount;
                if (password.equals(userAccount.getPassword()))
                    return userAccount;
            }
            else
                return null;
            
        } catch (Exception e) {
            Logger.getLogger(AccountServices.class.getName()).log(Level.SEVERE, null, e);
        }        
        return null;
    }
}
