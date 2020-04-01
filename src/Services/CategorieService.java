/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entities.Categorie;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import TGT.MyDbConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Haddad
 */

public class CategorieService implements IserviceCategorie{
 Connection cnx;
  public CategorieService() {
        cnx=MyDbConnection.getInstance().getConnexion();
    }

    @Override
    public void addCategorie(Categorie c) throws SQLException {
        try{    Statement stm=cnx.createStatement();
             String requete= "INSERT INTO `Categorie`"
                +"(`Nom_Categorie`)"
                +  "VALUES ('"+c.getNom_Categorie()+"')";
      stm.executeUpdate(requete);
      
      System.out.println("Element Insr");
        }catch(SQLException ex){}
    }

    @Override
    public void deleteCategorie(int id) throws SQLException {
        PreparedStatement pst;
        String requete = " DELETE FROM `Categorie` WHERE Id_Categorie='"+id+"'" ;
        pst = cnx.prepareStatement(requete);
        Statement ste=cnx.createStatement();
        ste.executeUpdate(requete);
        System.out.println("Element Supp");    }

    @Override
      public void updateCategorie(Categorie c) throws SQLException {
            PreparedStatement pst;
            String requete = " UPDATE `Categorie` SET `Nom_Categorie`=? WHERE `Id_Categorie`=?" ;
            pst = cnx.prepareStatement(requete);
            pst.setString(1,c.getNom_Categorie());
            pst.setInt(2,c.getId_Categorie());

            pst.executeUpdate();
      System.out.println("Element Upd");    
      }

    @Override
    public List<Categorie> getCategorie() throws SQLException {
        Statement stm = cnx.createStatement();
        String requete = "select * from `Categorie`";
        ResultSet rst = stm.executeQuery(requete);
        List<Categorie> categories = new ArrayList<>();
        while (rst.next()) {
            Categorie c2 = new Categorie();
            c2.setId_Categorie(rst.getInt("Id_Categorie"));
            c2.setNom_Categorie(rst.getString("Nom_Categorie"));
            categories.add(c2);
        }
       System.out.println("Categorie Rec");

     return categories; 
    }

    @Override
    public Categorie getById(int id) throws SQLException {
          Categorie c = null;
          Statement stm = cnx.createStatement();
          String requete = " SELECT * FROM `Categorie` WHERE `Id_Categorie`= '"+id+"'" ;
          ResultSet rst = stm.executeQuery(requete);

            if (rst.next())
            {c=new Categorie(rst.getInt("Id_Categorie"),rst.getString("Nom_Categorie")
            );}
                  System.out.println("Categorie Par ID");

       
        return c ;
    
    }
    
      public Categorie getByName(String nom) throws SQLException {
      Categorie c = null;
      Statement stm = cnx.createStatement();
         String requete = " SELECT * FROM `Categorie` WHERE (Nom_Categorie like '"+nom+"%')" ;
        try {
           
            stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            if (rst.next())
            c=new Categorie(rst.getInt("Id_Categorie"),rst.getString("Nom_Categorie"));
            
                } catch (SQLException ex) {
        }
                          System.out.println("Categorie Par Nom");

        return c ;
    
}
      
 public List<Categorie> getTrier() throws SQLException {
 List<Categorie> arrcategorie=new ArrayList<>();
 Statement stm = cnx.createStatement();
 String requete = "select * from Categorie ORDER BY Nom_Categorie ASC";
 ResultSet rst = stm.executeQuery(requete);

       
     while (rst.next()) {
         
         int Id_Categorie=rst.getInt("Id_Categorie");
         String Nom_Categorie=rst.getString("Nom_Categorie");            
         Categorie a = new Categorie(Id_Categorie, Nom_Categorie);
         arrcategorie.add(a);
        
        }
        System.out.println("Afficher Categories :");


    return arrcategorie;
    }          
               
}