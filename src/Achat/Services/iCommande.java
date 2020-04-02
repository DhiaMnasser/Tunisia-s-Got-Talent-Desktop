/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Achat.Services;

import Achat.Entities.Commande;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Klaizer
 */

public interface iCommande {
    
    public void addCommande(Commande c)throws SQLException;
    public void validerCommande(Commande c)throws SQLException;
    public List<Commande> getAllCommandes() throws SQLException;
    public List<Commande> getAllCommandesByUser(int idUser) throws SQLException;
    public Commande getCommandeById(int id)throws SQLException;
     public List<Commande> chercherCommande(String query) throws SQLException;
      
}
