/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.Personne;
import Services.PersonneService;
import Services.Usercourant;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author mohamed khrouf
 */
public class Code extends Application {
    Stage window;
    Personne p=null;
     private String code;
      public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
         window=primaryStage;
       window.setTitle("Entrer ");
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));
        Text welcomeTxt=new Text("Code de Confirmation");
        welcomeTxt.setFont(Font.font("Tahoma",FontWeight.LIGHT,25));
        grid.add(welcomeTxt, 0, 0);
       
        TextField txtcode = new TextField();
        txtcode.setPromptText("code");
        grid.add(txtcode, 0, 1); 
       
      
         Button loginBtn=new Button("confirm");
         grid.add(loginBtn, 1, 3);
         loginBtn.setOnAction(e->{
             PersonneService ps = new PersonneService();
           
          if(txtcode.getText().equals(this.p.getConfirmation_token())){
             
               ps.ajouterPersonne(p);
               Usercourant.ok=p;
               window.close();
            
           Stage s = new Stage();
             UProfile a =new UProfile();
           a.start(s);
          
          
          
          }else{
               JOptionPane.showMessageDialog(null,"votre code est incorrect veiller réessayer");
          }
          
         });
       
         Scene scene = new Scene(grid,500,500);
         window.setScene(scene);
         window.show();
        
    }
    public String getCode(){
        return this.code;
    }
    public void get(Personne p){
    this.p=p    ;
    }
}
