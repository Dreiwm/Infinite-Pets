/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 *
 * @author StormCloud
 */
public class PasswordServices {
    
    private static SecureRandom secrand;
    private static final String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ`1234567890~-=!@#$%^&*()_+[]{};|,./<>?";
    
    static{
        try {
            secrand= SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (NoSuchAlgorithmException|NoSuchProviderException e) {
            // TODO Auto-generated catch block
            secrand = new SecureRandom();
            e.printStackTrace();
        }
        secrand.setSeed(SecureRandom.getSeed(128));
    }
    
    public static boolean doesMatchHash(String password,String salt,String hash) {
        
        
        try {
            return hash(password+salt).equals(hash);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
            return false;
        }
    }
    
    
    public static String generatePasswordHash(String password,String salt) throws NoSuchAlgorithmException{
        return hash(password+salt);
    }
    
    public static String getRandomSalt(){
        String out = "";
        
        for (int i = 0; i < 32; i++) {
            out+=SALTCHARS.charAt(secrand.nextInt(SALTCHARS.length()));
        }
        
        return out;
    }
    
    private static String hash(String in) throws NoSuchAlgorithmException{
        byte[] hash = MessageDigest.getInstance("SHA-256").digest(in.getBytes(StandardCharsets.UTF_8));
        String out= bytetohex(hash);
        return out;
    }
    /*
    
    // this can be used to make password hash/salts for direct injection into the database.
    //
    
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String pass = "password";
        String salt = getRandomSalt();
        String hash = generatePasswordHash(pass, salt);
        //FileWriter fw = new FileWriter(new File("hash.txt"));
        //fw.write(hash);
        //fw.flush();
        //fw.close();
        System.out.println(pass);
        System.out.println(salt);
        System.out.println(hash);
        System.out.printf("'%s','%s','%s',\n", pass, hash, salt);
        
        //BufferedReader br =  new BufferedReader(new FileReader(new File("hash.txt")));
        //String hashin =  br.readLine();
        //br.close();
        //System.out.println(hashin);
        //System.out.println(hash.equals(hashin));
        
    }
    */
    private static String bytetohex(byte[] bytes) {
        String out="";
        for (int i = 0; i < bytes.length; i++) {
            out+=String.format("%02X", bytes[i]);
        }
        return out;
    }
}