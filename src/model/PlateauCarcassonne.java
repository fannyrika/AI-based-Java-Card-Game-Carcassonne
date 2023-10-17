package model;

import java.util.ArrayList;

public class PlateauCarcassonne extends Plateau {
    public ArrayList<Zone> zones=new ArrayList<Zone>();
    
    public PlateauCarcassonne(int longueur, int largeur){
        super(longueur, largeur);
    }

    /**
     * @param p Piece courante qui sera possiblement placé
     * @param x Coordonnées de la futur piece dans le plateau
     * @param y Coordonnées de la futur piece dans le plateau
     * @return retournera le nombre de point marqué
     */
    public int placerPiece(Piece p, int x, int y){
        if(cases[x][y].isOccupied()) System.out.println("Cette case est deja occupée!");
        else{
            if(placeable((PieceCarcassonne)p, x, y)){
            //if(true){
                cases[x][y].setPiece(p);
                return pointMarque((PieceCarcassonne)p, x, y);
            }
        }
        System.out.println("Impossible à placer la toile ici!");
        return 0;
    }

    public int pointMarque(Piece p, int x, int y){
        
        int points = 0;
        if(!horsLimite(x - 1, y) && cases[x - 1][y].isOccupied()) points += 1;
        if(!horsLimite(x, y - 1) && cases[x][y - 1].isOccupied()) points += 1;
        if(!horsLimite(x, y + 1) && cases[x][y + 1].isOccupied()) points += 1;
        if(!horsLimite(x + 1, y) && cases[x + 1][y].isOccupied()) points += 1;

        return points;
    }

