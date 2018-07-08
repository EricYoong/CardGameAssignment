import java.util.ArrayList;

public class SameKind extends SetPlayerValidation {
    private ArrayList<Card> mSameKind;
    private int counter = 0;

    public void setKind(ArrayList<Card> sameKind) {
        if (comparePair(sameKind)) {
            for (int i = 0; i < sameKind.size(); i++) {
                mSameKind.add(sameKind.get(sameKind.size() - i));
                sameKind.remove(sameKind.size() - i);
            }
        } else
            System.out.println("Both card are not pair!");
    }

    public boolean comparePair(ArrayList<Card> sameKind) {
        Card tmp = sameKind.get(0);
        for (int i = 0; i < sameKind.size(); i++) {
           if(tmp == sameKind.get(i)){

           }
        }
        return false;
    }

}
