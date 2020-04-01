/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Produit;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Haddad
 */
 
 public interface IserviceProduit {
    public void addProduct(Produit p)throws SQLException;
    public void deleteProduct(int id)throws SQLException;
    public void updateProduct(Produit p)throws SQLException;
    public List<Produit> getProduct() throws SQLException;
    public Produit getById(int id)throws SQLException;
}
