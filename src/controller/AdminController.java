
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Leonardo
 */
public class AdminController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private TextField txtPreco;
    @FXML
    private TextField txtId;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnSalvar;
    @FXML
    private TableView<Personne> tableView;
    @FXML
    private TextField txtPesquisar;
    @FXML
    private Button btnPesquisar;
    Personne p = new Personne();
    PersonneService ps = new PersonneService();
    List<Personne> list = new ArrayList();
    ObservableList<Personne> pList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {        criarColuna();

        atualizarTableView();
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        if((txtNome!=null) && (txtQuantidade!=null) && (txtPreco!=null)){
            Personne p = new Personne(txtNome.getText(),txtQuantidade.getText(),txtPreco.getText()); 
           
        
        
         ps.ajouterPersonne(p);
            limparCampos();
            atualizarTableView();
            System.out.println("ajout");
          
           
           
           
           
           
           
           }else{           
           JOptionPane.showMessageDialog(null,"veuillez remplir les champs");
                                 
        }}
   

    @FXML
    private void btnNovoAction(ActionEvent event) {
        limparCampos();
    }

    @FXML
    private void btnRemoverAction(ActionEvent event) {
        ps.remove(p);
        limparCampos();
        atualizarTableView();
    }

    @FXML
    private void btnPesquisarAction(ActionEvent event) {
       pesquisarTable(txtPesquisar.getText());
    }

    @FXML
    private void atualizarFormularioRelease(MouseEvent event) {
        setFormulario();
    }

    public void criarColuna() {
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

        for (Personne p : ps.getAllPersonnes()) {
            if(!(p.getUsername().equals(Usercourant.ok.getUsername()))){
            pList.add(p);
            tableView.setItems(pList);
            }
        }
    }

    public void atualizarTableView() {

        tableView.getItems().clear();

       for (Personne p : ps.getAllPersonnes()) {
            if(!(p.getUsername().equals(Usercourant.ok.getUsername()))){
            pList.add(p);
            tableView.setItems(pList);
            }
        }
    }
    public void pesquisarTable(String desc) {

        tableView.getItems().clear();

        for (Personne p : ps.readPesquisar(desc)) {
              if(!(p.getUsername().equals(Usercourant.ok.getUsername()))){
            pList.add(p);
            tableView.setItems(pList);
              }
        }
    }

    public void setFormulario() {

        p = tableView.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf(p.getId()));
        txtNome.setText(p.getUsername());
        txtQuantidade.setText(String.valueOf(p.getEmail()));
        txtPreco.setText(p.getPassword());

    }

    public void limparCampos() {
        p = null;
        txtId.setText(null);
        txtNome.setText(null);
        txtQuantidade.setText(null);
        txtPreco.setText(null);
    }

}
  

