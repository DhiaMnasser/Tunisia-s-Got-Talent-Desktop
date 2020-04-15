/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Personne;
import Services.PersonneService;
import UI.Code;
import UI.Register;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author mohamed khrouf
 */
public class registerController {
    @FXML
    private Button registerBtn;
     @FXML
    private TextField  txtUser;
     @FXML
    private TextField  txtEmail;
     @FXML
    private PasswordField  pwBox;
       @FXML
    private PasswordField  pwBox1;
         @FXML
    private  void register(ActionEvent event) {
        if(!(txtEmail.getText().isEmpty()) && !(txtUser.getText().isEmpty())&&!(pwBox.getText().isEmpty())&&!(pwBox1.getText().isEmpty())){
   if(pwBox.getText().equals(pwBox1.getText())){
              Personne p = new Personne(txtUser.getText(),txtEmail.getText(),pwBox.getText());
              
               PersonneService ps =new PersonneService();
               if(ps.recherche(p.getUsername())==null){
              ps.sendCode(p);
              Code c = new Code();
              c.get(p);
              Stage s = new Stage();
              c.start(s);
               }
               else{
                  JOptionPane.showMessageDialog(null,"nom utilisateur deja utilisé");  
               }
        
       }
       else{
            JOptionPane.showMessageDialog(null,"erreur de saisie de password");
       }
          
        }else{
             JOptionPane.showMessageDialog(null,"champs vides");
        }      
              
              
        
    }

 
}
