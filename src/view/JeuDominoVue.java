package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.JeuDominoControleur;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import model.*;
import util.myUtil;

public class JeuDominoVue extends JeuVue implements Serializable{
    public transient JeuDomino model;//to modify
    public transient JeuDominoControleur controleur;

    public JeuDominoVue(JeuDomino model) throws IOException{
        super();
        this.model=model;
        this.controleur=new JeuDominoControleur(model, this);

        JButton pause = new JButton("Pause");
        pause.addActionListener((arg0) ->{
            int i = JOptionPane.showOptionDialog(null,
                "Que souhaitez-vous faire ^^?",
                "Pause",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new String[]{"Reprendre", "Enregistrer et aller au menu principal", "Quitter"}, null);

                if (i == JOptionPane.YES_OPTION);
                else if(i == JOptionPane.NO_OPTION){
                    File fichier = new File("saveDomino.ser");
                    try{
                        ObjectOutputStream tmp = new ObjectOutputStream(new FileOutputStream(fichier));
                        tmp.writeObject(this);
                    }catch(Exception e){ e.printStackTrace(); }
                    this.getContentPane().removeAll();
                    this.revalidate();
                    this.repaint();
                    //new test();
                }else if(i == JOptionPane.CANCEL_OPTION){
                    System.exit(0);
                };
        });

        super.frame.add(pause, BorderLayout.SOUTH);
    }

