package service;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BdJDBC {

    private static BdJDBC instance;
    static Connection con = null;
    private String url = "jdbc:mysql://localhost/tuto";
    private String driver= "com.mysql.jdbc.Driver";
    private String user = "root";
    private String password = "";

    private BdJDBC()
    {
        try {
            if(con==null){
                Class.forName(driver);
                con= DriverManager.getConnection(url,user,password);
                System.out.println("Connexion réussie");
            }
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public static BdJDBC getInstance() {
        if (BdJDBC.instance == null) {
            BdJDBC.instance = new BdJDBC();
        }
        return BdJDBC.instance;
    }

    public Connection getConnexion() {
        return this.con;
    }


}
