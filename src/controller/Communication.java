package controller;
//from tp3
import java.util.Scanner;

public class Communication {
    private Scanner scanReponse;

    public Communication(){
        this.scanReponse = new Scanner(System.in);
    }

    public String demanderStr(String q){
        System.out.print(q);
        return scanReponse.nextLine();
    }

    public int demanderInt(String q){
        System.out.print(q);
        int reponse=scanReponse.nextInt();
        scanReponse.nextLine();//absorber '\n'
        return reponse;
    }
    
    public int[] demanderCoordonnes(){
        int[] coo=new int[2];
        String reponse=demanderStr("Donnez une coordonnée(par exemple 'B6'):");
        coo[0]=Character.getNumericValue(reponse.charAt(0))-9;
        coo[1]=Character.getNumericValue(reponse.charAt(1));
        return coo;
    }

    public int demanderDegre(){
        return demanderInt("Pour tourner la tuile, donnez un degres du sens des aiguilles:0(ne tourne pas), 90, 180, 270:");
    }

    public int[] getTaillePlateau(){
        int[] coo = {0, 0};
        System.out.println("Taille plateau (x, y) (max. 11): ");
        do{    
            String reponse=demanderStr("Largeur :");
            coo[0]=Integer.parseInt(reponse);
        }while(coo[0] < 2 || coo[0] > 11);
        do{    
            String reponse=demanderStr("Longeur :");
            coo[1]=Integer.parseInt(reponse);
        }while(coo[0] < 2 || coo[0] > 11);
        return coo;
    }
}
