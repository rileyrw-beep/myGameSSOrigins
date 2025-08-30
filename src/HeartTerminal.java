import java.util.ArrayList;

public class HeartTerminal implements Nodeable {
    private String displayid;
    private String inGameid;
    private boolean canMoveTo;
    private ListOfNodes type;
    private ArrayList<String> actionList;

    public HeartTerminal() {
        displayid = "+";
        inGameid = "Terminal";
        type = ListOfNodes.WALL;
        canMoveTo = false;
        actionList = new ArrayList<String>();
        actionList.add("Turn On");
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
        //implement laters
        boolean[] returnArray = {false, false};
        return returnArray;
    }
}
