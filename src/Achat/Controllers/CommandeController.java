/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Achat.Controllers;

import Achat.Entities.Commande;
import Achat.Entities.Panier;
import Achat.Services.CommandeService;
import Achat.Services.GeneratePdf;
import Achat.Services.PanierService;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import tgt.Controllers.MainController;
import tgt.MyDbConnection;

/**
 * FXML Controller class
 *
 * @author Klaizer
 */
public class CommandeController implements Initializable {

    PanierService pans = new PanierService();
    CommandeService cmds = new CommandeService();
    int currentUser = 12;

    @FXML
    private TableView<Commande> table;
    @FXML
    private TableColumn<Commande, Integer> col_id;
    @FXML
    private TableColumn<Commande, Date> col_date;
    @FXML
    private TableColumn<Commande, Boolean> col_etat;
    private TableColumn<Commande, Button> col_actions;
    @FXML
    private TextField rechercher;
    @FXML
    private Label openHomeBtn;
    @FXML
    private Label openPanierBtn;
    @FXML
    private Label openStoreBtn;
    @FXML
    private Label openCommandeBtn;

    ObservableList<Commande> oblist = FXCollections.observableArrayList();

    Connection connexion;

    @FXML
    private Button consulter;
    @FXML
    private AnchorPane anc;

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {

            connexion = MyDbConnection.getInstance().getConnexion();

//                TODO : select commandes of the connected user
//                FXMLLoader userLoader = new FXMLLoader(getClass().getResource("/Khrouf/Views/User.fxml"));
//                Parent root = (Parent)userLoader.load();
//                UserController userController =  userLoader.getController();
//                User currentUser = userController.getConnectedUser();
//                ResultSet rs = connexion.createStatement().executeQuery("" SELECT * FROM `commande` WHERE `user_id`= '"+currentUser.getId()+"'" );
            ResultSet rs = connexion.createStatement().executeQuery(" SELECT * FROM `commande` WHERE `user_id`= '" + currentUser + "'");

            while (rs.next()) {

                oblist.add(new Commande(rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getDate("date"),
                        rs.getBoolean("etat"),
                        rs.getInt("idPanier"),
                        rs.getString("address"),
                        rs.getString("tel")));

            }

        } catch (Exception e) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, e);

        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        table.setItems(oblist);

    }

    @FXML
    private void rechercherCommandes(ActionEvent event) throws SQLException {

        connexion = MyDbConnection.getInstance().getConnexion();
        table.getItems().clear();

        try {
            String query = rechercher.getText();
            System.out.println(query);
            String requete = "select * from commande where `user_id`= '" + currentUser + "' and( `id` ='" + query + "' )";

            ResultSet rs = connexion.createStatement().executeQuery(requete);
            while (rs.next()) {
                oblist.add(new Commande(rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getDate("date"),
                        rs.getBoolean("etat"),
                        rs.getInt("idPanier"),
                        rs.getString("address"),
                        rs.getString("tel")));
            }
        } catch (SQLException e) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, e);

        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        System.out.println(oblist);

        table.setItems(oblist);
        System.out.println("result : " + oblist);

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
            Parent panierPage = FXMLLoader.load(getClass().getResource("/Stock/Services/Produit.fxml"));
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
            stage.setTitle("Main");
        } catch (IOException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
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

//    hethi tji fl controller li fih list ta3 les commande, velo , ....
    @FXML
    private void consulter(ActionEvent event) throws SQLException {

        try {

            if (table.getSelectionModel().getSelectedItem() != null) {

//             bch        
                Commande commande = table.getSelectionModel().getSelectedItem();

//      na3mel f instance ta3 controller e5er li bch yji fih affichage ta3 ka3ba wa7da w bch tet7at fih methode showCommande() 
                FXMLLoader consulterLoader = new FXMLLoader(getClass().getResource("/Achat/Views/ConsulterCommande.fxml"));
                Parent root = (Parent) consulterLoader.load();
                ConsulterCommandeController ccController = consulterLoader.getController();

//      t3ayet lel fonction showCommande ta3 controller le5er          
                ccController.showCommande(commande);

//      bch thezzek lel interface lo5ra                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Store");
            } else {
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
                alert1.setTitle("Alert");
                alert1.setContentText("Selectionner une commande");
                alert1.setHeaderText(null);
                alert1.show();
            }

        } catch (IOException ex) {
            Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void ajouterCommande(Panier panier, String address, String tel) throws SQLException {

        Panier pan1 = new Panier(panier.getUser_id());
        System.out.println(panier);
        System.out.println(pan1);

        Commande cmd = new Commande(panier.getUser_id(), panier.getId(), address, tel);
        System.out.println(cmd);
//        cmd.setDate(new java.sql.Date(System.currentTimeMillis()));
        cmds.addCommande(cmd);

        System.out.println("cmd ajouter");
        pans.changerEtatPanier(panier);
        pans.addPanier(pan1);
        System.out.println("new panier assigned to user"+currentUser);

        System.out.println("pdf in commande controller");

        try {
            GeneratePdf pdf = new GeneratePdf(cmd);
        } catch (DocumentException ex) {
            Logger.getLogger(ConsulterCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }

//         try {
//            Parent commandePage = FXMLLoader.load(getClass().getResource("/Achat/Views/Commande.fxml"));
//            Stage stage = (Stage) ((Node) MouseEvent.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(commandePage));
//            stage.setTitle("Commande");
//        } catch (IOException ex) {
//                Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
