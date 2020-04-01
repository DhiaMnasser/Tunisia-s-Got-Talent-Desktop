package service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import javax.media.*;
import java.io.IOException;

public class PublicationService {

    private boolean closing= false;
    private Player player= null;
    private static String arg1;
    private MediaPlayer mediaPlayer;
    private Media media;

    @FXML
    private Pane pane_video;

    @FXML
    private Group groupe;

    @FXML
    private Button b_look;

    @FXML
    private MediaView currentVideo;

    @FXML
    private Label l_back;

    @FXML
    private Label l_close;



    public void regarderAction(ActionEvent event) {
    }

    public void publicationServicesAction(MouseEvent event) throws IOException {
        if(event.getSource()==l_back){
            Parent uploadPage= FXMLLoader.load(getClass().getResource("/view/sample.fxml"));

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(uploadPage, 861, 731));
            stage.setTitle("Welcome!");
        }
        if(event.getSource()==l_close){
            System.exit(0);
        }
    }

}
