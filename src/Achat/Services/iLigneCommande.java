/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Achat.Services;

import Achat.Entities.LigneCommande;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Klaizer
 */
public interface iLigneCommande {
    
    public void addLigneCommande(LigneCommande lc)throws SQLException;
    public void addLigneCommande2(LigneCommande lc)throws SQLException;
    public void deleteLigneCommande(int id)throws SQLException;
    public void updateLigneCommande(LigneCommande lc,int qte)throws SQLException;
    public List<LigneCommande> getAllLigneCommandes() throws SQLException;
    public LigneCommande getLigneCommandeById(int id)throws SQLException;
  
}
