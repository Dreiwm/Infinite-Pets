/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.AccountDB;
import dataaccess.AppointmentDB;
import dataaccess.PetDB;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Appointment;
import models.Pet;
import java.util.List;
import models.Account;
import models.Appointment;


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
}
