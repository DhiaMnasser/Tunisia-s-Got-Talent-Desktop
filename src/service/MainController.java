package service;


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
import java.util.ResourceBundle;


public class MainController implements Initializable {



    @FXML
    private Button b_close;

    @FXML
    private void exitAction(ActionEvent event){
        System.exit(0);
    }

    @FXML
    private  void publicationAction(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("/view/lecteur.fxml"));

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
        stage.setTitle("Les Prestations");
    }

    @FXML
    private void sendVideo(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("/view/upload.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        stage.setTitle("Ajouter Ma Video");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
