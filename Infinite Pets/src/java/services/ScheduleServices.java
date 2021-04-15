/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AccountDB;
import dataaccess.AppointmentDB;
import dataaccess.AppointmentServiceDB;
import dataaccess.PetDB;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Appointment;
import models.Pet;
import java.util.List;
import models.Account;
import models.Appointment;
import models.Appointmentservice;
import models.Empservicepreference;
import models.Employee;
import models.Service;
import models.Appointmentservice;
import services.exceptions.AppointmentException;

/**
 *
 * @author BTran
 */
public class ScheduleServices {

    public final static String EARLY_MORNING = "Early Morning (6AM to 9AM)";
    public final static String MORNING = "Morning (9AM to 12AM";
    public final static String AFTERNOON = "Afternoon (12PM to 4PM)";
    public final static String EVENING = "Evening (4PM to 6PM)";

    // Time corrspends to schedule block.
    public final static int EARLY_MORNING_TIME_START = 6;
    public final static int MORNING_TIME_START = 9;
    public final static int AFTERNOON_TIME_START = 12;
    public final static int EVENING_TIME_START = 4;

//    //Retrieves a list of ScheduleBlockTypes
//    public List<ScheduleBlockType> getScheduleBlockTypes() throws Exception{
//        AppointmentDB apDB = new AppointmentDB();
//        return apDB.getScheduleBlockTypes();
//    }
//    
//    //Retrieves a list of available ScheduleBlocks
//    public List<ScheduleBlock> getScheduleBlocks() throws Exception{
//        AppointmentDB apDB = new AppointmentDB();
//        return apDB.getScheduleBlocks();
//    }
    //Retrieves a list of appointments the logged in user has
    public List<Appointment> getAllAppointments(int userID) throws Exception {
        AppointmentDB apDB = new AppointmentDB();
        return apDB.getAllAppointments();
    }

    //Retrieves a list of apppointments a specific pet has
    public List<Appointment> getAllAppointmentsByUserId(int userID) {
        AppointmentDB apDB = new AppointmentDB();
        return apDB.getAllAppointmentsByUserId(userID);
    }

    //Retrieves a list of apppointments a specific pet has
    public Appointment getAppointmentById(int appointmentID) {
        AppointmentDB apDB = new AppointmentDB();
        return apDB.getAppointmentById(appointmentID);
    }

    //checked to see fi the current pet is owned by the user current logged in
    /**
     * *****NOTE - might have to set an admin bypass for them to book an
     * appointment for the pet*********
     */
    private boolean verifyInfo(int petID, int userID) {
        boolean checked = false;
        try {
            PetDB petDB = new PetDB();
            Pet pet = petDB.getItemById(petID);
            if (pet.getOwner().getUserId() == userID) {
                checked = true;
            }
        } catch (Exception e) {
            Logger.getLogger(ScheduleServices.class.getName()).log(Level.WARNING, null, e);
        }
        return checked;
    }

    private boolean verifyOwner(int petID, int userID) {
        boolean checked = false;
        PetDB petdb = new PetDB();
        try {
            if (petdb.getItemById(petID).getOwner().getUserId() == userID) {
                checked = true;
            }
        } catch (Exception e) {
            Logger.getLogger(ScheduleServices.class.getName()).log(Level.WARNING, null, e);
        }
        return checked;
    }

