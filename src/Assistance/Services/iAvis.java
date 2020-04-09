/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assistance.Services;

import Assistance.Entities.Avis;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author frauDEee
 */
public interface iAvis {
    public void addAvis(Avis a)throws SQLException;
    public List<Avis> getAllAvis() throws SQLException;
    public void deleteAvis(int id)throws SQLException;
    
}
