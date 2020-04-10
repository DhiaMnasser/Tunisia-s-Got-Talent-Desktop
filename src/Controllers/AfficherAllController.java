/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Evenement;
import Services.EvenementService;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class AfficherAllController implements Initializable {
@FXML
    private Pane firstpane;
    @FXML
    private TableView<Evenement> Evenement;
    
    @FXML
    private TableColumn<Evenement, String> nomevent;
    @FXML
    private TableColumn<Evenement, String> Duree;
    
    @FXML
    private TableColumn<Evenement, String> Date_d;
    @FXML
    private TableColumn<Evenement, String> Date_f;
    @FXML
    private TableColumn<Evenement, String> Gagnant;
    @FXML
    private TableColumn<Evenement, Integer> Etat;
    @FXML
    private TableColumn<Evenement, Integer> MaxParticipants;
    @FXML
    private TableColumn<Evenement, Integer> region_id;
    @FXML
    private TableColumn<Evenement, String> image;
    public ObservableList<Evenement> data = FXCollections.observableArrayList();
EvenementService s = new EvenementService () ;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button bloquer;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomevent.setCellValueFactory(new PropertyValueFactory<>("nomevent"));
         Duree.setCellValueFactory(new PropertyValueFactory<>("Duree"));
         Date_d.setCellValueFactory(new PropertyValueFactory<>("Date_d"));

         Date_f.setCellValueFactory(new PropertyValueFactory<>("Date_f"));
         Gagnant.setCellValueFactory(new PropertyValueFactory<>("Gagnant"));
                  Etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
               MaxParticipants.setCellValueFactory(new PropertyValueFactory<>("MaxParticipants"));
                  region_id.setCellValueFactory(new PropertyValueFactory<>("region_id"));
                  image.setCellValueFactory(new PropertyValueFactory<>("image"));


       
  
       
        
        try {
            System.out.println(data);
            data = s.indexAction();
            System.out.println(data);
            Evenement.setItems(data);
            System.out.println(data);
        } catch (SQLException ex) {
            Logger.getLogger(AffichereventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    @FXML
    private void ajouterevent(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("Ajout.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        
    }
    @FXML
    private void deleteevent(ActionEvent event) throws SQLException {
        
        if(Evenement.getSelectionModel().getSelectedItems().size()!=0) {
           
           s.supprimerEvenement(Evenement.getSelectionModel().getSelectedItems().get(0).getId());
             try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("afficherAll.fxml"));
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
    private void bloquerevent(ActionEvent event) throws SQLException {
        if(Evenement.getSelectionModel().getSelectedItems().size()!=0) {
           
           s.BloquerEvenement(Evenement.getSelectionModel().getSelectedItems().get(0).getId());
             try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("afficherAll.fxml"));
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
    private void retour(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("MainInterface.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        
    }
    
    
    
}
