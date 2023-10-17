package controller;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.*;
import util.RotatedIcon;
import util.myUtil;
import view.JeuDominoVue;


public class JeuDominoControleur implements JeuControleurInterface{
    private JeuDomino modifiedModel;
    private JeuDominoVue vueActuel;
    private SacDomino sac;

    public JeuDominoControleur(JeuDomino m, JeuDominoVue v){
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
                // Add the label to the right panel
                vueActuel.rightPanel.remove(row * 9 + col);
                vueActuel.rightPanel.add(vueActuel.currentCard, row * 9 + col);
                
                vueActuel.currentCard=vueActuel.cardLabelBlanc;
            }
            else{
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
        modifiedModel.shouldContinue=true;
        System.out.println("shouldContinue?"+modifiedModel.shouldContinue);
        System.out.println(modifiedModel.joueurCourant);
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
        //update modifiedModel
        modifiedModel.pieceCourante.rotate(rotation);
        modifiedModel.pieceCourante.afficherPiece(modifiedModel.pieceCourante);//debug

        //ImageIcon icon = new ImageIcon(());

        //firstCard.setIcon(icon);

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
        if(joueurIA.getScoreIncrement()==0)
            passer();
    }

}
