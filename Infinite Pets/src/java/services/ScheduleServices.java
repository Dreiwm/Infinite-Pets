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
    
    public final static String earlyMorning = "Early Morning (6AM to 9AM)";
    public final static String morning = "Morning (9AM to 12AM";
    public final static String afternoon = "Afternoon (12PM to 4PM)";
    public final static String evening = "Evening (4PM to 6PM)";
    
    
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
        
        list.add(earlyMorning);
        list.add(morning);
        list.add(afternoon);
        list.add(evening);
        
        
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
        
        final int earlyMorning = 6;
        final int morning = 9;
        final int afternoon = 12;
        final int evening = 4; // luckily, they don't work in 4am, else I'll have to use strings 
        
        // case switch 
        switch (hour) {
            case earlyMorning:
                return "Early Morning (6AM to 9AM)";
            case morning:
                return "Morning (9AM to 12PM)";
            case afternoon:
                return "Afternoon (12PM to 4PM)";
            case evening: 
                return "Evening (4PM to 6PM)";
            default:
                return "Unrecognized schedule block";
        }  
    }
}
