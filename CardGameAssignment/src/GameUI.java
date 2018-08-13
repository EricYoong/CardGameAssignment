import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class GameUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        /*Various boolean values needed throughout the game. Objects are needed
         instead of regular booleans because regular local booleans could not be referenced
         from inside the lambda expressions used for the event listeners and game loop
        */
        boolean gameStarted = false;
        boolean gameOver = false;

        //TextField to insert player name
        TextField nameText = new TextField();

        //Object Player and the deck of card
        DeckOfCards deck = new DeckOfCards();
        Player player;

        /*Alerts that pop up at various points in the game*/
        Alert existNameWarning = new Alert(Alert.AlertType.WARNING, "That is an existing name!");
        existNameWarning.setHeaderText(null);
        Alert wrongSetWarning = new Alert(Alert.AlertType.WARNING, "That is an incorrect set!");
        wrongSetWarning.setHeaderText(null);


        /*Declaring all buttons for the application*/
        Button startGameButton = new Button("Start Game");
        Button instructionsButton = new Button("View Instructions");
        Button viewScoresButton = new Button("View High Score List");
        Button exitButton = new Button("Exit Game");

        Button backToMenuButton = new Button("Back to Menu");
        Button toMenuFromScores = new Button("Back To Menu");
        Button createSet = new Button("Create Set");
        Button skipPlayer = new Button("Skip Player");


        Button clearButton = new Button("Play Again");
        Button exitToMenuFromGameButton = new Button("Exit To Menu");

        /*Text displays for displaying various game data,
        and their X and Y and styles*/
        Text playerScore = new Text();
        playerScore.setX(265);
        playerScore.setY(210);
        playerScore.setFill(Color.YELLOW);
        playerScore.setFont(Font.font(null, FontWeight.BOLD, 26));

        Text playerName = new Text();
        playerScore.setX(265);
        playerScore.setY(210);
        playerScore.setFill(Color.YELLOW);
        playerScore.setFont(Font.font(null, FontWeight.BOLD, 26));

        /*Panes containing buttons and labels that sit on top of Canvases in the Groups
          This is done in order to have buttons and other JavaFX controls mixed in with Canvas graphics
          being drawn underneath*/
        Pane pane = new Pane();
        Pane menuPane = new Pane();
        Pane instructionsPane = new Pane();





































        Scene scene = new Scene();

    }
}
