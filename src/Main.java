import java.util.InputMismatchException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        boolean x = game.askPlayerName();
        game.startGame(x);

        while (true) {
            if (game.getCurrentChapter() == 1) {
                game.chapterOne();
            } else if (game.getCurrentChapter() == 2) {
                game.chapterTwo();
            }


            //once a chapter ends
            game.endText();
            System.out.println("Would you like to proceed to the next chapter or a previous one?");
            System.out.println();
            ArrayList<String> optionList = new ArrayList<>();
            optionList.add("Next Chapter");
            optionList.add("A Previous Chapter");
            optionList.add("End Game");
            int get = game.basicGameLoop(optionList);
            switch (get) {
                case 1: {
                    game.setCurrentChapter(game.getCurrentChapter() + 1);
                    game.setCurrentAct(1, game.getCurrentChapter());
                    break;
                }

                case 2: {
                    System.out.println();
                    int chap = -1;
                    int act = -1;

                    while (true) {
                        try {
                            System.out.print("Chapter: ");
                            chap = game.getInput().nextInt();
                            game.getInput().nextLine();
                            if (chap >= 1 && chap <= 8) {
                                game.setCurrentChapter(chap);
                                break;
                            } else {
                                System.out.println("Try Again");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Try Again");
                        }
                    }
                    System.out.println();

                    // add all the other act numbers / change the current Chap 1 Act 1 number if I add more than 2.
                    while (true) {
                        try {
                            System.out.print("Act: ");
                            act = game.getInput().nextInt();
                            game.getInput().nextLine();
                            if (act >= 1 && act <= game.getTotalActs()[game.getCurrentChapter() - 1]) {
                                game.setCurrentAct(act, game.getCurrentChapter());
                                break;
                            } else {
                                System.out.println("Try Again");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Try Again");
                        }
                    }
                    game.time(3);
                    game.endText();
                    System.out.println();
                    System.out.println();
                }

                case 3: {
                    System.exit(0);
                }
            }


        }


    }


    //System.out.println("bozo");

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




