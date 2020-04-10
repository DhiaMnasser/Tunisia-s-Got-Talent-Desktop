package Controllers;


import Entities.Evenement;
import Services.EvenementService;
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
    private Button b_close;

    @FXML
    private void exitAction(ActionEvent event){
        System.exit(0);
    }
    
    
    

    @FXML
    private  void region(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("AfficherAllR.fxml"));

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

    @FXML
    private void evenement(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("AfficherAll.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        
        
        
    
       
    }

    
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
