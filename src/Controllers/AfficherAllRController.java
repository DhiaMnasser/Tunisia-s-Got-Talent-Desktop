/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Region;
import Services.RegionService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
public class AfficherAllRController implements Initializable {

    @FXML
    private TableView<Region> table;
    @FXML
    private TableColumn<Region, String> Nom;
    @FXML
    private TableColumn<Region, Integer> Nb_villes;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<Region, Integer> event_id;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button retour;
    @FXML
    private TableColumn<?,?> nomsearchr ;
    @FXML
    private TableColumn<?,?> nbvillessearchr ;
    @FXML
    private TextField rechercherregiontest;
public ObservableList<Region> data = FXCollections.observableArrayList();
RegionService s = new RegionService () ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
         Nb_villes.setCellValueFactory(new PropertyValueFactory<>("Nb_villes"));
         event_id.setCellValueFactory(new PropertyValueFactory<>("event_id"));
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
    private void ajouterregion(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("AjoutR.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        
    } 
    @FXML
    private void deleteregion(ActionEvent event) throws SQLException {
        
        if(table.getSelectionModel().getSelectedItems().size()!=0) {
           
           s.supprimerRegion(table.getSelectionModel().getSelectedItems().get(0).getId());
           new Alert(Alert.AlertType.INFORMATION, "Région supprimée !").show();
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
        
        
       else{
           
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("aucun élément 'a ètè séléctionné");
        alert.showAndWait();

           
        
       }
    }
    @FXML
    public void rechercherregion(ActionEvent event) throws IOException,SQLException{
      RegionService s = new RegionService ();
      
     
      Region e = new Region () ;
      e =s.rechercheRegion(rechercherregiontest.getText()) ;
      if (e!=null)
      {
          new Alert(Alert.AlertType.INFORMATION, "Evénement Trouvé !").show();
          nomsearchr.setText(e.getNom());
          nbvillessearchr.setText(String.valueOf(e.getNb_villes()));
      }
      else
      {
          nomsearchr.setText("No result");
          nbvillessearchr.setText("No result");
          new Alert(Alert.AlertType.INFORMATION, "Evénement Introuvable ! :(((").show();
          
      }}
    @FXML
    private void retourR(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("/UI/MainInterface.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        
    }
    @FXML
    private void modifierRegiontest(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("ModifierR.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        
    }
    
}
