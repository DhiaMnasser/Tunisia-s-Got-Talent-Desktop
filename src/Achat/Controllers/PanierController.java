/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Achat.Controllers;

import Achat.Entities.LigneCommande;
import Achat.Entities.Panier;
import Achat.Services.LigneCommandeService;
import Achat.Services.PanierService;
import Stock.Entities.Produit;
import Stock.Services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Klaizer
 */
public class PanierController implements Initializable {

    Text nomProduit;
    LigneCommandeService lcs = new LigneCommandeService();
    PanierService pans = new PanierService();
    ProduitService pdts;

//  TODO complete the connected user
    int currentUser = 12;

    @FXML
    private Button checkoutBtn;
    @FXML
    private Label openHomeBtn;
    @FXML
    private Label openPanierBtn;
    @FXML
    private Label openStoreBtn;
    @FXML
    private Label openCommandeBtn;
    @FXML
    private Text prixTotal;
    @FXML
    private GridPane grid;
    @FXML
    private ColumnConstraints rowProduit1;
    @FXML
    private ColumnConstraints rowPrix1;
    @FXML
    private ColumnConstraints rowQte1;
    @FXML
    private ColumnConstraints rowTotal1;
    private TextField rechercher;
    @FXML
    private AnchorPane anc;

    public PanierController() {
        this.pdts = new ProduitService();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        TODO 
        System.out.println("initialize panier");
        ObservableList<LigneCommande> lcList = FXCollections.observableArrayList();
        int i = 0;
        Double total = 0.0;
        try {

            Panier pan = pans.getPanierByUser(currentUser);
            System.out.println(pan);
            List<LigneCommande> ligneCmdList = lcs.getLigneCommandesByPanier(pan.getId());
            Iterator<LigneCommande> it = ligneCmdList.iterator();
            System.out.println(ligneCmdList);
            while (it.hasNext()) {
                System.out.println("entered while");
                LigneCommande lc = it.next();
                System.out.println(lc);
                Produit pdt = pdts.getById(lc.getIdproduit());
                System.out.println(pdt);
                System.out.println("produit : " + pdt);
                Text pname = new Text("" + lc.getNomProduit());
                Text pprix = new Text("" + pdt.getPrix_Produit() + " DT");
                Text pqte = new Text("" + lc.getQuantite());
                Text ptotal = new Text("" + pdt.getPrix_Produit() * lc.getQuantite() + " DT");

//                total+=pdt.getPrix_Produit() * lc.getQuantite();
                Button plus = new Button("+");
                Button moins = new Button("-");
                Button delete = new Button("x");

                plus.setOnAction((event) -> {
                    try {
                        lc.setQuantite(lc.getQuantite() + 1);
                        lcs.updateLigneCommande(lc, lc.getQuantite());
                        pans.updatePanier(pan, pan.getPrixTotal() + pdt.getPrix_Produit());

                        Parent root = FXMLLoader.load(getClass().getResource("/Achat/Views/Panier.fxml"));
                        grid.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });

                moins.setOnAction((event) -> {
                    try {
                        if (lc.getQuantite() > 1) {
                            lc.setQuantite(lc.getQuantite() - 1);
                            lcs.updateLigneCommande(lc, lc.getQuantite());
                            pans.updatePanier(pan, pan.getPrixTotal() - pdt.getPrix_Produit());

                        }
                        Parent root = FXMLLoader.load(getClass().getResource("/Achat/Views/Panier.fxml"));
                        grid.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });

                delete.setOnAction((event) -> {
                    try {
                        lcs.deleteLigneCommande(lc.getId());
                        pans.updatePanier(pan, pan.getPrixTotal() - lc.getQuantite() * pdt.getPrix_Produit());

                        Parent root = FXMLLoader.load(getClass().getResource("/Achat/Views/Panier.fxml"));
                        grid.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });

                grid.setAlignment(Pos.CENTER);
                grid.add(moins, 0, i);
                grid.add(plus, 1, i);
                grid.add(pname, 2, i);
                grid.add(pprix, 3, i);
                grid.add(pqte, 4, i);
                grid.add(ptotal, 5, i);
                grid.add(delete, 6, i);

                i++;

            }

//            pan.setPrixTotal(total);
            prixTotal.setText("" + pan.getPrixTotal());
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    fi partie ahmed hethi :  !!!!
    public void AjouterLigneCommande(Produit pdt) throws SQLException {

//        Produit pdt = pdts.getByName(nomProduit.getText());
//        Produit pdt1 = pdts.getByName(nomProduit);
        System.out.println("PanierController : ajouterLigneCommande  ");

        try {

            Panier panier = pans.getPanierByUser(currentUser);

            LigneCommande lgcmd = lcs.getLigneCommandeByPanierEtProduit(pdt.getId_Produit(), panier.getId());
            System.out.println("dans ajouterLigneCommande  " + lgcmd);
            if (lgcmd != null) {
                lgcmd.setQuantite(lgcmd.getQuantite() + 1);
                lcs.updateLigneCommande(lgcmd, lgcmd.getQuantite());

                System.out.println("lc exists");

            } else {
                LigneCommande lc = new LigneCommande(pdt.getId_Produit(),
                        pdt.getNom_Produit(),
                        1,
                        panier.getId());
                lgcmd = lc;
                lcs.addLigneCommande(lc);
                System.out.println("lc created");

            }

            double prixpdt = Double.parseDouble(Double.toString(pdt.getPrix_Produit()));
            double qte = Double.parseDouble(Integer.toString(lgcmd.getQuantite()));
            pans.updatePanier(panier, panier.getPrixTotal() + qte * prixpdt);
            System.out.println("LigneCommande Added");

//        LigneCommande lc = new LigneCommande(45, pdt1.getId_Produit(),pdt1.getNom_Produit(), 2, 55);
//        System.out.println("lc created");
//        lcs.addLigneCommande(lc);
//        System.out.println("lc added");
//            lcs.addLigneCommande(lc);
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void deleteLigneCommande(ActionEvent event) throws SQLException {
//        lcs.deleteLigneCommande(0);
    }

    @FXML
    private void openCheckout(MouseEvent event) throws SQLException {

        Panier pan = pans.getPanierByUser(currentUser);
        pans.updatePanier(pan, Double.parseDouble(prixTotal.getText()));

        if (pans.getPanierByUser(currentUser).getPrixTotal() != 0) {
            try {
                Parent panierPage = FXMLLoader.load(getClass().getResource("/Achat/Views/Checkout.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(panierPage));
                stage.setTitle("Checkout");
            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Alert");
            alert1.setContentText("Panier Vide");
            alert1.setHeaderText(null);
            alert1.show();
        }
    }

    @FXML
    private void openPanier(MouseEvent event) {
    }

    @FXML
    private void openStore(MouseEvent event) {
        try {
            Parent panierPage = FXMLLoader.load(getClass().getResource("/Stock/Graphique/ProductUser.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(panierPage));
            stage.setTitle("Store");
        } catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void openHome(MouseEvent event) {
        try {
            Parent panierPage = FXMLLoader.load(getClass().getResource("/tgt/Views/main.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(panierPage));
            stage.setTitle("Main");
        } catch (IOException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void rechercherProduit(ActionEvent event) {

        System.out.println("rechercher Produit ");
        ObservableList<LigneCommande> lcList = FXCollections.observableArrayList();
        int i = 0;
        Double total = 0.0;
        try {

            Panier pan = pans.getPanierByUser(currentUser);
//            System.out.println(pan);
            List<LigneCommande> ligneCmdList = lcs.chercherLigneCommande(rechercher.getText(), pan.getId());
            Iterator<LigneCommande> it = ligneCmdList.iterator();
            System.out.println(ligneCmdList);
            while (it.hasNext()) {
                System.out.println("entered while");
                LigneCommande lc = it.next();
                System.out.println(lc);
                Produit pdt = pdts.getById(lc.getIdproduit());
                System.out.println("produit : " + pdt);
                Text pname = new Text("" + lc.getNomProduit());
                Text pprix = new Text("" + pdt.getPrix_Produit() + " DT");
                Text pqte = new Text("" + lc.getQuantite());
                Text ptotal = new Text("" + pdt.getPrix_Produit() * lc.getQuantite() + " DT");

                total += pdt.getPrix_Produit() * lc.getQuantite();
                Button plus = new Button("+");
                Button moins = new Button("-");
                Button delete = new Button("x");

                grid.setAlignment(Pos.CENTER);
                grid.add(moins, 0, i);
                grid.add(plus, 1, i);

                grid.add(pname, 2, i);

                grid.add(pprix, 3, i);
                grid.add(pqte, 4, i);
                grid.add(ptotal, 5, i);
                grid.add(delete, 6, i);

                i++;

            }
            pan.setPrixTotal(total);
            prixTotal.setText("" + pan.getPrixTotal());
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
