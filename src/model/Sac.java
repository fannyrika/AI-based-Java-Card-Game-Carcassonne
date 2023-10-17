package model;

import java.util.ArrayList;

public class Sac {
    protected ArrayList<Piece> pieces=new ArrayList<Piece>();

    public Sac(){}

    public boolean estVide(){
        return pieces.isEmpty();
    }

}
