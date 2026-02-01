import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.InputMismatchException;

public class Game {
    //keep track of player
    private String playerName;
    private Player currentPlayer;

    //objects needed to do work
    static private final Scanner input = new Scanner(System.in);;
    private final Prompt prompt;

    //keep track of chapter/acts
    private int currentChapter;
    private int latestChapter;
    private int[] currentAct;
    private int[] latestActs;
    private final int[] totalActs;
    private Board currentBoard;
    private boolean intendedRoute;

    //all the different maps so they can be held outside the scope of the act methods
    static Map NYC = new Map(7,5);
    static Map daltonApartmentComplex = new Map(5, 5);
    static Map nelsonLabs = new Map(4, 7);
    static Map currentMap;

    //convenience.
    private int timeNumber;
    private Hobo hobo;

    //constructor
    public Game() {

        playerName = "";

        prompt = new Prompt();
        currentChapter = 0;
        latestChapter = 0;
        currentAct = new int[8];
        latestActs = new int[8];
        totalActs = new int[]{3, 0, 0, 0, 0, 0, 0, 0};
        timeNumber = 0;

    }


    //getter for the user input if needed in prompt.
    public Scanner getInput() {
        return input;
    }

    //getter and setters for playerName
    public void setPlayerName(String newName) {
        playerName = newName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayer(Player player) {
        currentPlayer = player;
    }

    public Player getPlayer() {
        return currentPlayer;
    }

    public void setBoard(Board board) {
        currentBoard = board;
    }

    public Board getBoard() {
        return currentBoard;
    }

    public void setCurrentMap(Map map) {
        currentMap = map;
    }

    public Map getCurrentMap() {
        return currentMap;
    }
    //getters for chapters and acts
    public int getCurrentChapter() {
        return currentChapter;
    }

    public int[] getCurrentAct() {
        return currentAct;
    }

    public int getLatestChapter() {
        return latestChapter;
    }

    public int[] getLatestAct() {
        return latestActs;
    }

    public int[] getTotalActs() {
        return totalActs;
    }

    //setters for chapters and acts
    public void setCurrentChapter(int newChapter) {
        currentChapter = newChapter;
    }

    public void setCurrentAct(int newAct, int chapter) {
        currentAct[chapter - 1] = newAct;
    }

    //some random convenient methods to make things easier
    public void setTimeNumber(int newTimeNumber) {
        timeNumber = newTimeNumber;
    }

    static public void endText() {
        System.out.println();
        System.out.println();
    }

    public int[] startFromSpecificAct() {
        int[] x = {currentChapter, currentAct[currentChapter-1]};
        while (true) {
            System.out.println();
            System.out.print("Enter your desired Chapter number:");
            try {
                System.out.println();
                int desiredChapter = input.nextInt();
                input.nextLine();
                System.out.println();
                if (desiredChapter >= 1 && desiredChapter <= latestChapter) {
                    currentChapter = desiredChapter;
                    break;
                } else {
                    System.out.println("That is not a valid Chapter number, please try again.");
                }
            } catch (Exception InputMismatchException) {
                System.out.println("Please enter a valid integer.");
            }
        }
        while (true) {
            System.out.println();
            System.out.println("Enter your desired Act number:");
            try {
                System.out.println();
                int desiredAct = input.nextInt();
                input.nextLine();
                System.out.println();
                if (desiredAct >= 1 && desiredAct <= latestActs[currentChapter - 1]) {
                    currentAct[currentChapter - 1] = desiredAct;
                    break;
                } else {
                    System.out.println("That is not a valid Act number, please try again.");
                }
            } catch (Exception InputMismatchException) {
                System.out.println("Please enter a valid integer.");
            }
        }
        intendedRoute = false;
        return x;
    }

    static public void time(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    //the beginning and end of game methods
    public boolean[] gameOver(String deathText, boolean inBattle, Ending ending) {
        boolean[] returnArray = {false, true};
        System.out.println(deathText);
        this.time(3);
        System.out.println();
        System.out.println("...");
        System.out.println();
        System.out.println("GAME OVER");
        System.out.println();
        time(2);
        if (ending == Ending.BAD_ENDING) System.out.println("BAD ENDING");
        if (ending == Ending.GOOD_ENDING) System.out.println("GOOD ENDING");
        if (ending == Ending.SPECIAL_ENDING) System.out.println("SPECIAL ENDING");
        if (ending == Ending.TRUE_ENDING) System.out.println("TRUE ENDING");
        this.time(2);

        System.out.println();
        System.out.println("Would you like to restart from the last act or end the game?");
        System.out.println();

        ArrayList<String> optionList = new ArrayList<String>();
        optionList.add("Respawn From Last Act");
        optionList.add("Respawn From Specific Act");
        optionList.add("End Game");
        if (inBattle) optionList.add("Restart Battle");

        int  response = this.basicGameLoop(optionList);
        if (response == 1) {
            return returnArray;
        }
        if (response == 2) {
            int[] arr = startFromSpecificAct();
            if (currentChapter == arr[0] && currentAct[currentChapter - 1] == arr[1]) {
                intendedRoute = true;
            }
            return returnArray;
        }
        if (inBattle && response == 4) {
            returnArray[1] = false;
            return returnArray;
        }
        if (response == 3) {
            System.exit(0);
        }
        currentPlayer.getInventory().saveInventory();
        returnArray[1] = false;
        return returnArray;
    }

    public boolean askPlayerName() {
        this.endText();
        System.out.println("Welcome to S.S. Origins");
        System.out.println();
        System.out.println("Please enter your name:");
        String get = input.nextLine();
        this.setPlayerName(get);
        System.out.println();
        if (get.equals("Tori")) System.out.println("Hi hi my love <3");
        else {
            System.out.println("Hello, " + playerName);
        }
        if (get.equals("fast")) {
            timeNumber = 200;
            return false;
        }
        if (get.equals("Tori")) {
            return true;
        }
        this.endText();
        return false;
    }

    private void resetPlayer() {
        currentBoard.addNode(currentBoard.getCharPosX(), currentBoard.getCharPosY(), currentPlayer.getPreviousNode());
    }

    public void startGame(boolean x) {
        System.out.println("Enter the word 'begin' below to start the game.");
        String get = "";
        while (true) {
            get = input.nextLine();
            System.out.println();
            if (get.equals("begin") || get.equals("What if Dalton was a superhero?")) {
                break;
            }
            System.out.println("Sorry, that is incorrect. Please try again.");
            System.out.println();
        }
        if (get.equals("begin")) {
            System.out.println("Choose your desired Chapter and Act below: ");
            System.out.println();
            int chap = -1;
            int act = -1;

            while (true) {
                try {
                    System.out.print("Chapter: ");
                    chap = input.nextInt();
                    input.nextLine();
                    if (chap >= 1 && chap <= 8) {
                        currentChapter = chap;
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
                    act = input.nextInt();
                    input.nextLine();
                    if (act >= 1 && act <= totalActs[currentChapter - 1]) {
                        currentAct[currentChapter - 1] = act;
                        break;
                    } else {
                        System.out.println("Try Again");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Try Again");
                }
            }

        }
        time(3);
        endText();
        System.out.println();
        System.out.println();
        time(2);
        Floor floor = new Floor();
        Player player = new Player("P", "Placeholder Name", floor);
        this.setPlayer(player);
    }

    //the game loops
    public String basicGameLoop(String battleName, ArrayList<String> optionList) {
        if (!battleName.isEmpty()) {
            System.out.println(battleName);
            System.out.println();
            time(2);
            System.out.println("-FIGHT-");
            System.out.println();
            time(2);
        }

        for (int i = 0; i < optionList.size(); i++) {
            if (!optionList.get(i).isEmpty()) {
                System.out.println("- " + optionList.get(i));
            }
        }
        System.out.println();

        String userInput = "";
        while (true) {
            userInput = input.nextLine();
            System.out.println();
            if (optionList.contains(userInput)) break;
            else {
                System.out.println("I don't know that one, try again.");
                System.out.println();
            }
        }
        return userInput;
    }

    static public int basicGameLoop(ArrayList<String> optionList) {

        for (int i = 0; i < optionList.size(); i++) {
            if (!optionList.get(i).isEmpty()) {
                int j = i+1;
                System.out.println(" " + j + " " + optionList.get(i));
            }
        }
        System.out.println();

        int userInput;
        while (true) {
            try {
                userInput = input.nextInt();
                input.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println("Be sure to enter in a number, not words.");
                continue;
            }
            System.out.println();
            if (userInput >= 0 && userInput <= optionList.size()) {
                return userInput;
            }
            else {
                System.out.println("I don't know that one, try again.");
                System.out.println();
            }
        }

    }

    public boolean advancedGameLoop(Player player, Map endMap, int messageCounter, String message, int endBoardX, int endBoardY, int endX, int endY, int battleX, int battleY) {
        Board thisBoard = currentMap.getCurrentBoard();
        String get = "";
        boolean[] boolArray = {true, false};
        thisBoard.printBoard();
        boolean canPrintBoard = true;
        int counter = 0;
        int boardCounter;
        // false false -> battle again
        // true false -> good, continue
        // false true -> retart from last act
        while (true) {
            Board board = currentMap.getCurrentBoard();
            if (!(battleX==-1 && battleY==-1)) {
                if ((board.getCharPosX() == battleX && board.getCharPosY() == battleY) || (battleX == -1 && board.getCharPosY() == battleY) || (battleY == 1 && board.getCharPosX() == battleX)) {
                    boolArray = prompt.displayBattleActions(board, player, this);
                    if (boolArray[1]) {
                        return false;
                    }
                    return true;
                }
            }
            if (counter == messageCounter) {
                System.out.println(message);
                System.out.println();
            }
            prompt.displayActions(currentMap, player, this);
            System.out.println();

            get = input.nextLine();
            System.out.println();
            boardCounter = currentMap.getBoardChangeCounter();
            boolArray = prompt.doAction(get, currentMap, player, this);
            if (!boolArray[0]) {
                if (boolArray[1]) {
                    return false;
                }
                canPrintBoard = false;

                if (get.length() > 4) {
                    System.out.println(get.startsWith("Move") ? "You cannot move to that spot! Try again." : "I don't know that one right now, try again.");
                } else {
                    System.out.println("I don't know that one right now, try again.");
                }
                System.out.println();

            }

            if (currentMap == endMap){
                if (currentMap.getCurrentBoardX() == endBoardX && currentMap.getCurrentBoardY() == endBoardY) {
                    if ((board.getCharPosX() == endX && board.getCharPosY() == endY) || (endX == -1 && board.getCharPosY() == endY) || (endY == -1 && board.getCharPosX() == endX)) {
                        return true;
                    }
                }
            }
            if (canPrintBoard) {
                board = currentMap.getCurrentBoard();
                board.printBoard();
                if (currentMap.getBoardChangeCounter() != boardCounter) {
                    System.out.println();
                    System.out.println("Entering... " + board.getBoardName());
                    System.out.println();

                }
            }
            canPrintBoard = true;
            counter++;
        }
        //return true;
    }

    public boolean advancedGameLoop(Player player, Map endMap, int endBoardX, int endBoardY, int endX, int endY, int battleX, int battleY) {
        Board thisBoard = currentMap.getCurrentBoard();
        String get = "";
        boolean[] boolArray = {true, false};
        thisBoard.printBoard();
        boolean canPrintBoard = true;
        int counter = 0;
        int boardCounter;
        // false false -> battle again
        // true false -> good, continue
        // false true -> retart from last act
        // true true -> end loop but continue story
        while (true) {
            Board board = currentMap.getCurrentBoard();
            if ((board.getCharPosX() == battleX && board.getCharPosY() == battleY) || (battleX == -1 && board.getCharPosY() == battleY) || (battleY == 1 && board.getCharPosX() == battleX)) {
                boolArray = prompt.displayBattleActions(board, player, this);
                if (boolArray[1]) {
                    return false;
                }
                return true;
            }
            prompt.displayActions(currentMap, player, this);
            System.out.println();

            get = input.nextLine();
            System.out.println();
            boardCounter = currentMap.getBoardChangeCounter();
            boolArray = prompt.doAction(get, currentMap, player, this);
            if (!boolArray[0]) {
                if (boolArray[1]) {
                    return false;
                }
                canPrintBoard = false;

                if (get.length() > 4) {
                    if (get.startsWith("Move")) {
                        System.out.println("You cannot move to that spot! Try again.");
                    } else {
                        System.out.println("I don't know that one right now, try again.");
                    }
                } else {
                    System.out.println("I don't know that one right now, try again.");
                }
                System.out.println();

            }
            if (boolArray[1]) {
                return true;
            }


            if (currentMap == endMap){
                if (currentMap.getCurrentBoardX() == endBoardX && currentMap.getCurrentBoardY() == endBoardY) {
                    if ((board.getCharPosX() == endX && board.getCharPosY() == endY) || (endX == -1 && board.getCharPosY() == endY) || (endY == -1 && board.getCharPosX() == endX)) {
                        break;
                    }
                }
            }
            if (canPrintBoard) {
                board = currentMap.getCurrentBoard();
                board.printBoard();
                board.stepBoard(this);
                if (currentMap.getBoardChangeCounter() != boardCounter) {
                    System.out.println();
                    System.out.println("Entering... " + board.getBoardName());
                    System.out.println();
                }
            }
            canPrintBoard = true;
            counter++;
        }
        return true;


    }

    //Chapter 1 acts.
    public boolean chapOneActOne(boolean firstTime) {
        currentMap = daltonApartmentComplex;
        Board easyAccessR = daltonApartmentComplex.getMapBoard().get(5).get(5);
        setBoard(easyAccessR);
        currentMap.setCurrentBoardX(5);
        currentMap.setCurrentBoardY(5);
        easyAccessR.addNode(7, 4, getPlayer());
        Board emptyBoard = new Board("E", daltonApartmentComplex, "Empty");



        //create the daltonApartmentComplex map in the constructor so we can put things everywhere.

        System.out.println();
        System.out.println("Act 1: Dalton's Apartment");
        endText();
        time(3);

        if (firstTime) {
            System.out.println("You are Dalton Young.");
            System.out.println();
            time(4);

            System.out.print("Billionaire. ");
            time(2);
            System.out.print("Playboy. ");
            time(2);
            System.out.println("World Famous.");
            System.out.println();
            this.time(3);

            System.out.println("You had just finished filming your latest Hype movie and are taking a relaxing retreat to the Bahamas. There, one of your servants reminds you of your 3:00 with Post Malone, to which you begin to hum the melody of 'Texas Tea.'");
            this.time(10);
            System.out.println();

            System.out.println("But the humming did not sound like hit artist Post Malone's 'Texas Tea.' Instead it sounded sort of like a BEEP! BEEP! BEEP!");
            this.time(7);
            System.out.println();

            emptyBoard.printBoard();
            this.time(2);
            System.out.println("Game Tip: When you see words following the '-' symbol, it means it is an ACTION. Type in the following action word for word to do it.");
            System.out.println();
            System.out.println("- Wake Up");
            System.out.println();
            String firstGet = "";
            while (true) {
                firstGet = input.nextLine();
                if (firstGet.equals("Wake Up")) {
                    break;
                } else {
                    System.out.println("Game Tip: Not quite, you need to type each action *word for word* and without the '-', try again.");
                }
            }
            System.out.println();
            System.out.println("...");
            System.out.println();
            this.time(3);
            System.out.println("You groggily fish around and shut off the timer on the phone.");
            this.time(4);
            System.out.println();
            System.out.println("You are Dalton Young.");
            this.time(4);
            System.out.println();


            System.out.print("Broke. ");
            time(2);
            System.out.print("Single. ");
            time(2);
            System.out.println("Known by Nobody Except Your Few Hundred Fans of Your Creative Maps.");
            System.out.println();
            this.time(3);

            ArrayList<String> optionList = new ArrayList<>();
            optionList.add("Wake Up");
            basicGameLoop("", optionList);
            System.out.println();

            //build dalton room


            //build dalton hall


            //print dalton room
            easyAccessR.printLegend();
            easyAccessR.printBoard();


            this.time(4);
            System.out.println("Instead of a beachy resort, you find yourself in a dimly lit room. As you try to remember who you are and why you are here, you have an urge to go back into the covers and sleep a while.");
            this.time(6);
            System.out.println();
            System.out.println("But no, you need to get up out of bed. Clearly you are NOT a morning person but this game has to start somehow.");
            this.time(4);
            System.out.println();
            optionList.clear();
            optionList.add("Get Up");
            basicGameLoop("", optionList);
            getPlayer().moveSouth(easyAccessR, this);
            System.out.println();
            System.out.println("...");
            this.time(3);
            System.out.println();
            easyAccessR.printLegend();
            easyAccessR.printBoard();

            System.out.println("You look around your musty, dusty, crusty one room apartment and see your bed, desk, and your faithful cat Kelly. Looking at your desk you remember you were just in the middle of making the latest hype update to Hype 2.");
            System.out.println();
            this.time(10);
            System.out.println("Ever since the success of Hype 1 you had moved out to live on your own. But unfortunately UEFN is dominated by basic Red V Blues and other brainrot maps, so it seems you need to get a job to pay the rent.");
            System.out.println();
            this.time(10);
        }
        getPlayer().moveSouth(easyAccessR, this);



        System.out.println("You turn to go back into bed but then you remember why you set your alarm to wake you so early. Today was the big Tech Fair at Nelson's Labs and they had open job positions that you wanted to check out.");
        System.out.println();
        this.time(10);
        System.out.println("You should head there now.");
        this.time(3);

        //the game loop begins.

        String message = "Game Tip: You always have access to the 'Help' action. This can tell you the universal actions you always have at your disposal.";
        String message2 = "I don't have all day and neither do you. You should probably get going to the Tech Fair!";
        if (!advancedGameLoop(getPlayer(), daltonApartmentComplex, 0, message, 5, 5, 6, 5, -1, -1)) return true;
        if (!advancedGameLoop(currentPlayer, daltonApartmentComplex, 9, message2, 5, 4, 5, 2, -1, -1)) return true;
        //5, 2, player






        //-----------------------board change------------------------





        currentPlayer.setHobo(hobo);
        currentBoard.getBoard().get(2).get(6).performAction("Close Door", currentBoard, this);
        currentBoard.getBoard().get(2).get(6).performAction("Lock Door", currentBoard, this);
        Board easyAccessH = daltonApartmentComplex.getMapBoard().get(4).get(5);
        easyAccessH.printLegend();
        easyAccessH.printBoard();

        System.out.println();
        System.out.println("...");
        System.out.println();

        time(3);

        System.out.println("You exit your room into your apartment complex's hallway, locking the door behind you.");
        System.out.println();
        time(3);

        System.out.println("You check your pockets to see if you have any change for breakfast, but you come up empty handed.");
        System.out.println();
        time(4);

        System.out.println("You look up to see a hobo wandering aimlessly in the halls. He had on a purple and black uniform with a name tag reading 'J. Young.'");
        System.out.println();
        System.out.println();
        time(6);

        System.out.println("As you walk closer he seems to be mimicking your movements.");
        System.out.println();
        time(4);

        System.out.println("Regardless, the exit to your apartment building is at the end of the hall.");
        System.out.println();
        time(3);

        System.out.println("Go there now.");

        if (!advancedGameLoop(currentPlayer,daltonApartmentComplex, -1, "", 5, 4, 2, 7, -1, hobo.currentY - 1)) return true;
        currentPlayer.setHobo(null);
        currentBoard.getBoard().get(2).get(6).performAction("Unlock Door", currentBoard, this);
        if (!advancedGameLoop(currentPlayer, NYC, -1, "", 7, 5, 6, 5, -1, -1)) return true;
        //finish the battle stuff

        currentAct[currentChapter - 1] = 2;
        currentPlayer.getInventory().saveInventory();
        endText();
        return true;
    }

    public void chapOneActTwo(boolean firstTime) {

        System.out.println();
        System.out.println("Act 2: Dalton's Trip");
        endText();
        time(3);

        if (firstTime) {
            System.out.println("As you leave the apartment complex doors the sun shines in your eyes as you are greeted by the noisy traffic of New York City.");
            System.out.println();
            time(5);

            System.out.println("New York City, once a historical, major port of the United States, is now one of the world leaders of cutting edge technology.");
            System.out.println();
            time(5);

            System.out.println("With skyscrapers taller than the cloudline and automated machines left and right, New York City is the go to spot for innovators of all types.");
            System.out.println();
            time(5);

            System.out.println();
            System.out.println("You walk past a manhole cover with steam bellowing out of it. It was a common sight as all manhole covers throughout the city do so.");
            System.out.println();
            time(5);

            System.out.println("There are many speculations why, but the city always chalks it up to the heating system of the city.");
            System.out.println();
            time(4);

            System.out.println("You, however, have no time to dwell on that now. You need to get to the Tech Fair at Nelson's Labs.");
            System.out.println();
            time(4);
        }
        else {
            System.out.println("You need to go to the Tech Fair at Nelson's Labs.");
            System.out.println();
            time(3);
        }

        System.out.println("It is to the West. Go there now.");
        System.out.println();
        time(3);

        if (!advancedGameLoop(getPlayer(), nelsonLabs, 70, "Don't forget the Fair won't last all day!", 4, 6, -1, 7, -1, -1)) {
            return;
        }

        currentAct[currentChapter - 1] = 3;
        latestActs[latestChapter - 1] = 2;
        currentPlayer.getInventory().saveInventory();
        endText();
    }

    public void chapOneActThree() {
        System.out.println();
        System.out.println("Act 3: The Tech Fair");
        endText();
        time(3);

        //timeNumber = 67;

        print("You did it! You actually did it! You made it to the Tech Fair without getting distracted or dying! I'm so proud of you!", 5);
        print("You walk through the double doors to find a massive room filled with people and scientists in lab coats.", 4);
        print("You see a total of 10 scientists you need to listen to and get tickets from to get your free Cane's.", 4);
        System.out.println();
        print("In the back right, you also see the hiring manager. Don't forget to go to him because that is why we are here.", 5);
        print("Alright, get going then.",3);


        if (!advancedGameLoop(currentPlayer, nelsonLabs, 1, 1, 1, 1, -1, -1)) {
            return;
        }

        currentChapter = 2;
        currentPlayer.getInventory().saveInventory();
        endText();
    }

    //chapter 1 method
    public void chapterOne() {
        currentPlayer.resetPlayer("D", "Dalton Young", "bed");
        System.out.println("Chapter 1: Dalton's Beginning");
        time(3);
        System.out.println();
        boolean completedChapter1 = false;
        boolean abnormalStartRan = false;

        boolean diedInA2 = false;
        boolean diedInA3 = false;

        boolean A1S = true;
        boolean A2S = true;

        //you need to make the Dalton apartment early too.

        while (currentChapter == 1 && currentAct[currentChapter - 1] == 1) {
            currentPlayer.resetPlayer("D", "Dalton Young", "bed");
            buildDaltonApartmentComplexMap();
            buildNYCMap(1);
            completedChapter1 = chapOneActOne(A1S);
            A1S = false;
        }
        if (completedChapter1) {
            intendedRoute = true;
        }
        while (currentChapter == 1 && currentAct[currentChapter - 1] == 2) {
            buildNelsonLabsMap(1);
            if (!intendedRoute || diedInA2) {
                currentPlayer.resetPlayer("D", "Dalton Young", "floor");
                buildNYCMap(1);
                currentMap = NYC;
                NYC.setCurrentBoardX(7);
                NYC.setCurrentBoardY(5);
                currentBoard = NYC.getMapBoard().get(5).get(7);
                NYC.getMapBoard().get(5).get(7).addNode(6, 5, getPlayer());
                if (!completedChapter1){
                    currentBoard.getBoard().get(5).get(7).performAction("Close Door", currentBoard, this);
                    currentBoard.getBoard().get(5).get(7).performAction("Lock Door", currentBoard, this);
                }
                abnormalStartRan = true;
            }
            chapOneActTwo(A2S);
            diedInA2 = true;
            A2S = false;
        }
        if (abnormalStartRan) {
            abnormalStartRan = false;
            intendedRoute = true;
        }
        while (currentChapter == 1 && currentAct[currentChapter - 1] == 3) {
            if (!intendedRoute || diedInA3) {
                currentPlayer.resetPlayer("D", "Dalton Young", "floor");
                //System.out.println("entered abnormal start");
                buildNelsonLabsMap(1);
                buildNYCMap(1);
                currentMap = nelsonLabs;
                currentMap.setCurrentBoardY(6);
                currentBoard = nelsonLabs.getMapBoard().get(6).get(4);
                nelsonLabs.getMapBoard().get(6).get(4).addNode(5, 9, getPlayer());
                if (!completedChapter1){
                    NYC.getMapBoard().get(5).get(7).getBoard().get(5).get(7).performAction("Close Door", currentBoard, this);
                    NYC.getMapBoard().get(5).get(7).getBoard().get(5).get(7).performAction("Lock Door", currentBoard, this);
                }
                abnormalStartRan = true;
            }
            chapOneActThree();
            diedInA3 = true;
        }
        if (abnormalStartRan) {
            abnormalStartRan = false;
            intendedRoute = true;
        }

    }

    public void chapTwoActOne() {

        System.out.println();
        System.out.println("Act 2: Riley's Awakening");
        endText();
        time(3);

        print("You are Riley Nelson", 3);
        print("Your eyes open as you hear the sound of the bell attached to the door.", 5);
        print("Another customer walks in.", 3);
        System.out.println();
        print("They hear the sizzling of patties on the grill and smell the sharp smell of cheddar.", 6);
        print("It was just another day at McRiley's.", 4);
        print("You turn to Antonio but before you can say a thing he begins screaming at you.", 6);
        System.out.println();
        print("BEEP BEEP BEEP", 3);

        ArrayList<String> optionList = new ArrayList<>();
        optionList.add("Wake Up");
        basicGameLoop("", optionList);
        System.out.println();
        print("...", 3);

        print("You wake up and checked your alarm clock.", 5);
        print("SIX O'CLOCK?!?!", 3);
        print("Just as you were about to fall back into your pillows and sleep until noon, you remember that today was the big Tech Fair at Nelson Labs.", 10);
        System.out.println();
        print("You recalled to last night where you spent hours finishing up your greatest invention: the Golden Electricity Generator.", 8);
        print("You couldn't wait to show it off to the world, you knew it had the capability to provide clean, renewable energy for the whole city of New York!", 10);
        print("However, that would have to wait as the grumbly in your tummy told you to get some breakfast.", 7);
        System.out.println();
        print("Head to the common room for some food, it is the room on the map directly below the room you are in.", 7);
        //do the advanced game loop until act 2.
        //do that by having the jerry fight end with Riley standing where Jerry's spot was so that an end spot for the advanced gameloop can be set (playermovement is covered by the jerry fight it seems)
        //have the package be a node sitting on a table that inputs an item in the inventory and then replaces itself it a table.
        //cover the initial vial explanations in the node itself then explain more in the act 2 instructions

        if (!advancedGameLoop(getPlayer(), nelsonLabs, 10000, "!!!", 3, 2, 5, 2, -1, -1)) {
            return;
        }

        currentAct[currentChapter - 1] = 2;
        latestActs[latestChapter - 1] = 1;
        currentPlayer.getInventory().saveInventory();
        endText();

            //actually do chapter 2
    }

    public void chapterTwo() {
        endText();
        buildNelsonLabsMap(2);
        time(2);
        System.out.println("Chapter 2: Riley's Beginning");
        time(3);
        System.out.println();

        //load nelson's labs
        currentMap = nelsonLabs;
        currentMap.setCurrentBoardY(1);
        currentMap.setCurrentBoardX(3);
        setBoard(currentMap.getCurrentBoard());

        //add player to correct spot
        currentBoard.addNode(3, 4, currentPlayer);

        //do act 1
        while(currentChapter == 2 && currentAct[currentChapter - 1] == 1) {
            chapTwoActOne();
        }

        //do act 2
        //do act 3
        //simple as that

    }



    private void buildNelsonLabsMap(int currentChapter) {
        Board lobby = new Board("L", nelsonLabs, "Nelson Lab's Lobby");
        Board B36 = new Board("B", nelsonLabs, "Nelson Labs's Main Room Body");
        Board B46 = new Board("B", nelsonLabs, "Nelson Labs's Main Room Body");
        Board B56 = new Board("B", nelsonLabs, "Nelson Labs's Main Room Body");
        Board B35 = new Board("B", nelsonLabs, "Nelson Labs's Main Room Body");
        Board B45 = new Board("B", nelsonLabs, "Nelson Labs's Main Room Body");
        Board B55 = new Board("B", nelsonLabs, "Nelson Labs's Main Room Body");
        Board RL40 = new Board("L", nelsonLabs, "Riley's Lab");
        Board H50 = new Board("H", nelsonLabs, "Hospital Room");
        Board B41 = new Board("O", nelsonLabs, "Common Lab Room");
        Board B42 = new Board("O", nelsonLabs, "Common Lab Room");
        Board B51 = new Board("O", nelsonLabs, "Common Lab Room");
        Board B52 = new Board("O", nelsonLabs, "Common Lab Room");
        Board RR31 = new Board("R", nelsonLabs, "Riley's Room");
        Board B32 = new Board("C", nelsonLabs, "Nelson Labs's Employee Common Room");
        Board B43 = new Board("I", nelsonLabs, "Nelson Labs's Hallway Top");
        Board B44 = new Board("I", nelsonLabs, "Nelson Labs's Hallway Bottom");
        nelsonLabs.addBoard(lobby, 4, 7);
        nelsonLabs.addBoard(B36, 3, 6);
        nelsonLabs.addBoard(B46, 4, 6);
        nelsonLabs.addBoard(B56, 5, 6);
        nelsonLabs.addBoard(B35, 3, 5);
        nelsonLabs.addBoard(B45, 4, 5);
        nelsonLabs.addBoard(B55, 5, 5);
        nelsonLabs.addBoard(RL40,4,0);
        nelsonLabs.addBoard(H50, 5, 0);
        nelsonLabs.addBoard(B41, 4 ,1 );
        nelsonLabs.addBoard(B42, 4, 2);
        nelsonLabs.addBoard(B51, 5, 1);
        nelsonLabs.addBoard(B52, 5, 2);
        nelsonLabs.addBoard(RR31, 3, 1);
        nelsonLabs.addBoard(B32, 3, 2);
        nelsonLabs.addBoard(B43, 4, 3);
        nelsonLabs.addBoard(B44, 4, 4);
        //floor for the civilians
        Floor floor = new Floor();


        lobby.setBoardFromString("      / - - /       " +
                "      / - - /       " +
                "    / - - - - /     " +
                "    / - P - - /     " +
                "    / - - - - /     " +
                "    / - - - - /     " +
                "      / P P /       " +
                "        P P         " +
                "                    " +
                "                    ");

        InteractableNode attendant = new InteractableNode("Lobby Attendant", "A", "Talk to Attendant");
        Entrance NLEXIT1 = new Entrance("Exit", "Exit", NYC, 3, 5, 4, 1, true);
        Entrance NLEXIT2 = new Entrance("Exit", "Exit", NYC, 3, 5, 5, 1, true);
        Door NLEXITD1 = new Door(true, true);
        Door NLEXITD2 = new Door(true, true);
        Nodeable[] nodeArray = {attendant, NLEXITD1, NLEXITD2, NLEXIT1, NLEXIT2};
        lobby.fillPlaceholders(nodeArray);


        B36.setBoardFromString("/ - - - - - - - - - " +
                "/ - P - - - - - - - " +
                "/ - T T - - - - - - " +
                "/ - - - - - - - - - " +
                "/ - P - - - - - - - " +
                "/ - T T - - - - - - " +
                "/ - - - - - - - / / " +
                "/ - P P P P - /     " +
                "  / P - - - /       " +
                "    / / / /         ");

        Nodeable[] B36Array = new Nodeable[7];
        Scientist B36a = new Scientist(2,1);
        Scientist B36b = new Scientist(2,4);
        if (currentChapter == 3) {
            B36a.getActionList().clear();
            B36b.getActionList().clear();
        }
        StaticNode B36c = new StaticNode("F", "Food Court Worker");
        B36Array[0] = B36a;
        B36Array[1] = B36b;
        if (currentChapter == 2) {
            B36Array[0] = new Floor();
            B36Array[1] = new Floor();
        }
        B36Array[6] = B36c;
        for (int i = 3; i < 6; i++) {
            StaticNode foodCourt = new StaticNode("=", "Food Court Table");
            B36Array[i] = foodCourt;
        }
        InteractableNode B36d = new InteractableNode("Food Court Table", "=", "Talk to Food Court Worker");
        B36Array[2] =  B36d;
        B36.fillPlaceholders(B36Array);

        if (currentChapter != 2) {
            Civilian civ1 = new Civilian(8, 3, floor);
            B36.addNode(8, 3, civ1);
            Civilian civ2 = new Civilian(6, 1, floor);
            B36.addNode(6, 1, civ2);
            Civilian civ3 = new Civilian(6, 6, floor);
            B36.addNode(6, 6, civ3);
        }


        B46.setBoardFromString("- - - - - - - - - - " +
                "- - - - - - - - - - " +
                "- P - - - - - - P - " +
                "- T T - - - - T T - " +
                "- - - - - - - - - - " +
                "- - - - - - - - - - " +
                "/ / - - - - - - / / " +
                "    / - - - - /     " +
                "      / [ [ /       " +
                "      / - - /       ");

        if (currentChapter == 2) {
            //lock the bottom doors
            B46.getBoard().get(8).get(4).performAction("Lock Door", B46, this);
            B46.getBoard().get(8).get(5).performAction("Lock Door", B46, this);
        }
        Scientist B46a = new Scientist(1,2);
        Scientist B46b = new Scientist(8,2);
        if (currentChapter == 3) {
            B46a.getActionList().clear();
            B46b.getActionList().clear();
        }
        B46.addNode(1, 2, B46a);
        B46.addNode(8, 2, B46b);

        if (currentChapter == 2) {
            B46.addNode(1, 2, B46a);
            B46.addNode(8, 2, B46b);
        }

        if (currentChapter != 2) {
            Civilian civ4 = new Civilian(3, 5, floor);
            B46.addNode(3, 5, civ4);
            Civilian civ5 = new Civilian(5, 2, floor);
            B46.addNode(5, 2, civ5);
            Civilian civ6 = new Civilian(7, 6, floor);
            B46.addNode(7, 6, civ6);
        }

        B56.setBoardFromString("- - - - - - - - - / " +
                "- - - - - - P - - / " +
                "- - - - - - T T - / " +
                "- - - - - - - - - / " +
                "- - - - - - P - - / " +
                "- - - - - - T T - / " +
                "/ / - - - - - - - / " +
                "    / - - - - - - / " +
                "      / - - - - /   " +
                "        / / / /     ");

        Scientist B56a = new Scientist(6,1);
        Scientist B56b = new Scientist(6,4);
        if (currentChapter == 3) {
            B56a.getActionList().clear();
            B56b.getActionList().clear();
        }
        B56.addNode(6, 1, B56a);
        B56.addNode(6, 4, B56b);

        if (currentChapter == 2) {
            B56.addNode(6, 1, new Floor());
            B56.addNode(6, 4, new Floor());
        }

        if (currentChapter != 2) {
            Civilian civ7 = new Civilian(2, 1, floor);
            B56.addNode(2, 1, civ7);
            Civilian civ8 = new Civilian(5, 7, floor);
            B56.addNode(5, 7, civ8);
            Civilian civ9 = new Civilian(4, 3, floor);
            B56.addNode(4, 3, civ9);
        }

        B35.setBoardFromString("    / / / /         " +
                "  / - - - - /       " +
                "/ - - - - - - /     " +
                "/ - P - - - - - / / " +
                "/ - T T - - - - - - " +
                "/ - - - - - - - - - " +
                "/ - - - - - - - - - " +
                "/ - P - - - - - - - " +
                "/ - T T - - - - - - " +
                "/ - - - - - - - - - ");

        Scientist B35a = new Scientist(2,3);
        Scientist B35b = new Scientist(2,7);
        if (currentChapter == 3) {
            B35a.getActionList().clear();
            B35b.getActionList().clear();
        }
        if (currentChapter == 3) {
            B35a.getActionList().clear();
            B35b.getActionList().clear();
        }
        B35.addNode(2, 3, B35a);
        B35.addNode(2, 7, B35b);

        if (currentChapter == 2) {
            B35.addNode(2, 3, new Floor());
            B35.addNode(2, 7, new Floor());
        }

        if (currentChapter != 2) {
            Civilian civ10 = new Civilian(3, 6, floor);
            B35.addNode(3, 6, civ10);
            Civilian civ11 = new Civilian(5, 2, floor);
            B35.addNode(5, 2, civ11);
            Civilian civ12 = new Civilian(7, 7, floor);
            B35.addNode(7, 7, civ12);
        }

        B45.setBoardFromString("      / - - /       " +
                "      / [ [ /       " +
                "    / - - - - /     " +
                "/ / - - - - - - / / " +
                "- - - - - - - - - - " +
                "- - - - - - - - - - " +
                "- - - - - - - - - - " +
                "- - - - - - - - - - " +
                "- - - - - - - - - - " +
                "- - - - - - - - - - ");

        if (currentChapter != 1) {
            B45.getBoard().get(1).get(4).performAction("Lock Door", B45, this);
            B45.getBoard().get(1).get(5).performAction("Lock Door", B45, this);
        }

        if (currentChapter != 2){
            Civilian civ13 = new Civilian(4, 6, floor);
            B45.addNode(4, 6, civ13);
            Civilian civ14 = new Civilian(6, 7, floor);
            B45.addNode(6, 7, civ14);
            Civilian civ15 = new Civilian(2, 4, floor);
            B45.addNode(2, 4, civ15);
        }

        B55.setBoardFromString("        / / / /     " +
                "      / - P J P /   " +
                "    / - - P - P - / " +
                "/ / - - - - - - - / " +
                "- - - - - - - - - / " +
                "- - - - - - G S - / " +
                "- - - - - - - - - / " +
                "- - - - - - - - - / " +
                "- - - - - - V S - / " +
                "- - - - - - - - - / ");

        InteractableNode hMan = new InteractableNode("Hiring Manager", "H", "Talk to Hiring Manager");
        StaticNode jobTable = new StaticNode("=", "Hiring Manager's Desk");
        StaticNode generator = new StaticNode("G", "Golden Electricity Generator");
        Scientist B55a = new Scientist(7, 5);

        Scientist B55b = new Scientist(7, 8);
        if (currentChapter == 3 || currentChapter == 2) {
            B55b.getActionList().clear();
        }
        StaticNode vr = new StaticNode("V", "Virtual Reality Goggles");


        Nodeable[] B55array = {jobTable, hMan, jobTable, jobTable, jobTable, generator, B55a, vr, B55b};
        if (currentChapter == 2) {
            for (int i = 0; i <= 6; i++) {
                B55array[i] = new Floor();
            }
        }
        B55.fillPlaceholders(B55array);

        if (currentChapter != 2) {
            Civilian civ16 = new Civilian(2, 6, floor);
            B55.addNode(2, 6, civ16);
            Civilian civ17 = new Civilian(4, 3, floor);
            B55.addNode(4, 3, civ17);
            Civilian civ18 = new Civilian(6, 7, floor);
            B55.addNode(6, 7, civ18);
        }

        if (currentChapter != 2) {
            return;
        }

        //build riley room:

        RL40.setBoardFromString("                  " +
                "  / / / / / / / /   " +
                "  / - - - S - - /   " +
                "  / T - - - - B /   " +
                "  / T - G - - P /   " +
                "  / C - - - - 2 /   " +
                "  / A - - - - P /   " +
                "  / / / [ / / / /   " +
                "      / - /         " +
                "      / - /         ");

        InteractableNode sunlightChamber = new InteractableNode("Sunlight Chamber", "S", "Inspect");
        InteractableNode burger = new InteractableNode("Burger", "B", "Inspect");
        InteractableNode love = new InteractableNode("Love Finding Device", "L", "Inspect");
        InteractableNode pancake = new InteractableNode("Two Sided Pancake Cooker", "2", "Inspect");
        InteractableNode pants = new InteractableNode("Portal Pocket Pants", "P", "Inspect");
        InteractableNode gen = new InteractableNode("Golden Electricity Generator", "G", "Pick Up");
        InteractableNode cast = new InteractableNode("Generator Cast", "C", "Inspect");
        InteractableNode addiction = new InteractableNode("Riley's Addiction Relief Pills", "A", "Inspect"); // get an item from these
        Nodeable[] RL40arr = {sunlightChamber, burger, gen, love, cast, pancake, addiction, pants};
        RL40.fillPlaceholders(RL40arr);

        H50.setBoardFromString("                    " +
                "                    " +
                "  / / / / / /       " +
                "  / - M - - /       " +
                "  / M P - - /       " +
                "  / - - - V /       " +
                "  / D - - T /       " +
                "  / / / [ / /       " +
                "      / - /         " +
                "      / - /         ");

        StaticNode medical = new StaticNode("M", "Medical Machine");
        StaticNode bed = new StaticNode("=", "Hospital Bed");
        InteractableNode doc = new InteractableNode("Doctor Robot", "D", "Talk");
        InteractableNode vialTable = new InteractableNode("Table to Put Vial On", "O", "Place Vial");
        Nodeable[] H50arr = {medical, medical, bed, vialTable, doc};
        H50.fillPlaceholders(H50arr);

        RR31.setBoardFromString("                    " +
                "                    " +
                "  / / W / / /       " +
                "  / - - | - /       " +
                "  / P - - + /       " +
                "  / - - - - / / / / " +
                "  / N J - - [ - - - " +
                "  / / / / / / / / / " +
                "                    " +
                "                    " +
                "                    ");

        InteractableNode boStaff = new InteractableNode("Bo Staff", "|", "Inspect");
        //bed
        StaticNode desk = new  StaticNode("+", "Desk");
        InteractableNode nightstand = new InteractableNode("Nightstand", "N", "Inspect");
        InteractableNode jukebox = new InteractableNode("Jukebox", "J", "Inspect"); //rickrools you
        InteractableNode photos = new InteractableNode("Photo Wall", "W", "Inspect");
        Nodeable[] RR31arr = {photos, boStaff, bed, desk, nightstand, jukebox};
        RR31.fillPlaceholders(RR31arr);


        B32.setBoardFromString("  / / / / / / / / / " +
                "/ - - - - - - - - / " +
                "/ - B - - J - - - / " +
                "/ - B - - T T - - / " +
                "/ - B - - T P - / / " +
                "/ - B - - - - - [ - " +
                "/ - B - - - - - / / " +
                "/ - B - - C C - - / " +
                "/ - - - - - - - - / " +
                "  / / / / / / / /   ");
        InteractableNode breakfastBar = new InteractableNode("Breakfast Bar", "B", "Eat"); //recursive formula to take out the action lists
        InteractableNode jerry = new InteractableNode("Jerry", "J", "Talk");
        StaticNode couch = new StaticNode("C", "Couch");
        InteractableNode pack = new InteractableNode("Package", "P", "Inspect");
        Nodeable[] B32arr = {breakfastBar, jerry, breakfastBar, breakfastBar, pack, breakfastBar, breakfastBar, breakfastBar, couch, couch};
        B32.fillPlaceholders(B32arr);

        B41.setBoardFromString("      / - /         " +
                "    / / [ / / / / / " +
                "  / - - - - - - - - " +
                "  / - - - - - - - - " +
                "  / - - L L - L L - " +
                "/ / - - - - - - - - " +
                "- [ - - L L - L L - " +
                "/ / - - - - - - - - " +
                "  / - - L L - L L - " +
                "  / - - - - - - - - ");

        //adding movable scientists
        Civilian sci1 = new Civilian("S", "Scientist",3, 6, floor);
        B41.addNode( 3, 6, sci1);
        Civilian sci2 = new Civilian("S", "Scientist",5, 2, floor);
        B41.addNode(5, 2, sci2);
        Civilian sci3 = new Civilian("S", "Scientist",7, 7, floor);
        B41.addNode(7, 7, sci3);

        B42.setBoardFromString("  / - - - - - - - - " +
                "  / - - - - - - - - " +
                "  / - - L L L - - - " +
                "  / - - L - L - - - " +
                "/ / - - - - - - - - " +
                "- [ - - L - L - - - " +
                "/ / - - L L L - - - " +
                "  / - - - - - - - - " +
                "    / / / [ [ / / / " +
                "        / - - /     ");


        Civilian sci4 = new Civilian("S", "Scientist",3, 6, floor);
        B42.addNode(3, 6, sci4);
        Civilian sci5 = new Civilian("S", "Scientist",5, 4, floor);
        B42.addNode(5, 4, sci5);
        Civilian sci6 = new Civilian("S", "Scientist",7, 7, floor);
        B42.addNode(7, 7, sci6);

        B51.setBoardFromString("      / - /         " +
                "/ / / / [ / / /     " +
                "- - - - - - - - /   " +
                "- - - - - - - - - S " +
                "- L L - L L - - - [ " +
                "- - - - - - - - - / " +
                "- L L - L L - - - S " +
                "- - - - - - - - - [ " +
                "- L L - L L - - - / " +
                "- - - - - - - - /   ");

        InteractableNode sign1 = new InteractableNode("Lab Sign 1", "S", "Read");
        InteractableNode sign2 = new InteractableNode("Lab Sign 2", "S", "Read");
        Nodeable[] B51arr = {sign1, sign2};
        B51.fillPlaceholders(B51arr);
        B51.getBoard().get(4).get(9).performAction("Lock Door", B51, this);
        B51.getBoard().get(7).get(9).performAction("Lock Door", B51, this);
        Civilian sci7 = new Civilian("S", "Scientist",3, 6, floor);
        B51.addNode(3, 6, sci7);
        Civilian sci8 = new Civilian("S", "Scientist",5, 5, floor);
        B51.addNode(5, 5, sci8);
        Civilian sci9 = new Civilian("S", "Scientist",7, 7, floor);
        B51.addNode(7, 7, sci9);

        B52.setBoardFromString("- - - - - - - - /   " +
                "- - - - - - - - - / " +
                "- - - - L L L - - / " +
                "- - - - L - L - - S " +
                "- - - - - - - - - [ " +
                "- - - - L - L - - / " +
                "- - - - L L L - - / " +
                "- - - - - - - - /   " +
                "/ / / / / / / /     " +
                "                    ");
        InteractableNode sign3 = new InteractableNode("Lab Sign 3", "S", "Read");
        B52.addNode(9,3, sign3);
        B52.getBoard().get(4).get(9).performAction("Lock Door", B52, this);
        Civilian sci10 = new Civilian("S", "Scientist",3, 6, floor);
        B52.addNode(3, 6, sci10);
        Civilian sci11 = new Civilian("S", "Scientist",5, 5, floor);
        B52.addNode(5, 5, sci11);



        B43.setBoardFromString("        / - - /     " +
                "        / - - /     " +
                "        / - - /     " +
                "        / - - /     " +
                "        / - - /     " +
                "        / - - /     " +
                "        / - - /     " +
                "        / - - /     " +
                "        / - - /     " +
                "        / - - /     ");

        B43.setBoardFromString("        / - - /     " +
                "        / - - /     " +
                "        / - - /     " +
                "        / - - /     " +
                "        / - - /     " +
                "        / - - /     " +
                "        / - - /     " +
                "        / ? ? /     " +
                "        / - - /     " +
                "        / - - /     ");


    }

    private void buildNYCMap(int currentChapter) {
        Board daltonApartmentOutside = new Board("A", NYC, "Apartment Complex Outside");
        NYC.addBoard(daltonApartmentOutside, 7, 5);
        daltonApartmentOutside.setBoardFromString("~ ~ - = = = - /     " +
                "~ ~ - = = = - /     " +
                "- - - - - - - /     " +
                "= = - = = = - /     " +
                "= = - = = = - /     " +
                "= = - = = = - ] P   " +
                "= = - = = = - /     " +
                "- - - - - - - /     " +
                "~ ~ - = = = - /     " +
                "~ ~ - = = = - /     ");
        Entrance daltonApartmentEntrance = new Entrance("Entrance", "Enter", daltonApartmentComplex, 5, 4, 3, 7, true);
        daltonApartmentOutside.addNode(8, 5, daltonApartmentEntrance);

        StaticNode lamp = new StaticNode("L", "Lamppost");
        Nodeable[] lampArr = new Nodeable[4];
        for (int i = 0; i < 4; i++) lampArr[i] = lamp;

        Board road65 = new Board("-", NYC, "Road"); NYC.addBoard(road65, 6, 5);
        road65.setBoardFromString("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ " +
                "~ ~ P ~ ~ ~ ~ P ~ ~ " +
                "- - - - - - - - - - " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "- - - - - - - - - - " +
                "~ ~ P ~ ~ ~ ~ P ~ ~ " +
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");

        road65.fillPlaceholders(lampArr);
        if (currentChapter == 1){
            MysteriousMan mm = new MysteriousMan();
            road65.addNode(5, 9, mm);
        }





        Board road55 = new Board("-", NYC, "Road"); NYC.addBoard(road55, 5, 5);
        road55.setBoardFromString("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ " +
                "~ ~ P ~ ~ ~ ~ P ~ ~ " +
                "- - - - - - - - - - " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "- - - - - - - - - - " +
                "~ ~ P ~ ~ ~ ~ P ~ ~ " +
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");

        road55.fillPlaceholders(lampArr);



        Board road45 = new Board("-", NYC, "Road"); NYC.addBoard(road45, 4, 5);
        road45.setBoardFromString("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ " +
                "~ ~ P ~ ~ ~ ~ P ~ ~ " +
                "- - - - - - - - - - " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "- - - - - - - - - - " +
                "~ ~ P ~ ~ ~ ~ P ~ ~ " +
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");

        road45.fillPlaceholders(lampArr);
        if (currentChapter == 1) {
            MysteriousMan mima = new MysteriousMan();
            road45.addNode(5, 9, mima);
        }
        InteractableNode sign = new InteractableNode("Nelson Labs Sign", "S", "Read Sign");
        road45.addNode(1, 1, sign);


        Board nelsonLabsOutside = new Board("N", NYC, "Nelson Labs Outside");
        NYC.addBoard(nelsonLabsOutside, 3, 5);
        nelsonLabsOutside.setBoardFromString("/ /     P P     / / " +
                "~ ~ / / P P / / ~ ~ " +
                "- - - - - - - - - - " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "- - - - - - - - - - " +
                "~ ~ P ~ ~ ~ ~ P ~ ~ " +
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");


        Entrance NLE1 = new Entrance("Entrance", "Enter", nelsonLabs, 4, 7, 4, 6, true);
        Entrance NLE2 = new Entrance("Entrance", "Enter", nelsonLabs, 4, 7, 5, 6, true);
        Door NLED1 = new Door(true, true);
        Door NLED2 = new Door(true, true);
        Nodeable[] nodeArray = {NLE1, NLE2, NLED1, NLED2, lamp, lamp};
        nelsonLabsOutside.fillPlaceholders(nodeArray);



    }


    private void buildNYCMap() {
        Board ShopRow1 = new Board("R", NYC, "Shop Row 1"); NYC.addBoard(ShopRow1, 7, 2);
        Board ShopRow2 = new Board("R", NYC, "Shop Row 2"); NYC.addBoard(ShopRow2, 7, 3);
        Board ShopRow3 = new Board("R", NYC, "Shop Row 3"); NYC.addBoard(ShopRow3, 7, 4);
        Board ShopRow4 = new Board("R", NYC, "Shop Row 4"); NYC.addBoard(ShopRow4, 7, 6);
        Board ShopRow5 = new Board("R", NYC, "Shop Row 5"); NYC.addBoard(ShopRow5, 7, 7);

        Board ShopCorner1 = new Board("C", NYC, "Shop Corner 1"); NYC.addBoard(ShopCorner1, 2, 1);
        Board ShopCorner2 = new Board("C", NYC, "Shop Corner 2"); NYC.addBoard(ShopCorner2, 1, 1);
        Board ShopCorner3 = new Board("C", NYC, "Shop Corner 3"); NYC.addBoard(ShopCorner3, 1, 2);
        Board ShopCorner4 = new Board("C", NYC, "Shop Corner 4"); NYC.addBoard(ShopCorner4, 1, 3);
        Board ShopCorner5 = new Board("C", NYC, "Shop Corner 5"); NYC.addBoard(ShopCorner5, 1, 4);
        Board ShopCorner6 = new Board("C", NYC, "Shop Corner 6"); NYC.addBoard(ShopCorner6, 1, 5);

        Board road65 = new Board("-", NYC, "Road"); NYC.addBoard(road65, 6, 5);
        Board road55 = new Board("-", NYC, "Road"); NYC.addBoard(road55, 5, 5);
        Board road45 = new Board("-", NYC, "Road"); NYC.addBoard(road45, 4, 5);
        Board road44 = new Board("-", NYC, "Road"); NYC.addBoard(road44, 4, 4);
        Board road43 = new Board("-", NYC, "Road"); NYC.addBoard(road43, 4, 3);
        Board road33 = new Board("-", NYC, "Road"); NYC.addBoard(road33, 3, 3);
        Board road23 = new Board("-", NYC, "Road"); NYC.addBoard(road23, 2, 3);
        Board road24 = new Board("-", NYC, "Road"); NYC.addBoard(road24, 2, 4);
        Board road25 = new Board("-", NYC, "Road"); NYC.addBoard(road25, 2, 5);
        Board road26 = new Board("-", NYC, "Road"); NYC.addBoard(road26, 2, 6);
        Board road36 = new Board("-", NYC, "Road"); NYC.addBoard(road36, 3, 6);
        Board road37 = new Board("-", NYC, "Road"); NYC.addBoard(road37, 3, 7);
        Board road38 = new Board("-", NYC, "Road"); NYC.addBoard(road38, 3, 8);
        Board road48 = new Board("-", NYC, "Road"); NYC.addBoard(road48, 4, 8);
        Board road57 = new Board("-", NYC, "Road"); NYC.addBoard(road57, 5, 7);
        Board road67 = new Board("-", NYC, "Road"); NYC.addBoard(road67, 6, 7);
        Board road68 = new Board("-", NYC, "Road"); NYC.addBoard(road68, 6, 8);
        Board road78 = new Board("-", NYC, "Road"); NYC.addBoard(road78, 7, 8);
        Board road32 = new Board("-", NYC, "Road"); NYC.addBoard(road32, 3, 2);
        Board road52 = new Board("-", NYC, "Road"); NYC.addBoard(road52, 5, 2);
        Board road53 = new Board("-", NYC, "Road"); NYC.addBoard(road53, 5, 3);
        Board road63 = new Board("-", NYC, "Road"); NYC.addBoard(road63, 6, 3);
        Board road31 = new Board("-", NYC, "Road"); NYC.addBoard(road31, 3, 1);
        Board road41 = new Board("-", NYC, "Road"); NYC.addBoard(road41, 4, 1);
        Board road61 = new Board("-", NYC, "Road"); NYC.addBoard(road61, 6, 1);
        Board road71 = new Board("-", NYC, "Road"); NYC.addBoard(road71, 7, 1);

        Board daltonApartmentOutside = new Board("A", NYC, "Apartment Complex Outside");
        NYC.addBoard(daltonApartmentOutside, 7, 5);
        Board nelsonLabsOutside = new Board("N", NYC, "Nelson Labs Outside");
        NYC.addBoard(nelsonLabsOutside, 3, 5);
        Board nelsonLabsBack = new Board("0", NYC, "Nelson Labs Back");
        NYC.addBoard(nelsonLabsBack, 3, 4);
        Board otherLabsOutside = new Board("O", NYC, "O.T.H.E.R. Labs Outside");
        NYC.addBoard(otherLabsOutside, 5, 8);
        Board otherLabsBack = new Board("0", NYC,  "O.T.H.E.R. Labs Back");
        NYC.addBoard(otherLabsBack, 5, 9);
        Board tCorpOutside = new Board("T", NYC, "T-Corp Outside");
        NYC.addBoard(tCorpOutside, 5, 1);
        Board tCorpBack = new Board("0", NYC, "T-Corp Back");
        NYC.addBoard(tCorpBack, 5, 0);

        daltonApartmentOutside.setBoardFromString("~ ~ - = = = - /     " +
                "~ ~ - = = = - /     " +
                "- - - - - - - /     " +
                "= = - = = = - /     " +
                "= = - = = = - /     " +
                "= = - = = = - ] P   " +
                "= = - = = = - /     " +
                "- - - - - - - /     " +
                "~ ~ - = = = - /     " +
                "~ ~ - = = = - /     ");
        Entrance daltonApartmentEntrance = new Entrance("Entrance", "Enter", daltonApartmentComplex, 5, 4, 3, 7, true);
        daltonApartmentOutside.addNode(8, 5, daltonApartmentEntrance);

        road65.setBoardFromString("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ " +
                "~ ~ L ~ ~ ~ ~ L ~ ~ " +
                "- - - - - - - - - - " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "- - - - - - - - - - " +
                "~ ~ L ~ ~ ~ ~ L ~ ~ " +
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");

        MysteriousMan mm = new  MysteriousMan();
        road65.addNode(5, 9, mm);

        road55.setBoardFromString("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ " +
                "~ ~ L ~ ~ ~ ~ L ~ ~ " +
                "- - - - - - - - - - " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "- - - - - - - - - - " +
                "~ ~ L ~ ~ ~ ~ L ~ ~ " +
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");

        road45.setBoardFromString("~ ~ ~ ~ ~ ~ ~ ~ ~ ~ " +
                "~ ~ L ~ ~ ~ ~ L ~ ~ " +
                "- - - - - - - - - - " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "- - - - - - - - - - " +
                "~ ~ L ~ ~ ~ ~ L ~ ~ " +
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");

        MysteriousMan mima = new  MysteriousMan();
        InteractableNode sign = new InteractableNode("Sign", "S", "Read Sign");
        road45.addNode(5, 9, mima);
        road45.addNode(1, 1, sign);

        nelsonLabsOutside.setBoardFromString("/ /     P P     / / " +
                "~ ~ / / P P / / ~ ~ " +
                "- - - - - - - - - - " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "= = = = - - = = = = " +
                "- - - - - - - - - - " +
                "~ ~ L ~ ~ ~ ~ L ~ ~ " +
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ");


        Entrance NLE1 = new Entrance("Entrance", "Enter", nelsonLabs, 4, 7, 4, 6, true);
        Entrance NLE2 = new Entrance("Entrance", "Enter", nelsonLabs, 4, 7, 5, 6, true);
        Door NLED1 = new Door(true, true);
        Door NLED2 = new Door(true, true);
        Nodeable[] nodeArray = {NLE1, NLE2, NLED1, NLED2};
        nelsonLabsOutside.fillPlaceholders(nodeArray);




        //build chapter 2:

    }


    private void buildDaltonApartmentComplexMap() {
        //make overall map
        Board boardDaltonHall = new Board("a", daltonApartmentComplex, "Apartment Hallway");
        Board boardDaltonRoom = new Board("a", daltonApartmentComplex, "Dalton's Room");
        daltonApartmentComplex.addBoard(boardDaltonRoom, 5, 5);
        daltonApartmentComplex.addBoard(boardDaltonHall, 5, 4);

        //design dalton room
        boardDaltonRoom.buildRectRoom(3, 3, 8, 3, 8, 7, 3, 7, 3, 6, Direction.WEST);
        Desk daltonDesk = new Desk();
        Kelly kellyCat = new Kelly();
        Floor floor = new Floor();
        Entrance daltonRoomExit = new Entrance("Exit", "Exit", daltonApartmentComplex, 5, 4, 6, 2, false);
        Item crazySoda = Inventory.getItemFromDictionary("soda");
        Drawer daltonDrawer = new Drawer("Dalton's Desk", crazySoda);
        InteractableNode poster = new InteractableNode("Poster","P", "Look at Poster");


        boardDaltonRoom.addNode(5, 7, poster);
        boardDaltonRoom.addNode(4, 4, daltonDrawer);
        boardDaltonRoom.addNode(6, 4, daltonDesk);
        boardDaltonRoom.addNode(7, 6, kellyCat);
        boardDaltonRoom.addNode(2, 6, daltonRoomExit);


        //design dalton hall
        boardDaltonHall.buildRectRoom(3, 0, 6, 0, 6, 9, 3, 9, 3, 7, Direction.WEST);
        Door doorApartment = new Door(false, true);
        Door daltonRoomDoor = new Door(true, false);
        hobo = new Hobo(floor, 5, 5);
        Entrance daltonRoomEntrance = new Entrance("Entrance", "Enter", daltonApartmentComplex, 5, 5, 3, 6, false);
        Entrance daltonApartmentExit = new Entrance("Exit", "Exit", NYC, 7, 5, 7, 5, true);

        boardDaltonHall.addNode(5, 5, hobo);
        boardDaltonHall.addNode(2, 7, floor);
        boardDaltonHall.addNode(6, 2, daltonRoomDoor);
        boardDaltonHall.addNode(7, 2, daltonRoomEntrance);
        boardDaltonHall.addNode(3, 2, doorApartment);
        boardDaltonHall.addNode(2, 7, daltonApartmentExit);
    }





    static public void print(String message, int time) {
        System.out.println(message);
        System.out.println();
        time(time);
    }

    static public void sPrint(String message) {
        int count = 0;
        for (char c : message.toCharArray()) {
            if (c == ' ') {
                count++;
            }
        }
        count++;
        int sec = (int)((count / 1.6) + 0.5);
        print(message, sec);
    }


    static public String userInput() {
        return input.nextLine();
    }

    static public int intInput() {
        return input.nextInt();
    }




    //---------------------------------------
    /*
    public void toriGameActZero(Map map) {
        System.out.println();
        System.out.println("...");
        System.out.println();
        time(3);

        System.out.println("Act 0: Awakening");
        System.out.println();
        time(3);

        System.out.println("You are Tori Huynh");
        System.out.println();
        time(3);

        System.out.println("A beautiful, kind, and fun girl who is a little on the shorter side.");
        System.out.println();
        time(4);

        System.out.println("You wake up in a field of grass; looking up you see you are in a large, dimly lit room with a large machine in the center and a terminal in the corner.");
        System.out.println();
        time(6);


        currentBoard.printLegend();
        currentBoard.printBoard();

        System.out.println("Game Tip: When you see words following the '-' symbol, it means it is an ACTION. Type in the following action word for word to do it.");
        System.out.println();
        System.out.println("- Get Up");
        System.out.println();
        String firstGet = "";
        while (true) {
            firstGet = input.nextLine();
            if (firstGet.equals("Get Up")) {
                break;
            } else {
                System.out.println("Game Tip: Not quite, you need to type each action *word for word* and without the '-', try again.");
            }
        }
        System.out.println();
        System.out.println("...");
        System.out.println();
        this.time(3);

        System.out.println();
        System.out.println("You do not know why you are here, but some inner voice inside of you tells you that you have a purpose to fulfill in this strange land.");
        System.out.println();
        time(5);

        System.out.println("You decide to look around and explore.");
        System.out.println();
        time(3);

        String message = "Game Tip: You always have access to the 'Help' action. This can tell you the universal actions you always have at your disposal.";
        advancedGameLoop(map, currentPlayer, 0, message, 5, 3, 0, 6, -1, -1);

    }

    public void toriGameActOne(Map map) {
        System.out.println();
        System.out.println("...");
        System.out.println();
        time(3);

        System.out.println("Act 1: Embarkment");
        System.out.println();
        time(3);

        System.out.println("You step through the door way and immediately you feel a gust of wind push you back.");
        System.out.println();
        time(3);

        System.out.println("You find yourself in a dark forest surrounded by trees violently thrashing in the wind.");
        System.out.println();
        time(3);

        System.out.println("The wind itself was off too, it blows in 2 opposite directions that alternate with a pause in between, on a rhythm almost like clockwork.");
        System.out.println();
        time(4);

        currentBoard.printLegend();

        time(2);

        System.out.println("You decide to continue your expedition through this Windy Forest.");
        System.out.println();
        time(2);

        advancedGameLoop(map, currentPlayer, 0, "", 4, 3, 0, 2, -1, -1);
        currentPlayer.moveWest(currentBoard, this);

        System.out.println();
        System.out.println("...");
        System.out.println();
        time(3);

        System.out.println("You find your way through the Windy Forest before stepping into an impossibly large room.");
        System.out.println();
        time(3);

        System.out.println("The horizon seems to span for miles upon miles, the only things in your view were a few tall trees and a lonely shack.");
        System.out.println();
        time(3);

        System.out.println("Your adventure continues!");
        System.out.println();
        time(1);

        currentBoard.printLegend();
        advancedGameLoop(map, currentPlayer, 0, "", 3, 3, 0, 5, -1, -1);
        currentPlayer.moveWest(currentBoard, this);

        System.out.println();
        System.out.println("...");
        System.out.println();
        time(3);

        System.out.println("You enter the next room to find a technological hallway, almost like the ones you see in Star Wars.");
        System.out.println();
        time(3);

        System.out.println("You could feel an engine of sort rumbling beneath the ground and see a bend in the hall with numerous rooms sprouting off from it.");
        System.out.println();
        time(4);

        System.out.println("You are confused how such a large, natural room could lead to a modern, small corridor, but you trek onwards.");
        System.out.println();
        time(3);

        currentBoard.printLegend();
        advancedGameLoop(map, currentPlayer, 0, "", 2, 3, 4, 9, -1, -1);
        currentPlayer.moveSouth(currentBoard, this);

        System.out.println();
        System.out.println("...");
        System.out.println();
        time(3);

        System.out.println("You leave the mechanical hallway to find the loud crashing of flowing water.");
        System.out.println();
        time(3);

        System.out.println("You look out and see large river flowing through space, a mind boggling sight that confirms your suspicions that this is no ordinary land.");
        System.out.println();
        time(5);

        System.out.println("At your feet appears a small raft you hop on it and begin to sail the waters.");
        System.out.println();
        time(3);

        currentBoard.printLegend();
        advancedGameLoop(map, currentPlayer, 0, "", 2, 4, 4, 9, -1, -1);
        currentPlayer.moveSouth(currentBoard, this);

        System.out.println("You step off your raft and move to the next room.");
        System.out.println();
        time(3);

        System.out.println("You see that the water continued and formed sort of a reverse pond, as in it surrounded a patch of space, and in the center of that patch of space was a building that looked like a shrine of sorts.");
        System.out.println();
        time(6);

        currentBoard.printLegend();
        advancedGameLoop(map, currentPlayer, 0, "", 2, 5, 4, 6, -1, -1);


        System.out.println("Upon entering the shrine, you could feel the world around you shake a bit, in the center of the shrine there was a small container.");
        System.out.println();
        time(5);

        System.out.println("Take the container and return back to the Room Where it Happened");
        System.out.println();
        time(4);

        advancedGameLoop(map, currentPlayer, 0, "", 2, 4, 4, 8, -1, -1);

        Item sirRibbitsItem = new Item("Sir Ribbits", "This is the first plushie of mine that I gave you, and you loved him so much! You gave him the personality I never could have, and thank you for making him special to us :)");
        ToriObject ribbits = new ToriObject("R", "A Froggie Plushie", sirRibbitsItem, 7, 5);
        map.getMapBoard().get(4).get(2).addNode(7, 5, ribbits);

        System.out.println("As you get back onto your raft, you see another object floating in the river.");
        System.out.println();
        time(4);

        advancedGameLoop(map, currentPlayer, 0, "", 5, 3, 1, 6, -1, -1);

        System.out.println();
        System.out.println("...");
        System.out.println();
        time(3);

        System.out.println("You return to the Room Where it Happened with your new collections of items, more confused than when you had left.");
        System.out.println();
        time(5);

        System.out.println("You hear a click come from the opposite side of the room. It seems the Right door had unlocked.");
        System.out.println();
        time(4);

        System.out.println("Go to it now.");
        time(3);

        currentBoard.getBoard().get(6).get(9).performAction("Unlock Door", currentBoard, this);

        advancedGameLoop(map, currentPlayer, 0, "", 5, 3, 9, 6, -1, -1);
    }

    public void toriGameActTwo(Map map) {
        System.out.println();
        System.out.println("Act 2: The Search Continues");
        time(3);

        System.out.println();
        System.out.println("...");
        System.out.println();
        time(3);

        System.out.println("You enter through the right door to find yourself back in the Windy Forest");
        System.out.println();
        time(3);

        System.out.println("However, the layout seems different, but it is still as daunting as before.");
        System.out.println();
        time(3);

        System.out.println("Your expedition continues!");
        System.out.println();
        time(3);

        currentBoard.printLegend();
        advancedGameLoop(map, currentPlayer, 0, "", 6, 3, 9, 5, -1, -1);
        currentPlayer.moveEast(currentBoard, this);

        System.out.println("You move to the next room to find yourself in a small room with a door.");
        System.out.println();
        time(3);

        System.out.println("Upon walking through the door, you find another large, spacious area that seemed to go on for miles.");
        System.out.println();
        time(4);

        System.out.println("However, the key difference here is that this room was full of sand and cacti were sprouted up everywhere.");
        System.out.println();
        time(5);

        System.out.println("You try and look further but the back wall seems missing, however the sandstorm blowing by obscures your vision");
        System.out.println();
        time(5);

        System.out.println("Regardless, you must trek through it.");
        System.out.println();
        time(3);

        currentBoard.printLegend();
        advancedGameLoop(map, currentPlayer, 0, "", 7, 3, 9, -1, -1, -1);
        currentPlayer.moveEast(currentBoard, this);

        System.out.println();
        System.out.println("...");
        System.out.println();
        time(3);

        System.out.println("You waddle through the sandstorm when it suddenly calms down. ");
        System.out.println();
        time(3);

        System.out.println("You look around and see a door leading to the next room and a bunch of chests littered around the place.");
        System.out.println();
        time(4);

        System.out.println("Continue on! You got this!");
        System.out.println();
        time(2);

        currentBoard.printLegend();
        advancedGameLoop(map, currentPlayer, 0, "", 8, 3, 5, 9, -1, -1);
        currentPlayer.moveSouth(currentBoard, this);

        System.out.println();
        System.out.println("...");
        System.out.println();
        time(3);

        System.out.println("You walk into the next room and are greeted with a wall. ");
        System.out.println();
        time(3);

        System.out.println("The technological hallway from before seems to has present itself again, however traversing it will be different.");
        System.out.println();
        time(5);

        System.out.println("See if you can figure it out!");
        System.out.println();
        time(3);

        currentBoard.printLegend();
        advancedGameLoop(map, currentPlayer, 0, "", 8, 4, 5, 9, -1, -1);
        currentPlayer.moveSouth(currentBoard, this);

        System.out.println();
        System.out.println("...");
        System.out.println();
        time(3);

        //add exposition + game loop for right hand floating iles

        System.out.println("You walk into the next room and find the edge of the world.");
        System.out.println();
        time(4);

        System.out.println("You look beyond and see a series of floating islands.");

    }

    public void designLeftLung(Board leftLung, Space space, Floor floor, WindyTree f) {
        leftLung.buildRectRoom(0, 0, 9, 0, 9, 9, 0, 9, -1, -1, Direction.NONE);
        leftLung.addNode(8, 5, f);
        leftLung.addNode(7, 5, f);
        leftLung.addNode(4, 5, f);
        leftLung.addNode(3, 5, f);
        leftLung.addNode(2, 5, f);
        leftLung.addNode(1, 1, f);
        leftLung.addNode(2, 1, space);
        leftLung.addNode(3, 1, space);
        leftLung.addNode(4, 1, space);
        leftLung.addNode(5, 1, f);
        leftLung.addNode(6, 1, f);
        leftLung.addNode(7, 1, space);
        leftLung.addNode(8, 1, space);
        leftLung.addNode(0, 2, floor);
        leftLung.addNode(1, 2, floor);
        leftLung.addNode(2, 2, f);
        leftLung.addNode(3, 2, f);
        leftLung.addNode(4, 2, f);
        leftLung.addNode(5, 2, floor);
        leftLung.addNode(6, 2, floor);
        leftLung.addNode(7, 2, f);
        leftLung.addNode(8, 2, space);
        leftLung.addNode(1, 3, floor);
        leftLung.addNode(2, 3, floor);
        leftLung.addNode(3, 3, floor);
        leftLung.addNode(4, 3, floor);
        leftLung.addNode(5, 3, floor);
        leftLung.addNode(6, 3, f);
        leftLung.addNode(7, 3, space);
        leftLung.addNode(8, 3, space);
        leftLung.addNode(1, 4, f);
        leftLung.addNode(2, 4, floor);
        leftLung.addNode(3, 4, floor);
        leftLung.addNode(4, 4, f);
        leftLung.addNode(5, 4, floor);
        leftLung.addNode(6, 4, f);
        leftLung.addNode(7, 4, space);
        leftLung.addNode(8, 4, space);
        leftLung.addNode(1, 5, floor);
        leftLung.addNode(2, 5, f);
        leftLung.addNode(3, 5, f);
        leftLung.addNode(4, 5, f);
        leftLung.addNode(5, 5, floor);
        leftLung.addNode(6, 5, floor);
        leftLung.addNode(7, 5, f);
        leftLung.addNode(8, 5, f);
        leftLung.addNode(1, 6, floor);
        leftLung.addNode(2, 6, floor);
        leftLung.addNode(3, 6, floor);
        leftLung.addNode(4, 6, floor);
        leftLung.addNode(5, 6, floor);
        leftLung.addNode(6, 6, floor);
        leftLung.addNode(7, 6, floor);
        leftLung.addNode(8, 6, floor);
        leftLung.addNode(9, 6, floor);
        leftLung.addNode(1, 7, f);
        leftLung.addNode(2, 7, floor);
        leftLung.addNode(3, 7, floor);
        leftLung.addNode(4, 7, f);
        leftLung.addNode(5, 7, f);
        leftLung.addNode(6, 7, f);
        leftLung.addNode(7, 7, f);
        leftLung.addNode(8, 7, f);
        leftLung.addNode(1, 8, space);
        leftLung.addNode(2, 8, f);
        leftLung.addNode(3, 8, f);
        leftLung.addNode(4, 8, space);
        leftLung.addNode(5, 8, space);
        leftLung.addNode(6, 8, space);
        leftLung.addNode(7, 8, space);
        leftLung.addNode(8, 8, space);
        //6, 2
        Item previewDinnerItem = new Item("Preview Dinner", "This was the day that I got to see you after a long summer of you being at UF without me!. Thank you for making my preview special by showing up and cooking us a yummy dinner. I loved seeing you then and I love seeing you now !");
        ToriObject previewDinner = new ToriObject("S", "A Steak Dinner", previewDinnerItem, 6, 2);
        leftLung.addNode(6, 2, previewDinner);
        Item wallEItem = new Item("Movie Disk", "This movie disk is to WallE, and this was the movie we watched in bed that one night, its my favorite Pixar movie but you made it even more amazing in my eyes. My eeeeeva <3. ");
        ToriObject wallE = new ToriObject("W", "A Movie Disk", wallEItem, 1, 5);
        leftLung.addNode(1, 5, wallE);
    }

    public void designLeftShoulderConnector(Board leftShoulderConnector, Space space, Floor floor) {
        leftShoulderConnector.buildRectRoom(0, 0, 9, 0, 9, 9, 0, 9, -1, -1, Direction.NONE);
        leftShoulderConnector.buildRectRoom(4, 5, 6, 5, 6, 7, 4, 7, 5, 5, Direction.NORTH);
        leftShoulderConnector.addNode(9, 2, floor);
        leftShoulderConnector.addNode(0, 5, floor);
        Item bobertaItem = new Item("Boberta", "The character made by the girl that I fell in love with. Ever since then I would smile each time I saw one on a whiteboard around the school :)");
        ToriObject boberta = new ToriObject("B", "A Figurine", bobertaItem, 5, 6);
        leftShoulderConnector.addNode(5, 6, boberta);
        WindyTree tree = new WindyTree("Y", "Tall Tree");
        leftShoulderConnector.addNode(2, 2, tree);
        leftShoulderConnector.addNode(4, 3, tree);
        leftShoulderConnector.addNode(7, 4, tree);
        leftShoulderConnector.addNode(2, 6, tree);
        leftShoulderConnector.addNode(7, 8, tree);
        leftShoulderConnector.addNode(6, 1, tree);
        leftShoulderConnector.addNode(1, 8, tree);
        Item drawingItem = new Item("Drawings", "The drawings of us that you made and drew at the beginning of our relationship that are so funny and I love so much. Thank you for giving our relationship personality and I am not that round >:O (JK I am, I am eating orange chicken as I write this).");
        ToriObject drawing = new ToriObject("O", "Some Drawings", drawingItem, 4, 8);
        leftShoulderConnector.addNode(4, 8, drawing);


    }

    public void designLeftShoulder(Board leftShoulder, Floor floor) {
        leftShoulder.buildRectRoom(0, 0, 9, 0, 9, 9, 0, 9, -1, -1, Direction.NONE);
        leftShoulder.buildRectRoom(0, 0, 3, 0, 3, 9, 0, 9, 3, 2, Direction.EAST);
        leftShoulder.buildRectRoom(0, 0, 9, 0, 9, 3, 0, 3, 4, 3, Direction.SOUTH);
        leftShoulder.buildRectRoom(5, 6, 9, 6, 9, 9, 5, 9, -1, -1, Direction.NONE);
        Door dooor = new Door(false, false);
        Door doior = new Door(false, false);
        leftShoulder.addNode(3, 5, dooor);
        Wall wall = new Wall();
        leftShoulder.addNode(6, 1, wall);
        leftShoulder.addNode(6, 2, doior);
        leftShoulder.addNode(9, 5, floor);
        leftShoulder.addNode(4, 9, floor);
        Item kikiItem = new Item("Movie Ticket", "This movie ticket is to Kiki's Delivery Service, and this was the first Studio Ghibli movie that we saw in theatres that I loved so much. Not only did I begin to see why so many people liked Studio Ghibli movies, but also it brought me closer to you :}. Also I like the cat :)");
        ToriObject kiki = new ToriObject("K", "A Movie Ticket", kikiItem, 1, 5);
        leftShoulder.addNode(1, 5, kiki);
        Item puddles = new Item("Puddles!", "The lovable first ducky!. Puddles, the funny, protective, round, squishible, lazy, and bestest ducky anyone could ever ask for! I love Puddles so so so so so so so so so much and I love the way he has worked his way into our relationship. Thank you for loving plushies like him, it makes our relationship, and most importantly YOU, special :)");
        ToriObject puddle = new ToriObject("P", "A Duck Plush", puddles, 8, 1);
        leftShoulder.addNode(8, 1, puddle);

    }

    public void designLeftArm(Board a, Space s, Floor f, River r) {
        Item promFotoItem = new Item("2025 Prom Photo", "This is our first real photo together [unfortunately Shane and Thomas were in it]. But it was then when I realized how pretty you were and I began to have more of a crush on you waaaahhhh.");
        ToriObject promFoto = new ToriObject("P", "A Photograph", promFotoItem, 2, 4);


        int y = 1;//1
        a.addNode(1, y, s);
        a.addNode(2, y, s);
        a.addNode(3, y, r);
        a.addNode(4, y, r);
        a.addNode(5, y, r);
        a.addNode(6, y, r);
        a.addNode(7, y, s);
        a.addNode(8, y, s);
        y++;//2
        a.addNode(1, y, s);
        a.addNode(2, y, r);
        a.addNode(3, y, r);
        a.addNode(4, y, r);
        a.addNode(5, y, r);
        a.addNode(6, y, s);
        a.addNode(7, y, s);
        a.addNode(8, y, s);
        y++;//3
        a.addNode(1, y, r);
        a.addNode(2, y, r);
        a.addNode(3, y, r);
        a.addNode(4, y, s);
        a.addNode(5, y, r);
        a.addNode(6, y, r);
        a.addNode(7, y, s);
        a.addNode(8, y, s);
        y++;//4
        a.addNode(1, y, r);
        a.addNode(2, y, promFoto);
        a.addNode(3, y, r);
        a.addNode(4, y, s);
        a.addNode(5, y, s);
        a.addNode(6, y, r);
        a.addNode(7, y, r);
        a.addNode(8, y, s);
        y++; //5
        a.addNode(1, y, r);
        a.addNode(2, y, r);
        a.addNode(3, y, s);
        a.addNode(4, y, s);
        a.addNode(5, y, s);
        a.addNode(6, y, r);
        a.addNode(7, y, r);
        a.addNode(8, y, r);
        y++; //6
        a.addNode(1, y, s);
        a.addNode(2, y, r);
        a.addNode(3, y, r);
        a.addNode(4, y, s);
        a.addNode(5, y, s);
        a.addNode(6, y, s);
        a.addNode(7, y, r);
        a.addNode(8, y, r);
        y++;//7
        a.addNode(1, y, s);
        a.addNode(2, y, s);
        a.addNode(3, y, r);
        a.addNode(4, y, r);
        a.addNode(5, y, s);
        a.addNode(6, y, r);
        a.addNode(7, y, r);
        a.addNode(8, y, s);
        y++;//8
        a.addNode(1, y, s);
        a.addNode(2, y, s);
        a.addNode(3, y, s);
        a.addNode(4, y, r);
        a.addNode(5, y, r);
        a.addNode(6, y, r);
        a.addNode(7, y, s);
        a.addNode(8, y, s);

        a.addNode(4, 0, f);
        a.addNode(4, 9, f);


    }

    public void designLeftHand(Board h, Space s, Floor f, River r) {
        Door shrineDoor = new Door(false, false);
        Wall w = new Wall();
        Item memCont1 = new Item("Memory Container One", "This Memory Container is currently locked. Please upload to the Brain and access on the Terminal.");
        ToriObject memoryContainer1 = new ToriObject("M", "Memory Container", memCont1, 4, 7);

        for (int y = 0; y <= 8; y++) {
            for (int x = 0; x <= 8; x++) {
                if (y == 3 && (x == 2 || x == 3 || x == 5 || x == 6)) h.addNode(x, y, s);
                else if (y >= 4 && (x == 0 || x == 8)) h.addNode(x, y, r);
                else if (x != 4) h.addNode(x, y, r);
            }
        }
        for (int y = 1; y <= 8; y++) {
            if (y == 4 || y == 7) continue;
            h.addNode(4, y, f);
        }

        h.addNode(4, 0, f);
        int y = 4; //4
        h.addNode(1, y, s);
        h.addNode(2, y, s);
        h.addNode(3, y, s);
        h.addNode(4, y, shrineDoor);
        h.addNode(5, y, s);
        h.addNode(6, y, s);
        h.addNode(7, y, s);
        y++; //5
        h.addNode(1, y, s);
        h.addNode(2, y, s);
        h.addNode(3, y, w);
        h.addNode(5, y, w);
        h.addNode(6, y, s);
        h.addNode(7, y, s);
        y++; //6
        h.addNode(1, y, s);
        h.addNode(2, y, w);
        h.addNode(3, y, f);
        h.addNode(5, y, f);
        h.addNode(6, y, w);
        h.addNode(7, y, s);
        y++; //7
        h.addNode(1, y, s);
        h.addNode(2, y, w);
        h.addNode(3, y, f);
        h.addNode(4, y, memoryContainer1);
        h.addNode(5, y, f);
        h.addNode(6, y, w);
        h.addNode(7, y, s);
        y++; //8
        h.addNode(1, y, r);
        h.addNode(2, y, s);
        h.addNode(3, y, w);
        h.addNode(5, y, w);
        h.addNode(6, y, s);
        h.addNode(7, y, r);
    }

    public void designRightLung(Board l, Space s, Floor f, WindyTree t) {
        l.buildRectRoom(0, 0, 9, 0, 9, 9, 0, 9, -1, -1, Direction.NONE);
        for (int y = 1; y <= 8; y++) {
            for (int x = 1; x <= 8; x++) {
                if ((y == 1 && x >= 3) || (y == 3 && (x == 2 || (x >= 4 && x < 7))) || (y == 4 && (x == 2 || x == 4 || x == 8)) || (y == 5 && (x == 1 || x == 2 || x == 5 || x == 7)) || (y == 6 && (x == 2 || x == 3 || x == 5 || x == 7)) || (y == 7 && (x == 3 || x == 5 || x == 7)) || (y == 8 && (x == 1 || x == 5)))
                    l.addNode(x, y, t);
            }
        }
        Item noopCupItem = new Item("Snoopy Cup", "This was the cup I gave you for your very first Valentine's Day with a significant other. I told you at the beginning that I promised to be your first Valentine and I am so happy that I was :}, and thank you for making it the best Valentine's Day ever! P.S. With many more to come ;)");
        ToriObject noopCup = new ToriObject("C", "A Cup", noopCupItem, 5, 4);
        Item notebookItem = new Item("Biology Notebook", "This was the notebook we had to make in 9th grade biology. While I could do with never stepping foot in that class again, it is notable because that is the first time we met :), And I am so glad we did :)))))");
        ToriObject notebook = new ToriObject("N", "A Notebook", notebookItem, 1, 4);
        l.addNode(1, 4, notebook);
        l.addNode(5, 4, noopCup);
        l.addNode(0, 6, f);
        l.addNode(9, 5, f);
    }

    public void designRightShoulderConnector(Board s, Floor f) {
        WindyTree c = new WindyTree("f", "Cactus");
        s.buildRectRoom(0, 0, 9, 0, 9, 9, 0, 9, -1, -1, Direction.NONE);

        int a = -1;
        int b = -1;
        int d = -1;
        for (int y = 1; y <= 8; y++) {
            if (y == 1) a = 7;
            if (y == 2) {
                a = 3;
                b = 8;
            }
            if (y == 3) {
                a = 1;
                b = 6;
            }
            if (y == 4) {
                a = 4;
                b = -1;
            }
            if (y == 5) a = 5;
            if (y == 6) a = -1;
            if (y == 7) {
                a = 4;
                b = 6;
                d = 8;
            }
            if (y == 8) {
                a = -1;
                b = 7;
                d = 3;
            }

            for (int x = 1; x <= 9; x++) {
                if (x == a || x == b || x == d) s.addNode(x, y, c);
                if (x == 9) s.addNode(x, y, f);
            }
        }

        s.buildRectRoom(0, 4, 2, 4, 2, 6, 0, 6, 2, 5, Direction.EAST);
        s.addNode(0, 5, f);
        Item buttersItem = new Item("Butters", "This rectangular, delicious-looking, bounceable, butthole-having dog is the first stuffed animal I bought you from my trip to Universal. Even though that trip was one of my favorite days of my life, I was still thinking of you during the whole time. I love you <3");
        ToriObject butter = new ToriObject("B", "A Slab of Butter", buttersItem, 7, 7);
        s.addNode(7, 7, butter);
    }

    public void designRightShoulder(Board b, Floor f) {
        b.buildRectRoom(0, 0, 9, 0, 9, 9, 0, 9, -1, -1, Direction.NONE);
        b.buildRectRoom(4, 7, 6, 7, 6, 9, 4, 9, -1, -1, Direction.NONE);

        for (int y = 1; y <= 8; y++) {
            b.addNode(0, y, f);
        }
        ArrayList<Chest> chestList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Chest chest = new Chest();
            chestList.add(chest);
        }
        b.addNode(1, 8, chestList.get(0));
        b.addNode(2, 5, chestList.get(1));
        b.addNode(3, 2, chestList.get(2));
        b.addNode(8, 2, chestList.get(3));
        b.addNode(4, 6, chestList.get(4));

        Item key = new Item("Key", "A small key used to unlock doors.");
        Chest keyChest = new Chest(key);
        b.addNode(7, 5, keyChest);

        Item blobox = new Item("Roblox", "This refers to all the Roblox games we have played together. To be honest, I always hated on Roblox since my sisters played it, but ever since playing with you I really enjoy it :), you make everything better, thank you <3");
        Chest robChest = new Chest(blobox);
        b.addNode(5, 3, robChest);

        Door keyDoor = new Door(false, true, key);
        b.addNode(5, 7, keyDoor);

        b.addNode(5, 9, f);
    }

    public void designRightArm(Board b, Floor f, Wall w) {
        b.buildRectRoom(6, 0, 9, 0, 9, 3, 6, 3, 6, 1, Direction.WEST);
        b.buildRectRoom(6, 3, 9, 3, 9, 6, 6, 6, -1, -1, Direction.NONE);
        b.buildRectRoom(6, 6, 9, 6, 9, 9, 6, 9, 6, 8, Direction.WEST);
        b.buildRectRoom(0, 0, 4, 0, 4, 3, 0, 3, -1, -1, Direction.NONE);
        b.buildRectRoom(0, 3, 4, 3, 4, 6, 0, 6, -1, -1, Direction.NONE);
        b.buildRectRoom(0, 6, 4, 6, 4, 9, 0, 9, 2, 6, Direction.NORTH);
        for (int y = 0; y <= 9; y++) {
            if (y == 3 || y == 6) b.addNode(5, y, w);
            else b.addNode(5, y, f);
        }
        Reference r1 = new Reference(1, 8);
        Reference r2 = new Reference(8, 1);
        Reference r3 = new Reference(8, 5);
        Reference r4 = new Reference(2, 1);
        Reference r5 = new Reference(5, 2);
        Reference r6 = new Reference(5, 4);
        Reference r7 = new Reference(8, 7);
        Reference r8 = new Reference(1, 5);
        b.addNode(1, 8, r1);
        b.addNode(8, 1, r2);
        b.addNode(8, 5, r3);
        b.addNode(2, 1, r4);
        b.addNode(5, 2, r5);
        b.addNode(5, 4, r6);
        b.addNode(8, 7, r7);
        b.addNode(1, 5, r8);

        Pointer p1 = new Pointer(r1);
        Pointer p2 = new Pointer(r2);
        Pointer p3 = new Pointer(r3);
        Pointer p4 = new Pointer(r4);
        Pointer p5 = new Pointer(r5);
        Pointer p6 = new Pointer(r6);
        Pointer p7 = new Pointer(r7);
        Pointer p8 = new Pointer(r1);
        Pointer p9 = new Pointer(r8);
        b.addNode(8, 2, p1);
        b.addNode(3, 5, p2);
        b.addNode(1, 4, p3);
        b.addNode(7, 4, p4);
        b.addNode(5, 5, p5);
        b.addNode(3, 1, p6);
        b.addNode(1, 2, p7);
        b.addNode(3, 8, p8);
        b.addNode(5, 7, p9);

        Item braceItem = new Item("Bracelets", "These are the bracelets you gave to me, one you made and the other was from the pack you gave to your color guard friends, and I love these bracelets because they make me think of you :)");
        ToriObject brace = new ToriObject("O", "A Pair of Bracelets", braceItem, 3, 4);
        Item bottl = new Item("The Beatles", "This is the very first band we listened to on that first fateful day of ribbon making, and it is the band we have bonded over. With songs like 'I Will' and 'When I'm Sixty-Four' the Beatles have been our favorite and I love them even more because of you. Thank you for making every aspect of my life better :D ");
        ToriObject beatl = new ToriObject("B", "THE BEATLES", bottl, 7, 7);

        b.addNode(3, 4, brace);
        b.addNode(7, 7, beatl);

    }

    public void designRightHand(Board b, Floor f) {
        for (int y = 0; y <= 9; y++) {
            for (int x = 0; x <= 9; x++) {
                if (y == 0) {
                    if (x == 5) b.addNode(x, y, f);
                } else if (y == 1) {
                    if (x == 4 || x == 5) b.addNode(x, y, f);
                } else if (y == 2) {
                    if (x == 4 || x == 5) b.addNode(x, y, f);
                } else if (y == 4) {
                    if (x == 1 || x == 2 || x >= 8) b.addNode(x, y, f);
                } else if (y == 5) {
                    if (x == 1 || x == 3) b.addNode(x, y, f);
                } else if (y == 6 && x == 2) b.addNode(x, y, f);
                else if (y == 8) {
                    if (x == 4 || x == 6) b.addNode(x, y, f);
                } else if (y == 9) {
                    if (x > 3 && x < 7) b.addNode(x, y, f);
                }
            }
        }
        Reference r1 = new Reference(2, 5);
        Reference r2 = new Reference(9, 3);
        Reference r3 = new Reference(5, 7);
        Reference r4 = new Reference(6, 1);
        b.addNode(2, 5, r1);
        b.addNode(9, 3, r2);
        b.addNode(5, 7, r3);
        b.addNode(6, 1, r4);


        Pointer p1 = new Pointer(r1);
        Pointer p2 = new Pointer(r2);
        Pointer p3 = new Pointer(r3);
        Pointer p4 = new Pointer(r4);
        b.addNode(5, 2, p1);
        b.addNode(1, 6, p2);
        b.addNode(9, 5, p3);
        b.addNode(7, 9, p4);

        Item paddlesItem = new Item("Paddles!", "The cute, fiery, adorable momma ducky and the bestest gift I could ever hope for. Paddles completed our relationship as now we had a ducky for you. I was so surprised and happy that day you gave me her, and once again thank you for making our relationship the most special one ever!");
        Chest chest = new Chest(paddlesItem);

        Door door = new Door(false, false);
        b.addNode(3, 4, chest);
        b.addNode(5,8, door);

        Item memCont2 = new Item("Memory Container Two", "This Memory Container is currently locked. Please upload to the Brain and access on the Terminal.");
        ToriObject memoryContainer2 = new ToriObject("M", "Memory Container", memCont2, 3, 9);
        b.addNode(3,9, memoryContainer2);
    }

    public void addHeart(Board heart, Space space) {
        Heart heartPiece = new Heart();
        heart.addNode(3, 2, heartPiece);
        heart.addNode(4, 3, heartPiece);
        heart.addNode(5, 3, heartPiece);
        heart.addNode(6, 2, heartPiece);
        heart.addNode(7, 3, heartPiece);
        heart.addNode(7, 4, heartPiece);
        heart.addNode(6, 5, heartPiece);
        heart.addNode(5, 6, heartPiece);
        heart.addNode(4, 6, heartPiece);
        heart.addNode(3, 5, heartPiece);
        heart.addNode(2, 4, heartPiece);
        heart.addNode(2, 3, heartPiece);
        heart.addNode(3, 3, space);
        heart.addNode(3, 4, space);
        heart.addNode(4, 4, space);
        heart.addNode(5, 4, space);
        heart.addNode(6, 4, space);
        heart.addNode(6, 3, space);
        heart.addNode(4, 5, space);
        heart.addNode(5, 5, space);
    }

    public void toriGame() {
        System.out.println("Welcome to Tori's Wonderland");
        time(3);
        Floor floor = new Floor();
        System.out.println();

        //create the Map:

        Map toriMap = new Map(5, 3);

        Board leftHand = new Board("[", toriMap, "Ocean Shrine");
        Board leftArm = new Board("|", toriMap, "Space River");
        Board leftShoulder = new Board("|", toriMap, "Tech Sector Hall");
        Board leftShoulderConnector = new Board("|", toriMap, "Flat Field");
        Board leftLung = new Board("|", toriMap, "Windy Forest Left");
        toriMap.addBoard(leftHand, 2, 5);
        toriMap.addBoard(leftArm, 2, 4);
        toriMap.addBoard(leftShoulder, 2, 3);
        toriMap.addBoard(leftShoulderConnector, 3, 3);
        toriMap.addBoard(leftLung, 4, 3);

        Board rightHand = new Board("]", toriMap, "Floating Isle");
        Board rightArm = new Board("|", toriMap, "Convoluted Hall");
        Board rightShoulder = new Board("|", toriMap, "Treasure Cove");
        Board rightShoulderConnector = new Board("|", toriMap, "Cactus City");
        Board rightLung = new Board("|", toriMap, "Windy Forest Right");
        toriMap.addBoard(rightHand, 8, 5);
        toriMap.addBoard(rightArm, 8, 4);
        toriMap.addBoard(rightShoulder, 8, 3);
        toriMap.addBoard(rightShoulderConnector, 7, 3);
        toriMap.addBoard(rightLung, 6, 3);

        Board heart = new Board("|", toriMap, "The Room Where it Happened");
        toriMap.addBoard(heart, 5, 3);
        currentBoard = heart;

        Board neck = new Board("|", toriMap, "Upward Passageway");
        Board mouth = new Board("|", toriMap, "Docking Bay");
        Board brainCenter = new Board("|", toriMap, "Central Processing Unit");
        Board brainLeft = new Board("|", toriMap, "Left Processing Unit");
        Board brainRight = new Board("|", toriMap, "Right Processing Unit");
        Board leftEye = new Board("0", toriMap, "Left Window");
        Board rightEye = new Board("0", toriMap, "Right Window");
        toriMap.addBoard(neck, 5, 2);
        toriMap.addBoard(mouth, 5, 1);
        toriMap.addBoard(brainCenter, 5, 0);
        toriMap.addBoard(brainLeft, 4, 0);
        toriMap.addBoard(brainRight, 6, 0);
        toriMap.addBoard(leftEye, 4, 1);
        toriMap.addBoard(rightEye, 6, 1);

        Board ribCage = new Board("|", toriMap, "Protected Passagway");
        Board stomach = new Board("|", toriMap, "Storage Warehouse");
        Board lowerStomach = new Board("|", toriMap, "Delivery Bay");
        toriMap.addBoard(ribCage, 5, 4);
        toriMap.addBoard(stomach, 5, 5);
        toriMap.addBoard(lowerStomach, 5, 6);

        Board hipCenter = new Board("|", toriMap, "Lower Lands Center");
        Board hipLeft = new Board("|", toriMap, "Lower Lands Left");
        Board hipRight = new Board("|", toriMap, "Lower Lands Right");
        toriMap.addBoard(hipCenter, 5, 7);
        toriMap.addBoard(hipLeft, 4, 7);
        toriMap.addBoard(hipRight, 6, 7);

        Board rightKnee = new Board("|", toriMap, "Lower Lands Bend Right");
        Board rightLeg = new Board("|", toriMap, "Lower Lands Hallway Right");
        Board rightFoot = new Board("[", toriMap, "Lower Lands Corridor Right");
        toriMap.addBoard(rightKnee, 7, 7);
        toriMap.addBoard(rightLeg, 7, 8);
        toriMap.addBoard(rightFoot, 7, 9);

        Board leftKnee = new Board("|", toriMap, "Lower Lands Bend Left");
        Board leftLeg = new Board("|", toriMap, "Lower Lands Hallway Left");
        Board leftFoot = new Board("]", toriMap, "Lower Lands Corridor Left");
        toriMap.addBoard(leftKnee, 3, 7);
        toriMap.addBoard(leftLeg, 3, 8);
        toriMap.addBoard(leftFoot, 3, 9);

        //fill the boards:
        heart.buildRectRoom(0, 0, 9, 0, 9, 9, 0, 9, 5, 0, Direction.NORTH);
        heart.getBoard().get(0).get(5).performAction("Lock Door", heart, this);
        Door topDoor = new Door(false, true);
        Door bottomDoor1 = new Door(false, true);
        Door bottomDoor2 = new Door(false, true);
        Door rightDoor = new Door(false, true);
        Door leftDoor = new Door(false, false);

        heart.addNode(4, 0, topDoor);
        heart.addNode(4, 9, bottomDoor1);
        heart.addNode(5, 9, bottomDoor2);
        heart.addNode(9, 6, rightDoor);
        heart.addNode(0, 6, leftDoor);

        //add the heart to heart.
        Space space = new Space();
        River river = new River();
        addHeart(heart, space);
        WindyTree f = new WindyTree("F", "Windy Tree");


        HeartTerminal terminal = new HeartTerminal();
        heart.addNode(7, 8, terminal);

        //design left
        designLeftLung(leftLung, space, floor, f);
        designLeftShoulderConnector(leftShoulderConnector, space, floor);
        designLeftShoulder(leftShoulder, floor);
        designLeftArm(leftArm, space, floor, river);
        designLeftHand(leftHand, space, floor, river);

        //design right
        designRightLung(rightLung, space, floor, f);
        designRightShoulderConnector(rightShoulderConnector, floor);
        designRightShoulder(rightShoulder, floor);
        designRightArm(rightArm, floor, new Wall());
        designRightHand(rightHand, floor);


        Player toriPlayer = new Player("T", "Tori", floor);
        heart.addNode(2, 7, toriPlayer);
        currentPlayer = toriPlayer;

        toriGameActZero(toriMap);
        toriPlayer.moveWest(currentBoard, this);

        toriGameActOne(toriMap);
        currentPlayer.moveEast(currentBoard, this);

        rightDoor.performAction("Unlock Door", currentBoard, this);
        toriGameActTwo(toriMap);
    }
    */

}

