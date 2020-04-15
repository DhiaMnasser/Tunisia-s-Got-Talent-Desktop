/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Achat.Controllers;

import Achat.Entities.Commande;
import Achat.Entities.LigneCommande;
import Achat.Entities.Panier;
import Achat.Services.LigneCommandeService;
import Achat.Services.PanierService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Klaizer
 */
public class ConsulterCommandeController implements Initializable {

    @FXML
    private Label openHomeBtn;
    @FXML
    private Label openPanierBtn;
    @FXML
    private Label openStoreBtn;
    @FXML
    private Label openCommandeBtn;
    @FXML
    private TableView<LigneCommande> table;
    @FXML
    private TableColumn<LigneCommande, String> col_produit;
    @FXML
    private TableColumn<LigneCommande, Integer> col_quantite;
    @FXML
    private AnchorPane anchorPane;

    Connection connexion;
    ObservableList<LigneCommande> oblist = FXCollections.observableArrayList();
    LigneCommandeService lcs = new LigneCommandeService();
    @FXML
    private Text numCommande;
    @FXML
    private Text date;
    @FXML
    private Text adresse;
    @FXML
    private Text etat;
    @FXML
    private Text tel;
    @FXML
    private Text prixTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
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

    @FXML
    private void openStore(MouseEvent event) {
        try {
            Parent panierPage = FXMLLoader.load(getClass().getResource("/Stock/Graphique/ProductUser.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(panierPage));
            stage.setTitle("Store");
        } catch (IOException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openHome(MouseEvent event) {

        try {
            Parent panierPage = FXMLLoader.load(getClass().getResource("/tgt/Views/main.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(panierPage));
            stage.setTitle("Home");
        } catch (IOException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openCommande(MouseEvent event) {

        try {

            Parent panierPage = FXMLLoader.load(getClass().getResource("/Achat/Views/Commande.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(panierPage));
            stage.setTitle("Mes Commandes");

        } catch (IOException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//  hethi mawjouda fl controller li bch ya3ml l'affichage ta3 l'commande   
    public void showCommande(Commande cmd) throws SQLException {
        // TODO  

        List<LigneCommande> lgList = null;
        PanierService pans = new PanierService();
        
           Panier pan = pans.getPanierById(cmd.getIdPanier());
        
        try {

//      tjib f list ta3 ligneCommande (les produits) li teb3in l'commande        
            lgList = lcs.getLigneCommandesByPanier(pan.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<LigneCommande> obLgList = FXCollections.observableArrayList(lgList);
        col_produit.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
//        col_prix.set
//        col_actions.setCellValueFactory(new PropertyValueFactory<>("button"));
        numCommande.setText("cmd" + cmd.getId());
        date.setText("" + cmd.getDate());
        adresse.setText("" + cmd.getAddress());
        tel.setText("" + cmd.getTel());
        prixTotal.setText(""+pan.getPrixTotal()+" DT");
        String et;
        if (cmd.getEtat()) {
            et = "validee";
        } else {
            et = "en cours";
        }

        etat.setText(et);

        table.setItems(obLgList);

    }

}
