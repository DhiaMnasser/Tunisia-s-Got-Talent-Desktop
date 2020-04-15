package tgt.Controllers;


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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class MainController implements Initializable {

    @FXML
    private ImageView panierBtn;
    @FXML
    private ImageView commandeBtn;
    @FXML
    private Circle openStore;



    private void exitAction(ActionEvent event){
        System.exit(0);
    }

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

    private void sendVideo(ActionEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("/view/upload.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(uploadPage, 861, 731));
        stage.setTitle("Ajouter Ma Video");
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void openPanier(MouseEvent event) {
         try {
            Parent panierPage = FXMLLoader.load(getClass().getResource("/Achat/Views/Panier.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(panierPage));
            stage.setTitle("Panier");
        } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void openCommande(MouseEvent event) {
        try {
            Parent panierPage = FXMLLoader.load(getClass().getResource("/Achat/Views/Commande.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(panierPage));
            stage.setTitle("Commandes");
        } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   @FXML
    private  void openStore(MouseEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("/Stock/Graphique/Base.fxml"));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(uploadPage);
        scene.setOnMouseClicked((MouseEvent event1) -> {
            if (event1.getClickCount() == 2) {
                stage.setFullScreen(true);
            }
        });

        stage.setScene(scene);
        stage.setTitle("Store");
    }

}
