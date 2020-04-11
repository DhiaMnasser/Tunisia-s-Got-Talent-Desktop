/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Evenement;
import Entities.Region;
import Services.EvenementService;
import Services.RegionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class ModifierRController implements Initializable {

    @FXML
    private TextField Nom;
    @FXML
    private Button modifierregion;
    @FXML
    private TextField Nb_villes;
    @FXML
    private ComboBox<Region> event_id;
    @FXML
    private Button RetourRegion;
    @FXML
    private TableView<Region> table;
    @FXML
    private TableColumn<Region, String> Nom1;
    @FXML
    private TableColumn<Region, Integer> Nb_villes2;
    @FXML
    private TableColumn<Region, Integer> Event_id1;
public ObservableList<Region> data = FXCollections.observableArrayList();
RegionService s = new RegionService () ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Nom1.setCellValueFactory(new PropertyValueFactory<>("Nom"));
         Nb_villes2.setCellValueFactory(new PropertyValueFactory<>("Nb_villes"));
         Event_id1.setCellValueFactory(new PropertyValueFactory<>("event_id"));
         try {
            System.out.println(data);
            data = s.indexActionR();
            System.out.println(data);
            table.setItems(data);
            System.out.println(data);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherAllRController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void editR(ActionEvent event) throws SQLException {
         RegionService cs = new RegionService();
        
       
            
            
            Region c  = new Region();
          Region c2 = table.getSelectionModel().getSelectedItem();
            int id_ev = c2.getId();
            
            
            cs.modifierRegion(id_ev, Nom.getText() ,Integer.parseInt(Nb_villes.getText()));
           
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Evenement modifié");
            alert.showAndWait();
            try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AfficherAllR.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            
            
        }
        
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
@FXML
    void selectR() {
     
        Region selected = table.getSelectionModel().getSelectedItem();
          if (!table.getSelectionModel().getSelectedItems().isEmpty()) {
        
        
        Nom.setText(selected.getNom());
        
        Nb_villes.setText(String.valueOf(selected.getNb_villes()));
        
         

        
        
        
        
          } else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("aucun élément 'a ètè séléctionné");
            alert.showAndWait();
          }
    }
    @FXML
    private void retourRA(ActionEvent event) throws IOException {
         Parent uploadPage= FXMLLoader.load(getClass().getResource("AfficherAllR.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
    }
    
}
