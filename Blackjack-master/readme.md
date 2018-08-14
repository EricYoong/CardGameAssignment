## Welcome to Blackjack
### Made By Silviu Popovici

### Blackjack rules and running the game
Blackjack is a classic card game where the point is to obtain a hand worth 21 or less without going over 21, 
and to beat the dealer who is also trying to do the same in order to win money. Number cards are worth their 
numeric value. Kings,Queens,and Jacks are worth 10 and Aces are worth 1 or 11 depending on which makes a better 
hand. You draw one card at a time and can "stay" when you're happy with your hand, then the dealer takes their 
turn. In this version there is only one deck and no splitting your hand or doubling down, and the dealer always 
hits on 17. More detailed game rules and instructions are available in-game via the "View Instructions" button 
in the main menu.

To run this game, you need at least 128MB of free memory but 256MB or more is recommended. You can run this 
game by simply downloading the BlackjackGame ZIP file from this repo, unzipping, and running the Blackjack.jar executable. It should run on Windows, Mac, or Linux as long as 
you have Java 8 or higher installed on your computer AND your Java 8 installation contains JavaFX 8 or higher. 
(Some JDK implementations like OpenJDK for Linux don't come with JavaFX by default and it has to be downloaded seperately)

Alternatively, you can compile the files yourself by downloading this repo and opening the main class 
(Blackjack.java) in an IDE, then compiling and running. Ensure that you have the latest SQLite JDBC library in your Java classpath when running.

### Development info and game logic
This game is written in Java with the JavaFX framework and also uses an embedded SQLite database to keep track 
of high scores. It is made up of multiple scenes, including the main menu scene, the main game scene, and the 
high scores scene. These scenes are contained within the main Stage, which in JavaFX is the main window of the 
application. The Stage switches between these scenes when certain buttons are pressed. 

For the main game, the player and the dealer are both represented by the Player class, which defines behavior 
such as what cards that person has in their hand, what the numeric value of their current hand is, and the 
player's money and bet amount. The cards themselves are represented by the Card class which defines the actual 
Image graphic of the card as well as its value and suit. Then there is a Deck class that initializes a deck of 
52 Card objects that represent a standard deck. The Deck class also defines behaviors such as drawing a card or 
shuffling the deck.

The main class is Blackjack.java. This class defines all of the UI elements for the game such as buttons and 
labels as well as all variables needed for the game loop logic. This class makes heavy use of Java 8 lambda 
expressions for event listeners to control user input. The main game loop runs once every 50 milliseconds, 
which means 20 frames per second. Since the game doesn't contain many animations it doesn't need higher FPS so 
running lower FPS saves some processing power.

The game also connects to an embedded SQLite database using the SQLite JDBC library. It uses this database to 
allow the user to save their score and also to retrieve the top 10 high scores.

If you have any questions about this project feel free to email me at Silviudev1@gmail.com. 

Check out my site at https://silviudev.github.io/ for more info on me and my projects! 
