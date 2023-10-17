package model;

import util.myUtil;

public class carrefour extends PieceCarcassonne{
    public ZonePre zonePre1=new ZonePre();
    public ZonePre zonePre2=new ZonePre();
    public ZonePre zonePre3=new ZonePre();
    public ZonePre zonePre4=new ZonePre();
    public ZoneChemin zoneChemin1=new ZoneChemin();

    public carrefour(){
        super(Decor.PRE,Decor.CHEMIN,Decor.PRE,Decor.PRE,Decor.CHEMIN,Decor.PRE,Decor.PRE,Decor.CHEMIN,Decor.PRE,Decor.PRE,Decor.CHEMIN,Decor.PRE,Decor.CARREFOUR );
        chemin=(new myUtil()).getPath("/images/imgCarcassonne/carrefour.jpg");

        indexZone.put(11, zonePre1);
        indexZone.put(0, zonePre1);

        indexZone.put(2, zonePre2);
        indexZone.put(3, zonePre2);

        indexZone.put(5, zonePre3);
        indexZone.put(6, zonePre3);

        indexZone.put(8, zonePre4);
        indexZone.put(9, zonePre4);

        indexZone.put(1,zoneChemin1);
        indexZone.put(4,zoneChemin1);
        indexZone.put(7,zoneChemin1);
        indexZone.put(10,zoneChemin1);
        indexZone.put(12,zoneChemin1);

        zonesComposants.add(zoneChemin1);
        zonesComposants.add(zonePre4);
        zonesComposants.add(zonePre3);
        zonesComposants.add(zonePre2);
        zonesComposants.add(zonePre1);
        
    }
}