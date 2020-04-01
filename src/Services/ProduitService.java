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
import Entities.Produit;
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
public class ProduitService implements IserviceProduit{
 Connection cnx;
  public ProduitService() {
        cnx=MyDbConnection.getInstance().getConnexion();
    }

    @Override
    public void addProduct(Produit p) throws SQLException {
        try{    Statement stm=cnx.createStatement();
             String requete= "INSERT INTO `produit`"
                +"(`Nom_Produit`, `Id_Categorie`, `Quantite_Totale`, `Prix_Produit`, `Etat_Produit`, `Taille_Produit`, `Url`)"
                +  "VALUES ('"+p.getNom_Produit()+"', "
                +"'"+p.getId_Categorie()+"',"
                +"'"+p.getQuantite_Totale()+"',"
                +"'"+p.getPrix_Produit()+"',"
                +"'"+p.getEtat_Produit()+"',"
                +"'"+p.getTaille_Produit()+"',"
                +"'"+p.getUrl()+"')";
      stm.executeUpdate(requete);
      
      System.out.println("Element Insr");
        }catch(SQLException ex){}
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        PreparedStatement pst;
        String requete = " DELETE FROM `produit` WHERE Id_Produit='"+id+"'" ;
        pst = cnx.prepareStatement(requete);
        Statement ste=cnx.createStatement();
        ste.executeUpdate(requete);
        System.out.println("Element Supp");    }

    @Override
      public void updateProduct(Produit p) throws SQLException {
            PreparedStatement pst;
            String requete = " UPDATE `produit` SET `Nom_Produit`=?,`Id_Categorie`=?,`Quantite_Totale`=?,`Prix_Produit`=?,`Etat_Produit`=?,`Taille_Produit`=?,`Url`=? WHERE `Id_Produit`=?" ;
            pst = cnx.prepareStatement(requete);
            pst.setString(1,p.getNom_Produit());
            pst.setInt(6, p.getId_Categorie());
            pst.setInt(2, p.getQuantite_Totale());
            pst.setFloat(3, p.getPrix_Produit());
            pst.setString(4, p.getEtat_Produit());
            pst.setString(5, p.getTaille_Produit());
            pst.setString(7, p.getUrl());
            pst.setInt(8,p.getId_Produit());

            pst.executeUpdate();
      System.out.println("Element Upd");    
      }

    @Override
    public List<Produit> getProduct() throws SQLException {
        Statement stm = cnx.createStatement();
        String requete = "select * from `produit`";
        ResultSet rst = stm.executeQuery(requete);
        List<Produit> products = new ArrayList<>();
        while (rst.next()) {
            Produit p2 = new Produit();
            p2.setId_Produit(rst.getInt("Id_Produit"));
            p2.setNom_Produit(rst.getString("Nom_Produit"));
            p2.setId_Categorie(rst.getInt("Id_Categorie"));
            p2.setQuantite_Totale(rst.getInt("Quantite_Totale"));
            p2.setPrix_Produit(rst.getFloat("Prix_Produit"));
            p2.setEtat_Produit(rst.getString("Etat_Produit"));
            p2.setTaille_Produit(rst.getString("Taille_Produit"));
            p2.setUrl(rst.getString("Url"));

            products.add(p2);
        }
       System.out.println("Produit Rec");

     return products; 
    }
    
    public List<Produit> getByCat(int IdCat) throws SQLException {
        Statement stm = cnx.createStatement();
        String requete = "select * from `produit` WHERE Id_Categorie='"+IdCat+"'";
        ResultSet rst = stm.executeQuery(requete);
        List<Produit> products = new ArrayList<>();
        while (rst.next()) {
            Produit p2 = new Produit();
            p2.setId_Produit(rst.getInt("Id_Produit"));
            p2.setNom_Produit(rst.getString("Nom_Produit"));
            p2.setId_Categorie(rst.getInt("Id_Categorie"));
            p2.setQuantite_Totale(rst.getInt("Quantite_Totale"));
            p2.setPrix_Produit(rst.getFloat("Prix_Produit"));
            p2.setEtat_Produit(rst.getString("Etat_Produit"));
            p2.setTaille_Produit(rst.getString("Taille_Produit"));
            p2.setUrl(rst.getString("Url"));
            products.add(p2);
        }
       System.out.println("Produit Par Categorie");

     return products; 
    }
    @Override
    public Produit getById(int id) throws SQLException {
          Produit p = null;
          Statement stm = cnx.createStatement();
          String requete = " SELECT * FROM `produit` WHERE `Id_Produit`= '"+id+"'" ;
          ResultSet rst = stm.executeQuery(requete);

            if (rst.next())
            {p=new Produit(rst.getInt("Id_Produit"),rst.getString("Nom_Produit"),rst.getInt("Id_Categorie"),rst.getInt("Quantite_Totale"),rst.getFloat("Prix_Produit"),
            rst.getString("Etat_Produit"),rst.getString("Taille_Produit"),rst.getString("Url")
            );}
                  System.out.println("Produit Par ID");

       
        return p ;
    
    }
    
