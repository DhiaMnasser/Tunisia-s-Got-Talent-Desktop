/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assistance.Services;

import java.sql.SQLException;
import Assistance.Entities.Appreciation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author frauDEee
 */
public interface iAppreciation {
    public void addAppreciation(Appreciation a)throws SQLException;
    public List<Appreciation> getAllAppreciations() throws SQLException;
    public void deleteAppreciation(int id)throws SQLException;
    public void updateAppreciationLike(Appreciation app)throws SQLException;
    public void updateAppreciationDisLike(Appreciation app)throws SQLException;
    
}
