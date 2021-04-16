/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.ServiceDB;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Service;

/**
 *
 * @author BTran
 */
public class AdminPetServices {
    
    public void createService(String name, double price, String des, boolean active){
        try {
            ServiceDB serviceDB = new ServiceDB();
            if (checkServiceInfo(name, price, des)){
                Service service = new Service(0);
                service.setServiceName(name);
                service.setBasePrice(new BigDecimal(price, MathContext.DECIMAL64));
                service.setServiceDescription(des);
                service.setActive(active);
                ServiceDB sDB = new ServiceDB();
                sDB.insert(service);
            }
        } catch (Exception e) {
            Logger.getLogger(PetServicesServices.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public boolean deleteService(Service service) {
        boolean removed = false;
        try {
            ServiceDB sDB = new ServiceDB();
            sDB.delete(service);
            removed = true;
        }catch(Exception e){
            
        }
        return removed;
    }
    
    public boolean updateService(Service oldService, String name, double price, String des, boolean active){
        boolean updated = false;
        try{
            ServiceDB sDB = new ServiceDB();
            Service newService = new Service(oldService.getServiceID());
            Service service = new Service(0);
            service.setServiceName(name);
            service.setBasePrice(new BigDecimal(price, MathContext.DECIMAL64));
            service.setServiceDescription(des);
            service.setActive(active);
            sDB.update(service);
        }catch(Exception e){
            
        }
        return updated;
    }
    
    //validates input before creating service
    public boolean checkServiceInfo(String name, double price, String des){
        boolean checked = true;
        
        //Check the input for ilegal values
        if(name == null || name == "")
            checked = false;
        if(price == 0.0 || price < 0.01 )
            checked = false;
        if(des == null || des =="")
            checked = false;
        
        return checked;
    }
    
}
