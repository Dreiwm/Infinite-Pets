/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author Chris
 */
public class SanitaizationService {
    /**
     * This function replaces all characters that are not alphanumeric with '%'
     * @param str
     * @return the sanitized string
     */
    public String sanitizeInput(String str){
        return str.replaceAll("[^a-zA-Z0-9]", "%");
    }
    
    /**
     * This function replaces all characters that are not alphanumeric or @,-,_
     * with '%'
     * @param str
     * @return the sanitized string
     */
    public String sanitizeEmail(String str){
        return str.replaceAll("[^a-zA-Z0-9@-_]", "%");
    }
    
    /**
     * This function replaces all spaces in the given string with '%'
     * @param str
     * @return the sanitized string
     */
    public String sanitizePassword(String str){
        return str.replace(' ', '%');
    }
    
    /**
     * This function replaces all instances of '%' with a space
     * @param str
     * @return 
     */
    public String desanitize(String str){
        return str.replace('%', ' ');
    }
}
