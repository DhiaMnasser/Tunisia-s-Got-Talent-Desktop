/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock.Services;

import Achat.Controllers.CommandeController;
import Achat.Controllers.PanierController;
import Achat.Entities.LigneCommande;
import Achat.Services.LigneCommandeService;
import Stock.Entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tgt.MyDbConnection;



/**
 * FXML Controller class
 *
 * @author Klaizer
 */
public class ProduitController implements Initializable {

    @FXML
    private Label nomProduit;

    
    Connection connexion;
    @FXML
    private Label message;
    @FXML
    private Label openPanierBtn;
    @FXML
    private Button serwel;
    @FXML
    private Button tshirt;
    @FXML
    private Button bracelet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void acheterProduit(ActionEvent event) throws SQLException, IOException {
        try {
            connexion = MyDbConnection.getInstance().getConnexion();
//        LigneCommandeService lcs = new LigneCommandeService();
            ProduitService pdts = new ProduitService();

//        Produit pdt = new Produit();
//            String nomPdt = nomProduit.getText();

            Button btn = (Button) event.getSource();
            System.out.println(btn.getId());
         
            Produit pdt = pdts.getByName(btn.getId());
            System.out.println(pdt);
//        LigneCommande lg = new LigneCommande();
//        lg.setIdPanier(55);
//        lg.setIdproduit(pdt.getId_Produit());
//        lg.setNomProduit(pdt.getNom_Produit());
//        lg.setQuantite(1);
//        lcs.addLigneCommande(lg);
//        message.setText("Pdt ajoutee aux panier");
            
            FXMLLoader panierLoader = new FXMLLoader(getClass().getResource("/Achat/Views/Panier.fxml"));
            Parent root = (Parent) panierLoader.load();
            PanierController panierController = panierLoader.getController();
            panierController.AjouterLigneCommande(pdt);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.setTitle("Panier");      
            
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Alert");
                    alert1.setContentText("Produit Ajoute Au Panier");
                    alert1.setHeaderText(null);
                    alert1.show();
        } catch (SQLException sQLException) {
            sQLException.getMessage();
        } catch (IOException iOException) {
            iOException.getMessage();
        }
              
        

    }
        
    @FXML
    private void openPanier(MouseEvent event) {
         try {
            Parent panierPage = FXMLLoader.load(getClass().getResource("/Achat/Views/Panier.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(panierPage));
            stage.setTitle("Panier");
        } catch (IOException ex) {
                Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    
}
