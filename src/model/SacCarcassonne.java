package model;
import java.util.Random;

public class SacCarcassonne extends Sac {

    public SacCarcassonne(){
        for(int i=0;i<8;i++) super.pieces.add(new abbaye());
        for(int i=0;i<2;i++) super.pieces.add(new carrefour());
        for(int i=0;i<16;i++) super.pieces.add(new chemin());
        for(int i=0;i<10;i++) super.pieces.add(new ville());
        for(int i=0;i<6;i++) super.pieces.add(new villeChemin());
    }

    public PieceCarcassonne piocher(){
        System.out.println("------Il reste "+pieces.size()+" toiles dans le sac.-------");
        // créer un objet de type Random
        Random rd = new Random();
        // produire un nombre entier aléatoire r, 0 <= r < size
        int n = rd.nextInt(pieces.size());
        Piece pieceTarget = pieces.get(n);
        pieces.remove(n);
        //debug
        //System.out.println(n);
        return (PieceCarcassonne)pieceTarget;
    }
}
