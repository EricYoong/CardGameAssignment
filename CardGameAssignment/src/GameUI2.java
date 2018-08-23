import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

;


public class GameUI2 extends Application {

    private final String gameTitle = "Poker Card Game";

    private HashMap<String, String> notifications = new HashMap<String, String>();

    protected final int minPlayers = 2;
    protected final int maxPlayers = 4;
    protected int playersAmt = minPlayers;
    protected int playerRotation = -1;

    private boolean validationDraw = false;
    private int counter = 0;

    @FXML
    protected HBox handBtm, handBtm1, handTop, handRight, handLeft;

    @FXML
    protected AnchorPane scenePane;

    @FXML
    protected Group pileStack;

    @FXML
    protected Button deckBtn, passBtn, againBtn, setBtn, sortBtn, endBtn, returnSetBtn;

    @FXML
    protected ImageView pileIV, BG;

    //Object Player and the deck of card
    protected DeckOfCards deck;
    protected ArrayList<Player> player = new ArrayList<Player>();
    protected DiscardPile pile;
    private ArrayList<Card> tmpCardSet = new ArrayList<>();

    @FXML
    protected Label btmName, leftName, rightName, topName, notificationLabel, endGameLabel, displayScore1, displayScore2, displayScore3, displayScore4;

    private static int width;
    private static int height;

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle(gameTitle);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void createNotifications() {
        notifications.put("SameName", "Cannot use same name.");
        notifications.put("returnSet", "Set returned!!!");
        notifications.put("notreturnSet", "Nothing to returned!!!");
        notifications.put("TWOCard", "You can choose to end the game!");
        notifications.put("WON", " has won the game!");
        notifications.put("Pass", "PASS!");
        notifications.put("Skip", "SKIP!");
        notifications.put("Score", "The Score is: ");
        notifications.put("Draw2", "DRAW TWO!");
        notifications.put("CantEndGame", "OPS!YOU CANT END THE GAME!");
        notifications.put("EndGame", "WOW! YOU CAN CHOOSE TO END THE GAME!!!!");
        notifications.put("NotEnough", "Not enough cards in deck.");
        notifications.put("CantPlayDrop", "You must drop a card before proceed!! ");
        notifications.put("CantDraw", "You can't draw anymore.");
        notifications.put("EmptyDeck", "Deck is empty.");
        notifications.put("NoCreateSet", "Cannot create set!!!");
    }

    public void ShowNotification(String key, String extraFront, String extraBack) {
        if (notificationLabel.getText().contains(notifications.get(key)) && notificationLabel.getOpacity() > 0)
            return;

        notificationLabel.setText(extraFront + notifications.get(key) + extraBack);

        FadeTransition ft1 = new FadeTransition(Duration.millis(250), notificationLabel);
        FadeTransition ft2 = new FadeTransition(Duration.millis(500), notificationLabel);

        ft1.setFromValue(0);
        ft1.setToValue(1);

        ft2.setDelay(Duration.millis(500));
        ft2.setFromValue(1);
        ft2.setToValue(0);

        SequentialTransition sq = new SequentialTransition(ft1, ft2);

        sq.play();
    }

    public void createDeck() {
        deck = new DeckOfCards();
    }

    public void addToPile(Card card) throws FileNotFoundException {
        pile.addPile(card);

        Image cardImg = new Image(new FileInputStream(card.getImagePath(card)));
        ImageView cardIV = new ImageView();

        cardIV.setFitWidth(pileIV.getFitWidth());
        cardIV.setFitHeight(pileIV.getFitHeight());
        cardIV.setLayoutX(pileIV.getLayoutX());
        cardIV.setLayoutY(pileIV.getLayoutY());
        cardIV.setEffect(pileIV.getEffect());

        cardIV.setImage(cardImg);
        cardIV.setRotate(Math.random() * 40 - 20);

        pileStack.getChildren().add(cardIV);

        if (player.get(0).handCardSize() <= 2) {
            ShowNotification("TWOCard", player.get(0).getName() + ", ", "");
        }
    }

    public void addPileToDeck() throws FileNotFoundException {
        deckBtn.setOpacity(1);

        pile.resetPile(deck);

        addToPile(deck.pullRandom());

        System.out.println("Deck size: " + deck.sizeOfDeck());
    }

