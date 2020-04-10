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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Label l_error;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void ajouter(ActionEvent event) throws SQLException, IOException {   
        
        String nomevent1 = nomevent.getText() ;
          int Etat1 = (Integer) Integer.parseInt(Etat.getText()) + 0;
          int MaxParticipants1 = (Integer) Integer.parseInt(MaxParticipants.getText()) + 0;
        String Date_d1 = Date_d.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String Date_f1 = Date_f.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String duree1 = Duree.getText() ;
        String gagnant1 = Gagnant.getText() ;
        String image1 = image.getText() ;

      
       
        EvenementService cs = new EvenementService();
        Evenement c = new Evenement (nomevent1,duree1,gagnant1,image1,Date_d1,Date_f1,MaxParticipants1,Etat1);
        cs.ajouterEvenement2(c);
        System.out.println("cours ajouté"); 
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
    public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return true;
        }

        return false;
    }
private boolean validateInputs() {
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
            alert1.setContentText("Entrez des chiffres pas autre chose");
            alert1.setHeaderText(null);
            alert1.show();
            return false;

        }

    
        
    
    
    

