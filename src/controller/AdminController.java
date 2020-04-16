
  package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import Entities.Personne;
import Services.PersonneService;
import Services.Usercourant;
import UI.MainInterface;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Leonardo
 */
public class AdminController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPass;
    @FXML
    private TextField txtId;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnSaver;
    @FXML
    private TableView<Personne> tableView;
    @FXML
    private TextField txtRech;
    @FXML
    private Button btnPesquisar;
     @FXML
    private Button btnRetour;
    Personne p = new Personne();
    PersonneService ps = new PersonneService();
    List<Personne> list = new ArrayList();
    ObservableList<Personne> pList = FXCollections.observableArrayList();

    @Override
   public void initialize(URL url, ResourceBundle rb) {        createColomn();

       atualiser();
    }

    @FXML
    private void btnSaveAction(ActionEvent event) {
        if((txtName!=null) && (txtEmail!=null) && (txtPass!=null)){
            Personne p = new Personne(txtName.getText(),txtEmail.getText(),txtPass.getText()); 
           
        
        
         ps.ajouterPersonne(p);
            setNull();
            atualiser();
            System.out.println("ajout");
          
           
           
           
           
           
           
           }else{           
           JOptionPane.showMessageDialog(null,"veuillez remplir les champs");
                                 
        }}
   

    @FXML
    private void btnNewAction(ActionEvent event) {
       setNull();
    }

    @FXML
    private void btnRemoverAction(ActionEvent event) {
        ps.remove(p);
        setNull();
        atualiser();
    }

    @FXML
    private void btnRechercheAction(ActionEvent event) {
        rechercheTable(txtRech.getText());
        
    }

    @FXML
    private void actualiserFormulaire(MouseEvent event) {
        setFormulaire();
    }

    public void createColomn() {
        TableColumn<Personne, Integer> tcId = new TableColumn("ID");
        TableColumn<Personne, String> tcNome = new TableColumn("Name");
        TableColumn<Personne, Integer> tcQuantidade = new TableColumn("Email");
        TableColumn<Personne, Double> tcPreco = new TableColumn("Password");

        tableView.getColumns().addAll(tcId, tcNome, tcQuantidade, tcPreco);

       tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcQuantidade.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcPreco.setCellValueFactory(new PropertyValueFactory<>("password"));

        // estudar esse código
        tcId.prefWidthProperty().bind(tableView.prefWidthProperty().multiply(0.15));
        tcNome.prefWidthProperty().bind(tableView.prefWidthProperty().multiply(0.5));
        tcQuantidade.prefWidthProperty().bind(tableView.prefWidthProperty().multiply(0.15));
        tcPreco.prefWidthProperty().bind(tableView.prefWidthProperty().multiply(0.2));

        for (Personne per : ps.getAllPersonnes()) {
            if(!(per.getUsername().equals(Usercourant.ok.getUsername()))){
            pList.add(per);
            tableView.setItems(pList);
            }
        }
    }

    public void atualiser() {

        tableView.getItems().clear();

       for (Personne per : ps.getAllPersonnes()) {
            if(!(per.getUsername().equals(Usercourant.ok.getUsername()))){
            pList.add(per);
            tableView.setItems(pList);
            }
        }
    }
    public void rechercheTable(String desc) {

        tableView.getItems().clear();

        for (Personne per : ps.readPesquisar(desc)) {
              if(!(per.getUsername().equals(Usercourant.ok.getUsername()))){
            pList.add(per);
            tableView.setItems(pList);
              }
        }
    }

    public void setFormulaire() {

        p = tableView.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf(p.getId()));
        txtName.setText(p.getUsername());
        txtEmail.setText(String.valueOf(p.getEmail()));
        txtPass.setText(p.getPassword());

    }

    public void setNull() {
        p = null;
        txtId.setText(null);
        txtName.setText(null);
        txtEmail.setText(null);
        txtPass.setText(null);
    }
    
 @FXML
    private void btnRetourAction(ActionEvent event) {
         Stage stage = (Stage) btnRetour.getScene().getWindow();
                stage.close();
                MainInterface m = new MainInterface();
                m.start(stage);
    }
}
  

