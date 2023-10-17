package model;

public class ZoneVille extends Zone{
    public Boolean estAchevee=false;

    public ZoneVille() {
        super();
    }
/* 
    @Override
    public Zone merge(Zone other) {
        habitants.addAll(other.habitants);
        System.out.println("from merge ZoneVille");
        System.out.println(this);//debug
        nbComposant+=other.nbComposant;
        estAchevee=true;

        other.nbComposant=this.nbComposant;
        other.habitants=this.habitants;
        return this;
    }
*/
    @Override
    public String toString() {
        return "zoneVille "+super.toString()+"estAchevee= "+estAchevee;
    }
}
