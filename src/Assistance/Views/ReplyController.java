/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assistance.Views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
//import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * FXML Controller class
 *
 * @author frauDEee
 */
public class ReplyController implements Initializable {

    @FXML
    private Label Label;
    @FXML
    private JFXTextArea txta;
    @FXML
    private JFXButton Sendbtn;
    @FXML
    private FontAwesomeIcon closebutton;
    @FXML
    private FontAwesomeIcon minimizebutton;
    @FXML
    private FontAwesomeIcon maximizebutton;
    @FXML
    private JFXTextField obj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    
    public void myfun(String s)
    {
        Label.setText(s);
    }

    @FXML
    private void Send(ActionEvent event) {
        String s=Label.getText();
        String t=txta.getText();
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
                    InternetAddress.parse(s)
            );
            message.setSubject(o);
            message.setText(t);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    
        
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
    
    
}
