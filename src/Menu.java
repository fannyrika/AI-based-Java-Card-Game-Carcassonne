
import model.*;
import util.myUtil;
import view.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.awt.image.BufferedImage;

public class Menu{
    private JFrame frame;
    private JButton domino = new JButton("Domino");
    private JButton carcassonne = new JButton("Jeu de Carcassonne");
    private JButton exit = new JButton("Exit");
    private JPanel panel = new JPanel();
    private JPanel panelG = new JPanel();
    private boolean isDomino = false;

    public Menu(JFrame frame) throws IOException{
        this.frame = frame;
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setTitle("Menu");

        panel.setLayout(new BorderLayout());
        panelG.setLayout(new GridLayout(1, 3));

        panelG.add(domino, BorderLayout.CENTER);
        panelG.add(carcassonne, BorderLayout.CENTER);
        panelG.add(exit, BorderLayout.CENTER);

        panel.add(panelG, BorderLayout.SOUTH);

        domino.addActionListener((event) -> {
            isDomino = true;
            boolean b = false;
            if(existeSauvegarde()) b = ouvrirSauvegarde();
            if(b)
                try {
                    isDomino = true;
                    File fichier = new File((new myUtil()).getPath("saveCarcassonne.ser"));
                    ObjectInputStream tmp = new ObjectInputStream(new FileInputStream(fichier));
                    JeuDominoVue c = (JeuDominoVue)tmp.readObject();
                    new PaletteDomino(c);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Impossible d'ouvrir la sauvegarde :( ", "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            else{
                (new File((new myUtil()).getPath("saveDomino.ser"))).delete();
                isDomino = true;
                try {
                    int x = NbrJoueurs(); 
                    initialize(x);
                } catch (Exception e) {}
            }
        });
        carcassonne.addActionListener((event) -> {
            boolean b = false;
            if(existeSauvegarde()) b = ouvrirSauvegarde();
            if(b){
                try {
                    File fichier = new File(new File("saveCarcassonne.ser").getAbsolutePath());
                    ObjectInputStream tmp = new ObjectInputStream(new FileInputStream(fichier));
                    JeuCarcassonneVue c = (JeuCarcassonneVue)tmp.readObject();
                    new PaletteCarcassonne(c);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Impossible d'ouvrir la sauvegarde :( ", "Erreur !", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                (new File((new myUtil()).getPath("saveCarcassonne.ser"))).delete();
                try {
                    int x = NbrJoueurs();
                    initialize(x);
                } catch (Exception e){}
            }
        });
        exit.addActionListener((event) -> System.exit(0));

        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.SOUTH);
    }

    public int NbrJoueurs() throws Exception{
        int x = 0;
        boolean d = isDomino;
        int max = ((d)? 6 : 4);
        isDomino = false;
        String getNbrJoueurs = null;
        do{
            getNbrJoueurs = JOptionPane.showInputDialog("Sélectionnez le nombres de joueurs pour cette partie (max. " + ((d)? "6" : "4") + " joueurs): ");
            x = (int) Integer.parseInt(getNbrJoueurs);
        }while(x < 2 && x > max);
        isDomino = d;
        if(x >= 2 && x <= max) return x;
        else return NbrJoueurs();
    }

    public boolean existeSauvegarde(){
        try{
            if(isDomino){
                isDomino = false;
                String s = (new myUtil()).getPath("saveDomino.ser");
                if((new File(s)).exists()) return true;
                else return true;
            }else{
                String s = (new myUtil()).getPath("saveCarcassonne.ser");
                if((new File(s)).exists()) return true;
                else return false;
            }
        }catch(Exception e){ 
            isDomino = false;
            return false;
        }
    }