    public boolean placeable(PieceCarcassonne p, int x, int y){
        int tmp = 0;
        PieceCarcassonne voisin=new PieceCarcassonne();
        if(!(horsLimite(x - 1, y)) && cases[x - 1][y].isOccupied()){
            tmp++;
            if((cases[x - 1][y].getPiece()).getBorder(8) != p.getBorder(0)) return false;
            if((cases[x - 1][y].getPiece()).getBorder(7) != p.getBorder(1)) return false;
            if((cases[x - 1][y].getPiece()).getBorder(6) != p.getBorder(2)) return false;

            //merge
            voisin=(PieceCarcassonne)cases[x - 1][y].getPiece();
            System.out.println("voisin:"+voisin);
            System.out.println("p.indexzone"+p.indexZone);//debug
            System.out.println("voisin.indexzone"+voisin.indexZone);//debug
            //exist-t-il une ville?
            if(p.indexZone.get(0) instanceof ZoneVille){
                if(tmp>1){
                    //partie ville
                    if( ((ville)voisin).zoneVille1.mergedZoneId != p.indexZone.get(0).mergedZoneId)
                        zones.set( ((ville)voisin).zoneVille1.mergedZoneId, null);
                    zones.get( p.indexZone.get(0).mergedZoneId).merge( ((ville)voisin).zoneVille1 );
                    ((ville)voisin).zoneVille1.mergedZoneId = p.indexZone.get(0).mergedZoneId;

                    ((ZoneVille)(zones.get(((ville)voisin).zoneVille1.mergedZoneId))).estAchevee=true;
                    ((ZonePre)(zones.get(((ville)voisin).zonePre1.mergedZoneId))).nbVillesDansPres++;
                }
                else{
                    //partie ville
                    zones.get(((ville)voisin).zoneVille1.mergedZoneId).merge(p.indexZone.get(0));

                    p.indexZone.get(0).mergedZoneId = ((ville)voisin).zoneVille1.mergedZoneId;
                    System.out.println("((ville)voisin).zoneVille1.mergedZoneId"+((ville)voisin).zoneVille1.mergedZoneId);

                    //partie pre
                    ((ZonePre)(zones.get(((ville)voisin).zonePre1.mergedZoneId))).nbVillesDansPres++;
                    
                    System.out.println("(ville)voisin).zonePre1.mergedZoneId"+((ville)voisin).zonePre1.mergedZoneId);
                    
                    ((ZoneVille)(zones.get(((ville)voisin).zoneVille1.mergedZoneId))).estAchevee=true;
                }

            }
             
            for(int i=0; i<3+0; i++){
                if(tmp>1){//already merged
                    if(voisin.indexZone.get(8-i).mergedZoneId != p.indexZone.get(i).mergedZoneId)
                        zones.set(voisin.indexZone.get(8-i).mergedZoneId,null);
                    zones.get(p.indexZone.get(i).mergedZoneId).merge(voisin.indexZone.get(8-i));
                    voisin.indexZone.get(8-i).mergedZoneId = p.indexZone.get(i).mergedZoneId;
                }
                else{
                    p.indexZone.get(i).mergedZoneId = voisin.indexZone.get(8-i).mergedZoneId;
                    zones.get(voisin.indexZone.get(8-i).mergedZoneId).merge(p.indexZone.get(i));
                    //update the exitance of pion which was just placed
                    //zones.get(voisin.indexZone.get(8-i).mergedZoneId).habitants.addAll(voisin.indexZone.get(8-i).habitants);
                    
            }}
            
            for (Zone zone : p.zonesComposants) {
                if(!zone.isMerged)
                    addZone(zone);
            }
            if(p.indexZone.get(0) instanceof ZoneVille)
                if( ((ville)p).zonePre1.mergedZoneId != ((ville)voisin).zonePre1.mergedZoneId )
                        ((ZonePre)(zones.get(((ville)p).zonePre1.mergedZoneId))).nbVillesDansPres++;
            printZones();
        }
        if(!(horsLimite(x, y - 1)) && cases[x][y - 1].isOccupied()){
            tmp++;
            if((cases[x][y - 1].getPiece()).getBorder(3) != p.getBorder(11)) return false;
            if((cases[x][y - 1].getPiece()).getBorder(4) != p.getBorder(10)) return false;
            if((cases[x][y - 1].getPiece()).getBorder(5) != p.getBorder(9)) return false;

            //merge
            voisin=(PieceCarcassonne)cases[x][y - 1].getPiece();
            System.out.println("voisin:"+voisin);
            System.out.println("p.indexzone"+p.indexZone);//debug
            System.out.println("voisin.indexzone"+voisin.indexZone);//debug

            //exist-t-il une ville?
            if(p.indexZone.get(9) instanceof ZoneVille){
                if(tmp>1){
                    if( ((ville)voisin).zoneVille1.mergedZoneId != p.indexZone.get(9).mergedZoneId)
                        zones.set( ((ville)voisin).zoneVille1.mergedZoneId, null);
                    zones.get( p.indexZone.get(9).mergedZoneId).merge( ((ville)voisin).zoneVille1 );
                    ((ville)voisin).zoneVille1.mergedZoneId = p.indexZone.get(9).mergedZoneId;
                    
                    ((ZoneVille)(zones.get(((ville)voisin).zoneVille1.mergedZoneId))).estAchevee=true;
                    ((ZonePre)(zones.get(((ville)voisin).zonePre1.mergedZoneId))).nbVillesDansPres++;
                }
                else{
                    //partie ville
                    zones.get(((ville)voisin).zoneVille1.mergedZoneId).merge(p.indexZone.get(9));
                    ((ZoneVille)(zones.get(((ville)voisin).zoneVille1.mergedZoneId))).estAchevee=true;

                    p.indexZone.get(9).mergedZoneId = ((ville)voisin).zoneVille1.mergedZoneId;
                    System.out.println("((ville)voisin).zoneVille1.mergedZoneId"+((ville)voisin).zoneVille1.mergedZoneId);

                    //partie pre
                    ((ZonePre)(zones.get(((ville)voisin).zonePre1.mergedZoneId))).nbVillesDansPres++;

                    System.out.println("(ville)voisin).zonePre1.mergedZoneId"+((ville)voisin).zonePre1.mergedZoneId);
                }
                
            }
            for(int i=9; i<3+9; i++){
                if(tmp>1){//already merged
                    if(voisin.indexZone.get(14-i).mergedZoneId != p.indexZone.get(i).mergedZoneId)
                        zones.set(voisin.indexZone.get(14-i).mergedZoneId,null);
                    zones.get(p.indexZone.get(i).mergedZoneId).merge(voisin.indexZone.get(14-i));
                    voisin.indexZone.get(14-i).mergedZoneId = p.indexZone.get(i).mergedZoneId;
                }
                else{
                    p.indexZone.get(i).mergedZoneId = voisin.indexZone.get(14-i).mergedZoneId;
                    zones.get(voisin.indexZone.get(14-i).mergedZoneId).merge(p.indexZone.get(i));
                    //update the exitance of pion which was just placed
                    //zones.get(voisin.indexZone.get(14-i).mergedZoneId).habitants.addAll(voisin.indexZone.get(14-i).habitants);
                    
            }}
            
            for (Zone zone : p.zonesComposants) {
                if(!zone.isMerged)
                    addZone(zone);
            }
            if(p.indexZone.get(9) instanceof ZoneVille)
                if( ((ville)p).zonePre1.mergedZoneId != ((ville)voisin).zonePre1.mergedZoneId )
                    ((ZonePre)(zones.get(((ville)p).zonePre1.mergedZoneId))).nbVillesDansPres++;
            printZones();
        }
        if(!(horsLimite(x, y + 1)) && cases[x][y + 1].isOccupied()){
            tmp++;
            if((cases[x][y + 1].getPiece()).getBorder(11) != p.getBorder(3)) return false;
            if((cases[x][y + 1].getPiece()).getBorder(10) != p.getBorder(4)) return false;
            if((cases[x][y + 1].getPiece()).getBorder(9) != p.getBorder(5)) return false;

            //merge
            voisin=(PieceCarcassonne)cases[x][y + 1].getPiece();
            System.out.println("voisin:"+voisin);
            System.out.println("p.indexzone"+p.indexZone);//debug
            System.out.println("voisin.indexzone"+voisin.indexZone);//debug
            //exist-t-il une ville?
            if(p.indexZone.get(3) instanceof ZoneVille){
                if(tmp>1){
                    if( ((ville)voisin).zoneVille1.mergedZoneId != p.indexZone.get(3).mergedZoneId)
                        zones.set( ((ville)voisin).zoneVille1.mergedZoneId, null);
                    zones.get( p.indexZone.get(3).mergedZoneId).merge( ((ville)voisin).zoneVille1 );
                    ((ville)voisin).zoneVille1.mergedZoneId = p.indexZone.get(3).mergedZoneId;

                    ((ZoneVille)(zones.get(((ville)voisin).zoneVille1.mergedZoneId))).estAchevee=true;
                    ((ZonePre)(zones.get(((ville)voisin).zonePre1.mergedZoneId))).nbVillesDansPres++;
                }
                else{
                    //partie ville
                    zones.get(((ville)voisin).zoneVille1.mergedZoneId).merge(p.indexZone.get(3));
                    ((ZoneVille)(zones.get(((ville)voisin).zoneVille1.mergedZoneId))).estAchevee=true;

                    p.indexZone.get(3).mergedZoneId = ((ville)voisin).zoneVille1.mergedZoneId;
                    System.out.println("((ville)voisin).zoneVille1.mergedZoneId"+((ville)voisin).zoneVille1.mergedZoneId);

                    //partie pre
                    ((ZonePre)(zones.get(((ville)voisin).zonePre1.mergedZoneId))).nbVillesDansPres++;
                    System.out.println("(ville)voisin).zonePre1.mergedZoneId"+((ville)voisin).zonePre1.mergedZoneId);
                }
                
            }
            for(int i=3; i<3+3; i++){
                if(tmp>1){//already merged
                    if(voisin.indexZone.get(14-i).mergedZoneId != p.indexZone.get(i).mergedZoneId)
                        zones.set(voisin.indexZone.get(14-i).mergedZoneId,null);
                    zones.get(p.indexZone.get(i).mergedZoneId).merge(voisin.indexZone.get(14-i));
                    voisin.indexZone.get(14-i).mergedZoneId = p.indexZone.get(i).mergedZoneId;
                }
                else{
                    p.indexZone.get(i).mergedZoneId = voisin.indexZone.get(14-i).mergedZoneId;
                    zones.get(voisin.indexZone.get(14-i).mergedZoneId).merge(p.indexZone.get(i));
                    //update the exitance of pion which was just placed
                    //zones.get(voisin.indexZone.get(14-i).mergedZoneId).habitants.addAll(voisin.indexZone.get(14-i).habitants);
                    
                }
            }
            
            for (Zone zone : p.zonesComposants) {
                if(!zone.isMerged)
                    addZone(zone);
            }
            if(p.indexZone.get(3) instanceof ZoneVille)
                if( ((ville)p).zonePre1.mergedZoneId != ((ville)voisin).zonePre1.mergedZoneId )
                    ((ZonePre)(zones.get(((ville)p).zonePre1.mergedZoneId))).nbVillesDansPres++;
            printZones();
        }
        if(!(horsLimite(x + 1, y)) && cases[x + 1][y].isOccupied()){
            tmp++;
            if((cases[x + 1][y].getPiece()).getBorder(0) != p.getBorder(8)) return false;
            if((cases[x + 1][y].getPiece()).getBorder(1) != p.getBorder(7)) return false;
            if((cases[x + 1][y].getPiece()).getBorder(2) != p.getBorder(6)) return false;

            //merge
            voisin=(PieceCarcassonne)cases[x + 1][y].getPiece();
            System.out.println("voisin:"+voisin);
            System.out.println("p.indexzone"+p.indexZone);//debug
            System.out.println("voisin.indexzone"+voisin.indexZone);//debug
            //exist-t-il une ville?
            if(p.indexZone.get(6) instanceof ZoneVille){
                if(tmp>1){
                    if( ((ville)voisin).zoneVille1.mergedZoneId != p.indexZone.get(6).mergedZoneId)
                        zones.set( ((ville)voisin).zoneVille1.mergedZoneId, null);
                    zones.get( p.indexZone.get(6).mergedZoneId).merge( ((ville)voisin).zoneVille1 );
                    ((ville)voisin).zoneVille1.mergedZoneId = p.indexZone.get(6).mergedZoneId;

                    ((ZoneVille)(zones.get(((ville)voisin).zoneVille1.mergedZoneId))).estAchevee=true;
                    ((ZonePre)(zones.get(((ville)voisin).zonePre1.mergedZoneId))).nbVillesDansPres++;
                }
                else{
                    //partie ville
                    zones.get(((ville)voisin).zoneVille1.mergedZoneId).merge(p.indexZone.get(6));
                    ((ZoneVille)(zones.get(((ville)voisin).zoneVille1.mergedZoneId))).estAchevee=true;

                    p.indexZone.get(6).mergedZoneId = ((ville)voisin).zoneVille1.mergedZoneId;
                    System.out.println("((ville)voisin).zoneVille1.mergedZoneId"+((ville)voisin).zoneVille1.mergedZoneId);

                    //partie pre
                    ((ZonePre)(zones.get(((ville)voisin).zonePre1.mergedZoneId))).nbVillesDansPres++;

                    System.out.println("(ville)voisin).zonePre1.mergedZoneId"+((ville)voisin).zonePre1.mergedZoneId);
                }
                
            }
            for(int i=6; i<3+6; i++){
                if(tmp>1){//already merged
                    //if not in the same zone (of plateau)
                    if(voisin.indexZone.get(8-i).mergedZoneId != p.indexZone.get(i).mergedZoneId)
                        zones.set(voisin.indexZone.get(8-i).mergedZoneId,null);
                    
                    //debug
                    //System.out.println("p.indexZone.get(i);"+p.indexZone.get(i));
                    //System.out.println("voisin.indexZone.get(8-i);"+voisin.indexZone.get(8-i));

                    zones.get(p.indexZone.get(i).mergedZoneId).merge(voisin.indexZone.get(8-i));
                    voisin.indexZone.get(8-i).mergedZoneId = p.indexZone.get(i).mergedZoneId;
                }
                else{
                    p.indexZone.get(i).mergedZoneId = voisin.indexZone.get(8-i).mergedZoneId;
                    zones.get(voisin.indexZone.get(8-i).mergedZoneId).merge(p.indexZone.get(i));
                    //update the exitance of pion which was just placed
                    //zones.get(voisin.indexZone.get(8-i).mergedZoneId).habitants.addAll(voisin.indexZone.get(8-i).habitants);
                    
            }}
            
            for (Zone zone : p.zonesComposants) {
                if(!zone.isMerged)
                    addZone(zone);
            }
            if(p.indexZone.get(6) instanceof ZoneVille)
                    if( ((ville)p).zonePre1.mergedZoneId != ((ville)voisin).zonePre1.mergedZoneId )
                        ((ZonePre)(zones.get(((ville)p).zonePre1.mergedZoneId))).nbVillesDansPres++;
            printZones();
        }

        if(p.indexZone.get(12) instanceof ZoneAbbaye){
            ((ZoneAbbaye)(zones.get(p.indexZone.get(12).mergedZoneId))).xPos=x;
            ((ZoneAbbaye)(zones.get(p.indexZone.get(12).mergedZoneId))).yPos=y;
        }
        // Le dernier if sert à empêcher de poser un domino à côté d'au moins un domino
        //System.out.println("tmp"+tmp);//debug
        if(tmp > 0) return true;
        return false;
    }