    public void distributeCardsToPlayers() {
        //Shuffle deck first
        deck.shuffleDeck();

        //Create players
        for (int i = 1; i <= playersAmt; i++) {
            //Distribute Cards
            player.add(new Player(("Player " + i), deck));
        }
    }

    public void update() throws FileNotFoundException {
        HBox playerHand = null;
        for (int i = 0; i < player.size(); i++) {
            switch (i) {
                case 0:
                    playerHand = handBtm;
                    playerHand.setScaleX(1.25);
                    playerHand.setScaleY(1.25);
                    btmName.setVisible(true);
                    btmName.setText(player.get(0).getName() + "\n" + "The score: " + player.get(0).getScore());
                    break;
                case 1:
                    playerHand = handRight;
                    rightName.setVisible(true);
                    rightName.setText(player.get(1).getName() + "\n" + "The score: " + player.get(1).getScore());
                    break;
                case 2:
                    playerHand = handTop;
                    topName.setVisible(true);
                    topName.setText(player.get(2).getName() + "\n" + "The score: " + player.get(2).getScore());
                    break;
                case 3:
                    playerHand = handLeft;
                    leftName.setVisible(true);
                    leftName.setText(player.get(3).getName() + "\n" + "The score: " + player.get(3).getScore());
            }

            Player currPlayer = player.get(i);

            double spacing = -60 - 3.5 * (currPlayer.handCardSize() / 12.0);

            playerHand.getChildren().clear();

            playerHand.setSpacing(spacing);

            if (i == 0) {
                playerHand.getChildren().addAll(currPlayer.displayHand().getChildren());

                setPlayCardfunction(playerHand, i);
            } else
                playerHand.getChildren().addAll(currPlayer.coverHand().getChildren());
        }
    }

