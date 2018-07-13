
public class Card implements Comparable<Card> {
    private Suits mSuit;
    private Ranks mRank;

    public Card() {
        super();
    }

    //	Initialize the card
    public Card(Suits suit, Ranks rank) {
        this.mSuit = suit;
        this.mRank = rank;
    }

    //    Set a constance for the suits
    public static enum Suits {
        Spade, Heart, Club, Diamond
    }

    //    Set a constance for the Ranks
    public static enum Ranks {
        Ace, Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King
    }

    public Suits getSuits() {
        return mSuit;
    }

    public Ranks getRanks() {
        return mRank;
    }

    //    Get the value of the Card
    public int getValue() {
        return mRank.ordinal() + 1;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null && o instanceof Card && ((Card) o).mRank == mRank && ((Card) o).mSuit == mSuit);
    }

    @Override
    public int compareTo(Card c) {
        int rankCompare = mRank.compareTo(c.mRank);
        return rankCompare != 0 ? rankCompare : mSuit.compareTo(c.mSuit);
    }

}


