import java.util.ArrayList;

public class DiscardPile {
    private ArrayList<Card> pileCard;
    private ArrayList<Card> tmpPile;

    public DiscardPile(DeckOfCards d1) {
        pileCard = new ArrayList<Card>();
        pileCard.add(d1.pullRandom());
    }

    public void addPile(Card card) {
        pileCard.add(card);
    }

    public void displayPile() {
        if (pileCard.isEmpty()) {
            System.out.println("There are no pile Card.");
        } else {
            System.out.println("The discard pile is: " + pileCard.get(pileCard.size() - 1).getRanks() + " " + pileCard.get(pileCard.size() - 1).getSuits());
        }
    }

    public boolean resetPile(DeckOfCards d1) {
        tmpPile = new ArrayList<Card>();
        if (pileCard.isEmpty()) {
            System.out.println("There are no pile card.");
            return false;
        }

        for (int i = 0; i < pileCard.size() - 1; i++) {
            if(!tmpPile.add(pileCard.get(i)))
                return false;
        }

        d1.addPile(tmpPile);

        for (int i = 0; i < tmpPile.size(); i++) {
            pileCard.remove(tmpPile.size() - i);
        }

        tmpPile.clear();
        return true;
    }


}
