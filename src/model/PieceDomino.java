package model;
public class PieceDomino extends Piece{
    public PieceDomino(){
        super();
    }

    public PieceDomino(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j, int x, int y){
        super(a, b, c, d, e, f, g, h, i, j, x, y);
    }

    //public static void main(String[] args){
    //    PieceDomino p = new PieceDomino(1,1,1,2,2,2,3,3,3,4,4,4);
    //    PieceDomino b = new PieceDomino(1,1,1,2,2,2,3,3,3,4,4,4);
    //    p.afficherPiece(p);
    //    
    //    b.rotate(0);
    //    b.afficherPiece(b);
    //}
}