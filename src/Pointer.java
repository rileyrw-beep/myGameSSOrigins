import java.util.ArrayList;

public class Pointer implements Nodeable {
    private String displayid;
    private String inGameid;
    private boolean canMoveTo;
    private ListOfNodes type;
    private ArrayList<String> actionList;
    private Reference ref;

    public Pointer(Reference ref) {
        displayid = "*";
        inGameid = "Pointer";
        type = ListOfNodes.NPC;
        canMoveTo = false;
        actionList = new ArrayList<String>();
        actionList.add("Use");
        this.ref = ref;
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
        if (response.equals("Use")) {
            game.getPlayer().teleport(board, game, ref.getX(), ref.getY());
            return returnArray;
        } else {
            returnArray[0] = false;
            return returnArray;
        }
    }
}
