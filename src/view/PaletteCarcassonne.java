//import src.view.*;
package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.JeuCarcassonne;
import model.Joueur;

public class PaletteCarcassonne{
    private JeuCarcassonneVue view;
    //private JeuDominoControleur controleur;
    //public JeuDomino modele;

    // Ce constructeur permt l'utilisation de test
    public PaletteCarcassonne(JeuCarcassonne m) throws IOException {
        view = new JeuCarcassonneVue(m);
        view.initialiser();
    }
    
    // Les deux constructeurs suivant permettent d'utiliser Menu
    public PaletteCarcassonne(ArrayList<Joueur> joueurs, JFrame frame) throws IOException {
        view = new JeuCarcassonneVue(joueurs, frame);
        view.initialiser();
    }

    public PaletteCarcassonne(JeuCarcassonneVue v) throws IOException {
        view = v;
        view.initialiser();
    }

    public static void main ( String [] args ) throws IOException {
        //new PaletteCarcassonne();
    }
}