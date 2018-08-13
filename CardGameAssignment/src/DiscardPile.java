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
            System.out.printf("|| %-60s||","The discard pile is: " + pileCard.get(pileCard.size() - 1).getRanks() + " " + pileCard.get(pileCard.size() - 1).getSuits());
            System.out.println(" ");
        }
    }

    //    reset the pile of card when there is no any deck card.
    public void resetPile(DeckOfCards d1) {
        tmpPile = new ArrayList<Card>();
        if (pileCard.isEmpty())
            System.out.println("|| There are no pile card.                                     ||");

        if (d1.checkDeck()) {
            for (int i = 0; i < pileCard.size() - 1; i++) {
                tmpPile.add(pileCard.get(i));
            }

            d1.addPile(tmpPile);

            for (int i = 0; i < tmpPile.size(); i++) {
                pileCard.remove(tmpPile.size() - i);
            }

            tmpPile.clear();
        }
    }
}
