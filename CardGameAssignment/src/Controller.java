import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller extends GameUI2 {
    @FXML
    private Label playersAmtLabel;

    @FXML
    private VBox playersAmtBox, playerNames;

    @FXML
    private Group deckAndPileSet;

    @FXML
    private HBox handBtm,handBTm1, handTop, handRight, handLeft;

    @FXML
    private Button red, blue, green, yellow;

    @FXML
    private TextField pNameField;

    private int pNameIndex = 0;

    public void onPlay() throws IOException {
        AnchorPane newPane = FXMLLoader.load(getClass().getResource("PlayScene.fxml"));
        scenePane.getChildren().setAll(newPane);
    }

    public void restart() throws IOException {
        AnchorPane newPane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        scenePane.getChildren().setAll(newPane);
    }

    public void increasePlayers() {
        clickSFX();
        if (playersAmt < maxPlayers)
            playersAmt++;

        playersAmtLabel.setText(Integer.toString(playersAmt));
    }

    public void decreasePlayers() {
        clickSFX();
        if (playersAmt > minPlayers)
            playersAmt--;

        playersAmtLabel.setText(Integer.toString(playersAmt));
    }

    public void pAmtDone() {
        clickSFX();
        scenePane.getChildren().remove(playersAmtBox);
        playerNames.setVisible(true);
        playerNames.setDisable(false);

        createDeck();
        distributeCardsToPlayers();
        createNotifications();
    }

    public void pNameDone() {
        clickSFX();
        if (pNameField.getText().length() > player.get(0).getMaxNameLength())
            pNameField.setText(pNameField.getText().substring(0, player.get(0).getMaxNameLength() - 1));

        pNameField.setText(pNameField.getText().trim());

        if (pNameField.getText().length() > 0) {
            for (int i = 0; i < player.size(); i++) {
                if (pNameField.getText().equals(player.get(i).getName())) {
                    ShowNotification("SameName", "", "");
                    return;
                }
            }
            player.get(pNameIndex).setName(pNameField.getText());
        }

        pNameIndex++;

        if (pNameIndex == player.size()) {
            scenePane.getChildren().remove(playerNames);
            try {
                startGame();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return;
        }

        pNameField.setPromptText(player.get(pNameIndex).getName());
        pNameField.setText("");
    }

    public void returnSetAction(){
        clickSFX();
        returnSet();
    }

    public void startGame() throws FileNotFoundException {
        deckAndPileSet.setVisible(true);
        deckAndPileSet.setDisable(false);

        pile = new DiscardPile();
        addToPile(deck.pullRandom());
        update();

        passBtn.setVisible(true);
        sortBtn.setVisible(true);
        setBtn.setVisible(true);
        endBtn.setVisible(true);
        returnSetBtn.setVisible(true);
    }

    public void drawCard() throws FileNotFoundException {
        super.drawCard();
    }

    public void onPass() throws FileNotFoundException {
        clickSFX();
        super.nextPlayer();
    }

    public void setCreater() {
        clickSFX();
        createSets();
    }
    public void endGame(){
        clickSFX();
        endGameAction();
    }

    public void sortCard() throws FileNotFoundException {
        clickSFX();
        super.sortUserCard();

    }

    private void clickSFX() {
        String BGM = "CardGameAssignment/src/SFX/btnClick.mp3";
        Media hit = new Media(new File(BGM).toURI().toString());
        MediaPlayer mp = new MediaPlayer(hit);
        mp.play();
    }

}
