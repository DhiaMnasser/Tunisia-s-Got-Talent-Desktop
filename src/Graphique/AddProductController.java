/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique;

import Entities.Produit;
import Services.ProduitService;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.awt.TrayIcon;
import static javafx.scene.control.Alert.AlertType.ERROR;import Services.ProduitService;
import TGT.MyDbConnection;
import com.sun.nio.sctp.Notification;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AddProductController implements Initializable {

    @FXML
    private TextField TName_Product;
    @FXML
    private TextField TQuantity_Total;
    @FXML
    private TextField TPrice_Product;
    @FXML
    private TextField TSize_Product;
    @FXML
    private TextField TId_Cat;
    @FXML
    private TextField TEt;
    @FXML
    private TextField TUrl;
    Services.ProduitService SP= new ProduitService();
    private Statement ste;
    private Connection cnx;
    @FXML
    private Button addproduct;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public boolean verifierchamps ()
    {
        String vName_Product = TName_Product.getText ();
        String vQuantity_Total = TQuantity_Total.getText ();
        String vPrice_Product = TPrice_Product.getText ();
        String vSize_Product  = TSize_Product.getText ();
        String vId_Cat = TId_Cat.getText ();
        String vUrl = TUrl.getText ();
        String vEt = TEt.getText ();
                // vérifie les champs vides
                if (vName_Product.trim (). equals ("") || vQuantity_Total.trim (). equals ("") || vPrice_Product.trim ().equals ("")
                   || vId_Cat.trim (). equals ("") || vSize_Product.trim (). equals ("") || vUrl.trim (). equals ("") || vEt.trim (). equals (""))
                {
                    JOptionPane.showMessageDialog (null, "One or more fields are empty", "Empty fields", 2);
                    return false;
                }else if(Integer.parseInt(vId_Cat)>6){
                 JOptionPane.showMessageDialog (null, "check for categorie", "categorie", 2);
                                    return false;
                }else if(Float.parseFloat(vPrice_Product)<=0){
                 JOptionPane.showMessageDialog (null, "check for price", "price", 2);
                                    return false;
                }else if(Integer.parseInt(vQuantity_Total)<0){
                 JOptionPane.showMessageDialog (null, "check for quantity", "quantity", 2);
                                    return false;
                }
                
                
                 return true;}
    @FXML
    private void addProduct(ActionEvent event)throws SQLException, IOException {
      cnx=MyDbConnection.getInstance().getConnexion();
      ste = cnx.createStatement();   
      Services.ProduitService SP= new ProduitService();
      Produit p = new Produit();
     
        String vName_Product = TName_Product.getText ();
        String vQuantity_Total = TQuantity_Total.getText ();
        String vPrice_Product = TPrice_Product.getText ();
        String vSize_Product  = TSize_Product.getText ();
        String vId_Cat = TId_Cat.getText ();
        String vUrl = TUrl.getText ();
        String vEt = TEt.getText ();
        if (verifierchamps ()==true)
        {p.setNom_Produit(vName_Product);
         p.setQuantite_Totale(Integer.parseInt(vQuantity_Total));
         p.setPrix_Produit(Float.parseFloat(vPrice_Product));
         p.setTaille_Produit(vSize_Product);
         p.setId_Categorie(Integer.parseInt(vId_Cat));
         p.setUrl(vUrl);
         p.setEtat_Produit(vEt);
         System.out.println(p.toString());
             Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Confirmation d'Ajout");
             alert.setHeaderText("Confirmation");
             alert.setContentText("Ajouter Produit ??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
           SP.addProduct(p);
           alert.setContentText("Ajouté");
           System.out.println("Nouveaux Produit ");
             }else{System.out.println("Annuler");}
        Parent tableview = FXMLLoader.load(getClass().getResource("ProductAdmin.fxml"));
        Scene sceneview = new Scene(tableview); 
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
         }              
         }
    
    @FXML
    private void back(ActionEvent event) throws IOException,SQLException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Graphique/Base.fxml")) ;
        Parent root = loader.load();
        Scene ascene = new Scene(root);
        Stage astage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        astage.setScene(ascene);
        astage.show();
    }
}
