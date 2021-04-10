/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.EmpServicePreferenceDB;
import dataaccess.ServiceTypeDB;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.EmpServicePreference;
import models.ServiceType;

/**
 * All services for Services and ServiceTypes.
 * Also includes the EmpServicePreferences.
 * @author Riley
 */
public class ServiceServices {
    
    /**
     * Returns list of serviceTypes. 
     * @return returns list of ServiceTyoes. Returns null if either can't find or an exception was thrown.
     */
    public List<ServiceType> getAllServiceTypes() {
        ServiceTypeDB sTDB = new ServiceTypeDB();
        
        try {
            return sTDB.getAllServiceTypes();
        } catch (Exception ex) {
            Logger.getLogger(ServiceServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    /**
     * Returns the ServiceType with given ID.
     * @param id id used to queery the DB.
     * @return returns null if there was an exception or table is empty. Otherwise returns lsit of ServiceTypes.
     */
    public ServiceType getServiceType(int id) {
        ServiceTypeDB sTDB = new ServiceTypeDB();
        
        try {
            return sTDB.get(id);
        } catch (Exception ex) {
            Logger.getLogger(ServiceServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    } 
    
    /**
     * Removes empServicePrefrence from DB.
     * @param empSP
     * @return 
     */
    public boolean insertEmpServicePreference(EmpServicePreference empSP) {
        EmpServicePreferenceDB empSPDB = new EmpServicePreferenceDB();
        try {
            return empSPDB.insert(empSP);
        } catch (Exception ex) {
            Logger.getLogger(ServiceServices.class.getName()).log(Level.SEVERE, null, ex);
           return false; 
        }
    }
    
    public boolean deleteEmpServicePreference(int id) {
        EmpServicePreferenceDB empSPDB = new EmpServicePreferenceDB();
        try {
            empSPDB.delete(empSPDB.get(id));
        } catch (Exception ex) {
            Logger.getLogger(ServiceServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
    
}
