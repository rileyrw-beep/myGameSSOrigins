import java.util.*;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Inventory {
    private Set<Item> inventoryList;
    private static Map<String, Item> itemDictionary;
    private static String filePath;




    public Inventory() {
        filePath = "../file/InventorySave.txt";
        inventoryList = new HashSet<>();
        itemDictionary = new HashMap<>();
        itemDictionary.put("soda", new Item("Crazy Soda", "The legendary origin story that started it all. Made by Dr. Riley Nelson. Consumed by Dalton Young on his skateboard.", "soda"));
        itemDictionary.put("rib", new Item("Toy Ribcage", "An oddly shaped bus with more wheels than seats. \nIt looks completely non-functional but nevertheless you decide to keep it. \nGiven to you by a mysterious man", "rib"));
        for (int i = 1; i <= 13; i++) {
            String key = "t" + i;
            String name = "Ticket " + i;
            itemDictionary.put(key, new Item(name, "A small ticket you got from the Nelson Lab's Tech Fair. If you get one from each presenting scientist, then you get free Raising Cane's.", key));
        }
        itemDictionary.put("tgd", new Item("The Great Diarrhea Figurine", "This is a figurine depicting the origin of the Universe. A large man with explosive diarrhea ate too much Taco Bell and the result was the creation of the Universe.", "tgd"));

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

    







}