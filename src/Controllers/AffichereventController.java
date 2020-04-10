/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Entities.Evenement;
import Services.EvenementService;
/**
 *
 * @author Achraf
 */
public class AffichereventController implements Initializable{
    
    @FXML
    private Button supprimer;
    
   
    @FXML
    private Button nouveau;
    @FXML
    private Button bloquer;
     
    @FXML
    private TableView<Evenement> Evenement;
     EvenementService s = new EvenementService () ;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                   
   
        
         
         
            
        
     
        
    
    }    
      

    
    
   
        
       
  
   @FXML
    private void edit(ActionEvent event) {
         {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("update.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }}
    
}
