package model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

public class JeuCarcassonne extends Jeu implements Serializable{
    public transient SacCarcassonne sac;
    public transient PlateauCarcassonne plateau;
    public transient PieceCarcassonne pieceCourante,pieceInit;
    public JeuCarcassonne(){
    }

    public JeuCarcassonne(ArrayList<Joueur> joueurs){
        super(joueurs);
        
        plateau=new PlateauCarcassonne(8,9);
        sac=new SacCarcassonne();
        pieceInit=(PieceCarcassonne)sac.piocher(); //debug
        //Piece pieceInit=new PieceDomino(1,1,1,2,2,2,3,3,3,4,4,4);
        plateau.cases[4][4].setPiece(pieceInit);
        for (Zone zone : pieceInit.zonesComposants) {
            plateau.addZone(zone);
        }
            

        pieceCourante=pieceInit;
    }

    public void evaluationFinale(){
        for (Zone zone : plateau.zones) {
            if(zone!=null){
                if(zone instanceof ZoneAbbaye){
                    for (Pion habitant : zone.habitants) {
                        joueurs.get(habitant.ownerId).evaluationFinale += plateau.compterAbbayeVoisins((ZoneAbbaye)zone);
                    }
                }
                else{
                    int blue, yellow, black, red;
                    blue=0;
                    yellow=0;
                    black=0;
                    red=0;
                    for (Pion habitant : zone.habitants) {
                        if(habitant.color==Color.blue)
                            blue++;
                        else if(habitant.color==Color.yellow)
                            yellow++;
                        else if(habitant.color==Color.black)
                            black++;
                        else if(habitant.color==Color.red)
                            red++;
                    }
                    if(blue==0 && yellow==0 && red==0 && black==0)
                        continue;
                    SortedMap<Integer, Joueur> compterMax = new TreeMap<>();
                    ArrayList<Joueur> joueurList=new ArrayList<Joueur>();
                    if(blue>0){
                        compterMax.put(blue, joueurs.get(0));
                        joueurList.add(joueurs.get(0));}
                    if(yellow>0){
                        compterMax.put(yellow, joueurs.get(1));
                        joueurList.add(joueurs.get(1));}
                    if(black>0){
                        compterMax.put(black, joueurs.get(2));
                        joueurList.add(joueurs.get(2));}
                    if(red>0){
                        compterMax.put(red, joueurs.get(3));
                        joueurList.add(joueurs.get(3));}
                    
                    //equal
                    if(compterMax.firstKey()==compterMax.lastKey()){
                        System.out.println(compterMax.firstKey());
                        System.out.println(compterMax.lastKey());
                        for (Joueur joueur : joueurList) {
                            if(zone instanceof ZonePre)
                                joueur.evaluationFinale += 4*((ZonePre)zone).nbVillesDansPres;
                            else
                                joueur.evaluationFinale += zone.nbComposant;
                        }}
                    else{
                        if(zone instanceof ZonePre)
                            compterMax.get(compterMax.lastKey()).evaluationFinale += 4*((ZonePre)zone).nbVillesDansPres;
                        else
                            compterMax.get(compterMax.lastKey()).evaluationFinale += zone.nbComposant;
                    }
                }
                //else if(zone instanceof ZonePre){
                //    for (Pion habitant : zone.habitants) {
                //        joueurs.get(habitant.ownerId).evaluationFinale += 4*((ZonePre)zone).nbVillesDansPres;
                //    }
                //}
            }
        }

        //pour instant, afficher les resultats en console
        for (Joueur joueur : joueurs) {
            System.out.println(joueur.toString());
        }
    }

}
