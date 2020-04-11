/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Services.PersonneService;
import TGT.MyDbConnection;
import java.util.Date;
import java.sql.Timestamp;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


 
public class Personne {
     private int id;
     private String username;
     private String username_canonical;
     private String email;
     private String email_canonical;
     private int enabled;
     private String Salt;
     private String password;
     private Timestamp last_login;
     private String confirmation_token;
     private Timestamp password_requested_at;
     private String roles;
     

    public Personne(String username, String email, String password)  {
        try {
            
     
   Connection connexion;
         connexion=MyDbConnection.getInstance().getConnexion();
       
         String req = "select * from fos_user";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
         while(result.next()){
           this.id = result.getInt("id");
   }
         
       this.id=this.id +1;
        this.username = username;
        this.username_canonical = username;
        this.email = email;
        this.email_canonical = email;
        this.enabled = 1;
        this.Salt = "";
        this.password = password ;
       int randomPIN = (int)(Math.random()*9000)+1000;
       java.util.Date date = new Date();
        Timestamp last = new java.sql.Timestamp(date.getTime());
	 
        this.last_login = last;
        this.confirmation_token = String.valueOf(randomPIN);
         Timestamp requested = new java.sql.Timestamp(date.getTime());
        this.password_requested_at = requested;
        this.roles = "a:1:{i:0;s:9:\"ROLE_USER\";}";
           } catch (SQLException e) {
               System.out.println(e);
        }
    }
    public Personne(int id,String username, String email, String password)  {
        try {
            
     
   Connection connexion;
         connexion=MyDbConnection.getInstance().getConnexion();
       
         String req = "select * from fos_user";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
         while(result.next()){
           this.id = result.getInt("id");
   }
         
       this.id=id ;
        this.username = username;
        this.username_canonical = username;
        this.email = email;
        this.email_canonical = email;
        this.enabled = 1;
        this.Salt = "";
        this.password = password ;
       int randomPIN = (int)(Math.random()*9000)+1000;
       java.util.Date date = new Date();
        Timestamp last = new java.sql.Timestamp(date.getTime());
	 
        this.last_login = last;
        this.confirmation_token = String.valueOf(randomPIN);
         Timestamp requested = new java.sql.Timestamp(date.getTime());
        this.password_requested_at = requested;
        this.roles = "a:1:{i:0;s:9:\"ROLE_USER\";}";
           } catch (SQLException e) {
               System.out.println(e);
        }
    }
    public Personne() {
       this.id=this.id +1;
     
        this.username_canonical = username;
     
        this.email_canonical = email;
        this.enabled = 1;
        this.Salt = "";
        
      
       java.util.Date date = new Date();
        Timestamp last = new java.sql.Timestamp(date.getTime());
	  int randomPIN = (int)(Math.random()*9000)+1000;
        this.last_login = last;
        this.confirmation_token = String.valueOf(randomPIN);
         Timestamp requested = new java.sql.Timestamp(date.getTime());
        this.password_requested_at = requested;
        this.roles = "a:1:{i:0;s:9:\"ROLE_USER\";}";
    }
  
    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Timestamp getLast_login() {
        return last_login;
    }

    public void setLast_login(Timestamp last_login) {
        this.last_login = last_login;
    }

    public int getEnabled() {
        return enabled;
    }
    public void setEnabled(int i){
        this.enabled=i;
    }

    public void disable() {
        this.enabled = 0;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    

  
    

    public int getId() {
        return id;
    }

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String Salt) {
        this.Salt = Salt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        PersonneService ps = new PersonneService();
        this.password = ps.hashPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Timestamp locked) {
        this.password_requested_at = locked;
    }

    @Override
    public String toString() {
       return "Personne : {" + "identifiant=" + this.id + ", nom=" + this.username + ", prenom=" + this.email+ '}'+ "\n"; 
    }

 

    

   
    
    
    

   
    
}
