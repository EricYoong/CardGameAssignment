// BlackjackHand.java - John K. Estell - 16 February 2004
// last modified 17 February 2004

public class BlackjackHand extends Hand {
    
   /**
    *  Evaluation of a (simple) blackjack hand.  For demonstration purposes,
    *  assumes that the hand consists of only two cards, so we're not implementing
    *  the complete logic for this...
    *  @return the value of the hand according to the rules of Blackjack.
    */
    public int evaluateHand() {
        int value = 0;
        for ( int i = 0; i < getNumberOfCards(); i++ ) {
            Card c = getCard( i );
            int thisValue = c.getRank().compareTo( Rank.ACE ) + 1;
            if ( thisValue > 10 )
               thisValue = 10;
            if ( thisValue == 1 )
               thisValue = 11;
            value += thisValue;
        }
        
        return value;
    }
}
            