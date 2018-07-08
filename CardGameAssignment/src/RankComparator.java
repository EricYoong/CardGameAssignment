import java.util.Comparator;

public class RankComparator implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
        if (card1.getRanks() == card2.getRanks()) {
            return card1.getSuits().compareTo(card2.getSuits());
        }
        return card1.getRanks().compareTo(card2.getRanks());
    }
}
