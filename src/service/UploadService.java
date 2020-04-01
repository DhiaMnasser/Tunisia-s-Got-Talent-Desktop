package service;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UploadService {

    Connection connexion;
    PreparedStatement pstm=null;
    ResultSet rs=null;
    Image video;
    private static int compt=0;

    private String filePath;
    private MediaPlayer mediaPlayer;


    @FXML
    private TextField vid_titre;

    @FXML
    private TextArea vid_des;

    @FXML
    private Button b_import;

    @FXML
    private Label l_lien;

    @FXML
    private ImageView img;

    @FXML
    private MediaView mediaView;

    @FXML
    private Button b_sendv;

    @FXML
    private Label l_error;

    private String lienVideo;



    public UploadService(){
        connexion = BdJDBC.getInstance().getConnexion();
    }

    @FXML
    private void exitAction(ActionEvent event){
        System.exit(0);
    }

    public void uploadAction(ActionEvent event){
        /*Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionne Ta video");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG","*.png"),
                new FileChooser.ExtensionFilter("JPEG","*.jpg"),
                new FileChooser.ExtensionFilter("MP4","*.mp4")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);

        Image OrigninalPhoto = new Image(selectedFile.toURI().toString());
        l_lien.setText(selectedFile.toURI().toString());

        img.setImage(OrigninalPhoto);

        video = new Image(selectedFile.toURI().toString());*/

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Selectionner une video (*.mp4)","*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        filePath =  file.toURI().toString();
        l_lien.setText(filePath);
        video = new Image(filePath);
        System.out.println(filePath);

        if(filePath != null){
            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);


            mediaPlayer.play();
        }

    }

    //enregister dans le dossier saveVideo
    public void saveDirectory(Image video,int nom) throws IOException {
        File output = new File("C:/Users/gth/IdeaProjects/tgt_my_part/src/view/saveVideo/"+nom+".mp4");
        lienVideo="C:/Users/gth/IdeaProjects/tgt_my_part/src/view/saveVideo/"+nom+".mp4";
        BufferedImage BI = SwingFXUtils.fromFXImage((Image)video,null);


        ImageIO.write(BI,"bmp",output);
    }


    public void saveVideoAction(ActionEvent event) throws SQLException, IOException {
        String res="Echec";
        compt=Utilitaire.readFileInt("C:/Users/gth/IdeaProjects/tgt_my_part/src/view/saveVideo/compteur.txt");

        //pour sauvegarder dans le dossier saveVideo
        saveDirectory(video,compt);

        String titre=vid_titre.getText().toString();
        String desc=vid_des.getText().toString();
        if(!((vid_des.getText() == null || vid_des.getText().trim().isEmpty()) ||
                (vid_titre.getText() == null || vid_titre.getText().trim().isEmpty()) ||
                (l_lien.getText() == null)))
        {


            String query = "INSERT INTO publication(titre,description,categorie,auteur,lien)  VALUES (?,?,?,?,?)";

            pstm=connexion.prepareStatement(query);

            pstm.setString(1,titre);
            pstm.setString(2,desc);
            pstm.setString(3,"Art");
            pstm.setInt(4,1);
            pstm.setString(5,lienVideo);

            pstm.executeUpdate();


            Utilitaire.showDialog("Bien Enregister",null,"Success");
            res="Success";
            compt++;

            Utilitaire.writterFile(""+compt,"C:/Users/gth/IdeaProjects/tgt_my_part/src/view/saveVideo/compteur.txt");
        }
        else {
            l_error.setText("Veillez Bien entrer les infos");
        }
    }


}
