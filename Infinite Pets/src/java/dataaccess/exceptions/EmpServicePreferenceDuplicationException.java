/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.exceptions;

/**
 *
 * @author Riley
 */
public class EmpServicePreferenceDuplicationException extends Exception {
    public EmpServicePreferenceDuplicationException() {
        super("WOrk Preferences cannot be duplicated");
    }
}
