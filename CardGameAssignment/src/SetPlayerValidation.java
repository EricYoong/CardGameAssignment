import java.util.ArrayList;
import java.util.List;

abstract public class SetPlayerValidation extends Set{
    private double score;


    public double getScore(){
        return score;
    }

    public void checkSet(List<Card> Card){
        if(isSameKind(Card)){
            score = 1.5 * Card.size();
        }else if(isFlush(Card)){

        }else if(isStraight(Card)){

        }
    }
}
