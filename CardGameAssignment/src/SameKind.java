import java.util.ArrayList;
import java.util.List;

abstract public class SameKind extends Set {

    public boolean isSameKind(List<Card> sameKind) {
        Card tmp = sameKind.get(0);
        for (int i = 0; i < sameKind.size(); i++) {
           if(tmp.getValue() != sameKind.get(i).getValue()){
               return false;
           }
        }
        return true;
    }

}
