/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.ServiceDB;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import models.Service;

/**
 *
 * @author BTran
 */
public class PetServicesServices {
    //Retrieves a list of all the services
    public List<Service> getAllServices() throws Exception {
        ServiceDB serviceDB = new ServiceDB();
        return serviceDB.getAllServices();
    }
    
    //Retrieves a specific Service requested
    public Service getService(int serviceID) {
        ServiceDB serviceDB = new ServiceDB();
        return serviceDB.getServiceById(serviceID);
    }
    
//    //Retrieves a list of different ServiceTypes
//    public List<ServiceType> getAllServiceTypes() {
//        ServiceDB serviceDB = new ServiceDB();
//        return apDB.getAllServiceTypes();
//    }
    
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
    
    private boolean checkServiceInfo(){
        boolean checked = false;
        return checked;
    }
    
}
