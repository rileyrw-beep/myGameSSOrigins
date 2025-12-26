import java.util.*;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Inventory {
    private Set<Item> inventoryList;
    private static final Map<String, Item> itemDictionary = Map.ofEntries(
            Map.entry("soda", new Item("Crazy Soda", "The legendary origin story that started it all. Made by Dr. Riley Nelson. Consumed by Dalton Young on his skateboard.", "soda")),
            Map.entry("rib", new Item("Toy Ribcage", "An oddly shaped bus with more wheels than seats. \nIt looks completely non-functional but nevertheless you decide to keep it. \nGiven to you by a mysterious man", "rib")),
            Map.entry("t1", new Item("Ticket 1", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.", "t1")),
            Map.entry("t2", new Item("Ticket 2", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.", "t2")),
            Map.entry("t3", new Item("Ticket 3", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.", "t3")),
            Map.entry("t4", new Item("Ticket 4", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.", "t4")),
            Map.entry("t5", new Item("Ticket 5", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.", "t5")),
            Map.entry("t6", new Item("Ticket 6", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.", "t6")),
            Map.entry("t7", new Item("Ticket 7", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.", "t7")),
            Map.entry("t8", new Item("Ticket 8", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.", "t8")),
            Map.entry("t9", new Item("Ticket 9", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.", "t9")),
            Map.entry("t10", new Item("Ticket 10", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.", "t10")),
            Map.entry("t11", new Item("Ticket 11", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.", "t11")),
            Map.entry("t12", new Item("Ticket 12", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.", "t12")),
            Map.entry("t13", new Item("Ticket 13", "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.", "t13")),
            Map.entry("tgd", new Item("The Great Diarrhea Figurine", "This is a figurine depicting the origin of the Universe. A large man with explosive diarrhea ate too much Taco Bell and the result was the creation of the Universe.", "tgd"))
    );
    private static String filePath;




    public Inventory() {
        filePath = "../file/InventorySave.txt";
        Comparator<Item> lexicographicalOrderComparator = new Comparator<Item>() {
            public int compare(Item i1, Item i2) {
                if (!Objects.equals(i1.getItemName(), i2.getItemName())) {
                    return i1.getItemName().compareTo(i2.getItemName());
                }
                return i1.getKey().compareTo(i2.getKey());
            }
        };
        inventoryList = new TreeSet<>(lexicographicalOrderComparator);

        retrieveInventory();
    }

    public void addItem(Item item) {
        inventoryList.add(item);
    }

    static public Item getItemFromDictionary(String key) {
        if (itemDictionary.containsKey(key)) {
            return itemDictionary.get(key);
        }
        return null;
    }

    public void removeItem(Item item) {
        inventoryList.remove(item);
    }

    public Set<Item> getItems() {
        return inventoryList;
    }

    public void printInventory() {
        int counter = 0;
        for (Item item : inventoryList) {
            if (counter != inventoryList.size() - 1) {
                System.out.print(item.getItemName() + ", ");
                if (counter % 3 == 0 && counter!=0) {
                    System.out.println();
                }
            }
            else {
                System.out.println(item.getItemName() + ".");
            }
            counter++;
        }
    }

    public boolean containsItem(Item item, String itemName) {
        if (item!=null) return inventoryList.contains(item);
        for (Item currItem : inventoryList) {if (currItem.getItemName().equals(itemName)) return true;}
        return false;
    }


    public void saveInventory() {
        StringBuilder contentBuilder = new StringBuilder();
        int count = 0;
        for (Item item : inventoryList) {
            String toAppend = item.getKey();
            if (count != inventoryList.size() - 1) {
                toAppend += "\r\n";
            }
            contentBuilder.append(toAppend);
            count++;
        }
        String content =  contentBuilder.toString();

        try (BufferedWriter file = new BufferedWriter(new FileWriter(filePath))) {
            file.write(content);
        }
        catch (IOException e) {
            System.err.println("Error writing file");
        }
    }

    public void retrieveInventory() {
        try (BufferedReader file = new BufferedReader(new FileReader(filePath))) {
            String currLine = "";
            while ((currLine = file.readLine()) != null) {
                if (!currLine.isEmpty()) inventoryList.add(getItemFromDictionary(currLine));
            }
        }
        catch (IOException e) {
            System.err.println("Error reading file");
        }
    }

    public Item getItem(String itemName) {
        for (Item item : inventoryList) {
            if (item.getItemName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public Item getItem() {
        if (inventoryList.size()==1) {
            for (Item item : inventoryList) {
                return item;
            }
        }
        return null;
    }

    







}