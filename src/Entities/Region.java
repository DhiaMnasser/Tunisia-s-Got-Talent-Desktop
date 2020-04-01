/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Achraf
 */
public class Region {
     private int id;
    private String Nom;
    private int Nb_villes;
    
    /*************************************************/

    public Region() {
    }

    public Region(int id, String Nom, int Nb_villes) {
        this.id = id;
        this.Nom = Nom;
        this.Nb_villes = Nb_villes;
    }

    public Region(String Nom, int Nb_villes) {
        this.Nom = Nom;
        this.Nb_villes = Nb_villes;
    }

  
    
    /********************************************************/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getNb_villes() {
        return Nb_villes;
    }

    public void setNb_villes(int Nb_villes) {
        this.Nb_villes = Nb_villes;
    }
    
    /*****************************************************/

    @Override
    public String toString() {
        return "Region{" + "id=" + id + ", Nom=" + Nom + ", Nb_villes=" + Nb_villes + '}';
    }
    
    
    
    
    
    
    
    
}
