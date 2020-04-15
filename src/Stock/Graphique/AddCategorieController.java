/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock.Graphique;

import Stock.Entities.Categorie;
import Stock.Services.CategorieService;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tgt.MyDbConnection;
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
 * @author Haddad
 */
public class AddCategorieController implements Initializable {

    @FXML
    private TextField TName_Cat;
    
    Stock.Services.CategorieService SP= new CategorieService();
    private Statement ste;
    private Connection cnx;
    @FXML
    private Button addCategorie;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public boolean verifierchamps ()
    {
        String vName_Cat = TName_Cat.getText ();

                // vérifie les champs vides
                if (vName_Cat.trim (). equals (""))
                {
                    JOptionPane.showMessageDialog (null, "One or more fields are empty", "Empty fields", 2);
                    return false;            
                }
                
                 return true;}
    @FXML
    private void addCategorie(ActionEvent event)throws SQLException, IOException {
    cnx=MyDbConnection.getInstance().getConnexion();
    ste = cnx.createStatement();   
    Stock.Services.CategorieService SP= new CategorieService();
        Categorie p = new Categorie();
        String vName_Cat = TName_Cat.getText ();
           if (verifierchamps ()==true)
           {p.setNom_Categorie(vName_Cat);
            System.out.println(p.toString());
             Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Confirmation d'Ajout");
             alert.setHeaderText("Confirmation");
             alert.setContentText("Ajouter Categorie ?");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
           SP.addCategorie(p);
           alert.setContentText("Ajouté");
           System.out.println("Nouvelle Categorie ");
             }else{System.out.println("Annuler");}
           
        
         }            
        Parent tableview = FXMLLoader.load(getClass().getResource("/Stock/Graphique/CategorieAdmin.fxml"));   
        Scene sceneview = new Scene(tableview);    
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); }
    
    @FXML
    private void back(ActionEvent event) throws IOException,SQLException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/tgt/Views/main.fxml")) ;
        Parent root = loader.load();
        Scene ascene = new Scene(root);
        Stage astage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        astage.setScene(ascene);
        astage.show();
    }

    @FXML
    private void cat(ActionEvent event) throws IOException,SQLException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Stock/Graphique/CategorieAdmin.fxml")) ;
        Parent root = loader.load();
        Scene ascene = new Scene(root);
        Stage astage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        astage.setScene(ascene);
        astage.show();
    }
    
    @FXML
    private void home(ActionEvent event) throws IOException,SQLException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Stock/Graphique/main.fxml")) ;
        Parent root = loader.load();
        Scene ascene = new Scene(root);
        Stage astage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        astage.setScene(ascene);
        astage.show();
    }
    @FXML
    private void prod(ActionEvent event) throws IOException,SQLException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Stock/Graphique/ProductAdmin.fxml")) ;
        Parent root = loader.load();
        Scene ascene = new Scene(root);
        Stage astage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        astage.setScene(ascene);
        astage.show();
    }
}
