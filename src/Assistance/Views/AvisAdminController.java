/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assistance.Views;

import Assistance.Entities.Avis;
import Assistance.Services.AvisService;
import Assistance.Views.ReplyController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author frauDEee
 */
public class AvisAdminController implements Initializable {

    
    @FXML
    private TableView<Avis> table;
    private TableColumn<Integer, Avis> col_id;
    @FXML
    private TableColumn<Integer, Avis> col_userid;
    @FXML
    private TableColumn<String, Avis> col_review;
    @FXML
    private TableColumn<Date, Avis> col_date;
    @FXML
    private TableColumn<String, Avis> col_email;
    @FXML
    private TableColumn<Integer, Avis> col_rating;
    
    private double xOffset = 0;
        private double yOffset = 0;
    private final ObservableList<Avis> dataList = FXCollections.observableArrayList();
    @FXML
    private JFXButton repbutton;
    @FXML
    private JFXButton delbutton;
    @FXML
    private JFXTextField keyword;
    @FXML
    private JFXButton menupro;
 
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         AvisService as = new AvisService();
         try
    {
        List<Avis> alist = as.getAllAvis();
        System.out.println(alist);
       // col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_userid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        col_review.setCellValueFactory(new PropertyValueFactory<>("texte"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ObservableList<Avis> oblist = FXCollections.observableArrayList(alist);
        
        table.setItems(oblist);
     /*   FilteredList<Avis> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		keyword.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Avis avis) -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(avis.getId()).indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (avis.getTexte().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (String.valueOf(avis.getDate()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (avis.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(avis.getRating()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Avis> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);*/
    }catch (SQLException e)
    {
        e.printStackTrace();
    }
    }    
    
    
    
   private void inittable()
   {
       AvisService as = new AvisService();
         try
    {
        List<Avis> alist = as.getAllAvis();
        System.out.println(alist);
      //  col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_userid.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        col_review.setCellValueFactory(new PropertyValueFactory<>("texte"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        ObservableList<Avis> oblist = FXCollections.observableArrayList(alist);
        table.setItems(oblist);
    }catch (SQLException e)
    {
        e.printStackTrace();
    }
   }
                
    
    
    @FXML
   private void DeleteRow(ActionEvent event) throws SQLException
    {
      Avis selected = table.getSelectionModel().getSelectedItem();
      int id= selected.getId();
      AvisService as = new AvisService();
      try
    {
        as.deleteAvis(id);
        inittable();
        
    }catch (SQLException e)
    {
        e.printStackTrace();
    
      
      
        
    }}
   
    @FXML
   private void Min(MouseEvent event)
   {
     Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
     s.setIconified(true);
   }
   
    @FXML
   private void Max(MouseEvent event)
   {
     Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
     s.setFullScreen(true);
   }
   
   @FXML
   private void Close(MouseEvent event)
   {
     Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
     s.close();
   }

    @FXML
    private void Reply(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Reply.fxml"));
Parent root1 = (Parent) fxmlLoader.load();
Avis selected = table.getSelectionModel().getSelectedItem();
String S = selected.getEmail();
ReplyController rc =fxmlLoader.getController();
rc.myfun(S);
Stage stage = new Stage();
stage.initStyle(StageStyle.TRANSPARENT);

root1.setOnMousePressed(e -> {
            xOffset = stage.getX() - e.getScreenX();
            yOffset = stage.getY() - e.getScreenY();
        });
        root1.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() + xOffset);
            stage.setY(e.getScreenY() + yOffset);
        });
stage.setScene(new Scene(root1));  
stage.show();


    }

    @FXML
    
    private void Gopro(ActionEvent event) throws IOException {
        
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PromotionsAdmin.fxml"));
Parent root1 = (Parent) fxmlLoader.load();
Stage stage = new Stage();
stage.initStyle(StageStyle.TRANSPARENT);

root1.setOnMousePressed(e -> {
            xOffset = stage.getX() - e.getScreenX();
            yOffset = stage.getY() - e.getScreenY();
        });
        root1.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() + xOffset);
            stage.setY(e.getScreenY() + yOffset);
        });
stage.setScene(new Scene(root1));  
stage.show();
Stage sc = (Stage) ((Node)event.getSource()).getScene().getWindow();
sc.close();
        
    }
   
}   