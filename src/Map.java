import java.util.*;

public class Map {
    private ArrayList<ArrayList<Board>> map;
    private int currentBoardX;
    private int currentBoardY;

    Map (int x, int y) {
        map = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ArrayList<Board> row = new ArrayList<>();
            for (int k = 0; k < 10; k++) {
                Board board = new Board(" ", this);
                row.add(board);
            }
            map.add(row);
        }
        currentBoardX = x;
        currentBoardY = y;
    }


    public int getCurrentBoardX() {
        return currentBoardX;
    }
    public int getCurrentBoardY() {
        return currentBoardY;
    }

    public void printMap() {
        for (ArrayList<Board> row : map) {
            for (Board board : row) {
                System.out.print(board.getBoardName() + " ");
            }
            System.out.println();
        }
    }

    public ArrayList<ArrayList<Board>> getMapBoard() {
        return map;
    }

    public boolean checkBoard(Direction direction) {
        switch (direction) {
            case NORTH:
                if (!(currentBoardY-- > 0) && !map.get(currentBoardY--).get(currentBoardX).isFullOfEmpty()) return true;
            case EAST:
                if (!(currentBoardX++ < 9) && !map.get(currentBoardY).get(currentBoardX++).isFullOfEmpty()) return true;
            case SOUTH:
                if (!(currentBoardY++ < 9) && !map.get(currentBoardY++).get(currentBoardX).isFullOfEmpty()) return true;
            case WEST:
                if (!(currentBoardX-- > 0) && !map.get(currentBoardY).get(currentBoardX--).isFullOfEmpty()) return true;
        }
        return false;
    }

    public void addBoard(Board board, int x, int y) {
        map.get(y).set(x, board);
    }


}
