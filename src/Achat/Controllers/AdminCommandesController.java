/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Achat.Controllers;

import Achat.Entities.Commande;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tgt.MyDbConnection;

/**
 * FXML Controller class
 *
 * @author Klaizer
 */
public class AdminCommandesController implements Initializable {

    @FXML
    private TextField rechercher;
    @FXML
    private TableView<Commande> table;
    @FXML
    private TableColumn<Commande, Integer> col_id;
    @FXML
    private TableColumn<Commande, Date> col_date;
    @FXML
    private TableColumn<Commande, Boolean> col_etat;
     @FXML
    private TableColumn<Commande, Button> col_actions;
    @FXML
    private Label openHomeBtn;
    @FXML
    private Label openStoreBtn;
    @FXML
    private Label openCommandeBtn;
    @FXML
    private Button consulter;
    
    Connection connexion;
    ObservableList <Commande> oblist = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          // TODO
        try {

                connexion=MyDbConnection.getInstance().getConnexion();
                
                
//                TODO : select commandes of the connected user
//                FXMLLoader userLoader = new FXMLLoader(getClass().getResource("/Khrouf/Views/User.fxml"));
//                Parent root = (Parent)userLoader.load();
//                UserController userController =  userLoader.getController();
//                User currentUser = userController.getConnectedUser();
//                ResultSet rs = connexion.createStatement().executeQuery("" SELECT * FROM `commande` WHERE `user_id`= '"+currentUser.getId()+"'" );

                
                ResultSet rs = connexion.createStatement().executeQuery(" SELECT * FROM `commande` ");
              
                while (rs.next()){
                    
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
        col_actions.setCellValueFactory(new PropertyValueFactory<>("button"));
        table.setItems(oblist);
    }    

    @FXML
    private void rechercherCommandes(InputMethodEvent event) {
    }

    @FXML
    private void openHome(MouseEvent event) {
    }

    @FXML
    private void openStore(MouseEvent event) {
    }

    @FXML
    private void openCommande(MouseEvent event) {
     
    }

    @FXML
    private void consulter(ActionEvent event) {
        
         try {
            
                
                if(table.getSelectionModel().getSelectedItem() != null){
                    

//      na3mel f instance ta3 controller e5er li bch yji fih affichage ta3 ka3ba wa7da w bch tet7at fih methode showCommande() 
                FXMLLoader consulterLoader = new FXMLLoader(getClass().getResource("/Achat/Views/AdminConsulterCommande.fxml"));
                Parent root = (Parent)consulterLoader.load();
                AdminConsulterCommandeController accController =  consulterLoader.getController();
                
//      t3ayet lel fonction showCommande ta3 controller le5er          
                accController.showCommande(table.getSelectionModel().getSelectedItem());
                
//      bch thezzek lel interface lo5ra                
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Admin : Commande "+table.getSelectionModel().getSelectedItem().getId());
                }else{
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
    
}
