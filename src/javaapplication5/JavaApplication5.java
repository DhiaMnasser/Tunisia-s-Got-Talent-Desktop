package javaapplication5;

import beans.Utilisateur;
import vue.FntConnexion;
import data.BdJDBC;
import java.util.ArrayList;
import java.util.List;


public class JavaApplication5 {

    public static void main(String[] args) {
       FntConnexion fnt=new FntConnexion();
       fnt.setVisible(true);
 
       BdJDBC data= new BdJDBC();
       data.chargerDriver();
       data.connexion();
       
       List<Utilisateur> result=new ArrayList<Utilisateur>();
       result=data.getDataDB();
        for (Utilisateur util: result) {
            System.out.println(util);
        }
    
       
    }
    
}
