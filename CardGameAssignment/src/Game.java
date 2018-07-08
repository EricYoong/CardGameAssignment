import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        DeckOfCards d1 = new DeckOfCards();
        ArrayList<Player> player = new ArrayList<Player>();


        Scanner input = new Scanner(System.in);

        for(int i = 0;i<2;i++){
            Player tmp;
            player.add(tmp = new Player(input.nextLine(), d1));
        }


    }
}
