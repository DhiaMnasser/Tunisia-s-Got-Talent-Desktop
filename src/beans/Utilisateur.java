package beans;

/**
 *
 * @author gth
 */
public class Utilisateur {
    private String login;
    private String passw;

    public Utilisateur(String login, String passw) {
        this.login = login;
        this.passw = passw;
    }

    public Utilisateur() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }
    
    
}
