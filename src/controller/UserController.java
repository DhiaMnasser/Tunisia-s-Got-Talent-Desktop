/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Entities.Personne;
import Services.PersonneService;
import Services.Usercourant;
import UI.MainInterface;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author mohamed khrouf
 */
public class UserController implements Initializable{
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtEmail;
     @FXML
    private TextField txtPass;
     @FXML
    private Button btnRetour;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         
       txtUser.setText(Usercourant.ok.getUsername());
        txtEmail.setText(Usercourant.ok.getEmail());
        
    }

    public void  btnEditAction(){
        if(!(txtEmail.getText().isEmpty()) && !(txtUser.getText().isEmpty())&&!(txtPass.getText().isEmpty())){
        PersonneService ps = new PersonneService();
      Personne p = ps.recherche(Usercourant.ok.getUsername());
      p.setUsername(txtUser.getText());
      p.setEmail(txtEmail.getText());
      p.setPassword(txtPass.getText());
      ps.modifier(p);
     Usercourant.ok= ps.recherche(Usercourant.ok.getId());
      
    }else{
          JOptionPane.showMessageDialog(null,"les champs sont vides");     
        }
    }
    @FXML
    private void btnRetourAction(ActionEvent event) {
         Stage stage = (Stage) btnRetour.getScene().getWindow();
                stage.close();
                MainInterface m = new MainInterface();
                m.start(stage);
    }
}
