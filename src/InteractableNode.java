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
        actionDict.put("Nelson Labs Sign", (Board b, Game g) -> {
            Game.print("Sign * Nelson Labs Next Right", 3);
            return false;
        });
        actionDict.put("Lobby Attendant", (Board b, Game g) -> {
            Game.print("...", 3);
            Game.print("Attendant * 'Hello, are you here to sign into the Nelson Labs's Tech Fair today?'", 4);
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
        actionDict.put("Food Court Table", (Board b, Game g) -> {
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
        actionDict.put("Poster", (Board b, Game g) -> {
            Game.print("...", 3);
            Game.print("You look at the poster on the wall and it was of the city's favorite superhero: The Mighty Shine.", 5);
            Game.print("You heard stories about him when you were a kid and learned about him in school.", 4);
            Game.print("Everyone knew who he was: America's Greatest Hero.", 3);
            return true;
        });
        actionDict.put("Hiring Manager", (Board b, Game g) -> {
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
            Game.print("Well, at the end of the day, there is still the free Raising Cane's, perhaps that will lift your spirits.", 7);

            return true;
        });

        actionDict.put("Breakfast Bar", (Board b, Game g) -> {
            var board = b.getBoard();
            for (ArrayList<Nodeable> row : board) {
                if (row.get(2).getInGameid().equals("Breakfast Bar")) {
                    row.get(2).getActionList().clear();
                }
            }

            Game.print("...", 3);
            Game.print("You walk up to the breakfast bar, the best invention to come out of Nelson Labs, with a crazed look in your eye and a rumble in your stomach.", 10);
            Game.print("It had all you could ever want: eggs, pancakes, waffles, cereal, toast, grits, oatmeal, yogurt, fruit, bagels, milk, coffee, orange juice, biscuits, gravy, fish, chips, quiches, squid, bacon, Jimmy Johns, McRyan's, mysterious goop, hashbrowns, and sausages.", 20);
            Game.print("However, it had the best item of all,", 4);
            System.out.println();
            Game.print("Your most favorite breakfast option ever,", 4);
            Game.print("Casey's Breakfast Pizza", 3);
            Game.print("But when you went to go pickup a nice 10 or so slices of it, you find there are none left.", 7);
            System.out.println();
            Game.print("Left heartbroken, you look around in distraught when you see the sleazy sleazeball that took the last slice:", 8);
            Game.print("That gosh darn Jerry.", 3);

            return false;
        });

        actionDict.put("Bo Staff", (Board b, Game g)-> {
            Game.print("...", 3);
            Game.print("You walk up to the stand for your Bo Staff which you try and practice daily.", 5);
            Game.print("Its more like weekly now.", 3);
            Game.print("You have been practicing ever since you took karate as a kid, but you did not keep up with any of the other practices you learned there", 9);
            System.out.println();
            Game.print("It was the only thing that you were sort of good at, so you still practice to this day.", 5);
            Game.print("You try and ignore the fact that it is a glorified stick.", 4);

            return true;
        });

        actionDict.put("Photo Wall", (Board b, Game g) -> {
            Game.print("...", 3);
            Game.print("You look at the wall and see a purposeful blank space.", 4);
            Game.print("This was despite the fact that all of your walls are blank.", 4);
            Game.print("This spot is meant for all the photos that you will take with your friends.", 5);
            System.out.println();
            Game.print("The only problem is that you haven't made any of those yet.",4);

            return true;
        });

        actionDict.put("Jukebox", (Board b, Game g) -> {
            Game.print("...", 3);
            Game.print("You look down at the old jukebox in the corner of your room.", 5);
            Game.print("Since you refuse to make monthly payments for music apps, you decided to get an old jukebox at a garage sale.", 8);
            Game.print("However, the only downside was that it could only play obscure hits from before 1980.", 6);
            return true;
        });

        actionDict.put("Nightstand", (Board b, Game g) -> {
            Game.print("...", 3);
            Game.print("You look over at your nightstand.", 3);
            Game.print("It has a lamp with no bulb and a potted cactus plant on it.", 4);
            Game.print("You put the cactus in your pocket.", 3);
            g.getPlayer().getInventory().addItem(Inventory.getItemFromDictionary("cactus"));
            System.out.println();
            Game.print("Added 'Cactus' to your inventory.", 3);
            return true;
        });

        actionDict.put("Package", (Board b, Game g) -> {
            Game.print("...", 3);
            Game.print("You approach the mysterious package sitting on the Common Room table.", 5);
            Game.print("You check the note placed on top of it and see that it is a shipment of chemicals meant for your father.", 7);
            Game.print("You open the package and put the contents in your pocket.", 4);
            System.out.println();
            Game.print("Added 'Medicine Vail' to your inventory.", 3);
            g.getPlayer().getInventory().addItem(Inventory.getItemFromDictionary("vial"));
            return true;
        });

        actionDict.put("Jerry", (Board b, Game g) -> {
           Game.print("...", 3);
           if (b.getBoard().get(2).get(2).getActionList().isEmpty()) {
               Game.print("You walk over to Jerry with a mean look on your face.", 4);
               Game.print("You see him enjoying, scarfing down the last bite of the last piece of the Casey's breakfast pizza.", 7);
               Game.print("You slam the table in front of him", 3);
               Game.print("BANG", 2);

               System.out.println();
               Game.print("Riley * 'DID YOU EAT THE LAST SLICE OF THE CASEYS PIZZA!!!'", 5);
               Game.print("Jerry * 'Yeah, so?'", 3);
               Game.print("Riley * 'EVERYONE KNOWS THAT I ALWAYS GET A SLICE OF THAT!! AND ITS LITERALLY SIX IN THE MORNING!! WE ALWAYS GET LIKE 20 BOXES OF IT HOW DID YOU GO THROUGH IT ALREADY?!?!?!'", 11);
               System.out.println();

               Game.print("Jerry * 'I dunno brah.'", 3);
               Game.print("Riley * 'Man I'm even madder than that time I accidentally decorated my base in 3V3V3V3 GOGOATED for 1000 vbucks.'", 10);
               Game.print("Jerry * 'Well I don't care man, you can go eat the oatmeal, I know that's still there.'", 6);
               System.out.println();

               Game.print("Riley * 'OATMEAL!?! NOW YOU'VE REALLY DONE IT!!'", 4);

               Game.sPrint("This is another type of battling: Turn Based Battling.");
               Game.sPrint("Here two teams can battle it out where the team to reduce the health of all the opposing team's members to 0.0 will win.");
               Game.sPrint("To keep it simple, this will just be a 1V1.");
               System.out.println();

               TurnBasedBattleManager.doBattle(new String[]{"riley"}, new String[]{"jerry"}, true);

               Game.endText();
               Game.sPrint("Wow, you did it!");

               

           }
           else {
               //what happens if you talk to jerry before going to breakfast
               Game.print("You walk over to Jerry and see him enjoying his breakfast.", 4);
               Game.print("Jerry * 'Yo'", 3);
               Game.print("You realize that you had no reason to walk up to him, so you turn back around.", 6);
               return false;
           }


            return false;
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
        if (actionDict.get(inGameid).doAction(board, game)) actionList.clear();
        return returnArray;
    }
}
