import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        DeckOfCards d1 = new DeckOfCards();
        DiscardPile p1;
        ArrayList<Player> player = new ArrayList<Player>();
        Object[] possibilities = {2, 3, 4};
        Integer noPlayer;
        Integer choice;
        boolean valid = false;


        while (valid == false) {
            System.out.printf("Number of Player: ");
            noPlayer = (Integer) JOptionPane.showInputDialog(null, "Select number of player:", "NumberOfPlayer", JOptionPane.INFORMATION_MESSAGE, null, possibilities, "Numbers");
            if ((noPlayer != null) && (noPlayer > 0)) {
                for (int i = 0; i < noPlayer; i++) {
                    Player tmp;
                    player.add(tmp = new Player(JOptionPane.showInputDialog(null, "Player " + (i + 1) + ": "), d1));
                }
                valid = true;
            } else
                JOptionPane.showMessageDialog(null, "Error number!");
            break;
        }

//        Game Start
        p1 = new DiscardPile(d1);
        for(int i = 0;i < player.size();i++){
            int tmp;
            int[] tmpSet;
            player.get(i).showHand();
            choice = (Integer) JOptionPane.showInputDialog(null, "Select number of player:", "NumberOfPlayer", JOptionPane.INFORMATION_MESSAGE, null, possibilities, "Numbers");
            if(choice == 1){
                player.get(i).addCards(d1);
                tmp = (Integer) JOptionPane.showInputDialog(null, "Select the card you wanted to remove:", "RemoveCard", JOptionPane.INFORMATION_MESSAGE, null, possibilities, "Numbers");
                player.get(i).removeCard(player.get(i).findCard(tmp));
                if(choice == 1){
                    player.get(i).getSet(tmpSet);
                }

            }else if(choice == 2){

            }


        }

    }
}

