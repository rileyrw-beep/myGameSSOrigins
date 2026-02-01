import java.util.ArrayList;
import java.util.Stack;
import java.util.LinkedList;

public class Fighter {

    private final String name;
    private final ArrayList<Move> ALL_MOVES;
    private final double MAX_HEALTH;
    private double currentHealth;
    private final int MAX_POWER;
    private int currentPower;
    private LinkedList<StatusEffect> statusEffects;

    public Fighter(String name, int pwr, double hp, ArrayList<Move> moves) {
        this.name = name;
        this.MAX_POWER = pwr;
        this.MAX_HEALTH = hp;
        ALL_MOVES = moves;
        statusEffects = new LinkedList<>();
        currentHealth = hp;
        currentPower = 0;
    }

    public double  getCurrentHealth() {
        return currentHealth;
    }
    public double getMaxHealth() {return  MAX_HEALTH;}
    public int getCurrentPower() {
        return currentPower;
    }
    public int getMaxPower() {return MAX_POWER;}
    public ArrayList<Move> getMoves() {
        return ALL_MOVES;
    }
    public String getName() {
        return name;
    }

    //smartly increases power
    public void smartIncreasePower() {
        double additionalPower = 2;
        additionalPower = smartStatusEffect(additionalPower, MoveType.PowerGain);
        currentPower += (int) additionalPower;
        if (currentPower > MAX_POWER) {
            currentPower = MAX_POWER;
        }
    }
    //increase power
    public void smartIncreasePower(int inc) {
        int additionalPower = (int)smartStatusEffect(inc, MoveType.PowerGain);
        currentPower += additionalPower;
        if (currentPower > MAX_POWER) {
            currentPower = MAX_POWER;
        }
    }


    //plain power decreasing
    public void decreasePower(int dec) {
        currentPower -= dec;
        if (currentPower < 0) currentPower = 0;
    }

    //plain health increase, no status checking
    public void increaseHealth(double inc) {
        currentHealth += inc;
    }

    //decreases health but only checks the fighters defenses, not the opponents offensives
    public void decreaseHealth(double dec) {
        double finalDec = smartStatusEffect(dec, MoveType.Defense);
        currentHealth -= finalDec;
        if (currentHealth < 0) currentHealth = 0;
    }

    //good
    public void addStatusEffect(StatusEffect se) {
        if (se.isBuff()) statusEffects.addFirst(se);
        else statusEffects.addLast(se);
    }

    public LinkedList<StatusEffect> getStatusEffects() {
        return statusEffects;
    }

    public void clearStatusEffects() {
        statusEffects.clear();
    }

    public void clearStatusEffects(boolean isBuff) {
        ArrayList<StatusEffect> seToRemove = new ArrayList<>();

        for (StatusEffect se : statusEffects) {
            if (se.isBuff() == isBuff)  seToRemove.add(se);
        }

        statusEffects.removeAll(seToRemove);
    }


    //this will run through the status effects and see if any
    //1: deal damage over time to the fighter
    //2: can skip the fighters turn
    //3: can nullify any turn skips
    //it will then do those status effects
    //it will also increment EVERY status effect timer by 1 because they are being "held" for another turn
    //it will then clean up any status effects which have reached their limit
    //if returns true then skip the fighter turn
    //usually returns false
    public boolean applyStatusEffects() {
        ArrayList<StatusEffect> statusEffectsToRemove = new ArrayList<>();
        boolean rv = false; //return value
        boolean turnProtected = false;

        for (StatusEffect se :  statusEffects) {
            switch (se.getTargetType()) {
                case Heal, Harming:
                    se.apply(this);
                    break;
                case TurnMiss:
                    if (se.apply(this)) {
                        rv = true; //turn will be missed
                    }
                    break;
                case TurnProtect:
                    if (se.apply(this)) {
                        turnProtected = true; //turn will be protected
                    }
                    break;
            }

            //every status effect increases in turns held each turn
            se.proc();

            //it is also cleaned up if they reach their limit
            if (se.checkIfStatusFinished()) {
                statusEffectsToRemove.add(se);
                se.resetTurnsHeld();
            }
        }

        //garbage collector
        statusEffects.removeAll(statusEffectsToRemove);

        if (turnProtected) rv = false;

        return rv;
    }


    //this function takes in a magnitude which is either some number meant to attack or affect health
    //it scans the status effect list for any status effects which would fit the description
    //and it applies the status effect.
    //it also cleans up after itself and if a status effect has moved out of
    public double smartStatusEffect(double magnitude, MoveType target) {
        ArrayList<StatusEffect> statusEffectsToRemove = new ArrayList<>();

        for (StatusEffect se : statusEffects) {
            if (se.getTargetType() == target) {
                magnitude = se.modify(magnitude);
                if (se.checkIfStatusFinished()) statusEffectsToRemove.add(se);
            }
        }

        for (StatusEffect se : statusEffectsToRemove) {
            se.resetTurnsHeld();
            statusEffects.remove(se);
        }

        return magnitude;
    }

    //this will first see if the attacker has any status effects that magnifies the attack
    //decreaseHealth() then will run through the hit fighter's status effects for any defensive status effects
    public void smartDecreaseHealth(Fighter other, double magnitude) {
        decreaseHealth(other.smartStatusEffect(magnitude, MoveType.Attack));
    }

    //this will increase the health by applying any status effects that deal with health
    public void smartIncreaseHealth(double magnitude) {
        increaseHealth(smartStatusEffect(magnitude, MoveType.Heal));
    }

    //consuume power but checks the status effects if there are any
    public void smartDecreasePower(double magnitude) {
        decreasePower((int)(smartStatusEffect(magnitude, MoveType.PowerConsume)));
    }

    //helper function for nominal vs real power costs
    public boolean checkCostDifference(Move move) {
        return move.getCost() != (int)(smartStatusEffect(move.getCost(), MoveType.PowerConsume));
    }

    //get the cost of the move with the energy modification status effects checked
    public int getNominalCost(Move move) {
        return (int)(smartStatusEffect(move.getCost(), MoveType.PowerConsume));
    }





    //this is for when the battle ends
    public void resetFighter() {
        currentHealth = MAX_HEALTH;
        currentPower = 0;
        statusEffects.clear();
    }
}


