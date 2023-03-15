/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.ServiceDB;
import dataaccess.ServiceTypeDB;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import models.Service;
import models.Servicetype;

/**
 *
 * @author BTran
 */
public class PetServicesServices {
    //Retrieves a list of all the services
    public List<Service> getAllServices() throws Exception {
        ServiceDB serviceDB = new ServiceDB();
        System.out.println("Getting all services in database");
        return serviceDB.getAllServices();
    }
    
    //Retrieves a specific Service requested
    public Service getService(int serviceID) {
        ServiceDB serviceDB = new ServiceDB();
        return serviceDB.getServiceById(serviceID);
    }
    
    //Retrieves a specific Service requested
    public Service getServiceByName(String serviceName) {
        ServiceDB serviceDB = new ServiceDB();
        return serviceDB.getServiceByName(serviceName);
    }

    //Retrieves list of all service types
    public List<Servicetype> getAllServiceTypes() throws Exception {
        ServiceTypeDB serviceTypeDB = new ServiceTypeDB();
        return serviceTypeDB.getAllServiceTypes();
    }

    public void update(int serviceID, String serviceName, BigDecimal basePrice, String description) throws Exception {
        ServiceDB servDB = new ServiceDB();
        Service tempServ = servDB.getServiceById(serviceID);
        Service newService = new Service(tempServ.getServiceID(), serviceName, basePrice, true);
        newService.setServiceDescription(description);
        newService.setServiceTypeID(tempServ.getServiceTypeID());
        servDB.update(newService);
    }

    public void addService(String serviceName, BigDecimal basePrice, String description) throws Exception {
        Service newService = new Service(0, serviceName, basePrice, true);
        ServiceDB servDB = new ServiceDB();
        ServiceTypeDB servTDB = new ServiceTypeDB();
        Servicetype st = servTDB.get(3);
        newService.setServiceTypeID(st);
        newService.setServiceDescription(description);
        servDB.insert(newService);
    }
}
