/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assistance.Services;

import Assistance.Entities.Appreciation;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import tunisiangt.MyDbConnection;

/**
 *
 * @author frauDEee
 */
public class AppreciationService implements iAppreciation{
Connection connexion;
    public AppreciationService() {
        connexion=MyDbConnection.getInstance().getConnexion();
    }
    
     @Override
    public void addAppreciation(Appreciation a) throws SQLException {
        try{    
            Statement stm=connexion.createStatement();
            
            String requete= "INSERT INTO `appreciation`(`user_id`, `dislike`, `likes`, `publication_id`) VALUES  ('"
                    +a.getUser_id()+"', '"
                    +a.getDislike()+"','"
                    +a.getLikes()+"','"
                    +a.getPublication_id()+"')"; 
                 
            stm.executeUpdate(requete);
      
            System.out.println("Appreciation inserée avec succèss!");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Echec d'insertion!");

        }
    }
    
    @Override
    public List<Appreciation> getAllAppreciations() throws SQLException {
        Statement stm = connexion.createStatement();
        String requete = "select * from `appreciation` ";
        ResultSet rst = stm.executeQuery(requete);
        List<Appreciation> Appreciations = new ArrayList<>();
        while (rst.next()) {
            Appreciation a = new Appreciation();
            a.setId(rst.getInt("id"));
            a.setUser_id(rst.getInt("user_id"));
            a.setLikes(rst.getInt("likes"));
            a.setDislike(rst.getInt("dislike"));
            a.setPublication_id(rst.getInt("publication_id"));
            Appreciations.add(a);
        }

     return Appreciations;  
    }
    
        @Override
    public void deleteAppreciation(int id) throws SQLException {
       try{
        String req = "DELETE FROM `appreciation` where id= "+id;
        Statement pstm = connexion.createStatement();
        pstm.executeUpdate(req);
        System.out.println("Appreciation supprimé!");
    }catch(SQLException e){
        System.out.println(e.getMessage());          
        System.out.println("erreur!");
    }  
    }
    
    @Override
    public void updateAppreciationLike(Appreciation app) throws SQLException {
          PreparedStatement pst;
          try{ 
            String requete = " UPDATE `appreciation` SET `likes`=1 WHERE `id`=?" ;
            pst = connexion.prepareStatement(requete);     
            pst.setInt(1,app.getId());
            pst.executeUpdate();
      System.out.println("l'appreciation est mis à jour."); 
      }catch(SQLException e){
            System.out.println(e.getMessage());          
            System.out.println("erreur");

        }
      }
          @Override
    public void updateAppreciationDisLike(Appreciation app) throws SQLException {
          PreparedStatement pst;
          try{ 
            String requete = " UPDATE `appreciation` SET `dislike`=1 WHERE `id`=?" ;
            pst = connexion.prepareStatement(requete);     
            pst.setInt(1,app.getId());
            pst.executeUpdate();
      System.out.println("l'appreciation est mis à jour."); 
      }catch(SQLException e){
            System.out.println(e.getMessage());          
            System.out.println("erreur");

        }

}
    
    

}
