/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AppointmentServiceDB;
import dataaccess.EmpQualificationDB;
import dataaccess.EmpServicePreferenceDB;
import dataaccess.ServiceDB;
import dataaccess.ServiceTypeDB;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Appointment;
import models.Appointmentservice;
import models.Empqualification;
import models.Empservicepreference;
import models.Employee;
import models.Service;
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
    private final EmpQualificationDB eQTDB;
    private final ServiceDB sDB;
    private final AppointmentServiceDB asDB;

    
    public ServiceServices() {
        sTDB = new ServiceTypeDB();
        eSPDB = new EmpServicePreferenceDB();
        eQTDB = new EmpQualificationDB();
        sDB = new ServiceDB();
        asDB = new AppointmentServiceDB();
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

    /**
     * 
     * @param e is the employee getting the services types
     * @return a list of service types
     */
    public List<Servicetype> getAllServiceTypesSpecificToEmployee(Employee e) {
//        List<Empqualification> qList = eQTDB.getAllEmployeeQualifications(e.getEmployeeID());
        //ArrayList<Servicetype> qualifiedServiceTypeList = new ArrayList<>();

        // Get all qualified that belongs to given e Employee.
       List<Service> qList = e.getServiceList(); // IDK if this will work or not.
//        for (Service eQT : qList) {
//            System.out.println(eQT.getServiceName());
//        }
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
     * 
     * @param empSP the EmpServicePreference to be inserted into.
     * @return returns true if successfully inserted into DB.
     * @throws Exception for null pointers
     */
    public boolean insertEmpServicePreference(Empservicepreference empSP) throws Exception {
        EmpServicePreferenceDB empSPDB = new EmpServicePreferenceDB();
        return empSPDB.insert(empSP);
    }

    /**
     * Deletes the EmpServicePreference from DB.
     *
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
     * Returns all of the EmpServicePreference objects from EmpServicePreference
     * table.
     *
     * @return list of all EmpServicePreference.
     */
    public List<Empservicepreference> getAllEmpServicePreferences() {
        return eSPDB.getAllEmpServicePreferences();
    }

    /**
     * Returns all of EmpServicePreferences from empServicePreference that
     * belongs to an employee.
     *
     * @param e the Employee object which is used for matching.
     * @return the list that belongs to given Employee.
     */
    public List<Empservicepreference> getAllEmpServicePreferencesBelongToEmp(Employee e) {
        List<Empservicepreference> list = getAllEmpServicePreferences();
        for (Empservicepreference esp : list) {
            if (!esp.getEmployeeID().equals(e)) {
                list.remove(esp);
            }
        }
        return list;
    }

    /**
     * Returns all services, regardless of service types.
     *
     * @return list of services. If none found, null.
     */
    public List<Service> getAllServices() {
        ServiceDB sDB = new ServiceDB();
//        sDB.getAllServices().forEach(item -> {
//            System.out.println(item.getServiceName());
//        });
        return sDB.getAllServices();
    }

    /**
     * Returns all services with given service type ID.
     *
     * @param selectedServiceTypeId the id of service type used to return all
     * services.
     * @return the list of services with given service type id.
     */
    public List<Service> getAllServicesByServiceTypeID(int selectedServiceTypeId) {
        ArrayList<Service> services = new ArrayList<>();

        // get all services
        List<Service> allServices = getAllServices();

        for (Service s : allServices) {
            if (s.getServiceTypeID().getServiceTypeID() == selectedServiceTypeId) {
                services.add(s);
            }
        }
//        services.forEach(service -> {
//            System.out.println(service.getServiceName());
//        });
        return (List) services;

    }

    /**
     
     */
    /**
     * 
     * @param serviceId the service id sued to query on the database.
     * @return a service using given service id
     */
    public Service getServiceById(int serviceId) {
        return sDB.getServiceById(serviceId);
    }

    /**
     * Inserts an AppointmentServive into DB.
     * @param apptService the AppointmentService to be isnerted into.
     * @return true if successfully inserted into.
     */
    public boolean insertAppointmentService(Appointmentservice apptService) {
        return asDB.insert(apptService);
    }
    
    /**
     * 
     * @param apptServiceId is the current selected service to delete
     * @return a boolean if the service was deleted
     */
    public boolean deleteAppointmentService(int apptServiceId) {
        // et appointment service
        Appointmentservice apptService = asDB.getAppointmentById(apptServiceId);
//        System.out.println("apptService null?" + apptService == null);
        if (apptService == null) return false;
        return asDB.delete(apptService);
    } 
    
}
