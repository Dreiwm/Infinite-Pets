/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.ServiceDB;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Service;

/**
 *
 * @author BTran
 */
public class AdminPetServices {
    
    public void createService(){
        try {
            ServiceDB serviceDB = new ServiceDB();
            if (checkServiceInfo()){
                Service service = new Service();
            }
        } catch (Exception e) {
            Logger.getLogger(PetServicesServices.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public boolean deleteService(String serviceID) {
        boolean removed = false;
        try {
            
        }catch(Exception e){
            
        }
        return removed;
    }
    
    
    //validates input before creating service
    private boolean checkServiceInfo(){
        boolean checked = false;
        return checked;
    }
    
}
