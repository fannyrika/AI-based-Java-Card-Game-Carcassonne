package model;
import java.awt.Color;
import java.util.ArrayList;

public abstract class Joueur {
    protected String pseudo;
    public int score;
    protected int scoreIncrement;
    public int evaluationFinale=0;
    public int id;
    public ArrayList<Pion> pions;
    protected Color pionColor;

    public Joueur(){
        this.pseudo="";
        this.score=0;
        this.scoreIncrement=0;
        this.id=-1;
        this.pions=new ArrayList<Pion>(10);
    }

    public Joueur(String name){
        this.pseudo=name;
        this.score=0;
        this.scoreIncrement=0;
        this.id=-1;
        this.pions=new ArrayList<Pion>(10);
    }
    
    public String getPseudo(){
        return pseudo;
    }
    public void setPseudo(String p){
        this.pseudo=p;
    }
    public void setId(int id){
        this.id=id;
        setPionColor();
        for(int i=0;i<10;i++){
            pions.add(new Pion(pionColor,i,id));
        }
    }
    public int getId(){
        return id;
    }
    public void setScore(int score){
        this.score=score;
    }
    public void scoreIncrease(int scoreIncre){
        this.scoreIncrement=scoreIncre;
        this.score+=scoreIncre;
    }
    public int getScoreIncrement(){
        return scoreIncrement;
    }
    public void resetScoreIncrement(){
        scoreIncrement=0;
    }
    public int getScore(){
        return score;
    }
    public int getNbPion(){
        return pions.size();
    }
    public void setPionColor(){
        if(id==0)
            pionColor=Color.blue;
        else if(id==1)
            pionColor=Color.yellow;
        else if(id==2)
            pionColor=Color.black;
        else if(id==3)
            pionColor=Color.red;
    }

    public Color getPionColor(){
        return pionColor;
    }

    public abstract boolean passer();

    public abstract int choisirX();

    public abstract int choisirY();
    
    public abstract int choisirRotation();

    public abstract void placerPion(int pos, PieceCarcassonne piece);

    public abstract String toString();

    public abstract boolean isIA();

}
