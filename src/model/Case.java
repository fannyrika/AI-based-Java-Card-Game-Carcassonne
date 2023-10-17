package model;
public class Case {
    private Piece piece;
    private boolean occupee;

    public Case(Piece piece){
        this.piece = piece;
        if(piece == null) occupee = false;
        else occupee = true;
    }

    public boolean isOccupied(){ return occupee; }

    public void setPiece(Piece p){ 
        piece = p;
        occupee=true;
    }

    public Piece getPiece(){ return piece; }
}
