package service;

import javafx.scene.control.Alert;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilitaire {
    static  Connection connexion;
    static PreparedStatement pstm;
    static ResultSet rs=null;

    public Utilitaire(){
        connexion = BdJDBC.getInstance().getConnexion();
    }

    //ecrie dans un fichier
    public static void writterFile(String text,String lien){
        File file = new File(lien);
        try(BufferedWriter bufferedWritter = new BufferedWriter(new FileWriter(file))){
            bufferedWritter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //recuperer la premiere ligne d'un fichier : retourne un string
    public static String readFileString(String lien) throws FileNotFoundException {
        File file = new File(lien);
        String line = null;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            line=bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    //recuperer la premiere ligne d'un fichier : retourne un int
    public static int readFileInt(String lien) throws FileNotFoundException {
        File file = new File(lien);
        String line = null;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            line=bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(line);
    }

    //creer un alert
    public static void showDialog(String info,String header,String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(info);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.showAndWait();
    }

    //recuperer l'id du user connecté a laide de son nom dans un fichier
    public static int idCurrentUser(String urlFile) throws  FileNotFoundException {
        String query = "SELECT id FROM user WHERE username=?";
        int res=1;

        try {
            pstm=connexion.prepareStatement(query);
            pstm.setString(1,Utilitaire.readFileString(urlFile));

            rs=pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return res;
    }
}
