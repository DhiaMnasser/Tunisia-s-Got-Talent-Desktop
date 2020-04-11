/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TGT;

//import view.PersonneService;
import Services.PersonneService;
import Entities.Personne;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TGT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        PersonneService ps = new PersonneService();
        Personne p = new Personne("medg", "mohamed.khrouf4@gmail.com","test");
        
       
  
        try {
            ps.promote("medk");
          
        } catch (Exception ex) {
            Logger.getLogger(TGT.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
    }

}
