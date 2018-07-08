import java.util.List;

abstract public class Straight extends Set {

    public boolean isStraight(List<Card> straight) {
        if (straight.size() < 3)
            return false;

        for (int i = 0; i < straight.size(); i++) {
            Card tmp = straight.get(i);
            if (straight.get(i + 1) != null) {
                if (tmp.getValue() > straight.get(i + 1).getValue()) {
                    return false;
                }
            } else
                return false;
        }
        return true;
    }
}
