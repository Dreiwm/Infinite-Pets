/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.EmpQualificationTypeDB;
import dataaccess.EmpServicePreferenceDB;
import dataaccess.ServiceTypeDB;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Empqualificationtype;
import models.EmpServicePreference;
import models.Employee;
import models.Servicetype;

/**
 * All services for Services and ServiceTypes. Also includes the
 * EmpServicePreferences.
 *
 * @author Riley
 */
public class ServiceServices {
    
    private final ServiceTypeDB sTDB;
    private final EmpServicePreferenceDB eSPDB;
    private final EmpQualificationTypeDB eQTDB;
    
    public ServiceServices() {
        sTDB = new ServiceTypeDB();
        eSPDB = new EmpServicePreferenceDB();
        eQTDB = new EmpQualificationTypeDB();
    }
    
    /**
     * Returns list of serviceTypes.
     *
     * @return returns list of ServiceTyoes. Returns null if either can't find
     * or an exception was thrown.
     */
    public List<Servicetype> getAllServiceTypes() {
        try {
            return sTDB.getAllServiceTypes();
        } catch (Exception ex) {
            Logger.getLogger(ServiceServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Servicetype> getAllServiceTypesSpecificToEmployee(Employee e) {
        List<Empqualificationtype> qList = eQTDB.getAllEmployeeQualificationTypes();
        ArrayList<Servicetype> qualifiedServiceTypeList = new ArrayList<>();

        // Get all qualified that belongs to given e Employee.
        
        qList = e.getEmpQualificationTypeList(); // IDK if this will work or not.
        for (EmpQualificationType eQT : qList) {
            System.out.println(eQT.getQualificationName());
        }
        // Now we need to loop thr the eList, remove if can't be found in eList.
        // Nested loop.
        
        
        return null;
    }

    /**
     * Returns the ServiceType with given ID.
     *
     * @param id id used to query the DB.
     * @return returns null if there was an exception or table is empty.
     * Otherwise returns list of ServiceTypes.
     */
    public Servicetype getServiceType(int id) {
        try {
            return sTDB.get(id);
        } catch (Exception ex) {
            Logger.getLogger(ServiceServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Inserts empServicePrefrence from DB.
     *
     * @param empSP the EmpServicePreference to be inserted into.
     * @return returns true if successfully inserted into DB.
     */
    public boolean insertEmpServicePreference(EmpServicePreference empSP) throws Exception {
        EmpServicePreferenceDB empSPDB = new EmpServicePreferenceDB();
                    return empSPDB.insert(empSP);
    }

    /**
     * Deletes the EmpServicePreference from DB.
     * @param id the id of EmpServicePreference to be deleted.
     * @return true if successfully deleted.
     */
    public boolean deleteEmpServicePreference(int id) {        
        try {
            eSPDB.delete(eSPDB.get(id));
        } catch (Exception ex) {
            Logger.getLogger(ServiceServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    /**
     * Returns all of the EmpServicePreference objects from EmpServicePreference table.
     * @return list of all EmpServicePreference.
     */
    public List<EmpServicePreference> getAllEmpServicePreferences() {
        return eSPDB.getAllEmpServicePreferences();
    }
    
    /**
     * Returns all of EmpServicePreferences from empServicePreference that belongs to an employee.
     * @param e the Employee object which is used for matching.
     * @return the list that belongs to given Employee.
     */
    public List<EmpServicePreference> getAllEmpServicePreferencesBelongToEmp(Employee e) {
        List<EmpServicePreference> list = getAllEmpServicePreferences();

        for (EmpServicePreference esp : list) {
            if (!esp.getEmployeeID().equals(e)) {
                list.remove(esp);
            }
        }

        return list;

    }
}
