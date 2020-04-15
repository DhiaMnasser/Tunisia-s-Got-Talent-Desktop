/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tgt.Entities;

/**
 *
 * @author Klaizer
 */
import Achat.Entities.LigneCommande;
import Achat.Entities.Panier;
import Achat.Services.LigneCommandeService;
import Achat.Services.PanierService;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;

public class SendEmailTLS {

    public static void sendCommandeConfrimationMail(String recieverMail, Panier panier) {
        
        PanierService pans = new PanierService();
        LigneCommandeService lcs = new LigneCommandeService();
        ArrayList<LigneCommande> lcList = new ArrayList<>();
        String email = "La liste des produits que vous avez commandés : \n";
        try {
            lcList = (ArrayList<LigneCommande>) lcs.getLigneCommandesByPanier(panier.getId());
        } catch (SQLException ex) {
            Logger.getLogger(SendEmailTLS.class.getName()).log(Level.SEVERE, null, ex);
        }

        final String username = "esprit.bitdev@gmail.com";
        final String password = "espritbitdev6";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("esprit.bitdev@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recieverMail)
            );
            message.setSubject("Notification de Commande");
            for (LigneCommande lc : lcList){
                email+=""+lc.getNomProduit()+"  x"+lc.getQuantite()+"\n\n";
            }
            
            email+="Prix total de votre commande = "+panier.getPrixTotal()+" DT";
            email+="\n\n Merci de magasiner avec nous  @TgtTeam \n\n\n Powered By BitDev!";

            
            message.setText(email);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
