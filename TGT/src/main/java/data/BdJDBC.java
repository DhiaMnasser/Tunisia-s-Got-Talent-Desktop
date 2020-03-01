package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author gth
 * @projet TGT
 * @creation 01/03/20
 * @modify 010/03/20
 * 
 */
public class BdJDBC {
    
    private String driverMySQL = "com.mysql.jdbc.Driver";
    private String urlMysql= "jdbc:mysql://127.0.0.1:3306/tgt";
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
   
}
