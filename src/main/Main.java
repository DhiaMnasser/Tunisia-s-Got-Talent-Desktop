package main;

import User.beans.User;
import User.view.FntConnexion;
import User.data.BdJDBC;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) { 
        new FntConnexion().setVisible(true);
    }
    
}
