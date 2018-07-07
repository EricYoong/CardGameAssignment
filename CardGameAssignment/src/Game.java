public class Game {
    public static void main(String[] args){
        DeckOfCards d1 = new DeckOfCards();
        Player p1 = new Player(d1);

        p1.showHand();

    }
}
