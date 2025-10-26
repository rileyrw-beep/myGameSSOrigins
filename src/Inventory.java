import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> inventoryList;
    private ArrayList<Item> clearList;



    public Inventory() {
        inventoryList = new ArrayList<Item>();
        clearList = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        inventoryList.add(item);
        clearList.add(item);
    }

    public void removeItem(Item item) {
        inventoryList.remove(item);
    }

    public ArrayList<Item> getItems() {
        return inventoryList;
    }

    public void printInventory() {
        int counter = 0;
        for (Item item : inventoryList) {
            if (inventoryList.indexOf(item) != inventoryList.size() - 1) {
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
        for (int i = 0; i < inventoryList.size(); i++) {if (inventoryList.get(i).getItemName().equals(itemName)) return true;}
        return false;
    }
    
    public void revertInventory() {
        for (int i = 0; i < clearList.size(); i++) {
            for (int j = 0; j < inventoryList.size(); j++) {
                if (containsItem(clearList.get(i), clearList.get(i).getItemName())) {
                    removeItem(clearList.get(i));
                }
            }
        }
    }

    public void saveInventory() {
        clearList.clear();
    }
    






}