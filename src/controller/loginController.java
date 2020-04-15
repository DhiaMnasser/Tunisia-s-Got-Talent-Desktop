/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Personne;
import Services.PersonneService;
import Services.Usercourant;
import UI.Aprofile;
import UI.Register;
import UI.UProfile;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author mohamed khrouf
 */
public class loginController {
     @FXML
    private Button loginBtn;
     @FXML
    private Button registerBtn;
     @FXML
    private TextField  txtUser;
     @FXML
    private PasswordField  pwBox;

   
    
    
    @FXML
    private  void login(ActionEvent event)  {
        if (!(txtUser.getText().isEmpty()) && !(pwBox.getText().isEmpty())){
  PersonneService ps = new PersonneService();
             
             System.out.println(ps.checkLog(txtUser.getText(),pwBox.getText()));
            Personne p=ps.recherche(txtUser.getText());
            if(ps.checkLog(txtUser.getText(),pwBox.getText()).equals("connexion réussie")){
                Usercourant.ok=p;
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                stage.close();
            if(p.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
            Stage s = new Stage();
           
            Aprofile u = new Aprofile();
            
            
           u.start(s);}
          
    
    else if(p.getRoles().equals("a:1:{i:0;s:9:\"ROLE_USER\";}")){
            Stage s = new Stage();
             UProfile a =new UProfile();
          
           a.start(s);
                
            }}else{
               JOptionPane.showMessageDialog(null,ps.checkLog(txtUser.getText(), pwBox.getText()));   
            }
    }else{
            JOptionPane.showMessageDialog(null," champs vides");  
        }
    }
     @FXML
    private  void register(ActionEvent event) {
    Stage stage = new Stage();
        new Register().start(stage);
        
    }

 
}
