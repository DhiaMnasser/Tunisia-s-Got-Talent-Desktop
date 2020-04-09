/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assistance.Views;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author frauDEee
 */
public class NewFXMain extends Application {
    private double xOffset = 0;
        private double yOffset = 0;
        private BorderPane p;
    
    @Override
    public void start(Stage stage) throws IOException {
        
        
        Parent root = FXMLLoader.load(getClass().getResource("AvisUtilisateur.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        
        root.setOnMousePressed(e -> {
            xOffset = stage.getX() - e.getScreenX();
            yOffset = stage.getY() - e.getScreenY();
        });
        root.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() + xOffset);
            stage.setY(e.getScreenY() + yOffset);
        });
        
                stage.setScene(scene);
        //        new animatefx.animation.ZoomIn(root).play();

        stage.show();
        

    }
    
    public static void main(String[] args) {
                launch(args);

 
        
    }
    
}

/*Basculer
FXMLLoader loader = new FXMLLoader();
                label.getScene().getWindow().hide();
                Stage prStage = new Stage();
                loader.setLocation(getClass().getResource("AccueilClient.fxml"));
*/