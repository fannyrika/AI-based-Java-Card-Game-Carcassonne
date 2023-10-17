package controller;
import java.io.IOException;
import java.io.Serializable;



public interface JeuControleurInterface extends Serializable{
    public void placer(int row, int col) throws IOException;
    public void passer() throws IOException;
    public void rotate(int rotation) throws IOException;
    public void controlIA() throws IOException;
}
