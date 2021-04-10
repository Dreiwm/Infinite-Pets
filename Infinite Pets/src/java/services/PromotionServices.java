/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.PromotionDB;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Promotion;

/**
 *
 * @author Riley
 * Deals everything with promotion and discounts (they have relationship in DB).
 */
public class PromotionServices {
    
    /**
     *
     * @param id the id of promotion.
     * @return returns promo from DB, null if cannot find.
     */
    public Promotion getPromotion(int id) throws Exception {
        PromotionDB pDB = new PromotionDB();
        
        return pDB.get(id);
    }
    
    /**
     * Updates the Promotion in the DB.
     * @param p the Promotion to be updated.
     * @return returns true if successfully updated. Otherwise, false.
     */
    public boolean updatePromotion(Promotion p) {
        PromotionDB pDB = new PromotionDB();
        try {
            pDB.update(p);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    /**
     * Deactivates the Promotion using given ID.
     * @param id id of promotion to be deactivated.
     * @return true if successfully, otherwise false.
     */
    public boolean deactivatePromotion(int id) {
        // get Promotion
        PromotionDB pDB = new PromotionDB();
        try {
            Promotion p = pDB.get(id);
            p.setActive(false);
            pDB.update(p);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Activates the promotion with given promo id.
     * @param id the id to be used to activate the promo.
     * @return true if successfully activated (regardless if already active)
     */
    public boolean activatePromotion(int id) {
// get Promotion
        PromotionDB pDB = new PromotionDB();
        try {
            Promotion p = pDB.get(id);
            p.setActive(true);
            pDB.update(p);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(PromotionServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }    
    }
    
    /**
     * Creates new promotion and insert into the DB.
     * @param p the promotion to be created.
     * @return true if successfully inserted. Otherwise false.
     */
    public boolean createPromotion(Promotion p) throws Exception {
        PromotionDB pDB = new PromotionDB();
        
        return pDB.insert(p);
    }
    
    
}
