/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.exceptions;

/**
 *
 * @author Riley
 * The exceptions related to appointment.
 */
public class AppointmentException extends Exception {
    public AppointmentException(String msg) {
        super(msg);
    }
}
