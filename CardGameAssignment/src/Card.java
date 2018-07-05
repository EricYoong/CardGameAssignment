
public class Card {
    private Suits mSuit;
    private Ranks mRank;


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


    public int getCard() {
        return mRank.ordinal() + 1;
    }

    public Card(){
        super();
    }

    //	Initialize the card
    public Card(Suits suit, Ranks rank) {
        this.mSuit = suit;
        this.mRank = rank;
    }

    @Override
    public boolean equals(Object o) {
        return (o != null && o instanceof Card && ((Card) o).mRank == mRank && ((Card) o).mSuit == mSuit);
    }
}