    public void setPlayCardfunction(HBox p, int pIndex) throws FileNotFoundException {
        Player currPlayer = player.get(pIndex);
        boolean checkDrop = false;

        if (currPlayer.isCantPlay()) {
            for (int j = 0; j < p.getChildren().size(); j++) {
                p.getChildren().get(j).setOnMouseClicked(e ->
                {
                    if (currPlayer.getDrawAmt() > 0)
                        ShowNotification("CantPlayDraw", "", currPlayer.getDrawAmt() + " cards.");
                    else
                        ShowNotification("CantPlayPass", "", "");
                });
            }
            return;
        }

        if (currPlayer.handCardSize() > 10 || validationDraw == true) {
            ShowNotification("CantPlayDrop", "", "");
            for (int j = 0; j < p.getChildren().size(); j++) {
                int index = j;

                p.getChildren().get(j).setOnMouseClicked(e ->
                {
                    Card currCard = currPlayer.getCard().get(index);

                    try {
                        currPlayer.removeCard(currCard);
                        addToPile(currCard);
                        validationDraw = false;
                        passBtn.setDisable(false);

                        update();
                    } catch (FileNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                });
            }
        } else {
            for (int j = 0; j < p.getChildren().size(); j++) {
                int index = j;

                p.getChildren().get(j).setOnMouseClicked(e ->
                {
                    Card currCard = currPlayer.getCard().get(index);

                    try {
                        currPlayer.removeCard(currCard);
                        tmpCardSet.add(currCard);
                        update();
                    } catch (FileNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                });
            }
        }
    }

    public void createSets() {
        Player currPlayer = player.get(0);
        if (checkSetCard(tmpCardSet, currPlayer)) {
            try {
                tmpCardSet.clear();
                if(currPlayer.handCardSize() <= 2){
                    ShowNotification("EndGame", "", "");
                    endBtn.setDisable(false);
                }else{
                    endBtn.setDisable(true);
                }
                nextPlayer();
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else {
            try {
                currPlayer.addCards2(tmpCardSet);
                ShowNotification("NoCreateSet", "", " ");
                update();
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
//                        e1.printStackTrace();
            }
        }
    }

    public void returnSet(){
        Player currPlayer = player.get(0);
        if(tmpCardSet.size()!= 0) {
            try {
                currPlayer.addCards2(tmpCardSet);
                ShowNotification("returnSet", "", " ");
                update();
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
//                        e1.printStackTrace();
            }
        }else
            ShowNotification("notreturnSet", "", " ");
    }

    public boolean checkSetCard(ArrayList<Card> setTmp, Player p1) {
        if (!p1.getSetbyCard(setTmp)) {
            return false;
        }
        return true;
    }

    public void drawCard() throws FileNotFoundException {
        Player currPlayer = player.get(0);

        if (currPlayer.getDrawAmt() < 1) {
            passBtn.setDisable(false);
            ShowNotification("CantDraw", "", "");
            return;
        }

        if (deckBtn.getOpacity() < 1) {
            if (pile.pileSize() <= 1) {
                ShowNotification("EmptyDeck", "", "");
                return;
            } else
                addPileToDeck();
        }

        currPlayer.addCards(deck);

        if (currPlayer.getDrawAmt() == 1) {
            currPlayer.setDrawAmt(currPlayer.getDrawAmt() - 1);
            validationDraw = true;
            update();
        } else
            currPlayer.setDrawAmt(currPlayer.getDrawAmt() - 1);

        if (deck.sizeOfDeck() == 0)
            deckBtn.setOpacity(0.5);

        update();
    }

    public void sortUserCard() throws FileNotFoundException {
        if (counter % 2 == 0) {
            player.get(0).sortByRank(player.get(0).getCard());
            counter++;
        } else {
            player.get(0).sortBySuit(player.get(0).getCard());
            counter++;
        }
        update();
    }


    public void nextPlayer() throws FileNotFoundException {
        if (player.get(0).handCardSize() == 0) {
            won(player.get(0), player);
            return;
        }

        if (player.get(0).getDrawAmt() == 0) {
            player.get(0).setCantPlay(false);
            player.get(0).setDrawAmt(1);
        }

        passBtn.setDisable(true);

        Collections.rotate(player, playerRotation);
        update();
    }

    public void endGameAction(){
        Player currplayer = player.get(0);

        if (currplayer.handCardSize() > 2) {
            ShowNotification("CantEndGame", "", "");
        }else
            won(currplayer, player);
    }

    public void won(Player p, ArrayList<Player> p1) {
        scenePane.getChildren().clear();
        scenePane.getChildren().addAll(BG, endGameLabel);
        endGameLabel.setText(p.getName() + notifications.get("WON"));
        switch (p1.size()) {
            case 2:
                scenePane.getChildren().addAll(displayScore1, displayScore2);
                displayScore1.setText(p1.get(0).getName() + notifications.get("Score") + p.getScore() + "\n");
                displayScore2.setText(p1.get(1).getName() + notifications.get("Score") + p.getScore() + "\n");
                break;
            case 3:
                scenePane.getChildren().addAll(displayScore1, displayScore2, displayScore3);
                displayScore1.setText(p1.get(0).getName() + notifications.get("Score") + p.getScore() + "\n");
                displayScore2.setText(p1.get(1).getName() + notifications.get("Score") + p.getScore() + "\n");
                displayScore3.setText(p1.get(2).getName() + notifications.get("Score") + p.getScore() + "\n");
                break;
            case 4:
                scenePane.getChildren().addAll(displayScore1, displayScore2, displayScore3, displayScore4);
                displayScore1.setText(p1.get(0).getName() + notifications.get("Score") + p.getScore() + "\n");
                displayScore2.setText(p1.get(1).getName() + notifications.get("Score") + p1.get(1).getScore() + "\n");
                displayScore3.setText(p1.get(2).getName() + notifications.get("Score") + p1.get(2).getScore() + "\n");
                displayScore4.setText(p1.get(3).getName() + notifications.get("Score") + p1.get(3).getScore() + "\n");
                break;
        }
        scenePane.getChildren().add(againBtn);

        FadeTransition ft = new FadeTransition(Duration.millis(350), endGameLabel);

        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        againBtn.setDisable(false);
        againBtn.setVisible(true);
    }

    public void skipPlayer() {
        if (pile.pileSize() == 1)
            ShowNotification("Skip", "", " " + player.get(0).getName() + " skipped.");
        else {
            if (playerRotation < 0)
                ShowNotification("Skip", "", " " + player.get(1).getName() + " skipped.");
            else
                ShowNotification("Skip", "", " " + player.get(player.size() - 1).getName() + " skipped.");
        }

        Collections.rotate(player, playerRotation);

        System.out.println("SKIP!");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
