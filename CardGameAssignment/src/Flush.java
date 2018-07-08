import java.util.List;

abstract public class Flush extends Set {

    public boolean isFlush(List<Card> sameKind) {
        Card tmp = sameKind.get(0);
        for (int i = 0; i < sameKind.size(); i++) {
            if(tmp.getValue() != sameKind.get(i).getValue()){
                return false;
            }
        }
        return true;
    }
}
