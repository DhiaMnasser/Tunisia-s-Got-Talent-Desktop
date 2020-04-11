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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class ModifierController implements Initializable {

    @FXML
    private TextField nomevent;
    @FXML
    private Button image;
    @FXML
    private ImageView img;
    @FXML
    private Button envoyer;
    @FXML
    private TextField Duree;
    @FXML
    private TextField Gagnant;
    @FXML
    private TextField Etat;
    @FXML
    private DatePicker Date_d;
    @FXML
    private DatePicker Date_f;
    @FXML
    private TextField MaxParticipants;
    @FXML
    private ComboBox<?> region_id;
    @FXML
    private Button retourevent;
    @FXML
    private TableView<Evenement> Evenement;
    @FXML
    private TableColumn<Evenement, String> nomevent1;
    @FXML
    private TableColumn<Evenement, String> Duree1;
    
    @FXML
    private TableColumn<Evenement, String> Date_d1;
    @FXML
    private TableColumn<Evenement, String> Date_f1;
    @FXML
    private TableColumn<Evenement, String> Gagnant1;
    @FXML
    private TableColumn<Evenement, Integer> Etat1;
    @FXML
    private TableColumn<Evenement, Integer> MaxParticipants1;
    @FXML
    private TableColumn<Evenement, Integer> region_id1;
    @FXML
    private TableColumn<Evenement, String> image1;
public ObservableList<Evenement> data = FXCollections.observableArrayList();
EvenementService s = new EvenementService () ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nomevent1.setCellValueFactory(new PropertyValueFactory<>("nomevent"));
         Duree1.setCellValueFactory(new PropertyValueFactory<>("Duree"));
         Date_d1.setCellValueFactory(new PropertyValueFactory<>("Date_d"));

         Date_f1.setCellValueFactory(new PropertyValueFactory<>("Date_f"));
         Gagnant1.setCellValueFactory(new PropertyValueFactory<>("Gagnant"));
                  Etat1.setCellValueFactory(new PropertyValueFactory<>("Etat"));
               MaxParticipants1.setCellValueFactory(new PropertyValueFactory<>("MaxParticipants"));
                  region_id1.setCellValueFactory(new PropertyValueFactory<>("region_id"));
                  image1.setCellValueFactory(new PropertyValueFactory<>("image"));


       
  
       
        
        try {
            System.out.println(data);
            data = s.indexAction();
            System.out.println(data);
            Evenement.setItems(data);
            System.out.println(data);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void edit(ActionEvent event) throws SQLException {
        EvenementService cs = new EvenementService();
        
       
            
            
            Evenement c  = new Evenement();
          Evenement c2 = Evenement.getSelectionModel().getSelectedItem();
            int id_ev = c2.getId();
            
            
            cs.modifierEvenement(id_ev, Duree.getText(), Gagnant.getText(),nomevent.getText(),image.getText(),Date_d.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),Date_f.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) ,Integer.parseInt(MaxParticipants.getText()),Integer.parseInt(Etat.getText()));
           
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Evenement modifié");
            alert.showAndWait();
            try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("AfficherAll.fxml"));
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
    void select() {
     
        Evenement selected = Evenement.getSelectionModel().getSelectedItem();
          if (!Evenement.getSelectionModel().getSelectedItems().isEmpty()) {
        
        
        nomevent.setText(selected.getNomevent());
        Duree.setText(selected.getDuree());  
        Etat.setText(String.valueOf(selected.getEtat()));
        MaxParticipants.setText(String.valueOf(selected.getMaxParticipants()));
        //Date_f.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //Date_d.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Gagnant.setText(selected.getGagnant());
         image.setText(selected.getImage());
         

        
        
        
        
          } else {
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("aucun élément 'a ètè séléctionné");
            alert.showAndWait();
          }
    }
    @FXML
    private void retourEA(ActionEvent event) throws IOException {
         Parent uploadPage= FXMLLoader.load(getClass().getResource("AfficherAll.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
    }
    
}
