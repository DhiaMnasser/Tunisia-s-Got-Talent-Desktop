/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assistance.Views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Assistance.Entities.Avis;
import Assistance.Services.AvisService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * FXML Controller class
 *
 * @author frauDEee
 */
public class PromotionsAdminController implements Initializable {

    @FXML
    private JFXButton menupro;
    @FXML
    private JFXButton menustat;
    @FXML
    private JFXButton menuord;
    @FXML
    private JFXButton menuev;
    @FXML
    private JFXButton menupos;
    @FXML
    private JFXButton menulog;
    @FXML
    private JFXTextField obj;
    @FXML
    private JFXTextArea cont;
    @FXML
    private JFXButton send;
    private double xOffset = 0;
        private double yOffset = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
   
   
   
    @FXML
    private void Send(ActionEvent event) throws SQLException {
        String diff = null;
        AvisService as = new AvisService();
    int i = 0;
        List<Avis> alist = as.getAllAvis();
        ListIterator<Avis> it = alist.listIterator();
        diff="hassen.benabid@esprit.tn";
         while(it.hasNext())
         {diff=diff+" ,"+it.next().getEmail();i++;}
         System.out.println(diff);
         
       
        String t=cont.getText();
        String o=obj.getText();
        final String username = "tunisiangt@gmail.com";
        final String password = "Gamer4ever";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("tunisiangt@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(diff)
            );
            message.setSubject(o);
            message.setText(t);

            Transport.send(message);

            System.out.println("Done");
              
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Status");
 
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("E-mail successfully sent to "+i+" users");
 
        alert.showAndWait();
    

        } catch (MessagingException e) {
            e.printStackTrace();
        }
         
      }

    @FXML
    private void Gorev(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AvisAdmin.fxml"));
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

    @FXML
    private void Attach(ActionEvent event) {
        
    }
        
    
    
}
