public interface Moveable {
    public boolean checkNorth(Board board);
    public boolean checkEast(Board board);
    public boolean checkSouth(Board board);
    public boolean checkWest(Board board);

    public void moveNorth(Board board, Game game);
    public void moveEast(Board board, Game game);
    public void moveSouth(Board board, Game game);
    public void moveWest(Board board, Game game);

}