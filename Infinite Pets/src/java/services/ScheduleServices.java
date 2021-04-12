/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AccountDB;
import dataaccess.AppointmentDB;
import dataaccess.PetDB;
import java.math.BigDecimal;
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
import models.Service;
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
    public List<Appointment> getAllAppointments(int userID) throws Exception{
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
    /*******NOTE -  might have to set an admin bypass for them to book an appointment for the pet**********/
    private boolean verifyInfo(int appointmentID, Date appointmentDate, boolean confirmed, boolean paid, boolean active, int petID, int userID) {
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
            if (petdb.getItemById(petID).getOwner().getUserId() == userID){
                checked = true;
            }
        } catch (Exception e) {
            Logger.getLogger(ScheduleServices.class.getName()).log(Level.WARNING, null, e);
        }
        return checked;
    }
    
    
    //passes information to get checked before creating an appointment and adding it to the database
    public void createAppointment(int appointmentID, Date appointmentDate, boolean confirmed, boolean paid, boolean active, int petID, int userID) {
        try {
            AppointmentDB apDB = new AppointmentDB();
            if (verifyInfo(appointmentID, appointmentDate, confirmed, paid, active, petID, userID)) {
                Appointment appointment = new Appointment(appointmentID, appointmentDate, confirmed, paid, active);
                apDB.insert(appointment);
                //persist to the Pets appointment List
                PetDB petDB = new PetDB();
//                Pet pet = petDB.getItemById(petID);
//                pet.getAppointments.add(appointment);
            }
        } catch (Exception e) {
            Logger.getLogger(ScheduleServices.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
     //passes information to get checked before updating an appointment and adding it to the database
    public void updateAppointment(int appointmentID, Date appointmentDate, boolean confirmed, boolean paid, boolean active, int petID, int userID) {
        try {
            AppointmentDB apDB = new AppointmentDB();
            if (verifyInfo(appointmentID, appointmentDate, confirmed, paid, active, petID, userID) && verifyOwner(petID, userID)) {
                Appointment appointment = apDB.getAppointmentById(appointmentID);
                appointment.setAppointmentDate(appointmentDate);
                appointment.setConfirmed(confirmed);
                appointment.setPaid(paid);
                appointment.setActive(active);
                Pet pet = new PetDB().getItemById(petID);
                appointment.setPetID(pet); //this should be the pet id and not the who pet object i think...
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
     * Cancels the appointment - removing from DB. Returns the cancelled Appointment.
     * @param apptId the id of Appointment to be removed from DB.
     * @return Appointment the object which was removed from DB.
     * @throws AppointmentException if client attempted to cancel appointment the appointment is within 24 hours.
     * @throws NullPointerException if appt is null.
     */
    public Appointment cancelAppointment(int apptId) throws AppointmentException, NullPointerException {        
        AppointmentDB apDB = new AppointmentDB();
        
        // get appointment from db
        Appointment appt = apDB.getAppointmentById(apptId);
        if (appt == null) throw new NullPointerException();
        
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
                return appt;
            
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
     * Returns a string representing a schedule block - Early Morning, Morning, Afternoon, and on.
     * Intended to be used with select and option HTML elements.
     * Determined by start appointment date, ie. 06:00 is Early Morning.
     * @param appt Appointment object to be used to determine the schedule block.
     * @return a string representing a schedule block
     */
    public static String getScheduleBlock(Appointment appt) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh");
        int hour = Integer.parseInt(sdf.format(appt.getAppointmentDate()));
        System.out.println("hour: " + hour);
        
        
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
     * Returns an integer representing hour that corrsepends to a given schedule block.
     * @param scheduleBlock a string representing a fully named schedule block (ie. Afternoon (12pm to 4pm).
     * @return an integer representing a hour that corrspends to a given schedule block. Returns -1 if no match is found.
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
     * @param appt the Appointment object to determine start time.
     * @return an integer representing start time of an appointment.
     */
    public static int getScheduleBLockStartTime(Appointment appt) {
        if (appt == null || appt.getAppointmentDate() == null) return -1;
        
        SimpleDateFormat sdf = new SimpleDateFormat("hh");
        return Integer.parseInt(sdf.format(appt.getAppointmentDate()));
    }
    
    /**
     * Returns all available appointments - unconfirmed appointments.
     * @return returns list of available appointments.
     */
    public List<Appointment> getAllAvailableAppointments() throws ParseException {
        AppointmentDB apptDB = new AppointmentDB();
//        List<Appointment> allAppts = apptDB.getAllAppointments();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
        sdf.setLenient(false);
        ArrayList<Appointment> allAppts = new ArrayList<>();
        allAppts.add(new Appointment(1, sdf.parse("2021-04-11-09"), true, false, false));
        allAppts.get(0).setServiceID(new Service(1, "test", new BigDecimal(12.0), true, true, true));
        allAppts.add(new Appointment(2, sdf.parse("2021-04-11-06"), true, false, false));
        allAppts.get(1).setServiceID(new Service(1, "test", new BigDecimal(12.0), true, true, true));
        allAppts.add(new Appointment(3, sdf.parse("2021-04-11-12"), false, false, false));
        allAppts.get(2).setServiceID(new Service(1, "test", new BigDecimal(12.0), true, true, true));
        ArrayList<Appointment> unConfirmedAppts = new ArrayList<>();
        // now filter out all confirmed appointments
        allAppts.forEach(appt -> {
            if (!appt.getConfirmed()) {
                unConfirmedAppts.add(appt);
            }
        });
        
        return (List) unConfirmedAppts;
    }
}
