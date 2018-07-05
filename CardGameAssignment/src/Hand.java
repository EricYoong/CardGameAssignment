import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Hand extends DeckOfCards {

    private ArrayList<Card> Hands = new ArrayList<Card>();


    public Hand() {
        clear();
        for (int i = 0; i < 10; i++) {
            Hands.add(pullRandom());
        }
    }

    public void clear() {
        Hands .clear();
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

    public void sortBySuit(){
       Collections.sort(Hands.sort());
    }

}
