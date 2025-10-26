import java.util.ArrayList;


public class MysteriousMan implements Nodeable {

    private String displayid;
    private String inGameid;
    private boolean canMoveTo;
    private ListOfNodes type;
    private ArrayList<String> actionList;

    public MysteriousMan() {
        displayid = "M";
        inGameid = "Mysterious Man";
        type = ListOfNodes.WALL;
        canMoveTo = false;
        actionList = new ArrayList<String>();
        actionList.add("Talk to Mysterious Man");
    }

    public String getDisplayid() {
        return displayid;
    }

    public String getInGameid() {
        return inGameid;
    }

    public boolean getCanMoveTo() {
        return canMoveTo;
    }

    public ListOfNodes getType() {
        return type;
    }

    public ArrayList<String> getActionList() {
        return actionList;
    }

    public boolean[] performAction(String response, Board board, Game game) {
        boolean[] returnArray = {true, false};


        if (board.getMap().getCurrentBoardX() == 6 && board.getMap().getCurrentBoardY() == 5) {
            System.out.println("...");
            System.out.println();
            game.time(3);

            System.out.println("You walk up to this mysterious man shrouded in darkness with the intent to talk to him.");
            System.out.println();
            game.time(5);

            System.out.println("Hold up, are you really going to talk to this menacing stranger?");
            System.out.println();
            game.time(4);

            ArrayList<String> optionList = new ArrayList<>();
            optionList.add("Hell Yeah");
            optionList.add("Actually Maybe Not");

            String get = game.basicGameLoop("", optionList);
            if (get.equals("Actually Maybe Not")) {
                System.out.println("Good choice, you got to get to the Tech Fair. No more distractions!");
                System.out.println();
                game.time(3);
                return returnArray;
            }

            System.out.println("Well, you really should be going to the Tech Fair, but alas you decide to walk up to this random guy who could secretly be trying to kill you.");
            System.out.println();
            game.time(7);

            System.out.println("Dalton * 'Yo whats good?'");
            System.out.println();
            game.time(3);

            System.out.println("Mysterious Man * 'Hello there, would you like some free candy?'");
            System.out.println();
            game.time(3);

            System.out.println("Dalton * 'Bet!'");
            System.out.println();
            game.time(3);

            System.out.println("Hold up, are you an idiot? Why would you accept free candy from a stranger.");
            System.out.println();
            game.time(4);

            optionList.clear();
            optionList.add("Cause its fun");
            optionList.add("Yeah you're right I shouldn't");

            int getInt = game.basicGameLoop(optionList);
            if (getInt == 2) {
                System.out.println("Good job, you politely decline the mysterious man's offer and continue on your way to the Tech Fair");
                System.out.println();
                game.time(4);
                return returnArray;
            }

            System.out.println("Okay fine, but don't say I didn't warn you.");
            System.out.println();
            game.time(3);

            System.out.println("You accept his free candy and you see its the kind that comes along with a toy hidden in some chocolate.");
            System.out.println();
            game.time(5);

            System.out.println("You excitedly open it and find an oddly shaped toy bus called 'The Ribcage'");
            System.out.println();
            game.time(3);

            System.out.println("You thank the Mysterious Man, put The Ribcage in your pocket, and turn to leave.");
            System.out.println();
            game.time(4);

            Item ribcage = new Item("Toy Ribcage", "An oddly shaped bus with more wheels than seats. \nIt looks completely non-functional but nevertheless you decide to keep it. \nGiven to you by a mysterious man");
            game.getPlayer().getInventory().addItem(ribcage);

            System.out.println("Added 'Toy Ribcage' to Inventory");
            System.out.println();
            game.time(2);

            actionList.clear();
            return returnArray;
        }
        else if (board.getMap().getCurrentBoardX() == 4 && board.getMap().getCurrentBoardY() == 5) {
            System.out.println("...");
            System.out.println();
            game.time(3);

            System.out.println("You walk up to this mysterious man shrouded in darkness with the intent to talk to him.");
            System.out.println();
            game.time(5);

            System.out.println("Hold up, are you really going to talk to this menacing stranger?");
            System.out.println();
            game.time(4);

            ArrayList<String> optionList = new ArrayList<>();
            optionList.add("Hell Yeah");
            optionList.add("Actually Maybe Not");

            String get = game.basicGameLoop("", optionList);
            if (get.equals("Actually Maybe Not")) {
                System.out.println("Good choice, you got to get to the Tech Fair. No more distractions!");
                System.out.println();
                game.time(3);
                return returnArray;
            }

            System.out.println("Well, you really should be going to the Tech Fair, but alas you decide to walk up to this random guy who could secretly be trying to kill you.");
            System.out.println();
            game.time(7);

            System.out.println("Dalton * 'Yo whats good?'");
            System.out.println();
            game.time(3);

            System.out.println("Mysterious Man * 'Hello there, would you like some free candy?'");
            System.out.println();
            game.time(3);

            System.out.println("Dalton * 'Bet!'");
            System.out.println();
            game.time(3);

            System.out.println("Hold up, are you an idiot? Why would you accept free candy from a stranger.");
            System.out.println();
            game.time(4);

            optionList.clear();
            optionList.add("Cause its fun");
            optionList.add("Yeah you're right I shouldn't");

            int getInt = game.basicGameLoop(optionList);
            if (getInt == 2) {
                System.out.println("Good job, you politely decline the mysterious man's offer and continue on your way to the Tech Fair");
                System.out.println();
                game.time(5);
                return returnArray;
            }

            System.out.println("Okay fine, but don't say I didn't warn you.");
            System.out.println();
            game.time(3);

            System.out.println("You hold out your hand to accept the candy when suddenly a white bus pulls up behind you.");
            System.out.println();
            game.time(4);

            System.out.println("4 Men in black jump out and throw you in the back.");
            System.out.println();
            game.time(3);

            System.out.println("You end up on the local news as the guy who got molested and got no candy.");
            System.out.println();
            game.time(4);

            System.out.println("Don't say I did not warn you.");
            System.out.println();
            game.time(3);

            return game.gameOver("You eventually escape your kidnappers, mentally scarred for life. Unfortunately you missed the Tech Fair, but I feel like that is the least of your worries right now.", false, Ending.BAD_ENDING);
        }

        System.out.println("How are you talking to this guy, hes not supposed to be here");
        throw new RuntimeException();

    }
}
