package model;

import java.util.ArrayList;

public class Jeu {
    protected int nbIA=0;
    protected int nbHumain=0;
    public ArrayList<Joueur> joueurs;
    public Joueur joueurCourant;
    public boolean shouldContinue=false;
    
    public Jeu(){
    }
    public Jeu(ArrayList<Joueur> joueurs){
        this.joueurs=joueurs;
    }

    public Jeu(int nbIA, int nbHumain){
        joueurs=new ArrayList<Joueur>();
        shouldContinue=false;
        for(int i=0; i<nbHumain; i++){
            joueurs.add(new JoueurHumain());
        }
        for(int i=0; i<nbIA; i++){
            joueurs.add(new JoueurIA());
        }
        for(int i=0;i<joueurs.size();i++){
            joueurs.get(i).setId(i);
        }
    }
    
}
