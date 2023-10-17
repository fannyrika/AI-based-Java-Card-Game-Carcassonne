package model;

import java.util.Random;

public class JoueurIA extends Joueur{
    public JoueurIA(){
        super();
    }

    public JoueurIA(String nom){
        super(nom);
    }

    public boolean passer(){
        // créer un objet de type Random
        Random rd = new Random();
        // produire un nombre entier aléatoire r, 0 <= r < 2
        int n = rd.nextInt(2);
        if(n==0){
            System.out.println("IA: Je ne vais passer la toile.");
            return false;
        }
        System.out.println("IA: Je vais passer la toile.");
        return true;
    }

    public int choisirX(){
        // créer un objet de type Random
        Random rd = new Random();
        // produire un nombre entier aléatoire r, 0 <= r < 8
        int n = rd.nextInt(8);
        System.out.println("IA: J'ai choisi la position x="+n);
        return n;
    }

    public int choisirY(){
        // créer un objet de type Random
        Random rd = new Random();
        // produire un nombre entier aléatoire r, 0 <= r < 9
        int n = rd.nextInt(9);
        System.out.println("IA: J'ai choisi la position y="+n);
        return n;
    }
    
    public int choisirRotation(){
        // créer un objet de type Random
        Random rd = new Random();
        // produire un nombre entier aléatoire r, 0 <= r < 4
        int n = rd.nextInt(4);
        System.out.println("IA: J'ai choisi la rotation="+n);
        return n;
    }

    public String toString(){
        return("Joueur"+id+" "+"(IA) - score:"+score+" - evaluation finale:"+evaluationFinale);
    }

    public int choisirPionPosition() {
        // créer un objet de type Random
        Random rd = new Random();
        // produire un nombre entier aléatoire r, 0 <= r < 13
        int n = rd.nextInt(13);
        System.out.println("IA: J'ai choisi la position de pion:"+n);
        return n;
    }

    @Override
    public void placerPion(int pos, PieceCarcassonne piece) {
        // TODO Auto-generated method stub
        
    }

    public boolean isIA(){ return true;}
}
