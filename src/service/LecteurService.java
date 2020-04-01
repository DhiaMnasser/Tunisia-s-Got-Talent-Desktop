package service;

import beans.Upload;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LecteurService implements Initializable {
    //BD
    @FXML
    private TableView<Upload> table;

    @FXML
    private TableColumn<Upload, Integer> id;

    @FXML
    private TableColumn<Upload, String> titre;

    @FXML
    private TableColumn<Upload, String> auteur;

    @FXML
    private TableColumn<Upload, String> categorie ;

    @FXML
    private Button b_description;

    public ObservableList<Upload> data = FXCollections.observableArrayList();



    //BD End
    Connection connexion;
    PreparedStatement pstm=null;
    PreparedStatement pstm1=null;
    ResultSet rs=null;
    int currentUser;

    public LecteurService(){
        connexion = BdJDBC.getInstance().getConnexion();
    }

    //affichage
    private MediaPlayer mediaPlayer;

    private String filePath;

    @FXML
    private Button b_voter;

    @FXML
    private MediaView mediaView;

    @FXML
    private Slider slider;

    @FXML
    private Slider seeSlider;

    @FXML
    private Label l_description;

    //affichage End

    @FXML
    private void close(ActionEvent event){
        System.exit(0);
    }



    private void afficherPublication() throws SQLException {
        String query = "SELECT pub.id,pub.titre,pub.categorie,pub.description,u.username FROM publication pub JOIN user u ON pub.auteur=u.id ";

        pstm=connexion.prepareStatement(query);
        rs=pstm.executeQuery();


        while(rs.next()){
            data.add(new Upload(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(5)));
        }

        id.setCellValueFactory(new PropertyValueFactory<Upload, Integer>("id"));
        titre.setCellValueFactory(new PropertyValueFactory<Upload, String>("titre"));
        auteur.setCellValueFactory(new PropertyValueFactory<Upload, String>("auteur"));
        categorie.setCellValueFactory(new PropertyValueFactory<Upload, String>("categorie"));

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        table.setItems(data);


    }

    @FXML
    private void descriptionPublication() {
        ObservableList<Upload> selectRows = null,allVideo;
        allVideo = table.getItems();

        selectRows = table.getSelectionModel().getSelectedItems();
        Upload upload1=selectRows.get(0);

        l_description.setText(upload1.getTitre());


    }

    //recuperer l'id du user connecté a laide de son nom dans un fichier
    public int idCurrentUser(String urlFile) throws  FileNotFoundException {
        String query = "SELECT id FROM user WHERE username=?";
        int res=1;

        try {
            pstm=connexion.prepareStatement(query);
            pstm.setString(1,Utilitaire.readFileString(urlFile));

            rs=pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return res;
    }


    //voter
    public void voteAction() throws SQLException, FileNotFoundException {
        ObservableList<Upload> selectRows = null,allVideo;
        allVideo = table.getItems();

        selectRows = table.getSelectionModel().getSelectedItems();

        String query = "INSERT INTO vote(publication_id,user_id)  VALUES (?,?)";

        pstm1=connexion.prepareStatement(query);

        Upload upload=selectRows.get(0);
        pstm1.setInt(1,upload.getId());
        pstm1.setInt(2,idCurrentUser("C:/Users/gth/IdeaProjects/tgt_my_part/src/service/currentUser.txt"));

        /*for(Upload upload: selectRows){
            pstm.setInt(1,upload.getId());
            pstm.setInt(2,1);
        }*/
        pstm1.executeUpdate();
        Utilitaire.showDialog("Vote Effectué",null,"Success");
        b_voter.setDisable(true);

    }


    @FXML
    private  void handleButtonAction(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Selectionner une video (*.mp4)","*.mp4");
            fileChooser.getExtensionFilters().add(filter);
            File file = fileChooser.showOpenDialog(null);
            filePath =  file.toURI().toString();
            System.out.println(filePath);

            if(filePath != null){
                Media media = new Media(filePath);
                mediaPlayer = new MediaPlayer(media);
                mediaView.setMediaPlayer(mediaPlayer);
                    DoubleProperty width = mediaView.fitWidthProperty();
                    DoubleProperty height = mediaView.fitHeightProperty();

                    width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
                    height.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));

                    slider.setValue(mediaPlayer.getVolume() * 100);
                    slider.valueProperty().addListener(new InvalidationListener() {
                        @Override
                        public void invalidated(Observable observable) {
                            mediaPlayer.setVolume(slider.getValue()/100);

                        }
                    });
                    mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                        @Override
                        public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                            seeSlider.setValue(newValue.toSeconds());
                        }
                    });

                    seeSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            mediaPlayer.seek(Duration.seconds(seeSlider.getValue()));
                        }
                    });

                mediaPlayer.play();
            }


    }

    @FXML
    private void pauseVideo(ActionEvent event){
        mediaPlayer.pause();
    }

    @FXML
    private void playVideo(ActionEvent event){
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }

    @FXML
    private void stopVideo(ActionEvent event){
        mediaPlayer.stop();
    }

    @FXML
    private void fastVideo(ActionEvent event){
        mediaPlayer.setRate(1.5);
    }

    @FXML
    private void fasterVideo(ActionEvent event){
        mediaPlayer.setRate(2);
    }

    @FXML
    private void slowVideo(ActionEvent event){
        mediaPlayer.setRate(.75);
    }

    @FXML
    private void slowerVideo(ActionEvent event){
        mediaPlayer.setRate(.5);
    }

    @FXML
    private void volumeVideo(ActionEvent event){
        mediaPlayer.volumeProperty();
    }


    @FXML
    private void exitVideo(ActionEvent event){
        System.exit(0);
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            afficherPublication();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM user u JOIN vote v ON v.user_id=u.id WHERE u.username=?";


        //recuperation de l'utilisateur courant
        try {
            pstm = connexion.prepareStatement(sql);
            pstm.setString(1,Utilitaire.readFileString("C:/Users/gth/IdeaProjects/tgt_my_part/src/service/currentUser.txt"));
            rs=pstm.executeQuery();
            if(rs.next()){
                b_voter.setDisable(true);
            }
            System.out.println(Utilitaire.readFileString("C:/Users/gth/IdeaProjects/tgt_my_part/src/service/currentUser.txt"));
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }



    }

}
