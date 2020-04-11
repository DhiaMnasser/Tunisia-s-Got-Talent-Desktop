/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Achat.Controllers;

import Achat.Entities.Panier;
import Achat.Services.LigneCommandeService;
import Achat.Services.PanierService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Klaizer
 */
public class CheckoutController implements Initializable {

    @FXML
    private Button commanderBtn;
    @FXML
    private Label openHomeBtn;
    @FXML
    private Label openPanierBtn;
    @FXML
    private Label openStoreBtn;
    @FXML
    private Label openCommandeBtn;
    @FXML
    private TextField tel;
    @FXML
    private TextField address;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void commander(MouseEvent event) throws IOException, SQLException {
        
//        FXMLLoader userLoader = new FXMLLoader(getClass().getResource("/Khrouf/Views/User.fxml"));
//        Parent root = (Parent)userLoader.load();
//        UserController userController =  userLoader.getController();
//        User currentUser = userController.getConnectedUser();

        PanierService pans = new PanierService();
        
//      TODO replace UserID
//      Panier panier = pans.getPanierByUser(User.getId());
        Panier panier = pans.getPanierByUser(12);

        FXMLLoader commandeLoader = new FXMLLoader(getClass().getResource("/Achat/Views/Commande.fxml"));
        Parent root = (Parent)commandeLoader.load();
        CommandeController commandeController =  commandeLoader.getController();
        
        commandeController.ajouterCommande(panier,address.getText(), tel.getText());
        System.out.println("ajouterCommande is called");

        Parent commandePage = FXMLLoader.load(getClass().getResource("/Achat/Views/Commande.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(commandePage));
        stage.setTitle("Commandes");
        
        


            
    }

       @FXML
    private void openPanier(MouseEvent event) {
         try {
            Parent panierPage = FXMLLoader.load(getClass().getResource("/Achat/Views/Panier.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(panierPage));
            stage.setTitle("Panier");
        } catch (IOException ex) {
                Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   @FXML
    private void openStore(MouseEvent event) {
         try {
            Parent storePage = FXMLLoader.load(getClass().getResource("/Stock/Services/Produit.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(storePage));
            stage.setTitle("Store");
        } catch (IOException ex) {
                Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openHome(MouseEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("/tgt/Views/main.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(homePage));
            stage.setTitle("Main");
        } catch (IOException ex) {
                Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openCommande(MouseEvent event) {
         try {
            Parent commandePage = FXMLLoader.load(getClass().getResource("/Achat/Views/Commande.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(commandePage));
            stage.setTitle("Commande");
        } catch (IOException ex) {
                Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
