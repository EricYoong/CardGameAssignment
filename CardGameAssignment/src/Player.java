import java.util.ArrayList;
import java.util.Collections;

public class Player extends DeckOfCards{

    private ArrayList<Card> handCard;
    private ArrayList<Card> setCard;


    public Player() {
        handCard = new ArrayList<Card>();
        setCard = new ArrayList<Card>();
    }

    public void showHand() {
        if (handCard.isEmpty()) {
            System.out.println("There are no cards hold by the player.");
        }
        for (int i = 0; i < 10; i++) {
            handCard.get(i);
        }
    }

    public void addSet(ArrayList<Card> tmpSet){
        for(int i=0;i<tmpSet.size();i++){
            if(!setCard.contains(tmpSet.get(i))) {
                setCard.add(tmpSet.get(i));
                removeCard(tmpSet.get(i));
            }
        }
    }

    public void removeCard(Card card) {
        handCard.remove(card);
    }

    public void addCards(){
        handCard.add(super.getRandom());
    }

    public int findCard(Card card) {
        return handCard.indexOf(card);
    }
}
