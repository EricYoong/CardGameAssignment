import java.util.List;

abstract public class Set {

   public Set(){

   }

    public boolean isSameKind(List<Card> sameKind) {
        Card tmp = sameKind.get(0);
        if (sameKind.size() < 2 || sameKind.size() > 4)
            return false;

        for (int i = 0; i < sameKind.size(); i++) {
            if (tmp.getValue() != sameKind.get(i).getValue()) {
                return false;
            }
        }
        return true;
    }
    public boolean isFlush(List<Card> flush) {
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
