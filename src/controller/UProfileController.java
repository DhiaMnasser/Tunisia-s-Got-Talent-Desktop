/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Services.Usercourant;
import UI.MainInterface;
import UI.login;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author mohamed khrouf
 */
public class UProfileController implements Initializable{
    @FXML
    private Label txtName;
    
    @FXML
    private Label txtEmail;
    
      @FXML
    private Button btnDeco;
        @FXML
    private Button btnMenu;
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        txtName.setText(Usercourant.ok.getUsername());
        txtEmail.setText(Usercourant.ok.getEmail());
    }
     public  void btnDecoAction() {
        Stage stage = (Stage) btnDeco.getScene().getWindow();
  Usercourant.ok=null;
    stage.close();
    login l = new login();
    l.start(stage);
    }
     public  void btnMenuAction() {
     Stage stage = (Stage) btnMenu.getScene().getWindow();
     
       MainInterface mi = new MainInterface();
    mi.start(stage);
    }
}
