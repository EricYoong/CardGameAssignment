import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;


public class GameUI extends Application {
    //Object Player and the deck of card
    private DeckOfCards deck;
    private ArrayList<Player> player;


    @Override
    public void start(Stage primaryStage) throws Exception {
        deck = new DeckOfCards();
        player = new ArrayList<Player>();

        /*Various boolean values needed throughout the game. Objects are needed
         instead of regular booleans because regular local booleans could not be referenced
         from inside the lambda expressions used for the event listeners and game loop
        */
        boolean gameStarted = false;
        boolean gameOver = false;


        //TextField to insert player name
        TextField nameText = new TextField();


        /*Initialize the game loop*/
        Timeline gameLoop = new Timeline();

        /*Alerts that pop up at various points in the game*/
        Alert existNameWarning = new Alert(Alert.AlertType.WARNING, "That is an existing name!");
        existNameWarning.setHeaderText(null);
        Alert emptyNameWarning = new Alert(Alert.AlertType.WARNING, "The name cannot be NULL!");
        emptyNameWarning.setHeaderText(null);
        Alert wrongSetWarning = new Alert(Alert.AlertType.WARNING, "That is an incorrect set!");
        wrongSetWarning.setHeaderText(null);


        /*Declaring all buttons for the application*/
        MenuItem itemExit = new MenuItem("EXIT");
        MenuItem item2Player = new MenuItem("2 Player");
        MenuItem item3Player = new MenuItem("3 Player");
        MenuItem item4Player = new MenuItem("4 Player");
        MenuItem itemInstruction = new MenuItem("View Instructions");

        MenuBox menuBox = new MenuBox(item2Player, item3Player, item4Player, itemInstruction, itemExit);
        menuBox.setTranslateX(280);
        menuBox.setTranslateY(300);

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
        playerName.setX(265);
        playerName.setY(210);
        playerName.setFill(Color.YELLOW);
        playerName.setFont(Font.font(null, FontWeight.BOLD, 26));

        /*Panes containing buttons and labels that sit on top of Canvases in the Groups
          This is done in order to have buttons and other JavaFX controls mixed in with Canvas graphics
          being drawn underneath*/
        Pane gamePane = new Pane();
        Pane menuPane = new Pane();
        Pane instructionsPane = new Pane();

        menuPane.getChildren().add(menuBox);


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
        root.getChildren().add(gamePane);
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
        itemExit.setOnMouseClicked(e -> System.exit(0));

        item2Player.setOnMouseClicked(e -> {
            for (int i = 0; i < 2; i++) {
                boolean nameExis = false;
                boolean cancelInsert = false;
                while (nameExis == false && cancelInsert == false) {
                    TextInputDialog dialog = new TextInputDialog("walter");
                    dialog.setTitle("Text Input Dialog");
                    dialog.setHeaderText("Look, a Text Input Dialog");
                    dialog.setContentText("Please enter your name:");
                    Optional<String> result = dialog.showAndWait();

                    if (result.isPresent()) {
                        if (result.get() == null || result.get() == "" || result.get() == " ") {
                            emptyNameWarning.showAndWait();
                        } else {
                            if (nameChecking(result.get(), existNameWarning)) {
                                player.add(new Player(result.get(), deck));
                                nameExis = true;
                            }
                        }
                    } else {
                        cancelInsert = true;
                    }
                }
                if (cancelInsert == true) {
                    break;
                }
            }
        });

        item3Player.setOnMouseClicked(e -> {
            for (int i = 0; i < 3; i++) {
                boolean nameExis = false;
                boolean cancelInsert = false;
                while (nameExis == false && cancelInsert == false) {
                    TextInputDialog dialog = new TextInputDialog("walter");
                    dialog.setTitle("Text Input Dialog");
                    dialog.setHeaderText("Look, a Text Input Dialog");
                    dialog.setContentText("Please enter your name:");
                    Optional<String> result = dialog.showAndWait();

                    if (result.isPresent()) {
                        if (result.get() == null || result.get() == "" || result.get() == " ") {
                            emptyNameWarning.showAndWait();
                        } else {
                            if (nameChecking(result.get(), existNameWarning)) {
                                player.add(new Player(result.get(), deck));
                                nameExis = true;
                            }
                        }
                    } else {
                        cancelInsert = true;
                    }
                }
                if (cancelInsert == true) {
                    break;
                }
            }
        });

        item4Player.setOnMouseClicked(e -> {
            for (int i = 0; i < 4; i++) {
                boolean nameExis = false;
                boolean cancelInsert = false;
                while (nameExis == false && cancelInsert == false) {
                    TextInputDialog dialog = new TextInputDialog("walter");
                    dialog.setTitle("Text Input Dialog");
                    dialog.setHeaderText("Look, a Text Input Dialog");
                    dialog.setContentText("Please enter your name:");
                    Optional<String> result = dialog.showAndWait();

                    if (result.isPresent()) {
                        if (result.get() == null || result.get() == "" || result.get() == " ") {
                            emptyNameWarning.showAndWait();
                        } else {
                            if (nameChecking(result.get(), existNameWarning)) {
                                player.add(new Player(result.get(), deck));
                                nameExis = true;
                            }
                        }
                    } else {
                        cancelInsert = true;
                    }
                }
                if (cancelInsert == true) {
                    break;
                }
            }
        });

        gameLoop.play();
        primaryStage.show();

    }

    public boolean nameChecking(String playerN, Alert a1) {
        Alert addSuccess = new Alert(Alert.AlertType.INFORMATION);
        addSuccess.setTitle("Information Dialog");
        addSuccess.setHeaderText("Look, an Information Dialog");
        addSuccess.setContentText("Add successful!!");

        if (player.size() == 0) {
            player.add(new Player(playerN, deck));
            addSuccess.showAndWait();
            return true;
        } else {
            for (int j = 0; j < player.size(); j++) {
                if (player.get(j).checkName(playerN)) {
                    a1.showAndWait();
                    return false;
                }
            }
            addSuccess.showAndWait();
            return true;
        }
    }

    //Menu
    private static class MenuBox extends VBox {
        public MenuBox(MenuItem... items) {
            getChildren().add(createSeparator());

            for (MenuItem item : items) {
                getChildren().addAll(item, createSeparator());
            }
        }

        private Line createSeparator() {
            Line sep = new Line();
            sep.setEndX(230);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }
    }

    private static class MenuItem extends StackPane {
        public MenuItem(String name) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[]{
                    new Stop(0, Color.DARKVIOLET),
                    new Stop(0.1, Color.BLACK),
                    new Stop(0.9, Color.BLACK),
                    new Stop(1, Color.DARKVIOLET)
            });

            Rectangle bg = new Rectangle(230, 45);
            bg.setOpacity(0.4);

            Text text = new Text(name);
            text.setFill(Color.DARKGREY);
            text.setFont(Font.font("Lato", FontWeight.SEMI_BOLD, 30));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setFill(gradient);
                text.setFill(Color.WHITE);
            });


            setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.DARKGREY);
            });

            setOnMousePressed(event -> {
                bg.setFill(Color.DARKVIOLET);
            });

            setOnMouseReleased(event -> {
                bg.setFill(gradient);
            });
        }
    }

    public static class cardBox extends StackPane{
        public cardBox(){

        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}
