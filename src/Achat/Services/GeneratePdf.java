/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Achat.Services;

import Achat.Entities.Commande;
import Achat.Entities.LigneCommande;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Klaizer
 */
public class GeneratePdf {
    LigneCommandeService lcs = new LigneCommandeService();
    CommandeService cmds =new CommandeService();
    
    Commande c = new Commande();
    List<LigneCommande> LigneCommandes = new ArrayList<>();

    

    public GeneratePdf(Commande cmd) throws DocumentException {
        
        
        try {
            LigneCommandes = lcs.getLigneCommandesByPanier(cmd.getIdPanier());
            System.out.println("pdf source");
            
            String fileName = "D:\\9raya @_@ !\\3eme annee\\semestre 2\\PI Dev\\JAVA\\tgttest\\tgt\\src\\Pdf_commandes\\cmd"+cmd.getIdPanier()+".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            
            System.out.println("pdf opened");
            document.open();
            
            Paragraph para = new Paragraph("nous somme BitDev .... :D ") ;
            document.add(para);
            
            document.add(new Paragraph("   "));
            document.add(new Paragraph("numero commande : " + cmd.getId()));
            document.add(new Paragraph("   "));
            document.add(new Paragraph("adresse : " + cmd.getAddress()));
            document.add(new Paragraph("   "));
            document.add(new Paragraph("date : " + cmd.getDate()));
            document.add(new Paragraph("   "));
            document.add(new Paragraph("tel : " + cmd.getTel()));
            document.add(new Paragraph("   "));
            document.add(new Paragraph("Les Produits : "));   
            
            for (LigneCommande lg : LigneCommandes){
                document.add(new Paragraph("Produit : "+lg.getNomProduit()+", Quantite :"+lg.getQuantite()+""));
            }


            
            document.close();
            
            
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(GeneratePdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

  
    
}
