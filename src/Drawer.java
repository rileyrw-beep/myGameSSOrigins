import java.sql.SQLOutput;
import java.util.*;

public class Drawer implements Nodeable{
    private String displayid;
    private String inGameid;
    private boolean canMoveTo;
    private ListOfNodes type;
    private ArrayList<String> actionList;
    private Inventory inventory;

    public Drawer(String inGameid, Item item) {
        displayid = "#";
        this.inGameid = inGameid;
        type = ListOfNodes.DESK;
        canMoveTo = false;
        actionList = new ArrayList<String>();
        actionList.add("Open Drawer");
        inventory = new Inventory();
        inventory.addItem(item);
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
        if (response.equals("Open Drawer")) {
            System.out.println();
            System.out.println("...");
            System.out.println();
            game.time(3);

            System.out.println("You approach the drawer, it came with the apartment and you never really touched it. ");
            System.out.println();
            game.time(3);

            System.out.println("You open it and a wave of dust flies into your face. After a coughing fit and a breath from your inhaler you look inside.");
            System.out.println();
            game.time(3);

            System.out.println("Inside there is an opened, half drunken can of soda. You put it in your pocket");
            System.out.println();
            game.time(2);

            game.getPlayer().getInventory().addItem(inventory.getItems().getFirst());
            System.out.println("Added 'Crazy Soda' to Inventory");
            System.out.println();

            actionList.clear();
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
