import java.util.ArrayList;

public class Pair{
    private ArrayList<Card> mPair;

    public void setPair(ArrayList<Card> pair){
        if(comparePair(pair)){
            for(int i =0;i<pair.size();i++){
                mPair.add(pair.get(pair.size()-i));
                pair.remove(pair.size()-i);
            }
        }else
            System.out.println("Both card are not pair!");
    }

    public boolean comparePair(ArrayList<Card> pair){
        if(pair.size()<2){
            return(pair.get(0).getValue() == pair.get(1).getValue());
        }else
            return false;
    }

}
