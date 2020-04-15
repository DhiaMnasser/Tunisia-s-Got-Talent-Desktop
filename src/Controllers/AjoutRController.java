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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Achraf
 */
public class AjoutRController implements Initializable {

    @FXML
    private TextField Nom;
    @FXML
    private Button envoyer;
    @FXML
    private TextField Nb_villes;
    @FXML
    private ComboBox<?> event_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterR(ActionEvent event) throws SQLException, IOException {
        String nomregion = Nom.getText() ;
          int nbvilles = (Integer) Integer.parseInt(Nb_villes.getText()) + 0;
         RegionService cs = new RegionService();
        Region c = new Region (nomregion,nbvilles);
        if (validateInputs()){
        cs.ajouterRegion2(c);
        System.out.println("Region ajoutée"); 
        new Alert(Alert.AlertType.INFORMATION, "Région ajoutée !").show();
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
    }
    @FXML
    private void retourRA(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("AfficherAllR.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        
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
        if (Nom.getText().length() == 0 
                ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Champ vide !");
            alert.showAndWait();
            return false;
        } else if (isNotInteger(Nb_villes.getText()) ) {

            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("champ Nb_villes : Entrez des chiffres pas autre chose");
            alert1.setHeaderText(null);
            alert1.show();
            return false;

        }
        else if ((Integer) Integer.parseInt(Nb_villes.getText()) + 0 <0 )
        {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Champ Nb_villes ne doit pas être négatif");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        }
        
         return true;
}
    
}
