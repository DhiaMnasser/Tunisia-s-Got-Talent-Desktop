/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entities.Personne;
import Services.PersonneService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Register extends Application {
     private Stage window;
    private  Personne p;
      public static void main(String[] args) {
        launch(args);
    }
   
    @Override
    public void start(Stage primaryStage) {
      window=primaryStage;
       window.setTitle("Register Form window");
        GridPane grid=new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));
        Text welcomeTxt=new Text("Register");
        welcomeTxt.setFont(Font.font("Tahoma",FontWeight.LIGHT,25));
        grid.add(welcomeTxt, 0, 0);
        Label lblUser=new Label("Username:");
        grid.add(lblUser,0,1);
        
        TextField txtUser = new TextField();
        txtUser.setPromptText("username");
        grid.add(txtUser, 1, 1); 
        
         Label lblEmail=new Label("Email");
        grid.add(lblEmail,0,2);
        
        TextField txtEmail = new TextField();
        txtUser.setPromptText("email");
        grid.add(txtEmail, 1, 2); 
        
        Label lblPassword=new Label("password:");
        grid.add(lblPassword,0,3);
        
        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("password");
        grid.add(pwBox, 1, 3);
        
        Label lblPassword1=new Label("repeat password:");
        grid.add(lblPassword1,0,4);
        
        PasswordField pwBox1 = new PasswordField();
        pwBox1.setPromptText("repeat password");
        grid.add(pwBox1, 1, 4);
       
          Button registerBtn=new Button("Register");
         grid.add(registerBtn, 2, 4);
         registerBtn.setOnAction(e->{
             
             
       
          
       if(pwBox.getText().equals(pwBox1.getText())){
              this.p = new Personne(txtUser.getText(),txtEmail.getText(),pwBox.getText());
               PersonneService ps =new PersonneService();
              ps.sendCode(p);
              Code c = new Code();
              c.get(p);
              Stage s = new Stage();
              c.start(s);
        
       }
       else{
            JOptionPane.showMessageDialog(null,"erreur de saisie de password");
       }
          
             
              
              
     
              
              
          
 
             
         });
         Scene scene = new Scene(grid,500,500);
         window.setScene(scene);
         window.show();
    }

 
    /**
     * @param args the command line arguments
     */
  
    
}
