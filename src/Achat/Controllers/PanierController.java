/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Achat.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Klaizer
 */
public class PanierController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private ImageView img1;
    @FXML
    private Button checkout;
    @FXML
    private MenuButton trierLigneCommande;
    @FXML
    private Label prixTotal;
    @FXML
    private Button deleteLigneCommande;
    @FXML
    private Label nomProduit;
    @FXML
    private Text tailleProduit;
    @FXML
    private TextField quantiteProduit;
    @FXML
    private TextField rechercherLigneCommande;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
 
    @FXML
    private void checkoutAction(ActionEvent event) {
    }

    @FXML
    private void deleteAction(ActionEvent event) {
    }
    
    @FXML
    private void updateQuantite(ActionEvent event) {
    }
    
     @FXML
    private void trierPrix(ActionEvent event) {
    }

    @FXML
    private void trierNom(ActionEvent event) {
    }
}
