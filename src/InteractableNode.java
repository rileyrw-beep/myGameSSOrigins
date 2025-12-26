import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InteractableNode implements Nodeable{
    private String displayid;
    private String inGameid;
    private boolean canMoveTo;
    private ListOfNodes type;
    private ArrayList<String> actionList;
    static private Map<String, Interactable> actionDict;

    static {
        actionDict = new HashMap<>();
        actionDict.put("Sign", (Board b) -> {
            Game.print("Sign * Nelson Lab's Next Right", 3);
            return false;
        });
        actionDict.put("Lobby Attendant", (Board b) -> {
            Game.print("...", 3);
            Game.print("Attendant * 'Hello, are you here to sign into the Nelson Lab's Tech Fair today?'", 4);
            Game.print("Dalton * 'Yes, I am here looking for a job, do you know where the sign up booth is?'", 5);
            Game.print("Attendant * 'Why yes, it will be inside the main room in the back right.'", 4);

            System.out.println();
            Game.print("The Lobby Attendant then places the temporary ID around your neck so that you can be let in.", 5);
            Game.print("The ID tingles a bit, giving you a strange sensation but you ignore it.", 4);
            Game.print("Dalton * 'Thank you, have a nice day.'", 3);
            System.out.println();

            Game.print("You turn to enter the double doors when you hear the attendant say one more thing:", 4);
            Game.print("Attendant * 'Also don't forget, if you visit each table and get a ticket from listening to their presentation, you can get free Cane's.'", 6);
            Game.print("At the sound of this, you lock in.", 3);
            System.out.println();

            Game.print("A job AND free lunch, who could resist?", 3);
            Game.print("Now go through the doors and participate in the fair.", 4);
            return true;
        });
        actionDict.put("Food Court Table", (Board b) -> {
            Game.print("...", 3);
            Game.print("You approach the food court worker with a grumble in your stomach.", 4);
            Game.print("You decide to order the classic grilled cheese.", 3);
            Game.print("Food Court Worker * 'Alright, that will be $18 sir.'", 3);
            System.out.println();

            Game.print("Dalton * 'Woah hold on now, there must be a misunderstanding, $18?!?!'", 4);
            Game.print("Food Court Worker * 'Yep.'", 3);
            Game.print("Dalton * 'Okay fine.'", 3);
            System.out.println();

            Game.print("However, when you reached into your wallet, you find only $5.", 3);
            Game.print("Dalton * 'Sorry.", 3);
            Game.print("The Food Court Worker then kicks you out and you continue on your way.", 4);
            return true;
        });
        actionDict.put("Poster", (Board b) -> {
            Game.print("...", 3);
            Game.print("You look at the poster on the wall and it was of the city's favorite superhero: The Mighty Shine.", 5);
            Game.print("You heard stories about him when you were a kid and learned about him in school.", 4);
            Game.print("Everyone knew who he was: America's Greatest Hero.", 3);
            return true;
        });
        actionDict.put("Hiring Manager", (Board b) -> {
            Game.print("...", 3);
            Game.print("You walk up to the hiring manager and awkwardly stand there.", 4);
            System.out.println();
            Game.print("Dalton * 'Hello?'", 3);
            Game.print("Hiring Manager * 'Alright, hand over your resume.'", 3);
            System.out.println();
            Game.print("Resume? You had no idea you needed one for a job.", 3);
            System.out.println();
            Game.print("Dalton * 'Uhh, I don't have one.'", 3);
            Game.print("Hiring Manager * 'Oof that's too bad.'", 3);
            Game.print("Dalton * 'Hold on wait, can we do the interview part at least?'", 4);
            Game.print("Hiring Manager * 'Fine I guess.'", 3);
            System.out.println();
            Game.print("Hiring Manager * 'First, what are you applying for.'", 4);
            Game.print("Dalton * 'Whatever's available'", 3);
            Game.print("Hiring Manager * 'Okay, now first question: If you were an animal, what would you be?'", 5);

            System.out.println();
            ArrayList<String> optionList = new ArrayList<>();
            optionList.add("Wait what?");
            optionList.add("Uhh, dog I guess?");
            optionList.add("Hmm, a bird.");
            optionList.add("Probably a walrus.");
            optionList.add("Definitely a cat.");
            optionList.add("100% a pink fairy armadillo.");
            Game.basicGameLoop(optionList);
            System.out.println();

            Game.print("Hiring Manager (with a concerned look) * 'Mhm. Okay, now if you just won the lottery, what color would you bet it all on?'", 5);

            System.out.println();
            optionList.clear();
            optionList.add("Hold on, huh?");
            optionList.add("ALL ON RED");
            optionList.add("ALL ON BLACK");
            Game.basicGameLoop(optionList);
            System.out.println();

            Game.print("Hiring Manager (under her breath) * 'Oof, I dunno if I would have done that.'", 4);
            Game.print("Hiring Manager * 'Okay, now if you were a pizza man, how would you benefit from scissors?'", 5);

            System.out.println();
            optionList.clear();
            optionList.add("Whaaaa");
            optionList.add("I guess I would cut the pizza with them?");
            optionList.add("Wait is there pizza in this job?");
            Game.basicGameLoop(optionList);
            System.out.println();

            Game.print("Hiring Manager (shaking her head) * 'Alright, and final question, why should we hire you?'", 5);
            Game.print("This is a free response question:", 1);
            Game.userInput();

            Game.print("The Hiring Manager looks at you with a cold, indifferent look.", 3);
            Game.print("Hiring Manager * 'Thanks for your time sir, we'll contact you within the next business week. Goodbye now.'", 6);
            Game.print("Dalton Young * 'But I didn't give you any contact information.'", 3);
            System.out.println();

            Game.print("Hiring Manager * 'That's ok sir. Goodbye now.'", 3);
            Game.print("You walk away realizing you are NOT getting the job. Drats.", 4);
            Game.print("Well, at the end of the day, there is still the free Raising Cane's, perhaps that will lift your spirits.", 5);

            return true;
        });

    }


    public InteractableNode(String g, String d, String action) {
        displayid = d;
        inGameid = g;
        type = ListOfNodes.NPC;
        canMoveTo = false;
        actionList = new ArrayList<String>();
        actionList.add(action);
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

    public ArrayList<String> getActionList() {
        return actionList;
    }

    public boolean[] performAction(String response, Board board, Game game) {
        boolean[] returnArray = {true, false};
        if (actionDict.get(inGameid).doAction(board)) actionList.clear();
        return returnArray;
    }
}
