package service;



import beans.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;

public class UserService {
    Connection connexion;
    PreparedStatement pstm=null;
    ResultSet rs=null;
    //ActionEvent event;


    @FXML
    private Label l_close;

    @FXML
    private Label l_error;

    @FXML
    private TextField username;


    @FXML
    private TextField password;

    @FXML
    private Button b_login;

    @FXML
    private Button b_upload;

    public UserService(){
        connexion = BdJDBC.getInstance().getConnexion();
    }



    public int addUser(User u) throws SQLException {

        String query = "INSERT INTO user(username,email,password) VALUES(?,?,?)";

        pstm=connexion.prepareStatement(query);

        pstm.setString(1,u.getUsername());
        pstm.setString(2,u.getEmail());
        pstm.setString(3,u.getPassword());

        pstm.executeUpdate();

        return 0;
    }

    public void registerAction(MouseEvent event) throws SQLException, IOException {
        if(event.getSource() == l_close){
            System.exit(0);
        }

        if(event.getSource() == b_upload){
            /*if(loginAction().equals("Success")){
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/upload.fxml")));
                stage.setScene(scene);
                stage.show();
            }*/
        }

        if(event.getSource() == b_upload){
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/upload.fxml")));
                stage.setScene(scene);
                stage.show();
        }

    }
    public void retour(ActionEvent event) throws IOException {
        Parent previousPage= FXMLLoader.load(getClass().getResource("test.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(previousPage, 800, 600));
        stage.setTitle("Next Page");
    }
    public void uploadAction(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("/view/upload.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        stage.setTitle("Ajouter Ma Video");
    }

    public void mainAction(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("/view/main.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 908, 757));
        stage.setTitle("Menu");
    }

    public void publicationListAction(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("/view/lecteur.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(uploadPage,1600, 737);
        scene.setFill(Color.TRANSPARENT);
        stage.setX(0);
        stage.setY(0);
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



    public String loginAction(ActionEvent event) throws SQLException, IOException {

        String res="Echec";
        String uname=username.getText().toString();
        String pw=password.getText().toString();

        String query = "SELECT * FROM user WHERE username= ?  AND password= ?";

        pstm=connexion.prepareStatement(query);

        pstm.setString(1,uname);
        pstm.setString(2,pw);

        rs= pstm.executeQuery();

        if(!rs.next()){
            l_error.setText("Vos coordonnées sont incorrectes!");
        }
        else {
            l_error.setTextFill(Color.GREEN);
            Parent uploadPage= FXMLLoader.load(getClass().getResource("/view/main.fxml"));

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(uploadPage, 908, 757));
            stage.setTitle("Menu");
            l_error.setText("Succès... Redirection");
            Utilitaire.writterFile(uname,"C:/Users/gth/IdeaProjects/tgt_my_part/src/service/currentUser.txt");
            res="Success";
        }

        return  res;


    }







}
