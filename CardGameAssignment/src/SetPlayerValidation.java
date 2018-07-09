import java.util.ArrayList;
import java.util.List;

public class SetPlayerValidation{

    private double score = 0;
    private Set set;

    public double getScore() {
        return score;
    }



    public boolean addSet(List<Card> tmpSet, List<Card> handSet) {

        if (!set.check(tmpSet)) {
            return false;
        }

        score = getScore();
        return true;
    }


}
