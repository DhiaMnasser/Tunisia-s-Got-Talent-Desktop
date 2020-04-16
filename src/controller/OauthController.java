/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import controller.NewFXMain;
/**
 *
 * @author mohamed khrouf
 */
public class OauthController implements Initializable {
@FXML
    private Button btnOauth;
@FXML
    private Label message;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
   
    @FXML
    private  void btnOauthAction(ActionEvent event){
       String domain = "https://localhost/";
        String appId = "1337934136390673";
       String secret ="18eeef85db3780495cfdc44592756d41";
       String fields="public_profile,email";
   
       OAuthFacebookAuthenticator authFB = new OAuthFacebookAuthenticator(appId, domain, secret,fields);
       authFB.startLogin();
  
        
    
       
        
       
       
    }


}

    


             

