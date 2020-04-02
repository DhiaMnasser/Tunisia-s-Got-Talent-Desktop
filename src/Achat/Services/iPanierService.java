/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Achat.Services;

import java.sql.SQLException;
import java.util.List;
import Achat.Entities.Panier;

/**
 *
 * @author Klaizer
 */
public interface iPanierService {
     public void addPanier(Panier p)throws SQLException;
     public void addPanier2(Panier p)throws SQLException;
    public void deletePanier(int id)throws SQLException;
    public void updatePanier(Panier p,Double newPrix)throws SQLException;
    public List<Panier> getAllPaniers() throws SQLException;
    public Panier getPanierById(int id)throws SQLException;
    public void changerEtatPanier(Panier p)throws SQLException;

    
}