    public void printZones() {
        System.out.println("=================Start printing zones================");
        for(int i=0; i<zones.size(); i++){
            System.out.println(zones.get(i));
        }
        System.out.println("=================End printing zones================");
    }

    public void addZone(Zone newZone){
        if(newZone.mergedZoneId==-1){// == not contain newZone
            zones.add(newZone);
            newZone.mergedZoneId=(zones.size()-1);
        }
    }

    public int compterAbbayeVoisins(ZoneAbbaye zoneAbbaye){
        int nbVoisin=1;
        int x=zoneAbbaye.xPos;
        int y=zoneAbbaye.yPos;
        System.out.println("xPos"+zoneAbbaye.xPos);
        System.out.println("yPos"+zoneAbbaye.yPos);

        if(!(horsLimite(x, y - 1)) && cases[x][y - 1].isOccupied())
            nbVoisin++;
        if(!(horsLimite(x, y + 1)) && cases[x][y + 1].isOccupied())
            nbVoisin++;
        if(!(horsLimite(x-1, y - 1)) && cases[x-1][y - 1].isOccupied())
            nbVoisin++;
        if(!(horsLimite(x-1, y)) && cases[x-1][y].isOccupied())
            nbVoisin++;
        if(!(horsLimite(x-1, y + 1)) && cases[x-1][y + 1].isOccupied())
            nbVoisin++;
        if(!(horsLimite(x+1, y - 1)) && cases[x+1][y - 1].isOccupied())
            nbVoisin++;
        if(!(horsLimite(x+1, y)) && cases[x+1][y].isOccupied())
            nbVoisin++;
        if(!(horsLimite(x+1, y + 1)) && cases[x+1][y + 1].isOccupied())
            nbVoisin++;

        return nbVoisin;
    }

    //public boolean prepareToMerge(PieceCarcassonne p, int x, int y){

}





