package beans;

public class Upload {
    private int id;
    private String titre;
    private String description;
    private String categorie;
    private String auteur;
    private String source;


    public Upload(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public Upload(String titre, String categorie, String auteur) {
        this.titre = titre;
        this.categorie = categorie;
        this.auteur = auteur;
    }

    public Upload(int id,String titre, String categorie, String auteur) {
        this.id = id;
        this.titre = titre;
        this.categorie = categorie;
        this.auteur = auteur;
    }

    public Upload(int id, String titre, String description, String categorie, String auteur) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.auteur = auteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


}
