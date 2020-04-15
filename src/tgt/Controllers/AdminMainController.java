/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tgt.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Klaizer
 */
public class AdminMainController implements Initializable {

    @FXML
    private Circle openStore;
    @FXML
    private ImageView commandeBtn;
    @FXML
    private ImageView panierBtn;
    @FXML
    private ImageView openBase;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private  void openStore(MouseEvent event) throws IOException {
        Parent uploadPage= FXMLLoader.load(getClass().getResource("/Stock/Graphique/ProductAdmin.fxml"));

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
       @FXML
    private void openCommande(MouseEvent event) {
        try {
            Parent panierPage = FXMLLoader.load(getClass().getResource("/Achat/Views/AdminCommandes.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(panierPage));
            stage.setTitle("Commandes");
        } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openPanier(MouseEvent event) {
    }

    @FXML
    private void openBase(MouseEvent event) throws IOException,SQLException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/tgt/Views/Base.fxml")) ;
        Parent root = loader.load();
        Scene ascene = new Scene(root);
        Stage astage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        astage.setScene(ascene);
        astage.show();
    }

    
}
