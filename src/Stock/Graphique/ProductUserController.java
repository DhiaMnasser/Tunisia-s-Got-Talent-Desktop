/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock.Graphique;

import Achat.Controllers.PanierController;
import Achat.Entities.LigneCommande;
import Achat.Services.LigneCommandeService;
import Stock.Entities.Categorie;
import Stock.Entities.Produit;
import tgt.MyDbConnection;
import Stock.Services.ProduitService;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author Haddad
 */
public class ProductUserController implements Initializable {
    ObservableList list=FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> categories; 
    @FXML
    private TextField names; 
    @FXML
    private TableView<Produit> tableview;
    @FXML
    private ImageView Img;
     @FXML
    private TableColumn<Produit, Integer> tcId_Product;
    @FXML
    private TableColumn<Produit, String> tcName_Product;
    @FXML
    private TableColumn<Produit, Integer> tcQuantity_Total;
    @FXML
    private TableColumn<Produit, Integer> tcId_Cat;
    //@FXML
    //private TableColumn<Categorie, String> tcNom_Cat;
    @FXML
    private TableColumn<Produit, Float> tcPrice_Product;
    @FXML
    private TableColumn<Produit, String> tcEt_Product;
    @FXML
    private TableColumn<Produit, String> tcSize;
    
    ObservableList<Produit> listproduct  = FXCollections.observableArrayList();
    Stock.Services.ProduitService SP= new ProduitService();
    private Statement ste;
    private Connection cnx;
    @FXML
    private Button Buy;
    private Produit selectedid;
    @FXML
    private TextField search;
    /**
     * Initializes the controller class.
     */
       public void Aff(){
           String catn = null;
        try {
        cnx=MyDbConnection.getInstance().getConnexion();
        ste = cnx.createStatement();
        listproduct.clear();
        for(Produit p: SP.getProduct())
        {
            catn = SP.getNCat(p.getId_Categorie());
            listproduct.add(p);
        }
        } catch (SQLException ex) {
        }
         tcId_Cat.setCellValueFactory(new PropertyValueFactory<>("Id_Categorie"));
         tcName_Product.setCellValueFactory(new PropertyValueFactory<>("Nom_Produit"));         
         tcQuantity_Total.setCellValueFactory(new PropertyValueFactory<>("Quantite_Totale"));
         tcPrice_Product.setCellValueFactory(new PropertyValueFactory<>("Prix_Produit"));
         tcEt_Product.setCellValueFactory(new PropertyValueFactory<>("Etat_Produit"));         
         tcSize.setCellValueFactory(new PropertyValueFactory<>("Taille_Produit"));
        
         tableview.setItems(listproduct);
         tableview.setEditable(true);
         //tcId_Cat.setCellFactory(TextFieldTableCell.forTableColumn());
         tcId_Cat.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
         tcName_Product.setCellFactory(TextFieldTableCell.forTableColumn());
         tcQuantity_Total.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
         tcPrice_Product.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
         tcEt_Product.setCellFactory(TextFieldTableCell.forTableColumn());
         tcSize.setCellFactory(TextFieldTableCell.forTableColumn()); 
        
    }
       public void AffCat(int id){
           String catn;
        try {
        cnx=MyDbConnection.getInstance().getConnexion();
        ste = cnx.createStatement();
        listproduct.clear();
        for(Produit p: SP.getByCat(id))
        listproduct.add(p);
        } catch (SQLException ex) {
        }
         //tcId_Cat.setCellValueFactory(new PropertyValueFactory<>("Nomcat"));
         tcId_Cat.setCellValueFactory(new PropertyValueFactory<>("Id_Categorie"));
         tcName_Product.setCellValueFactory(new PropertyValueFactory<>("Nom_Produit"));         
         tcQuantity_Total.setCellValueFactory(new PropertyValueFactory<>("Quantite_Totale"));
         tcPrice_Product.setCellValueFactory(new PropertyValueFactory<>("Prix_Produit"));
         tcEt_Product.setCellValueFactory(new PropertyValueFactory<>("Etat_Produit"));         
         tcSize.setCellValueFactory(new PropertyValueFactory<>("Taille_Produit"));
        
         tableview.setItems(listproduct);
         tableview.setEditable(true);
         //tcId_Cat.setCellFactory(TextFieldTableCell.forTableColumn());
         tcId_Cat.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
         tcName_Product.setCellFactory(TextFieldTableCell.forTableColumn());
         tcQuantity_Total.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
         tcPrice_Product.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
         tcEt_Product.setCellFactory(TextFieldTableCell.forTableColumn());
         tcSize.setCellFactory(TextFieldTableCell.forTableColumn()); 
        
    }

