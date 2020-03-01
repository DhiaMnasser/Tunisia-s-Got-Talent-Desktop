package User.beans;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author gth
 */
public class User {
    int id;
    String nom; 
    String prenom; 
    String login;
    String pwd; 
    int profil; 
    Date date_creation; 
    Date heure_creation; 
    Date date_maj; 
    Date heure_maj; 
    String observation; 

    public User() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getProfil() {
        return profil;
    }

    public void setProfil(int profil) {
        this.profil = profil;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getHeure_creation() {
        return heure_creation;
    }

    public void setHeure_creation(Date heure_creation) {
        this.heure_creation = heure_creation;
    }

    public Date getDate_maj() {
        return date_maj;
    }

    public void setDate_maj(Date date_maj) {
        this.date_maj = date_maj;
    }

    public Date getHeure_maj() {
        return heure_maj;
    }

    public void setHeure_maj(Date heure_maj) {
        this.heure_maj = heure_maj;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.pwd, other.pwd)) {
            return false;
        }
        return true;
    }
    
    
}
