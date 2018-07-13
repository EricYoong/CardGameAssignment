import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Game {


    public static void main(String[] args) {
        //Initial Game data
        Scanner input = new Scanner(System.in);
        int noPlayer = 0;
        DeckOfCards d1 = new DeckOfCards();
        ArrayList<Player> player = new ArrayList<Player>();
        DiscardPile p1;

        //numPlayer data
        boolean valid = false;
        String sPlayer;
        StringTokenizer st;

        //start game data
        boolean validGame = true;
        String choice;
        int[] intChoice;
        p1 = new DiscardPile(d1);

        //Number of Player
        while (valid == false) {
            System.out.printf("Number of Player: ");
            try {
                sPlayer = input.nextLine();
                st = new StringTokenizer(sPlayer, " ");

                if (st.countTokens() > 1) {
                    System.out.println("Invalid enter!! Please re-enter.");
                } else {
                    for (int i = 0; i < st.countTokens(); i++) {
                        noPlayer = Integer.parseInt(st.nextToken());
                    }

//                check is the number of the user input valid between 2 to 4
                    if (noPlayer > 1 && noPlayer < 5) {
                        for (int i = 0; i < noPlayer; i++) {
                            Player tmp;
                            boolean nameValid = true;

                            while (nameValid) {

                                System.out.printf("Player " + (i + 1) + ": ");
                                sPlayer = input.nextLine();

//                                Check the existing player name
                                if (player.size() == 0) {
                                    player.add(tmp = new Player(sPlayer, d1));
                                    break;
                                }

                                for (int j = 0; j < player.size(); j++) {

                                    if (player.get(j).checkName(sPlayer)) {
                                        System.out.println("The player name is existing!!");
                                        System.out.println("Please re-enter");
                                        break;
                                    } else {
                                        nameValid = false;
                                    }
                                }
                                if (nameValid != true) {
                                    player.add(tmp = new Player(sPlayer, d1));
                                }
                            }
                        }
                        valid = true;
                    } else {
                        System.out.println("It is an invalid number of player!");
                    }

                }

            } catch (NumberFormatException e) {
                System.out.println("That is not a correct number");
            }
        }

        while (validGame) {
            //Game started
            for (int i = 0; i < player.size(); i++) {
                boolean sort = false;

                //Check is the user enter int or string
                valid = false;
                while (valid == false) {
                    try {
                        //use to sort the card
                        do {
                            System.out.println(player.get(i).getName() + " Turns");
                            player.get(i).showHand();
                            choice = input.nextLine();
                            st = new StringTokenizer(choice, " ");
                            int tmp = st.countTokens();
                            intChoice = new int[tmp];

                            for (int j = 0; j < tmp; j++) {
                                intChoice[j] = Integer.parseInt(st.nextToken()) - 1;
                            }

                            if (intChoice[0] == -1) {
                                player.get(i).sortByRank(player.get(i).getCard());
                                sort = true;
                            } else if (intChoice[0] == -2) {
                                player.get(i).sortBySuit(player.get(i).getCard());
                                sort = true;
                            } else
                                sort = false;
                        } while (sort);

                        if (!player.get(i).getSet(intChoice)) {
                            System.out.println("Invalid Set Build or Card selected!!!");
                            System.out.println(" ");
                            valid = false;
                        } else
                            valid = true;

                    } catch (NumberFormatException e) {
                        System.out.println("That is not a correct number");
                        valid = false;
                    }
                }

                if (player.get(i).checkHand()) {
                    String notEnd = "no";
                    String end = "knock";
                    String choices;
                    valid = true;

                    while (valid) {
                        try {
                            System.out.println("You have left 2 cards or less hand cards.");
                            System.out.println("Do you want to end the game.");
                            System.out.println("If \"yes\" please type knock, if no press \"no\" to continue");
                            System.out.printf("Choice: ");

                            choices = input.nextLine();

                            if (end.equals(choices)) {
                                validGame = false;
                                valid = false;
                            } else if (notEnd.equals(choices)) {
                                validGame = true;
                                valid = false;
                            } else {
                                System.out.printf("Invalid choices. Please re-enter: ");
                                valid = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("That is not a correct number");
                            valid = true;
                        }
                    }

                }
            }
        }
    }
}

