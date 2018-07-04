import java.util.Arrays;
import java.util.Collections;

public class Card {
	private int[] card = new int[52];
	private String[] suits = {"Spades", "Hearts", "Clubs", "Diamonds"};
	private String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9",
            "10", "Jack", "Queen", "King"};
    
	
	
	public void showCardInfo() {
		for(int i = 0;i<10;i++) {
			String suit = suits[card[i]/13];
			String rank = ranks[card[i]%13];
			System.out.println("Card number " + card[i] + ": " 
			        + rank + " of " + suit);
		}
		
	}
	
	public String getSuits(int i) {
		return suits[i];
	}
	
	public String getRanks(int i) {
		return ranks[i];
	}
	
	public int[] getCard() {
		int[] tmp = new int[10];
		
		for(int i = 0 ; i< 10; i++) {
		}
		
		shuffle();
		
		return tmp;
	}
	
//	Shuffle the cards
	public void shuffle() {
		Collections.shuffle(Arrays.asList(card));
	}
	
//	Initialize the card
	public Card() {
		for(int i = 0;i < card.length;i++) {
			card[i] = i;
		}
		shuffle();
	}
	
	
	
}
