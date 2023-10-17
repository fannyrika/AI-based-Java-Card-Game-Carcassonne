package model;
public abstract class Plateau {
    public Case[][] cases;
    private int longueur, largeur;
    //private AffichageTerminal AffichageT;
    //private AffichageGraphic AffichageG;
    
    public Plateau(int longueur, int largeur){
        // Taille maximal de largeur est de 12, donc pas oublie de mettre une restriction dessus
        this.longueur = longueur;
        this.largeur = largeur;
        cases = new Case[longueur][largeur];
        for(int i = 0; i < longueur; i++){
            for(int j = 0; j < largeur; j++) cases[i][j] = new Case(null);
        }
    }

    public boolean horsLimite(int x, int y){
        return !((x < longueur && x >= 0) && (y < largeur && y >= 0));
    }

    // Renvoie l'indice du tableau de domino en fonction de la rotation
    //public static int indiceRotate(int x, int rotate){
    //    if(x + (rotate * 3) < 12) return x + (rotate * 3);
    //    else return x - (rotate * 3);
    //}

    // Affichage terminal du plateau
    public void afficher(){
        System.out.print("\n  ");
        for(int i = 0; i < largeur; i++) System.out.print("       " + i + "     ");
        System.out.print("\n  ");
        for(int i = 0; i < largeur; i++) System.out.print("-------------");
        System.out.println();
        for(int i = 0; i < longueur; i++){
            System.out.print("  |");
            for(int j = 0; j < largeur; j++) System.out.print(PieceDomino.afficherDebut((PieceDomino) cases[i][j].getPiece()));
            System.out.print("\n  |");
            for(int j = 0; j < largeur; j++) System.out.print(PieceDomino.afficherLigne1((PieceDomino) cases[i][j].getPiece()));
            System.out.print("\n" + i + ((i < 10)? " |" : "|"));
            for(int j = 0; j < largeur; j++) System.out.print(PieceDomino.afficherLigne2((PieceDomino) cases[i][j].getPiece()));
            System.out.print("\n  |");
            for(int j = 0; j < largeur; j++) System.out.print(PieceDomino.afficherLigne3((PieceDomino) cases[i][j].getPiece()));
            System.out.print("\n  |");
            for(int j = 0; j < largeur; j++) System.out.print(PieceDomino.afficherFin((PieceDomino) cases[i][j].getPiece()));
            System.out.println();
        }
    }

    public abstract int placerPiece(Piece p, int x, int y);

    public abstract int pointMarque(Piece p, int x, int y);
}





