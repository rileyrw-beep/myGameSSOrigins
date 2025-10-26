import java.util.ArrayList;
import java.util.Scanner;


public class Kelly implements Nodeable {
    private final String displayid;
    private final String inGameid;
    private final boolean canMoveTo;
    private final ListOfNodes type;
    private final ArrayList<String> actionList;
    private final Scanner input;
    private int kellyCounter;

    public Kelly() {
        displayid = "K";
        inGameid = "Kelly the Cat";
        type = ListOfNodes.KELLY;
        canMoveTo = false;
        actionList = new ArrayList<String>();
        actionList.add("Pet Kelly");
        input = new Scanner(System.in);
        kellyCounter = 0;
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

    public ListOfNodes getType() {
        return type;
    }

    //actionDecider 0
    public boolean[] performAction(String response, Board board, Game game) {
        boolean[] returnArray = {true, false};

        if (response.equals("Pet Kelly")) {
            ArrayList<String> optionList = new ArrayList<String>();
            optionList.add("Try Again");
            optionList.add("Get Up to Leave");
            if (kellyCounter == 0) {
                System.out.println("...");
                System.out.println();

                game.time(3);

                System.out.println("You walk over to the little feline and hunch down to get a better look.");
                System.out.println();

                game.time(3);

                System.out.println("You extend your hand hoping to land a few pets on her head when she unexpectedly bites at you.");
                System.out.println();

                game.time(4);

                System.out.println("Perhaps Kelly is not feeling pets right now. Regardless, you need to get to the Tech Fair. You should go there now");
                System.out.println();

                game.time(5);


                String responseKelly = game.basicGameLoop("", optionList);
                kellyCounter++;
                if (responseKelly.equals("Get Up to Leave")) {
                    System.out.println("Good choice. You give Kelly one last look before standing up to begin your journey.");
                    System.out.println();
                    return returnArray;
                }

            }
            if (kellyCounter == 1) {
                System.out.println();
                System.out.println("...");
                System.out.println();
                game.time(3);

                System.out.println("Alright I'll let you try one more time, but you know this is not going to go anywhere.");
                game.time(4);
                System.out.println();

                System.out.println("You extend your hand, again, and you attempt to pet her soft, little head. She rears back, eyes darting, clearly not wanting your friendly pets.");
                game.time(5);
                System.out.println();

                System.out.println("I'm warning you, this cat does NOT want to be pet right now. You should make your way to the door, the Tech Fair won't last all day.");
                game.time(4);
                System.out.println();

                String responseKellyTwo = game.basicGameLoop("", optionList);
                kellyCounter++;
                if (responseKellyTwo.equals("Get Up to Leave")) {
                    System.out.println("Good choice. You give Kelly one last look before finally standing up to begin your journey.");
                    System.out.println();
                    return returnArray;
                }

            }
            if (kellyCounter == 2) {
                System.out.println();
                System.out.println("...");
                System.out.println();
                game.time(3);

                System.out.println("You know, I cannot help you any more. For some insane reason you are STILL trying to pet this cat.");
                System.out.println();
                game.time(4);

                System.out.println("You stupidly reach out your hand again, expecting it to go well despite time after time failing.");
                System.out.println();
                game.time(4);

                System.out.println("She backs into the shadows, her outline looks more like an eldritch beast rather than an average housecat.");
                System.out.println();
                game.time(4);

                System.out.println("As you attempt to pet her, she lashes out, barely missing your arm.");
                System.out.println();
                game.time(2);

                String responseKellyThree = game.basicGameLoop("", optionList);
                kellyCounter++;
                if (responseKellyThree.equals("Get Up to Leave")) {
                    System.out.println("Thank the lord. You give the demented Kelly one last look before finally standing up to begin your journey.");
                    game.time(4);
                    System.out.println();
                    System.out.println("I was afraid if you stayed one more second you would not have made it out of there!");
                    game.time(3);
                    System.out.println();
                    return returnArray;
                }

            }
            if (kellyCounter==3) {
                System.out.println();
                System.out.println("...");
                System.out.println();
                game.time(3);

                System.out.println("Of course, you decide to try again. There must be seriously something wrong with you if you can't seem to get the HINTS I have been trying to tell you.");
                game.time(5);
                System.out.println();

                System.out.println("Not only is this a waste of time, but you have been ACTIVELY PUTTING YOUR LIFE IN DANGER!");
                game.time(4);
                System.out.println();

                System.out.println("But alas, I am simply the Narrator, and now I must narrate.");
                game.time(3);
                System.out.println();

                System.out.println("Kelly, now a seemingly 10 foot tall nightmare of a cat, stands with eyes that pierce your soul. Of course, you jump with open arms aiming to land just one pet on your cat's head.");
                game.time(6);
                System.out.println();

                return game.gameOver("You land in Kelly's gaping jaws as she viciously eats you. It seems curiosity, in this case, fed the cat.", false, Ending.BAD_ENDING);
            }
        }
        returnArray[0] = false;
        return returnArray;
    }

    public ArrayList<String> getActionList() {
        return actionList;
    }
}
