
public class Player {
	private Card card = new Card();
	
	public Player() {
		int[] hand= this.card.getCard();
		
		showHand(hand);
		
		
	}
	
	public void showHand(int []hand) {
		for(int i = 0;i<10;i++) {
			String suit = this.card.getSuits(hand[i]/13);
			String rank = this.card.getRanks(hand[i]%13);
			System.out.println("Card number " + hand[i] + ": " 
			        + rank + " of " + suit);
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println("P1");
		Player p1 = new Player();
		System.out.println("P2");
		Player p2 = new Player();
	}
	
	
	
}
