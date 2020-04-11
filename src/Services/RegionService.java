/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import Entities.Region;
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
public class RegionService {
    
    Connection connexion;
   

    public RegionService() {
       connexion=MyDbConnection.getInstance().getConnexion();
    }
    
    void ajouterRegion(Region r) throws SQLException {
        String req = "INSERT INTO `region` ( `Nom`,`Nb_villes`) VALUES ( '"+ r.getNom()+"','" + r.getNb_villes()  + "') ";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

     public void ajouterRegion2(Region r) throws SQLException {
        String req = "INSERT INTO `region` (`Nom`,`Nb_villes`) VALUES ( ?,?) ";
        PreparedStatement pstm = connexion.prepareStatement(req);
        pstm.setString(1, r.getNom());
        pstm.setInt(2, r.getNb_villes());
        
        pstm.executeUpdate();
    }
    
      public void supprimerRegion(int id) throws SQLException {
        String req = "DELETE FROM `region` where id= "+id;
        System.out.println(req);
        Statement pstm = connexion.createStatement();
        pstm.executeUpdate(req);
        System.out.println("region Supprimer");
    }
    
    
    public void modifierRegion(int id, String Nom, int Nb_villes) throws SQLException {
        String req = "UPDATE `region` SET  Nom='"+Nom
                               +"', Nb_villes='"+Nb_villes
                               + "' WHERE id="+id;
        Statement pstm = connexion.createStatement();
       pstm.executeUpdate(req);
        System.out.println("region modifier");
    }
    
    
      List<Region> getAllRegions() throws SQLException {
       List<Region> Reg = new ArrayList<>();
        String req = "select * from region";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            Region p = new Region(result.getInt(1),result.getString("Nom"),result.getInt("Nb_villes"));
            Reg.add(p);
        }
        return Reg;
    }
      
      
      public Region rechercheRegion(String Nom) throws SQLException {
        String req = "select * FROM `region` where Nom= '"+Nom+"'";
        Statement pstm = connexion.createStatement();
       ResultSet rst = pstm.executeQuery(req);
       rst.last();
       int nbrRow=rst.getRow();
       if (nbrRow != 0 )
       {
           Region r = new Region (rst.getString("Nom"),rst.getInt("Nb_villes")) ;
           return r ;
           /*System.out.println("region trouvée "+"\n"+"Nom : "+rst.getString("Nom")
       +"   "+"Nombre de villes : "+rst.getInt("Nb_villes")) ;*/
       }
       else
       {
           return null ;
       }
    
    }
      void nbRegion() throws SQLException {
        String req = "select * FROM `region`";
        Statement pstm = connexion.createStatement();
       ResultSet rst = pstm.executeQuery(req);
       rst.last();
       int nbrRow=rst.getRow();
       System.out.println("Nombre total de Régions : " +nbrRow);
    
    }
      void afficher ()throws SQLException
      {
          String req = "select * from region ";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
      while(result.next()){
      System.out.println("ID : " +result.getInt(1)+"\n" +"Nom : "+result.getString("Nom")+"\n" +"Nombre de villes : "+ result.getInt("Nb_villes")+"\n"  );

      }
      }
      public ObservableList<Region> indexActionR() throws SQLException 
     { 
        ObservableList<Region> Regions=FXCollections.observableArrayList();
        String req= "  select * from region";
        Statement st;
        
            st=connexion.createStatement();
            ResultSet result=st.executeQuery(req);
            while(result.next())
            {    Region p = new Region(result.getInt(1),result.getString("Nom"),result.getInt("Nb_villes"));
            Regions.add(p);
                    }
        
          return  Regions;
        
        
     }
    
}
