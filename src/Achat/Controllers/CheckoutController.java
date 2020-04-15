/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Achat.Controllers;

import Achat.Entities.Panier;
import Achat.Entities.StripePayment;
import Achat.Services.PanierService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.stripe.model.Charge;
import com.stripe.model.Token;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Klaizer
 */
public class CheckoutController implements Initializable {

    //  TODO complete the connected user
    int currentUser = 12;
    String userMail = "mohameddhia.mnasser@esprit.tn";
    

    @FXML
    private TextField tel;
    @FXML
    private TextField address;
    @FXML
    private AnchorPane anc;
    @FXML
    private Label openHomeBtn;
    @FXML
    private Label openPanierBtn;
    @FXML
    private Label openStoreBtn;
    @FXML
    private Label openCommandeBtn;
    @FXML
    private JFXTextField numeroCarte;
    @FXML
    private JFXTextField MoisValidite;
    @FXML
    private JFXTextField AnneeValidite;
    @FXML
    private JFXPasswordField ccvTextField;
    @FXML
    private JFXButton btnValider;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private ImageView imgValidationPanier;
    @FXML
    private ImageView imgLivraison;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void commander() throws IOException, SQLException {

//        FXMLLoader userLoader = new FXMLLoader(getClass().getResource("/Khrouf/Views/User.fxml"));
//        Parent root = (Parent)userLoader.load();
//        UserController userController =  userLoader.getController();
//        User currentUser = userController.getConnectedUser();
        PanierService pans = new PanierService();

//      TODO replace UserID
//      Panier panier = pans.getPanierByUser(User.getId());
        Panier panier = pans.getPanierByUser(currentUser);

        FXMLLoader commandeLoader = new FXMLLoader(getClass().getResource("/Achat/Views/Commande.fxml"));
        Parent root = (Parent) commandeLoader.load();
        CommandeController commandeController = commandeLoader.getController();

        commandeController.ajouterCommande(panier, address.getText(), tel.getText());
        System.out.println("ajouterCommande is called");
        tgt.Entities.SendEmailTLS.sendCommandeConfrimationMail(userMail,panier);


    }

    @FXML

    private void openPanier(MouseEvent event) {
        try {
            Parent panierPage = FXMLLoader.load(getClass().getResource("/Achat/Views/Panier.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(panierPage));
            stage.setTitle("Panier");
        } catch (IOException ex) {
            Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML

    private void openStore(MouseEvent event) {
        try {
            Parent storePage = FXMLLoader.load(getClass().getResource("/Stock/Services/Produit.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(storePage));
            stage.setTitle("Store");
        } catch (IOException ex) {
            Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML

    private void openHome(MouseEvent event) {
        try {
            Parent homePage = FXMLLoader.load(getClass().getResource("/tgt/Views/main.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(homePage));
            stage.setTitle("Main");
        } catch (IOException ex) {
            Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML

    private void openCommande(MouseEvent event) {
        try {
            Parent commandePage = FXMLLoader.load(getClass().getResource("/Achat/Views/Commande.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(commandePage));
            stage.setTitle("Commande");
        } catch (IOException ex) {
            Logger.getLogger(CheckoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void validerFunction(ActionEvent event) throws SQLException, IOException {
        try {
            int mois = Integer.parseInt(MoisValidite.getText());
            int annee = Integer.parseInt(AnneeValidite.getText());
            PanierService pans = new PanierService();

//      TODO replace UserID
//      Panier panier = pans.getPanierByUser(User.getId());
            Panier panier = pans.getPanierByUser(currentUser);
            System.out.println("payment controller : " + panier);

            

            if (tel.getText().length() != 8) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert");
                alert.setContentText("numero Tel incorrect !");
                alert.setHeaderText(null);
                alert.showAndWait();

            } else if (address.getText().length() < 5) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert");
                alert.setContentText("adresse incorrecte !");
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {

                Token token = StripePayment.getToken("pk_test_VkxHIqxNUhztx7sLrBe14vNu00HVIf29N2", numeroCarte.getText(), mois, annee, ccvTextField.getText(), userMail);

                if (token != null) {
//                TODO change amount by the real Panier Prix Total
//                TODO change UserMail By Real User mail
                    Double amount = panier.getPrixTotal() * 100;

                    Charge ch = StripePayment.ChargePayement("rk_test_oGfrFNOjpnRPklUVzjelPHgf", "usd", "tok_visa", amount, "sk_test_lE9pTHIXFMFcZr7CZvTS33wM00fIb8c2WL", numeroCarte.getText(), mois, annee, ccvTextField.getText(), userMail);
                    String tit = "Paiement réussi";
                    String message = "Votre paiement a été traité avec succès";
                    System.out.println(message);
//            NotificationType notification = NotificationType.SUCCESS;

//            TrayNotification tray = new TrayNotification(tit, message, notification);          
//            tray.setAnimationType(AnimationType.POPUP);
//            tray.showAndDismiss(javafx.util.Duration.seconds(2));
//        HomeController.afficherprofile=1;
//        ProfileController.affichercommandes=1;
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Achat/Views/Commande.fxml"));
//        AnchorPane root = (AnchorPane) loader.load();
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         stage.setScene(new Scene(loader));
//         stage.setTitle("Panier");
//       sendEmail();
//        email.getScene().setRoot(root);
//      t3ayet lel fonction showCommande ta3 controller le5er
                    commander();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Votre Commande est en Cours de traitement !");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                    Parent commandePage = FXMLLoader.load(getClass().getResource("/Achat/Views/Commande.fxml"));
                    anc.getChildren().clear();
                    anc.getChildren().add(commandePage);
                    anc.toFront();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Alert");
                    alert.setContentText("carte invalide !");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            }

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alert");
            alert.setContentText("carte invalide !");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

    }

    @FXML
    private void AnnulerFunction(ActionEvent event) {
        try {
            Parent panierPage = FXMLLoader.load(getClass().getResource("/Achat/Views/Panier.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(panierPage));
            stage.setTitle("Checkout");
        } catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
