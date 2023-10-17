package view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import util.myUtil;

public abstract class JeuVue extends JFrame{

    public JTextField textField1,textField2;
    public JPanel leftPanel, rightPanel, scorePanel, panel5;
    public JLabel cardLabelBlanc,currentCard,labelMessage,firstCard;
    public JLabel[] labelScore;
    public JButton button1, button2, button3, button4, button5, button6;
    public JFrame frame;

    // Ce constructeur permet d'utiliser Menu
    public JeuVue() throws IOException{
        frame = this;
        this.setSize(1800, 1000);

        //blank label
        cardLabelBlanc = (new myUtil()).getIconLabel(
            (new myUtil()).getPath("/images/background.jpg"));
            
        firstCard=cardLabelBlanc;
        // Right part of the frame
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(810, 720)); // set the size of the panel
        rightPanel.setLayout(new GridLayout(8, 9)); // set the layout to be a grid with 8 rows and 9 columns
        //rightPanel.add(cardLabelBlanc, 0);

        //initialiser le plateau
        for(int i=0; i<8; i++){
            JLabel cardLabelB = new JLabel();
            //coordinates
            if(i==0)
                cardLabelB=(new myUtil()).getIconLabel(
                (new myUtil()).getPath("/images/xy.jpg"));
            else if(i==7)
                cardLabelB=(new myUtil()).getIconLabel(
                    (new myUtil()).getPath("/images/blanc.jpg"));
            else
                cardLabelB=(new myUtil()).getIconLabel(
                    (new myUtil()).getPath("/images/"+i+".jpg"));
            rightPanel.add(cardLabelB);
            for(int j=1; j<9; j++){
                //System.out.println("i="+i+" j="+j);
                //rightPanel.remove(i * 9 + j);
                //initial
                if(i==4 && j==4){
                    rightPanel.add(firstCard);
                    continue;
                }
                if(i==7){
                    cardLabelB=(new myUtil()).getIconLabel(
                        (new myUtil()).getPath("/images/blanc.jpg"));
                    rightPanel.add(cardLabelB);
                    continue;
                }
                // x y coordinates
                if(j==8)
                    cardLabelB=(new myUtil()).getIconLabel(
                        (new myUtil()).getPath("/images/blanc.jpg"));
                else if(i==0){
                    //cardLabelB.setText(Integer.toString(j));
                    cardLabelB=(new myUtil()).getIconLabel(
                        (new myUtil()).getPath("/images/"+j+".jpg"));
                }
                else{
                    cardLabelB=(new myUtil()).getIconLabel(
                        (new myUtil()).getPath("/images/background.jpg"));
                    //rightPanel.add(cardLabelBlanc, i * 9 + j);
                }
                rightPanel.add(cardLabelB);
            }
        }
        
        // Left part of the frame
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        // 1st panel
        labelMessage = new JLabel("---");
        leftPanel.add(labelMessage);
        //leftPanel.add(Box.createVerticalStrut(10)); // add a vertical line separator
        
