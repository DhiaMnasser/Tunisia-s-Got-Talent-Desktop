/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tgt.Controllers;

import Achat.Controllers.CommandeController;
import Stock.Graphique.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Haddad
 */
public class BaseController implements Initializable {
    
    @FXML
    private Button Admin;
    @FXML
    private Button User;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void Admin(ActionEvent event) throws IOException {
        
        
        try {
            Parent panierPage = FXMLLoader.load(getClass().getResource("/tgt/Views/AdminMain.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(panierPage));
            stage.setTitle("Admin");
        } catch (IOException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    //loadPage("AdminController");
    }

    @FXML
    private void User(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/tgt/Views/main.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("User");
        } catch (IOException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void home(ActionEvent event) throws IOException,SQLException {
        try {
            Parent panierPage = FXMLLoader.load(getClass().getResource("/tgt/Views/Base.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(panierPage));
            stage.setTitle("home");
        } catch (IOException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*private void loadPage(String page){
                           
        Parent root = null;
        try {
        root = FXMLLoader.load(getClass().getClassLoader().getResource(page+".fxml"));

        } catch (Exception ex) {
            Logger.getLogger(Graphique.BaseController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }  */
    
    
    
}
