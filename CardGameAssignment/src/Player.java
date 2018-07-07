import java.util.ArrayList;
import java.util.Collections;

public class Player {

    private ArrayList<Card> handCard;
    private ArrayList<Card> setCard;


    public Player() {
        handCard = new ArrayList<Card>();
        setCard = new ArrayList<Card>();
    }

    public void showHand(){
        for(int i=0;i<10;i++){
        }
    }


    public int findCard( Card card ) {
        return handCard.indexOf( card );
    }
//
//    public boolean replaceCard( Card oldCard, Card replacementCard ) {
//        int location = findCard( oldCard );
//        if ( location < 0 )
//            return false;
//        hand.set( location, replacementCard );
//        return true;
//    }




}
