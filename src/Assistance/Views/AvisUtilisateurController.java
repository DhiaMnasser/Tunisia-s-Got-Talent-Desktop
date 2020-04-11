/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assistance.Views;

import Assistance.Entities.Avis;
import Assistance.Services.AvisService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.validation.RequiredFieldValidator;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import javafx.event.ActionEvent;
//import java.util.Date;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * FXML Controller class
 *
 * @author frauDEee
 */
public class AvisUtilisateurController implements Initializable {

    @FXML
    private JFXTextField textfield;
    
    @FXML
    private JFXComboBox<String> cbox;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXToggleButton tgl;
    private static final String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbox.getItems().add("* 1 Star");
        cbox.getItems().add("** 2 Stars");
        cbox.getItems().add("*** 3 Stars");
        cbox.getItems().add("**** 4 Stars");
        cbox.getItems().add("***** 5 Stars");
       // cbox.setFocusColor(Paint.valueOf("#a34"));
        // TODO
        
    }    

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
    
   // Show a Information Alert without Header Text
    private void showAlertWithoutHeaderText() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Tunisian Got Talents");
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Thank you for your review!");
 
        alert.showAndWait();
    }
   private void wronginput() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Tunisian Got Talents");
 
        // Header Text: null
        alert.setHeaderText("Fail");
        alert.setContentText("Please enter a valid e-mail adress!");
 
        alert.showAndWait();
    }
   
    private void wronginputtxt() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Tunisian Got Talents");
 
        // Header Text: null
        alert.setHeaderText("Fail");
        alert.setContentText("The review must contain more than 5 caracters!");
 
        alert.showAndWait();
    }
    
   private boolean emailisvalid (String s)
   {

  Pattern pattern = Pattern.compile(regex);
  Matcher matcher = pattern .matcher(s); 
  return matcher.matches();

   }
   
   
   @FXML
    private void SendReview(ActionEvent event) throws SQLException {
        
        if (textfield.getText().length()>5 && emailisvalid(email.getText()) )
        {
        
        
        Avis a= new Avis();
        String s = cbox.getSelectionModel().getSelectedItem();
        if (s.equals("* 1 Star")) {
        a.setRating(1);
}
else if (s.equals("** 2 Stars")) {
    a.setRating(2);
}
else if (s.equals("*** 3 Stars")) {
    a.setRating(3);
}
else if (s.equals("**** 4 Stars")) {
    a.setRating(4);
}
        else if (s.equals("***** 5 Stars")) {
            a.setRating(5);
}
        
        a.setUser_id(1);
        a.setTexte(textfield.getText());
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date date = new java.sql.Date(currentDate.getTime());
        a.setDate(date);
        a.setEmail(email.getText());
        AvisService as = new AvisService();
        
        as.addAvis(a);
       
        showAlertWithoutHeaderText() ;
        

        }
        else 
        {
        if (textfield.getText().length()<5)
                
                 { wronginputtxt() ;}
        
                      else {wronginput();}
        }
    }
   
}
