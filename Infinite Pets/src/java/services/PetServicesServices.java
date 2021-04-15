/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.ServiceDB;
import dataaccess.ServiceTypeDB;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import models.Service;
import models.ServiceType;

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
    
    //Retrieves a specific Service requested
    public Service getService(String serviceName) {
        ServiceDB serviceDB = new ServiceDB();
        return serviceDB.getServiceByName(serviceName);
    }

    //Retrieves list of all service types
    public List<ServiceType> getAllServiceTypes() throws Exception {
        ServiceTypeDB serviceTypeDB = new ServiceTypeDB();
        return serviceTypeDB.getAllServiceTypes();
    }
}
