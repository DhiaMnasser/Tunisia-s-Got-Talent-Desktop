package main;

import data.BdJDBC;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gth
 * @projet TGT
 * @creation 01/03/20
 * @modify 010/03/20 15:14
 * 
 */
public class Main {

    public static void main(String[] args) {
 
       BdJDBC data= new BdJDBC();
       data.chargerDriver();
       data.connexion();
       
       /**List<Utilisateur> result=new ArrayList<Utilisateur>();
       result=data.getDataDB();
        for (Utilisateur util: result) {
            System.out.println(util);
        }**/
    
    }
    


}
