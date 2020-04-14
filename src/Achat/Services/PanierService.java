/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Achat.Services;


import Achat.Entities.LigneCommande;
import Achat.Entities.Panier;
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
import tgt.MyDbConnection;
import Stock.Entities.Produit;

/**
 *
 * @author Klaizer
 */
public class PanierService implements iPanierService{
    
    Connection connexion;
 
     public PanierService() {
       connexion=MyDbConnection.getInstance().getConnexion();
    }
     
     
    @Override
    public void addPanier(Panier p) throws SQLException {
        try{    
            Statement stm=connexion.createStatement();
            String requete= "INSERT INTO `panier` (`user_id`, `prixTotal`, `etat`) VALUES ('"
                    +p.getUser_id()+"', '"
                    +p.getPrixTotal()+"','"
                +(p.getEtat()?1:0)+"')";

            stm.executeUpdate(requete);
      
            System.out.println("😃😈 Panier inserted 😈😃");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("😃😈 insertion error 1😈😃");

        }
    }

    
    
    
    
     @Override
    public void addPanier2(Panier p) throws SQLException {
        try{ 
        String requete= "insert into `panier` (`user_id`, `prixTotal`, `etat`) values (?,?,?)";
            PreparedStatement pstm = connexion.prepareStatement(requete);
            pstm.setInt(1, p.getUser_id());
            pstm.setDouble(2, p.getPrixTotal());
            pstm.setBoolean(3, p.getEtat());
            pstm.executeUpdate();
            
//            stm.executeUpdate(requete);
       System.out.println("😃😈 Panier inserted 😈😃");
        }catch(SQLException e){
            System.out.println("😃😈 insertion error 2😈😃");

        }
    }
    
    

    @Override
    public void updatePanier(Panier p,Double newPrix) throws SQLException {
         PreparedStatement pst;
          try{ 
            String requete = " UPDATE `Panier` SET `prixTotal`=? WHERE `id`=?" ;
            pst = connexion.prepareStatement(requete);     
            pst.setDouble(1,newPrix);
            pst.setInt(2,p.getId());
            pst.executeUpdate();
      System.out.println("😃😈 Panier updated  😈😃"); 
      }catch(SQLException e){
            System.out.println("😃😈 Update error 😈😃");

        }
      }

    
    @Override
    public List<Panier> getAllPaniers() throws SQLException {
        Statement stm = connexion.createStatement();
        String requete = "select * from `Panier`";
        ResultSet rst = stm.executeQuery(requete);
        List<Panier> paniers = new ArrayList<>();
        while (rst.next()) {
            Panier p = new Panier();
            p.setId(rst.getInt("id"));
            p.setUser_id(rst.getInt("user_id"));
            p.setPrixTotal(rst.getDouble("prixTotal"));
            p.setEtat(rst.getBoolean("etat"));

            paniers.add(p);
        }
       System.out.println(" display ");

     return paniers; 
    }

    @Override
    public Panier getPanierById(int id) throws SQLException {
          Panier p = null;
        try{
          Statement stm = connexion.createStatement();
          String requete = " SELECT * FROM `Panier` WHERE `id`= '"+id+"'" ;
          ResultSet rst = stm.executeQuery(requete);

            if (rst.next())
            {
                p=new Panier(rst.getInt("id"), rst.getInt("user_id"),rst.getDouble("prixtotal"),rst.getBoolean("etat"));
            }
                  System.out.println("😃😈 display by id  😈😃");

       
        
        }catch(SQLException e){
            System.out.println("panier not found");
            System.out.println(e.getMessage());

        }
        return p ;
    
    }

    @Override
    public void changerEtatPanier(Panier p) throws SQLException {
       PreparedStatement pst;
       try{
            String requete = " UPDATE `Panier` SET `etat`=? WHERE `id`=?" ;
            pst = connexion.prepareStatement(requete);
            pst.setBoolean(1,false);
            pst.setInt(2,p.getId());

            pst.executeUpdate();
      System.out.println(" etat panier changed  ");    
      }catch(SQLException e){
            System.out.println(" changer etat error ");

        }
      }

    @Override
    public void deletePanier(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Panier getPanierByUser(int idUser) throws SQLException {
        Panier p = null;
        try{
          Statement stm = connexion.createStatement();
          String requete = " SELECT * FROM `Panier` WHERE `user_id`= '"+idUser+"' and `etat` = 1" ;
          ResultSet rst = stm.executeQuery(requete);

            if (rst.next())
            {
                p=new Panier(rst.getInt("id"), rst.getInt("user_id"),rst.getDouble("prixtotal"),rst.getBoolean("etat"));
            }
                  System.out.println("display by id  ");

       
        
        }catch(SQLException e){
            System.out.println("panier not found");
            System.out.println(e.getMessage());

        }
        return p ;
    }

    


}
