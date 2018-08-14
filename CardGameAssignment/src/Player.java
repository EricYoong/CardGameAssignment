import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private List<Card> handCard;
    private List<Card> setCard;
    private String name;
    private RankComparator rankComparator = new RankComparator();
    private SuitComparator suitComparator = new SuitComparator();
    private SetPlayerValidation valid = new SetPlayerValidation();

    public Player(String name, DeckOfCards d1) {
        handCard = new ArrayList<Card>();
        for (int i = 0; i < 10; i++) {
            addCards(d1);
        }
        sortBySuit(handCard);
        setCard = new ArrayList<Card>();
        this.name = name;
    }

    public void showHand() {
        String sortRank = String.format("|| %-60s||", "-1. Do Rank sorting.");
        String sortSuit = String.format("|| %-60s||", "-2. Do Suit sorting.");

        if (handCard.isEmpty()) {
            System.out.println("|| There are no cards hold by the player.                      ||");
        } else {
            showHand2();

            System.out.println("||*************************************************************||");
            System.out.println(sortRank + "\n" + sortSuit);
            System.out.printf("|| %-60s||", "This is your total score: " + getScore());
            System.out.println("\n|| Please select atleast 2 card for set                        ||");
            System.out.println("|| Or choose 0 to skip the game.                                 ||");
            System.out.println("*****************************************************************");
            System.out.println("*****************************************************************" + "\n");
            System.out.print("Please enter your choice: ");
        }
    }

    public void showHand2() {
        if (handCard.isEmpty()) {
            System.out.println("|| There are no cards hold by the player.                      ||");
        } else {
            System.out.println("|| This is your hand card:                                     ||");
            for (int i = 0; i < handCard.size(); i++) {
                String tmp = String.format("|| %-60s||", "[" + (i + 1) + "] " + handCard.get(i).getRanks() + " " + handCard.get(i).getSuits());
                System.out.println(tmp);
            }
        }
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return valid.getScore();
    }

    public boolean getSet(int[] tmp) {
        List<Card> tmpCard = new ArrayList<Card>();

        if (tmp.length < 2)
            return false;

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] < 0 || tmp[i] > 10)
                return false;
        }

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] < 0 || tmp[i] > handCard.size())
                return false;

            if (!tmpCard.add(findCard(tmp[(tmp.length - 1) - i]))) {
                return false;
            }
        }

        Collections.sort(tmpCard, rankComparator);

        if (!valid.addSet(tmpCard, setCard)) {
            return false;
        }

        for (int i = 0; i < tmp.length; i++) {
            handCard.remove(tmp[(tmp.length - 1) - i]);
        }
        return true;
    }

    public void addCards(DeckOfCards d1) {
        handCard.add(d1.pullRandom());
    }

    public List<Card> getCard() {
        return handCard;
    }

    public Card findCard(int i) {
        return handCard.get(i);
    }

    public void removeCard(Card card) {
        handCard.remove(card);
    }

    public void sortByRank(List<Card> Card) {
        Collections.sort(Card, rankComparator);
    }

    public void sortBySuit(List<Card> Card) {
        Collections.sort(Card, suitComparator);
    }

    public void sortCard(int tmp) {
        if (tmp == -2) {
            sortByRank(handCard);
        } else if (tmp == -3) {
            sortBySuit(handCard);
        }
    }

    public boolean checkName(String name) {
        return this.name.equals(name);
    }

    public boolean checkHand() {
        if (handCard.size() > 2)
            return false;
        else
            return true;
    }

    public boolean checkGame(List<Card> Card, String choice) {
        String notEnd = "no";
        String end = "knock";

        if (Card.size() < 2) {
            try {
                if (end.equals(choice)) {
                    return true;
                } else if (notEnd.equals(choice)) {
                    return false;
                } else {
                    System.out.printf("|%-50s|", "Invalid choices. Please re-enter: \n");
                    return false;
                }

            } catch (NumberFormatException e) {
                System.out.println("That is not a correct number");
                return false;
            }
        } else
            return false;
    }
}
