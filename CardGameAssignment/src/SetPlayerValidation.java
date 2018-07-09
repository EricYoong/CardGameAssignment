import java.util.ArrayList;
import java.util.List;

public class SetPlayerValidation extends Set{

    private double score = 0;

    public double getScore() {
        return score;
    }

    public SetPlayerValidation(){
        super();
    }

    public boolean checkSet(List<Card> Card) {
        if (isSameKind(Card)) {
            score = 1.5 * Card.size();
            return true;
        } else if (isFlush(Card)) {
            score = 1.0 * Card.size();
            return true;
        } else if (isStraight(Card)) {
            score = 1.2 * Card.size();
            return true;
        } else {
            System.out.println("There are no set Available!");
            return true;
        }
    }

    public boolean addSet(ArrayList<Card> tmpSet) {
        if (!checkSet(tmpSet)) {
            return false;
        }
        score = getScore();
        return true;
    }


}
