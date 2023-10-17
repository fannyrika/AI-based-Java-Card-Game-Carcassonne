package model;

import java.util.ArrayList;
import java.util.Random;

import util.myUtil;

public class SacDomino extends Sac {

    public SacDomino(){
        for(int i = 0; i < 50; i++){
            int tmp = (int) Math.random() * 10;
            if(tmp == 0) super.pieces.add(new PieceDominoType0());
            if(tmp == 1) super.pieces.add(new PieceDominoType1());
            if(tmp == 2) super.pieces.add(new PieceDominoType2());
            if(tmp == 3) super.pieces.add(new PieceDominoType3());
            if(tmp == 4) super.pieces.add(new PieceDominoType4());
            if(tmp == 5) super.pieces.add(new PieceDominoType5());
            if(tmp == 6) super.pieces.add(new PieceDominoType6());
            if(tmp == 7) super.pieces.add(new PieceDominoType7());
            if(tmp == 8) super.pieces.add(new PieceDominoType8());
            if(tmp == 9) super.pieces.add(new PieceDominoType9());
        }
    }

    public SacDomino(int max){
        PieceDomino[] tab = {new PieceDominoType0(), new PieceDominoType1(), new PieceDominoType2(), new PieceDominoType3(), new PieceDominoType4(), new PieceDominoType5(), new PieceDominoType6(), new PieceDominoType7(), new PieceDominoType8(), new PieceDominoType9()};
        for(int i = 0; i < max; i++) super.pieces.add(tab[(int) (Math.random() * 10)]);
    }

    private class PieceDominoType0 extends PieceDomino{
        public PieceDominoType0(){
            super(0,1,2,3,4,3,2,1,0,1,2,3);
            super.chemin=(new myUtil()).getPath("/images/imgDomino/0.jpg");
        }
    }

    private class PieceDominoType1 extends PieceDomino{
        public PieceDominoType1(){
            super(3,2,1,0,1,2,3,4,3,2,1,0);
            super.chemin=(new myUtil()).getPath("/images/imgDomino/1.jpg");
        }
    }

    private class PieceDominoType2 extends PieceDomino{
       public PieceDominoType2(){
            super(2, 0, 1, 0, 1, 2, 2, 3, 4, 1, 1, 0);
            super.chemin=(new myUtil()).getPath("/images/imgDomino/2.jpg");
        }
    }

    private class PieceDominoType3 extends PieceDomino{
        public PieceDominoType3(){
             super(2, 2, 2, 2, 0, 1, 0, 1, 2, 2, 2, 2);
             super.chemin=(new myUtil()).getPath("/images/imgDomino/3.jpg");
        }
    }

    private class PieceDominoType4 extends PieceDomino{
        public PieceDominoType4(){
             super(0, 1, 1, 3, 2, 1, 3, 4, 3, 3, 4, 3);
             super.chemin=(new myUtil()).getPath("/images/imgDomino/4.jpg");
        }
    }

    private class PieceDominoType5 extends PieceDomino{
        public PieceDominoType5(){
             super(0, 4, 4, 3, 4, 3, 1, 2, 2, 4, 3, 2);
             super.chemin=(new myUtil()).getPath("/images/imgDomino/5.jpg");
        }
    }

    private class PieceDominoType6 extends PieceDomino{
        public PieceDominoType6(){
             super(2, 0, 3, 3, 0, 2, 2, 3, 4, 2, 2, 1);
             super.chemin=(new myUtil()).getPath("/images/imgDomino/6.jpg");
        }
    }

    private class PieceDominoType7 extends PieceDomino{
        public PieceDominoType7(){
             super(3, 2, 3, 1, 4, 4, 1, 1, 0, 4, 0, 0);
             super.chemin=(new myUtil()).getPath("/images/imgDomino/7.jpg");
        }
    }

    private class PieceDominoType8 extends PieceDomino{
        public PieceDominoType8(){
             super(3, 4, 4, 3, 0, 2, 1, 1, 1, 0, 0, 4);
             super.chemin=(new myUtil()).getPath("/images/imgDomino/8.jpg");
        }
    }

    private class PieceDominoType9 extends PieceDomino{
        public PieceDominoType9(){
             super(3, 3, 1, 1, 0, 0, 2, 1, 1, 4, 4, 1);
             super.chemin=(new myUtil()).getPath("/images/imgDomino/9.jpg");
        }
    }

    public PieceDomino piocher(){
        System.out.println("------Il reste "+pieces.size()+" toiles dans le sac.-------");
        // créer un objet de type Random
        Random rd = new Random();
        // produire un nombre entier aléatoire r, 0 <= r < size
        int n = rd.nextInt(pieces.size());
        Piece pieceTarget = pieces.get(n);
        pieces.remove(n);
        //debug
        //System.out.println(n);
        return (PieceDomino)pieceTarget;
    }
}
