import java.util.ArrayList;
import java.util.List;

abstract public class SetPlayerValidation{
    private double score;
    private List<Card> set = new ArrayList<Card>();
    private SameKind s1 = new SameKind();

    public void CheckSet(List<Card> Card){
        if(s1.comparePair(Card)){

        }
    }
}
