import java.util.List;

public class SetPlayerValidation {

    private double score = 0;

    public double getScore() {
        return score;
    }

    public boolean addSet(List<Card> tmpSet, List<Card> setCard) {

        Set setStraight = new Straight();
        Set setFlush = new Flush();
        Set setSame = new SameKind();

        if (setFlush.check(tmpSet)) {

            this.score += setFlush.setScore(tmpSet.size());
            setCard.addAll(tmpSet);
            System.out.println("Set for Flush create !!");
            System.out.println("Score Updated: " + getScore());
            return true;

        } else if (setStraight.check(tmpSet)) {

            this.score += setStraight.setScore(tmpSet.size());
            setCard.addAll(tmpSet);
            System.out.println("Set for Straight created !!");
            System.out.println("Score Updated: " + getScore());
            return true;

        } else if (setSame.check(tmpSet)) {

            this.score += setSame.setScore(tmpSet.size());
            setCard.addAll(tmpSet);
            System.out.println("Set for SameKind created!!");
            System.out.println("Score Updated: " + getScore());
            return true;

        } else
            return false;
    }


}
