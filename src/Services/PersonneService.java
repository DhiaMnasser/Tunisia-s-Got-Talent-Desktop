/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.BCrypt;
import Entities.Personne;
import TGT.MyDbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
import java.util.Scanner;
import javamail.JavaMailUtil;

public class PersonneService {

   Connection connexion;
   

    public PersonneService() {
       connexion=MyDbConnection.getInstance().getConnexion();
    }
     public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(13);
        System.out.println(salt);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return (hashed_password);
    }

   public void ajouterPersonne(Personne p) throws Exception {
  try{      String requete="insert INTO fos_user(id,username,username_canonical,email,email_canonical,enabled,salt,password,last_login,confirmation_token,password_requested_at,roles) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        
           
            PreparedStatement pst= connexion.prepareStatement(requete);
             pst.setInt(1, p.getId());
             pst.setString(2, p.getUsername());
             pst.setString(3, p.getUsername_canonical());
             pst.setString(4, p.getEmail());
             pst.setString(5, p.getEmail_canonical());
             pst.setInt(6, p.getEnabled());
             pst.setString(7, p.getSalt());
             pst.setString(8, hashPassword(p.getPassword()));
             pst.setTimestamp(9, p.getLast_login());
             pst.setString(10, p.getConfirmation_token());
             pst.setTimestamp(11, p.getPassword_requested_at());
             pst.setString(12, p.getRoles());
  JavaMailUtil.sendMail(p.getEmail(),p.getConfirmation_token());
Scanner sc = new Scanner(System.in);

     String str = sc.nextLine();
           
           
   
     
          if(str.equals(p.getConfirmation_token())){
             pst.executeUpdate();
            System.out.println("Insertion réussie");
                  
          }else{
              System.out.println("Insertion echoué");
          }}catch(Exception ex){
              System.out.println(ex);
          }
     
    }

public void supprimerPersonne(int id) {
          String requete="delete from fos_user where id="+id;
       Statement st;
        try {
            st = connexion.createStatement();
              st.executeUpdate(requete);
              System.out.println("suppression réussie");
        } catch (SQLException ex) {
            System.out.println("erreur");   
        }
    }
 public void modifierPersonne(int id,String username,String password) {
          String requette2="update fos_user set username=?,password=? where id="+id;
     
         
              try {  PreparedStatement pst2 = connexion.prepareStatement(requette2);
               
      pst2.setString(1,username);
      pst2.setString(2,hashPassword(password));

        
      pst2.executeUpdate();
      System.out.println("modification reussie");
          } catch (SQLException ex) {
                  System.out.println("erreur");
          } 
    }
 public void modifierMdpPersonne(Personne p,String password) {
          String requette2="update fos_user set password=? where id="+p.getId();
     
         
              try {  PreparedStatement pst2 = connexion.prepareStatement(requette2);
               
      
      pst2.setString(1,hashPassword(password));

        
      pst2.executeUpdate();
      System.out.println("modification reussie");
          } catch (SQLException ex) {
                  System.out.println("erreur");
          } 
    }

     public Personne checkLog(String username, String password) {
        java.sql.Statement query;
		Personne user = null;
		try {
			connexion = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/tgtof","root","");
			String sql = "Select * from fos_user u where u.username = '" +username+"'";
			query = connexion.prepareStatement(sql);
			java.sql.ResultSet result = query.executeQuery(sql);
			if(result.next() != false) {
				user = new Personne(result.getString("username"), result.getString("email"), result.getString("password"));
                            System.out.println("mdp    "+result.getString("password"));
                         
                                String mdp = result.getString("password");
                                if(checkPassword(password, mdp)){
                                System.out.println("connexion réussie");
                                }else{
                                System.out.println("mot de passe incorrect");
                                } 
                                
                                
			}else {
				user = null;
			}
		} catch (SQLException e) {
                    
                e.printStackTrace();
		}
		       System.out.println(user);
		return user;
	}
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2y$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return (password_verified);
    }
    public List<Personne> getAllPersonnes() throws SQLException {
       List<Personne> personnes = new ArrayList<>();
        
        String req = "select * from fos_user";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            Personne p = new Personne( result.getString("username"), result.getString("email"),result.getString("password"));
            personnes.add(p);
        }
        System.out.println("Afficher Personnes :");
        return personnes;
    }
  

}
