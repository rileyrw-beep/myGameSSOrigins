import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;

public class HeartTerminal implements Nodeable {
    private String displayid;
    private String inGameid;
    private boolean canMoveTo;
    private ListOfNodes type;
    private ArrayList<String> actionList;
    private int counter;

    public HeartTerminal() {
        displayid = "+";
        inGameid = "Terminal";
        type = ListOfNodes.WALL;
        canMoveTo = false;
        actionList = new ArrayList<String>();
        actionList.add("Turn On");
        counter =0;
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
        //implement now!
        boolean[] returnArray = {true, false};
        if (response.equals("Turn On")) {
            System.out.println();
            System.out.println("...");
            System.out.println();
            game.time(3);

            if (counter==0){
                System.out.println("You walk over to the terminal, its interface seemed foreign yet simple.");
                System.out.println();
                game.time(3);

                System.out.println("It was covered in dust, which you attempted to wipe away.");
                System.out.println();
                game.time(3);

                System.out.println("It seemed as if it hasn't been used right in ages.");
                System.out.println();
                game.time(3);

                System.out.println("You press the large circular button in the center and the screen lights up.");
                System.out.println();
                game.time(3);

                System.out.println("It displayed multiple directories the system stored:");
                System.out.println();
                game.time(3);
                counter++;
            }


            ArrayList<String> terminalDirectories = new ArrayList<String>();
            terminalDirectories.add("Logs");
            terminalDirectories.add("Reboot");
            terminalDirectories.add("Exit");

            String terminalInput = game.basicGameLoop("",  terminalDirectories);
            switch (terminalInput) {
                case "Logs":
                    System.out.println();
                    System.out.println("...");
                    System.out.println();
                    game.time(3);

                    System.out.println("You enter the Logs directory and it displays the all previous saved logs.");
                    System.out.println();
                    game.time(4);

                    System.out.println("However, all the previous logs seemed to be corrupted.");
                    System.out.println();
                    game.time(3);
                    return returnArray;

                case ("Reboot"):
                    if (checkReboot(game.getPlayer())) {
                        // do reboot things
                        System.out.println("yay");
                    }
                    else {
                        System.out.println("The terminal shakes violently before displaying: \\ERROR\\ SUFFICIENT LOGS NOT FOUND.");
                        System.out.println();
                        game.time(4);
                    }
                case ("Exit"):
                    return returnArray;
            }
        }
        returnArray[0] = false;
        return returnArray;
    }

    public boolean checkReboot(Player player) {
        return false;
    }
}
