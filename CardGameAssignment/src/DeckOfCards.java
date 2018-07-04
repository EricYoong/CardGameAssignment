import java.util.ArrayList;
import java.util.Random;

public class DeckOfCards {

	private ArrayList<Card> mCards;
	private ArrayList<Card> mPulledCards;
	private Random mRandom;

	public static enum Suit {
		SPADES, HEARTS, DIAMONDS, CLUBS;
	}

	public static enum Rank {
		TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;
	}

	public DeckOfCards() {
		mRandom = new Random();
		mPulledCards = new ArrayList<Card>();
		mCards = new ArrayList<Card>(Suit.values().length * Rank.values().length);
		reset();
	}

	public void reset() {
		mPulledCards.clear();
		mCards.clear();
		/* Creating all possible cards... */
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				Card c = new Card(s, r);
				mCards.add(c);
			}
		}
	}

	public static class Card {

		private Suit mSuit;
		private Rank mRank;

		public Card(Suit suit, Rank rank) {
			this.mSuit = suit;
			this.mRank = rank;
		}

		public Suit getSuit() {
			return mSuit;
		}

		public Rank getRank() {
			return mRank;
		}

		public int getValue() {
			return mRank.ordinal() + 2;
		}

		@Override
		public boolean equals(Object o) {
			return (o != null && o instanceof Card && ((Card) o).mRank == mRank && ((Card) o).mSuit == mSuit);
		}

	}

	/**
	 * get a random card, removing it from the pack
	 * 
	 * @return
	 */
	public Card pullRandom() {
		if (mCards.isEmpty())
			return null;

		Card res = mCards.remove(randInt(0, mCards.size() - 1));
		if (res != null)
			mPulledCards.add(res);
		return res;
	}

	/**
	 * Get a random cards, leaves it inside the pack
	 * 
	 * @return
	 */
	public Card getRandom() {
		if (mCards.isEmpty())
			return null;

		Card res = mCards.get(randInt(0, mCards.size() - 1));
		return res;
	}

	/**
	 * Returns a pseudo-random number between min and max, inclusive. The difference
	 * between min and max can be at most <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min
	 *            Minimum value
	 * @param max
	 *            Maximum value. Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public int randInt(int min, int max) {
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = mRandom.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public boolean isEmpty() {
		return mCards.isEmpty();
	}
}
