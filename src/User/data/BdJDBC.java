package User.data;

import User.beans.User;
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

/**
 * @author gth
 * @projet TGT
 * @create 01/03/20
 * @modify 01/03/20 18:49
 * @version 1.0.0
 */

public class BdJDBC { 
    
    // variables pour chacun des drivers
    private String driver = "com.mysql.jdbc.Driver";
    
    // variable pour le SGBD utilisé 
    private String userDataBase;
    private String passwordDataBase; 
    
    // url de connexion aux bases de donnees 
    private String url = "jdbc:mysql://127.0.0.1:3306/tgt";


    Connection  connection;
    Statement stm; 
    ResultSet rs; 
    PreparedStatement pstmt; 

    public BdJDBC(String userDataBase, String passwordDataBase) {
        this.userDataBase = userDataBase;
        this.passwordDataBase = passwordDataBase;
    }
    
    public void loadDriver() { 
        try {
            Class.forName(driver);
            System.out.println("Driver MySQL chargé avec succès.");
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(BdJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }         
    } 
    
    // connexion à la base de données  
    public void connectionToDB() {
        try {
            connection = DriverManager.getConnection(url, userDataBase, passwordDataBase);
            System.out.println("Connexion effective à la BD : " + url);
        }
        catch (SQLException  exc)  {
            System.err.println("Error opening SQL connection: " +
            exc.getMessage()); 
        }

    } 
    
    public User getUtilisateur(String log) { 
        User res = null; 
        String query = "SELECT * FROM fos_user WHERE username=?";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, log); 
            rs = pstmt.executeQuery();
            if (rs.next()) {
                res = new User();
                res.setId(rs.getInt(1));
                res.setLogin(rs.getString(4));
                res.setPwd(rs.getString(8));
                res.setProfil(rs.getInt(3));
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(BdJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public int addUtilisateur(User util) { 
        int res = 0; 
        String query = "INSERT INTO fos_user (username, username_canonical, username, password, profil) "
                     + "VALUES (?, ?, ?, ?, ?)";
        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, util.getNom());
            pstmt.setString(2, util.getPrenom());
            pstmt.setString(3, util.getLogin());
            pstmt.setString(4, util.getPwd());
            pstmt.setInt(5, util.getProfil());
            res = pstmt.executeUpdate();            
        } 
        catch (SQLException ex) {
            Logger.getLogger(BdJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public void fermer() { 
        if (pstmt != null) {
            try {
                pstmt.close();
            } 
            catch (SQLException ex) {
                Logger.getLogger(BdJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (connection != null) {
            try {
                connection.close();
            } 
            catch (SQLException ex) {
                Logger.getLogger(BdJDBC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public List<User> getAllUtilisateur() { 
        List<User> res = new ArrayList<User>(); 
        String query = "SELECT * FROM fos_user";
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery(query);
            while (rs.next()) {
                 User util = new User(); 
                 util.setId(rs.getInt(1));
                 util.setNom(rs.getString(2));
                 util.setPrenom(rs.getString(3));
                 util.setLogin(rs.getString(2));
                 util.setPwd(rs.getString(8));
                 util.setProfil(rs.getInt(2));
                 res.add(util);
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(BdJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
}
