package model;
public class PlateauDomino extends Plateau {
    
    public PlateauDomino(int longueur, int largeur){
        super(longueur, largeur);
    }

    /**
     * @param p Piece courante qui sera possiblement placé
     * @param x Coordonnées de la futur piece dans le plateau
     * @param y Coordonnées de la futur piece dans le plateau
     * @return retournera le nombre de point marqué
     */
    public int placerPiece(Piece p, int x, int y){
        if(horsLimite(x, y)) System.out.println("Impossible à placer la toile ici!");
        else if(cases[x][y].isOccupied()) System.out.println("Cette case est deja occupée!");
        else{
            if(placeable(p, x, y)){
            //if(true){
                cases[x][y].setPiece(p);
                return pointMarque((PieceDomino)p, x, y);
            }
        }
        return 0;
    }

    public int pointMarque(Piece p, int x, int y){
        int points = 0;
        if(!horsLimite(x - 1, y) && cases[x - 1][y].isOccupied()) points += ((int)p.getBorder(0) + (int)p.getBorder(1) + (int)p.getBorder(2));
        if(!horsLimite(x, y - 1) && cases[x][y - 1].isOccupied()) points += ((int)p.getBorder(11) + (int)p.getBorder(10) + (int)p.getBorder(9));
        if(!horsLimite(x, y + 1) && cases[x][y + 1].isOccupied()) points += ((int)p.getBorder(3) + (int)p.getBorder(4) + (int)p.getBorder(5));
        if(!horsLimite(x + 1, y) && cases[x + 1][y].isOccupied()) points += ((int)p.getBorder(8) + (int)p.getBorder(7) + (int)p.getBorder(6));

        return points;
    }

        // Vérifie s'il est possible de placer la pièce (prend aussi en compte la rotation)
    // Fonctionne que si le jeu est Domino
    public boolean placeable(Piece p, int x, int y){
        int tmp = 0;
        if(!(horsLimite(x - 1, y)) && cases[x - 1][y].isOccupied()){
            tmp++;
            if((cases[x - 1][y].getPiece()).getBorder(8) != p.getBorder(0)) return false;
            if((cases[x - 1][y].getPiece()).getBorder(7) != p.getBorder(1)) return false;
            if((cases[x - 1][y].getPiece()).getBorder(6) != p.getBorder(2)) return false;
        }
        if(!(horsLimite(x, y - 1)) && cases[x][y - 1].isOccupied()){
            tmp++;
            if((cases[x][y - 1].getPiece()).getBorder(3) != p.getBorder(11)) return false;
            if((cases[x][y - 1].getPiece()).getBorder(4) != p.getBorder(10)) return false;
            if((cases[x][y - 1].getPiece()).getBorder(5) != p.getBorder(9)) return false;
        }
        if(!(horsLimite(x, y + 1)) && cases[x][y + 1].isOccupied()){
            tmp++;
            if((cases[x][y + 1].getPiece()).getBorder(11) != p.getBorder(3)) return false;
            if((cases[x][y + 1].getPiece()).getBorder(10) != p.getBorder(4)) return false;
            if((cases[x][y + 1].getPiece()).getBorder(9) != p.getBorder(5)) return false;
        }
        if(!(horsLimite(x + 1, y)) && cases[x + 1][y].isOccupied()){
            tmp++;
            if((cases[x + 1][y].getPiece()).getBorder(0) != p.getBorder(8)) return false;
            if((cases[x + 1][y].getPiece()).getBorder(1) != p.getBorder(7)) return false;
            if((cases[x + 1][y].getPiece()).getBorder(2) != p.getBorder(6)) return false;
        }

        // Le dernier if sert à empêcher de poser un domino à côté d'au moins un domino
        //System.out.println("tmp"+tmp);//debug
        

        if(tmp > 0) return true;
        return false;
    }
}





