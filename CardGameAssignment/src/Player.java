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

    public Player() {
        super();
    }

    public Player(String name, DeckOfCards d1) {
        handCard = new ArrayList<Card>();
        for (int i = 0; i < 10; i++) {
            addCards(d1);
        }
        sortByRank(handCard);
        setCard = new ArrayList<Card>();
        this.name = name;
    }

    public void showHand() {
        if (handCard.isEmpty()) {
            System.out.println("There are no cards hold by the player.");
        } else {
            System.out.println("This is your hand card:");
            for (int i = 0; i < handCard.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + handCard.get(i).getRanks() + " " + handCard.get(i).getSuits());
            }
            System.out.println("[-1] Sort by Rank, [-2] Sort by Suits");
            System.out.println("This is your total score: " + getScore());
            System.out.println("Please select atleast 2 card for set, ");
            System.out.printf("or choose -1 or -2 to do sorting: ");
        }
    }

    public void showHand2(){
        if (handCard.isEmpty()) {
            System.out.println("There are no cards hold by the player.");
        } else {
            System.out.println("This is your hand card:");
            for (int i = 0; i < handCard.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + handCard.get(i).getRanks() + " " + handCard.get(i).getSuits());
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
            if (tmp[i] < 0 || tmp[i] > handCard.size())
                return false;

            if (!tmpCard.add(findCard(tmp[(tmp.length - 1) - i]))) {
                return false;
            }
            handCard.remove(tmp[(tmp.length - 1) - i]);
        }

        Collections.sort(tmpCard, rankComparator);

        if (!valid.addSet(tmpCard, setCard)) {
            System.out.println("This is an invalid set!");
            return false;
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

    public int findCard(Card card) {
        return handCard.indexOf(card);
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

    public boolean checkName(String name) {
        return this.name.equals(name);
    }

    public boolean checkHand() {
        if (handCard.size() > 2)
            return false;
        else
            return true;
    }
}
