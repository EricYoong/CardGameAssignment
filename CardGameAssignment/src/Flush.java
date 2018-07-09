import java.util.List;

public class Flush extends Set {

    @Override
    public boolean check(List<Card> flush) {
        Card tmp = flush.get(0);

        if (flush.size() < 4)
            return false;

        for (int i = 0; i < flush.size(); i++) {
            if (tmp.getSuits() != flush.get(i).getSuits()) {
                return false;
            }
        }
        return true;
    }
}
