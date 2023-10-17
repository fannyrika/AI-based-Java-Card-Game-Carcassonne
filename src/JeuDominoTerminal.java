
import java.util.ArrayList;

import controller.Communication;
import model.JeuDomino;
import model.*;

public class JeuDominoTerminal {
    public JeuDominoTerminal(){
        Communication communication=new Communication();
        int nbIA,nbHumain;
        nbHumain= communication.demanderInt("Veuillez choisir le nombre des joueurs humain(0-3) : ");
        nbIA= communication.demanderInt("Veuillez choisir le nombre des joueurs IA(0-3) : ");
        ArrayList<Joueur> joueurs=new ArrayList<Joueur>();
        int tmp = 1;
        for(int i = 0; i < nbIA; i++){
            Joueur joueur = new JoueurIA();
            joueur.setId(tmp);
            joueurs.add(joueur);
            tmp++;
        }
        for(int i = 0; i < nbHumain; i++){
            Joueur joueur = new JoueurHumain();
            joueur.setId(tmp);
            joueurs.add(joueur);
            tmp++;
        }

        JeuDomino jeu = new JeuDomino(joueurs, true);
        for(int i=0; i<jeu.joueurs.size(); i++){
            //le joueur courant change
            Joueur joueurCourant=jeu.joueurs.get(i);
            System.out.println(joueurCourant.toString());
        }
        jeu.jouerPartieTerminal();
        for(int i=0; i<jeu.joueurs.size(); i++){
            //le joueur courant change
            Joueur joueurCourant=jeu.joueurs.get(i);
            System.out.println(joueurCourant.toString());
        }
    }

    public static void main(String[] args){
        new JeuDominoTerminal();
    }
}
