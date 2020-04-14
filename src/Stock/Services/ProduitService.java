/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Stock.Entities.Produit;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import tgt.MyDbConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Haddad
 */

public class ProduitService implements IserviceProduit{
 Connection cnx;
  public ProduitService() {
        cnx=MyDbConnection.getInstance().getConnexion();
    }

    @Override
    public void addProduct(Produit p) throws SQLException {
        try{    Statement stm=cnx.createStatement();
             String requete= "INSERT INTO `produit`"
                +"(`nom`, `qte`, `prix`, `etat`, `size`, `cat_id`)"
                +  "VALUES ('"+p.getNom_Produit()+"', "
                +"'"+p.getQuantite_Totale()+"',"
                +"'"+p.getPrix_Produit()+"',"
                +"'"+p.getEtat_Produit()+"',"
                +"'"+p.getTaille_Produit()+"',"
                +"'"+p.getId_Categorie()+"')";
      stm.executeUpdate(requete);
      
      System.out.println("😃😈 element inserted 😍 succeeds 😈😃");
        }catch(SQLException ex){}
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        PreparedStatement pst;
        String requete = " DELETE FROM `produit` WHERE id='"+id+"'" ;
        pst = cnx.prepareStatement(requete);
        Statement ste=cnx.createStatement();
        ste.executeUpdate(requete);
        System.out.println("😃😈 element deleted 😍 succeeds 😈😃");    }

    @Override
      public void updateProduct(Produit p) throws SQLException {
            PreparedStatement pst;
            String requete = " UPDATE `produit` SET `Nom_Produit`=?,`Quantite_Totale`=?,`Prix_Produit`=?,`Etat_Produit`=?,`Taille_Produit`=?,`Id_Categorie`=? WHERE `Id_Produit`=?" ;
            pst = cnx.prepareStatement(requete);
            pst.setString(1,p.getNom_Produit());
            pst.setInt(2, p.getQuantite_Totale());
            pst.setFloat(3, p.getPrix_Produit());
            pst.setString(4, p.getEtat_Produit());
            pst.setString(5, p.getTaille_Produit());
            pst.setInt(6, p.getId_Categorie());
            pst.setInt(7,p.getId_Produit());

            pst.executeUpdate();
      System.out.println("😃😈 element updated 😍 succeeds 😈😃");    
      }

    @Override
    public List<Produit> getProduct() throws SQLException {
        Statement stm = cnx.createStatement();
        String requete = "select * from `produit`";
        ResultSet rst = stm.executeQuery(requete);
        List<Produit> products = new ArrayList<>();
        while (rst.next()) {
            Produit p2 = new Produit();
            p2.setId_Produit(rst.getInt("id"));
            p2.setNom_Produit(rst.getString("Nom_Produit"));
            p2.setQuantite_Totale(rst.getInt("Quantite_Totale"));
            p2.setPrix_Produit(rst.getFloat("Prix_Produit"));
            p2.setEtat_Produit(rst.getString("Etat_Produit"));
            p2.setTaille_Produit(rst.getString("Taille_Produit"));
            p2.setId_Categorie(rst.getInt("Id_Categorie"));
            products.add(p2);
        }
       System.out.println("😃😈 display 😍 succeeds 😈😃");

     return products; 
    }

    @Override
    public Produit getById(int id) throws SQLException {
          Produit p = null;
      Statement stm = cnx.createStatement();
         String requete = " SELECT * FROM `produit` WHERE id="+id;
        try {
           
//            stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            if (rst.next())
            p=new Produit(rst.getInt("id"),rst.getString("nom"),rst.getInt("qte"),rst.getFloat("prix"),
            rst.getString("etat"),rst.getString("size"),rst.getInt("cat_id"));
            
                } catch (SQLException ex) {
        }
                          System.out.println(" display Produit by id succeeds ");

        return p ;
    
    }
    
      public Produit getByName(String nom) throws SQLException {
      Produit p = null;
      Statement stm = cnx.createStatement();
         String requete = " SELECT * FROM `produit` WHERE (nom = '"+nom+"')" ;
        try {
           
//            stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            if (rst.next())
            p=new Produit(rst.getInt("id"),rst.getString("nom"),rst.getInt("qte"),rst.getFloat("prix"),
            rst.getString("etat"),rst.getString("size"),rst.getInt("cat_id"));
            
                } catch (SQLException ex) {
        }
                          System.out.println(" display Produit by name succeeds ");

        return p ;
    
}
      
 public List<Produit> getTrier() throws SQLException {
 List<Produit> arrproduct=new ArrayList<>();
 Statement stm = cnx.createStatement();
 String requete = "select * from produit ORDER BY Nom_Produit ASC";
 ResultSet rst = stm.executeQuery(requete);

       
     while (rst.next()) {
         
         int Id_Produit=rst.getInt("id");
         String Nom_Produit=rst.getString("Nom_Produit");
         int Quantite_Totale=rst.getInt("Quantite_Totale");
         float Prix_Produit=rst.getFloat("Prix_Produit");
         String Etat_Produit=rst.getString("Etat_Produit");
         String Taille_Produit=rst.getString("Taille_Produit");
         int Id_Categorie=rst.getInt("Id_Categorie");
         Produit a = new Produit(Id_Produit, Nom_Produit,Quantite_Totale,Prix_Produit,Etat_Produit,Taille_Produit,Id_Categorie);
         arrproduct.add(a);
        
        }
        System.out.println("Afficher Produits :");


    return arrproduct;
    }     
    
}