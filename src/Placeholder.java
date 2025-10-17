import java.util.ArrayList;

public class Placeholder implements Nodeable {
    private String displayid;
    private String inGameid;
    private boolean canMoveTo;
    private ListOfNodes type;
    private ArrayList<String> actionList;

    public Placeholder() {
        displayid = "P";
        inGameid = "Placeholder";
        type = ListOfNodes.PLACEHOLDER;
        canMoveTo = false;
        actionList = new ArrayList<String>();
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
        System.out.println("asd");
        boolean[] returnArray = {false, false};
        return returnArray;
    }
}
