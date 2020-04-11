/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique;

import Entities.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import TGT.MyDbConnection;
import Services.ProduitService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import java.net.URL;
import java.sql.Timestamp;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.text.Document;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.BufferedOutputStream;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import javafx.print.PrinterJob;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ProductUserController implements Initializable {

    @FXML
    private TextField names;
    @FXML
    private Pane pane;
    @FXML
    private TableView<Produit> tableview;
    @FXML
    private ImageView Img;
     @FXML
    private TableColumn<Produit, Integer> tcId_Product;
    @FXML
    private TableColumn<Produit, String> tcName_Product;
    @FXML
    private TableColumn<Produit, Integer> tcQuantity_Total;
    @FXML
    private TableColumn<Produit, Integer> tcId_Cat;
    @FXML
    private TableColumn<Produit, Float> tcPrice_Product;
    @FXML
    private TableColumn<Produit, String> tcEt_Product;
    @FXML
    private TableColumn<Produit, String> tcSize;
    
     ObservableList<Produit> listproduct  = FXCollections.observableArrayList();
      Services.ProduitService SP= new ProduitService();
    private Statement ste;
    private Connection cnx;
    @FXML
    private Button Buy;
    
    @FXML
    private TextField search;
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
         tcId_Product.setCellValueFactory(new PropertyValueFactory<>("Id_Produit"));
         tcId_Cat.setCellValueFactory(new PropertyValueFactory<>("Id_Categorie"));
         tcName_Product.setCellValueFactory(new PropertyValueFactory<>("Nom_Produit"));         
         tcQuantity_Total.setCellValueFactory(new PropertyValueFactory<>("Quantite_Totale"));
         tcPrice_Product.setCellValueFactory(new PropertyValueFactory<>("Prix_Produit"));
         tcEt_Product.setCellValueFactory(new PropertyValueFactory<>("Etat_Produit"));         
         tcSize.setCellValueFactory(new PropertyValueFactory<>("Taille_Produit"));
        
         tableview.setItems(listproduct);
         tableview.setEditable(true);
         tcId_Product.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
         tcId_Cat.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
         tcName_Product.setCellFactory(TextFieldTableCell.forTableColumn());
         tcQuantity_Total.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
         tcPrice_Product.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
         tcEt_Product.setCellFactory(TextFieldTableCell.forTableColumn());
         tcSize.setCellFactory(TextFieldTableCell.forTableColumn()); 
        
    }

    public TableColumn<Produit, String> getTcName_Product() {
        return tcName_Product;
    }
       
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     Aff();
       
            Produit productselected = tableview.getSelectionModel().getSelectedItem();
            

        RechercheAV(); 
    }

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
    private void addCommand(ActionEvent event) throws IOException,SQLException{
        Parent tableview = FXMLLoader.load(getClass().getResource("AddCommandUser.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); }
    
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
    
    @FXML
    private void Show(ActionEvent event) throws IOException,SQLException {
         pane.getChildren().clear();
         String name = names.getText();
         Image Image = new Image("@../image/"+name+".png");
         javafx.scene.image.ImageView Img= new javafx.scene.image.ImageView(Image);
         pane.getChildren().add(Img);  
    }
  
/*  
    @FXML
    private void importer(ActionEvent event) throws MalformedURLException {
        
        BufferedOutputStream stream = null;
	String globalPath="C:\\wamp64\\www\\pijava";
                
        try {
        
        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {         
            
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getName();
            
            Path p = selectedFile.toPath();      
            byte[] bytes = Files.readAllBytes(p); 
            File dir = new File(globalPath);
            
            File serverFile = new File(dir.getAbsolutePath()+File.separator + path);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            
            String path2 = selectedFile.toURI().toURL().toString();
            Image image = new Image(path2);
            ImageView.

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("NoData");
        }
        
                } catch (IOException ex) {
            Logger.getLogger(ProductUserController.class.getName()).log(Level.SEVERE, null, ex);}        
}
*/
}
   
  

    

