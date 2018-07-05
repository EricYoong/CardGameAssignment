import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class Hand extends DeckOfCards {

    private ArrayList<Card> Hands;

    private ArrayList<Card> cards;
    private String handCards;


    //Constuctor
    public Hand() {
        Hands = new ArrayList<Card>(10);
    }

    public void clear() {
        Hands.clear();
    }

    public void addCard() {
        if (Hands.size() > 11) {
            System.out.println("You have reach the limit to add card.");
        } else
            Hands.add(pullRandom());
    }

    public void removeCard(Card c) {
        if (Hands.contains(c)) {
            Hands.set(Hands.indexOf(c), null);
        }
    }

    public List<Card> sortBySuit(){
      Collections.sort(Hands);
      return Hands;
    }

}
