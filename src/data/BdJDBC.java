package data;

import beans.Utilisateur;
import java.sql.PreparedStatement;
//import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.sql.Statement;

/**
 *
 * @author gth
 */
public class BdJDBC {
    private String driverMySQL = "com.mysql.jdbc.Driver";
    private String urlMysql= "jdbc:mysql://127.0.0.1:3306/utilisateur";
    Connection connection=null;
    PreparedStatement stm;
    ResultSet rs;
    ResultSetMetaData meta;
    
    private String loginBD = "root";
    private String pwdBD= "";
    public BdJDBC() {
        super();
    }
    
    public void chargerDriver(){
        try {
            Class.forName(driverMySQL);
            System.out.println("Driver chargé");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver error:" + ex);
        }
        
    }
    
    public void connexion(){
        try {
            connection= DriverManager.getConnection(urlMysql,loginBD,pwdBD);
            System.out.println("Connexion reussi " + urlMysql);
        } catch (SQLException ex) {
            System.err.println("Erreurr de connexion: "+ ex);
        }
    }
    
    public List<Utilisateur> getDataDB(){
        
        List<Utilisateur> result;
            result = new ArrayList<Utilisateur>();
        try {
            stm=connection.prepareStatement("Select * from utilisateur");
            rs=stm.executeQuery();
            meta=rs.getMetaData();
            while(rs.next()){
                Utilisateur util= new Utilisateur();
                //util.setLogin(meta.getColumnName(2));
                //util.setLogin(meta.getColumnName(3));
                util.setPassw(rs.getString(2));
                result.add(util);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BdJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return result;
        
       
    }
    
    
}
