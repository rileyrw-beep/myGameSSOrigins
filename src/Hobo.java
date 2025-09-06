import java.util.*;

public class Hobo implements Nodeable, Moveable {
    private String displayid;
    private String inGameid;
    private Nodeable previousNode;
    private boolean canMoveTo;
    private ListOfNodes type;
    public int currentX;
    public int currentY;




    public Hobo (Nodeable startNode, int x, int y) {
        this.displayid = "H";
        this.inGameid = "Hobo";
        previousNode = startNode;
        type = ListOfNodes.NPC;
        canMoveTo = true;
        currentX = x;
        currentY = y;
    }

    public boolean[] performAction(String response, Board board, Game game) {
         boolean[] a = {true, false};
         return a;
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

    public int getX() {
        return currentX;
    }
    public int getY() {
        return currentY;
    }

    public boolean checkSouth(Board board) {
        int x = currentX;
        int y = currentY;
        y++;
        ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();

        if (y<=9 && boardList.get(y).get(x).getCanMoveTo()) {
            return true;
        }
        return false;
    }
    public boolean checkEast(Board board) {
        int x = currentX;
        int y = currentY;
        x++;
        ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();

        if (x<=9 && boardList.get(y).get(x).getCanMoveTo()) {
            return true;
        }
        return false;
    }
    public boolean checkNorth(Board board) {
        int x = currentX;
        int y = currentY;
        y--;
        ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();

        if (y>=0 && boardList.get(y).get(x).getCanMoveTo()) {
            return true;
        }
        return false;
    }
    public boolean checkWest(Board board) {
        int x = currentX;
        int y = currentY;
        x--;
        ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();

        if (x>=0 && boardList.get(y).get(x).getCanMoveTo()) {
            return true;
        }
        return false;
    }

    public void moveSouth(Board board, Game game) {
        if(this.checkSouth(board)) {
            int x = currentX;
            int prev = currentY;
            int y = prev + 1;
            ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
            Nodeable placeholder = boardList.get(y).get(x);
            board.addNode(x, prev, previousNode);
            previousNode = placeholder;
            board.addNode(x,y,this);

            currentX = x;
            currentY = y;
            return;
        }


    }
    public void moveEast(Board board, Game game) {
        if(this.checkEast(board)) {
            int prev = currentX;
            int y = currentY;
            int x = prev + 1;
            ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
            Nodeable placeholder = boardList.get(y).get(x);
            board.addNode(prev, y, previousNode);
            previousNode = placeholder;
            board.addNode(x,y,this);
            currentX = x;
            currentY = y;
            return;
        }


    }
    public void moveNorth(Board board, Game game) {
        if(this.checkNorth(board)) {
            int x = currentX;
            int prev = currentY;
            int y = prev - 1;
            ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
            Nodeable placeholder = boardList.get(y).get(x);
            board.addNode(x, prev, previousNode);
            previousNode = placeholder;
            board.addNode(x,y,this);

            currentX = x;
            currentY = y;
            return;
        }


    }
    public void moveWest(Board board, Game game) {
        if(this.checkWest(board)) {
            int prev = currentX;
            int y = currentY;
            int x = prev - 1;
            ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
            Nodeable placeholder = boardList.get(y).get(x);
            board.addNode(prev, y, previousNode);
            previousNode = placeholder;
            board.addNode(x,y,this);

            currentX = x;
            currentY = y;
            return;
        }

    }

    public ListOfNodes getType() {
        return type;
    }

    public ArrayList<String> getActionList() {
        return new ArrayList<String>();
    }

    public boolean checkTeleport(Board board, int x, int y) {
        return true;
    }

    public void teleport(Board board, Game game, int x, int y) {
        return;
    }

}