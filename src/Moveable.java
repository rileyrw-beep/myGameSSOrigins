import java.util.*;

public interface Moveable {
    public boolean checkNorth(Board board);
    public boolean checkEast(Board board);
    public boolean checkSouth(Board board);
    public boolean checkWest(Board board);

    public void moveNorth(Board board);
    public void moveEast(Board board);
    public void moveSouth(Board board);
    public void moveWest(Board board);

}