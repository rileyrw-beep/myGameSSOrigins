import java.util.*;

public class Player implements Nodeable, Moveable {
    private String displayid;
    private String inGameid;
    private Nodeable previousNode;
    private boolean canMoveTo;
    private ListOfNodes type;
    private Inventory inventory;
    private Hobo hobo;



    public Player (String displayid, String inGameid, Nodeable startNode) {
        this.displayid = displayid;
        this.inGameid = inGameid;
        previousNode = startNode;
        type = ListOfNodes.PLAYER;
        canMoveTo = false;
        inventory = new Inventory();
        hobo = null;

    }

    public boolean[] performAction(String response, Board board, Game game) {
        boolean[] returnArray = {true, false};
        if (response.equals("Move North") && this.checkNorth(board)) {
            this.moveNorth(board);
            return returnArray;
        }
        else if (response.equals("Move East") && this.checkEast(board)) {
            this.moveEast(board);
            return returnArray;
        }
        else if (response.equals("Move South") && this.checkSouth(board)) {
            this.moveSouth(board);
            return returnArray;
        }
        else if (response.equals("Move West") && this.checkWest(board)) {
            this.moveWest(board);
            return returnArray;
        }
        else {
            returnArray[0] = false;
            return returnArray;
        }
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

    public boolean checkSouth(Board board) {
        int x = board.getCharPosX();
        int y = board.getCharPosY();
        y++;
        ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();

        if (y<=9 && boardList.get(y).get(x).getCanMoveTo()) {
            return true;
        }
        return false;
    }
    public boolean checkEast(Board board) {
        int x = board.getCharPosX();
        int y = board.getCharPosY();
        x++;
        ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();

        if (x<=9 && boardList.get(y).get(x).getCanMoveTo()) {
            return true;
        }
        return false;
    }
    public boolean checkNorth(Board board) {
        int x = board.getCharPosX();
        int y = board.getCharPosY();
        y--;
        ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();

        if (y>=0 && boardList.get(y).get(x).getCanMoveTo()) {
            return true;
        }
        return false;
    }
    public boolean checkWest(Board board) {
        int x = board.getCharPosX();
        int y = board.getCharPosY();
        x--;
        ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();

        if (x>=0 && boardList.get(y).get(x).getCanMoveTo()) {
            return true;
        }
        return false;
    }

    public void moveSouth(Board board) {
        if(this.checkSouth(board)) {
            int x = board.getCharPosX();
            int prev = board.getCharPosY();
            int y = prev + 1;
            ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
            Nodeable placeholder = boardList.get(y).get(x);
            board.addNode(x, prev, previousNode);
            previousNode = placeholder;
            board.addNode(x,y,this);
            board.setCharPosX(x);
            board.setCharPosY(y);
            return;
        }
        System.out.println("You cannot move to that spot! Try again.");

    }
    public void moveEast(Board board) {
        if(this.checkEast(board)) {
            int prev = board.getCharPosX();
            int y = board.getCharPosY();
            int x = prev + 1;
            ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
            Nodeable placeholder = boardList.get(y).get(x);
            board.addNode(prev, y, previousNode);
            previousNode = placeholder;
            board.addNode(x,y,this);
            if (hobo!=null) {
                hobo.moveEast(board);
            }
            board.setCharPosX(x);
            board.setCharPosY(y);
            return;
        }
        System.out.println("You cannot move to that spot! Try again.");

    }
    public void moveNorth(Board board) {
        if(this.checkNorth(board)) {
            int x = board.getCharPosX();
            int prev = board.getCharPosY();
            int y = prev - 1;
            ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
            Nodeable placeholder = boardList.get(y).get(x);
            board.addNode(x, prev, previousNode);
            previousNode = placeholder;
            board.addNode(x,y,this);
            board.setCharPosX(x);
            board.setCharPosY(y);
            return;
        }
        System.out.println("You cannot move to that spot! Try again.");

    }
    public void moveWest(Board board) {
        if(this.checkWest(board)) {
            int prev = board.getCharPosX();
            int y = board.getCharPosY();
            int x = prev - 1;
            ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
            Nodeable placeholder = boardList.get(y).get(x);
            board.addNode(prev, y, previousNode);
            previousNode = placeholder;
            board.addNode(x,y,this);
            if (hobo!=null) {
                hobo.moveWest(board);
            }
            board.setCharPosX(x);
            board.setCharPosY(y);
            return;
        }
        System.out.println("You cannot move to that spot! Try again.");
    }

    public ListOfNodes getType() {
        return type;
    }

    public ArrayList<String> getActionList() {
        throw new IllegalArgumentException("How did you do this?");
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setHobo(Hobo hobo) {
        this.hobo = hobo;
    }

    public Hobo getHobo() {
        return hobo;
    }


}