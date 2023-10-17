package model;
public abstract class Piece<T> {
    private static int ID = 0;
    protected int rotate;
    protected String chemin="";

    T[] border = (T[])new Object[12];
     /*
      *    0  1  2 
      * 11         3
      * 10         4
      *  9         5
      *    8  7  6
      */
    public Piece(){
    }
    public Piece(T a, T b, T c, T d, T e,T f,T g,T h,T i,T j,T x,T y){
        border[0] = a;
        border[1] = b;
        border[2] = c;
        border[3] = d;
        border[4] = e;
        border[5] = f;
        border[6] = g;
        border[7] = h;
        border[8] = i;
        border[9] = j;
        border[10] = x;
        border[11] = y;
    }


    public int getRotation(){ return rotate;}

    public int getID(){ return ID;}
    
    public T getBorder(int i){ return border[i]; }

    
    public void rotate(int r)//clockwise
    {
        if(r==1)
            rotate=3;
        else if(r==3)
            rotate=1;
        else rotate=r;
        T[] newBorder = (T[])new Object[12];
        rotate = (rotate > 3)? rotate % 3 : rotate;
        int x = rotate * 3;
        for(int i = 0; i < 12; i++){
            newBorder[i] = border[x];
            x++;
            if(x > 11) x = 0;
        }
        border = newBorder;
    }

    public String getChemin(){
        return chemin;
    }

    //to modify
    public static String afficherDebut(Piece p){
        if(p != null) return ("   " + p.border[0] + "  " + p.border[1] + "  " + p.border[2] + "   ");
        else return ("   *  *  *   ");
    }

    public static String afficherLigne1(Piece p){
        if(p != null) return (" " + p.border[11] + "         " + p.border[3] + " ");
        else return (" *         * ");

    }

    public static String afficherLigne2(Piece p){
        if(p != null) return (" " + p.border[10] + "         " + p.border[4] + " ");
        else return (" *         * ");

    }

    public static String afficherLigne3(Piece p){
        if(p != null) return (" " + p.border[9] + "         " + p.border[5] + " ");
        else return (" *         * ");
    }

    public static String afficherFin(Piece p){
        if(p != null) return ("   " + p.border[8] + "  " + p.border[7] + "  " + p.border[6] + "   ");
        else return ("   *  *  *   ");
    }
    
    public void afficherPiece(Piece p){
        System.out.println(afficherDebut(p) + "\n" + afficherLigne1(p) + "\n" + afficherLigne2(p) + "\n" + afficherLigne3(p) + "\n" + afficherFin(p));
    }
}
