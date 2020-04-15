/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock.Graphique;

import Stock.Entities.Produit;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Stock.Services.ProduitService;
import tgt.MyDbConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Haddad
 */
public class AddProductController implements Initializable {
    ObservableList list=FXCollections.observableArrayList();
    String Categorie[] = 
                   { "T-Shirt", "Acc", "Tickets", 
                                   "Autres", "VIP" }; 
    @FXML
    private TextField TName_Product;
    @FXML
    private TextField TQuantity_Total;
    @FXML
    private TextField TPrice_Product;
    @FXML
    private TextField TSize_Product;
    @FXML
    private ComboBox TId_Cat ;
    @FXML
    private TextField TEt;
    @FXML
    private TextField TUrl;
    Stock.Services.ProduitService SP= new ProduitService();
    private Statement ste;
    private Connection cnx;
    @FXML
    private Button addproduct;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadData();
        // TODO
    }    
    public boolean verifierchamps ()
    {
        String vName_Product = TName_Product.getText ();
        String vQuantity_Total = TQuantity_Total.getText ();
        String vPrice_Product = TPrice_Product.getText ();
        String vSize_Product  = TSize_Product.getText ();
        String vId_Cat = (String) TId_Cat.getValue();
        String vUrl = TUrl.getText ();
        String vEt = TEt.getText ();
                // vérifie les champs vides
                if (vName_Product.trim (). equals ("") || vQuantity_Total.trim (). equals ("") || vPrice_Product.trim ().equals ("")
                   || vId_Cat.trim (). equals ("") || vSize_Product.trim (). equals ("") || vUrl.trim (). equals ("") || vEt.trim (). equals (""))
                {
                    JOptionPane.showMessageDialog (null, "One or more fields are empty", "Empty fields", 2);
                    return false;
                }else if(Float.parseFloat(vPrice_Product)<=0){
                 JOptionPane.showMessageDialog (null, "check for price", "price", 2);
                                    return false;
                }else if(Integer.parseInt(vQuantity_Total)<0){
                 JOptionPane.showMessageDialog (null, "check for quantity", "quantity", 2);
                                    return false;
                }
                
                
                 return true;}
    
    private void LoadData() {
        list.removeAll(list);
        String a = "T-Shirt";
        String b = "Acc";
        String c = "Tickets";
        String d = "Autres";
        String e = "VIP";
        list.addAll(a,b,c,d,e);
        TId_Cat.getItems().addAll(list);
    }
    
    public int cherchercat(String s ) throws SQLException
    {
        String req= " SELECT id FROM `categorie` WHERE (nomc like '"+s+"%')" ;
        
        Statement pstm = cnx.createStatement();
        ResultSet rst = pstm.executeQuery(req);
       
       rst.last();
       int nbrRow=rst.getRow();
       if (nbrRow != 0 )
       {
           int a = rst.getInt("id") ;
           return a ;}
       return 0 ;
       
    }
    
    @FXML
    private void addProduct(ActionEvent event)throws SQLException, IOException {
      cnx=MyDbConnection.getInstance().getConnexion();
      ste = cnx.createStatement();   
      Stock.Services.ProduitService SP= new ProduitService();
      Produit p = new Produit();
     
        String vName_Product = TName_Product.getText ();
        String vQuantity_Total = TQuantity_Total.getText ();
        String vPrice_Product = TPrice_Product.getText ();
        String vSize_Product  = TSize_Product.getText ();
        String vId_Cat = (String)TId_Cat.getValue();
        String vUrl = TUrl.getText ();
        String vEt = TEt.getText ();
        if (verifierchamps ()==true)
        {p.setNom_Produit(vName_Product);
         p.setQuantite_Totale(Integer.parseInt(vQuantity_Total));
         p.setPrix_Produit(Float.parseFloat(vPrice_Product));
         p.setTaille_Produit(vSize_Product);
         int i = cherchercat(vId_Cat);
         p.setId_Categorie(i);
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
        Parent tableview = FXMLLoader.load(getClass().getResource("/Stock/Graphique/ProductAdmin.fxml"));
        Scene sceneview = new Scene(tableview); 
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
         }              
         }
    
    @FXML
    private void back(ActionEvent event) throws IOException,SQLException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Stock/Graphique/Base.fxml")) ;
        Parent root = loader.load();
        Scene ascene = new Scene(root);
        Stage astage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        astage.setScene(ascene);
        astage.show();
    }
    
    @FXML
    private void home(ActionEvent event) throws IOException,SQLException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/tgt/Views/main.fxml")) ;
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
}
