/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import models.ServiceType;

/**
 * Responsible for interacting with ServiceType table in the database
 * @author Riley
 */
public class ServiceTypeDB {
    /**
     * Inserts the ServiceType into the database.
     * @param serviceType ServiceType to be inserted into the database
     * @return returns true if successfully inserted.
     * @throws Exception if something went wrong in process of inserting into the database.
     */
    public boolean insert(ServiceType serviceType) throws Exception {
        return false;
    }
    
    /**
     * Updates the given ServiceType on the ServiceType table.
     * @param serviceType the ServiceType to be updated.
     * @return returns true if successfully updated.
     * @throws Exception if something went wrong in process of updating object in the database.
     */
    public boolean update(ServiceType serviceType) throws Exception {
        return false;
    }
    
    /**
     * Deletes the ServiceType from the database.
     * @param serviceType the object to be deleted from the database.
     * @return if successfully deleted from the database.
     * @throws Exception  if something went with accessing the database.
     */
    public boolean delete(ServiceType serviceType) throws Exception {
        return false;
    }
    
    /**
     * Returns the ServiceType from the database with given ID.
     * @param id id to be used to access information about ServiceType
     * @return returns ServiceType if found on the database. 
     * @throws Exception if something went wrong with process of accessing database.
     */
    public ServiceType getServiceType(int id) throws Exception {
        return null;
    }
    
    /**
     * Returns all ServiceType.
     * @return List of the ServiceType.
     * @throws Exception if something went wrong with process of accessing the database.
     */
    public List<ServiceType> getAllServiceTypes() throws Exception {
        return null;
    }
}
