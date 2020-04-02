/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Entities.Evenement;
import Entities.Region;
import Services.EvenementService;
import Services.RegionService;
import java.sql.SQLException;

/**
 *
 * @author Achraf
 */
public class TGT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Evenement e = new Evenement("Achraf", "Chourabi","Tunis","img");
        Region r = new Region("achraf",21);
        RegionService rs = new RegionService();
        EvenementService es = new EvenementService();
       // es.ajouterEvenement2(e);
       // es.supprimerEvenement(25);
        //es.modifierEvenement(24,"Modification", "Modification","Tunis","img",10,2);
       // System.out.println(es.getAllEvenements()+"\t");
       // System.out.println("crud region");
       
      // System.out.println("\n");
      // es.rechercheEvenement("Sousse");
       // es.rechercheEvenement("Youssef");
       
       
        //rs.ajouterRegion2(r);
        //rs.supprimerRegion(26);
        //rs.modifierRegion(24,"Zaghouan", 7);
        //rs.rechercheRegion("Tunis");
        
        
       
       // rs.nbRegion();
       //rs.afficher();
       
       // es.BloquerEvenement(10);
        
       // es.afficher();
        
        
        /*System.out.println("Evenements Actifs : "+"\n" ) ;
        es.affichereventactif();
        System.out.println("Evenements Terminés : "+"\n" ) ;
        es.affichereventpassif();*/

        
       // System.out.println(rs.getAllRegions()+"\t");
    }
    
}
