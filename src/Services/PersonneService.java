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

     public void sendCode(Personne p){
       try {
           JavaMailUtil.sendMail(p.getEmail(),p.getConfirmation_token());
       } catch (Exception ex) {
          
       }
     }
   public void ajouterPersonne(Personne p)  {
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
  

           
   
     
          
             pst.executeUpdate();
            System.out.println("Insertion réussie");
                  
          
          }catch(Exception ex){
              System.out.println(ex);
          }
     
    }
       public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2y$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return (password_verified);
    }
   public Personne recherche(String username){
         java.sql.Statement query;
		Personne user = null;
              
		try { connexion=MyDbConnection.getInstance().getConnexion();
			
			String sql = "Select * from fos_user u where u.username = '" +username+"'";
			query = connexion.prepareStatement(sql);
			java.sql.ResultSet result = query.executeQuery(sql);
			if(result.next() != false) {
				user = new Personne(result.getInt("id"),result.getString("username"), result.getString("email"), result.getString("password"));
                          user.setEnabled(result.getInt("enabled"));
                          user.setRoles(result.getString("roles"));
                         
                               
                                
                                
			}else {
				user = null;
			}
		} catch (SQLException e) {
                    user=null;
                    System.out.println("username ou mot de passe introuvable");
		}
		      
		return user;
    }

public void remove(Personne p) {
          String requete="delete from fos_user where id="+p.getId();
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
  public void modifier(Personne p) {
          String requette2="update fos_user set username=?,username_canonical=?,email=?,email_canonical=?,enabled=?,salt=?,password=?,last_login=?,confirmation_token=?,password_requested_at=?,roles=? where id="+p.getId();
     
         
              try {  PreparedStatement pst = connexion.prepareStatement(requette2);
               
         
             pst.setString(1, p.getUsername());
             pst.setString(2, p.getUsername_canonical());
             pst.setString(3, p.getEmail());
             pst.setString(4, p.getEmail_canonical());
             pst.setInt(5, p.getEnabled());
             pst.setString(6, p.getSalt());
             pst.setString(7, p.getPassword());
             pst.setTimestamp(8, p.getLast_login());
             pst.setString(9, p.getConfirmation_token());
             pst.setTimestamp(10, p.getPassword_requested_at());
             pst.setString(11, p.getRoles());
  

        
      pst.executeUpdate();
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

     public String checkLog(String username, String password) {
        java.sql.Statement query;
        try{ 
                 Personne user =recherche(username);
                       if (user.getEnabled()==1){
                         
                             
                                String mdp = user.getPassword();
                                if(checkPassword(password, mdp)){
                                System.out.println("connexion réussie");
                               
                                return "connexion réussie";
                                }else{
                              
                                return "username ou mot de passe  incorrect";
                                }
                                
                            }else{
                           return "this account is disabled";
                       }
                  
			}catch(NullPointerException e){
				
                                return "username ou mot de passe incorrect";
			}
		
                    
               
		      
	}

      
      public void promote(String username){
          Personne p;
          p=recherche(username);
          p.setRoles("a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
          modifier(p);
      }
    public List<Personne> getAllPersonnes()  {
       List<Personne> personnes = new ArrayList<>();
        try {
            
       
        String req = "select * from fos_user";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            
            Personne p = new Personne( result.getString("username"), result.getString("email"),result.getString("password"));
            p.setId(result.getInt("id"));
            personnes.add(p);
        }
        System.out.println("Afficher Personnes :");
        
         } catch (Exception e) {
        }
        return personnes;
    }
    
  
public void disable(String username){
    Personne p ;
       p=recherche(username);
    p.disable();
    modifier(p);
}
 public Personne recherche(int id){
         java.sql.Statement query;
		Personne user = null;
              
		try { connexion=MyDbConnection.getInstance().getConnexion();
			
			String sql = "Select * from fos_user u where u.id = '" +id+"'";
			query = connexion.prepareStatement(sql);
			java.sql.ResultSet result = query.executeQuery(sql);
			if(result.next() != false) {
				user = new Personne(result.getInt("id"),result.getString("username"), result.getString("email"), result.getString("password"));
                          user.setEnabled(result.getInt("enabled"));
                          user.setRoles(result.getString("roles"));
                         
                               
                                
                                
			}else {
				user = null;
			}
		} catch (SQLException e) {
                    
                    System.out.println("username ou mot de passe introuvable");
		}
		      
		return user;
    }
 public List<Personne> readPesquisar(String desc) {
        List<Personne> users = new ArrayList();

        connexion=MyDbConnection.getInstance().getConnexion();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connexion.prepareStatement("SELECT * FROM fos_user Where username like ?;");
            stmt.setString(1, "%"+desc+"%");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Personne p = new Personne();

                p.setId(rs.getInt("id"));
                p.setUsername(rs.getString("username"));
                p.setEmail(rs.getString("email"));
                p.setPassword(rs.getString("password"));

                users.add(p);
            }

        } catch (SQLException ex) {
            
        
           
        }
        return users;
    }
}

