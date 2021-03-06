/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.PetDB;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Pet;


/**
 *
 * @author BTran
 */
public class ScheduleServices {
    
    //Retrieves a list of ScheduleBlockTypes
    public List<ScheduleBlockType> getScheduleBlockTypes() throws Exception{
        AppointmentDB apDB = new AppointmentDB();
        return apDB.getScheduleBlockTypes();
    }
    
    //Retrieves a list of available ScheduleBlocks
    public List<ScheduleBlock> getScheduleBlocks() throws Exception{
        AppointmentDB apDB = new AppointmentDB();
        return apDB.getScheduleBlocks();
    }
    
    //Retrieves a list of appointments the logged in user has
    public List<Appointment> getAllAppointments(int userID) throws Exception{
        AppointmentDB apDB = new AppointmentDB();
        return apDB.getAllAppointments(userID);
    }
    
    //Retrieves a list of apppointments a specific pet has
    public List<Appointment> getPetAppointments(int userID, int petID) {
        AppointmentDB apDB = new AppointmentDB();
        verifyInfo(userID, petID);
        return apDB.getPetAppointments(petID);
    }
    
    //checked to see fi the current pet is owned by the user current logged in
    /*******NOTE -  might have to set an admin bypass for them to book an appointment for the pet**********/
    private boolean verifyInfo(int userID, int petID) {
        boolean checked = false;
        PetDB petDB = new PetDB();
        Pet pet = petDB.getPet(petID);
        if (pet.getOwner.getUserID == userID) {
            checked = true;
        }
        return checked;
    }
    
    public void createAppointment(int appointmentID, int petID) {
        try {
            AppointmentDB apDB = new AppointmentDB();
            if (checkAppointmentInfo()) {
                Appointment appointment = new Appointment(appointmentID, petID)
                apDB.insert(Appointment)
                //persist to the Pets appointment List
                PetDB petDB = new PetDB();
                Pet pet = petDB.getPet(petID);
                pet.getAppointments.add(appointment);
            }
        } catch (Exception e) {
            Logger.getLogger(ScheduleServices.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private boolean checkAppointmentInfo(){
        boolean checked = false;
        return checked;
    }
}
