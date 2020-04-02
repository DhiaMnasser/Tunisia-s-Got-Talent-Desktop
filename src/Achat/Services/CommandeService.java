/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Achat.Services;

import Achat.Entities.Commande;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import tgt.MyDbConnection;

/**
 *
 * @author Klaizer
 */
public class CommandeService implements iCommande{
    Connection connexion;

    public CommandeService() {
       connexion=MyDbConnection.getInstance().getConnexion();
    }

    @Override
    public void addCommande(Commande c) throws SQLException {
        try{    
            Statement stm=connexion.createStatement();
            
            String requete= "INSERT INTO `commande`(`user_id`, `date`, `etat`, `idPanier`, `address`, `tel`) VALUES  ('"
                    +c.getUser_id()+"', '"
                    +c.getDate()+"','"
                    +c.getEtat()+"','"
                    +c.getIdPanier()+"','"   
                    +c.getAddress()+"','"                    
                    +c.getTel()+"')";
                
            stm.executeUpdate(requete);
      
            System.out.println("😃😈 Commande inserted 😈😃");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("😃😈 insertion Commande error 1😈😃");

        }
    }

    @Override
    public void validerCommande(Commande c) throws SQLException {
        PreparedStatement pst;
          try{ 
            String requete = " UPDATE `commande` SET  `etat`=1 WHERE `id`=?" ;
            pst = connexion.prepareStatement(requete);     
            pst.setInt(1,c.getId());
            pst.executeUpdate();
      System.out.println("😃😈 Commande updated  😈😃"); 
      }catch(SQLException e){
            System.out.println(e.getMessage());          
            System.out.println("😃😈 Update Commande error 😈😃");

        }
    }

    @Override
    public List<Commande> getAllCommandes() throws SQLException {
        Statement stm = connexion.createStatement();
        String requete = "select * from `commande` ";
        ResultSet rst = stm.executeQuery(requete);
        List<Commande> Commandes = new ArrayList<>();
        while (rst.next()) {
            Commande c = new Commande();
            c.setId(rst.getInt("id"));
            c.setUser_id(rst.getInt("user_id"));
            c.setDate(rst.getDate("date"));
            c.setEtat(rst.getBoolean("etat"));
            c.setIdPanier(rst.getInt("idPanier"));
            c.setAddress(rst.getString("address"));
            c.setTel(rst.getString("tel"));
            

            Commandes.add(c);
        }
       System.out.println("😃😈 display  All Commandes😈😃");

     return Commandes;  
    }

    @Override
    public List<Commande> getAllCommandesByUser(int idUser) throws SQLException {
        Statement stm = connexion.createStatement();
        String requete = "select * from `commande` where user_id= "+idUser;
        ResultSet rst = stm.executeQuery(requete);
        List<Commande> Commandes = new ArrayList<>();
        while (rst.next()) {
            Commande c = new Commande();
            c.setId(rst.getInt("id"));
            c.setUser_id(rst.getInt("user_id"));
            c.setDate(rst.getDate("date"));
            c.setEtat(rst.getBoolean("etat"));
            c.setIdPanier(rst.getInt("idPanier"));
            c.setAddress(rst.getString("address"));
            c.setTel(rst.getString("tel"));
            

            Commandes.add(c);
        }
       System.out.println("😃😈 display  All Commandes😈😃");

     return Commandes;
    }

    @Override
    public Commande getCommandeById(int id) throws SQLException {
        Commande c = null;
          Statement stm = connexion.createStatement();
          String requete = " SELECT * FROM `commande` WHERE `id`= '"+id+"'" ;
          ResultSet rst = stm.executeQuery(requete);

            if (rst.next())
            {
                c=new Commande(rst.getInt("id"), rst.getInt("user_id"),rst.getDate("date"),rst.getBoolean("etat"),rst.getInt("idPanier"),rst.getString("address"),rst.getString("tel"));
            }
                  System.out.println("😃😈 display Commande by id  😈😃");

       
        return c ;    
    }

    @Override
    public List<Commande> chercherCommande(String query) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
