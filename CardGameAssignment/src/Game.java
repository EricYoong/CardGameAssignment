import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        DeckOfCards d1 = new DeckOfCards();
        DiscardPile p1 = new DiscardPile(d1);
        ArrayList<Player> player = new ArrayList<Player>();
        int noPlayer;
        boolean valid = false;

        while (valid == false) {
            noPlayer = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of Player: "));
            if (noPlayer < 4) {
                for (int i = 0; i < noPlayer; i++) {
                    Player tmp;
                    player.add(tmp = new Player(JOptionPane.showInputDialog(null, "Player " + (i + 1) + ": "), d1));
                }
                valid = true;
            }else
                JOptionPane.showMessageDialog(null,"Error number of Player set!!");
        }


    }
}
