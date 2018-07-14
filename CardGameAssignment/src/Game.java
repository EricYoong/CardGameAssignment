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
                        System.out.println(" ");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("That is not a correct number");
                System.out.println(" ");
            }
        }

//        pile deck create
        p1 = new DiscardPile(d1);

        //Loop the game if the player is not choose to win.
        while (validGame) {

            p1.resetPile(d1);
            //Game started
            for (int i = 0; i < player.size(); i++) {
                boolean sort;
                int tmp;

                //Use to get user want choose seton hand or take one card from the deck
                valid = false;
                while (valid == false) {
                    try {

                        player.get(i).sortByRank(player.get(i).getCard());
                        //Get user input
                        do {

                            //use to call out player name.
                            System.out.println(player.get(i).getName() + " Turns");

                            //Display the discard pile card.
                            p1.displayPile();
                            player.get(i).showHand2();

                            //function Let the user choose to create set on hand or take one card from the deck.
                            System.out.println("1. Do you want to make Set from your handCard.");
                            System.out.println("2. Do you want to take a card from the deck and");
                            System.out.println("discard 1 card from your hand then only create a set.");
                            System.out.println("Or choose -1 or -2 to do sorting. ");
                            System.out.printf("Please enter your choice: ");
                            choice = input.nextLine();
                            System.out.println(" ");
                            st = new StringTokenizer(choice, " ");
                            tmp = st.countTokens();
                            intChoice = new int[tmp];

                            //Check constraint
                            if (tmp <= 1) {

                                //set value of the intChoice
                                for (int j = 0; j < 1; j++) {
                                    intChoice[0] = Integer.parseInt(st.nextToken()) - 1;
                                }

                                //Check is the choice is 1 or 2 or (-1 or -2 to sort)
                                if (intChoice[0] == 0) {

                                    valid = false;

                                } else if (intChoice[0] == 1) {
                                    //add random card from the deck
                                    player.get(i).addCards(d1);

                                    //show player name and hand card after added the card.
                                    System.out.println(player.get(i).getName() + " Turns");
                                    System.out.println("Card Added.");
                                    player.get(i).sortByRank(player.get(i).getCard());
                                    do {
                                        player.get(i).showHand2();

                                        //Get the user input to remove card they want from the handCard.
                                        System.out.println("Which card would you like to remove.");
                                        System.out.println("Or choose -1 or -2 to do sorting. ");
                                        System.out.printf("Please choose one card: ");
                                        choice = input.nextLine();
                                        System.out.println(" ");
                                        st = new StringTokenizer(choice, " ");
                                        tmp = st.countTokens();
                                        intChoice = new int[tmp];
                                        if (tmp <= 1) {

                                            for (int j = 0; j < tmp; j++) {
                                                intChoice[j] = Integer.parseInt(st.nextToken()) - 1;
                                            }

                                            if (intChoice[0] >= 0 && intChoice[0] < 11) {

                                                //add pile card from the player hand
                                                p1.addPile(player.get(i).findCard(intChoice[0]));
                                                //remove a card from the hand after getting one card from the deck
                                                player.get(i).removeCard(player.get(i).findCard(intChoice[0]));
                                                valid = false;

                                            } else if (intChoice[0] == -2 || intChoice[0] == -3) {
                                                player.get(i).sortCard(intChoice[0]);
                                                valid = true;
                                            } else {
                                                System.out.println("Error choice.");
                                                System.out.println(" ");
                                                valid = true;
                                            }
                                        } else {
                                            System.out.println("Error.");
                                            System.out.println(" ");
                                            valid = true;
                                        }
                                    } while (valid);
                                    valid = false;

                                    //Let the user sort their card before make the choice.
                                } else if (intChoice[0] == -2 || intChoice[0] == -3) {
                                    player.get(i).sortCard(intChoice[0]);
                                    valid = true;
                                } else {
                                    System.out.println("Invalid choice!!");
                                    System.out.println(" ");
                                    valid = true;
                                }

                            } else {
                                System.out.println("Please enter correct number!!");
                                System.out.println(" ");
                                valid = true;
                            }
                        } while (valid);

                        //exit loop
                        valid = true;
                    } catch (NumberFormatException e) {
                        System.out.println("That is not a correct number");
                        System.out.println(" ");
                        valid = false;
                    }
                }


                //main functions
                valid = false;
                while (valid == false) {
                    try {
                        player.get(i).sortByRank(player.get(i).getCard());
                        sort = true;

                        do {
                            //Game continue
                            //use to call out player name.
                            System.out.println(player.get(i).getName() + " Turns");

                            //Display the player's handCard and get user input
                            p1.displayPile();
                            player.get(i).showHand();
                            choice = input.nextLine();
                            System.out.println(" ");
                            st = new StringTokenizer(choice, " ");
                            tmp = st.countTokens();
                            intChoice = new int[tmp];

                            for (int j = 0; j < tmp; j++) {
                                intChoice[j] = Integer.parseInt(st.nextToken()) - 1;
                            }

                            //check the user choices whether they want to sort the card.
                            if (tmp < 2) {
                                if (intChoice[0] == -2 || intChoice[0] == -3) {
                                    player.get(i).sortCard(intChoice[0]);
                                    sort = true;
                                }
                            }else
                                sort = false;

                        } while (sort);

                        //Check is the set that the player create is successful or invalid.
                        if (!player.get(i).getSet(intChoice)) {
                            System.out.println("Invalid Set Build or Card selected!!!");
                            System.out.println(" ");
                            valid = false;
                        } else
                            valid = true;

                    } catch (NumberFormatException e) {
                        System.out.println("That is not a correct number");
                        System.out.println(" ");
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
                                valid = true;
                            } else if (notEnd.equals(choices)) {
                                validGame = true;
                                valid = true;
                            } else {
                                System.out.printf("Invalid choices. Please re-enter: ");
                                valid = false;
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("That is not a correct number");
                            valid = false;
                        }
                    }

                }
            }
        }
    }
}

