import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player{

    private List<Card> handCard;
    private List<Card> setCard;
    private double score = 0;
    private RankComparator rankComparator = new RankComparator();
    private SuitComparator suitComparator = new SuitComparator();
    private SetPlayerValidation valid;

    public Player(){
        super();
    }

    public Player(DeckOfCards d1) {
        handCard = new ArrayList<Card>();
        for (int i = 0; i < 10; i++) {
            addCards(d1);
        }
        setCard = new ArrayList<Card>();
    }

    public void showHand() {
        if (handCard.isEmpty()) {
            System.out.println("There are no cards hold by the player.");
        } else {
            for (int i = 0; i < 10; i++) {
                System.out.println(handCard.get(i).getRanks() + " " + handCard.get(i).getSuits());
            }
        }
    }

    public double getScore(){
        return score;
    }

    public void addSet(ArrayList<Card> tmpSet) {
        sortByRank(tmpSet);
        valid.checkSet(tmpSet);
        score = valid.getScore();
    }

    public void addCards(DeckOfCards d1) {
        handCard.add(d1.pullRandom());
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

    public void sortByRank(List<Card> Card){
        Collections.sort(Card, rankComparator);
    }
    public void sortBySuit(List<Card> Card){
        Collections.sort(Card, suitComparator);
    }

}
