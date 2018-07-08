import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards extends Card {

    private ArrayList<Card> mCards;
    private ArrayList<Card> mPulledCards;
    private Random mRandom;

    public DeckOfCards() {
        mRandom = new Random();
        mPulledCards = new ArrayList<Card>();
        mCards = new ArrayList<Card>(Suits.values().length * Ranks.values().length);
        reset();
    }

    //    Reset the whole deck
    public void reset() {
        mPulledCards.clear();
        mCards.clear();
        /* Creating all possible cards... */
        for (Suits s : Suits.values()) {
            for (Ranks r : Ranks.values()) {
                Card c = new Card(s, r);
                mCards.add(c);
            }
        }
    }

    //    Get a random card
    public int randInt(int min, int max) {
        int randNum = mRandom.nextInt((max - min) + 1) + min;
        return randNum;
    }

    //    Get a random card and then remove from the deck
    public Card pullRandom() {
        if (mCards.isEmpty())
            return null;

        Card tmp = mCards.remove(randInt(0, mCards.size() - 1));
        if (tmp != null)
            mPulledCards.add(tmp);
        return tmp;
    }

    //    When the deck reach 0 it will add the card from the pile card
    public void addPile(ArrayList<Card> pileCard) {
        for (int i = 0; i < pileCard.size(); i++) {
            mCards.add(pileCard.get(i));
        }
    }

    public boolean isEmpty() {
        return mCards.isEmpty();
    }


}
