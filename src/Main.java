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








        Map testMap = new Map(1, 1);
        Board test = new Board("D", testMap, "Test");
        test.setBoardFromString("/ / / / / / / / / / " +
                "/ - - - - - - f - - " +
                "/ - - f - - - - f - " +
                "/ f - - - - f - - - " +
                "/ / / - f - - - - - " +
                "- - [ - - f - - - - " +
                "/ / / - - - - - - - " +
                "/ - - - f - f B f - " +
                "/ - - f - - - f - - " +
                "/ / / / / / / / / / ");


        ArrayList<Nodeable> fillList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            WindyTree f = new WindyTree("f", "Cactus");
            fillList.add(f);
        }
        Item but = new Item("BUttters", "you're sixteen you're beautiful and you're mine");
        ToriObject b = new ToriObject("B", "butters", but, 7,7);
        fillList.add(b);

        for (int i = 0; i < 3; i++) {
            WindyTree f = new WindyTree("f", "Cactus");
            fillList.add(f);
        }

        test.fillPlaceholders(fillList);
        test.printBoard();
        */
    }
}



