package controller;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.*;
import util.RotatedIcon;
import util.myUtil;
import view.JeuCarcassonneVue;


public class JeuCarcassonneControleur implements JeuControleurInterface{
    private JeuCarcassonne modifiedModel;
    private JeuCarcassonneVue vueActuel;
    private SacCarcassonne sac;
    private PieceCarcassonne pieceTmp;
    public int rowTmp, colTmp, rotationTmp;
    private JLabel currentCardTmp;

    //private Modele modeleSaved=new Modele();
    //private Modele modeleCompl=new Modele();

    public JeuCarcassonneControleur(JeuCarcassonne m, JeuCarcassonneVue v){
        this.modifiedModel=m;
        this.vueActuel=v;
        this.sac=modifiedModel.sac;
    }

    @Override
    public void placer(int row, int col) throws IOException{
        int score=modifiedModel.plateau.placerPiece(modifiedModel.pieceCourante, row, col);
        if(score==0){
            modifiedModel.joueurCourant.resetScoreIncrement();
            modifiedModel.pieceCourante.afficherPiece(modifiedModel.pieceCourante);//debug
            vueActuel.labelMessage.setText("Impossible à placer la toile ici!");
            return;
        }
        else{
            modifiedModel.joueurCourant.scoreIncrease(score);

            //System.out.println("Placer button clicked"+row+" "+col);
            // Remove the label from the 6th panel
            vueActuel.leftPanel.remove(5);

            //piocher
            if(sac.estVide()){
                pieceTmp=modifiedModel.pieceCourante;
                rowTmp=row;
                colTmp=col;
                currentCardTmp=vueActuel.currentCard;

                // Add the label to the right panel
                vueActuel.rightPanel.remove(row * 9 + col);
                vueActuel.rightPanel.add(vueActuel.currentCard, row * 9 + col);
                
                vueActuel.currentCard=vueActuel.cardLabelBlanc;
            }
            else{
                pieceTmp=modifiedModel.pieceCourante;
                rowTmp=row;
                colTmp=col;
                currentCardTmp=vueActuel.currentCard;

                modifiedModel.pieceCourante=sac.piocher();
                JLabel nextCard=(new myUtil()).getIconLabel((modifiedModel.pieceCourante.getChemin()));
                vueActuel.leftPanel.add(nextCard,5);
                // Add the label to the right panel
                vueActuel.rightPanel.remove(row * 9 + col);
                vueActuel.rightPanel.add(vueActuel.currentCard, row * 9 + col);
                vueActuel.currentCard=nextCard;
            }
            // Refresh the frame to show the changes
            vueActuel.validate();
            vueActuel.repaint();
        }
        vueActuel.model=modifiedModel;
        System.out.println("shouldContinue?"+modifiedModel.shouldContinue);
        System.out.println(modifiedModel.joueurCourant);
    }
    public void placerPion(int pos) throws IOException{
        System.out.println(modifiedModel.joueurCourant.getNbPion());
        if(pos>12)
            return;
        if(modifiedModel.joueurCourant.getNbPion()==0)
            return;
        //there are already pions in the zone
        if(modifiedModel.plateau.zones.get(pieceTmp.indexZone.get(pos).mergedZoneId).habitants.size()>0)
            return;
        //debug
        System.out.println("commencer a placer le pion");
        //update in plateau
        modifiedModel.plateau.zones.get(pieceTmp.indexZone.get(pos).mergedZoneId).habitants.add(modifiedModel.joueurCourant.pions.get(modifiedModel.joueurCourant.pions.size()-1));
        modifiedModel.joueurCourant.placerPion(pos, pieceTmp);
        
        int row=rowTmp;
        int col=colTmp;


        JLabel cardWithPion=ajouterPion(pos, pieceTmp.getChemin(), modifiedModel.joueurCourant.getPionColor(),rotationTmp);

        vueActuel.rightPanel.remove(row * 9 + col);
        vueActuel.rightPanel.add(cardWithPion, row * 9 + col);

        vueActuel.validate();
        vueActuel.repaint();

        modifiedModel.shouldContinue=true;
        vueActuel.model=modifiedModel;
        System.out.println("shouldContinue?"+modifiedModel.shouldContinue);
        System.out.println(modifiedModel.joueurCourant);
    }

