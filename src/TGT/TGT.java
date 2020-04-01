/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TGT;

//import view.PersonneService;
import Services.PersonneService;
import Entities.Personne;
import Services.ProduitService;
import java.sql.SQLException;
import Services.CategorieService;
import Entities.Categorie;
import Entities.Produit;
import Entities.Photo;
import Services.ProduitService;
import Services.PhotoService;

/**
 *
 * @author Haddad
 */
public class TGT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Personne p = new Personne("Test", "Test");
        PersonneService ps = new PersonneService();
        ProduitService pr = new ProduitService();
        CategorieService ca = new CategorieService();
        
        Produit p1 = new Produit(0, "tshirt4",1, 1, 1, "Dispo", "m","test");
        
        Categorie c1 = new Categorie(99, "VIP");
        //pr.addProduct(p1);
        //pr.deleteProduct(33);
        //pr.updateProduct(p1);
        //ca.addCategorie(c1);
        //ca.deleteCategorie(5);
        //ca.updateCategorie(c1);
        // ps.ajouterPersonne2(p);

        System.out.println(ps.getAllPersonnes());
        System.out.println(pr.getTrier());
        System.out.println(ca.getTrier());
        
        System.out.println(pr.getByCat(1));
        System.out.println(pr.getById(30));
        System.out.println(pr.getByName("SmartL"));
        System.out.println(ca.getById(3));
        System.out.println(ca.getByName("Acc"));





       
        
    }

}
