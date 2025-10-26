import java.util.ArrayList;

public class Entrance implements Nodeable{
    private String displayid;
    private String inGameid;
    private boolean canMoveTo;
    private ListOfNodes type;
    private ArrayList<String> actionList;
    int boardX;
    int boardY;
    int x;
    int y;
    Map desiredMap;
    boolean betweenMaps;

    public Entrance(String inGameid, String action, Map map, int bX, int bY, int x, int y, boolean betweenMaps) {
        displayid = "0";
        this.inGameid = inGameid;
        type = ListOfNodes.WALL;
        canMoveTo = false;
        actionList = new ArrayList<String>();
        actionList.add(action);
        desiredMap = map;
        this.x = x;
        this.y = y;
        this.boardX = bX;
        this.boardY = bY;
        this.betweenMaps = betweenMaps;

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
        if (!response.equals("Enter") && !response.equals("Exit")) {
            returnArray[0] = false;
            return returnArray;
        }
        if (betweenMaps) {
            game.getPlayer().mapTeleport(board, desiredMap, game, boardX, boardY, x, y);
        }
        else {
            game.getPlayer().withinMapTeleport(board, game, boardX, boardY, x, y);
        }



        return returnArray;
    }
}
