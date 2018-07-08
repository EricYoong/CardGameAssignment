import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player extends SetPlayerValidation{

    private List<Card> handCard;
    private List<Card> setCard;
    private RankComparator rankComparator = new RankComparator();
    private SuitComparator suitComparator = new SuitComparator();

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

    public void addSet(ArrayList<Card> tmpSet) {
        for (int i = 0; i < tmpSet.size(); i++) {
            if (!setCard.contains(tmpSet.get(tmpSet.size() - i))) {
                setCard.add(tmpSet.get(tmpSet.size() - i));
                removeCard(tmpSet.get(tmpSet.size() - i));
            }
        }
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