    public JLabel ajouterPion(int pos, String chemin, Color color, int rotationTmp) throws IOException{
        if(pos!=12)
            pos=(pos-rotationTmp*3+12 )%12;
        int x=0;
        int y=0;
        switch(pos){
            case 0:
                x=18;
                y=0;
                break;
            case 1:
                x=18*2;
                y=0;
                break;
            case 2:
                x=18*3;
                y=0;
                break;
            case 3:
                x=18*4;
                y=18;
                break;
            case 4:
                x=18*4;
                y=18*2;
                break;
            case 5:
                x=18*4;
                y=18*3;
                break;
            case 6:
                x=18*3;
                y=18*4;
                break;
            case 7:
                x=18*2;
                y=18*4;
                break;
            case 8:
                x=18*1;
                y=18*4;
                break;
            case 9:
                x=0;
                y=18*3;
                break;
            case 10:
                x=0;
                y=18*2;
                break;
            case 11:
                x=0;
                y=18*1;
                break;
            case 12:
                x=18*2;
                y=18*2;
                break;
        }
        return (new myUtil()).dessinerPion(chemin, x, y,color, rotationTmp);
    }

    @Override
    public void passer() throws IOException{
        // Remove the label from the 6th panel
        vueActuel.leftPanel.remove(5);
        //piocher
        if(modifiedModel.sac.estVide()){
            vueActuel.currentCard=vueActuel.cardLabelBlanc;
        }
        else{
            modifiedModel.pieceCourante=modifiedModel.sac.piocher();
            JLabel nextCard=(new myUtil()).getIconLabel((modifiedModel.pieceCourante.getChemin()));
            vueActuel.leftPanel.add(nextCard,5);
            vueActuel.currentCard=nextCard;
        }
        // Refresh the frame to show the changes
        vueActuel.validate();
        vueActuel.repaint();

        vueActuel.model=modifiedModel;
        modifiedModel.shouldContinue=true;
        System.out.println("shouldContinue?"+modifiedModel.shouldContinue);
        System.out.println(modifiedModel.joueurCourant);
    }

    @Override
    public void rotate(int rotation) throws IOException{
        rotationTmp=rotation;
        //update modifiedModel
        modifiedModel.pieceCourante.rotate(rotation);
        modifiedModel.pieceCourante.afficherPiece(modifiedModel.pieceCourante);//debug

        JLabel rotatedCard=new JLabel();
        //ImageIcon icon = new ImageIcon(modifiedModel.pieceCourante.getChemin());
        BufferedImage image =ImageIO.read(getClass().getResource(modifiedModel.pieceCourante.getChemin()));
        Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon icon = new ImageIcon(newimg);  // transform it back
        RotatedIcon rotatedIcon = new RotatedIcon(icon,rotation*90);
        // Set the rotated image back to the JLabel
        rotatedCard.setIcon(rotatedIcon);
        rotatedCard.setPreferredSize(new Dimension(90, 90)); // set the size of the label
        
        // Remove the label from the 6th panel
        vueActuel.leftPanel.remove(5);
        vueActuel.leftPanel.add(rotatedCard,5);

        vueActuel.currentCard=rotatedCard;
        // Refresh the frame to show the changes
        vueActuel.model=modifiedModel;
        vueActuel.validate();
        vueActuel.repaint();
        System.out.println("shouldContinue?"+modifiedModel.shouldContinue);
    }

    @Override
    public void controlIA() throws IOException{
        JoueurIA joueurIA=(JoueurIA)modifiedModel.joueurCourant;
        if(joueurIA.passer()){
            passer();
            return;
        }
        rotate(joueurIA.choisirRotation());
        placer(joueurIA.choisirX(), joueurIA.choisirY());
        if(joueurIA.getScoreIncrement()==0){
            passer();
            return;
        }
        
        placerPion(joueurIA.choisirPionPosition());
    }
}
