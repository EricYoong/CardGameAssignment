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

        if (setSame.check(tmpSet)) {

            this.score += setSame.setScore(tmpSet.size());
            setCard.addAll(tmpSet);
            System.out.println(" ");
            System.out.println("Set for SameKind created!!");
            System.out.print("Score Updated: ");
            System.out.printf("%.2f", getScore());
            return true;

        } else if (setStraight.check(tmpSet)) {

            this.score += setStraight.setScore(tmpSet.size());
            setCard.addAll(tmpSet);
            System.out.println(" ");
            System.out.println("Set for Straight created !!");
            System.out.print("Score Updated: ");
            System.out.printf("%.2f", getScore());
            return true;

        } else if (setFlush.check(tmpSet)) {

            this.score += setFlush.setScore(tmpSet.size());
            System.out.println(" ");
            setCard.addAll(tmpSet);
            System.out.println("Set for Flush create !!");
            System.out.print("Score Updated: ");
            System.out.printf("%.2f", getScore());
            return true;

        } else
            return false;
    }


}
