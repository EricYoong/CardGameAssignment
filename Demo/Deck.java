// Deck.java - John K. Estell - 8 May 2003
// last modified: 23 Febraury 2004
// Implementation of a deck of playing cards.  Uses the Card class.

import java.util.*;


/**
 * Represents a deck of playing cards.  In order to have maximum flexibility,
 * this class does not implement a standard deck of playing cards; it only
 * provides the functionality of a deck of cards.  The client programmer must
 * instantiate a Deck object, then populate it with the set of playing cards
 * appropriate for the card game being implemented.  This allows for proper
 * implementation of card games such as pinochle (a 48-card deck containing
 * only aces, nines, tens, jacks, queens, and kings in all four suits, with
 * each card present twice in the deck) or casino-style blackjack (where six
 * standard decks are used for a game).  
 * @author John K. Estell
 * @version 1.0
 */
public class Deck {
   private java.util.List deck;
   private int index;
 
  
  /**
   * Creates an empty deck of cards.
   */
   public Deck() {
      deck = new ArrayList();
      index = 0;
   }
  
  
  /**
   * Adds a card to the deck.
   * @param card card to be added to the deck.
   */
   public void addCard( Card card ) {
      deck.add( card );
   }
  
  
  /**
   * The size of a deck of cards.
   * @return the number of cards present in the full deck.
   */
   public int getSizeOfDeck() {
      return deck.size();
   }
   
   
  /**
   * The number of cards left in the deck.
   * @return the number of cards left to be dealt from the deck.
   */
   public int getNumberOfCardsRemaining() {
      return deck.size() - index;
   }
   
   
  /**
   * Deal one card from the deck.
   * @return a card from the deck, or the null reference if there
   * are no cards left in the deck.
   */
   public Card dealCard() {
      if ( index >= deck.size() )
         return null;
      else
         return (Card) deck.get( index++ );
   }
   
   
  /**
   * Shuffles the cards present in the deck.
   */
   public void shuffle() {
      Collections.shuffle( deck );
   }
   
   
  /**
   * Looks for an empty deck.
   * @return <code>true</code> if there are no cards left to be dealt from the deck.
   */
   public boolean isEmpty() {
      if ( index >= deck.size() )
         return true;
      else
         return false;
   }
   
   
  /**
   * Restores the deck to "full deck" status.
   */
   public void restoreDeck() {
      index = 0;
   }   

}
