import java.util.ArrayList;


public class Board {

    private ArrayList<ArrayList<Nodeable>> boardList;
    private int charPosX;
    private int charPosY;
    private String boardChar;
    private String boardName;
    private Map map;



    public Board(String boardChar, Map map, String boardName) {
        boardList = new ArrayList<>();
        Space space = new Space();
        int rows = 10;
        int col = 10;
        for (int i = 0; i < rows; i++) {
            ArrayList<Nodeable> row = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                row.add(space);
            }
            boardList.add(row);
        }
        this.boardChar = boardChar;
        this.boardName = boardName;
        this.charPosX = -1;
        this.charPosY = -1;
        this.map = map;

    }

    public void addNode(int x, int y, Nodeable node) {
        boardList.get(y).set(x, node);
        if (node.getType()==ListOfNodes.PLAYER) {
            charPosX = x;
            charPosY = y;
        }
    }


    public void setBoardFromString(String boardString) {
        ArrayList<String> splicedStrings = new ArrayList<>();
        int start = 0;
        int end = 19;
        do {
            String temp = boardString.substring(start, end);
            splicedStrings.add(temp);
            start += 20;
            end += 20;
        } while (splicedStrings.size() != 10);


        for (int y = 0; y < splicedStrings.size(); y++) {
            for (int x = 0; x < splicedStrings.get(y).length(); x++) {
                if (x % 2 == 1) {
                    continue;
                }
                String current = String.valueOf(splicedStrings.get(y).charAt(x));
                Nodeable node;
                if (current.equals(" ")) {
                    node = new Space();
                }
                else if (current.equals("-")) {
                    node = new Floor();
                }
                else if (current.equals("P")) {
                    node = new Placeholder();
                }
                else if (current.equals("/")) {
                    node = new Wall();
                }
                else if (current.equals("[")) {
                    node = new Door(false, false);
                }
                else if (current.equals("]")) {
                    node = new Door(true, false);
                }
                else if (current.equals("~")) {
                    node = new River("Grass");
                }
                else if (current.equals("=")) {
                    node = new StaticNode("=","Road");
                }
                else if (current.equals("L")) {
                    node = new StaticNode("L", "Lab Bench");
                }
                else if (current.equals("T")) {
                    node = new StaticNode("T", "Table");
                }
                else {
                    node = new Placeholder();
                }
                addNode(x/2, y, node);
            }
        }
    }

    public void fillPlaceholders(Nodeable[] fillList) {
        int i = 0;
        for (int y = 0; y < boardList.size(); y++) {
            for (int x = 0; x < boardList.size(); x++) {
                Nodeable currentNode = boardList.get(y).get(x);
                if (currentNode.getDisplayid().equals("P")) {
                    if (fillList.length != 0) {
                        addNode(x, y, fillList[i]);
                        i++;
                    }
                    else {
                        return;
                    }
                }
            }
        }
    }

    public void setAllFloor() {
        for (int i = 0; i < boardList.size(); i++) {
            for (int j = 0; j < boardList.get(i).size(); j++) {
                Floor floor = new Floor();
                this.addNode(i, j, floor);
            }
        }
    }



    //print the board
    public void printBoard() {
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < boardList.get(0).size(); i++) {
            System.out.print(i + " ");
        }
        System.out.println("X");
        for (int i = 0; i < boardList.size(); i++) {
            System.out.print(i + " ");
            for(int j = 0; j < boardList.get(i).size(); j++) {
                String x = boardList.get(i).get(j).getDisplayid();

                System.out.print(x + " ");
            }
            System.out.println();
        }
        System.out.println("Y");
        System.out.println();

    }
    public Map getMap() {
        return map;
    }

    public int getCharPosX() {
        return charPosX;
    }

    public int getCharPosY() {
        return charPosY;
    }

    public void setCharPosX (int newPos) {
        charPosX = newPos;
    }

    public void setCharPosY (int newPos) {
        charPosY = newPos;
    }

    public String getBoardChar() {
        return boardChar;
    }

    public String getBoardName() {
        return boardName;
    }
    public ArrayList<ArrayList<Nodeable>> getBoard() {
        return boardList;
    }

    public boolean isFullOfEmpty() {
        for (ArrayList<Nodeable> row : boardList) {
            for (Nodeable node : row) {
                if (!node.getInGameid().equals("Space")) return false;
            }
        }
        return true;
    }

    private boolean checkLegendList(ArrayList<Nodeable> nodeList, Nodeable currentNode) {
        for (Nodeable node : nodeList) {
            if (currentNode.getDisplayid()==node.getDisplayid()) {
                return false;
            }
        }
        return true;
    }

    public void printLegend() {
        ArrayList<Nodeable> nodeList = new ArrayList<>();
        Nodeable currentNode;
        for (int i = 0; i < boardList.size(); i++ ) {
            for (int k = 0; k < boardList.get(i).size(); k++) {
                currentNode = boardList.get(i).get(k);
                if (currentNode.getType()!=ListOfNodes.SPACE && this.checkLegendList(nodeList, currentNode)) {
                    nodeList.add(currentNode);
                }
            }
        }
        System.out.println("Legend:");
        for (Nodeable node : nodeList) {
            if (node.getDisplayid()=="[" || node.getDisplayid()=="]") {
                System.out.println("] = Open Door");
                System.out.println("[ = Closed Door");
            }
            else {System.out.println(node.getDisplayid() + " = " + node.getInGameid());}
        }
        System.out.println();
        System.out.println("You are currently in... " + boardName);

    }


    public void buildRectRoom(int topLeftX, int topLeftY, int topRightX, int topRightY, int bottomRightX, int bottomRightY, int bottomLeftX, int bottomLeftY, int doorX, int doorY, Direction side) {
        //numbers
        ArrayList<Nodeable> wallList = new ArrayList<Nodeable>();
        ArrayList<Nodeable> floorList = new ArrayList<Nodeable>();
        int numWalls = (((topRightX-topLeftX+1)*2)+((bottomRightY-topRightY+1)*2))-4;
        int doorNum = -1;
        int numFloors = (topRightX-topLeftX-1)*(bottomRightY-topRightY-1)+1;

        //deciding which side the door is on
        if (side==Direction.NORTH) {
            doorNum = doorX-topLeftX;
        }
        if (side==Direction.EAST) {
            doorNum = (doorY-topRightY) + (topRightX-topLeftX);
        }
        if (side==Direction.SOUTH) {
            doorNum = numWalls - ((doorX-bottomLeftX) + (bottomLeftY-topLeftY));
        }
        if (side==Direction.WEST) {
            doorNum = numWalls - (doorY-topLeftY);
        }

        //create the list of floor objects
        for (int i = 0; i < numFloors; i++) {
            Floor floor = new Floor();
            floorList.add(floor);
        }

        //create the list of wall objects + a door
        for (int i = 0; i < numWalls; i++) {
            if (doorNum > 0 && i == doorNum) {
                Door door = new Door(false, false);
                wallList.add(door);
            }
            else {
                Wall wall = new Wall();
                wallList.add(wall);
            }
        }

        //make the perimeter
        int wallX = topLeftX;
        int wallY = topLeftY;
        for (int i = 0; i < numWalls; i++) {
            if (i == 0) {
                this.addNode(wallX, wallY, wallList.get(i));
                i++;
            }
            if(wallX < topRightX && wallY == topRightY) {
                wallX++;
                this.addNode(wallX, wallY, wallList.get(i));
            }
            else if (wallX == bottomRightX && wallY < bottomRightY) {
                wallY++;
                this.addNode(wallX, wallY, wallList.get(i));
            }
            else if (wallY == bottomLeftY && wallX > bottomLeftX) {
                wallX--;
                this.addNode(wallX, wallY, wallList.get(i));
            }
            else if (wallX == topLeftX && wallY > topLeftY) {
                wallY--;
                this.addNode(wallX, wallY, wallList.get(i));
            }

        }

        //make the area
        int floorX = topLeftX + 1;
        int floorY = topLeftY + 1;
        int endFloorX = bottomRightX;
        int endFloorY = bottomRightY - 1;
        int currentFloor = 0;
        while(floorX!= endFloorX || floorY!=endFloorY) {
            if (floorX!=(endFloorX)) {
                this.addNode(floorX, floorY, floorList.get(currentFloor));
                floorX++;
                currentFloor++;
            }
            else {
                floorX = topLeftX + 1;
                floorY++;
            }
        }

    }

    public void stepBoard(Game game) {
        for (int y = 0; y < boardList.size(); y++) {
            for (int x = 0; x < boardList.get(y).size(); x++) {
                Nodeable node = boardList.get(y).get(x);
                if (node.getType() == ListOfNodes.CIVILIAN) {
                    node.performAction("", this, game);
                }
            }
        }
    }


}
