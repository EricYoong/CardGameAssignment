import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private List<Card> handCard;
    private List<Card> setCard;
    private String name;
    private RankComparator rankComparator = new RankComparator();
    private SuitComparator suitComparator = new SuitComparator();
    private SetPlayerValidation valid = new SetPlayerValidation();

    private final int maxNameLength = 10;
    private final int fitHeight = 130;

    private int drawAmt = 1;

    private boolean cantPlay = false;

    public Player(String name, DeckOfCards d1) {
        handCard = new ArrayList<Card>();
        for (int i = 0; i < 10; i++) {
            addCards(d1);
        }
        sortBySuit(handCard);
        setCard = new ArrayList<Card>();
        this.name = name;
    }

    public void showHand() {
        String sortRank = String.format("|| %-60s||", "-1. Do Rank sorting.");
        String sortSuit = String.format("|| %-60s||", "-2. Do Suit sorting.");

        if (handCard.isEmpty()) {
            System.out.println("|| There are no cards hold by the player.                      ||");
        } else {
            showHand2();

            System.out.println("||*************************************************************||");
            System.out.println(sortRank + "\n" + sortSuit);
            System.out.printf("|| %-60s||", "This is your total score: " + getScore());
            System.out.println("\n|| Please select atleast 2 card for set                        ||");
            System.out.println("|| Or choose 0 to skip the game.                                 ||");
            System.out.println("*****************************************************************");
            System.out.println("*****************************************************************" + "\n");
            System.out.print("Please enter your choice: ");
        }
    }

    public void showHand2() {
        if (handCard.isEmpty()) {
            System.out.println("|| There are no cards hold by the player.                      ||");
        } else {
            System.out.println("|| This is your hand card:                                     ||");
            for (int i = 0; i < handCard.size(); i++) {
                String tmp = String.format("|| %-60s||", "[" + (i + 1) + "] " + handCard.get(i).getRanks() + " " + handCard.get(i).getSuits());
                System.out.println(tmp);
            }
        }
    }

    public int getMaxNameLength() {
        return maxNameLength;
    }

    public void setName(String name1) {
        this.name = name1;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return valid.getScore();
    }

    public boolean getSet(int[] tmp) {
        List<Card> tmpCard = new ArrayList<Card>();

        if (tmp.length < 2)
            return false;

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] < 0 || tmp[i] > 10)
                return false;
        }

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] < 0 || tmp[i] > handCard.size())
                return false;

            if (!tmpCard.add(findCard(tmp[(tmp.length - 1) - i]))) {
                return false;
            }
        }

        Collections.sort(tmpCard, rankComparator);

        if (!valid.addSet(tmpCard, setCard)) {
            return false;
        }

        for (int i = 0; i < tmp.length; i++) {
            handCard.remove(tmp[(tmp.length - 1) - i]);
        }
        return true;
    }

    public boolean getSetbyCard(List<Card> tmpCard) {
        if (tmpCard.size() < 2)
            return false;

        Collections.sort(tmpCard, rankComparator);

        if (!valid.addSet(tmpCard, setCard)) {
            return false;
        }

        for (int i = 0; i < tmpCard.size(); i++) {
            handCard.remove(tmpCard.get(tmpCard.size() - 1 - i));
        }
        return true;
    }

    public void addCards(DeckOfCards d1) {
        handCard.add(d1.pullRandom());
    }

    public void addCards2(ArrayList<Card> tmp) {
        for (int i = 0; i < tmp.size(); i++) {
            handCard.add(tmp.get(i));
        }
        tmp.clear();
    }

    public List<Card> getCard() {
        return handCard;
    }

    public Card findCard(int i) {
        return handCard.get(i);
    }

    public void removeCard(Card card) {
        handCard.remove(card);
    }

    public void sortByRank(List<Card> Card) {
        Collections.sort(Card, rankComparator);
    }

    public void sortBySuit(List<Card> Card) {
        Collections.sort(Card, suitComparator);
    }

    public void sortCard(int tmp) {
        if (tmp == -2) {
            sortByRank(handCard);
        } else if (tmp == -3) {
            sortBySuit(handCard);
        }
    }

    public boolean checkName(String name) {
        return this.name.equals(name);
    }

    public boolean checkHand() {
        if (handCard.size() > 2)
            return false;
        else
            return true;
    }

    public boolean checkGame(List<Card> Card, String choice) {
        String notEnd = "no";
        String end = "knock";

        if (Card.size() < 2) {
            try {
                if (end.equals(choice)) {
                    return true;
                } else if (notEnd.equals(choice)) {
                    return false;
                } else {
                    System.out.printf("|%-50s|", "Invalid choices. Please re-enter: \n");
                    return false;
                }

            } catch (NumberFormatException e) {
                System.out.println("That is not a correct number");
                return false;
            }
        } else
            return false;
    }

    public int handCardSize() {
        return handCard.size();
    }

    // Display cards in hand
    public HBox displayHand() throws FileNotFoundException {
        HBox playerhandBox = new HBox();

        for (int i = 0; i < handCard.size(); i++) {
            Image cardImg = new Image
                    (new FileInputStream(handCard.get(i).getImagePath(handCard.get(i))));

            ImageView iv = (getCardIV(cardImg, handCard.get(i).getAngle()));

            iv.getStyleClass().add("handCard");

            playerhandBox.getChildren().add(iv);
        }
        return playerhandBox;
    }

    // Display cards in hand
    public HBox displaysetHand(ArrayList<Card> tmp) throws FileNotFoundException {
        HBox playerhandBox2 = new HBox();

        for (int i = 0; i < tmp.size(); i++) {
            Image cardImg = new Image
                    (new FileInputStream(tmp.get(i).getImagePath(tmp.get(i))));

            ImageView iv = (getCardIV(cardImg, tmp.get(i).getAngle()));

            iv.getStyleClass().add("setCard");

            playerhandBox2.getChildren().add(iv);
        }
        return playerhandBox2;
    }

    //Cover cards in hand
    public HBox coverHand() throws FileNotFoundException {
        HBox playerhandBox = new HBox();

        for (int i = 0; i < handCard.size(); i++) {
            Image cardImg = new Image
                    (new FileInputStream("CardGameAssignment/src/images/card_back.png"));

            playerhandBox.getChildren().add(getCardIV(cardImg, handCard.get(i).getAngle()));
        }
        return playerhandBox;
    }

    public ImageView getCardIV(Image image, double angle) {
        ImageView cardIV = new ImageView(image);

        DropShadow dShdw = new DropShadow(BlurType.GAUSSIAN, new Color(0, 0, 0, 0.65), 3.5, 0.25, 0, 0);
        dShdw.setWidth(25);
        dShdw.setHeight(25);

        cardIV.setPreserveRatio(true);
        cardIV.setFitHeight(130);
        cardIV.setSmooth(true);
        cardIV.setEffect(dShdw);

        cardIV.setRotate(angle);

        return cardIV;
    }

    public int getDrawAmt() {
        return drawAmt;
    }

    public void setDrawAmt(int amt) {
        drawAmt = amt;
    }

    public boolean isCantPlay() {
        return cantPlay;
    }

    public void setCantPlay(boolean cantPlay) {
        this.cantPlay = cantPlay;
    }
}
