import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DecisionTree {

    private static class Node {

        ArrayList<Node> children;
        Decisionable decision;

        Node(Decisionable decision) {
            this.decision = decision;
            children = new ArrayList<Node>();
        }

    }

    private Node root;
    private int size;


    public DecisionTree(ArrayList<Decisionable> decisionList, int n) {
        size = 0;
        root = new Node(decisionList.getFirst());

        int i = 1;
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        while (i < decisionList.size() && !q.isEmpty()) {
            if (decisionList.get(i) == null) {
                q.addAll(q.poll().children);
                i++;
                continue;
            }

            if (q.peek().children.size() < n) {
                q.peek().children.add(new Node(decisionList.get(i)));
                size++;
                i++;
            }
            else {
                q.addAll(q.poll().children);
            }

        }

        decisionList.clear();

    }



    public int getSize() {
        return size;
    }

    public void traverseTree() {

        while (true) {

            Node current = root;

            while (current != null) {

                int choice = current.decision.makeDecision();
                if (choice == -1) break;
                current = current.children.get(choice-1);

            }
            ArrayList<String> options = new ArrayList<>();
            options.add("Reset Decision Tree");
            options.add("End Decision Tree");
            int choice = Game.basicGameLoop(options);
            if (choice == 2) break;
            else Game.print("",0);

        }


    }




}


//decision tree example:

/*

        Decisionable OGr = () -> {
            System.out.println("We good?");
            System.out.println();
            return Game.basicGameLoop(new ArrayList<String>(Arrays.asList("No", "Yes")));
        };

        Decisionable r = () -> {
            System.out.println("We good?");
            System.out.println();
            return Game.basicGameLoop(new ArrayList<String>(Arrays.asList("No", "Maybe", "Yes")));
        };

        Decisionable rL = () -> {
            System.out.println("Why not?");
            System.out.println();

            return Game.basicGameLoop(new ArrayList<String>(Arrays.asList("I'm just not having a good day", "Actually I am feeling good", "Nobody knows")));
        };

        Decisionable rR = () -> {
            System.out.println("Lets Go!");
            return -1;
        };

        Decisionable rLL = () -> {
            System.out.println("Well lets make it better with some Bjorn Skifs!");
            System.out.println();
            return -1;
        };

        Decisionable rLM = () -> {
            System.out.println("Thats good, don't lie next time");
            System.out.println();
            return -1;
        };

        Decisionable rLR = () -> {
            System.out.println("You know who does know? Jerry!");
            System.out.println();
            Game.time(1);
            TurnBasedBattleManager.doBattle(new String[]{"riley"}, new String[]{"jerry"}, true, 0);
            return -1;
        };

        Decisionable rRL= () -> {
            System.out.println("Yay!");
            System.out.println();
            return -1;
        };

        Decisionable rM = () -> {
            System.out.println("What do you mean Maybe?");
            System.out.println();
            return Game.basicGameLoop(new ArrayList<String>(Arrays.asList("I'm just not sure", "Its a maybe answer yk", "Nobody knows")));
        };

        Decisionable rML = () -> {
            System.out.println("Well you better be sure");
            System.out.println();
            return -1;
        };

        Decisionable rMM = () -> {
            System.out.println("You're a Maybe?");
            System.out.println();
            return -1;
        };

        Decisionable rMR = rLR;




        DecisionTree dt = new DecisionTree(new ArrayList<>(Arrays.asList(OGr, rL, rR, null, rLL, rLM, rLR, null, rRL)), 3);
        DecisionTree dt2 = new DecisionTree(new ArrayList<>(Arrays.asList(r, rL, rM, rR, null, rLL, rLM, rLR, null, rML, rMM, rMR, null, rRL)), 3);
        DecisionTree dt3 = new DecisionTree(new ArrayList<>(Arrays.asList(r, rL, rM, rR, rLL, rLM, rLR, rML, rMM, rMR, rRL)), 3);

        dt.traverseTree();
        dt2.traverseTree();
        dt3.traverseTree();

        //dt2 and dt3 are identical trees, one just places null in between each set of children
        //null is only needed if you reach the end of an incomplete set of children, aka a node has less than n children where n is the order.



*/