public class Item {
    private String itemName;
    private String itemDescription;
    private String key;
    private boolean isCollectible;


    public Item (String name, String description, String key) {
        this.itemName = name;
        this.itemDescription = description;
        this.key = key;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getKey() {return key;}
}
