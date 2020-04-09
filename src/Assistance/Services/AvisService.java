/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assistance.Services;

import Assistance.Entities.Appreciation;
import Assistance.Entities.Avis;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tunisiangt.MyDbConnection;



/**
 *
 * @author frauDEee
 */
public class AvisService implements iAvis {
   
    Connection connexion;
    
    public AvisService() {
        connexion=MyDbConnection.getInstance().getConnexion();
    }
    
    private static java.sql.Date getCurrentDate() {
    java.util.Date today = new java.util.Date();
    return new java.sql.Date(today.getTime());
}
    
     @Override
    public void addAvis(Avis a) throws SQLException {
          Statement stm=connexion.createStatement();
            try{
            String requete= "INSERT INTO `avis`(`user_id`, `texte`, `date`, `rating`, `Email`)VALUES ('"+a.getUser_id()+"', '"+a.getTexte()+"','"+a.getDate()+"','"+a.getRating()+"','"+a.getEmail()+"')";
                     
                 
            stm.executeUpdate(requete);
      
            System.out.println("Avis inserée avec succèss!");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Echec d'insertion!");

        }
    }
    
    @Override
    public List<Avis> getAllAvis() throws SQLException {
        Statement stm = connexion.createStatement();
        String requete = "select * from `avis` ";
        ResultSet rst = stm.executeQuery(requete);
        List<Avis> Avislist = new ArrayList<>();
        while (rst.next()) {
            Avis a = new Avis();
            a.setId(rst.getInt("id"));
            a.setUser_id(rst.getInt("user_id"));
            a.setTexte(rst.getString("texte"));
            a.setDate(rst.getDate("date"));
            a.setRating(rst.getInt("Rating"));
            a.setEmail(rst.getString("Email"));
            Avislist.add(a);
        }

     return Avislist;  
    }
    
     @Override
    public void deleteAvis(int id) throws SQLException {
       try{
        String req = "DELETE FROM `avis` where id= "+id;
        Statement pstm = connexion.createStatement();
        pstm.executeUpdate(req);
        System.out.println("Avis supprimé!");
    }catch(SQLException e){
        System.out.println(e.getMessage());          
        System.out.println("erreur!");
    }  
    }
    
}
