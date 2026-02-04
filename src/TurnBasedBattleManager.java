import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class TurnBasedBattleManager {
//this should have a static map of string keys to fighters
    //then that means that the methods can be static so we dont need to have some large facade

    static final Map<String, Fighter> fighterMap = Map.ofEntries(
            /*
            * Fighter - String name, int pwr, double hp, ArrayList<Move> moves
            * -------------------------------------------------------------------
            * mv - String name, double hpAmount, int cost, MoveType targetType,
                MoveType statusType, boolean pureChance, double chance, double multiplier,
                int duration, String description, String procDescription, String failDescription,
                String multiDescription, String statusDescription,
                FighterMoveable extraFunction, FighterMoveable failFunction
            * */


            Map.entry("riley", new Fighter("Riley Nelson", 10, 100.0, new ArrayList<Move>(Arrays.asList(
                    new Move("Punch", 0, "Just a regular punch that does 5 damage", MoveType.Attack, ((actingFighter, passiveFighter, timesProcced) -> {
                        passiveFighter.smartDecreaseHealth(actingFighter, 5.0);
                        Game.sPrint("You throw a punch. Wow.");
                        timesProcced[0]++;
                    })),
                    new Move("Ponder", 0, "You think deeply for a bit (like kiddie pool deep though) for a bit and gain 1 power.", MoveType.PowerGain, ((actingFighter, passiveFighter, timesProcced) -> {
                        actingFighter.smartIncreasePower(1);
                        Game.sPrint("After thinking really hard you get 1 power.");
                        timesProcced[0]++;
                    })),
                    new Move("Quick Snack", 4, "You eat a quick snack (55 burgers, 55 fries, 55 milkshakes) and heal 10 health", MoveType.Heal, ((actingFighter, passiveFighter, timesProcced) -> {
                        actingFighter.smartIncreaseHealth(10.0);
                        Game.sPrint("After eating 55 burgers, 55 fries, 55 milkshakes you feel much better. Fatass.");
                        timesProcced[0]++;
                    })),
                    new Move("Big Punch", 4, "A bigger punch that does 15 damage", MoveType.Attack, ((actingFighter, passiveFighter, timesProcced) -> {
                        passiveFighter.smartDecreaseHealth(actingFighter, 15.0);
                        Game.sPrint("You throw a pretty big punch. Nice.");
                        timesProcced[0]++;
                    })),
                    new Move("Using Your Head", 5, "You use your head to charge headfirst into the opponent dealing 30 damage to them but 15 damage back at you, applying a 15% defense nerf to both of you.", MoveType.Attack, ((actingFighter, passiveFighter, timesProcced) -> {
                        passiveFighter.smartDecreaseHealth(actingFighter, 30.0);
                        actingFighter.decreaseHealth(15.0);
                        StatusEffect dazed = new StatusEffect("Dazed and Confused", "15% Defense Nerf", 3, MoveType.Defense, false, ((magnitude) -> magnitude * 1.15), null);
                        actingFighter.addStatusEffect(dazed);
                        passiveFighter.addStatusEffect(dazed.copy());
                        Game.sPrint("You finally decide to use that head of yours, but not to think, instead you charge and headbutt the opponent. It hurts you too.");
                        timesProcced[0]++;
                    })),
                    new Move("Crazy Soda", 7, "You make a string concoction and drink it for a 50% chance to get a 2x damage, healing, power, and defensive boost or -15 health", MoveType.Heal, ((actingFighter, passiveFighter, timesProcced) -> {
                        Random rand = new Random();
                        System.out.print("You mix together some orange juice, toothpaste, Butyric Acid, Liquid Ammonia, and Formic Acid to make your crazy soda and drink it.... ");
                        if (rand.nextDouble() < 0.5) {
                            actingFighter.addStatusEffect(new StatusEffect("Crazy Soda Damage Boost", "2x Damage", 5, MoveType.Attack, true, ((magnitude) -> magnitude * 2.0), null));
                            actingFighter.addStatusEffect(new StatusEffect("Crazy Soda Health Boost", "2x Healing", 5, MoveType.Heal, true, ((magnitude) -> magnitude * 2.0), null));
                            actingFighter.addStatusEffect(new StatusEffect("Crazy Soda Power Boost", "2x Power", 5, MoveType.PowerGain, true, ((magnitude) -> magnitude * 2.0), null));
                            actingFighter.addStatusEffect(new StatusEffect("Crazy Soda Defense Boost", "2x Defense", 5, MoveType.Defense, true, ((magnitude) -> magnitude * 0.5), null));
                            Game.print("Somehow, someway, it works and you get a 2x damage, healing, power, and defensive buff.", 8);
                        }
                        else {
                            Game.print("Your stomach hurts and you take 15 damage", 7);
                            actingFighter.decreaseHealth(15.0); //absolute cinema
                        }
                        timesProcced[0]++;
                    })),
                    new Move("Riley's Addiction Relief Pills", 7, "Taking these has a 50% chance to clear your status effects and a 50% chance to turn you into a communist clown.", MoveType.Heal, ((actingFighter, passiveFighter, timesProcced) -> {
                        actingFighter.clearStatusEffects();
                        Game.sPrint("You pop a few of the pills and after a bit you feel pretty good. No Communism and no clowns. You die for a bit but its only temporary. Status Effects Cleared.");
                        timesProcced[0]++;
                    }))
            )))),
            Map.entry("jerry", new Fighter("Jerry", 15, 110, new ArrayList<Move>(Arrays.asList(
                    new Move("Kick", 0, "", MoveType.Attack, (actingFighter, passiveFighter, timesProcced) -> {
                        passiveFighter.smartDecreaseHealth(actingFighter, 5);
                        Game.sPrint("Jerry hits you with a roundhouse kick that does 5 damage.");
                        timesProcced[0]++;
                    }),
                    new Move("Negotiate", 0, "", MoveType.PowerGain,  (actingFighter, passiveFighter, timesProcced) -> {
                        actingFighter.smartIncreasePower(1);
                        timesProcced[0]++;
                        Game.sPrint("Jerry tries to talk it out with you, as he does so he gets 1 power.");
                    }),
                    new Move("Charging Up", 0, "", MoveType.PowerGain, (actingFighter, passiveFighter, timesProcced) -> {
                        actingFighter.smartIncreasePower(5);
                        timesProcced[0]++;
                        Game.sPrint("Jerry takes matters into his own hands and begins yelling as he charges his power up. +5 Power");
                    }),
                    new Move("Quick Heal", 0, "", MoveType.Heal,  (actingFighter, passiveFighter, timesProcced) -> {
                        double preHealth = actingFighter.getCurrentHealth();
                        actingFighter.smartIncreaseHealth(5.0);
                        timesProcced[0]++;
                        Game.sPrint("Jerry drinks a bit of nectar and quickly heals " + (actingFighter.getCurrentHealth() - preHealth) + " health.");
                    }),
                    new Move("Photosynthesis", 3, "", MoveType.Heal,  (actingFighter, passiveFighter, timesProcced) -> {
                        actingFighter.smartIncreaseHealth(15);
                        timesProcced[0]++;
                        Game.sPrint("Since Jerry is a botanist he knows how to photosynthesize. He puts some water, leaves, air and sunlight on his arm and it heals for 15 damage.");
                    }),
                    new Move("Long Photosynthesis", 5, "", MoveType.Heal,   (actingFighter, passiveFighter, timesProcced) -> {
                        actingFighter.addStatusEffect(new StatusEffect("Long Photosynthesis Defense Boost", "25% Defense Boost", 4, MoveType.Defense, true, ((magnitude) -> magnitude * 0.75), null));
                        actingFighter.addStatusEffect(new StatusEffect("Long Photosynthesis Health Boost", "5 HP Regen", 4, MoveType.Heal, true, null, ((f) -> {
                            f.smartIncreaseHealth(5);
                            Game.sPrint("Jerry photosynthesizes back 5 health.");
                            return true;
                        })));
                        Game.sPrint("Jerry uses his botanist knowledge to convert a bit of his skin to leaves giving him a 25% defensive boost and allowing him to photosynthesize 5 health back up to 4 times.");
                        timesProcced[0]++;
                    }),
                    new Move("Big Kick", 4, "", MoveType.Attack,  (actingFighter, passiveFighter, timesProcced) -> {
                        passiveFighter.smartDecreaseHealth(actingFighter, 15);
                        Game.sPrint("Jerry hits you with a bigger kick that does 15 damage. Ouch");
                        timesProcced[0]++;
                    }),
                    new Move("Final Jerry Cannon", 10, "", MoveType.Attack, ((actingFighter, passiveFighter, timesProcced) -> {
                        passiveFighter.smartDecreaseHealth(actingFighter, 50.0);
                        timesProcced[0]++;
                        Game.sPrint("Jerry begins to levitate as he shouts out \"Final Jerry Cannon!\" He hits you with his ultimate attack dealing 50 damage.");
                    }))
            ))))
            //work on this now
            //    String name,
            //    int cost,
            //    String description,
            //    MoveType target,
            //    FighterMoveable uniqueFunction
    );



    static public boolean doBattle(final String[] keyProtag, final String[] keyEnemy, boolean protagShouldWin) {
        //game loop
        //track using turns: if even then player, if odd then enemy
        //show the possible moves, health, and power
        //if any status affects, do them
        //Player chooses their move
        //do the move
        //enemy randomly chooses their move
        //do the move
        //check if one's health reached zero

        int numTurn = 0;
        int currentProtagIndex = 0;
        int currentEnemyIndex = 0;
        boolean protagWins = false;
        Fighter protag = fighterMap.get(keyProtag[currentProtagIndex]);
        Fighter enemy = fighterMap.get(keyEnemy[currentEnemyIndex]);

        //introductary stuff

        Game.print((protag.getName() + " VS " + enemy.getName()), 3);
        Game.print("BEGIN", 3);



        while (true) {

            if (numTurn % 2 == 0) {
                //protag turn
                protag.smartIncreasePower();
                System.out.println(protag.getName() + "'s Turn");

                System.out.println();

                System.out.println(protag.getName() + " HP: " + protag.getCurrentHealth() + "/" + protag.getMaxHealth());
                System.out.println(protag.getName() + " Power: " + protag.getCurrentPower() + "/" + protag.getMaxPower());

                Game.time(1);
                System.out.println();

                System.out.println(enemy.getName() + " HP: " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth()); //print out enemy hp too

                //protag status effects
                Game.print("",1);
                printStatusEffects(protag);
                Game.print("", 2);




                double hpNow = protag.getCurrentHealth();
                boolean skipTurn = protag.applyStatusEffects();

                //if the protag's health changed due to status effects, print the new final health
                if (hpNow != protag.getCurrentHealth())
                    Game.print(protag.getName() + " HP: " + protag.getCurrentHealth() + "/" + protag.getMaxHealth(), 2);
                //if the turn is being skipped, skip the turn
                if (skipTurn) {
                    Game.print(("Skipping " + protag.getName() + "'s Turn..."), 3);
                    numTurn++;
                    continue;
                }


                //protag move display sequence
                if (canSwap(keyProtag, protag)) System.out.println("0. Swap");
                for (int i = 0; i < protag.getMoves().size(); i++) {
                    if (protag.getNominalCost(protag.getMoves().get(i)) <= protag.getCurrentPower()) {
                        System.out.print(((i + 1)) + ". " + protag.getMoves().get(i).getName() + " | Cost: ");
                        if (protag.checkCostDifference(protag.getMoves().get(i))) {
                            System.out.print(protag.getNominalCost(protag.getMoves().get(i)) + " | (Real Cost: " + protag.getMoves().get(i).getCost() + ")");
                        } else {
                            System.out.print(protag.getMoves().get(i).getCost());
                        }
                        System.out.println(" | " + protag.getMoves().get(i).getDescription());
                    }
                }
                System.out.println();
                Game.time(2);

                //protag move choice sequence
                Move chosenMove = null;

                while (true) {
                    System.out.print("Enter the number of the move you want to perform: ");
                    int moveNum;
                    try {
                        moveNum = Game.intInput();
                    }
                    catch (InputMismatchException e) {//catch invalid input
                        System.out.println();
                        System.out.println("Invalid choice: Unrecognized Input");
                        System.out.println();
                        Game.time(1);
                        continue;
                    }
                    int min = (canSwap(keyProtag, protag)) ? 0 : 1;//if you can swap then min should be 0
                    if (moveNum > protag.getMoves().size() || moveNum < min) {//catch invalid numbers
                        System.out.println();
                        System.out.println("Invalid Choice: Input Out of Range");
                        System.out.println();
                        Game.time(1);
                        continue;
                    }

                    if (moveNum==0) {//if 0 you are swapping, and the move logic should not be ran
                        break;
                    }

                    chosenMove = protag.getMoves().get(moveNum - 1);

                    if (protag.getNominalCost(chosenMove) > protag.getCurrentPower()) {//catch if chosen move is too expensive
                        System.out.println();
                        System.out.println("Invalid Choice: Move too Expensive");
                        System.out.println();
                        Game.time(1);
                        chosenMove = null; //if too expensive then reset chosen move
                        continue;
                    }
                    break;
                }

                //protag move choice logic
                System.out.println();
                if (chosenMove == null) { //if no chosen move, must be swapping
                    System.out.print(protag.getName() + " has swapped out with ");
                    currentProtagIndex = swap(keyProtag, currentProtagIndex, new Fighter[]{protag});
                    System.out.println(protag.getName());
                }
                else { //else there is a move and you should do the move
                    System.out.println(protag.getName() + " used " + chosenMove.getName() + "!");
                    System.out.println();
                    double preHealthA = protag.getCurrentHealth();
                    double preHealthP = enemy.getCurrentHealth();
                    int prePowerA = protag.getCurrentPower();
                    int prePowerP = enemy.getCurrentPower();
                    Game.time(3);
                    chosenMove.doMove(protag, enemy); //all move functionality calcuated here
                    System.out.println();
                    displayInfo(protag, preHealthA, prePowerA, enemy, preHealthP, prePowerP);
                    Game.time(2);
                }
                Game.endText();
            }
            else { //determined by modding a counter by two
                //enemy's turn
                enemy.smartIncreasePower();
                System.out.println(enemy.getName() + "'s Turn");
                System.out.println();
                System.out.println(enemy.getName() + " HP: " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());


                //enemy status effect sequence
                Game.print("",1);
                printStatusEffects(enemy);
                Game.print("", 5);

                double preHealth = enemy.getCurrentHealth();
                boolean skipTurn = enemy.applyStatusEffects();

                if (preHealth != enemy.getCurrentHealth())
                    Game.sPrint(enemy.getName() + " HP: " + enemy.getCurrentHealth() + "/" + enemy.getMaxHealth());
                if (skipTurn) {
                    Game.print(("Skipping " + enemy.getName() + "'s Turn..."), 3);
                    numTurn++;
                    continue;
                }

                //enemy turn selection sequence
                Random rand = new Random();

                ArrayList<Move> possibleMoves = new ArrayList<>();

                if (enemy.getName().equals("Jerry") && enemy.getCurrentPower() >= 10) {
                    for (Move move : enemy.getMoves()) {
                        if (move.getCost() >= 10) {
                            possibleMoves.add(move);
                        }
                    }
                }
                else if (enemy.getCurrentHealth() / enemy.getMaxHealth() < .3) {
                    for (Move move : enemy.getMoves()) {
                        if (enemy.getNominalCost(move) <= enemy.getCurrentPower() && move.getTargetType() == MoveType.Heal) {
                            possibleMoves.add(move);
                        }
                    }
                }
                else {
                    for (Move move : enemy.getMoves()) {
                        if (enemy.getNominalCost(move) <= enemy.getCurrentPower()) {
                            possibleMoves.add(move);
                        }
                    }
                }

                int enemyChoice = rand.nextInt(possibleMoves.size());

                double preHealthA = enemy.getCurrentHealth();
                double preHealthP = protag.getCurrentHealth();
                int prePowerA = enemy.getCurrentPower();
                int prePowerP = protag.getCurrentPower();

                Move chosenMove = possibleMoves.get(enemyChoice);
                Game.print(enemy.getName() + " used " + chosenMove.getName() + "!", 4);
                chosenMove.doMove(enemy, protag);
                Game.time(0);
                System.out.println();
                displayInfo(enemy, preHealthA, prePowerA, protag, preHealthP, prePowerP);
                Game.time(2);
                Game.endText();
            }

            //check if either died
                //if so, check if any alive members left on team
                    //if yes, swap to them
                    //if no, then battle over
                        //if protag cant lose and enemy won
                            //restart
                        //else
                            //end battle sequence and protag walks away
            if (enemy.getCurrentHealth() <= 0) {
                System.out.print(enemy.getName() + " has fallen");
                if (canSwap(keyEnemy, enemy)) {
                    int swapCount = 0;
                    while (enemy.getCurrentHealth() <= 0) {
                        currentEnemyIndex = swap(keyEnemy, currentEnemyIndex, new Fighter[]{enemy});
                        swapCount++;
                        if (swapCount > keyEnemy.length) break;
                    }
                }
                if (enemy.getCurrentHealth() <= 0) {
                    Game.print(", Battle Over.", 3);
                    protagWins = true;
                    break;
                }
                else {
                    Game.print(", but " + enemy.getName() + " has stepped up to fight.", 4);
                }
            }
            if (protag.getCurrentHealth() <= 0) {
                System.out.print(protag.getName() + " has fallen, ");
                if (canSwap(keyProtag, protag)){
                    int swapCount = 0;
                    while (protag.getCurrentHealth() <= 0) {
                        currentProtagIndex = swap(keyProtag, currentProtagIndex, new Fighter[]{protag});
                        swapCount++;
                        if (swapCount > keyProtag.length) break;
                    }
                }
                if (protag.getCurrentHealth() <= 0) {
                    if (!protagShouldWin) break;
                    else {
                        Game.print("Dang, you really just lost.", 4);
                        Game.print("That's okay, I'm giving you another chance.", 5);
                        Game.print("bozo", 1);
                        Game.endText();
                        for (String str : keyProtag) {
                            fighterMap.get(str).resetFighter();
                        }
                        for (String str : keyEnemy) {
                            fighterMap.get(str).resetFighter();
                        }
                        numTurn = -1;
                    }
                }
                else {
                    Game.print("but " + protag.getName() + " has stepped up to fight.", 4);
                }
            }
            numTurn++;
        }


        //reset both sides.
        for (String str : keyProtag) {
            fighterMap.get(str).resetFighter();
        }
        for (String str : keyEnemy) {
            fighterMap.get(str).resetFighter();
        }

        return protagWins;

    }


    //status effects are added to statusEffects LL with order logic already calculated, so just have to print here
    //LL makes adding to the front and back O(1)
    static public void printStatusEffects(Fighter f) {
        System.out.print(f.getName() + " Status Effects: ");
        if (!f.getStatusEffects().isEmpty()) {
            System.out.println();
            for (StatusEffect se : f.getStatusEffects()) {
                System.out.print((se.isBuff()) ? "Buff - " : "Debuff - ");
                System.out.println(se.getName() + " | Turns Held: " + (se.getTurnsHeld()+1) + "/" + se.getDuration() + " | " + se.getDescription());
            }
        }
        else {
            System.out.println("None");
        }
    }

    //swap is only ever ran after a canSwap check, so possible infinite loop is accounted for
    static public int swap(String[] arr, int i, Fighter[] f) {
        int newIndex = i;

        do {
            newIndex = (newIndex + 1) % arr.length;
            f[0] = fighterMap.get(arr[newIndex]);
        } while (f[0].getCurrentHealth() <= 0); //while current fighter is dead, keep swapping

        return newIndex;
    }


    static public boolean canSwap(String[] arr, Fighter currentFighter) {
        if (arr.length <= 1) return false;//if only one fighter (cant swap)
        for (var s : arr) {
            if (fighterMap.get(s).equals(currentFighter)) continue;
            if (fighterMap.get(s).getCurrentHealth() > 0) return true;//once you find someone who is not the current fighter that is alive then show you can swap
        }
        return false;
    }

    static void displayInfo(Fighter actingFighter, double preHealthA, int prePowerA, Fighter passiveFighter, double preHealthP, int prePowerP) {
        if (actingFighter.getCurrentPower() != prePowerA) 
            System.out.println(actingFighter.getName() + " Power: " +  prePowerA + " -> " + actingFighter.getCurrentPower());
        if (actingFighter.getCurrentHealth() != preHealthA)
            System.out.println(actingFighter.getName() + " HP: " +  preHealthA + " -> " + actingFighter.getCurrentHealth());

        System.out.println();
        
        if (passiveFighter.getCurrentPower() != prePowerP)
            System.out.println(passiveFighter.getName() + " Power: " + prePowerP + " -> " + passiveFighter.getCurrentPower());
        if (passiveFighter.getCurrentHealth() != preHealthP)
            System.out.println(passiveFighter.getName() + " HP: " + preHealthP + " -> " + passiveFighter.getCurrentHealth());
    }

}
