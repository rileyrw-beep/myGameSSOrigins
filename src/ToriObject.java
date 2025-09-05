import java.util.ArrayList;

public class ToriObject implements Nodeable {
    private String displayid;
    private String inGameid;
    private boolean canMoveTo;
    private ListOfNodes type;
    private ArrayList<String> actionList;
    private Inventory inventory;
    private String name;
    private int x;
    private int y;

    public ToriObject (String displayid, String name, Item item, int x, int y) {
        this.displayid = displayid;
        this.inGameid = "Object";
        this.name = name;
        type = ListOfNodes.DESK;
        canMoveTo = false;
        actionList = new ArrayList<String>();
        actionList.add("Pick Up");
        inventory = new Inventory();
        inventory.addItem(item);
        this.x = x;
        this.y = y;
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
        if (response.equals("Pick Up")) {
            System.out.println();
            System.out.println("...");
            System.out.println();
            game.time(3);

            boolean isInWater = board.getBoard().get(y-1).get(x).getDisplayid().equals("~") || board.getBoard().get(y+1).get(x).getDisplayid().equals("~") || board.getBoard().get(y).get(x+1).getDisplayid().equals("~") || board.getBoard().get(y).get(x-1).getDisplayid().equals("~");

            if (isInWater) {
                System.out.println("You approach the object floating in the sea and see that it is " + name + ".");
            }
            else {
                System.out.println("You approach the object on the ground and see that it is " + name + ".");
            }
            System.out.println();
            game.time(3);

            if (name.equals("Memory Container")) System.out.println("You wonder what is inside before putting it in your pocket.");
            else System.out.println("It reminds you of a memory and you smile :) before putting it in your pocket.");

            System.out.println();
            game.time(3);

            System.out.println("Open your inventory to read more about the object.");
            System.out.println();
            game.time(2);

            game.getPlayer().getInventory().addItem(inventory.getItems().getFirst());
            System.out.println("Added " + name + " to Inventory");
            System.out.println();

            actionList.clear();
            if (isInWater) {
                River river = new River();
                board.addNode(x, y, river);

            }
            else {
                Floor floor = new Floor();
                board.addNode(x, y, floor);

            }
            return returnArray;
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
