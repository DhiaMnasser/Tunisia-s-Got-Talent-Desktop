/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock.Graphique;

import Stock.Entities.Produit;
import tgt.MyDbConnection;
import Stock.Services.ProduitService;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import java.awt.AWTException;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert.AlertType;
/**
 * FXML Controller class
 *
 * @author Haddad
 */
public class ProductAdminController implements Initializable {
 Stock.Services.ProduitService SP= new ProduitService();
    private Statement ste;
    private Connection cnx;
    @FXML
    private BarChart<String, Number> Barchart;
    @FXML
    private TextField search;
    @FXML
    private Button addProduct;
    @FXML
    private Button deleteProduct;
    @FXML
    private Button export;
    @FXML
    private Button sendmail;
    @FXML
    private TableColumn<Produit, String> tcName_Product;
    @FXML
    private TableColumn<Produit, Integer> tcId_Product;
    @FXML
    private TableColumn<Produit, Integer> tcQuantity_Total;
    @FXML
    private TableColumn<Produit, Float> tcPrice_Product;
    @FXML
    private TableColumn<Produit, String> tcSize_Product;
    @FXML
    private TableColumn<Produit, String> tcEt_Product;
    @FXML
    private TableColumn<Produit, Integer> tcId_Cat;
    @FXML
    private TableColumn<Produit, String> tcUrl;
    @FXML
    private TableView<Produit> tableview;
     ObservableList<Produit> listproduct  = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
     
       public void Aff(){
        try {
        cnx=MyDbConnection.getInstance().getConnexion();
        ste = cnx.createStatement();
        listproduct.clear();
        for(Produit p: SP.getProduct())
        listproduct.add(p);
        } catch (SQLException ex) {
        }
         tcId_Cat.setCellValueFactory(new PropertyValueFactory<>("Id_Categorie"));
         tcName_Product.setCellValueFactory(new PropertyValueFactory<>("Nom_Produit"));         
         tcQuantity_Total.setCellValueFactory(new PropertyValueFactory<>("Quantite_Totale"));
         tcPrice_Product.setCellValueFactory(new PropertyValueFactory<>("Prix_Produit"));
         tcEt_Product.setCellValueFactory(new PropertyValueFactory<>("Etat_Produit"));         
         tcSize_Product.setCellValueFactory(new PropertyValueFactory<>("Taille_Produit"));
         tcUrl.setCellValueFactory(new PropertyValueFactory<>("Url"));
         tableview.setItems(listproduct);
         tableview.setEditable(true);
         tcId_Cat.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
         tcName_Product.setCellFactory(TextFieldTableCell.forTableColumn());         
         tcQuantity_Total.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
         tcPrice_Product.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
         tcEt_Product.setCellFactory(TextFieldTableCell.forTableColumn());        
         tcSize_Product.setCellFactory(TextFieldTableCell.forTableColumn());
         tcUrl.setCellFactory(TextFieldTableCell.forTableColumn());
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                     Aff();
       
            Produit productselected = tableview.getSelectionModel().getSelectedItem();
            

        RechercheAV();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Courbes de Stock");
        try {
         series.getData().add(new XYChart.Data<>("Total Stock", SP.TotalProduct()));
        } catch (SQLException ex) {
         Logger.getLogger(ProductAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
         series.getData().add(new XYChart.Data<>("Total prix",SP.TotalPrise()));
        } catch (SQLException ex) {
         Logger.getLogger(ProductAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Barchart.getData().addAll(series);
    }    

    @FXML
    private void addProduct(ActionEvent event) throws IOException,SQLException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Stock/Graphique/AddProduct.fxml")) ;
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
    private void deleteProduct() throws SQLException,AWTException {
    tableview.setItems(listproduct);
             ObservableList<Produit> allProducts,SingleDemandes ;
             allProducts=tableview.getItems();
             SingleDemandes=tableview.getSelectionModel().getSelectedItems();
             Produit p = SingleDemandes.get(0);
             ProduitService SP = new ProduitService(); 
             Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation delete : "+p.getNom_Produit());
             alert.setHeaderText("this confirmation about delet");
             alert.setContentText("are you sure to delete??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
               SP.deleteProduct(p.getId_Produit());
               SingleDemandes.forEach(allProducts::remove);
             }else{System.out.println("Cancel");}  
             RechercheAV(); }
    
    
     public void RechercheAV(){
       FilteredList<Produit> filteredData = new FilteredList<>(listproduct, b -> true);
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(product -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}				
				String lowerCaseFilter = newValue.toLowerCase();
				if (product.getNom_Produit().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (String.valueOf(product.getPrix_Produit()).indexOf(lowerCaseFilter)!=-1) {
					return true; 
				}
				else
				
				if (String.valueOf(product.getId_Produit()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<Produit> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		
		tableview.setItems(sortedData);
    }
  
   
    @FXML
     private void update_Name(TableColumn.CellEditEvent bb) throws SQLException {
     Produit productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setNom_Produit(bb.getNewValue().toString());
     Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");}
    }

    @FXML
     private void update_Quantity_Total(TableColumn.CellEditEvent b) throws SQLException {
     Produit productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setQuantite_Totale(Integer.parseInt(b.getNewValue().toString()));
       Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");}
    }

    @FXML
     private void update_Price_Product(TableColumn.CellEditEvent b) throws SQLException {
     Produit productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setPrix_Produit(Float.parseFloat(b.getNewValue().toString()));
       Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");}}

    @FXML
     private void update_Size_Product(TableColumn.CellEditEvent b) throws SQLException {
     Produit productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setTaille_Produit(b.getNewValue().toString());
       Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");}}

    @FXML
     private void update_Et(TableColumn.CellEditEvent b) throws SQLException {
     Produit productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setEtat_Produit((b.getNewValue().toString()));
       Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");}}

    @FXML
     private void update_Id_Cat(TableColumn.CellEditEvent b) throws SQLException {
     Produit productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setId_Categorie(Integer.parseInt(b.getNewValue().toString()));
     Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");}}
    
    @FXML
     private void update_Id_Product(TableColumn.CellEditEvent b) throws SQLException {
     Produit productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setId_Produit(Integer.parseInt(b.getNewValue().toString()));
     Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");}}

    @FXML
     private void update_Url(TableColumn.CellEditEvent b) throws SQLException {
     Produit productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setUrl(b.getNewValue().toString());
        Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");} }
     
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
        loader.setLocation(getClass().getResource("/tgt/Views/Usermain.fxml")) ;
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
