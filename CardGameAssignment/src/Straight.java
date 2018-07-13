import java.util.List;

public class Straight extends Set{

    @Override
    public boolean check(List<Card> straight) {
        if (straight.size() < 3)
            return false;

        for (int i = 0; i < straight.size(); i++) {
            Card tmp = straight.get(i);
            while (straight.get(i + 1) != null) {
                if (tmp.getValue() >= straight.get(i + 1).getValue()) {
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
