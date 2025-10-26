import java.util.ArrayList;
import java.util.Random;

public class Civilian implements Nodeable, Moveable {
    private final String displayid;
    private final String inGameid;
    private Nodeable previousNode;
    private final boolean canMoveTo;
    private final ListOfNodes type;
    private int x;
    private int y;
    private ArrayList<String> actionList;



    public Civilian (int startX, int startY, Nodeable startNode) {
        this.displayid = "C";
        this.inGameid = "Civilian";
        previousNode = startNode;
        type = ListOfNodes.CIVILIAN;
        canMoveTo = false;
        x = startX;
        y = startY;
        actionList = new ArrayList<>();
    }

    public boolean[] performAction(String response, Board board, Game game) {
        boolean[] returnArray = {true, false};

        Random rand = new Random();
        int c = 0;
        while (true) {
            c++;
            if (c==2000) {
                return returnArray;
            }
            double dub = rand.nextDouble();
            if (dub <= .25) {
                if (!checkNorth(board) || !checkForPlayer(board, Direction.NORTH)) {
                    continue;
                }
                this.moveNorth(board, game);
                return returnArray;
            } else if (dub <= .5) {
                if (!checkEast(board) || !checkForPlayer(board, Direction.EAST)) {
                    continue;
                }
                this.moveEast(board, game);
                return returnArray;
            } else if (dub <= .75) {
                if (!checkSouth(board) || !checkForPlayer(board, Direction.SOUTH)) {
                    continue;
                }
                this.moveSouth(board, game);
                return returnArray;
            } else {
                if (!checkWest(board) || !checkForPlayer(board, Direction.WEST)) {
                    continue;
                }
                this.moveWest(board, game);
                return returnArray;
            }
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
        ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
        if (y+1>9) {
            return false;
        }
        return boardList.get(y+1).get(x).getCanMoveTo();
    }
    public boolean checkEast(Board board) {
        ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
        if (x+1>9) {
            return false;
        }
        return boardList.get(y).get(x+1).getCanMoveTo();
    }
    public boolean checkNorth(Board board) {
        ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
        if (y-1<0) {
            return false;
        }
        return boardList.get(y-1).get(x).getCanMoveTo();
    }
    public boolean checkWest(Board board) {
        ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
        if (x-1<0) {
            return false;
        }
        return boardList.get(y).get(x-1).getCanMoveTo();
    }

    public void moveSouth(Board board, Game game) {
        if(this.checkSouth(board)) {
            int prev = y;
            y++;
            ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
            Nodeable placeholder = boardList.get(y).get(x);
            board.addNode(x, prev, previousNode);
            previousNode = placeholder;
            board.addNode(x,y,this);
            return;
        }
        System.out.println("Civilian cannot move to that spot! Try again.");

    }
    public void moveEast(Board board, Game game) {
        if(this.checkEast(board)) {
            int prev = x;
            x++;
            ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
            Nodeable placeholder = boardList.get(y).get(x);
            board.addNode(prev, y, previousNode);
            previousNode = placeholder;
            board.addNode(x,y,this);
            return;
        }
        System.out.println("Civilian cannot move to that spot! Try again.");

    }
    public void moveNorth(Board board, Game game) {
        if(this.checkNorth(board)) {
            int prev = y;
            y--;
            ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
            Nodeable placeholder = boardList.get(y).get(x);
            board.addNode(x, prev, previousNode);
            previousNode = placeholder;
            board.addNode(x,y,this);
            return;
        }
        System.out.println("Civilian cannot move to that spot! Try again.");

    }
    public void moveWest(Board board, Game game) {
        if(this.checkWest(board)) {
            int prev = x;
            x--;
            ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
            Nodeable placeholder = boardList.get(y).get(x);
            board.addNode(prev, y, previousNode);
            previousNode = placeholder;
            board.addNode(x,y,this);
            return;
        }
        System.out.println("You cannot move to that spot! Try again.");
    }

    public ListOfNodes getType() {
        return type;
    }

    public ArrayList<String> getActionList() {
        return actionList;
    }

    public boolean checkTeleport(Board board, int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 9) return false;
        ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
        Nodeable desiredNode = boardList.get(y).get(x);
        return desiredNode.getCanMoveTo();
    }

    public void teleport(Board board, Game game, int a, int b) {
        if (checkTeleport(board, a, b)) {
            ArrayList<ArrayList<Nodeable>> boardList = board.getBoard();
            Nodeable desiredNode = boardList.get(b).get(a);
            Nodeable placeholder = previousNode;
            previousNode = desiredNode;
            board.addNode(board.getCharPosX(), board.getCharPosY(), placeholder);
            board.addNode(a, b, this);
            x = a;
            y = b;
        }
    }

