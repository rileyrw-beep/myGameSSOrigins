import java.util.function.DoubleUnaryOperator;
public class Move {


    //the only things needed
    private final String name;
    private final int cost;
    private final String description;
    private int timesProc;
    private final MoveType targetType;
    private final FighterMoveable uniqueFunction;


    //idea- a move that increases in damage with each hit


    //only constructor needed
    public Move(String name, int cost, String description, MoveType target, FighterMoveable uniqueFunction) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.uniqueFunction = uniqueFunction;
        this.targetType = target;
        timesProc = 0;
        //all you need
    }

    //only functions needed
    public int getCost() {return cost;}
    public String getName() {
        return name;
    }
    public String getDescription() {return description;}
    public MoveType getTargetType() {return targetType;}

    public void doMove(Fighter actingFighter, Fighter passiveFighter) {
        uniqueFunction.perform(actingFighter, passiveFighter, new int[]{timesProc});
        timesProc++;
        actingFighter.smartDecreasePower(cost);
    }


}
