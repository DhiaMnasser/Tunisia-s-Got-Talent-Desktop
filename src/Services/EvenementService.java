/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Entities.Evenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tgt.MyDbConnection;

/**
 *
 * @author Achraf
 */
public class EvenementService {
    
     Connection connexion;
   

    public EvenementService() {
       connexion=MyDbConnection.getInstance().getConnexion();
    }

    void ajouterEvenement(Evenement e) throws SQLException {
        String req = "INSERT INTO `evenement` (`Duree`, `Gagnant`,`nomevent`,`image`,`Date_d` ,`Date_f`, `MaxParticipants`,`Etat`,`region_id`) VALUES ( '"+ e.getDuree()+"','"
                + e.getGagnant() + "','" + e.getNomevent() + "','" + e.getImage() + "','" + e.getDate_d() + "','" + e.getDate_f()+ "','" + e.getMaxParticipants()+ "','" + e.getEtat() +"','"+e.getRegion_id()+ "') ";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    public void ajouterEvenement2(Evenement e) throws SQLException {
        String req = "INSERT INTO `evenement` (`Duree`, `Gagnant`,`nomevent`,`image`,`Date_d` ,`Date_f`, `MaxParticipants`,`Etat`) VALUES ( ?,?,?,?,?,?,?,?) ";
        PreparedStatement pstm = connexion.prepareStatement(req);
        pstm.setString(1, e.getDuree());
        pstm.setString(2, e.getGagnant());
        pstm.setString(3, e.getNomevent());
        pstm.setString(4, e.getImage());
        pstm.setString(5,  e.getDate_d());
        pstm.setString(6, e.getDate_d());
        pstm.setInt(7, e.getMaxParticipants());
        pstm.setInt(8, e.getEtat());
       
        
        pstm.executeUpdate();
    }
    
      public void supprimerEvenement(int id) throws SQLException {
        String req = "DELETE FROM `evenement` where id= "+id;
        System.out.println(req);
        Statement pstm = connexion.createStatement();
        pstm.executeUpdate(req);
        System.out.println("Evenement Supprimer");
    }
    
    
  public void modifierEvenement(int id, String Duree, String Gagnant, String nomevent, String image,String Date_d ,String Date_f, int MaxParticipants, int Etat) throws SQLException {
        String req = "UPDATE `evenement` SET  Duree='"+Duree
                               +"', Gagnant='"+Gagnant
                               +"', nomevent='"+nomevent
                               +"',image='"+image 
                               +"',Date_d='"+Date_d
                               +"',Date_f='"+Date_f
                               +"',MaxParticipants='"+MaxParticipants 
                               +"',Etat='"+Etat 
                               
                               + "' WHERE id="+id;
        Statement pstm = connexion.createStatement();
       pstm.executeUpdate(req);
        System.out.println("Evenement modifier");
    }
    
    
     public List<Evenement> getAllEvenements() throws SQLException {
       List<Evenement> Evenements = new ArrayList<>();
        String req = "select * from evenement";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            Evenement p = new Evenement(result.getInt(1),result.getString("Duree"), result.getString("Gagnant"),result.getString("nomevent"),result.getString("image"),result.getString("Date_d"),result.getString("Date_f"), result.getInt("MaxParticipants"),result.getInt("Etat"),result.getInt("region_id"));
            Evenements.add(p);
            
            
        }
        return Evenements;
    }
      public Evenement rechercheEvenement(String Nom) throws SQLException {
        String req = "select * FROM `evenement` where nomevent= '"+Nom+"'";
        Statement pstm = connexion.createStatement();
       ResultSet rst = pstm.executeQuery(req);
       
       rst.last();
       int nbrRow=rst.getRow();
       if (nbrRow != 0 )
       {/*System.out.println("Evenement trouvé "+"\n"+"Nom : "+rst.getString("nomevent")
       +"   "+"Duree : "+rst.getString("Duree")+" "+"Jours") ;*/
      // return true ;
           Evenement e = new Evenement (rst.getString("nomevent"), rst.getString("Duree"),rst.getInt("Etat"));
           return  e ;
       }
       else{ 
           return null;
       //return false ;
    }
    }
      public void BloquerEvenement(int id) throws SQLException {
        String req = "update `evenement` set etat=0   where id= '"+id+"'";
        Statement pstm = connexion.createStatement();
        pstm.executeUpdate(req);
       
       
    }
      void affichereventactif ()throws SQLException
      {
          String req = "select * from evenement where etat=1";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
      while(result.next()){
      System.out.println("ID : " +result.getInt(1)+"\n" +"Nom : "+result.getString("nomevent")+"\n" +"Duree : "+ result.getString("Duree")+"\n" +"Date de debut : " +result.getString("Date_d")+"\n" +"Date de fin : "+result.getString("Date_f")+"\n" +"Etat : "+result.getInt("Etat")+"\n" +"Gagnant : "+ result.getString("Gagnant")+"\n" +"Max Participants :  "+ result.getInt("MaxParticipants")+"\n" + "Image : "+result.getString("image")+"\n"+"region_id : "+result.getInt("region_id")+"\n" );

      }
      }
      void affichereventpassif ()throws SQLException
      {
          String req = "select * from evenement where etat=0";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
      while(result.next()){
      System.out.println("ID : " +result.getInt(1)+"\n" +"Nom : "+result.getString("nomevent")+"\n" +"Duree : "+ result.getString("Duree")+"\n" +"Date de debut : " +result.getString("Date_d")+"\n" +"Date de fin : "+result.getString("Date_f")+"\n" +"Etat : "+result.getInt("Etat")+"\n" +"Gagnant : "+ result.getString("Gagnant")+"\n" +"Max Participants :  "+ result.getInt("MaxParticipants")+"\n" + "Image : "+result.getString("image")+"\n"+"region_id : "+result.getInt("region_id")+"\n" );

      }
      }
      void afficher ()throws SQLException
      {
          String req = "select * from evenement ";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
      while(result.next()){
      System.out.println("ID : " +result.getInt(1)+"\n" +"Nom : "+result.getString("nomevent")+"\n" +"Duree : "+ result.getString("Duree")+"\n" +"Date de debut : " +result.getString("Date_d")+"\n" +"Date de fin : "+result.getString("Date_f")+"\n" +"Etat : "+result.getInt("Etat")+"\n" +"Gagnant : "+ result.getString("Gagnant")+"\n" +"Max Participants :  "+ result.getInt("MaxParticipants")+"\n" + "Image : "+result.getString("image")+"\n"+"region_id : "+result.getInt("region_id")+"\n" );

      }
      }
      public ObservableList<Evenement> indexAction() throws SQLException 
     { 
        ObservableList<Evenement> Evenements=FXCollections.observableArrayList();
        String req= "  select * from evenement";
        Statement st;
        
            st=connexion.createStatement();
            ResultSet result=st.executeQuery(req);
            while(result.next())
            {    Evenement p = new Evenement(result.getInt(1),result.getString("Duree"), result.getString("Gagnant"),result.getString("nomevent"),result.getString("image"),result.getString("Date_d"),result.getString("Date_f"), result.getInt("MaxParticipants"),result.getInt("Etat"),result.getInt("region_id"));
            Evenements.add(p);
                    }
        
          return  Evenements;
        
        
     }
    
}