    // Ce constructeur permet d'utiliser le fichier Menu
    public JeuDominoVue(ArrayList<Joueur> joueurs, JFrame frame) throws IOException{
        super(frame);
        this.frame = frame;
        model = new JeuDomino(joueurs, false);
        controleur = new JeuDominoControleur(model, this);

        JButton pause = new JButton("Pause");
        pause.addActionListener((arg0) ->{
            int i = JOptionPane.showOptionDialog(null,
                "Que souhaitez-vous faire ^^?",
                "Pause",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new String[]{"Reprendre", "Enregistrer et aller au menu principal", "Quitter"}, null);

            if (i == JOptionPane.YES_OPTION);
            else if(i == JOptionPane.NO_OPTION){
                File fichier = new File("saveDomino.ser");
                ObjectOutputStream tmp;
                try {
                    tmp = new ObjectOutputStream(new FileOutputStream(fichier));
                    tmp.writeObject(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                frame.dispose();
                new Menu();
            }else if(i == JOptionPane.CANCEL_OPTION){
                System.exit(0);
            };
        });
        frame.add(pause, BorderLayout.SOUTH);
    }

    @Override
    public void fillScorePanel() {
        model.joueurCourant=model.joueurs.get(0);//to modify
        //labelScore = new JLabel[model.joueurs.size()];
        for(int i=0;i<model.joueurs.size();i++){
            if(i==0)
                if(model.joueurs.get(i) instanceof JoueurHumain)
                    labelScore[i].setText("-> Joueur"+model.joueurs.get(i).id+"(humain)");
                else
                labelScore[i].setText("-> Joueur"+model.joueurs.get(i).id+"(IA");
            else
                //labelScore[i].setText(model.joueurs.get(i).toString());
                if(model.joueurs.get(i) instanceof JoueurHumain)
                    labelScore[i].setText("   Joueur"+model.joueurs.get(i).id+"(humain)");
                else
                labelScore[i].setText("   Joueur"+model.joueurs.get(i).id+"(IA)");
            scorePanel.add(labelScore[i]);
        }
    }

    @Override
    public void addSomeListeners(){
        
        button6.addActionListener(e -> {
            int row = Integer.parseInt(textField1.getText());
            textField1.setText("");
            int col = Integer.parseInt(textField2.getText());
            textField2.setText("");

            try {
                controleur.placer(row,col);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("from view:");//debug
            model.pieceCourante.afficherPiece(model.pieceCourante);
        });
        button1.addActionListener(e -> {
            try {
                controleur.passer();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("from view:");//debug
            model.pieceCourante.afficherPiece(model.pieceCourante);
        });
        button2.addActionListener(e -> {
            try {
                controleur.rotate(0);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("from view:");//debug
            model.pieceCourante.afficherPiece(model.pieceCourante);
        });
        button3.addActionListener(e -> {
            try {
                controleur.rotate(1);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("from view:");//debug
            model.pieceCourante.afficherPiece(model.pieceCourante);
        });
        button4.addActionListener(e -> {
            try {
                controleur.rotate(2);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("from view:");//debug
            model.pieceCourante.afficherPiece(model.pieceCourante);
        });
        button5.addActionListener(e -> {
            try {
                controleur.rotate(3);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("from view:");//debug
            model.pieceCourante.afficherPiece(model.pieceCourante);
        });
    }

    @Override
    public void lancerJeu() throws IOException {
        while(true){
            //for(int i=0; i<model.joueurs.size(); i++) System.out.println("affiche joueurs:"+model.joueurs.get(i));
            for(int i=0; i<model.joueurs.size(); i++){
                for(int j=0; j<model.joueurs.size(); j++){
                    if(i==j){
                        if(model.joueurs.get(j) instanceof JoueurHumain)
                            labelScore[j].setText("-> Joueur"+model.joueurs.get(j).id+"(humain) - score:"+model.joueurs.get(j).score+"\n");
                        else
                            labelScore[j].setText("-> Joueur"+model.joueurs.get(j).id+"(IA) - score:"+model.joueurs.get(j).score+"\n");
                    }
                    else
                        if(model.joueurs.get(j) instanceof JoueurHumain)
                            labelScore[j].setText("   Joueur"+model.joueurs.get(j).id+"(humain) - score:"+model.joueurs.get(j).score+"\n");
                        else
                            labelScore[j].setText("   Joueur"+model.joueurs.get(j).id+"(IA) - score:"+model.joueurs.get(j).score+"\n");
                }
                validate();
                repaint();

                System.out.println("current player changed!");//debug
                //debug
                //System.out.println(model.joueurs.size());
                model.joueurCourant=model.joueurs.get(i);
                
                if(model.joueurCourant instanceof JoueurIA){
                    try {
                        controleur.controlIA();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                
                //waiting
                while(model.shouldContinue==false){
                    System.out.print("");
                }

                System.out.println("ready to update the info of player");//debug
                
                model.shouldContinue=false;
            }
            if(model.sac.estVide()){
                String s = "";
                Joueur gagnant = model.joueurs.get(0);
                for(int a = 0; a < model.joueurs.size(); a++){
                    s += (model.joueurs.get(a).getPseudo() + " -> " + model.joueurs.get(a).getScore() + " points. \n");
                    if(model.joueurs.get(a).getScore() > gagnant.getScore()) gagnant = model.joueurs.get(a);
                }
                int i = JOptionPane.showOptionDialog(null,
                (gagnant.getPseudo() + " a gagné la partie.\n" + s),
                "Partie terminée",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new String[]{"Rejouer", "Aller au menu principal", "Quitter"}, null);

                if (i == JOptionPane.YES_OPTION){
                    frame.dispose();
                    for(int a = 0; a < model.joueurs.size(); a++) model.joueurs.get(a).resetScoreIncrement();
                    JeuDomino a = new JeuDomino(model.joueurs, false);
                    new PaletteDomino(a);
                }
                else if(i == JOptionPane.NO_OPTION){ 
                    frame.dispose();
                    //new test();
                }
                else if(i == JOptionPane.CANCEL_OPTION) System.exit(0);
            }
        }
    }

    @Override
    public void initialiser() throws IOException{
        //add listeners
        addSomeListeners();

        fillScorePanel();

        BufferedImage image =ImageIO.read(getClass().getResource(model.pieceCourante.getChemin()));
        Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon icon = new ImageIcon(newimg);  // transform it back
        firstCard.setIcon(icon);;

        model.pieceCourante=model.sac.piocher();
        currentCard=(new myUtil()).getIconLabel((model.pieceCourante.getChemin()));
        leftPanel.add(currentCard);
        
        lancerJeu();
        frame.dispose();
    }

}
