package model;

public class ZonePre extends Zone{
    public int nbVillesDansPres=0;

    public ZonePre(){
        super();
    }

    @Override
    public String toString() {
        return "zonePre "+super.toString()+"nbVillesDansPres= "+nbVillesDansPres;
    }

}
