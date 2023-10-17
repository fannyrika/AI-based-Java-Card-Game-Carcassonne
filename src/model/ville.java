package model;

import util.myUtil;

public class ville extends PieceCarcassonne{
    public ZonePre zonePre1=new ZonePre();
    public ZoneVille zoneVille1=new ZoneVille();

    public ville(){
        super(Decor.VILLE,Decor.VILLE,Decor.VILLE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE );
        chemin=(new myUtil()).getPath("/images/imgCarcassonne/ville.jpg");

        for(int i=0;i<13;i++){
            if(i==0 || i==1 || i==2)
                indexZone.put(i,zoneVille1);
            else
                indexZone.put(i,zonePre1);
        }
        zonesComposants.add(zonePre1);
        zonesComposants.add(zoneVille1);

    }
    public ville(Decor a, Decor b, Decor c, Decor d, Decor e,Decor f,Decor g,Decor h,Decor ii,Decor j,Decor x,Decor y, Decor z){
        border[0] = a;
        border[1] = b;
        border[2] = c;
        border[3] = d;
        border[4] = e;
        border[5] = f;
        border[6] = g;
        border[7] = h;
        border[8] = ii;
        border[9] = j;
        border[10] = x;
        border[11] = y;
        centre=z;
        for(int i=0; i<13; i++){
            pions.add(new Pion(null,-1,-1));
        }
    }
}