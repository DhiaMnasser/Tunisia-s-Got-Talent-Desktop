/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Achat.Services;

import Achat.Entities.LigneCommande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tgt.MyDbConnection;

/**
 *
 * @author Klaizer
 */
public class LigneCommandeService implements iLigneCommande {

    Connection connexion;

    public LigneCommandeService() {
        connexion = MyDbConnection.getInstance().getConnexion();
    }

    @Override
    public void addLigneCommande(LigneCommande lc) throws SQLException {
        try {
            Statement stm = connexion.createStatement();

            String requete = "INSERT INTO `ligne_commande` (`idproduit`, `nomProduit`, `quantite`, `idPanier`) VALUES ('"
                    + lc.getIdproduit() + "', '"
                    + lc.getNomProduit() + "','"
                    + lc.getQuantite() + "','"
                    + lc.getIdPanier() + "')";

            stm.executeUpdate(requete);

            System.out.println(" LigneCommande inserted ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(" insertion LigneCommande error 1");

        }
    }

    @Override
    public void addLigneCommande2(LigneCommande lc) throws SQLException {
        try {
            String requete = "insert into `ligne_commande` (`idproduit`, `nomProduit`, `quantite`, `idPanier`) values (?,?,?)";
            PreparedStatement pstm = connexion.prepareStatement(requete);
            pstm.setInt(1, lc.getIdproduit());
            pstm.setString(2, lc.getNomProduit());
            pstm.setInt(3, lc.getQuantite());
            pstm.setInt(4, lc.getIdPanier());

            pstm.executeUpdate();

//            stm.executeUpdate(requete);
            System.out.println(" LigneCommande inserted ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(" insertion LigneCommande error 2");

        }
    }

    @Override
    public void updateLigneCommande(LigneCommande lc, int qte) throws SQLException {
        PreparedStatement pst;
        try {
            String requete = " UPDATE `ligne_commande` SET `quantite`=? WHERE `id`=?";
            pst = connexion.prepareStatement(requete);
            pst.setDouble(1, qte);
            pst.setInt(2, lc.getId());
            pst.executeUpdate();
            System.out.println(" LigneCommande updated ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Update LigneCommande error ");

        }
    }

    @Override
    public List<LigneCommande> getAllLigneCommandes() throws SQLException {
        Statement stm = connexion.createStatement();
        String requete = "select * from `ligne_commande` ";
        ResultSet rst = stm.executeQuery(requete);
        List<LigneCommande> LigneCommandes = new ArrayList<>();
        while (rst.next()) {
            LigneCommande lc = new LigneCommande();
            lc.setId(rst.getInt("id"));
            lc.setId(rst.getInt("idproduit"));
            lc.setNomProduit(rst.getString("nomProduit"));
            lc.setQuantite(rst.getInt("quantite"));
            lc.setIdPanier(rst.getInt("idPanier"));

            LigneCommandes.add(lc);
        }
        System.out.println(" display  All LigneCommandes");

        return LigneCommandes;
    }

    @Override
    public LigneCommande getLigneCommandeById(int id) throws SQLException {
        LigneCommande lc = null;
        Statement stm = connexion.createStatement();
        String requete = " SELECT * FROM `ligne_commande` WHERE `id`= '" + id + "'";
        ResultSet rst = stm.executeQuery(requete);

        if (rst.next()) {
            lc = new LigneCommande(rst.getInt("id"), rst.getInt("idProduit"), rst.getString("nomProduit"), rst.getInt("quantite"), rst.getInt("idPanier"));
        }
        System.out.println(" display LigneCommande by id ");

        return lc;

    }

    @Override
    public LigneCommande getLigneCommandeByNomProduit(String nomPdt) throws SQLException {
        LigneCommande lc = null;
        try {
            Statement stm = connexion.createStatement();
            String requete = " SELECT * FROM `ligne_commande` WHERE `nomProduit`= '" + nomPdt + "'";
            ResultSet rst = stm.executeQuery(requete);

            if (rst.next()) {
                lc = new LigneCommande(rst.getInt("id"),
                        rst.getInt("idProduit"),
                        rst.getString("nomProduit"),
                        rst.getInt("quantite"),
                        rst.getInt("idPanier"));
            }
            System.out.println(" display LigneCommande by nom ");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lc;

    }

    @Override
    public LigneCommande getLigneCommandeByIdProduit(int IdPdt) throws SQLException {
        LigneCommande lc = null;
        try {
            Statement stm = connexion.createStatement();
            String requete = " SELECT * FROM `ligne_commande` WHERE `idproduit`= '" + IdPdt + "'";
            ResultSet rst = stm.executeQuery(requete);

            if (rst.next()) {
                lc = new LigneCommande(rst.getInt("id"),
                        rst.getInt("idProduit"),
                        rst.getString("nomProduit"),
                        rst.getInt("quantite"),
                        rst.getInt("idPanier"));
            }
            System.out.println(" display LigneCommande by nom ");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lc;

    }

    @Override
    public void deleteLigneCommande(int id) throws SQLException {
        try {
            String req = "DELETE FROM `ligne_commande` where id= " + id;
            Statement pstm = connexion.createStatement();
            pstm.executeUpdate(req);
            System.out.println("Panier Supprimer");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Delete LigneCommande error");
        }
    }

    @Override
    public List<LigneCommande> chercherLigneCommande(String query, int idPanier) throws SQLException {
        Statement stm = connexion.createStatement();
        String requete = "select * from `ligne_commande` where `idPanier`= '" + idPanier + "' and ( `nomProduit` like '%" + query + "%' or `quantite` like '%" + query + "%' )";
        ResultSet rst = stm.executeQuery(requete);
        List<LigneCommande> LigneCommandes = new ArrayList<>();
        while (rst.next()) {
            LigneCommande lc = new LigneCommande();
            lc.setId(rst.getInt("id"));
            lc.setId(rst.getInt("idproduit"));
            lc.setNomProduit(rst.getString("nomProduit"));
            lc.setQuantite(rst.getInt("quantite"));
            lc.setIdPanier(rst.getInt("idPanier"));

            LigneCommandes.add(lc);
        }
        System.out.println("chercher LigneCommandes " + query);

        return LigneCommandes;

    }

    @Override
    public List<LigneCommande> getLigneCommandesByPanier(int id) throws SQLException {
        Statement stm = connexion.createStatement();
        String requete = " SELECT * FROM `ligne_commande` WHERE `idPanier`= '" + id + "'";
        ResultSet rst = stm.executeQuery(requete);
        List<LigneCommande> LigneCommandes = new ArrayList<>();
        while (rst.next()) {
            LigneCommande lc = new LigneCommande();
            lc.setId(rst.getInt("id"));
            lc.setIdproduit(rst.getInt("idproduit"));
            lc.setNomProduit(rst.getString("nomProduit"));
            lc.setQuantite(rst.getInt("quantite"));
            lc.setIdPanier(rst.getInt("idPanier"));

            LigneCommandes.add(lc);
        }
        System.out.println(" display  All LigneCommandes");

        return LigneCommandes;
    }

    public LigneCommande getLigneCommandeByPanierEtProduit(int idProduit, int idPanier) {
        LigneCommande lc = null;
        try {
            Statement stm = connexion.createStatement();
            String requete = " SELECT * FROM `ligne_commande` WHERE `idproduit`= '" + idProduit + "' and `idpanier`='" + idPanier + "'";
            ResultSet rst = stm.executeQuery(requete);

            if (rst.next()) {
                lc = new LigneCommande(rst.getInt("id"),
                        rst.getInt("idProduit"),
                        rst.getString("nomProduit"),
                        rst.getInt("quantite"),
                        rst.getInt("idPanier"));
            }
            System.out.println(" display LigneCommande by produit and panier ");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(lc);
        return lc;
    }

}
