//import src.view.*;
package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.JeuDomino;
import model.Joueur;

public class PaletteDomino{
    private JeuDominoVue view;
    //private JeuDominoControleur controleur;
    //public JeuDomino modele;

    public PaletteDomino(JeuDomino modelDomino) throws IOException {
        view = new JeuDominoVue(modelDomino);
        view.initialiser();
    }

    public PaletteDomino(JeuDominoVue modelDomino) throws IOException {
        view = modelDomino;
        view.initialiser();
    }
    
    // Ce constructeur permet d'utiliser le fichier Menu
    public PaletteDomino(ArrayList<Joueur> joueurs, JFrame frame) throws IOException {
        view = new JeuDominoVue(joueurs, frame);
        view.initialiser();
    }

    public static void main ( String [] args ) throws IOException {
        // A utiliser car dans la page Menu, à partir du moment où l'on souhaite lancer la partie,
        // l'affichage n'est pas present :
        // new PaletteDomino();
    }
}