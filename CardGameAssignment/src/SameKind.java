import java.util.ArrayList;
import java.util.List;

public class SameKind extends SetPlayerValidation {
    private List<Card> mSameKind;
    private int counter = 0;

    public SameKind(){

    }

    public void setKind(ArrayList<Card> sameKind) {
        if (comparePair(sameKind)) {
            for (int i = 0; i < sameKind.size(); i++) {
                mSameKind.add(sameKind.get(sameKind.size() - i));
                sameKind.remove(sameKind.size() - i);
            }
        } else
            System.out.println("Both card are not pair!");
    }

    public boolean comparePair(List<Card> sameKind) {
        Card tmp = sameKind.get(0);
        for (int i = 0; i < sameKind.size(); i++) {
           if(tmp == sameKind.get(i)){
                counter++;
           }else {
               System.out.println("There are only " + counter + " Kinds!");
               return false;
           }
        }
        return true;
    }

}
