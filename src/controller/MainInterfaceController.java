package controller;


import Services.Usercourant;
import UI.Admin;
import UI.Aprofile;
import UI.MainInterface;
import UI.UProfile;
import UI.User;
import UI.login;
    import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class MainInterfaceController implements Initializable {


@FXML
    private Button btnSetting;
@FXML
    private Button btnProfile;
    @FXML
    private Button b_close;

    @FXML
    private void exitAction(ActionEvent event){
        System.exit(0);
    }
    
    
    

    @FXML
    private  void region(ActionEvent event) throws IOException {
         if(Usercourant.ok.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
        Parent uploadPage= FXMLLoader.load(getClass().getResource("/Controllers/AfficherAllR.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(uploadPage,1600, 737);
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount()==2){
                    stage.setFullScreen(true);
                }
            }
        });

        stage.setScene(scene);
         }
    }

    @FXML
    private void evenement(ActionEvent event) throws IOException {
        if(Usercourant.ok.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
        Parent uploadPage= FXMLLoader.load(getClass().getResource("/Controllers/AfficherAll.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        
        
        
        }
        else 
        {
            Parent uploadPage= FXMLLoader.load(getClass().getResource("/Controllers/interfaceuser.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        }
       
    }

    @FXML
    private void setting(ActionEvent event){
        if(Usercourant.ok.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
         Stage stage = (Stage) btnSetting.getScene().getWindow();
 
    stage.close();
   Admin l = new Admin();
    l.start(stage);    
        
        
    
       
    }else{
            Stage stage = (Stage) btnSetting.getScene().getWindow();
 
    stage.close();
   User l = new User();
    l.start(stage);    
          
        }}
 
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    @FXML
    private void btnRetourAction(ActionEvent event) {
         Stage stage = (Stage) btnProfile.getScene().getWindow();
                stage.close();
                if(Usercourant.ok.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
                 Aprofile m = new Aprofile();
                m.start(stage);
    }else{
                   UProfile m = new UProfile();
                m.start(stage);  
                }}
}

