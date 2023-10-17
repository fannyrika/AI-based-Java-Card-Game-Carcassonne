package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class PieceCarcassonne extends Piece{
    protected Decor centre;
    public ArrayList<Pion> pions=new ArrayList<Pion>(13);
    public HashSet<Zone> zonesComposants=new HashSet<Zone>();
    public Map<Integer,Zone> indexZone = new HashMap<Integer,Zone>(13); 

    public enum Decor
	{
		VILLE("V"),
        CHEMIN("C"),
        PRE("P"),
        CARREFOUR("+"),
        ABBAYE("A");


        String name;
        Decor(String name){
            this.name = name;
        }
  
        public String toString() {
            return name;
        }
	}

    public PieceCarcassonne(){
        super();
    }

    public PieceCarcassonne(Decor a, Decor b, Decor c, Decor d, Decor e, Decor f, Decor g, Decor h, Decor ii, Decor j, Decor x, Decor y, Decor ex){
        super(a, b, c, d, e, f, g, h, ii, j, x, y);
        centre=c;
        for(int i=0; i<13; i++){
            pions.add(new Pion(null,-1,-1));
        }
    }

    public void placerPion(Pion pion,int pos){
        System.out.println("pos:"+pos);
        System.out.println(pions);
        pions.set(pos, pion);
        System.out.println("indexZone:"+indexZone);//debug
        System.out.println("indexZone.get(pos):"+indexZone.get(pos));//debug
        System.out.println("habitants:" + indexZone.get(pos).habitants);//debug
        System.out.println("nb habitant:"+indexZone.get(pos).habitants.size());//debug
        Zone zone=indexZone.get(pos);
        zone.habitants.add(pion);//attention rotate?
    }

    public void rotate(int r){
        if(r==1)
            rotate=3;
        else if(r==3)
            rotate=1;
        else rotate=r;
        Decor[] newBorder = new Decor[12];
        Map<Integer,Zone> newIndexZone = new HashMap<Integer,Zone>(13); 

        rotate = (rotate > 3)? rotate % 3 : rotate;
        int x = rotate * 3;
        for(int i = 0; i < 12; i++){
            newBorder[i] = (Decor)border[x];
            newIndexZone.put(i, indexZone.get(x));
            x++;
            if(x > 11) x = 0;
        }
        border = newBorder;
        newIndexZone.put(12, indexZone.get(12));//fix the rotation problem
        indexZone = newIndexZone;
    }

}