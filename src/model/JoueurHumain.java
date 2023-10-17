package model;

import controller.Communication;

public class JoueurHumain extends Joueur{
    private Communication communication;

    public JoueurHumain(){
        super();
        this.communication=new Communication();
    }

    public JoueurHumain(String nom){
        super(nom);
        this.communication=new Communication();
    }
    
    public boolean passer(){
        String response = communication.demanderStr("Si vous ne trouvez pas une correspondance, tappez P pour passer votre tour. Tappez ENTER pour continuer.");
        if(response.equals("P"))
        //passe son tour
            return true;
        return false;
    }

    public int choisirX(){
        return communication.demanderInt("position x=");
    }

    public int choisirY(){
        return communication.demanderInt("position y=");
    }
    
    public int choisirRotation(){
        return communication.demanderInt("Combien de fois(0-3) vous voulez tourner la toile dans le sens des aiguille d'une montre?");
    }

    public String toString(){
        return("Joueur "+id+" (humain) - score: "+score);
    }

    @Override
    public void placerPion(int pos, PieceCarcassonne piece) {
        piece.placerPion(pions.remove(pions.size()-1), pos);
    }

    public boolean isIA(){ return false;}

}
