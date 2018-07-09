import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Game {
    public static void main(String[] args) {
        DeckOfCards d1 = new DeckOfCards();
        DiscardPile p1;
        ArrayList<Player> player = new ArrayList<Player>();
        Object[] possibilities = {2, 3, 4};
        String sPlayer;
        int choice, noPlayer = 0;
        boolean valid = false;
        StringTokenizer st;
        Scanner input = new Scanner(System.in);


        while (valid == false) {

            System.out.printf("Number of Player: ");
            try {
                sPlayer = input.nextLine();
                st = new StringTokenizer(sPlayer, " ");

                if (st.countTokens() > 1) {
                    System.out.println("Invalid enter!");
                } else {
                    for (int i = 0; i < st.countTokens(); i++) {
                        noPlayer = Integer.parseInt(st.nextToken());
                    }
                }

//                check is the number of the user input valid between 2 to 4
                if (noPlayer > 1 && noPlayer < 5) {
                    for (int i = 0; i < noPlayer; i++) {
                        Player tmp;
                        System.out.println("Player " + (i + 1) + ": ");
                        sPlayer = input.nextLine();
                        player.add(tmp = new Player(sPlayer, d1));
                    }
                    valid = true;
                } else {
                    System.out.println("It is an invalid number of player!");
                }
            } catch (NumberFormatException e) {
                System.out.println("That is not a correct number");
            }

        }

//        Game Start
        p1 = new DiscardPile(d1);

    }
}

