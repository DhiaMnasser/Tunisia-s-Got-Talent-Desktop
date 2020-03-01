package data;

import java.sql.Connection;
import java.sql.DriverManager;
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
    
    Connection conn=null;
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/tgt?useSSL=false","root","");
            JOptionPane.showMessageDialog(null,"Connected to database");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
   
}
