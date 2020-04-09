/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assistance.Views;

import Assistance.Entities.Avis;
import Assistance.Services.AvisService;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    
}
