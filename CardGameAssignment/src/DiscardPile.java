import java.util.ArrayList;

public class DiscardPile extends DeckOfCards{
    ArrayList<Card> pileCard;
    ArrayList<Card> tmpPile;

    public DiscardPile(){
        pileCard = new ArrayList<Card>();
        pileCard.add(super.getRandom());
    }

    public void addPile(Card card){
        pileCard.add(card);
    }

    public void resetPile(){
        for(int i = 0;i<pileCard.size()-1;i++){
            tmpPile.add(pileCard.get(i));
        }

        super.addPile(tmpPile);

        for(int i = 0;i<tmpPile.size();i++){
            pileCard.remove(tmpPile.size()-i);
        }

        tmpPile.clear();
    }


}
