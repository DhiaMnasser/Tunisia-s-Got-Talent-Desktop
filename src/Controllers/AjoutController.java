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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
public class AjoutController implements Initializable {

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
    private ComboBox<String> region_id;
    @FXML
    private Button retourevent;
    @FXML
    private TableView<?> Evenement;
    @FXML
    private TableColumn<?, ?> nomevent1;
    @FXML
    private TableColumn<?, ?> Duree1;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> Date_d1;
    @FXML
    private TableColumn<?, ?> Date_f1;
    @FXML
    private TableColumn<?, ?> Gagnant1;
    @FXML
    private TableColumn<?, ?> Etat1;
    @FXML
    private TableColumn<?, ?> MaxParticipants1;
    @FXML
    private TableColumn<?, ?> region_id1;
    @FXML
    private TableColumn<?, ?> image1;
public ObservableList<String> data = FXCollections.observableArrayList();
RegionService s = new RegionService () ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        try {
            
            data = s.indexActionRliste();
            
            region_id.setItems(data);
           
        } catch (SQLException ex) {
            Logger.getLogger(AfficherAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    @FXML
    private void ajouter(ActionEvent event) throws SQLException, IOException {   
        if (validateInputs())
        {
        String nomevent1 = nomevent.getText() ;
          int Etat1 = (Integer) Integer.parseInt(Etat.getText()) + 0;
          int MaxParticipants1 = (Integer) Integer.parseInt(MaxParticipants.getText()) + 0;
        String Date_d1 = Date_d.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String Date_f1 = Date_f.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String duree1 = Duree.getText() ;
        String gagnant1 = Gagnant.getText() ;
        String image1 = image.getText() ;

      
       
        EvenementService cs = new EvenementService();
        String region_id1 = region_id.getSelectionModel().getSelectedItem();
        
        int a = cs.chercherregion(region_id1) ;
        
        Evenement c = new Evenement (nomevent1,duree1,gagnant1,image1,Date_d1,Date_f1,MaxParticipants1,Etat1,a);
        cs.ajouterEvenement2(c);
        System.out.println("Event ajouté");
        new Alert(Alert.AlertType.INFORMATION, "Evénement Ajouté !").show();
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
    }
    public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
    }
public boolean validateInputs() {
        if (nomevent.getText().length() == 0 || Duree.getText().length() == 0
                ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Champ vide !");
            alert.showAndWait();
            return false;
        } else if (isNotInteger(Etat.getText()) || isNotInteger(MaxParticipants.getText())) {

            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("champ Etat/Maxparticipants : Entrez des chiffres pas autre chose");
            alert1.setHeaderText(null);
            alert1.show();
            return false;

        }
        else if ((Integer) Integer.parseInt(Etat.getText()) + 0 !=0 && (Integer) Integer.parseInt(Etat.getText()) + 0 !=1)
        {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Champ Etat doit être égale à 0 ou 1");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        }
        else if (Date_d.getValue().compareTo(Date_f.getValue())>=0   || Date_d.getValue().compareTo(java.time.LocalDate.now())<0)
        {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Champ date corrompu : date de début sup à la date de fin et sup à la date actuelle !   ");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
            
        }
        else if ((Integer) Integer.parseInt(MaxParticipants.getText()) + 0 <0)
        {
             Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Champ MaxParticipants ne doit pas être négatif");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        }
         return true;
}
    @FXML
    private void retourEA(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("AfficherAll.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        
    }}
        
    
    
    

