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
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.paint.Color;


/**
 *
 * @author Achraf
 */
public class TGT extends Application{
    
        @Override
    public void start(Stage stage) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("/Controllers/MainInterface.fxml"));

            Scene scene = new Scene(root);
            stage.setTitle("TGT");
            stage.setScene(scene);
            stage.show();
        
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        
        btn.setLayoutY(0.5);
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                EvenementService es = new EvenementService();
                try {
                    es.afficher();
                } catch (SQLException ex) {
                    Logger.getLogger(TGT.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 200, 200);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
      
       // Evenement e = new Evenement("Achraf", "Chourabi","Tunis","img");
       // Region r = new Region("achraf",21);
       // RegionService rs = new RegionService();
      //  EvenementService es = new EvenementService();
       // es.ajouterEvenement2(e);
       // es.supprimerEvenement(26);
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
      launch(args) ;
    }
    
}
