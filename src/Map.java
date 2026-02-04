import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<Board>> map;
    private int currentBoardX;
    private int currentBoardY;
    private int boardChangeCounter;

    Map (int x, int y) {
        map = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            ArrayList<Board> row = new ArrayList<>();
            for (int k = 0; k < 10; k++) {
                Board board = new Board(" ", this, "Nothingness");
                row.add(board);
            }
            map.add(row);
        }
        currentBoardX = x;
        currentBoardY = y;
        boardChangeCounter = 0;
    }

    public Board getCurrentBoard() {
        return map.get(currentBoardY).get(currentBoardX);
    }
    public int getCurrentBoardX() {
        return currentBoardX;
    }
    public int getCurrentBoardY() {
        return currentBoardY;
    }
    public void setCurrentBoardX(int currentBoardX) {
        this.currentBoardX = currentBoardX;
    }
    public  void setCurrentBoardY(int currentBoardY) {
        this.currentBoardY = currentBoardY;
    }


    public void printMap() {
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < map.get(0).size(); i++) {
            System.out.print(i + " ");
        }
        System.out.println("X");
        for (int i = 0; i < map.size(); i++) {
            System.out.print(i + " ");
            for(int j = 0; j < map.get(i).size(); j++) {
                String x = map.get(i).get(j).getBoardChar();

                System.out.print(x + " ");
            }
            System.out.println();
        }
        System.out.println("Y");
        System.out.println();
        Game.print("You are at (" + getCurrentBoardX() + ", " + getCurrentBoardY() + ").", 3);
    }

    public void boardChanged() {
        boardChangeCounter++;
    }
    public int getBoardChangeCounter() {
        return boardChangeCounter;
    }

    public ArrayList<ArrayList<Board>> getMapBoard() {
        return map;
    }

    public boolean checkBoard(Direction direction, int x, int y) {

        Board currentBoard = map.get(currentBoardY).get(currentBoardX);
        switch (direction) {
            case NORTH:
                if (currentBoardY-1 >= 0){
                    Board desiredBoard = map.get(currentBoardY - 1).get(currentBoardX);
                    if (!(currentBoard.getCharPosY() - 1 > 0) && desiredBoard.getBoard().get(9).get(x).getCanMoveTo()) return true;
                }
                return false;
            case EAST:
                if (currentBoardX+1 <= 9){
                    Board desiredBoard2 = map.get(currentBoardY).get(currentBoardX + 1);
                    if (!(currentBoard.getCharPosX() + 1 < 9) && desiredBoard2.getBoard().get(y).get(0).getCanMoveTo()) return true;
                }
                return false;
            case SOUTH:
                if (currentBoardY+1 <= 9){
                    Board desiredBoard3 = map.get(currentBoardY + 1).get(currentBoardX);
                    if (!(currentBoard.getCharPosY() + 1 < 9) && desiredBoard3.getBoard().get(0).get(x).getCanMoveTo()) return true;
                }
                return false;
            case WEST:
                if (currentBoardX-1 >= 0){
                    Board desiredBoard4 = map.get(currentBoardY).get(currentBoardX - 1);
                    if (!(currentBoard.getCharPosX() - 1 > 0) && desiredBoard4.getBoard().get(y).get(9).getCanMoveTo()) return true;
                }
        }
        return false;
    }

    public void addBoard(Board board, int x, int y) {
        map.get(y).set(x, board);
    }


}
