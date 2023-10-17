package model;

import util.myUtil;

public class chemin extends PieceCarcassonne{
    public ZonePre zonePre1=new ZonePre();
    public ZonePre zonePre2=new ZonePre();
    public ZoneChemin zoneChemin1=new ZoneChemin();

    public chemin(){
        super(Decor.PRE,Decor.CHEMIN,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.CHEMIN,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.CHEMIN );
        chemin=(new myUtil()).getPath("/images/imgCarcassonne/chemin.jpg");

        for(int i=0;i<13;i++){
            if(i==1 || i==12 || i==7)
                indexZone.put(i,zoneChemin1);
            else if(i==2 || i==3 || i==4 || i==5 || i==6)
                indexZone.put(i,zonePre2);
            else
                indexZone.put(i,zonePre1);

            zonesComposants.add(zoneChemin1);
            zonesComposants.add(zonePre1);
            zonesComposants.add(zonePre2);
        }
    }
}