        // 2nd panel
        scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));

        labelScore=new JLabel[4];
        for(int i=0; i<4; i++) labelScore[i] = new JLabel();

        leftPanel.add(scorePanel);
        //leftPanel.add(Box.createVerticalStrut(10)); // add a vertical line separator

        // 3rd panel
        button1 = new JButton("PASSER");

        leftPanel.add(button1);
        //leftPanel.add(Box.createVerticalStrut(10)); // add a vertical line separator

        // 4th panel
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
        JLabel label4 = new JLabel("CHOISER LA ROTATION");
        panel4.add(label4);
        button2 = new JButton("0°");
        button3 = new JButton("90°");
        button4 = new JButton("180°");
        button5 = new JButton("270°");
        panel4.add(button2);
        panel4.add(button3);
        panel4.add(button4);
        panel4.add(button5);
        leftPanel.add(panel4);

        // 5th panel
        panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
        JLabel label5_1 = new JLabel("x=");
        textField1 = new JTextField(5); // set the width of the text field to be the same as a single character
        panel5.add(label5_1);
        panel5.add(textField1);
        JLabel label5_2 = new JLabel("y=");
        textField2 = new JTextField(5); // set the width of the text field to be the same as a single character
        panel5.add(label5_2);
        panel5.add(textField2);
        button6 = new JButton("PLACER LA TUILE");

        panel5.add(button6);
        leftPanel.add(panel5);
        //leftPanel.add(Box.createVerticalStrut(10)); // add a vertical line separator

        JButton pause = new JButton("Pause");
        JButton resume = new JButton("Reprendre");
        pause.addActionListener((arg0) ->{
            int i = JOptionPane.showOptionDialog(null,
                "Que souhaitez-vous faire ^^?",
                "Pause",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, new String[]{"Reprendre", "Enregistrer et aller au menu principal", "Quitter"}, null);

                if (i == JOptionPane.YES_OPTION);
                else if(i == JOptionPane.NO_OPTION){
                    
                }else if(i == JOptionPane.CANCEL_OPTION){
                    this.getContentPane().removeAll();
                    this.revalidate();
                    this.repaint();
                   // new Menu(frame);
                };
        });

        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.setVisible ( true );
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE); //to modify
    }

    // Ce constructeur permet d'utiliser Menu
    public JeuVue(JFrame frame) throws IOException{
        //this.setSize(1800, 1000);
        //this.setVisible ( true );
        this.frame = frame;

        //blank label
        cardLabelBlanc = (new myUtil()).getIconLabel(
            (new myUtil()).getPath("/images/background.jpg"));
            
        firstCard=cardLabelBlanc;
        // Right part of the frame
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(810, 720)); // set the size of the panel
        rightPanel.setLayout(new GridLayout(8, 9)); // set the layout to be a grid with 8 rows and 9 columns
        //rightPanel.add(cardLabelBlanc, 0);

        //initialiser le plateau
        for(int i=0; i<8; i++){
            JLabel cardLabelB = new JLabel();
            //coordinates
            if(i==0)
                cardLabelB=(new myUtil()).getIconLabel(
                (new myUtil()).getPath("/images/xy.jpg"));
            else if(i==7)
                cardLabelB=(new myUtil()).getIconLabel(
                    (new myUtil()).getPath("/images/blanc.jpg"));
            else
                cardLabelB=(new myUtil()).getIconLabel(
                    (new myUtil()).getPath("/images/"+i+".jpg"));
            rightPanel.add(cardLabelB);
            for(int j=1; j<9; j++){
                //System.out.println("i="+i+" j="+j);
                //rightPanel.remove(i * 9 + j);
                //initial
                if(i==4 && j==4){
                    rightPanel.add(firstCard);
                    continue;
                }
                if(i==7){
                    cardLabelB=(new myUtil()).getIconLabel(
                        (new myUtil()).getPath("/images/blanc.jpg"));
                    rightPanel.add(cardLabelB);
                    continue;
                }
                // x y coordinates
                if(j==8)
                    cardLabelB=(new myUtil()).getIconLabel(
                        (new myUtil()).getPath("/images/blanc.jpg"));
                else if(i==0){
                    //cardLabelB.setText(Integer.toString(j));
                    cardLabelB=(new myUtil()).getIconLabel(
                        (new myUtil()).getPath("/images/"+j+".jpg"));
                }
                else{
                    cardLabelB=(new myUtil()).getIconLabel(
                        (new myUtil()).getPath("/images/background.jpg"));
                    //rightPanel.add(cardLabelBlanc, i * 9 + j);
                }
                rightPanel.add(cardLabelB);
            }
        }
    }
    
    public abstract void fillScorePanel();

    public abstract void addSomeListeners();
    
    public abstract void lancerJeu() throws IOException;
    
    public abstract void initialiser() throws IOException;
}
