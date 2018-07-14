import java.util.List;

public class Straight extends Set{

    @Override
    public boolean check(List<Card> straight) {
        if (straight.size() < 3)
            return false;

        for (int i = 0; i < straight.size(); i++) {
            Card tmp = straight.get(i);
            if ((i+1) < straight.size()) {
                if (straight.get(i + 1).getValue() != tmp.getValue()+1) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public double setScore(int noCard){
        return 1.2 * noCard;
    }
}
