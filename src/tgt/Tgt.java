/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tgt;

import Achat.Entities.Commande;
import Achat.Entities.LigneCommande;
import Achat.Services.LigneCommandeService;
import Achat.Entities.Panier;
import Achat.Services.CommandeService;
import Achat.Services.PanierService;
import Stock.Entities.Categorie;
import Stock.Entities.Produit;
import Stock.Services.CategorieService;
import Stock.Services.ProduitService;
import java.sql.SQLException;

/**
 *
 * @author Klaizer
 */

public class Tgt {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here

//  Entities
//        Panier pa = new Panier(10);
//        Produit pdt = new Produit(1, "tshirt999", 5, 10, "dispo", "M", 0);
//        LigneCommande lc1 = new LigneCommande(45, pdt.getId_Produit(),pdt.getNom_Produit(), 2, 55);
//        Commande c1 = new Commande (10, 56, "regueb", "123456789");
//        
////  Services
//        PanierService ps = new PanierService();
//        ProduitService pr = new ProduitService();
//        LigneCommandeService lcs = new LigneCommandeService(); 
//        CommandeService cs = new CommandeService();
//        
//    System.out.println("CRUD LigneCommande");
//        System.out.println(lc1);
//        lcs.addLigneCommande(lc1);
//        lcs.updateLigneCommande(lc1, 3);
//        System.out.println(lcs.getAllLigneCommandes());
//        System.out.println(lcs.getLigneCommandeById(102));
//        System.out.println(lcs.chercherLigneCommande("tshirt"));
//        
//    System.out.println("CRUD Panier");
//        ps.addPanier(pa);
////        ps.addPanier2(p);
//        System.out.println(pa);
////        Produit pdt1 = pr.getById(lc1.getIdproduit());
//        ps.updatePanier(ps.getPanierById(55), (double)(lc1.getQuantite()*pdt.getPrix_Produit()));
//        System.out.println(ps.getAllPaniers());
//        System.out.println(ps.getPanierById(56));
//        ps.changerEtatPanier(ps.getPanierById(109));
//        
//    System.out.println("CRUD Commande");
////        System.out.println(c1);
////        cs.addCommande(c1);
////        cs.validerCommande(c1);
////        System.out.println(cs.getAllCommandes());
////        System.out.println(cs.getCommandeById(188));
////        System.out.println(cs.getAllCommandesByUser(12));
////        System.out.println(cs.chercherCommande("valide"));
        
        //ProduitService pr = new ProduitService();
        //CategorieService ca = new CategorieService();
        
        //Produit p1 = new Produit(0, "tshirt5",1, 1, 1, "Dispo", "m","test");
        
        //Categorie c1 = new Categorie(99, "Test");
        //pr.addProduct(p1);
        //pr.deleteProduct(35);
        //pr.updateProduct(p1);
        //ca.addCategorie(c1);
        //ca.deleteCategorie(5);
        //ca.updateCategorie(c1);
        // ps.ajouterPersonne2(p);

       
        //System.out.println(pr.getTrier());
        //System.out.println(ca.getTrier());
        
        //System.out.println(pr.getByCat(1));
        //System.out.println(pr.getById(30));
        //System.out.println(pr.getByName("SmartL"));
        //System.out.println(ca.getById(3));
        //System.out.println(ca.getByName("Acc"));




    }
    
}
