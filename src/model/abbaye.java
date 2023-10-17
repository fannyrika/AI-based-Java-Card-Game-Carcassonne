package model;

import util.myUtil;

public class abbaye extends PieceCarcassonne{
    public ZonePre zonePre1=new ZonePre();
    public ZoneAbbaye zoneAbbaye1=new ZoneAbbaye();

    public abbaye(){
        super(Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.PRE,Decor.ABBAYE );
        chemin=(new myUtil()).getPath("/images/imgCarcassonne/abbaye.jpg");

        for(int i=0;i<12;i++)
            indexZone.put(i, zonePre1);
        indexZone.put(12, zoneAbbaye1);

        zonesComposants.add(zoneAbbaye1);
        zonesComposants.add(zonePre1);

    }
}
