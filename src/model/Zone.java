package model;

import java.util.HashSet;

public abstract class Zone {
    public HashSet<Pion> habitants;
    public int nbComposant=1;
    public int mergedZoneId=-1;
    public boolean isMerged=false;

    public Zone() {
        habitants = new HashSet<Pion>();
    }
    public void merge(Zone other)
    {
        System.out.println("other.habitants"+other.habitants);
        habitants.addAll(other.habitants);
        nbComposant+=other.nbComposant;
        other.isMerged=true;
        System.out.println("--------start: info from merge-----------");
        System.out.println(this);//debug
        System.out.println("--------end: info from merge-----------");
    }
    @Override
    public String toString() {
        return "habitants= "+habitants+"\n nbComposant= "+nbComposant+"\n mergedZoneId= "+mergedZoneId+"\n";
    }
}
