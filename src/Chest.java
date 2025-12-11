import java.util.ArrayList;

public class Chest implements Nodeable{
    private String displayid;
    private String inGameid;
    private boolean canMoveTo;
    private ListOfNodes type;
    private ArrayList<String> actionList;
    private Inventory inventory;

    public Chest(Item item) {
        displayid = "C";
        this.inGameid = "Chest";
        type = ListOfNodes.DESK;
        canMoveTo = false;
        actionList = new ArrayList<String>();
        actionList.add("Open Chest");
        inventory = new Inventory();
        inventory.addItem(item);
    }

    public Chest() {
        displayid = "C";
        inGameid = "Chest";
        type = ListOfNodes.DESK;
        canMoveTo = false;
        actionList = new ArrayList<String>();
        actionList.add("Open Chest");
        inventory = new Inventory();
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
        if (response.equals("Open Chest")) {
            System.out.println("You open the chest!");
            System.out.println();
            game.time(3);

            if (inventory.getItems().isEmpty()) {
                System.out.println("Theres nothing in it D:");
                System.out.println();
                game.time(3);
            }
            else {
                System.out.println("There's something in it :D");
                System.out.println();
                game.time(2);

                System.out.print("You found a ");
                game.time(2);

                System.out.println(inventory.getItem().getItemName() + "!");
                System.out.println();
                game.time(3);

                game.getPlayer().getInventory().addItem(inventory.getItem());
                System.out.println("Added " + inventory.getItem().getItemName() + " to your inventory.");
                System.out.println();
                game.time(3);
            }
            actionList.clear();
            displayid = "O";
            inGameid = "Opened Chest";
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
