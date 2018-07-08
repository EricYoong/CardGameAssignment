import java.util.ArrayList;
import java.util.List;

abstract public class SetPlayerValidation extends Set {
    private double score;


    public double getScore() {
        return score;
    }

    public void checkSet(List<Card> Card) {
        if (isSameKind(Card)) {
            score = 1.5 * Card.size();
        } else if (isFlush(Card)) {
            score = 1.0 * Card.size();
        } else if (isStraight(Card)) {
            score = 1.2 * Card.size();
        }
    }
}
