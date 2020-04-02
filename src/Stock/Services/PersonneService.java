/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock.Services;

import Entities.Personne;
import tgt.MyDbConnection;
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


public class PersonneService {

   Connection connexion;
   

    public PersonneService() {
       connexion=MyDbConnection.getInstance().getConnexion();
    }

    void ajouterPersonne(Personne p) throws SQLException {
        String req = "INSERT INTO `personne` (`nom`, `prenom`) VALUES ( '"
                + p.getNom() + "', '" + p.getPrenom() + "') ";
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    void ajouterPersonne2(Personne p) throws SQLException {
        String req = "INSERT INTO `personne` (`nom`, `prenom`) VALUES ( ?, ?) ";
        PreparedStatement pstm = connexion.prepareStatement(req);
        pstm.setString(1, p.getNom());
        pstm.setString(2, p.getPrenom());
        pstm.executeUpdate();
    }

    public List<Personne> getAllPersonnes() throws SQLException {
       List<Personne> personnes = new ArrayList<>();
        
        String req = "select * from personne";
        Statement stm = connexion.createStatement();
        ResultSet result =  stm.executeQuery(req);
        
        while(result.next()){
            Personne p = new Personne(result.getInt(1), result.getString("nom"), result.getString("prenom"));
            personnes.add(p);
        }
        System.out.println("Afficher Personnes :");
        return personnes;
    }

}
