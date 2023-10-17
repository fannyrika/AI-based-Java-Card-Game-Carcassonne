package model;

import java.util.ArrayList;
import controller.Communication;

public class JeuDomino extends Jeu{
    public SacDomino sac;
    public PlateauDomino plateau;
    public PieceDomino pieceCourante,pieceInit;

    public JeuDomino(int nbIA, int nbHumain){
        super(nbIA, nbHumain);
        
        plateau=new PlateauDomino(8,9);
        sac=new SacDomino();
        pieceInit=(PieceDomino)sac.piocher(); //debug
        //Piece pieceInit=new PieceDomino(1,1,1,2,2,2,3,3,3,4,4,4);
        plateau.cases[4][4].setPiece(pieceInit);
        pieceCourante=pieceInit;
    }

    // Ce constructeur permet d'utiliser le fichier Menu
    public JeuDomino(ArrayList<Joueur> joueurs, boolean terminal){
        super(joueurs);
        int x = 8;
        int y = 9;
        if(!terminal) plateau=new PlateauDomino(8,9);
        else {
            int[] taille = (new Communication()).getTaillePlateau();
            plateau = new PlateauDomino(taille[0],taille[1]);
            x = taille[0];
            y = taille[1];
        }
        sac=new SacDomino(x * y);
        pieceInit=(PieceDomino)sac.piocher(); //debug
        //Piece pieceInit=new PieceDomino(1,1,1,2,2,2,3,3,3,4,4,4);
        plateau.cases[(int) x / 2][(int) y / 2].setPiece(pieceInit);
        pieceCourante=pieceInit;
    }


    public void jouerTourTerminal(Joueur joueurCourant){
        int x,y,rotation;
        PieceDomino pieceCourante = (PieceDomino)sac.piocher();
        System.out.println("Voici ta toile:");
        pieceCourante.afficherPiece(pieceCourante);
        
        if(joueurCourant.passer()){
        //passe son tour
            System.out.println(joueurCourant);
            return;
        }
        
        x= joueurCourant.choisirX();
        y= joueurCourant.choisirY();
        rotation= joueurCourant.choisirRotation();
        pieceCourante.rotate(rotation);
        joueurCourant.setScore(joueurCourant.getScore() + plateau.placerPiece(pieceCourante, x, y));
        System.out.println(joueurCourant);
    }

    public void jouerPartieTerminal(){
        
        while(true){
            for(int i=0; i<joueurs.size(); i++){
                //le joueur courant change
                Joueur joueurCourant=joueurs.get(i);
                System.out.println("============================");
                plateau.afficher();
                if(sac.estVide())
                    break;
                jouerTourTerminal(joueurCourant);
            }
            if(sac.estVide())
                break;
        }
    }
}
