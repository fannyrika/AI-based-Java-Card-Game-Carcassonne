package model;

public class ZoneAbbaye extends Zone{
    public int nbComposant=1;
    public int xPos;
    public int yPos;

    public ZoneAbbaye() {
        super();
    }
    
    @Override
    public String toString() {
        return "zoneAbbaye"+super.toString();
    }
}
