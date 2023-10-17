package util;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class myUtil implements util{
    /* 
    @Override
    public JLabel getIconLabel(String chemin){
        JLabel firstCard=new JLabel();
        ImageIcon icon = new ImageIcon(chemin);
        //System.out.println("chemin:"+model.sac.piocher().getChemin());//debug
        Image image = icon.getImage(); // transform it 

        Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back
        firstCard.setIcon(icon);
        firstCard.setPreferredSize(new Dimension(90, 90)); // set the size of the label
        return firstCard;
    }
    */

    @Override
    public JLabel getIconLabel(String chemin) throws IOException{
        JLabel firstCard=new JLabel();
        BufferedImage image = ImageIO.read(getClass().getResource(chemin));
        Image newimg = image.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon icon = new ImageIcon(newimg);  // transform it back
        firstCard.setIcon(icon);
        firstCard.setPreferredSize(new Dimension(90, 90)); // set the size of the label
        return firstCard;
    }

    @Override
    public String getPath(String relativePath){
        return (new File(relativePath)).getAbsolutePath();
    }

    @Override
    public JLabel dessinerPion(String chemin, int x, int y, Color color, int rotationTmp) throws IOException{
        
        JLabel firstCard=new JLabel();
        BufferedImage image =ImageIO.read(getClass().getResource(chemin));
        //test
        Graphics g = (image).getGraphics();
        g.setColor(color);
        //g.drawRect(x,y,18,18);
        g.fillRect(x, y, 18,18);
        Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon icon = new ImageIcon(newimg);  // transform it back
        RotatedIcon rotatedIcon = new RotatedIcon(icon,rotationTmp*90);
        // Set the rotated image back to the JLabel
        firstCard.setIcon(rotatedIcon);
        firstCard.setPreferredSize(new Dimension(90, 90)); // set the size of the label
        return firstCard;
    }
}
