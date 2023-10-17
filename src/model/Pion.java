package model;

import java.awt.Color;

public class Pion {
    public Color color;
    public int id;
    public int ownerId;

    public Pion(Color c, int i, int o){
        color=c;
        id=i;
        ownerId=o;
    }

    public void setColor(Color c){
        color=c;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if(ownerId==0)
            return "BLUE "+id;
        if(ownerId==1)
            return "YELLOW "+id;
        if(ownerId==2)
            return "BLACK "+id;
        return "RED "+id;
    }
}
