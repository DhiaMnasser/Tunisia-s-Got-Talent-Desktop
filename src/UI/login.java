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
import javax.servlet.http.HttpSession;
import UI.Register;
import java.lang.Object;


import javax.swing.JOptionPane;
/**
 *
 * @author mohamed khrouf
 */
public class login extends Application {
    
    Stage window;
    Personne b;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
       window=primaryStage;
       window.setTitle("Login Form window");
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));
        Text welcomeTxt=new Text("Welcome");
        welcomeTxt.setFont(Font.font("Tahoma",FontWeight.LIGHT,25));
        grid.add(welcomeTxt, 0, 0);
        Label lblUser=new Label("Username:");
        grid.add(lblUser,0,1);
        
        TextField txtUser = new TextField();
        txtUser.setPromptText("username");
        grid.add(txtUser, 1, 1); 
        Label lblPassword=new Label("password:");
        grid.add(lblPassword,0,2);
        
        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("password");
        grid.add(pwBox, 1, 2);
         Button loginBtn=new Button("Login");
         grid.add(loginBtn, 1, 3);
         loginBtn.setOnAction((ActionEvent e)->{
             
             PersonneService ps = new PersonneService();
             
             System.out.println(ps.checkLog(txtUser.getText(),pwBox.getText()));
            Personne p=ps.recherche(txtUser.getText());
            if(ps.checkLog(txtUser.getText(),pwBox.getText()).equals("connexion réussie")){
                Usercourant.ok=p;
                
                window.close();
            if(p.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
            Stage s = new Stage();
            Admin a = new Admin();
            a.get(p);
           a.start(s);
            
            }
            else if(p.getRoles().equals("a:1:{i:0;s:9:\"ROLE_USER\";}")){
            Stage s = new Stage();
            User u = new User();
            u.get(p);
           u.start(s);}
                
            }
         });
          Button registerBtn=new Button("Register");
         grid.add(registerBtn, 2, 3);
         registerBtn.setOnAction(e->{
              Stage stage = new Stage();
        new Register().start(stage);
           
           
         });
         Scene scene = new Scene(grid,500,500);
         window.setScene(scene);
         window.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
       public Personne getPersonne(){
           return this.b;
       }
}