    public TableColumn<Produit, String> getTcName_Product() {
        return tcName_Product;
    }
       
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               Aff();

     Produit productselected = tableview.getSelectionModel().getSelectedItem();

     LoadData();
     RechercheAV(); 
    }

      public void RechercheAV(){
       FilteredList<Produit> filteredData = new FilteredList<>(listproduct, b -> true);		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(product -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}				
				String lowerCaseFilter = newValue.toLowerCase();
				if (product.getNom_Produit().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (String.valueOf(product.getPrix_Produit()).indexOf(lowerCaseFilter)!=-1) {
					return true; 
				}
				else
				
				if (String.valueOf(product.getId_Produit()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; 
			});
		});
				
		SortedList<Produit> sortedData = new SortedList<>(filteredData);		
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		tableview.setItems(sortedData);
    }    

    @FXML
    private void addCommand(ActionEvent event) throws IOException,SQLException{
        Parent tableview = FXMLLoader.load(getClass().getResource("AddCommandUser.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); }
    
    @FXML
    private void back(ActionEvent event) throws IOException,SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Stock/Graphique/Base.fxml")) ;
        Parent root = loader.load();
        Scene ascene = new Scene(root);
        Stage astage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        astage.setScene(ascene);
        astage.show();
    }
    
    @FXML
    private void Show(ActionEvent event) throws IOException,SQLException {
        selectedid = (Produit) tableview.getSelectionModel().getSelectedItem();
        TextField names = new TextField(selectedid.getNom_Produit());
        Image image = new Image("file:///C:/wamp64/www/BitDevTGT/web/"+selectedid.getUrl());//wamp64\\www\\BitDevTGT\\web\\"+selectedid.getUrl());
        Img.setImage(image);
        javafx.scene.image.ImageView Img= new javafx.scene.image.ImageView(image);
    }
    
    @FXML
    private void home(ActionEvent event) throws IOException,SQLException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/tgt/Views/main.fxml")) ;
        Parent root = loader.load();
        Scene ascene = new Scene(root);
        Stage astage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        astage.setScene(ascene);
        astage.show();
    }
    @FXML
    private void prod(ActionEvent event) throws IOException,SQLException {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Stock/Graphique/ProductUser.fxml")) ;
        Parent root = loader.load();
        Scene ascene = new Scene(root);
        Stage astage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        astage.setScene(ascene);
        astage.show();
    }
    
    @FXML
    private void filter(ActionEvent event) throws IOException,SQLException {
        String choice = categories.getValue();
        switch(choice)
        {
            case "Tshirt":
                AffCat(1);
            break;
            case "Acc":
                AffCat(2);
            break;
            case "Tickets":
                AffCat(3);
            break;
            case "Autres":
                AffCat(4);
            break;
            case "VIP":
                AffCat(5);
            break;
            default:
                Aff();
            break;
        } 
        RechercheAV();
    }
    private void LoadData() {
        list.removeAll(list);
        String all = "All";
        String a = "Tshirt";
        String b = "Acc";
        String c = "Tickets";
        String d = "Autres";
        String e = "VIP";
        list.addAll(all,a,b,c,d,e);
        categories.getItems().addAll(list);
    }
    
   @FXML
    private void acheterProduit(ActionEvent event) throws SQLException, IOException {
       try {
            cnx = MyDbConnection.getInstance().getConnexion();
            ProduitService pdts = new ProduitService();


            Produit pdt = tableview.getSelectionModel().getSelectedItem();
           
            
            FXMLLoader panierLoader = new FXMLLoader(getClass().getResource("/Achat/Views/Panier.fxml"));
            Parent root = (Parent) panierLoader.load();
            PanierController panierController = panierLoader.getController();
            panierController.AjouterLigneCommande(pdt);  
            
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Alert");
                    alert1.setContentText("Produit Ajoute Au Panier");
                    alert1.setHeaderText(null);
                    alert1.show();
        } catch (IOException iOException) {
            iOException.getMessage();
        }
              
    }
      
        public String getNCat(int id) throws SQLException {
          
          String p = null;
          Statement stm = cnx.createStatement();
          String requete = " SELECT nomc FROM `categorie` WHERE `id`= '"+id+"'" ;
          ResultSet rst = stm.executeQuery(requete);

            if (rst.next())
            {p=rst.getString("nomc");
            }
        return p ;
            }
       
    }
    
       /*try {
            cnx = MyDbConnection.getInstance().getConnexion();
            LigneCommandeService lcs = new LigneCommandeService();
            ProduitService pdts = new ProduitService();

            Produit pdt = new Produit();
            String nomPdt = tcName_Product.getText();

            LigneCommande lg = new LigneCommande();
            lg.setIdPanier(55);
            lg.setIdproduit(pdt.getId_Produit());
            lg.setNomProduit(pdt.getNom_Produit());
            lg.setQuantite(1);
            lcs.addLigneCommande(lg);
            //message.setText("Pdt ajoutee aux panier");
            
            FXMLLoader panierLoader = new FXMLLoader(getClass().getResource("/Achat/Views/Panier.fxml"));
            Parent root = (Parent) panierLoader.load();
            PanierController panierController = panierLoader.getController();
            panierController.AjouterLigneCommande(pdt);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Panier");      
            
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Alert");
                    alert1.setContentText("Produit Ajoute Au Panier");
                    alert1.setHeaderText(null);
                    alert1.show();
        } catch (SQLException sQLException) {
            sQLException.getMessage();
        } catch (IOException iOException) {
            iOException.getMessage();
        }
              
        

    }
         
        /* pane.getChildren().clear();
         String name = names.getText();
        // Image Image = new Image("C:\\Users\\Haddad\\Documents\\NetBeansProjects\\TGT\\src\\Image\\"+name+".png");
                  Image Image = new Image("C:\\Users\\Haddad\\Documents\\NetBeansProjects\\TGT\\src\\Image\\proda.png");

         javafx.scene.image.ImageView Img= new javafx.scene.image.ImageView(Image);
         pane.getChildren().add(Img); 
        
        } 
    
  
/*  C:\Users\Haddad\Documents\NetBeansProjects\TGT\src\Image\proda.png
    @FXML
    private void importer(ActionEvent event) throws MalformedURLException {
        
        BufferedOutputStream stream = null;
	String globalPath="C:\\wamp64\\www\\pijava";
                
        try {
        
        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {         
            
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getName();
            
            Path p = selectedFile.toPath();      
            byte[] bytes = Files.readAllBytes(p); 
            File dir = new File(globalPath);
            
            File serverFile = new File(dir.getAbsolutePath()+File.separator + path);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            
            String path2 = selectedFile.toURI().toURL().toString();
            Image image = new Image(path2);
            ImageView.

        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("NoData");
        }
        
                } catch (IOException ex) {
            Logger.getLogger(ProductUserController.class.getName()).log(Level.SEVERE, null, ex);}        
}
*/

   
  

    

