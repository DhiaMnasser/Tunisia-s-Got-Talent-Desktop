/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.Personne;
import Services.Usercourant;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Services.Usercourant;
/**
 *
 * @author mohamed khrouf
 */
public class User extends Application {
    Personne a;
    @Override
    public void start(Stage primaryStage) {
       
       
        
  Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Userfx.fxml"));
            Scene scene = new Scene(root);
             primaryStage.setTitle(Usercourant.ok.toString());
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
     public void get(Personne p){
    this.a=p    ;}
     
}
