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

    public static void main(String[] args) {
        DeckOfCards d1 = new DeckOfCards();
        d1.pullRandom();
    }

    public int randInt(int min, int max){
        int randNum = mRandom.nextInt((max-min)+1)+min;
        return randNum;
    }

    public Card pullRandom() {
        if (mCards.isEmpty())
            return null;

        Card tmp = mCards.remove(randInt(0,mCards.size()-1));
        if(tmp!=null)
            mPulledCards.add(tmp);
        return tmp;
    }

    public Card getRandom() {
        if (mCards.isEmpty())
            return null;

        Card tmp = mCards.get(randInt(0, mCards.size() - 1));
        return tmp;
    }

    public boolean isEmpty(){
        return mCards.isEmpty();
    }
}