    //passes information to get checked before creating an appointment and adding it to the database
    public void createAppointment(Date appointmentDate, Time time, Account user, List<Appointmentservice> contents) {
        try {
            AppointmentDB apDB = new AppointmentDB();
            AppointmentServiceDB aservDB = new AppointmentServiceDB();
            boolean check = false;
            
            for(Appointmentservice as: contents){
                check = verifyInfo(as.getPetID().getPetID(),user.getUserId());
            }
            System.out.println("checking pets");
            if (check==true) {
                //make an new appointment and then add the AppointmentService list to it
                Appointment appointment = new Appointment(0, appointmentDate, false, false, false);
               // appointment.setAppointmentTime(appointmentTime);
                appointment.setClientID(user);
                appointment.setAppointmentTime(time);
                System.out.println("inserting appointemnt");
                apDB.insert(appointment);
                for(Appointmentservice ser: contents){
                    ser.setAppointmentID(appointment);
                    aservDB.insert(ser);
                }
                appointment.setAppointmentserviceList(contents);
                
                
                
                System.out.println("appointemtn inserted");
            }
        } catch (Exception e) {
            Logger.getLogger(ScheduleServices.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //passes information to get checked before updating an appointment and adding it to the database
    public void updateAppointment(int appointmentID, Date appointmentDate, boolean confirmed, boolean paid, boolean active, int petID, int userID) {
        try {
            AppointmentDB apDB = new AppointmentDB();
            if (verifyInfo(petID, userID) && verifyOwner(petID, userID)) {
                Appointment appointment = apDB.getAppointmentById(appointmentID);
               
                appointment.setAppointmentDate(appointmentDate);
                appointment.setConfirmed(confirmed);
                appointment.setPaid(paid);
                appointment.setActive(active);
                Pet pet = new PetDB().getItemById(petID);
                Appointmentservice aps = new Appointmentservice(); //this should be the pet id and not the who pet object i think...
/******************NEEDS MORE LOGIC FOR PROPERLY PERSISTING EACH PET ON THE APPOINTMENT************************************/
                aps.setPetID(pet);
                aps.setAppointmentID(appointment);
                appointment.setClientID(pet.getOwner());
                apDB.update(appointment);
                //persist to the Pets appointment List
                //PetDB petDB = new PetDB();
//                Pet pet = petDB.getItemById(petID);
//                pet.getAppointments.add(appointment);
            }
        } catch (Exception e) {
            Logger.getLogger(ScheduleServices.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Cancels the appointment - removing from DB. Returns the cancelled
     * Appointment.
     *
     * @param apptId the id of Appointment to be removed from DB.
     * @return Appointment the object which was removed from DB.
     * @throws AppointmentException if client attempted to cancel appointment
     * the appointment is within 24 hours.
     * @throws NullPointerException if appt is null.
     */
    public Appointment cancelAppointment(int apptId) throws AppointmentException, NullPointerException {
        AppointmentDB apDB = new AppointmentDB();

        // get appointment from db
        Appointment appt = apDB.getAppointmentById(apptId);
        if (appt == null) {
            throw new NullPointerException();
        }

        // get today date
        Calendar calToday = Calendar.getInstance();
        calToday.setTime(new Date());

        Calendar calApptDate = Calendar.getInstance();
        calApptDate.setTime(appt.getAppointmentDate());

        // add appt one day and then compare, if appt is still before today, then
        // it is 24 hours before.
        calApptDate.add(Calendar.DAY_OF_MONTH, 1);

        // throw exception if it is after today date -- after adding one day.
        if (calApptDate.compareTo(calToday) > 0) {
            throw new AppointmentException("Appointment can not be cancelled within 24 hours of appointment date.");
        }

        if (appt != null) {
            if (apDB.delete(appt)) // return appt if actually removed.
            {
                return appt;
            }

        }

        return null;
    }

    public static List<String> getScheduleBlockList() {
        ArrayList<String> list = new ArrayList<>();

        list.add(EARLY_MORNING);
        list.add(MORNING);
        list.add(AFTERNOON);
        list.add(EVENING);

        return (List) list;
    }

    /**
     * Returns a string representing a schedule block - Early Morning, Morning,
     * Afternoon, and on. Intended to be used with select and option HTML
     * elements. Determined by start appointment date, ie. 06:00 is Early
     * Morning.
     *
     * @param appt Appointment object to be used to determine the schedule
     * block.
     * @return a string representing a schedule block
     */
    public static String getScheduleBlock(Appointment appt) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh");
        int hour = Integer.parseInt(sdf.format(appt.getAppointmentTime()));
//        System.out.println("hour: " + hour);

        // case switch 
        switch (hour) {
            case EARLY_MORNING_TIME_START:
                return EARLY_MORNING;
            case MORNING_TIME_START:
                return MORNING;
            case AFTERNOON_TIME_START:
                return AFTERNOON;
            case EVENING_TIME_START:
                return EVENING;
            default:
                return "Unrecognized schedule block";
        }
    }

    /**
     * Returns an integer representing hour that corrsepends to a given schedule
     * block.
     *
     * @param scheduleBlock a string representing a fully named schedule block
     * (ie. Afternoon (12pm to 4pm).
     * @return an integer representing a hour that corrspends to a given
     * schedule block. Returns -1 if no match is found.
     */
    public static int getScheduleBlock(String scheduleBlock) {
        switch (scheduleBlock) {
            case EARLY_MORNING:
                return EARLY_MORNING_TIME_START;
            case MORNING:
                return MORNING_TIME_START;
            case AFTERNOON:
                return AFTERNOON_TIME_START;
            case EVENING:
                return EVENING_TIME_START;
            default:
                return -1;
        }
    }

    /**
     * Returns an integer representing start time of given Appointment object.
     * used with schedule blocks.
     *
     * @param appt the Appointment object to determine start time.
     * @return an integer representing start time of an appointment.
     */
    public static int getScheduleBLockStartTime(Appointment appt) {
        if (appt == null || appt.getAppointmentDate() == null) {
            return -1;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("hh");
        return Integer.parseInt(sdf.format(appt.getAppointmentDate()));
    }

    /**
     * Returns all available appointments - unconfirmed appointments.
     *
     * @return returns list of available appointments.
     */
    public List<Appointment> getAllAvailableAppointments() throws ParseException {
        AppointmentDB apDB = new AppointmentDB();
        // get all appointments 

        List<Appointment> allAppts = apDB.getAllAppointments();

        // Only proceed if not null
        if (allAppts != null) {
            ArrayList<Appointment> unConfirmedAppts = new ArrayList<>();

            for (Appointment appt : allAppts) {
                if (!appt.getConfirmed()) {
                    unConfirmedAppts.add(appt);
                }          
            }
             return (List) unConfirmedAppts;
        }
        System.out.println("returning null");
        // and then filter
        return null;
    }

    /**
     * Returns all available appointments (unconfirmed) filtered by
     * EmpServicePreference.
     *
     * @param e the Employee to used to filter.
     * @return the list of the available appointments selected by employee.
     */
    public List<Appointment> getAllAvailableAppointmentsByPreferences(Employee e) throws ParseException {

        // Get EmpServicePreference list
        ServiceServices ss = new ServiceServices();
        List<Empservicepreference> empSPList = ss.getAllEmpServicePreferencesBelongToEmp(e);
        List<Appointment> allAppts = getAllAvailableAppointments();
        
        ArrayList<Appointment> filteredAppointments = new ArrayList<>();
        
        if (empSPList != null) {
            // we need to loop through appointment, then loop through appointmentServices.
            // You msut be certain that all services is preferred, not one of them. 
            // Break out loop immeidately if found one of them not match.
            List<Appointmentservice> apptServices = null;

            for (Appointment appt : allAppts) {
                System.out.println("appt id: " + appt.getAppointmentID());
                boolean allQualified = true;
                apptServices = getAllAppointmentServices(appt);
                for (Appointmentservice apptService : apptServices) {
                    System.out.println("AppointmentService id: " + apptService.getAppServID() + "with serviceTypeID: " + apptService.getServiceID().getServiceTypeID().getServiceType());
                    if (!isAppointmentServiceInWorkPrefence(apptService, empSPList)) {
                        allQualified = false;
                        System.out.println("Unmatch found for: " + apptService.getAppointmentID().getAppointmentID());
                        break;
                    }
                }
                if (allQualified) {
                    System.out.println("all qualified, adding appt id" + appt.getAppointmentID());
                    filteredAppointments.add(appt);
                }
                    
            }
            filteredAppointments.forEach(appointment -> {
                System.out.println(appointment.getAppointmentID());
            });
            return (List) filteredAppointments;
        }
        System.out.println("empSPList is null");
        return null;

    }

    /**
     * Returns true if serviceType found in AppointmentService matches. If
     * cannot be found (ie. no match), return false. Else return true.
     *
     * @param empSPList the EmpServicePreference list as a reference for
     * matching.
     * @param apptS AppointmentService used to match with EmpServicePreference
     * @return
     */
    private boolean isAppointmentServiceInWorkPrefence(Appointmentservice appS, List<Empservicepreference> empSPList) {
        // looping joys.

        for (Empservicepreference empSP : empSPList) {
            if (appS.getServiceID().getServiceTypeID().getServiceTypeID() == empSP.getServiceTypeID().getServiceTypeID()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns all AppointmentService objects with given Appointment object.
     *
     * @param apptId to use with.
     * @return returns list of AppointmentServices with given appointment
     * object.
     */
    public List<Appointmentservice> getAllAppointmentServices(Appointment appt) {
        AppointmentServiceDB aptSDB = new AppointmentServiceDB();

        List<Appointmentservice> allApptServices = aptSDB.getAllAppointmentServices();

        ArrayList<Appointmentservice> apptServices = new ArrayList<>();

        if (allApptServices != null) {
                for (Appointmentservice apptService : allApptServices) {
                    if (appt.getAppointmentID() == apptService.getAppointmentID().getAppointmentID()) {
                        apptServices.add(apptService);
                        System.out.println("adding appt from  appt services with id: " + appt.getAppointmentID());
                    }
                }
        }
        return (List) apptServices;
    }

    public boolean updateAppointment(Appointment appt) {
        AppointmentDB apDB = new AppointmentDB();
        return apDB.update(appt);
    }
}
