/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock.Services;

import Stock.Entities.Categorie;
import java.sql.SQLException;
import java.util.List;

public interface IserviceCategorie {
    public void addCategorie(Categorie p)throws SQLException;
    public void deleteCategorie(int id)throws SQLException;
    public void updateCategorie(Categorie p)throws SQLException;
    public List<Categorie> getCategorie() throws SQLException;
    public Categorie getById(int id)throws SQLException;
}
