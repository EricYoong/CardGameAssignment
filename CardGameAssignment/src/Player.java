import java.util.ArrayList;
import java.util.Collections;

public class Player {


    public Player() {
        super();
    }

    public Player(DeckOfCards tmp) {
        setHand(tmp);
    }


    public Card[] getHand() {
        return hand;
    }

    public void showHand(){
        for(int i=0;i<10;i++){
        }
    }


    public void setHand(DeckOfCards tmp) {
        for (int i = 0; i < 10; i++) {
            hand[i] = tmp.pullRandom();
        }
    }

    public void sort() {

    }

//    public int findCard( Card card ) {
//        return hand.indexOf( card );
//    }
//
//    public boolean replaceCard( Card oldCard, Card replacementCard ) {
//        int location = findCard( oldCard );
//        if ( location < 0 )
//            return false;
//        hand.set( location, replacementCard );
//        return true;
//    }



    public static void main(String[] args) {

        DeckOfCards d1 = new DeckOfCards();
        Player test = new Player();


    }


}