      public Produit getByName(String nom) throws SQLException {
      Produit p = null;
      Statement stm = cnx.createStatement();
         String requete = " SELECT * FROM `produit` WHERE (Nom_Produit like '"+nom+"%')" ;
        try {
           
            stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            if (rst.next())
            p=new Produit(rst.getInt("Id_Produit"),rst.getString("Nom_Produit"),rst.getInt("Id_Categorie"),rst.getInt("Quantite_Totale"),rst.getFloat("Prix_Produit"),
            rst.getString("Etat_Produit"),rst.getString("Taille_Produit"),rst.getString("Url"));
            
                } catch (SQLException ex) {
        }
                          System.out.println("Produit Par Nom");

        return p ;
    }
      
    

      
 public List<Produit> getTrier() throws SQLException {
 List<Produit> arrproduct=new ArrayList<>();
 Statement stm = cnx.createStatement();
 String requete = "select * from produit ORDER BY Nom_Produit ASC";
 ResultSet rst = stm.executeQuery(requete);

       
     while (rst.next()) {
         
         int Id_Produit=rst.getInt("Id_Produit");
         String Nom_Produit=rst.getString("Nom_Produit");
         int Id_Categorie=rst.getInt("Id_Categorie");
         int Quantite_Totale=rst.getInt("Quantite_Totale");
         float Prix_Produit=rst.getFloat("Prix_Produit");
         String Etat_Produit=rst.getString("Etat_Produit");
         String Taille_Produit=rst.getString("Taille_Produit");
         String Url=rst.getString("Url");
         Produit a = new Produit(Id_Produit, Nom_Produit,Id_Categorie,Quantite_Totale,Prix_Produit,Etat_Produit,Taille_Produit,Url);
         arrproduct.add(a);
        
        }
        System.out.println("Afficher Produits :");


    return arrproduct;
    }   
 
 
 //Math
  public boolean ProductHasNote(int id) {
        try {
            String requete = "SELECT * FROM produit WHERE Quantite_Totale=0 AND Id_Produit = '" + id+ "'";
            PreparedStatement pst;
            pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);//ça est
            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Echec de recherche de produit" + e);
        }
        return false;
    } 
     public float SalesRate(int id) throws SQLException{
                 float SalesRate = 0.0f;
                 PreparedStatement pst;
                 String requete = "SELECT (((Quantite_Totale-1) /Quantite_Totale)*100) FROM produit WHERE Id_Produit = '" + id + "'";
                 pst = cnx.prepareStatement(requete);
                 ResultSet rs = pst.executeQuery(requete);
                 while (rs.next()) {
                    SalesRate = rs.getFloat(1);}
                 System.out.println("Vendu Calc");
                 return SalesRate;
                    
//ça est y
   
}
     public float TotalProduct() throws SQLException{
                float totalproduct=0.0f;
                PreparedStatement pst;
                String requete = "SELECT COUNT(Quantite_Totale) FROM produit ";
                pst = cnx.prepareStatement(requete);
                ResultSet rs = pst.executeQuery(requete);
                while (rs.next()) {
                   totalproduct = rs.getFloat(1);}
                System.out.println("Totale des Produits");
                return totalproduct;}
     
      public float TotalQuantiteRemaining() throws SQLException{
               float TotalQuantiteRemaining=0.0f;
               PreparedStatement pst;
               String requete = "SELECT SUM(Quantite_Totale-1) FROM produit ";
               pst = cnx.prepareStatement(requete);
               ResultSet rs = pst.executeQuery(requete);
               while (rs.next()) {
                   TotalQuantiteRemaining = rs.getFloat(1);}
               System.out.println("Quantité Totale");
                   return TotalQuantiteRemaining;
                   //ça est y
     }
     public float TotalPrise(int id) throws SQLException{
               float Total = 0.0f;
               PreparedStatement pst;
               String requete = "SELECT (Prix_Produit * Quantite_Totale) FROM produit WHERE Id_Produit = '" + id + "'";
               pst = cnx.prepareStatement(requete);
               ResultSet rs = pst.executeQuery(requete);
               while (rs.next()) {
                 Total = rs.getFloat(1);}
               System.out.println("Calc Totale");
                    return Total;
//ça est y
}
    
}