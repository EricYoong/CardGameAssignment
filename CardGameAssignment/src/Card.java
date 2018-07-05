
public class Card {
    private Suits mSuit;
    private Ranks mRank;

    public static enum Suits {
        Spade, Diamond, Club, Heart
    }

    public static enum Ranks {
        Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King
    }

    public Suits getSuits() {
        return mSuit;
    }

    public Ranks getRanks() {
        return mRank;
    }

    public int getCard() {
        return mRank.ordinal() + 2;
    }

    public Card(){

    }

    //	Initialize the card
    public Card(Suits suit, Ranks rank) {
        this.mSuit = suit;
        this.mRank = rank;
    }

    public boolean equals(Object o) {
        return (o != null && o instanceof Card && ((Card) o).mRank == mRank && ((Card) o).mSuit == mSuit);
    }
}