    public void mapTeleport(Board currentBoard, Map newMap, Game game, int boardX, int boardY, int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            System.out.println("Wrong x or y.");
            return;
        }
        if (boardX < 0 || boardX > 9 || boardY < 0 || boardY > 9) {
            System.out.println("Wrong boardX or boardY.");
            return;
        }
        Nodeable desiredNode = newMap.getMapBoard().get(y).get(x).getBoard().get(y).get(x);
        if (desiredNode.getCanMoveTo()) {
            currentBoard.addNode(currentBoard.getCharPosX(), currentBoard.getCharPosY(), previousNode);
            previousNode = desiredNode;
            newMap.getMapBoard().get(y).get(x).addNode(x, y, this);
            newMap.getMapBoard().get(y).get(x).setCharPosX(x);
            newMap.getMapBoard().get(y).get(x).setCharPosY(y);
            game.setBoard(newMap.getMapBoard().get(boardY).get(boardX));
            game.setCurrentMap(newMap);
            newMap.setCurrentBoardX(boardX);
            newMap.setCurrentBoardY(boardY);
            newMap.boardChanged();
        }
        else {
            System.out.println("You cannot teleport there.");
        }
    }

    public void withinMapTeleport(Board board, Game game, int boardX, int boardY, int x, int y) {
        if (x < 0 || x > 9 || y < 0 || y > 9) {
            System.out.println("Wrong x or y.");
            return;
        }
        if (boardX < 0 || boardX > 9 || boardY < 0 || boardY > 9) {
            System.out.println("Wrong boardX or boardY.");
            return;
        }
        Map map = board.getMap();
        Board newBoard = map.getMapBoard().get(boardY).get(boardX);
        if (!newBoard.getBoard().get(y).get(x).getCanMoveTo()) {
            System.out.println("You cannot teleport there.");
            return;
        }
        board.addNode(board.getCharPosX(), board.getCharPosY(), previousNode);
        previousNode = newBoard.getBoard().get(y).get(x);
        newBoard.addNode(x, y, this);
        newBoard.setCharPosX(x);
        newBoard.setCharPosY(y);
        game.setBoard(newBoard);
        map.setCurrentBoardX(boardX);
        map.setCurrentBoardY(boardY);
        map.boardChanged();
    }



    public boolean checkForPlayer(Board board, Direction d) {
        int cX = board.getCharPosX();
        int cY = board.getCharPosY();
        switch (d) {
            case NORTH:
                if ((cX+1 == x && cY == y-1)) return false;
                if (cX == x && cY+1 == y-1) return false;
                if (cX-1 == x && cY == y-1) return false;
                if (cX == x && cY-1 == y-1) return false;
                if ((cX+1 == x && cY+1 == y-1)) return false;
                if (cX-1 == x && cY+1 == y-1) return false;
                if (cX-1 == x && cY-1 == y-1) return false;
                if (cX+1 == x && cY-1 == y-1) return false;
                return true;

            case EAST:
                if ((cX+1 == x-1 && cY == y)) return false;
                if (cX == x-1 && cY+1 == y) return false;
                if (cX-1 == x-1 && cY == y) return false;
                if (cX == x-1 && cY-1 == y) return false;
                if ((cX+1 == x-1 && cY+1 == y)) return false;
                if (cX-1 == x-1 && cY+1 == y) return false;
                if (cX-1 == x-1 && cY-1 == y) return false;
                if (cX+1 == x-1 && cY-1 == y) return false;

                return true;

            case WEST:
                if ((cX+1 == x+1 && cY == y)) return false;
                if (cX == x+1 && cY+1 == y) return false;
                if (cX-1 == x+1 && cY == y) return false;
                if (cX == x+1 && cY-1 == y) return false;
                if ((cX+1 == x+1 && cY+1 == y)) return false;
                if (cX-1 == x+1 && cY+1 == y) return false;
                if (cX-1 == x+1 && cY-1 == y) return false;
                if (cX+1 == x+1 && cY-1 == y) return false;
                return true;

            case SOUTH:
                if ((cX+1 == x && cY == y+1)) return false;
                if (cX == x && cY+1 == y+1) return false;
                if (cX-1 == x && cY == y+1) return false;
                if (cX == x && cY-1 == y+1) return false;
                if ((cX+1 == x && cY+1 == y+1)) return false;
                if (cX-1 == x && cY+1 == y+1) return false;
                if (cX-1 == x && cY-1 == y+1) return false;
                if (cX+1 == x && cY-1 == y+1) return false;
                return true;
        }
        return true;

    }

}
