/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.EmpServicePreferenceDB;
import dataaccess.ServiceTypeDB;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.EmpQualificationType;
import models.EmpServicePreference;
import models.Employee;
import models.ServiceType;

/**
 * All services for Services and ServiceTypes. Also includes the
 * EmpServicePreferences.
 *
 * @author Riley
 */
public class ServiceServices {

    /**
     * Returns list of serviceTypes.
     *
     * @return returns list of ServiceTyoes. Returns null if either can't find
     * or an exception was thrown.
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
     *
     * @param id id used to queery the DB.
     * @return returns null if there was an exception or table is empty.
     * Otherwise returns lsit of ServiceTypes.
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
     *
     * @param empSP
     * @return
     */
    public boolean insertEmpServicePreference(EmpServicePreference empSP) throws Exception {
        EmpServicePreferenceDB empSPDB = new EmpServicePreferenceDB();
                    return empSPDB.insert(empSP);
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

    public List<EmpServicePreference> getAllEmpServicePreferences() {
        EmpServicePreferenceDB eDB = new EmpServicePreferenceDB();
        return eDB.getAllEmpServicePreferences();
    }

    public List<EmpServicePreference> getAllEmpServicePreferencesBelongToEmp(Employee e) {
        List<EmpServicePreference> list = getAllEmpServicePreferences();

        for (EmpServicePreference esp : list) {
            if (!esp.getEmployeeID().equals(e)) {
                list.remove(esp);
            }
        }

        return list;

    }
    
//    public List<ServiceType> getAllServiceTypesForEmployeeSpecific(Employee e) {
//        List<ServiceType> list = getAllServiceTypes();
//        
//        // Get qualified list
//        List<EmpQualificationType> qualifiedForList = e.getEmpQualificationTypeList();
//        
//        for (ServiceType st : list) {
//            //
//        }
//    }
}
