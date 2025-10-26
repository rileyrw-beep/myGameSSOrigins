import java.util.ArrayList;

public interface Nodeable {


    String getDisplayid();

    String getInGameid();

    boolean getCanMoveTo();

    ListOfNodes getType();

    ArrayList<String> getActionList();

    boolean[] performAction(String response, Board board, Game game);




}