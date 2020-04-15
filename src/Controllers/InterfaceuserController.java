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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class InterfaceuserController implements Initializable {

    @FXML
    private ImageView image;
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
    public ObservableList<Evenement> data = FXCollections.observableArrayList();
EvenementService s = new EvenementService () ;
    @FXML
    private Button afficherseul;
    @FXML
    private Button nouveau1;
    @FXML
    private TextField rechercher;
    @FXML
    private Button search;
    @FXML
    private TableView<?> tablesearch;
    @FXML
    private TableColumn<?, ?> nomsearch;
    @FXML
    private TableColumn<?, ?> etatsearch;

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
                  
                 // image.setCellValueFactory(new PropertyValueFactory<>("image"));
                 try {
            
            data = s.indexAction();
            
            Evenement.setItems(data);
           
            
           // System.out.println(data.get(1));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouterevent(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource(""));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("/UI/MainInterface.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
    }

    @FXML
    private void rechercherevent(ActionEvent event) throws SQLException {
        EvenementService s = new EvenementService ();
      
     
      Evenement e = new Evenement () ;
      e =s.rechercheEvenement(rechercher.getText()) ;
      if (e!=null)
      {
          new Alert(Alert.AlertType.INFORMATION, "Evénement Trouvé !").show();
          nomsearch.setText(e.getNomevent());
          etatsearch.setText(String.valueOf(e.getEtat()));
      }
      else
      {
          nomsearch.setText("No result");
          etatsearch.setText("No result");
          new Alert(Alert.AlertType.INFORMATION, "Evénement Introuvable ! :(((").show();
          
      }
    }
    
}
