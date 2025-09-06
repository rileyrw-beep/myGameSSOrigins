import java.util.ArrayList;

public class Reference implements Nodeable{
    private String displayid;
    private String inGameid;
    private boolean canMoveTo;
    private ListOfNodes type;
    private ArrayList<String> actionList;
    private int x;
    private int y;

    public Reference(int x, int y) {
        displayid = "&";
        inGameid = "Reference";
        type = ListOfNodes.FLOOR;
        canMoveTo = true;
        actionList = new ArrayList<String>();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
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
