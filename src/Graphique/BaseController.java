/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique;

import java.io.IOException;
import java.net.URL;
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Graphique/ProductAdmin.fxml")) ;
        Parent root = loader.load();
        Scene ascene = new Scene(root);
        Stage astage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        astage.setScene(ascene);
        astage.show();
    //loadPage("AdminController");
    }

    @FXML
    private void User(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Graphique/ProductUser.fxml")) ;
        Parent root = loader.load();
        Scene ascene = new Scene(root);
        Stage astage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        astage.setScene(ascene);
        astage.show();
       // loadPage("StoreController");

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
