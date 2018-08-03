import java.util.Scanner;
import java.util.ArrayList;
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
        boolean valid2 = true;
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
                            boolean nameValid = false;

                            while (nameValid == false) {
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
                                    if (sPlayer.isEmpty() || sPlayer.equals(" ") || sPlayer.equals("")) {
                                        System.out.println("Cannot be empty name!!");
                                        System.out.println(" ");
                                    } else {
                                        if (player.get(j).checkName(sPlayer)) {
                                            System.out.println("The player name is existing!!");
                                            System.out.println("Please re-enter");
                                            System.out.println(" ");
                                            break;
                                        } else {
                                            nameValid = true;
                                        }
                                    }
                                }
                                if (nameValid != false) {
                                    player.add(tmp = new Player(sPlayer, d1));
                                }
                            }
                        }
                        valid = true;
                    } else {
                        System.out.println("|| It is an invalid number of player!                          ||");
                        System.out.println(" ");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("|| That is not a correct number                                ||");
                System.out.println(" ");
            }
        }

//        pile deck create
        p1 = new DiscardPile(d1);

        //Loop the game if the player is not choose to win.
        while (validGame) {
            //reset pile for start game
            p1.resetPile(d1);
            //Game started
            for (int i = 0; i < player.size(); i++) {
                boolean sort;
                String sortRank = String.format("|| %-60s||", "-1. Do Rank sorting.");
                String sortSuit = String.format("|| %-60s||", "-2. Do Suit sorting.");
                int tmp;

                //Use to get user want choose seton hand or take one card from the deck
                valid = false;
                while (valid == false) {
                    try {

                        player.get(i).sortBySuit(player.get(i).getCard());
                        //Get user input
                        do {

                            String pName=String.format("** %37s",player.get(i).getName() + " Turns");
                            //use to call out player name.
                            System.out.println(" ");
                            System.out.println("*****************************************************************");
                            System.out.println("*****************************************************************");
                            System.out.print(pName);
                            System.out.printf("%25s","**");
                            System.out.println();
                            System.out.println("*****************************************************************");
                            System.out.println("*****************************************************************");

                            //Display the discard pile card.
                            p1.displayPile();

                            //Display the number of deck of cards
                            System.out.printf("|| %-60s||","No of Deck Cards: " + d1.sizeOfDeck());
                            System.out.println(" ");

                            //Display the hand card of the player
                            player.get(i).showHand2();

                            //function Let the user choose to create set on hand or take one card from the deck.
                            System.out.println("||*************************************************************||");
                            System.out.println("|| 1. Do you want to make Set from your handCard.              ||");
                            System.out.println("|| 2. Do you want to take a card from the deck and             ||");
                            System.out.println("||    discard 1 card from your hand then only create a set.    ||");
                            System.out.println("||    and create a set.                                        ||");
                            System.out.println(sortRank + "\n" + sortSuit);
                            System.out.println("*****************************************************************");
                            System.out.println("*****************************************************************" + "\n");
                            System.out.print("Please enter your choice: ");
                            choice = input.nextLine();
                            System.out.println(" ");
                            st = new StringTokenizer(choice, " ");
                            tmp = st.countTokens();
                            intChoice = new int[tmp];

                            //Check if the player is insert one number?
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
                                    System.out.println(" ");
                                    String pName2=String.format("** %37s",player.get(i).getName() + " Turns");
                                    //use to call out player name.
                                    System.out.println(" ");
                                    System.out.println("*****************************************************************");
                                    System.out.println("*****************************************************************");
                                    System.out.print(pName2);
                                    System.out.printf("%25s","**");
                                    System.out.println();
                                    System.out.println("*****************************************************************");
                                    System.out.println("*****************************************************************");

                                    //Display the discard pile card.
                                    p1.displayPile();

                                    //Display the number of deck of cards
                                    System.out.printf("|| %-60s||","No of Deck Cards: " + d1.sizeOfDeck());
                                    System.out.println(" ");

                                    System.out.println("|| Card Added.                                                 ||");

                                    do {
                                        player.get(i).showHand2();

                                        //Get the user input to remove card they want from the handCard.
                                        System.out.println("||*************************************************************||");
                                        System.out.println("|| Which card would you like to remove.                        ||");
                                        System.out.println(sortRank + "\n" + sortSuit);
                                        System.out.println("||*************************************************************||");
                                        System.out.println("||*************************************************************||");
                                        System.out.print("Please choose one card: ");
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
                                                valid = true;
                                            }
                                        } else {
                                            System.out.println("Error.");
                                            valid = true;
                                        }
                                    } while (valid);
                                    valid = false;

                                    //Let the user sort their card before make the choice.
                                } else if (intChoice[0] == -2 || intChoice[0] == -3) {
                                    player.get(i).sortCard(intChoice[0]);
                                    valid = true;
                                } else {
                                    System.out.println("|| Invalid choice!!                                            ||");
                                    valid = true;
                                }

                            } else {
                                System.out.println("|| Please enter correct number!!                               ||");
                                valid = true;
                            }
                        } while (valid);

                        //exit loop
                        valid = true;
                    } catch (NumberFormatException e) {
                        System.out.println("|| That is not a correct number.                               ||");
                        valid = false;
                    }
                }
                //main functions
                valid = false;
                while (valid == false) {
                    try {
                        player.get(i).sortBySuit(player.get(i).getCard());

                        //When the player left 2 or less handCard the user are allowed to end the game.
                        if (player.get(i).checkHand()) {
                            do {
                                try {
                                    System.out.println("|| You have left 2 cards or less hand cards.                   ||");
                                    System.out.println("|| Do you want to end the game.                                ||");
                                    System.out.println("|| If \"yes\" please type knock, if no press \"no\" to continue    ||");
                                    System.out.printf("Choice: ");

                                    choice = input.nextLine();

                                    if (player.get(i).checkGame(player.get(i).getCard(), choice)) {
                                        valid = true;
                                        valid2 = false;
                                    } else
                                        valid = false;

                                } catch (NumberFormatException e) {
                                    System.out.println("|| That is not a correct number                                ||");
                                    valid = false;
                                }
                            } while (valid == false);
                        }

                        sort = true;

                        if (valid2 == true) {
                            do {
                                //Game continue
                                //use to call out player name.
                                String pName=String.format("** %37s",player.get(i).getName() + " Turns");
                                System.out.println(" ");
                                System.out.println("*****************************************************************");
                                System.out.println("*****************************************************************");
                                System.out.print(pName);
                                System.out.printf("%25s","**");
                                System.out.println();
                                System.out.println("*****************************************************************");
                                System.out.println("*****************************************************************");
                                //Display the player's handCard and get user input
                                p1.displayPile();

                                //Display the number of deck of cards
                                System.out.printf("|| %-60s||","No of Deck Cards: " + d1.sizeOfDeck());
                                System.out.println(" ");

                                player.get(i).showHand();
                                choice = input.nextLine();
                                st = new StringTokenizer(choice, " ");
                                tmp = st.countTokens();
                                intChoice = new int[tmp];

                                for (int j = 0; j < tmp; j++) {
                                    intChoice[j] = Integer.parseInt(st.nextToken()) - 1;
                                }

                                //check the user choices whether they want to sort the card.
                                if (tmp <= 1) {
                                    if (intChoice[0] == -2 || intChoice[0] == -3) {
                                        player.get(i).sortCard(intChoice[0]);
                                        sort = true;
                                    } else if (intChoice[0] == -1)
                                        sort = false;
                                } else
                                    sort = false;

                            } while (sort);

                            //Check is the set that the player create is successful or invalid.
                            //or want to skip a not
                            //Check the tmp size is 1? if the tmp size is not 1 then it is list of card
                            if (tmp <= 1) {
                                if (intChoice[0] == -1) {
                                    valid = true;
                                }
                            } else if (!player.get(i).getSet(intChoice)) {
                                System.out.println(" ");
                                System.out.println("****************************************************************************");
                                System.out.println("****************************************************************************");
                                System.out.println("  Invalid Set Build or Card selected!!!");
                                System.out.println("  To Build Straight you must have at least 3 cards (e.g: 10 J Q K)");
                                System.out.println("  To Build Flush you must have at least 4 cards (e.g: spade spade spade spade)");
                                System.out.println("  To Build SameKind you must have 2 to 4 cards (e.g: 10 10 10 10)");
                                System.out.println("****************************************************************************");
                                System.out.println("****************************************************************************");
                                System.out.println(" ");
                                valid = false;
                            } else
                                valid = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("|| That is not a correct number                                ||");
                        System.out.println(" ");
                        valid = false;
                    }
                }
            }
        }
    }
}
