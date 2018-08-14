import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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

        /*Initialize the game loop*/
        Timeline gameLoop = new Timeline();

        /*Alerts that pop up at various points in the game*/
        Alert existNameWarning = new Alert(Alert.AlertType.WARNING, "That is an existing name!");
        existNameWarning.setHeaderText(null);
        Alert wrongSetWarning = new Alert(Alert.AlertType.WARNING, "That is an incorrect set!");
        wrongSetWarning.setHeaderText(null);


        /*Declaring all buttons for the application*/
        Button player2Button = new Button("2 Player");
        Button player3Button = new Button("3 PLayer");
        Button player4Button = new Button("4 PLayer");
        Button instructionsButton = new Button("View Instructions");
        Button exitButton = new Button("Exit Game");

        Button backToMenuButton = new Button("Back to Menu");
        Button toMenuFromScores = new Button("Back To Menu");
        Button createSet = new Button("Create Set");
        Button skipPlayer = new Button("Skip Player");

        Button clearButton = new Button("Play Again");
        Button exitToMenuFromGameButton = new Button("Exit To Menu");

        /*Group elements that will contain the Canvases and Panes*/
        Group root = new Group();
        Group menu = new Group();
        Group instructionsGroup = new Group();

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


        menuPane.getChildren().add(player2Button);
        menuPane.getChildren().add(player3Button);
        menuPane.getChildren().add(player4Button);
        menuPane.getChildren().add(instructionsButton);
        menuPane.getChildren().add(exitButton);




        /*Declaring the scenes that will be used and adding the Groups to them
        gameScene = main game screen
        menuScene = main menu upon startup
        instructionsScene = the game instructions screen
        saveScoreScene = the screen where user enters their name to save score to DB
        viewScoresScene = screen where user can view top 10 high scores
      */
        Scene gameScene = new Scene(root);
        Scene menuScene = new Scene(menu);
        Scene instructionsScene = new Scene(instructionsGroup);


        /*Declaring the Canvases that graphics will be drawn to*/
        Canvas canvas = new Canvas(800, 600);
        Canvas menuCanvas = new Canvas(800, 600);
        Canvas instructionsCanvas = new Canvas(800, 600);
        Canvas saveScoreCanvas = new Canvas(800, 600);
        Canvas viewScoresCanvas = new Canvas(800, 600);

         /*Add the Canvases and Panes to the groups. Canvases must be added first
      for each Group or else the Canvas will cover the Pane*/
        root.getChildren().add(canvas);
        root.getChildren().add(pane);
        menu.getChildren().add(menuCanvas);
        menu.getChildren().add(menuPane);
        instructionsGroup.getChildren().add(instructionsCanvas);
        instructionsGroup.getChildren().add(instructionsPane);

        /*GraphicsContext objects that get attached to the Canvases in order to
      be able to draw graphics. One for each Scene*/
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext mgc = menuCanvas.getGraphicsContext2D();
        GraphicsContext igc = instructionsCanvas.getGraphicsContext2D();

        /*Set the game loop to go on indefinitely*/
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        /*Set the first scene to be the main menu*/
        primaryStage.setScene(menuScene);


        /*===BUTTON EVENT LISTENERS ===*/
        player2Button.setOnAction(e -> {
            primaryStage.setScene(gameScene);
            primaryStage.setHeight(625);
            primaryStage.setWidth(804);

        });

        player3Button.setOnAction(e -> {
            primaryStage.setScene(gameScene);
            primaryStage.setHeight(625);
            primaryStage.setWidth(804);
            resetGame(gc, previousX, player, dealer, gameOver, dealersTurn, betFinished);
        });

        player4Button.setOnAction(e -> {
            primaryStage.setScene(gameScene);
            primaryStage.setHeight(625);
            primaryStage.setWidth(804);
            resetGame(gc, previousX, player, dealer, gameOver, dealersTurn, betFinished);
        });

        instructionsButton.setOnAction(e -> primaryStage.setScene(instructionsScene));

        exitButton.setOnAction(e -> {
            Platform.exit();
        });

        backToMenuButton.setOnAction(e -> primaryStage.setScene(menuScene));

        exitToMenuFromGameButton.setOnAction(e -> {
            primaryStage.setScene(menuScene);
        });


        gameLoop.getKeyFrames().add(frame);
        gameLoop.play();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
