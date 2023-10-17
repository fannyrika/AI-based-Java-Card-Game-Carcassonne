

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.JeuCarcassonne;
import model.Joueur;
import model.JoueurHumain;
import model.JoueurIA;
import model.*;
import view.*;

import javax.swing.*;

public class test extends JFrame{
    boolean isWaitingToClick=true;
    boolean isCarcassonne=false;
    private JTextField nomJeu, nom1, nom2, nom3, nom4, type1, type2, type3, type4;
    public test() throws IOException{
        this.setSize(1800, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ArrayList<Joueur> joueurs=new ArrayList<Joueur>();
        JPanel panel0 = new JPanel();

        nomJeu = new JTextField("CARCASSONNE");
        nom1 = new JTextField("JOUEUR1");
        nom2 = new JTextField("JOUEUR2");
        nom3 = new JTextField("JOUEUR3");
        nom4 = new JTextField("JOUEUR4");
        type1 = new JTextField("HUMAIN");
        type2 = new JTextField("HUMAIN");
        type3 = new JTextField("HUMAIN");
        type4 = new JTextField("HUMAIN");

        panel0.add(nomJeu);
        this.add(panel0);

        JPanel panel1=new JPanel();
        panel1.add(nom1);
        panel1.add(type1);

        JPanel panel2=new JPanel();
        panel2.add(nom2);
        panel2.add(type2);

        JPanel panel3=new JPanel();
        panel3.add(nom3);
        panel3.add(type3);

        JPanel panel4=new JPanel();
        panel4.add(nom4);
        panel4.add(type4);

        JButton valider=new JButton("VALIDER");

        JPanel mainContainer=new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.add(panel0);
        mainContainer.add(panel1);
        mainContainer.add(panel2);
        mainContainer.add(panel3);
        mainContainer.add(panel4);
        mainContainer.add(valider);

        this.add(mainContainer);
        this.setVisible ( true );

        
        valider.addActionListener(e->{
            this.validate();
            this.repaint();
            if(nomJeu.getText().equals("CARCASSONNE"))
                isCarcassonne=true;
            if(!nom1.getText().equals("")){
                if(type1.getText().equals("HUMAIN"))
                    joueurs.add(new JoueurHumain(nom1.getText()));
                else
                    joueurs.add(new JoueurIA(nom1.getText()));
                joueurs.get(0).setId(0);
            }
            if(!nom2.getText().equals("")){
                if(type2.getText().equals("HUMAIN"))
                    joueurs.add(new JoueurHumain(nom2.getText()));
                else
                    joueurs.add(new JoueurIA(nom2.getText()));
                joueurs.get(1).setId(1);
            }
            if(!nom3.getText().equals("")){
                if(type3.getText().equals("HUMAIN"))
                    joueurs.add(new JoueurHumain(nom3.getText()));
                else
                    joueurs.add(new JoueurIA(nom3.getText()));
                joueurs.get(2).setId(2);
            }
            if(!nom4.getText().equals("")){
                if(type4.getText().equals("HUMAIN"))
                    joueurs.add(new JoueurHumain(nom4.getText()));
                else
                    joueurs.add(new JoueurIA(nom4.getText()));
                joueurs.get(3).setId(3);
            }
            isWaitingToClick=false;
            
        });
        while(isWaitingToClick){
            System.out.print("");
        }
        if(isCarcassonne && joueurs.size() != 0){
            this.dispose();
            JeuCarcassonne modelCarcassonne=new JeuCarcassonne(joueurs);
            new PaletteCarcassonne(modelCarcassonne);
        }
        else if(joueurs.size() != 0){
            this.dispose();
            JeuDomino modelDomino=new JeuDomino(joueurs, false);
            new PaletteDomino(modelDomino);
        }
    }
    public static void main(String[] args) throws IOException {
        new test();
    }

}
