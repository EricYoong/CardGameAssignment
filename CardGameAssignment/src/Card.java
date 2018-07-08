
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
        Spade, Diamond, Club, Heart
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
    public int compareTo(Card compareCard) {
        Card compareToCard = compareCard;
        Suits thisSuit = this.mSuit;
        Ranks thisRank = this.mRank;

        if (thisSuit.ordinal() == compareToCard.getSuits().ordinal() &&
                thisRank.ordinal() == compareToCard.getRanks().ordinal()) {
            return 0;
        } else if (thisSuit.ordinal() == compareToCard.getSuits().ordinal()) {
            if (thisRank.ordinal() > compareToCard.getRanks().ordinal()) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}


