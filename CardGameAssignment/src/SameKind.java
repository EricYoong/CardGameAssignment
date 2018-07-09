import java.util.ArrayList;
import java.util.List;

public class SameKind extends Set{

    @Override
    public boolean check(List<Card> sameKind) {
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
}
