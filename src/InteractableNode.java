import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

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
            b.addNode(6,4, new StaticNode("T", "Table"));
            return true;
        });

        actionDict.put("Jerry", (Board b, Game g) -> {
           Game.print("...", 3);
           if (b.getBoard().get(2).get(2).getActionList().isEmpty()) {
               Game.print("You walk over to Jerry with a mean look on your face.", 4);
               Game.print("You see him enjoying, scarfing down the last bite of the last piece of the Casey's breakfast pizza.", 7);
               Game.print("You slam the table in front of him", 3);
               Game.print("BANG!", 2);

               System.out.println();
               Game.print("Riley * 'DID YOU EAT THE LAST SLICE OF THE CASEYS PIZZA!!!'", 5);
               Game.print("Jerry * 'Yeah, so?'", 3);
               Game.print("Riley * 'EVERYONE KNOWS THAT I ALWAYS GET A SLICE OF THAT!! AND ITS LITERALLY SIX IN THE MORNING!! WE ALWAYS GET LIKE 20 BOXES OF IT HOW DID YOU GO THROUGH IT ALREADY?!?!?!'", 11);
               System.out.println();

               Game.print("Jerry * 'I dunno brah.'", 3);
               Game.print("Riley * 'Man I'm even madder than that time I accidentally payed to decorate my base in 3V3V3V3 GOGOATED for 1000 vbucks.'", 10);
               Game.print("Jerry * 'Well I don't care man, you can go eat the oatmeal, I know that's still there.'", 6);
               System.out.println();

               Game.print("Riley * 'OATMEAL!?! NOW YOU'VE REALLY DONE IT!!'", 4);

               Game.sPrint("This is another type of battling: Turn Based Battling.");
               Game.sPrint("Here two teams can battle it out where the team to reduce the health of all the opposing team's members to 0 will win.");
               Game.sPrint("To keep it simple, this will just be a 1V1.");
               System.out.println();

               TurnBasedBattleManager.doBattle(new String[]{"riley"}, new String[]{"jerry"}, true);

               Game.endText();
               Game.sPrint("Wow, you did it!");
               Game.print("Well, now you stand over Jerry's body as you feel satisfied with your work", 5);
               Game.print("I never said you were the hero of this story.", 4);
               System.out.println();

               Player p = g.getPlayer();

               Floor f = new Floor();
               b.addNode(5, 2, f);
               p.teleport(b,g,5,2);
               return true;
           }
           else {
               //what happens if you talk to jerry before going to breakfast
               Game.print("You walk over to Jerry and see him enjoying his breakfast.", 4);
               Game.print("Jerry * 'Yo'", 3);
               Game.print("You realize that you had no reason to walk up to him, so you turn back around.", 6);
               return false;
           }

        });

        actionDict.put("Table to Put Vials On", (Board b, Game g) -> {
            Game.print("...", 3);
            Game.print("You look over at the empty table", 3);
            Game.print("You take the many vials out from the pockets of your lab coat", 5);
            System.out.println();
            Game.print("They came in many colors and had long, hard to pronounce names.", 5);
            Game.print("You assumed they came from Dr. Nelson's remote, industrial sized laboratory where he produces his important work.", 6);
            Game.print("You place the vials on the table.", 3);

            g.getPlayer().getInventory().removeItem(Inventory.getItemFromDictionary("vial"));

            StaticNode newVialTable = new StaticNode("V", "Table with the Vials on It");
            b.addNode(5, 6,  newVialTable);

            return true;
        });

        actionDict.put("Doctor Robot", (Board b, Game g) -> {
            g.print("...", 3);
            Game.print("You walk up to the robot and it scowls back at you", 4);
            System.out.println();
            Game.print("Doctor Robot * 'Can't you see I am busy?'", 3);
            Game.print("Riley * 'You don't seem that busy.'", 3);
            Game.print("Doctor Robot * 'What do you require sir?'", 3);
            System.out.println();
            Game.print("Riley * 'How is my father's condition?'", 3);
            Game.print("Doctor Robot * 'I am afraid it is worsening.'", 3);
            Game.print("Doctor Robot * 'At this rate, if we do not do something effective soon, his bones may disintegrate entirely.'", 6);
            System.out.println();
            Game.print("Riley * 'Oh no, that's not good.'", 3);
            Game.print("Doctor Robot * 'Precisely, that would mean your dumbass would inherit this place, and in turn me!'", 5);
            Game.print("Riley * 'Are we being for real? Anyways, where is he now?'", 4);
            System.out.println();
            Game.print("Doctor Robot * 'He is undergoing an experimental treatment in his remote laboratory, he will return within the hour.'", 6);
            Game.print("Riley * 'Okay, well I will come back and check up on him once I get back from presenting at the Tech Fair.'", 5);

            return true;
        });

        actionDict.put("Sunlight Chamber", (Board b, Game g) -> {
            Game.print("...", 3);
            Game.print("You look over at the metal cast just about the size of you.", 4);
            Game.print("It has a hinge and can open, revealing an array of panels that display light that mimics the sun.", 5);
            Game.sPrint("This is your sunlight chamber, since you never leave the lab and do not get enough sunlight, you have a sunlight chamber you can climb in to get the required Vitamin-D to survive and not look like a vampire.");
            System.out.println();
            Game.print("That is literal troll behavior though.", 3);
            return true;
        });

        actionDict.put("Burger", (Board b, Game g) -> {
            Game.print("...", 3);
            Game.print("You look over at the table and see a perfectly fine burger sitting on a plate.", 4);
            Game.print("Oddly enough, the only invention of yours that has \"worked\": a perfect cheeseburger.", 4);
            Game.print("There is nothing special about it, no gimmick, it is just a perfect cheeseburger.", 4);

            ArrayList<String> options = new ArrayList<>(Arrays.asList("Eat it", "Move along"));
            if (Game.basicGameLoop(options) == 2) {
                return true;
            }

            Game.print("Hold on now, what do you mean 'Eat it'?", 3);

            options.clear();
            options.add("I mean what I mean");
            options.add("Move along");
            if (Game.basicGameLoop(options) == 2) {
                return true;
            }

            Game.print("Might I remind you, this is the only invention that has worked for you EVER", 4);

            options.remove("I mean what I mean");
            options.add("Eat it");
            if (Game.basicGameLoop(options) == 2) {
                return true;
            }

            Game.print("Okay, but just so you know this is a PERFECT burger.", 3);
            Game.print("I mean it might even be a crime to ruin this work of art.", 3);

            if (Game.basicGameLoop(options) == 2) {
                return true;
            }

            Game.print("Are you sure?", 3);

            options.clear();
            options.add("Yes");
            options.add("No");

            if (Game.basicGameLoop(options) == 2) {
                return true;
            }

            Game.print("Are you sure you are sure?", 3);

            if (Game.basicGameLoop(options) == 2) {
                return true;
            }

            Game.print("Are you really sure?", 3);

            if (Game.basicGameLoop(options) == 2) {
                return true;
            }

            Game.print("Okay fine, since it seems you really really want it.", 3);
            Game.print("You pick up the perfect burger sitting on the table, your one working invention, and your fatass eats it.", 5);

            options.clear();
            options.add("Yum");
            Game.basicGameLoop(options);

            Game.print("You did not in fact enjoy it because it has been sitting on your lab table for the past 3 months.", 4);
            return true;
        });

        actionDict.put("Love Finding Device", (Board b, Game g) -> {
            g.print("...", 3);
            Game.print("You look over at the odd shaped device on the table.", 3);
            Game.print("It is red and heart-shaped with a screen in the middle.", 3);
            Game.print("It is a love finding device, it detects all the possible romantic matches there are in the world for someone.", 5);
            System.out.println();
            Game.print("However you have decreed that it does not work because when you tried it on yourself, the machine never registered anyone on the planet.", 6);
            return true;
        });

        actionDict.put("Two Sided Pancake Cooker", (Board b, Game g) -> {
            Game.print("...", 3);
            Game.print("One morning when you were cooking pancakes, you had a brilliant idea: in order to speed things up what if you added another side to the pan.", 6);
            Game.print("Thus, the two sided pancake cooker was born.", 3);
            Game.print("No longer did you have to flip the pancakes, all you had to do was press both heated sides onto the batter and it cooked perfectly.", 5);
            System.out.println();
            Game.print("You even thought of the nice idea of adding designs onto each of the sides to give the pancakes a bit of life.", 4);
            Game.print("Your favorite design was the grid design that placed a grid of squares on your pancakes.", 4);
            Game.print("It was around this point when you realized you accidentally invented a waffle maker instead.", 4);
            return true;
        });

        actionDict.put("Portal Pocket Pants", (Board b, Game g) -> {
            Game.print("...", 3);
            Game.print("Ooh this one is a good one.", 3);
            Game.print("One day, you realized your pockets were not deep enough since you couldn't fit enough sauce packets while smuggling them out of the employee common room.", 7);
            Game.print("So you decided to make pants with bottomless pockets by exploiting a pocket dimension.", 4);
            System.out.println();
            Game.print("The only problem was you never found a way to access a pocket dimension.", 4);
            Game.print("So right now, they are just regular pants.", 3);
            return true;
        });

        actionDict.put("Riley's Addiction Relief Pills", (Board b, Game g) -> {
            Game.print("...", 3);
            Game.print("Taking after your father, you got into the field of medicine", 4);
            Game.print("On the bottle it read:", 1);
            Game.print("Take the necessary amount by mouth twice a day for a week to cure your addiction. Works on any addiction.", 5);
            System.out.println();
            Game.print("And in fine print it read:", 1);
            Game.sPrint("Side effects may include: Tuberculosis, the common cold, blood or vision loss, easy bruising, diabetes types one; two; and three, loss in win streak, more addiction, becoming a communist, becoming a clown, becoming a communist clown, memory loss, growing another arm, brain damage, brain cell loss, and temporary death.");
            Game.print("You put them in your pocket.", 3);
            g.getPlayer().getInventory().addItem(Inventory.getItemFromDictionary("pills"));
            System.out.println();
            Game.print("Added 'Riley's Addiction Relief Pills' to your inventory.", 3);
            return true;
        });

        actionDict.put("Generator Cast", (Board b, Game g) -> {
            Game.print("...", 3);
            Game.print("You look on the table and see a machine of metal about the size of your torso.", 4);
            Game.sPrint("This is the cast that is supposed to regulate the Golden Electricity Generator by collapsing the wave function of the quantumly superpositioned electrons so that it does not get out of hand.");
            Game.print("However, you could not get it done in time because you procrastinated until last night to start so you are just going to leave it out.", 5);
            System.out.println();
            Game.print("Whoops.", 3);
            return true;
        });

        actionDict.put("Golden Electricity Generator", (Board b, Game g) -> {
            Game.print("...", 3);
            Game.print("You look over at your prized possession.", 3);
            Game.print("The invention that is going to give you your big break.", 4);
            Game.print("People are really going to drop dead once they feel its power.", 4);
            System.out.println();
            Game.print("But for now, you have to get it to the Tech Fair.", 3);
            Game.print("You put in your pocket.", 3);
            g.getPlayer().getInventory().addItem(Inventory.getItemFromDictionary("geg"));
            System.out.println();
            Game.print("Added 'Golden Electricity Generator' to your inventory.", 3);
            b.addNode(4, 4, new Floor());
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
        if (actionDict.get(inGameid).doAction(board, game)) actionList.clear();
        return returnArray;
    }
}
