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
                +"(`nom`, `cat_id`, `qte`, `prix`, `etat`, `size`, `url`)"
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
        String requete = " DELETE FROM `produit` WHERE id='"+id+"'" ;
        pst = cnx.prepareStatement(requete);
        Statement ste=cnx.createStatement();
        ste.executeUpdate(requete);
        System.out.println("Element Supp");    }

    @Override
      public void updateProduct(Produit p) throws SQLException {
            PreparedStatement pst;
            String requete = " UPDATE `produit` SET `nom`=?,`cat_id`=?,`qte`=?,`prix`=?,`etat`=?,`size`=?,`url`=? WHERE `id`=?" ;
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
            p2.setId_Produit(rst.getInt("id"));
            p2.setNom_Produit(rst.getString("nom"));
            p2.setId_Categorie(rst.getInt("cat_id"));
            p2.setQuantite_Totale(rst.getInt("qte"));
            p2.setPrix_Produit(rst.getFloat("prix"));
            p2.setEtat_Produit(rst.getString("etat"));
            p2.setTaille_Produit(rst.getString("size"));
            p2.setUrl(rst.getString("url"));

            products.add(p2);
        }
       System.out.println("Produit Rec");

     return products; 
    }
    
    public List<Produit> getByCat(int IdCat) throws SQLException {
        Statement stm = cnx.createStatement();
        String requete = "select * from `produit` WHERE cat_id='"+IdCat+"'";
        ResultSet rst = stm.executeQuery(requete);
        List<Produit> products = new ArrayList<>();
        while (rst.next()) {
            Produit p2 = new Produit();
            p2.setId_Produit(rst.getInt("id"));
            p2.setNom_Produit(rst.getString("nom"));
            p2.setId_Categorie(rst.getInt("cat_id"));
            p2.setQuantite_Totale(rst.getInt("qte"));
            p2.setPrix_Produit(rst.getFloat("prix"));
            p2.setEtat_Produit(rst.getString("etat"));
            p2.setTaille_Produit(rst.getString("size"));
            p2.setUrl(rst.getString("url"));
            products.add(p2);
        }
       System.out.println("Produit Par Categorie");

     return products; 
    }
    @Override
    public Produit getById(int id) throws SQLException {
          Produit p = null;
          Statement stm = cnx.createStatement();
          String requete = " SELECT * FROM `produit` WHERE `id`= '"+id+"'" ;
          ResultSet rst = stm.executeQuery(requete);

            if (rst.next())
            {p=new Produit(rst.getInt("id"),rst.getString("nom"),rst.getInt("cat_id"),rst.getInt("qte"),rst.getFloat("prix"),
            rst.getString("etat"),rst.getString("size"),rst.getString("url")
            );}
                  System.out.println("Produit Par ID");

       
        return p ;
    
    }
    
      public Produit getByName(String nom) throws SQLException {
      Produit p = null;
      Statement stm = cnx.createStatement();
         String requete = " SELECT * FROM `produit` WHERE (nom like '"+nom+"%')" ;
        try {
           
            stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            if (rst.next())
            {p=new Produit(rst.getInt("id"),rst.getString("nom"),rst.getInt("cat_id"),rst.getInt("qte"),rst.getFloat("prix"),
            rst.getString("etat"),rst.getString("size"),rst.getString("url")
            );}
            
                } catch (SQLException ex) {
        }
                          System.out.println("Produit Par Nom");

        return p ;
    }
      
    

      
 public List<Produit> getTrier() throws SQLException {
 List<Produit> arrproduct=new ArrayList<>();
 Statement stm = cnx.createStatement();
 String requete = "select * from produit ORDER BY nom ASC";
 ResultSet rst = stm.executeQuery(requete);

       
     while (rst.next()) {
         
         int Id_Produit=rst.getInt("id");
         String Nom_Produit=rst.getString("nom");
         int Id_Categorie=rst.getInt("cat_id");
         int Quantite_Totale=rst.getInt("qte");
         float Prix_Produit=rst.getFloat("prix");
         String Etat_Produit=rst.getString("etat");
         String Taille_Produit=rst.getString("size");
         String Url=rst.getString("url");
         Produit a = new Produit(Id_Produit, Nom_Produit,Id_Categorie,Quantite_Totale,Prix_Produit,Etat_Produit,Taille_Produit,Url);
         arrproduct.add(a);
        
        }
        System.out.println("Afficher Produits :");


    return arrproduct;
    }   
 
 
 //Math
  public boolean ProductHasNote(int id) {
        try {
            String requete = "SELECT * FROM produit WHERE qte=0 AND id = '" + id+ "'";
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
                 String requete = "SELECT (((qte-1) /qte)*100) FROM produit WHERE id = '" + id + "'";
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
                String requete = "SELECT SUM(qte) FROM produit ";
                pst = cnx.prepareStatement(requete);
                ResultSet rs = pst.executeQuery(requete);
                while (rs.next()) {
                   totalproduct = rs.getFloat(1);}
                System.out.println("Totale des Produits");
                return totalproduct;}
     
      public float TotalQuantiteRemaining() throws SQLException{
               float TotalQuantiteRemaining=0.0f;
               PreparedStatement pst;
               String requete = "SELECT SUM(qte-1) FROM produit ";
               pst = cnx.prepareStatement(requete);
               ResultSet rs = pst.executeQuery(requete);
               while (rs.next()) {
                   TotalQuantiteRemaining = rs.getFloat(1);}
               System.out.println("Quantité Totale");
                   return TotalQuantiteRemaining;
                   //ça est y
     }
     public float TotalPrise() throws SQLException{
               float Total = 0.0f;
               PreparedStatement pst;
               String requete = "SELECT Sum(prix) FROM produit ";
               pst = cnx.prepareStatement(requete);
               ResultSet rs = pst.executeQuery(requete);
               while (rs.next()) {
                 Total = rs.getFloat(1);}
               System.out.println("Calc Totale");
                    return Total;
//ça est y
}
    
}
