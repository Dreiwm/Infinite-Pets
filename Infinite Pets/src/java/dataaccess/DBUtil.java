/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Riley Hiltz <riley.hiltz@edu.sait.ca>
 */
public class DBUtil {
    private static final EntityManagerFactory emf =
          Persistence.createEntityManagerFactory("HOME_nVentoryPU");

    public static EntityManagerFactory getEmFactory() {
      return emf;
    }
}
