/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock.Graphique;

import Stock.Entities.Categorie;
import tgt.MyDbConnection;
import Stock.Services.CategorieService;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
import java.awt.AWTException;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert.AlertType;
/**
 * FXML Controller class
 *
 * @author Haddad
 */
public class CategorieAdminController implements Initializable {
 Stock.Services.CategorieService SP= new CategorieService();
    private Statement ste;
    private Connection cnx;
    @FXML
    private BarChart<String, Number> Barchart;
    @FXML
    private TextField search;
    @FXML
    private Button addCat;
    @FXML
    private Button deleteCat;
    @FXML
    private TableColumn<Categorie, Integer> tcId_Cat;
    @FXML
    private TableColumn<Categorie, String> tcName_Cat;
    @FXML
    private TableView<Categorie> tableview;
     ObservableList<Categorie> listcat  = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
     
       public void Aff(){
        try {
        cnx=MyDbConnection.getInstance().getConnexion();
        ste = cnx.createStatement();
        listcat.clear();
        for(Categorie p: SP.getCategorie())
        listcat.add(p);
        } catch (SQLException ex) {
        }
         tcName_Cat.setCellValueFactory(new PropertyValueFactory<>("Nom_Categorie")); 
         tableview.setItems(listcat);
         tableview.setEditable(true);
         tcName_Cat.setCellFactory(TextFieldTableCell.forTableColumn());

        
    }
       
       
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            Aff();
            Categorie categorieselected = tableview.getSelectionModel().getSelectedItem();         
            RechercheAV();
    }

    @FXML
    private void addCat(ActionEvent event) throws IOException,SQLException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Stock/Graphique/AddCategorie.fxml")) ;
        Parent root = loader.load();
        Scene ascene = new Scene(root);
        Stage astage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        astage.setScene(ascene);
        astage.show();
    }
    
    @FXML
    private void back(ActionEvent event) throws IOException,SQLException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/tgt/Views/Base.fxml")) ;
        Parent root = loader.load();
        Scene ascene = new Scene(root);
        Stage astage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        astage.setScene(ascene);
        astage.show();
    }
   @FXML
    private void home(ActionEvent event) throws IOException,SQLException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/tgt/Views/AdminMain.fxml")) ;
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
      private void deleteCat() throws SQLException,AWTException {   
             tableview.setItems(listcat);
             ObservableList<Categorie> allCats,SingleDemandes ;
             allCats=tableview.getItems();
             tableview.getSelectionModel().clearSelection(0, tcName_Cat);
             SingleDemandes=tableview.getSelectionModel().getSelectedItems();
             Categorie c = SingleDemandes.get(0);
             CategorieService SC = new CategorieService(); 
             Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation delete : "+c.getNom_Categorie());
             alert.setHeaderText("this confirmation about delete");
             alert.setContentText("are you sure to delete??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
               SC.deleteCategorie(c.getId_Categorie());
               SingleDemandes.forEach(allCats::remove);
             }else{System.out.println("Cancel");}           
             RechercheAV(); }
      
     public void RechercheAV(){
       FilteredList<Categorie> filteredData = new FilteredList<>(listcat, b -> true);
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(cat -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				if (cat.getNom_Categorie().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} 
				else
				if (String.valueOf(cat.getId_Categorie()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<Categorie> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		
		tableview.setItems(sortedData);
    }
  
   
    @FXML
     private void update_Name(TableColumn.CellEditEvent bb) throws SQLException {
     Categorie catselected = tableview.getSelectionModel().getSelectedItem();
     catselected.setNom_Categorie(bb.getNewValue().toString());
     Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateCategorie(catselected);
             }else{System.out.println("Cancel");}
    }

 
   

    @FXML
     private void update_Id_Cat(TableColumn.CellEditEvent b) throws SQLException {
     Categorie catselected = tableview.getSelectionModel().getSelectedItem();
     catselected.setId_Categorie(Integer.parseInt(b.getNewValue().toString()));
     Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateCategorie(catselected);
             }else{System.out.println("Cancel");}}

    public TableColumn<Categorie, Integer> getTcId_Cat() {
        return tcId_Cat;
    }

    public TableColumn<Categorie, String> getTcName_Cat() {
        return tcName_Cat;
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
