import java.security.spec.RSAOtherPrimeInfo;
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
    private Board currentBoard;

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
    public void setBoard(Board board) {
        currentBoard = board;
    }
    public Board getBoard() {
        return currentBoard;
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
    public boolean askPlayerName() {
        this.endText();
        System.out.println("Welcome to S.S. Origins");
        System.out.println();
        System.out.println("Please enter your name:");
        String get = input.nextLine();
        this.setPlayerName(get);
        System.out.println();
        if (get.equals("Tori")) System.out.println("Hi hi my love <3");
        else {System.out.println("Hello, " + playerName);}
        if (get.equals("fast")) {
            timeNumber = 200;
        }
        if (get.equals("Tori")) {
            return true;
        }
        this.endText();
        return false;
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
            System.out.println("Sorry, that was not a valid name. Please try again.");
            System.out.println();
        }
        if (get.equals("begin")) {
            System.out.println("Starting game...");
            currentChapter = 1;
            currentAct[currentChapter-1] = 1;
            latestChapter = 1;
            latestActs[latestChapter-1] = 1;
            if (x) {
                currentChapter = 99;
            }
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
    public boolean advancedGameLoop(Map map, Player player, int messageCounter, String message, int endBoardX, int endBoardY, int endX, int endY, int battleX, int battleY) {
        Board thisBoard = map.getCurrentBoard();
        String get = "";
        boolean[] boolArray = new boolean[2];
        thisBoard.printBoard();
        boolean canPrintBoard = true;
        int counter = 0;
        int boardCounter;
        // false false -> battle again
        // true false -> good, continue
        // false true -> retart from last act
        while (true) {
            Board board = map.getCurrentBoard();
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
            prompt.displayActions(map, player,this);
            System.out.println();

            get = input.nextLine();
            System.out.println();
            boardCounter = map.getBoardChangeCounter();
            boolArray = prompt.doAction(get, map, player, this);
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

            if (map.getCurrentBoardX()==endBoardX && map.getCurrentBoardY()==endBoardY) {
                if (board.getCharPosX() == endX && board.getCharPosY() == endY) {
                    break;
                }
            }
            if (canPrintBoard) {
                board = map.getCurrentBoard();
                board.printBoard();
                if (map.getBoardChangeCounter() != boardCounter) {
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
    public void chapOneActOne() {
        Map moop = new Map(5, 5);

        Map meep = new Map(5, 5);
        Board boardDaltonHall = new Board("a", moop, "Apartment Hallway");
        Board boardDaltonRoom = new Board("a", meep, "Dalton's Room");
        moop.addBoard(boardDaltonHall, 5, 5);
        meep.addBoard(boardDaltonRoom, 5, 5);

        setBoard(boardDaltonRoom);
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
        player.moveSouth(boardDaltonRoom, this);
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
        if (!advancedGameLoop(meep, player, 0, message, 5, 5, 6, 5, -1, -1)) return;
        if (!advancedGameLoop(meep, player, 9, message2, 5, 5, 2, 6, -1, -1)) return;

        //-----------------------board change------------------------


        setBoard(boardDaltonHall);
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

        if (!advancedGameLoop(moop, player, -1, "", 5, 5, 2, 7, -1, hobo.currentY-1)) return;
        player.setHobo(null);
        if (!advancedGameLoop(moop, player, -1, "", 5, 5, 2, 7, -1, -1)) return;
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

        // make the map now

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

    }

    //---------------------------------------

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
        while(true) {
            firstGet = input.nextLine();
            if (firstGet.equals("Get Up")) {
                break;
            }
            else {System.out.println("Game Tip: Not quite, you need to type each action *word for word* and without the '-', try again.");}
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

        advancedGameLoop(map, currentPlayer, 0, "", 2 , 4, 5, 9, -1 , -1);
        currentPlayer.moveSouth(currentBoard, this);

        System.out.println("You step off your raft and move to the next room.");
        System.out.println();
        time(3);

        System.out.println("You see that the water continued and formed sort of a reverse pond, as in it surrounded a patch of space, and in the center of that patch of space was a building that looked like a shrine of sorts.");
        System.out.println();
        time(6);

        advancedGameLoop(map, currentPlayer, 0, "", 2, 5, 4, 6, -1, -1);

        System.out.println();
        System.out.println("...");
        System.out.println();
        time(3);



        System.out.println("Upon entering the shrine, you could feel the world around you shake a bit, in the center of the shrine there was a small container.");
        System.out.println();
        time(5);

        System.out.println("Take the container and return back to the Room Where it Happened");
        System.out.println();
        time(4);

        advancedGameLoop(map, currentPlayer, 0, "", 5,3,1,6,-1,-1);
    }

    public void designLeftLung(Board leftLung, Space space, Floor floor) {
        leftLung.buildRectRoom(0, 0, 9, 0, 9, 9, 0, 9, -1, -1, Direction.NONE);
        WindyTree f = new  WindyTree("F", "Windy Tree");
        leftLung.addNode(8,5,f);
        leftLung.addNode(7,5,f);
        leftLung.addNode(4,5,f);
        leftLung.addNode(3,5,f);
        leftLung.addNode(2,5,f);
        leftLung.addNode(1,1,f);
        leftLung.addNode(2,1,space);
        leftLung.addNode(3,1,space);
        leftLung.addNode(4,1,space);
        leftLung.addNode(5,1,f);
        leftLung.addNode(6,1,f);
        leftLung.addNode(7,1,space);
        leftLung.addNode(8,1,space);
        leftLung.addNode(0,2,floor);
        leftLung.addNode(1,2,floor);
        leftLung.addNode(2,2,f);
        leftLung.addNode(3,2,f);
        leftLung.addNode(4,2,f);
        leftLung.addNode(5,2,floor);
        leftLung.addNode(6,2,floor);
        leftLung.addNode(7,2,f);
        leftLung.addNode(8,2,space);
        leftLung.addNode(1,3,floor);
        leftLung.addNode(2,3,floor);
        leftLung.addNode(3,3,floor);
        leftLung.addNode(4,3,floor);
        leftLung.addNode(5,3,floor);
        leftLung.addNode(6,3,f);
        leftLung.addNode(7,3,space);
        leftLung.addNode(8,3,space);
        leftLung.addNode(1,4,f);
        leftLung.addNode(2,4,floor);
        leftLung.addNode(3,4,floor);
        leftLung.addNode(4,4,f);
        leftLung.addNode(5,4,floor);
        leftLung.addNode(6,4,f);
        leftLung.addNode(7,4,space);
        leftLung.addNode(8,4,space);
        leftLung.addNode(1,5,floor);
        leftLung.addNode(2,5,f);
        leftLung.addNode(3,5,f);
        leftLung.addNode(4,5,f);
        leftLung.addNode(5,5,floor);
        leftLung.addNode(6,5,floor);
        leftLung.addNode(7,5,f);
        leftLung.addNode(8,5,f);
        leftLung.addNode(1,6,floor);
        leftLung.addNode(2,6,floor);
        leftLung.addNode(3,6,floor);
        leftLung.addNode(4,6,floor);
        leftLung.addNode(5,6,floor);
        leftLung.addNode(6,6,floor);
        leftLung.addNode(7,6,floor);
        leftLung.addNode(8,6,floor);
        leftLung.addNode(9,6,floor);
        leftLung.addNode(1,7,f);
        leftLung.addNode(2,7,floor);
        leftLung.addNode(3,7,floor);
        leftLung.addNode(4,7,f);
        leftLung.addNode(5,7,f);
        leftLung.addNode(6,7,f);
        leftLung.addNode(7,7,f);
        leftLung.addNode(8,7,f);
        leftLung.addNode(1,8,space);
        leftLung.addNode(2,8,f);
        leftLung.addNode(3,8,f);
        leftLung.addNode(4,8,space);
        leftLung.addNode(5,8,space);
        leftLung.addNode(6,8,space);
        leftLung.addNode(7,8,space);
        leftLung.addNode(8,8,space);
        //6, 2
        Item previewDinnerItem = new Item("Preview Dinner", "This was the day that I got to see you after a long summer of you being at UF without me!. Thank you for making my preview special by showing up and cooking us a yummy dinner. I loved seeing you then and I love seeing you now !");
        ToriObject previewDinner = new ToriObject("S", "A Steak Dinner", previewDinnerItem, 6, 2);
        leftLung.addNode(6,2,previewDinner);
        Item wallEItem = new Item("Movie Disk", "This movie disk is to WallE, and this was the movie we watched in bed that one night, its my favorite Pixar movie but you made it even more amazing in my eyes. My eeeeeva <3. ");
        ToriObject wallE = new ToriObject("W", "A Movie Disk", wallEItem, 1, 5);
        leftLung.addNode(1, 5, wallE);
    }
    public void designLeftShoulderConnector(Board leftShoulderConnector, Space space, Floor floor) {
        leftShoulderConnector.buildRectRoom(0, 0, 9, 0, 9, 9, 0, 9, -1, -1, Direction.NONE);
        leftShoulderConnector.buildRectRoom(4, 5, 6 ,5, 6, 7, 4 ,7, 5, 5, Direction.NORTH);
        leftShoulderConnector.addNode(9, 2, floor);
        leftShoulderConnector.addNode(0, 5, floor);
        Item bobertaItem = new Item("Boberta", "The character made by the girl that I fell in love with. Ever since then I would smile each time I saw one on a whiteboard around the school :)");
        ToriObject boberta = new ToriObject("B", "A Figurine", bobertaItem, 5, 6);
        leftShoulderConnector.addNode(5, 6, boberta);
        WindyTree tree = new WindyTree("Y", "Tall Tree");
        leftShoulderConnector.addNode(2, 2, tree);
        leftShoulderConnector.addNode(4, 3,tree);
        leftShoulderConnector.addNode(7, 4, tree);
        leftShoulderConnector.addNode(2, 6, tree);
        leftShoulderConnector.addNode(7, 8, tree);
        leftShoulderConnector.addNode(6, 1, tree);
        leftShoulderConnector.addNode(1, 8, tree);
        Item drawingItem = new Item("Some Drawings", "The drawings of us that you made and drew at the beginning of our relationship that are so funny and I love so much. Thank you for giving our relationship personality and I am not that round >:O (JK I am, I am eating orange chicken as I write this)." );
        ToriObject  drawing = new ToriObject("O", "Drawings", drawingItem, 4,8);
        leftShoulderConnector.addNode(4, 8, drawing);



    }
    public void designLeftShoulder(Board leftShoulder, Floor floor) {
        leftShoulder.buildRectRoom(0, 0, 9, 0, 9, 9, 0, 9, -1, -1, Direction.NONE);
        leftShoulder.buildRectRoom(0,0,3,0,3,9,0,9,3,2,Direction.EAST);
        leftShoulder.buildRectRoom(0,0,9,0,9,3,0,3,4,3,Direction.SOUTH);
        leftShoulder.buildRectRoom(6,7, 9,7,9,9,6,9,-1,-1,Direction.NONE);
        Door dooor = new Door(false, false);
        Door doior = new Door(false, false);
        leftShoulder.addNode(3,5,dooor);
        Wall wall = new Wall();
        leftShoulder.addNode(6,1,wall);
        leftShoulder.addNode(6,2,doior);
        leftShoulder.addNode(9,5,floor);
        leftShoulder.addNode(4,9,floor);
        Item kikiItem = new Item("Movie Ticket", "This movie ticket is to Kiki's Delivery Service, and this was the first Studio Ghibli movie that we saw in theatres that I loved so much. Not only did I begin to see why so many people liked Studio Ghibli movies, but also it brought me closer to you :}. Also I like the cat :)");
        ToriObject kiki = new ToriObject("K", "A Movie Ticket", kikiItem, 1, 5);
        leftShoulder.addNode(1,5,kiki);
        Item puddles = new Item("Puddles!", "The lovable first ducky!. Puddles, the funny, protective, round, squishible, lazy, and bestest ducky anyone could ever ask for! I love Puddles so so so so so so so so so much and I love the way he has worked his way into our relationship. Thank you for loving plushies like him, it makes our relationship, and most importantly YOU, special :)");
        ToriObject puddle = new ToriObject("P", "A Duck Plush", puddles, 8, 1);
        leftShoulder.addNode(8,1,puddle);

    }
    public void designLeftArm(Board a, Space s, Floor f, River r) {
        Item promFotoItem = new Item("2025 Prom Photo", "This is our first real photo together [unfortunately Shane and Thomas were in it]. But it was then when I realized how pretty you were and I began to have more of a crush on you waaaahhhh.");
        ToriObject promFoto = new ToriObject("P", "Photograph", promFotoItem, 2, 4);

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
        a.addNode(1, y,s);
        a.addNode(2, y,r);
        a.addNode(3, y,r);
        a.addNode(4, y,r);
        a.addNode(5, y,r);
        a.addNode(6, y,s);
        a.addNode(7, y,s);
        a.addNode(8, y,s);
        y++;//3
        a.addNode(1, y,r);
        a.addNode(2, y,r);
        a.addNode(3, y,r);
        a.addNode(4, y,s);
        a.addNode(5, y,r);
        a.addNode(6, y,r);
        a.addNode(7, y,s);
        a.addNode(8, y,s);
        y++;//4
        a.addNode(1, y,r);
        a.addNode(2, y,promFoto);
        a.addNode(3, y,r);
        a.addNode(4, y,s);
        a.addNode(5, y,s);
        a.addNode(6, y,r);
        a.addNode(7, y,r);
        a.addNode(8, y,s);
        y++; //5
        a.addNode(1, y,r);
        a.addNode(2, y,r);
        a.addNode(3, y,s);
        a.addNode(4, y,s);
        a.addNode(5, y,s);
        a.addNode(6, y,s);
        a.addNode(7, y,r);
        a.addNode(8, y,r);
        y++; //6
        a.addNode(1, y,s);
        a.addNode(2, y,r);
        a.addNode(3, y,r);
        a.addNode(4, y,s);
        a.addNode(5, y,s);
        a.addNode(6, y,s);
        a.addNode(7, y,r);
        a.addNode(8, y,r);
        y++;//7
        a.addNode(1, y,s);
        a.addNode(2, y,s);
        a.addNode(3, y,r);
        a.addNode(4, y,r);
        a.addNode(5, y,s);
        a.addNode(6, y,r);
        a.addNode(7, y,r);
        a.addNode(8, y,s);
        y++;//8
        a.addNode(1, y,s);
        a.addNode(2, y,s);
        a.addNode(3, y,s);
        a.addNode(4, y,r);
        a.addNode(5, y,r);
        a.addNode(6, y,r);
        a.addNode(7, y,s);
        a.addNode(8, y,s);

        a.addNode(4,0,f);
        a.addNode(5,9,f);



    }
    public void designLeftHand(Board h, Space s, Floor f, River r) {
        Door shrineDoor = new Door(false, false);
        Wall w = new Wall();
        Item memCont1 = new Item("Memory Container One", "This Memory Container is currently locked. Please upload to the Brain and access on the Terminal.");
        ToriObject memoryContainer1 = new ToriObject("M", "Memory Container", memCont1, 4, 7);
        h.addNode(0,4,f);
        for (int y = 1; y <= 8; y++) {
            for (int x = 1; x <= 8; x++) {
                if (y==3 && (x == 2 || x ==3 || x==5 || x==6)) h.addNode(x, y, s);
                else if (y >= 4) h.addNode(8, y, r);
                else if (x!=4) h.addNode(x, y, r);
            }
        }

        
        int y = 4; //4
        h.addNode(1,y, s);
        h.addNode(2,y, s);
        h.addNode(3,y, s);
        h.addNode(4,y, shrineDoor);
        h.addNode(5,y, s);
        h.addNode(6,y, s);
        h.addNode(7,y, s);
        y++; //5
        h.addNode(1,y, s);
        h.addNode(2,y, s);
        h.addNode(3,y, w);
        h.addNode(5,y, w);
        h.addNode(6,y, s);
        h.addNode(7,y, s);
        y++; //6
        h.addNode(1,y, s);
        h.addNode(2,y, w);
        h.addNode(6,y, w);
        h.addNode(7,y, s);
        y++; //7
        h.addNode(1,y, s);
        h.addNode(2,y, w);
        h.addNode(4,y, memoryContainer1);
        h.addNode(6,y, w);
        h.addNode(7,y, s);
        y++; //8
        h.addNode(1,y, s);
        h.addNode(2,y, s);
        h.addNode(3,y, w);
        h.addNode(5,y, w);
        h.addNode(6,y, s);
        h.addNode(7,y, r);
    }
    public void addHeart(Board heart, Space space) {
        Heart heartPiece = new Heart();
        heart.addNode(3,2, heartPiece);
        heart.addNode(4,3, heartPiece);
        heart.addNode(5,3, heartPiece);
        heart.addNode(6,2, heartPiece);
        heart.addNode(7,3, heartPiece);
        heart.addNode(7,4, heartPiece);
        heart.addNode(6,5, heartPiece);
        heart.addNode(5,6, heartPiece);
        heart.addNode(4,6, heartPiece);
        heart.addNode(3,5, heartPiece);
        heart.addNode(2,4, heartPiece);
        heart.addNode(2,3, heartPiece);
        heart.addNode(3,3,space);
        heart.addNode(3,4,space);
        heart.addNode(4,4,space);
        heart.addNode(5,4,space);
        heart.addNode(6,4,space);
        heart.addNode(6,3,space);
        heart.addNode(4,5,space);
        heart.addNode(5,5,space);
    }

    public void toriGame() {
        System.out.println("Welcome to Tori's Wonderland");
        time(3);
        Floor floor = new Floor();
        System.out.println();

        //create the Map:

        Map toriMap = new Map(5, 3);

        Board leftHand = new Board("[", toriMap, "Left Corridor");
        Board leftArm = new Board("|", toriMap, "Left Hallway");
        Board leftShoulder = new Board("|", toriMap, "Left Bend");
        Board leftShoulderConnector = new  Board("|", toriMap, "Left Field");
        Board leftLung = new  Board("|", toriMap, "Windy Forest Left");
        toriMap.addBoard(leftHand, 2, 5);
        toriMap.addBoard(leftArm, 2, 4);
        toriMap.addBoard(leftShoulder, 2, 3);
        toriMap.addBoard(leftShoulderConnector, 3, 3);
        toriMap.addBoard(leftLung, 4, 3);

        Board rightHand = new Board("]", toriMap, "Right Corridor");
        Board rightArm = new Board("|", toriMap, "Right Hallway Two");
        Board rightShoulder = new Board("|", toriMap, "Right Bend");
        Board rightShoulderConnector = new  Board("|", toriMap, "Right Hallway One");
        Board rightLung = new  Board("|", toriMap, "Windy Forest Right");
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
        Board brainRight = new  Board("|", toriMap, "Right Processing Unit");
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
        Board lowerStomach =  new Board("|", toriMap, "Delivery Bay");
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
        Board rightLeg = new  Board("|", toriMap, "Lower Lands Hallway Right");
        Board rightFoot = new  Board("[", toriMap, "Lower Lands Corridor Right");
        toriMap.addBoard(rightKnee, 7, 7);
        toriMap.addBoard(rightLeg, 7, 8);
        toriMap.addBoard(rightFoot, 7, 9);

        Board leftKnee = new Board("|", toriMap, "Lower Lands Bend Left");
        Board leftLeg = new  Board("|", toriMap, "Lower Lands Hallway Left");
        Board leftFoot = new  Board("]", toriMap, "Lower Lands Corridor Left");
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



        HeartTerminal terminal = new HeartTerminal();
        heart.addNode(7,8, terminal);

        //design left
        designLeftLung(leftLung, space, floor);
        designLeftShoulderConnector(leftShoulderConnector, space, floor);
        designLeftShoulder(leftShoulder, floor);
        designLeftArm(leftArm, space, floor, river);


        Player toriPlayer = new Player("T", "Tori", floor);
        heart.addNode(2, 7, toriPlayer);
        currentPlayer = toriPlayer;

        toriGameActZero(toriMap);
        toriPlayer.moveWest(currentBoard, this);
        toriGameActOne(toriMap);


    }


}

