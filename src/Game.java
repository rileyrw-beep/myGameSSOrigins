import java.util.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {
    //keep track of player
    private String playerName;
    private Player currentPlayer;

    //objects needed to do work
    private final Scanner input;
    private final Prompt prompt;

    //keep track of chapter/acts
    private int currentChapter;
    private int latestChapter;
    private int[] currentAct;
    private int[] latestActs;
    private final int[] totalActs;

    //convenience.
    private int timeNumber;

    //constructor
    public Game () {

        playerName = "";
        input = new Scanner(System.in);
        prompt = new Prompt();
        currentChapter = 0;
        latestChapter = 0;
        currentAct = new int[8];
        latestActs = new int[8];
        totalActs = new int[]{2, 0, 0, 0, 0, 0, 0, 0};
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

    //setters for chapters and acts
    public void setCurrentChapter(int newChapter) {
        currentChapter = newChapter;
    }
    public void setCurrentAct(int newAct, int chapter) {
        currentAct[chapter-1] = newAct;
    }

    //some random convenient methods to make things easier
    public void setTimeNumber(int newTimeNumber) {
        timeNumber = newTimeNumber;
    }
    public void endText() {
        System.out.println();
        System.out.println();

    }
    public void startFromSpecificAct() {
        while (true) {
            System.out.println();
            System.out.print("Enter your desired Chapter number:");
            try {
                System.out.println();
                int desiredChapter = input.nextInt();
                System.out.println();
                if (desiredChapter >= 1 && desiredChapter <= latestChapter) {
                    currentChapter = desiredChapter;
                    break;
                }
                else {
                    System.out.println("That is not a valid Chapter number, please try again.");
                }
            }
            catch (Exception InputMismatchException) {
                System.out.println("Please enter a valid integer.");
            }
        }
        while (true) {
            System.out.println();
            System.out.println("Enter your desired Act number:");
            try {
                System.out.println();
                int desiredAct = input.nextInt();
                System.out.println();
                if (desiredAct >= 1 && desiredAct <= latestActs[currentChapter - 1]) {
                    currentAct[currentChapter-1] = desiredAct;
                    break;
                }
                else {
                    System.out.println("That is not a valid Act number, please try again.");
                }
            }
            catch (Exception InputMismatchException) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }
    public void time(int sec) {
        if (timeNumber==200) {
            return;
        }
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    //the beginning and end of game methods
    public boolean[] gameOver (String deathText, boolean inBattle, Ending ending) {
        boolean[] returnArray = {false, true};
        System.out.println(deathText);
        this.time(3);
        System.out.println();
        System.out.println("...");
        System.out.println();
        System.out.println("GAME OVER");
        System.out.println();
        time(2);
        if (ending==Ending.BAD_ENDING) System.out.println("BAD ENDING");
        if (ending==Ending.GOOD_ENDING) System.out.println("GOOD ENDING");
        if (ending==Ending.SPECIAL_ENDING) System.out.println("SPECIAL ENDING");
        if (ending==Ending.TRUE_ENDING) System.out.println("TRUE ENDING");
        this.time(2);

        System.out.println();
        System.out.println("Would you like to restart from the last act or end the game?");
        System.out.println();

        ArrayList<String> optionList = new ArrayList<String>();
        optionList.add("Restart From Last Act");
        optionList.add("Restart From Specific Act");
        optionList.add("End Game");
        if (inBattle) optionList.add("Restart Battle");

        String response = this.basicGameLoop("", optionList);
        if (response.equals("Restart From Last Act")) {
            return returnArray;
        }
        if (response.equals("Restart From Specific Act")) {
            startFromSpecificAct();
            return returnArray;
        }
        if (response.equals("Restart Battle")) {
            returnArray[1] = false;
            return returnArray;
        }
        if (response.equals("End Game")){
            System.exit(0);
        }
        returnArray[1] = false;
        return returnArray;
    }
    public void askPlayerName() {
        this.endText();
        System.out.println("Welcome to S.S. Origins");
        System.out.println();
        System.out.println("Please enter your name:");
        String get = input.nextLine();
        this.setPlayerName(get);
        System.out.println();
        System.out.println("Hello, " + playerName);
        if (get.equals("fast")) {
            timeNumber = 200;
        }

        this.endText();
    }
    public void startGame() {
        System.out.println("Enter the word 'begin' below to start the game.");
        String get = "";
        while (true) {
            get = input.nextLine();
            System.out.println();
            if (get.equals("begin") || get.equals("What if Dalton was a superhero?")) {
                break;
            }
            System.out.println("Sorry, that was not a valid name. Please try again.");
            System.out.println();
        }
        if (get.equals("begin")) {
            System.out.println("Starting game...");
            currentChapter = 1;
            currentAct[currentChapter-1] = 1;
            latestChapter = 1;
            latestActs[latestChapter-1] = 1;
        }
        else {
            System.out.println("Interesting, we must have an OG fan on our hands.");
            System.out.println();
            System.out.println("Choose your desired Chapter and Act below: ");
            System.out.println();
            int chap = -1;
            int act = -1;
            while (true) {
                try {
                    System.out.print("Chapter: ");
                    chap = input.nextInt();
                    if (chap >= 1 && chap <= 8) {
                        currentChapter = chap;
                        break;
                    }
                    else {
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
                    if (act >= 1 && act <= totalActs[currentChapter-1]) {
                        currentAct[currentChapter-1] = act;
                        break;
                    }
                    else {
                        System.out.println("Try Again");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Try Again");
                }
            }

        }
        time(3);
        this.endText();
        System.out.println();
        System.out.println();
        time(2);
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
    public boolean advancedGameLoop(Board board, Player player, int messageCounter, String message, int endX, int endY, int battleX, int battleY) {
        String get = "";
        boolean[] boolArray = new boolean[2];
        board.printBoard();
        boolean canPrintBoard = true;
        int counter = 0;
        // false false -> battle again
        // true false -> good, continue
        // false true -> retart from last act
        while (true) {
            if ((board.getCharPosX()==battleX && board.getCharPosY()==battleY) || (battleX==-1 && board.getCharPosY()==battleY) || (battleY==1 && board.getCharPosX()==battleX)) {
                boolArray = prompt.displayBattleActions(board, player, this);
                if (boolArray[1]) {
                    return false;
                }
                return true;
            }
            if (counter == messageCounter) {
                System.out.println(message);
                System.out.println();
            }
            prompt.displayActions(board, player,this);
            System.out.println();

            get = input.nextLine();
            System.out.println();
            boolArray = prompt.doAction(get, board, player, this);
            if (!boolArray[0]) {
                if (boolArray[1]) {
                    return false;
                }
                canPrintBoard = false;
                if(get.length()>4) {
                    if (get.startsWith("Move")) {
                        System.out.println("You cannot move to that spot! Try again.");
                    }
                    else {
                        System.out.println("I don't know that one right now, try again.");
                    }
                }
                else {
                    System.out.println("I don't know that one right now, try again.");
                }
                System.out.println();
            }

            if (board.getCharPosX()==endX && board.getCharPosY()==endY) {
                break;
            }
            if (canPrintBoard) {
                board.printBoard();
            }
            canPrintBoard = true;
            counter++;
        }
        return true;
    }

    //Chapter 1 acts.
    public void chapOneActOne() {
        Board boardDaltonRoom = new Board("Dalton's Room");
        System.out.println();
        System.out.println("Act 1: Dalton's Apartment");
        endText();
        time(3);

        System.out.println("You are Dalton Young.");
        System.out.println();
        this.time(3);

        System.out.println("Billionaire. Playboy. World Famous.");
        System.out.println();
        this.time(3);

        System.out.println("You had just finished filming your latest Hype movie and are taking a relaxing retreat to the Bahamas. There, one of your servants reminds you of your 3:00 with Post Malone, to which you begin to hum the melody of 'Texas Tea.'");
        this.time(5);
        System.out.println();

        System.out.println("But the humming did not sound like hit artist Post Malone's 'Texas Tea.' Instead it sounded sort of like a BEEP! BEEP! BEEP!");
        this.time(4);
        System.out.println();

        boardDaltonRoom.printBoard();
        this.time(1);
        System.out.println("Game Tip: When you see words following the '-' symbol, it means it is an ACTION. Type in the following action word for word to do it.");
        System.out.println();
        System.out.println("- Wake Up");
        System.out.println();
        String firstGet = "";
        while(true) {
            firstGet = input.nextLine();
            if (firstGet.equals("Wake Up")) {
                break;
            }
            else {System.out.println("Game Tip: Not quite, you need to type each action *word for word* and without the '-', try again.");}
        }
        System.out.println();
        System.out.println("...");
        System.out.println();
        this.time(3);
        System.out.println("You groggily fish around and shut off the timer on the phone.");
        this.time(3);
        System.out.println();
        System.out.println("You are Dalton Young.");
        this.time(3);
        System.out.println();
        System.out.println("Broke. Single. Known by Nobody Except Your Few Hundred Fans of Your Creative Maps.");
        this.time(3);
        System.out.println();

        ArrayList<String> optionList = new  ArrayList<>();
        optionList.add("Wake Up");
        basicGameLoop("", optionList);
        System.out.println();

        boardDaltonRoom.buildRectRoom(3, 3, 8, 3, 8, 7, 3, 7, 3, 6, Direction.WEST);
        Bed daltonBed = new Bed();
        Desk daltonDesk = new Desk();
        Kelly kellyCat = new Kelly();
        Floor floor = new Floor();
        Item crazySoda = new Item("Crazy Soda", "");
        Drawer daltonDrawer = new Drawer("Dalton's Desk", crazySoda);

        Player player = new Player("D", "Dalton Young", daltonBed);
        this.setPlayer(player);
        boardDaltonRoom.addNode(7, 4, player);
        boardDaltonRoom.addNode(4, 4, daltonDrawer);
        boardDaltonRoom.addNode(6, 4, daltonDesk);
        boardDaltonRoom.addNode(7, 6, kellyCat);
        boardDaltonRoom.addNode(2, 6, floor);
        boardDaltonRoom.printLegend();
        boardDaltonRoom.printBoard();



        this.time(4);
        System.out.println("Instead of a beachy resort, you find yourself in a dimly lit room. As you try to remember who you are and why you are here, you have an urge to go back into the covers and sleep a while.");
        this.time(4);
        System.out.println();
        System.out.println("But no, you need to get up out of bed. Clearly you are NOT a morning person but this game has to start somehow.");
        this.time(3);
        System.out.println();
        optionList.clear();
        optionList.add("Get Up");
        basicGameLoop("", optionList);
        player.moveSouth(boardDaltonRoom);
        System.out.println();
        System.out.println("...");
        this.time(3);
        System.out.println();
        boardDaltonRoom.printLegend();
        boardDaltonRoom.printBoard();

        System.out.println("You look around your musty, dusty, crusty one room apartment and see your bed, desk, and your faithful cat Kelly. Looking at your desk you remember you were just in the middle of making the latest hype update to Hype 2.");
        System.out.println();
        this.time(4);
        System.out.println("Ever since the success of Hype 1 you had moved out to live on your own. But unfortunately UEFN is dominated by basic Red V Blues and other brainrot maps, so it seems you need to get a job to pay the rent.");
        System.out.println();
        this.time(4);
        System.out.println("You turn to go back into bed but then you remember why you set your alarm to wake you so early. Today was the big Tech Fair at Nelson's Labs and they had open job positions that you wanted to check out.");
        System.out.println();
        this.time(4);
        System.out.println("You should head there now.");
        this.time(3);

        //the game loop begins.

        String message = "Game Tip: You always have access to the 'Help' action. This can tell you the universal actions you always have at your disposal.";
        String message2 = "I don't have all day and neither do you. You should probably get going to the Tech Fair!";
        if (!advancedGameLoop(boardDaltonRoom, player, 0, message, 6, 5, -1, -1)) return;
        if (!advancedGameLoop(boardDaltonRoom, player, 9, message2, 2, 6, -1, -1)) return;

        //-----------------------board change------------------------

        Board boardDaltonHall = new Board("Apartment Hallway");
        boardDaltonHall.buildRectRoom(3,0,6,0,6,9,3,9,3,7,Direction.WEST);
        Door doorApartment = new Door(false, true);
        Hobo hobo = new Hobo(floor, 5, 5);
        player.setHobo(hobo);
        boardDaltonHall.addNode(5, 5, hobo);
        boardDaltonHall.addNode(5, 2, player);
        boardDaltonHall.addNode(2, 7, floor);
        boardDaltonHall.addNode(6,2,doorApartment);
        boardDaltonHall.addNode(3,2,doorApartment);



        boardDaltonHall.printLegend();
        boardDaltonHall.printBoard();

        System.out.println();
        System.out.println("...");
        System.out.println();

        time(3);

        System.out.println("You exit your room into your apartment complex's hallway, locking the door behind you.");
        System.out.println();
        time(3);

        System.out.println("You check your pockets to see if you have any change for breakfast, but you come up empty handed.");
        System.out.println();
        time(3);

        System.out.println("You look up to see a hobo wandering aimlessly in the halls. He had on a purple and black uniform with a name tag reading 'J. Young.'");
        System.out.println();
        System.out.println();
        time(5);

        System.out.println("As you walk closer he seems to be mimicking your movements.");
        System.out.println();
        time(4);

        System.out.println("Regardless, the exit to your apartment building is at the end of the hall.");
        System.out.println();
        time(3);

        System.out.println("Go there now.");

        if (!advancedGameLoop(boardDaltonHall, player, -1, "", 2, 7, -1, hobo.currentY-1)) return;
        player.setHobo(null);
        if (!advancedGameLoop(boardDaltonHall, player, -1, "", 2, 7, -1, -1)) return;
        //finish the battle stuff
        currentAct[currentChapter-1]++;
        endText();
    }
    public void chapOneActTwo() {
        System.out.println();
        System.out.println("Act 2: Dalton's Trip");
        endText();
        time(3);

        System.out.println("As you leave the apartment complex doors the sun shines in your eyes as you are greeted by the noisy traffic of Brumeshire City.");
        System.out.println();
        time(5);

        System.out.println("Brumeshire City, once a historical, major port of the United States, is now one of the world leaders of cutting edge technology.");
        System.out.println();
        time(5);

        System.out.println("With skyscrapers taller than the cloudline and automated machines left and right, Brumeshire City is the go to spot for innovators of all types.");
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

        System.out.println("It is to the West. Go there now.");
        System.out.println();
        time(3);

        currentAct[currentChapter-1]++;}

    //chapter 1 method
    public void chapterOne() {
        System.out.println("Chapter 1: Dalton's Beginning");
        time(3);
        System.out.println();


        while (currentChapter == 1 && currentAct[currentChapter-1]==1) {
            chapOneActOne();
        }
        while (currentChapter == 1 && currentAct[currentChapter-1]==2) {
            chapOneActTwo();
        }

        System.out.println("YAyy");
    }


}

