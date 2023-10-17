package model;

import util.myUtil;

public class villeChemin extends ville{
    public ZonePre zonePre2=new ZonePre();
    public ZoneChemin zoneChemin1=new ZoneChemin();

    public villeChemin(){
        super(Decor.VILLE,Decor.VILLE,Decor.VILLE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.CHEMIN,Decor.PRE,Decor.PRE,Decor.CHEMIN,Decor.PRE,Decor.PRE );
        chemin=(new myUtil()).getPath("/images/imgCarcassonne/villeChemin.jpg");

        for(int i=0;i<13;i++){
            if(i==0 || i==1 || i==2)
                indexZone.put(i,zoneVille1);
            else if(i==8 || i==9)
                indexZone.put(i,zonePre2);
            else if(i==7 || i==10)
                indexZone.put(i,zoneChemin1);
            else
                indexZone.put(i,zonePre1);
        }
        zonesComposants.add(zoneChemin1);
        zonesComposants.add(zonePre1);
        zonesComposants.add(zonePre2);
        zonesComposants.add(zoneVille1);
    }
}
