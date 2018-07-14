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

                                if (player.size() == 0) {
                                    if (sPlayer.isEmpty() || sPlayer.equals(" ")) {
                                        System.out.println("Cannot be empty name!!");
                                    } else {
                                        player.add(tmp = new Player(sPlayer, d1));
                                        break;
                                    }
                                }

//                                Check the existing player name
                                for (int j = 0; j < player.size(); j++) {

                                    if (sPlayer.isEmpty() || sPlayer.equals(" ")) {
                                        System.out.println("Cannot be empty name!!");
                                    } else {
                                        if (player.get(j).checkName(sPlayer)) {
                                            System.out.println("The player name is existing!!");
                                            System.out.println("Please re-enter");
                                            break;
                                        } else {
                                            nameValid = false;
                                        }
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

        //Loop the game if the player is not choose to win.
        while (validGame) {

            //Game started
            for (int i = 0; i < player.size(); i++) {
                boolean sort;
                p1 = new DiscardPile(d1);
                int tmp;

                //Check is the user enter int or string
                valid = false;
                while (valid == false) {
                    try {

                        //use to sort the card
                        System.out.println(player.get(i).getName() + " Turns");

                        //Display the discard pile card.
                        p1.displayPile();
                        player.get(i).showHand2();

                        //Let the user choose to create set on hand or take one card from the deck.
                        System.out.println("1. Do you want to make Set from your handCard.");
                        System.out.println("2. Do you want to take a card from the deck and");
                        System.out.println("discard 1 card from your hand then only create a set.");


                        //Get user input
                        do {
                            System.out.println("Please enter your choice: ");
                            choice = input.nextLine();
                            st = new StringTokenizer(choice, " ");
                            tmp = st.countTokens();
                            intChoice = new int[tmp];
                            if (intChoice.length < 1) {
                                if (intChoice[0] == 1) {
                                    do {

//                                  Display the player's handCard and get user input
                                        player.get(i).showHand();
                                        choice = input.nextLine();
                                        st = new StringTokenizer(choice, " ");
                                        tmp = st.countTokens();
                                        intChoice = new int[tmp];

                                        for (int j = 0; j < tmp; j++) {
                                            intChoice[j] = Integer.parseInt(st.nextToken()) - 1;
                                        }

                                        //check the user choices whether they want to sort the card.
                                        if (intChoice[0] == -1) {
                                            player.get(i).sortByRank(player.get(i).getCard());
                                            sort = true;
                                        } else if (intChoice[0] == -2) {
                                            player.get(i).sortBySuit(player.get(i).getCard());
                                            sort = true;
                                        } else {
                                            System.out.println("Error choice!!");
                                            sort = false;
                                        }
                                    } while (sort);
                                    valid = false;

                                } else if (intChoice[0] == 2) {
                                    do {

                                        //add random card from the deck
                                        player.get(i).addCards(d1);

                                        //Get the user input to remove card they want from the handCard.
                                        System.out.println("Which card would you like to remove.");
                                        System.out.println("Please choose one card: ");
                                        choice = input.nextLine();
                                        st = new StringTokenizer(choice, " ");
                                        tmp = st.countTokens();
                                        intChoice = new int[tmp];
                                        if (intChoice.length < 1) {

                                            //add pile card from the player hand
                                            p1.addPile(player.get(i).findCard(intChoice[0]));
                                            //remove a card from the hand after getting one card from the deck
                                            player.get(i).removeCard(player.get(i).findCard(tmp));

                                        }

//                                      Display the player's handCard and get user input
                                        player.get(i).showHand();
                                        choice = input.nextLine();
                                        st = new StringTokenizer(choice, " ");
                                        tmp = st.countTokens();
                                        intChoice = new int[tmp];

                                        for (int j = 0; j < tmp; j++) {
                                            intChoice[j] = Integer.parseInt(st.nextToken()) - 1;
                                        }

                                        if (intChoice.length < 1) {
                                            //check the user choices whether they want to sort the card.
                                            if (intChoice[0] == -1) {
                                                player.get(i).sortByRank(player.get(i).getCard());
                                                sort = true;
                                            } else if (intChoice[0] == -2) {
                                                player.get(i).sortBySuit(player.get(i).getCard());
                                                sort = true;
                                            } else
                                                sort = false;
                                        } else {
                                            System.out.println("Error choice!!");
                                            sort = true;
                                        }

                                    } while (sort);
                                    valid = false;
                                } else {
                                    System.out.println("Invalid choice!!");
                                    valid = true;
                                }

                            } else {
                                System.out.println("Please enter correct number!!");
                                valid = true;
                            }
                        } while (!valid);
                        //Check if the player choose the correct number for the choice
                        //which from 1 or 2

                        //Check is the set that the player create is successful or invalid.
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

                //When the player left 2 or less handCard the user are allowed to end the game.
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

