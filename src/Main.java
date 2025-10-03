import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        Game game = new Game();

        boolean x = game.askPlayerName();
        game.startGame(x);

        if (game.getCurrentChapter() == 99) {
            game.toriGame();
        }
        if (game.getCurrentChapter() == 1) {
            game.chapterOne();
        }


        System.out.println("bozo");

        //LAST ACT ->d fix

        /*
        Board boardOne = new Board("Dalton's Room");

        Bed daltonBed = new Bed();
        Player playerOne = new Player("1", "Player One", daltonBed);
        Desk daltonDesk = new Desk();
        Kelly kellyCat = new Kelly();
        boardOne.buildRectRoom(3, 3, 8, 3, 8, 7, 3, 7, 3, 6, RoomSides.WEST);
        boardOne.addNode(7, 4, playerOne);
        boardOne.addNode(6, 4, daltonDesk);
        boardOne.addNode(7, 6, kellyCat);

        //boardOne.setAllFloor();

        boardOne.printLegend();
        boardOne.printBoard();

        prompt.displayActions(boardOne, playerOne);
        */

        /*w
         * the game loop:
         * print board / legend
         *
         * ask them what they want to do
         *  get what they want to do
         *
         *
         * do the thing they asked
         *
         * repeat
         *
         */


    }
}



