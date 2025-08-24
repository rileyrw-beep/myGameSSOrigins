import java.util.*;

public class Bed implements Nodeable {
    private String displayid;
    private String inGameid;
    private boolean canMoveTo;
    private ListOfNodes type;
    private ArrayList<String> actionList;


    public Bed() {
        displayid = "=";
        inGameid = "Bed";
        type = ListOfNodes.BED;
        canMoveTo = false;
        actionList = new ArrayList<String>();
        actionList.add("Get Back in Bed");

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
        if (response.equals("Get Back in Bed")) {
            System.out.println("...");
            System.out.println();
            game.time(3);
            System.out.println("You turn around and look at your ever so enticing bed.");
            System.out.println();
            game.time(2);
            System.out.println("It really was just an air mattress, but for some odd reason you really like them.");
            System.out.println();
            game.time(3);
            System.out.println("Its warm blankets call out to, wanting you to jump back in and sleep a while.");
            System.out.println();
            game.time(3);
            System.out.println("But no, you need to get to the Tech Fair at Nelson's Labs.");
            System.out.println();
            game.time(2);
            actionList.clear();
        }

        return returnArray;
    }

    public ListOfNodes getType() {
        return type;
    }

    public ArrayList<String> getActionList() {
        return actionList;
    }
}