    public boolean ouvrirSauvegarde(){
        if (JOptionPane.showConfirmDialog(null, "Voulez-vous ouvrir la sauvegarde existante ? \n (Si non, la sauvegrade sera automatiquement supprimée)", "Sauvegarde existante", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) return true;
        else return false;
    }

    public void initialize(int x) throws IOException {
        panelG.removeAll();
        panelG.revalidate();
        panelG.repaint();
        panel.removeAll();
        panel.revalidate();
        panel.repaint();

        JPanel check = new JPanel();
        JPanel title = new JPanel();
        JPanel text = new JPanel();
        text.setLayout(new GridLayout(x, 1));
        JLabel label = new JLabel("Veuillez remplir les champs suivants:");
        label.setFont(new Font("Arial", Font.BOLD, 32));
        title.add(label);

        JPanel confirmation = new JPanel();
        JButton valide = new JButton("Lancer la partie");
        JButton retour = new JButton("Retour");
        confirmation.setLayout(new GridLayout(1, 2));
        confirmation.add(valide);
        confirmation.add(retour);
        
        panelG.setLayout(new GridLayout(x * 3, 1));
        check.setLayout(new GridLayout(x, 2));
        
        ArrayList<JTextField> names = new ArrayList<JTextField>();
        ArrayList<JCheckBox> humains = new ArrayList<JCheckBox>();
        ArrayList<JCheckBox> IA = new ArrayList<JCheckBox>();

        for(int i = 0; i < x; i++){
            JTextField name = new JTextField("Joueur " + (i + 1));
            names.add(name);
            JCheckBox a = new JCheckBox("Humain", false);
            JCheckBox b = new JCheckBox("IA", false);
            a.addActionListener((e) ->{
                if(b.isSelected()) b.setSelected(false);
            });
            b.addActionListener((e) ->{
                if(a.isSelected()) a.setSelected(false);
            });
            humains.add(a);
            IA.add(b);
        }

        for(int j = 0; j < names.size(); j++) {
            JLabel tmp = new JLabel("   Joueur " + (j + 1) + " :     ");
            tmp.setFont(new Font("bold", Font.BOLD, 20));
            text.add(tmp);
            panelG.add(new JLabel());
            panelG.add(names.get(j));
            panelG.add(new JLabel());
            check.add(humains.get(j));
            check.add(IA.get(j));
        }
 
        valide.addActionListener((e) ->{
            ArrayList<Joueur> joueurs = createPlayer(x, names, humains, IA);
            try{
                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                if(isDomino){
                    frame.setTitle("Domino");
                    new PaletteDomino(joueurs, frame);
                }else{
                    frame.setTitle("Jeu de Carcassonne");
                    new PaletteCarcassonne(joueurs, frame);
                }
            }catch(IOException error){ error.printStackTrace();
            }
        });

        retour.addActionListener((e)->{
            try {
                panel.removeAll();
                panel.revalidate();
                panel.repaint();
                frame.getContentPane().removeAll();
                frame.repaint();
                new Menu(frame);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        panel.add(title, BorderLayout.NORTH);
        panel.add(text, BorderLayout.WEST);
        panel.add(panelG, BorderLayout.CENTER);
        panel.add(check, BorderLayout.EAST);
        panel.add(confirmation, BorderLayout.SOUTH);
        frame.add(panel, BorderLayout.CENTER);
    }
    
    // Si le ième joueur n'a selectionné ni humain ni IA, il sera automatiquement humain
    public ArrayList createPlayer(int x, ArrayList<JTextField> names, ArrayList<JCheckBox> humains, ArrayList<JCheckBox> IA){
        ArrayList<Joueur> players = new ArrayList<Joueur>();
        for(int i = 0; i < x; i++){
            if(IA.get(i).isSelected()) players.add(new JoueurIA(names.get(i).getText()));
            else players.add(new JoueurHumain(names.get(i).getText()));
        }
        return players;
    }
    
    public static void printArray(ArrayList<Joueur> a){
        for(int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i).getPseudo() + " -> " + ((a.get(i).isIA())? "IA" : "Humain"));
        }
    }
    public static void main(String[] args) throws IOException{
        new Menu(new JFrame());
    }
}