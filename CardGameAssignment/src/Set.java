import java.util.List;

abstract public class Set {

    public abstract void checkSet(List<Card> Card);
    public abstract boolean isSameKind(List<Card> sameKind);
    public abstract boolean isStraight(List<Card> straight);
    public abstract boolean isFlush(List<Card> flush);



}
