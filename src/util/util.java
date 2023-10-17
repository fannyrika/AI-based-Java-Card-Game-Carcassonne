package util;
import java.awt.Color;

import java.io.IOException;

import javax.swing.JLabel;

public interface util{
    JLabel getIconLabel(String chemin) throws IOException;
    String getPath(String relativePath);
    JLabel dessinerPion(String chemin, int x, int y,Color color, int rotationTmp) throws IOException;
}
