import java.util.*;

public class Desk implements Nodeable{
    private String displayid;
    private String inGameid;
    private boolean canMoveTo;
    private ListOfNodes type;
    private ArrayList<String> actionList;
    private int deskCounter;

    public Desk() {
        displayid = "+";
        inGameid = "Dalton's Desk";
        type = ListOfNodes.DESK;
        canMoveTo = false;
        actionList = new ArrayList<String>();
        actionList.add("Sit Down at Desk");
        deskCounter = 0;
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

    public boolean[] performAction(String response, Board board, Game game) {
        boolean[] returnArray = {true, false};
        ArrayList<String> optionList = new ArrayList<String>();
        optionList.add("Yes");
        optionList.add("No");

        if (response.equals("Sit Down at Desk")) {
            if (deskCounter == 0) {
                optionList.clear();
                deskCounter++;
                System.out.println();
                System.out.println("...");
                game.time(3);
                System.out.println();

                System.out.println("You look over at your desk, and on it your prized possessions: your PS6 and your gaming PC.");
                System.out.println();
                game.time(3);

                System.out.println("The desk and chair itself were a little musty. The desk was filled with empty containers from Raising Cane's, and the chair's back was breaking a little.");
                System.out.println();
                game.time(4);

                System.out.println("While you exclusively play on the PS6, you use the PC to build Hype 2 updates and stream.");
                System.out.println();
                game.time(3);

                System.out.println("Speaking of Hype 2, you remember that you did not finish the update you were working on last night, and you have the urge to finish it.");
                System.out.println();
                game.time(4);

                System.out.println("But you cannot do that right now, you need to get to the tech fair. You should head there now.");
                System.out.println();
                game.time(2);

                optionList.add("Sit Down at Desk");
                optionList.add("Continue to the Fair");
                String get = game.basicGameLoop("", optionList);
                optionList.clear();
                optionList.add("Yes");
                optionList.add("No");
                if (get.equals("Continue to the Fair")) {
                    System.out.println("Good choice, you really need to get going. You accept you will have to build the update tonight as you turn to leave.");
                    return returnArray;
                }
            }
            if (deskCounter == 1) {
                deskCounter++;

                System.out.println();
                System.out.println("...");
                System.out.println();
                game.time(3);

                System.out.println("Like I said, you can absolutely do this another time, but alas you decide to sit down.");
                System.out.println();
                game.time(2);

                System.out.println("Be warned, if you sit down at your desk, there is a high likelihood that you do not get up.");
                System.out.println();
                game.time(2);

                System.out.println("Are you sure?");
                System.out.println();
                game.time(1);

                String get = game.basicGameLoop("", optionList);
                System.out.println();
                if (get.equals("No")) {
                    System.out.println("Good choice, you had me scared there. You accept you will have to build the update tonight as you turn to leave.");
                    return returnArray;
                }
            }
            if (deskCounter==2) {
                deskCounter++;
                System.out.println();
                System.out.println("...");
                System.out.println();
                game.time(3);

                System.out.println("Are you sure you're sure?");
                System.out.println();
                game.time(2);

                String get = game.basicGameLoop("", optionList);
                System.out.println();
                if (get.equals("No")) {
                    System.out.println("Good choice, you REALLY had me scared there. You accept you will have to build the update tonight as you turn to leave.");
                    return returnArray;
                }

            }
            if (deskCounter==3) {
                deskCounter++;
                System.out.println();
                System.out.println("...");
                System.out.println();
                game.time(3);

                System.out.println("Ok buddy, if you really, and I mean REALLY want to sit at the desk I guess I will let you.");
                System.out.println();
                game.time(3);

                System.out.println("You sit your fatass down and boot up the PC.");
                System.out.println();
                game.time(2);

                System.out.println("You open UEFN and find some left over toast in a Raising Cane's container.");
                System.out.println();
                game.time(3);
                return game.gameOver("You get sucked into building the next Hype 2 update and miss the Tech Fair", false, Ending.BAD_ENDING);
            }
        }
        returnArray[0] = false;
        return returnArray;
    }


    public ListOfNodes getType() {
        return type;
    }

    public ArrayList<String> getActionList() {
        return actionList;
    }
}
