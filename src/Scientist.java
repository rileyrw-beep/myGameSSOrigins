import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class Scientist implements Nodeable{
    private String displayid;
    private String inGameid;
    private final boolean canMoveTo;
    private final ListOfNodes type;
    private ArrayList<String> actionList;
    private static int count = 0;
    private final int x;
    private final int y;



    public Scientist(int a, int b) {
        displayid = "S";
        inGameid = "Scientist";
        type = ListOfNodes.WALL;
        canMoveTo = false;
        actionList = new ArrayList<String>();
        actionList.add("Talk to Scientist");
        x = a;
        y = b;
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
        game.print("...", 3);
        game.print("You go over to the scientist standing at the table, not happy at all that you have to listen to them.", 5);
        game.print("But, the sweet promise of Cane's kept you going.", 3);
        System.out.println();


        if (x == 1 && y == 2) { //B46 Left
            game.print("As you walk up to this scientist, in your head, you think he looks like a nerdy looking nerd.", 4);
            game.print("But you keep your mouth shut as he begins to talk.", 3);
            game.print("Hey everyone, I have been researching the targeting of Signaling Molecules of P. aeruginosa by Using Mucin as an Anti-Quorum Sensing Drug...", 4);
            System.out.println();
            game.print("Just as he begins to speak, it was so boring that you doze off for a bit.", 3);
            game.print("At the end, the people around you clap and you jolt awake.", 3);
            game.print("You get your ticket and you take your leave.", 3);
            System.out.println();
            Item ticket = new Item("Ticket", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.");
            game.getPlayer().getInventory().addItem(ticket);
            game.print("Added 'Ticket' to Inventory", 3);
            count++;
            actionList.clear();
            return returnArray;
        }
        else if (x == 8 && y == 2) { //B46 Right
            game.print("As you walk up to this scientist, in your head, you think he looks like the type of guy to edit Wikipedia.", 4);
            game.print("But you keep your mouth shut as he begins to talk.", 3);
            game.print("Scientist * 'Hey guys, let me tell you a bit about my project about the Mechanistic Basis for the Analysis of SARS-CoV-2 Omicron Variant Severity...'", 5);
            System.out.println();
            game.print("Just as he begins to speak, you see a fly buzzing past his head and focus on that for a second.", 4);
            game.print("Before you know it, the group around you is clapping and you snap back to reality.", 4);
            game.print("You get your ticket and you take your leave.", 3);
            System.out.println();
            Item ticket = new Item("Ticket", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.");
            game.getPlayer().getInventory().addItem(ticket);
            game.print("Added 'Ticket' to Inventory", 3);
            System.out.println();
            count++;
            actionList.clear();
            return returnArray;
        }
        else if (x == 2 && y == 3) { //B35 Top
            game.print("As you walk up to this scientist, in your head, you think he looks (and smells) like the type of guy to have not showered in 3 months.", 5);
            game.print("But you keep your mouth shut as he begins to talk.", 3);
            game.print("Scientist * 'Hello everybody, I'd like to present to you my presentation titled: On the Maximum Number of Spanning Trees in a Planar Graph With a Fixed Number of Edges: A Linear-Algebraic Connection...'", 7);
            System.out.println();
            game.print("As he begins to speak, you decide now is a great time for a really long blink.", 4);
            game.print("When you were done blinking, the scientist was done.", 3);
            game.print("You get your ticket, holding your nose, and take your leave.", 3);
            System.out.println();
            Item ticket = new Item("Ticket", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.");
            game.getPlayer().getInventory().addItem(ticket);
            game.print("Added 'Ticket' to Inventory", 3);
            System.out.println();
            count++;
            actionList.clear();
            return returnArray;
        }
        else if (x == 2 && y == 7) { //B35 Bottom
            game.print("As you walk up to this scientist, in your head, you think that he looks like he doesn't belong here. He looks older than every other scientist and has a tinfoil hat on his head.", 7);
            game.print("You greet him and he smiles, you are the only one who went to his presentation.", 4);
            System.out.println();
            game.print("Scientist * 'You there sir!'", 2);
            game.print("Dalton (looking around) * 'Me?'", 2);
            game.print("Scientist * 'Yes you! You are familiar with the Big Bang theory, yes?'", 4);
            System.out.println();
            game.print("Dalton * 'I guess yeah.'", 2);
            game.print("Scientist * 'Well I am a specialist in the field, and I propose a new theory of the origin of the universe.'", 7);
            game.print("Dalton (nodding) * 'Uh huh'", 2);
            System.out.println();
            game.print("Scientist * 'You see, what I know really happened (showing slides on a computer) is the Great Diarrhea.'", 6);
            game.print("Scientist * 'Long ago, before the Dawn of Creation, there was a large man and an even larger toilet.'", 6);
            game.print("Scientist * 'And this man had explosive diarrhea and had just eaten $30's worth of Taco Bell.'", 5);
            System.out.println();
            game.print("Scientist * 'When that man sat down on the toilet to explosive diarrhea, it was a diarrhea so big and so great that it crated the Universe as we know it.'", 9);
            game.print("Dalton * 'Wow.'", 4);
            game.print("Dalton * 'That's gotta be the greatest thing I've heard all day.'", 4);
            game.print("Scientist * 'Hey thanks man, I don't get a lot of love for my theory. In fact, here is a little figurine to remember me by.'", 7);
            System.out.println();
            Item TGD = new Item("The Great Diarrhea Figurine", "This is a figurine depicting the origin of the Universe. A large man with explosive diarrhea ate too much Taco Bell and the result was the creation of the Universe.");
            game.getPlayer().getInventory().addItem(TGD);
            game.print("Added 'The Great Diarrhea Figurine' to Inventory", 3);
            System.out.println();
            game.print("All of a sudden, two security guards came bolting at the scientist yelling that the scientist was not supposed to be here.", 7);
            game.print("Scientist * 'Well that's my cue to leave.'", 3);
            game.print("The scientist bolts out the door leaving you standing there with the figurine in your hand.", 5);
            System.out.println();
            Floor floor = new Floor();
            board.addNode(2, 7, floor);
            count++;
            actionList.clear();
            return returnArray;
        }
        else if (x == 2 && y ==1) { //B36 Top
            game.print("As you approach this scientist, you see they are eating a sandwich absolutely covered in mayonnaise.", 5);
            game.print("They look up and see you walking to them.", 3);
            System.out.println();
            game.print("Sandwich Eating Scientist * 'Woah, woah, woah! I'm on my lunch break Buckaroo.'", 4);
            game.print("Dalton * 'Oh my bad.'", 2);
            System.out.println();
            game.print("You awkwardly stand there waiting as the scientist demolishes his mayo sandwich.", 4);
            System.out.println();
            game.print("Sandwich Eating Scientist * 'What are you still doing here? Just take the ticket.'", 4);
            game.print("You take the ticket and walk off.", 3);
            System.out.println();
            Item ticket = new Item("Ticket", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.");
            game.getPlayer().getInventory().addItem(ticket);
            game.print("Added 'Ticket' to Inventory", 3);
            System.out.println();
            count++;
            actionList.clear();
            return returnArray;
        }
        else if (x == 2 && y == 4) { //B36 Bottom
            game.print("You approach this scientist, as you do so you think to yourself that he looks quite funny with his bald head but long, neatly kept beard and mustache.", 6);
            game.print("You decide to give him the benefit of the doubt and listen to his presentation: ", 4);
            System.out.println();
            game.print("Scientist * 'Alright guys, thanks for coming. Today I will present my thesis about how Jet Biggington was the true hero of New York City.'", 5);
            game.print("At that moment you jump up and punch the guy for having such an L opinion.", 4);
            game.print("People around look at you, but nobody objects." , 3);
            System.out.println();
            game.print("You take your ticket and take your leave", 3);
            System.out.println();
            Item ticket = new Item("Ticket", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.");
            game.getPlayer().getInventory().addItem(ticket);
            game.print("Added 'Ticket' to Inventory", 3);
            System.out.println();
            count++;
            actionList.clear();
            return returnArray;
        }
        else if (x == 6 && y == 1) { //B56 Top
            game.print("As you walk up to this scientist, in your head, you think he looks like the type of guy to cartoonishly float towards a good smelling pie.", 6);
            game.print("But you keep your mouth shut as he begins to talk.", 3);
            game.print("Scientist * 'Alright guys, I am here to present my thesis on how Jared Leto is the Franchise Killer.'", 5);
            System.out.println();
            game.print("Just then the entire crowd began giving him a standing ovation that lasted 17 minutes.", 4);
            game.print("He was so flattered he ended at that and gave everyone tickets.", 4);
            System.out.println();
            Item ticket = new Item("Ticket", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.");
            game.getPlayer().getInventory().addItem(ticket);
            game.print("Added 'Ticket' to Inventory", 3);
            System.out.println();
            count++;
            actionList.clear();
            return returnArray;
        }
        else if (x == 6 && y == 4) { //B56 Bottom
            game.print("As you approach this scientist, you think to yourself that they look like the dog from Mr. Peabody and Sherman.", 4);
            game.print("But as you got closer, you realized it was just a cardboard cut out of them with a note saying 'I coulnd't make it in today, press the button to hear my presentation.'", 6);
            game.print("You are now pressed with a choice: ", 3);
            ArrayList<String> optionList1 = new ArrayList<>();
            optionList1.add("Press the Button to Hear the Recording.");
            optionList1.add("Take the Ticket and Leave.");
            int get = game.basicGameLoop(optionList1);
            if (get == 2) {
                game.print("You decide to take the easier option and take the ticket without listening.", 4);
                game.print("Nice.", 1);
                Item ticket = new Item("Ticket", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.");
                game.getPlayer().getInventory().addItem(ticket);
                game.print("Added 'Ticket' to Inventory", 3);
                System.out.println();
                count++;
                actionList.clear();
                return returnArray;
            }
            game.print("You decide to take the morally correct route. You press on the button--", 4);
            game.print("Wait. You are actually listening to this scientist's presentation?", 3);
            optionList1.clear();
            optionList1.add("Yes");
            optionList1.add("No");
            String get2 = game.basicGameLoop("",  optionList1);
            if (get2.equals("No")) {
                game.print("That's what I thought, you bypass the button and grab a ticket.", 3);
                game.print("Nice.", 1);
                Item ticket = new Item("Ticket", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.");
                game.getPlayer().getInventory().addItem(ticket);
                game.print("Added 'Ticket' to Inventory", 3);
                System.out.println();
                count++;
                actionList.clear();
                return returnArray;
            }
            game.print("Hold on, you literally have not listened to a single single real presentation yet.", 4);
            game.print("You actually want to listen to this one?", 3);
            String get3 = game.basicGameLoop("",  optionList1);
            if (get3.equals("No")) {
                game.print("That's what I thought, you bypass the button and grab a ticket.", 3);
                game.print("Nice.", 1);
                Item ticket = new Item("Ticket", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.");
                game.getPlayer().getInventory().addItem(ticket);
                game.print("Added 'Ticket' to Inventory", 3);
                System.out.println();
                count++;
                actionList.clear();
                return returnArray;
            }
            game.print("But you have literally no incentive to listen to this.", 3);
            game.print("You are here for the Cane's, not the science!", 3);
            ArrayList<String> optionList2 = new ArrayList<>();
            optionList2.add("I dunno man, science is pretty cool.");
            optionList2.add("I'm starting to think you don't want me to press the button.");
            optionList2.add("Ehhh, you're right.");
            int get4 = game.basicGameLoop(optionList2);
            if (get4 == 3) {
                game.print("That's what I thought, you bypass the button and grab a ticket.", 3);
                game.print("Nice.", 1);
                Item ticket = new Item("Ticket", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.");
                game.getPlayer().getInventory().addItem(ticket);
                game.print("Added 'Ticket' to Inventory", 3);
                System.out.println();
                count++;
                actionList.clear();
                return returnArray;
            }
            game.print("That's it, I give up. I was too lazy to figure out what this guy's presentation was going to be so I tried to give you an easy option so this wont take longer but OF COURSE you had to RUIN it.", 9);
            game.print("After all this do you REALLY want a presentation?", 3);
            String get5 =  game.basicGameLoop("",  optionList1);
            if (get5.equals("No")) {
                game.print("THANK YOU. You bypass the button and grab a ticket.", 3);
                Item ticket = new Item("Ticket", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.");
                game.getPlayer().getInventory().addItem(ticket);
                game.print("Added 'Ticket' to Inventory", 3);
                System.out.println();
                count++;
                actionList.clear();
                return returnArray;
            }
            game.print("Okay fine, I hope you enjoy it.", 3);
            game.print("You walk over, thinking in your head you are doing something of importance, and press the button.", 4);
            game.print("A grainy voice that speaks slower than your average Psychology professor begins to present.", 4);

            System.out.println("\n" +
                    "Hey everyone, this is my presentation, I hope you like it.\n" +
                    "SECTION I.: OF THE SEPARATION OF ARTS AND PROFESSIONS.\n" +
                    "IT is evident, that, however urged by a sense of necessity, and a\n" +
                    "desire of convenience, or favoured by any advantages of situation and\n" +
                    "policy, a people can make no great progress in cultivating the arts of\n" +
                    "life, until they have separated, and committed to different persons,\n" +
                    "the several tasks which require a peculiar skill and attention. The\n" +
                    "savage, or the barbarian, who must build and plant, and fabricate for\n" +
                    "himself, prefers, in the interval of great alarms and fatigues, the\n" +
                    "enjoyments of sloth to the improvement of his fortune: he is,\n" +
                    "perhaps, by the diversity of his wants, discouraged from industry; or,\n" +
                    "by his divided attention, prevented from acquiring skill in the\n" +
                    "management of any particular subject.\n" +
                    "The enjoyment of peace, however, and the prospect of being able to\n" +
                    "exchange one commodity for another, turns, by degrees, the hunter\n" +
                    "and the warrior into a tradesman and a merchant. The accidents\n" +
                    "which distribute the means of subsistence unequally, inclination, and\n" +
                    "favourable opportunities, assign the different occupations of men;\n" +
                    "and a sense of utility leads them, without end, to subdivide their\n" +
                    "professions.\n" +
                    "The artist finds, that the more he can confine his attention to a\n" +
                    "particular part of any work, his productions are the more perfect, and\n" +
                    "grow under his hands in the greater quantities. Every undertaker in\n" +
                    "manufacture finds, that the more he can subdivide the tasks of his\n" +
                    "workmen, and the more hands he can employ on separate articles,\n" +
                    "the more are his expences diminished, and his profits increased. The\n" +
                    "consumer too requires, in every kind of commodity, a workmanship\n" +
                    "more perfect than hands employed on a variety of subjects can\n" +
                    "produce; and the progress of commerce is but a continued\n" +
                    "subdivision of the mechanical arts.\n" +
                    "Every craft may engross the whole of a man’s attention, and has a\n" +
                    "mystery which must be studied or learned by a regular apprenticeship.\n" +
                    "Nations of tradesmen come to consist of members, who, beyond\n" +
                    "their own particular trade, are ignorant of all human affairs, and who\n" +
                    "may contribute to the preservation and enlargement of their\n" +
                    "common-wealth, without making its interest an object of their regard\n" +
                    "or attention. Every individual is distinguished by his calling, and has a\n" +
                    "place to which he is fitted. The savage, who knows no distinction but\n" +
                    "that of his merit, of his sex, or of his species, and to whom his\n" +
                    "\n" +
                    "community is the sovereign object of affection, is astonished to find,\n" +
                    "that in a scene of this nature, his being a man does not qualify him\n" +
                    "for any station whatever: he flies to the woods with amazement,\n" +
                    "distaste, and aversion.\n" +
                    "By the separation of arts and professions, the sources of wealth are\n" +
                    "laid open; every species of material is wrought up to the greatest\n" +
                    "perfection, and every commodity is produced in the greatest\n" +
                    "abundance. The state may estimate its profits and its revenues by the\n" +
                    "number of its people. It may procure, by its treasure, that national\n" +
                    "consideration and power, which the savage maintains at the expence\n" +
                    "of his blood.\n" +
                    "The advantage gained in the inferior branches of manufacture by the\n" +
                    "separation of their parts, seem to be equalled by those which arise\n" +
                    "from a similar device in the higher departments of policy and war.\n" +
                    "The soldier is relieved from every care but that of his service;\n" +
                    "statesmen divide the business of civil government into shares; and\n" +
                    "the servants of the public, in every office, without being skilful in the\n" +
                    "affairs of state, may succeed, by observing forms which are already\n" +
                    "established on the experience of others. They are made, like the parts\n" +
                    "of an engine, to concur to a purpose, without any concert of their\n" +
                    "own: and equally blind with the trader to any general combination,\n" +
                    "they unite with him, in furnishing to the state its resources, its\n" +
                    "conduct, and its force.\n" +
                    "The artifices of the beaver, the ant, and the bee, are ascribed to the\n" +
                    "wisdom of nature. Those of polished nations are ascribed to\n" +
                    "themselves, and are supposed to indicate a capacity superior to that\n" +
                    "of rude minds. But the establishments of men, like those of every\n" +
                    "animal, are suggested by nature, and are the result of instinct, directed\n" +
                    "by the variety of situations in which mankind are placed. Those\n" +
                    "establishments arose from successive improvements that were made,\n" +
                    "without any sense of their general effect; and they bring human\n" +
                    "affairs to a state of complication, which the greatest reach of capacity\n" +
                    "with which human nature was ever adorned, could not have\n" +
                    "projected; nor even when the whole is carried into execution, can it\n" +
                    "be comprehended in its full extent.\n" +
                    "Who could anticipate, or even enumerate, the separate occupations\n" +
                    "and professions by which the members of any commercial state are\n" +
                    "distinguished; the variety of devices which are practised in separate\n" +
                    "cells, and which the artist, attentive to his own affair, has invented, to\n" +
                    "abridge or to facilitate his separate task? In coming to this mighty\n" +
                    "end, every generation, compared to its predecessors, may have\n" +
                    "appeared to be ingenious; compared to its followers, may have\n" +
                    "appeared to be dull: And human ingenuity, whatever heights it may\n" +
                    "have gained in a succession of ages, continues to move with an equal\n" +
                    "pace, and to creep in making the last, as well as the first, step of\n" +
                    "commercial or civil improvement.\n" +
                    "\n" +
                    "It may even be doubted, whether the measure of national capacity\n" +
                    "increases with the advancement of arts. Many mechanical arts,\n" +
                    "indeed, require no capacity; they succeed best under a total\n" +
                    "suppression of sentiment and reason; and ignorance is the mother of\n" +
                    "industry as well as of superstition. Reflection and fancy are subject to\n" +
                    "err; but a habit of moving the hand, or the foot, is independent of\n" +
                    "either. Manufactures, accordingly, prosper most, where the mind is\n" +
                    "least consulted, and where the workshop may, without any great\n" +
                    "effort of imagination, be considered as an engine, the parts of which\n" +
                    "are men.\n" +
                    "The forest has been felled by the savage without the use of the axe,\n" +
                    "and weights have been raised without the aid of the mechanical\n" +
                    "powers. The merit of the inventor, in every branch, probably\n" +
                    "deserves a preference to that of the performer; and he who invented\n" +
                    "a tool, or could work without its assistance, deserved the praise of\n" +
                    "ingenuity in a much higher degree than the mere artist, who, by its\n" +
                    "assistance, produces a superior work.\n" +
                    "But if many parts in the practice of every art, and in the detail of\n" +
                    "every department, require no abilities, or actually tend to contract and\n" +
                    "to limit the views of the mind, there are others which lead to general\n" +
                    "reflections, and to enlargement of thought. Even in manufacture, the\n" +
                    "genius of the master, perhaps, is cultivated, while that of the inferior\n" +
                    "workman lies waste. The statesman may have a wide comprehension\n" +
                    "of human affairs, while the tools he employs are ignorant of the\n" +
                    "system in which they are themselves combined. The general officer\n" +
                    "may be a great proficient in the knowledge of war, while the skill of\n" +
                    "the soldier is confined to a few motions of the hand and the foot.\n" +
                    "The former may have gained what the latter has lost; and being\n" +
                    "occupied in the conduct of disciplined armies, may practise on a\n" +
                    "larger scale all the arts of preservation, of deception, and of\n" +
                    "stratagem, which the savage exerts in leading a small party, or merely\n" +
                    "in defending himself.\n" +
                    "The practitioner of every art and profession may afford matter of\n" +
                    "general speculation to the man of science; and thinking itself, in this\n" +
                    "age of separations, may become a peculiar craft. In the bustle of civil\n" +
                    "pursuits and occupations, men appear in variety of lights, and suggest\n" +
                    "matter of inquiry and fancy, by which conversation is enlivened, and\n" +
                    "greatly enlarged. The productions of ingenuity are brought to the\n" +
                    "market; and men are willing to pay for whatever has a tendency to\n" +
                    "inform or amuse. By this means the idle, as well as the busy,\n" +
                    "contribute to forward the progress of arts, and bestow on polished\n" +
                    "nations that air of superior ingenuity, under which they appear to\n" +
                    "have gained the ends that were pursued by the savage in his forest,\n" +
                    "knowledge, order, and wealth.\n" +
                    "\n" +
                    "SECTION II: OF THE SUBORDINATION CONSEQUENT TO THE\n" +
                    "SEPARATION OF ARTS AND PROFESSIONS.\n" +
                    "THERE is one ground of subordination in the difference of natural\n" +
                    "talents and dispositions; a second in the unequal division of property;\n" +
                    "and a third, not less sensible, in the habits which are acquired by the\n" +
                    "practice of different arts.\n" +
                    "Some employments are liberal, others mechanic. They require\n" +
                    "different talents, and inspire different sentiments; and whether or not\n" +
                    "this be the cause of the preference we actually give, it is certainly\n" +
                    "reasonable to form our opinion of the rank that is due to men of\n" +
                    "certain professions and stations, from the influence of their manner\n" +
                    "of life in cultivating the powers of the mind, or in preserving the\n" +
                    "sentiments of the heart.\n" +
                    "There is an elevation natural to man, by which he would be thought,\n" +
                    "in his rudest state, however urged by necessity, to rise above the\n" +
                    "consideration of mere subsistence, and the regards of interest: He\n" +
                    "would appear to act only from the heart, in its engagements of\n" +
                    "friendship or opposition; he would shew himself only upon occasions\n" +
                    "of danger or difficulty, and leave ordinary cares to the weak or the\n" +
                    "servile.\n" +
                    "The same apprehensions, in every situation, regulate his notions of\n" +
                    "meanness or of dignity. In that of polished society, his desire to avoid\n" +
                    "the character of sordid, makes him conceal his regard for what relates\n" +
                    "merely to his preservation or his livelihood. In his estimation, the\n" +
                    "beggar, who depends upon charity; the labourer, who toils that he\n" +
                    "may eat; the mechanic, whose art requires no exertion of genius, are\n" +
                    "degraded by the object they pursue, and by the means they employ to\n" +
                    "attain it. Professions requiring more knowledge and study;\n" +
                    "proceeding on the exercise of fancy, and the love of perfection;\n" +
                    "leading to applause as well as to profit, place the artist in a superior\n" +
                    "class, and bring him nearer to that station in which men, because they\n" +
                    "are bound to no task, because they are left to follow the disposition\n" +
                    "of the mind, and to take that part in society, to which they are led by\n" +
                    "the sentiments of the heart, or by the calls of the public, are supposed\n" +
                    "to be highest.\n" +
                    "This last was the station, which, in the distinction betwixt freemen\n" +
                    "and slaves, the citizens of every ancient republic strove to gain, and\n" +
                    "to maintain for themselves. Women, or slaves, in the earliest ages,\n" +
                    "had been set apart for the purposes of domestic care, or bodily\n" +
                    "labour; and in the progress of lucrative arts, the latter were bred to\n" +
                    "mechanical professions, and were even intrusted with merchandise\n" +
                    "for the benefit of their masters. Freemen would be understood to\n" +
                    "have no object beside those of politics and war. In this manner, the\n" +
                    "honours of one half of the species were sacrificed to those of the\n" +
                    "other; as stones from the same quarry are buried in the foundation, to\n" +
                    "sustain the blocks which happen to be hewn for the superior parts of\n" +
                    "the pile. In the midst of our encomiums bestowed on the Greeks and\n" +
                    "\n" +
                    "the Romans, we are, by this circumstance, made to remember, that\n" +
                    "no human institution is perfect.\n" +
                    "In many of the Grecian states, the benefits arising to the free from\n" +
                    "this cruel distinction, were not conferred equally on all the citizens.\n" +
                    "Wealth being unequally divided, the rich alone were exempted from\n" +
                    "labour; the poor were reduced to work for their own subsistence;\n" +
                    "interest was a reigning passion in both, and the possession of slaves,\n" +
                    "like that of any other lucrative property, became an object of avarice,\n" +
                    "not an exemption from sordid attentions. The entire effects of the\n" +
                    "institution were obtained, or continued to be enjoyed for any\n" +
                    "considerable time, at Sparta alone. We feel its injustice; we suffer for\n" +
                    "the helot, under the severities and unequal treatment to which he was\n" +
                    "exposed: but when we think only of the superior order of men in this\n" +
                    "state; when we attend to that elevation and magnanimity of spirit, for\n" +
                    "which danger had no terror, interest no means to corrupt; when we\n" +
                    "consider them as friends, or as citizens, we are apt to forget, like\n" +
                    "themselves, that slaves have a title to be treated like men.\n" +
                    "We look for elevation of sentiment, and liberality of mind, among\n" +
                    "those orders of citizens, who, by their condition, and their fortunes,\n" +
                    "are relieved from sordid cares and attentions. This was the\n" +
                    "description of a free man at Sparta; and if the lot of a slave among\n" +
                    "the ancients was really more wretched than that of the indigent\n" +
                    "labourer and the mechanic among the moderns, it may be doubted\n" +
                    "whether the superior orders, who are in possession of consideration\n" +
                    "and honours, do not proportionally fail in the dignity which befits\n" +
                    "their condition. If the pretensions to equal justice and freedom\n" +
                    "should terminate in rendering every class equally servile and\n" +
                    "mercenary, we make a nation of helots, and have no free citizens.\n" +
                    "In every commercial state, notwithstanding any pretension to equal\n" +
                    "rights, the exaltation of a few must depress the many. In this\n" +
                    "arrangement, we think that the extreme meanness of some classes\n" +
                    "must arise chiefly from the defect of knowledge, and of liberal\n" +
                    "education; and we refer to such classes, as to an image of what our\n" +
                    "species must have been in its rude and uncultivated state. But we\n" +
                    "forget how many circumstances, especially in populous cities, tend to\n" +
                    "corrupt the lowest orders of men. Ignorance is the least of their\n" +
                    "failings. An admiration of wealth unpossessed, becoming a principle\n" +
                    "of envy, or of servility; a habit of acting perpetually with a view to\n" +
                    "profit, and under a sense of subjection; the crimes to which they are\n" +
                    "allured, in order to feed their debauch, or to gratify their avarice, are\n" +
                    "examples, not of ignorance, but of corruption and baseness. If the\n" +
                    "savage has not received our instructions, he is likewise unacquainted\n" +
                    "with our vices. He knows no superior, and cannot be servile; he\n" +
                    "knows no distinctions of fortune, and cannot be envious; he acts\n" +
                    "from his talents in the highest station which human society can offer,\n" +
                    "that of the counsellor, and the soldier of his country. Toward\n" +
                    "forming his sentiments, he knows all that the heart requires to be\n" +
                    "known; he can distinguish the friend whom he loves, and the public\n" +
                    "interest which awakens his zeal.\n" +
                    "\n" +
                    "The principal objections to democratical or popular government, are\n" +
                    "taken from the inequalities which arise among men in the result of\n" +
                    "commercial arts. And it must be confessed, that popular assemblies,\n" +
                    "when composed of men whose dispositions are sordid, and whose\n" +
                    "ordinary applications are illiberal, however they may be intrusted with\n" +
                    "the choice of their masters and leaders, are certainly, in their own\n" +
                    "persons, unfit to command. How can he who has confined his views\n" +
                    "to his own subsistence or preservation, be intrusted with the conduct\n" +
                    "of nations? Such men, when admitted to deliberate on matters of\n" +
                    "state, bring to its councils confusion and tumult, or servility and\n" +
                    "corruption; and seldom suffer it to repose from ruinous factions, or\n" +
                    "the effect of resolutions ill formed or ill conducted.\n" +
                    "The Athenians retained their popular government under all these\n" +
                    "defects. The mechanic was obliged, under a penalty, to appear in the\n" +
                    "public market-place, and to hear debates on the subjects of war, and\n" +
                    "of peace. He was tempted by pecuniary rewards, to attend on the trial\n" +
                    "of civil and criminal causes. But, notwithstanding an exercise tending\n" +
                    "so much to cultivate their talents, the indigent came always with\n" +
                    "minds intent upon profit, or with the habits of an illiberal calling.\n" +
                    "Sunk under the sense of their personal disparity and weakness, they\n" +
                    "were ready to resign themselves entirely to the influence of some\n" +
                    "popular leader, who flattered their passions, and wrought on their\n" +
                    "fears; or, actuated by envy, they were ready to banish from the state\n" +
                    "whomsoever was respectable and eminent in the superior order of\n" +
                    "citizens; and whether from their neglect of the public at one time, or\n" +
                    "their mal-administration at another, the sovereignty was every\n" +
                    "moment ready to drop from their hands.\n" +
                    "The people, in this case, are, in fact, frequently governed by one, or a\n" +
                    "few, who know how to conduct them. Pericles possessed a species of\n" +
                    "princely authority at Athens; Crassus, Pompey, and Cæsar, either\n" +
                    "jointly or successively, possessed for a considerable period the\n" +
                    "sovereign direction at Rome.\n" +
                    "Whether in great or in small states, democracy is preserved with\n" +
                    "difficulty, under the disparities of condition, and the unequal\n" +
                    "cultivation of the mind, which attend the variety of pursuits, and\n" +
                    "applications, that separate mankind in the advanced state of\n" +
                    "commercial arts. In this, however, we do but plead against the form\n" +
                    "of democracy, after the principle is removed; and see the absurdity of\n" +
                    "pretensions to equal influence and consideration, after the characters\n" +
                    "of men have ceased to be similar.\n" +
                    "SECTION III: OF THE MANNERS OF POLISHED AND\n" +
                    "COMMERCIAL NATIONS.\n" +
                    "MANKIND, when in their rude state, have a great uniformity of\n" +
                    "manners; but when civilized, they are engaged in a variety of pursuits;\n" +
                    "they tread on a larger field, and separate to a greater distance. If they\n" +
                    "be guided, however, by similar dispositions, and by like suggestions\n" +
                    "\n" +
                    "of nature, they will probably in the end, as well as in the beginning of\n" +
                    "their progress, continue to agree in many particulars; and while\n" +
                    "communities admit, in their members, that diversity of ranks and\n" +
                    "professions which we have already described as the consequence or\n" +
                    "the foundation of commerce, they will resemble each other in many\n" +
                    "effects of this distribution, and of other circumstances in which they\n" +
                    "nearly concur.\n" +
                    "Under every form of government, statesmen endeavour to remove\n" +
                    "the dangers by which they are threatened from abroad, and the\n" +
                    "disturbances which molest them at home. By this conduct, if\n" +
                    "successful, they in a few ages gain an ascendant for their country;\n" +
                    "establish a frontier at a distance from its capital; they find, in the\n" +
                    "mutual desires of tranquillity, which come to possess mankind, and in\n" +
                    "those public establishments which tend to keep the peace of society,\n" +
                    "a respite from foreign wars, and a relief from domestic disorders.\n" +
                    "They learn to decide every contest without tumult, and to secure, by\n" +
                    "the authority of law, every citizen in the possession of his personal\n" +
                    "rights.\n" +
                    "In this condition, to which thriving nations aspire, and which they in\n" +
                    "some measure attain, mankind having laid the basis of safety, proceed\n" +
                    "to erect a superstructure suitable to their views. The consequence is\n" +
                    "various in different states; even in different orders of men of the\n" +
                    "same community; and the effect to every individual corresponds with\n" +
                    "his station. It enables the statesman and the soldier to settle the\n" +
                    "forms of their different procedure; it enables the practitioner in every\n" +
                    "profession to pursue his separate advantage; it affords the man of\n" +
                    "pleasure a time for refinement, and the speculative, leisure for literary\n" +
                    "conversation or study.\n" +
                    "In this scene, matters that have little reference to the active pursuits\n" +
                    "of mankind, are made subjects of enquiry, and the exercise of\n" +
                    "sentiment and reason itself becomes a profession. The songs of the\n" +
                    "bard, the harangues of the statesman and the warrior, the tradition\n" +
                    "and the story of ancient times, are considered as the models, or the\n" +
                    "earliest production, of so many arts, which it becomes the object of\n" +
                    "different professions to copy or to improve. The works of fancy, like\n" +
                    "the subjects of natural history, are distinguished into classes and\n" +
                    "species; the rules of every particular kind are distinctly collected; and\n" +
                    "the library is stored, like the warehouse, with the finished\n" +
                    "manufacture of different artists, who, with the aids of the\n" +
                    "grammarian and the critic, aspire, each in his particular way, to\n" +
                    "instruct the head, or to move the heart.\n" +
                    "Every nation is a motley assemblage of different characters, and\n" +
                    "contains, under any political form, some examples of that variety,\n" +
                    "which the humours, tempers, and apprehensions of men, so\n" +
                    "differently employed, are likely to furnish. Every profession has its\n" +
                    "point of honour, and its system of manners; the merchant his\n" +
                    "punctuality and fair dealing; the statesman his capacity and address;\n" +
                    "the man of society his good breeding and wit. Every station has a\n" +
                    "\n" +
                    "carriage, a dress, a ceremonial, by which it is distinguished, and by\n" +
                    "which it suppresses the national character under that of the rank, or\n" +
                    "of the individual.\n" +
                    "This description may be applied equally to Athens and Rome, to\n" +
                    "London and Paris. The rude, or the simple observer, would remark\n" +
                    "the variety he saw in the dwellings and in the occupations of different\n" +
                    "men, not in the aspect of different nations. He would find, in the\n" +
                    "streets of the same city, as great a diversity, as in the territory of a\n" +
                    "separate people. He could not pierce through the cloud that was\n" +
                    "gathered before him, nor see how the tradesman, mechanic, or\n" +
                    "scholar, of one country, should differ from those of another. But the\n" +
                    "native of every province can distinguish the foreigner; and when he\n" +
                    "himself travels, is struck with the aspect of a strange country, the\n" +
                    "moment he passes the bounds of his own. The air of the person, the\n" +
                    "tone of the voice, the idiom of language, and the strain of\n" +
                    "conversation, whether pathetic or languid, gay or severe, are no\n" +
                    "longer the same.\n" +
                    "Many such differences may arise among polished nations, from the\n" +
                    "effects of climate, or from sources of fashion, that are still more\n" +
                    "hidden or unobserved; but the principal distinctions on which we can\n" +
                    "rest, are derived from the part a people are obliged to act in their\n" +
                    "national capacity; from the objects placed in their view by the state;\n" +
                    "or from the constitution of government, which, prescribing the terms\n" +
                    "of society to its subjects, had a great influence in forming their\n" +
                    "apprehensions and habits.\n" +
                    "…\n" +
                    "The member of a republic, and the subject of a monarchy, must\n" +
                    "differ; because they have different parts assigned to them by the\n" +
                    "forms of their country: The one destined to live with his equals, or to\n" +
                    "contend by his personal talents and character, for pre-eminence; the\n" +
                    "other, born to a determinate station, where any pretence to equality\n" +
                    "creates a confusion, and where nought but precedence is studied.\n" +
                    "Each, when the institutions of his country are mature, may find in the\n" +
                    "laws a protection to his personal rights; but those rights themselves\n" +
                    "are differently understood, and with a different set of opinions, give\n" +
                    "rise to a different temper of mind. The republican must act in the\n" +
                    "state, to sustain his pretensions; he must join a party, in order to be\n" +
                    "safe; he must lead one, in order to be great. The subject of monarchy\n" +
                    "refers to his birth for the honour he claims; he waits on a court, to\n" +
                    "shew his importance; and holds out the ensigns of dependence and\n" +
                    "favour, to gain him esteem with the public.\n" +
                    "If national institutions, calculated for the preservation of liberty,\n" +
                    "instead of calling upon the citizen to act for himself, and to maintain\n" +
                    "his rights, should give a security, requiring, on his part, no personal\n" +
                    "attention or effort; this seeming perfection of government might\n" +
                    "weaken the bands of society, and, upon maxims of independence,\n" +
                    "separate and estrange the different ranks it was meant to reconcile.\n" +
                    "\n" +
                    "Neither the parties formed in republics, nor the courtly assemblies\n" +
                    "which meet in monarchical governments, could take place, where the\n" +
                    "sense of a mutual dependence should cease to summon their\n" +
                    "members together. The resorts for commerce might be frequented,\n" +
                    "and mere amusement might be pursued in the crowd, while the\n" +
                    "private dwelling became a retreat for reserve, averse to the trouble\n" +
                    "arising from regards and attentions, which it might be part of the\n" +
                    "political creed to believe of no consequence, and a point of honour\n" +
                    "to hold in contempt.\n" +
                    "This humour is not likely to grow either in republics or monarchies:\n" +
                    "It belongs more properly to a mixture of both; where the\n" +
                    "administration of justice may be better secured; where the subject is\n" +
                    "tempted to look for equality, but where he finds only independence\n" +
                    "in its place; and where he learns, from a spirit of equality, to hate the\n" +
                    "very distinctions to which, on account of their real importance, he\n" +
                    "pays a remarkable deference.\n" +
                    "In either of the separate forms of republic or monarchy, or in acting\n" +
                    "on the principles of either, men are obliged to court their fellowcitizens, and to employ parts and address to improve their\n" +
                    "fortunes, or even to be safe. They find in both a school for\n" +
                    "discernment and penetration; but in the one, are taught to overlook\n" +
                    "the merits of a private character, for the sake of abilities that have\n" +
                    "weight with the public; and in the other to overlook great and\n" +
                    "respectable talents, for the sake of qualities engaging or pleasant in\n" +
                    "the scene of entertainment and private society. They are obliged, in\n" +
                    "both, to adapt themselves with care to the fashion and manners of\n" +
                    "their country. They find no place for caprice or singular humours.\n" +
                    "The republican must be popular, and the courtier polite. The first\n" +
                    "must think himself well placed in every company; the other must\n" +
                    "chuse his resorts, and desire to be distinguished only where the\n" +
                    "society itself is esteemed. With his inferiors, he takes an air of\n" +
                    "protection; and suffers, in his turn, the same air to be taken with\n" +
                    "himself. It did not, perhaps, require in a Spartan, who feared nothing\n" +
                    "but a failure in his duty, who loved nothing but his friend and the\n" +
                    "state, so constant a guard on himself to support his character, as it\n" +
                    "frequently does in the subject of a monarchy, to adjust his expence\n" +
                    "and his fortune to the desires of his vanity, and to appear in a rank as\n" +
                    "high as his birth, or ambition, can possibly reach.\n" +
                    "There is no particular, in the mean time, in which we are more\n" +
                    "frequently unjust, than in applying to the individual the supposed\n" +
                    "character of his country; or more frequently misled, than in taking\n" +
                    "our notion of a people from the example of one, or a few of their\n" +
                    "members. It belonged to the constitution of Athens, to have\n" +
                    "produced a Cleon, and a Pericles; but all the Athenians were not,\n" +
                    "therefore, like Cleon, or Pericles. Themistocles and Aristides lived in\n" +
                    "the same age; the one advised what was profitable, the other told his\n" +
                    "country what was just.");
            game.time(10);
            game.print("I hope you are happy with yourself.", 3);
            game.print("You pick up the stupid ticket and take your leave", 3);
            Item ticket = new Item("Ticket", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.");
            game.getPlayer().getInventory().addItem(ticket);
            game.print("Added 'Ticket' to Inventory", 3);
            System.out.println();
            count++;
            actionList.clear();
            return returnArray;
        }
        else if (x == 7 && y == 5) { //B55 Riley
            //fix
            game.print("You approach this scientist, and even though he is not facing you, in your head you think that he looks like the type of guy to scream when the lights go out.", 7);
            game.print("He looks like the type of guy to say 'easy peasy lemon squeezy' after doing something.", 4);
            game.print("He looks like the type of guy to say 'see you later alligator' when saying goodbye to someone.", 5);
            System.out.println();
            game.print("He looks like the type of guy who would remind the teacher that there was homework due.", 4);
            game.print("He looks like the type of guy who would say 'well well well', when facing his nemesis.", 4);
            game.print("He looks like the type of guy to count down from 10 to get his friend's attention and include one and a half in it too.", 6);
            System.out.println();
            game.print("He looks like the type of guy who would say 'night night, don't let the bed bugs bite' to the homies", 5);
            game.print("But in the end, you are a nice guy so you decide to give him a chance.", 4);
            game.print("The Scientist turns towards you, face still nose deep in a folder of papers.", 4);
            System.out.println();

            game.print("Scientist (while still reading/working on something) * 'Hello there, wow you're the first person who's came to my presentation!'", 6);
            game.print("Dalton (under his breath) * 'I couldn't possibly see why.'", 3);
            game.print("The Scientist then puts down the folder allowing you to see his face.", 4);
            game.print("Scientist * 'Well my name is Riley Nelson, what's yours?'", 3);
            System.out.println();

            displayid = "R";
            inGameid = "Riley Nelson";
            game.print("Dalton (annoyed that he has to be doing this) * 'I'm Dalton Young, now can we get going with the presentation.'", 5);
            game.print("Riley * 'Why of course, see that thing right there.' (Points at Generator)", 4);
            game.print("Dalton * 'Yep.'", 3);
            System.out.println();

            game.print("Riley * 'That is my revolutionary new generator that runs off of Golden Electricity.'", 4);
            game.print("Riley * 'It is 110% clean, renewable energy with no downsides!' ", 3);
            game.print("Riley * 'Golden Electricity was discovered deep in an Australian gold mine and it has been shown to exhibit quantum properties' ", 6);
            System.out.println();

            game.print("Riley * 'My generator here refines the raw Golden Electricity allowing it to run like a regular generator while also letting it reap the quantum benefits.'", 8);
            game.print("Riley * 'It works by having each Golden Electrical particle quantumly entangle itself with another Golden Electrical particle from another universe, but that particle entangles itself with another which causes a long chain of entanglement creating effectively infinite energy.'", 15);
            game.print("Riley looks up to see that Dalton had fallen asleep.", 3);
            System.out.println();

            game.print("Dalton then violently wakes up as his soothing lullaby had stopped.", 4);
            game.print("Dalton (clearly disinterested) * 'Huh what, oh you're done, alright cool gimme the ticket.'", 5);
            game.print("Riley was upset that Dalton did not listen, but nonetheless he gave him the ticket and watched as he walked away.", 6);
            System.out.println();

            Item ticket = new Item("Ticket", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.");
            game.getPlayer().getInventory().addItem(ticket);
            game.print("Added 'Ticket' to Inventory", 3);
            count++;
            actionList.clear();
            return returnArray;

            //actionList.clear();
        }
        else if (x == 7 && y ==8) { //B55 VR
            if (count < 9) {
                game.print("You approach this scientist, but she turns to you and say that her presentation is not ready yet and tells you to come back later.", 5);
                return returnArray;
            }
            game.print("You approach this scientist, and she turns to you and smiles saying that her presentation is ready.", 4);
            game.print("She was not at all the type of person you would expect to be a scientist in this lousy lab.", 4);
            game.print("You feel a lot better and are ready to listen to whatever presentation she wants to give.", 4);
            System.out.println();

            game.print("Scientist * 'Hey there, I'm Amanda, wanna try out my new Virtual Reality Headset I invented?'", 4);
            game.print("Dalton (ready for a break from the boring presentations) * 'Bet!'", 3);
            game.print("You put on the headset and immediately you are thrown into an action scene where you are jumping out of a plane.", 5);
            System.out.println();

            game.print("While you are being amazed at how realistic it all feels, Riley Nelson was standing at his generator still bummed out that nobody came to his presentation.", 6);
            game.print("He then sees you enjoying his competitor's invention and gets jealous", 4);
            game.print("So then he gets an idea to show you the power of his generator and why you should support him: ", 5);
            System.out.println();

            game.print("He would just plug in the VR Headset into his Golden Electricity Generator, how simple!",  5);
            game.print("So, while Amanda was not looking Riley rushed over and unplugged the VR Headset.", 4);
            game.print("Dalton * 'Hey who turned out the lights?'", 3);
            System.out.println();

            game.print("And Riley plugged the VR Headset into the generator.", 3);
            game.print("However, the generator was built to power the city, and plugging it into the VR Headset caused an excess in refined Golden Electricity to burst out onto you.", 6);
            game.print("You scream in anguish as you fall to the ground spasming out as your heart stops.", 4);
            System.out.println();

            game.print("Everything goes to black as the last things you hear are the screams of Amanda.", 5);
            game.print("This was the tragic death of Dalton Young.", 5);
            System.out.println();
            game.print("END OF CHAPTER ONE", 4);
            game.endText();
            actionList.clear();
            returnArray[1] = true;
            return returnArray;
        }
        System.out.println("something wrong is happening, you called Scientist but it did not register as a valid scientist");
        System.out.println(x);
        System.out.println(y);
        return returnArray;

    }
}
