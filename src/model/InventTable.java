package model;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class InventTable {
    public enum itemTypeValues {
        BOARDGAME(0),
        BOOK(1);
        private int value;
        private static Map map = new HashMap<>();

        private itemTypeValues(int value) {
            this.value = value;
        }

        static {
            for (itemTypeValues pageType : itemTypeValues.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static itemTypeValues valueOf(int pageType) {
            return (itemTypeValues) map.get(pageType);
        }

        public int getValue() {
            return value;
        }

    }
    private Integer ItemId;
    private String Description;
    private String Name;
    private String Author;
    private Integer NumOfPlayersMin;
    private Integer NumOfPlayersMax;
    private Double Price;
    private Integer ItemType;
    private boolean readyForSale;
    private Integer QTY;



    public Integer getItemType() {
        return ItemType;
    }

    public void setItemType(Integer itemType) {
        ItemType = itemType;
    }
    public Integer getItemId() {
        return ItemId;
    }

    public void setItemId(Integer itemId) {
        ItemId = itemId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Integer getNumOfPlayersMin() {
        return NumOfPlayersMin;
    }

    public void setNumOfPlayersMin(Integer numOfPlayersMin) {
        NumOfPlayersMin = numOfPlayersMin;
    }

    public Integer getNumOfPlayersMax() {
        return NumOfPlayersMax;
    }

    public void setNumOfPlayersMax(Integer numOfPlayersMax) {
        NumOfPlayersMax = numOfPlayersMax;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public boolean isReadyForSale() {
        return readyForSale;
    }

    public void setReadyForSale(boolean readyForSale) {
        this.readyForSale = readyForSale;
    }

    public int getQuantity() {
        return QTY;
    }

    public void setQuantity(int quantity) {
        QTY = quantity;
    }

    public InventTable(Integer itemId, String description, String name, String author, Integer numOfPlayersMin, Integer numOfPlayersMax, Double price, boolean readyForSale, Integer quantity, Integer itemType) {
        ItemId = itemId;
        Description = description;
        Name = name;
        Author = author;
        NumOfPlayersMin = numOfPlayersMin;
        NumOfPlayersMax = numOfPlayersMax;
        Price = price;
        this.readyForSale = readyForSale;
        QTY = quantity;
        ItemType = itemType;
    }

    public void print()
    {
        System.out.printf("ItemId: %s,ItemType %s , Descriptions: %s, Name: %s," +
                " Author: %s, Qty: %d, Price: %f, Number of Players: %d to %d, ReadyForSale %b \n",
                ItemId, itemTypeValues.valueOf(ItemType), Description, Name, Author, QTY, Price, NumOfPlayersMin, NumOfPlayersMax, readyForSale);
    }

    public boolean itemContainsWord(String smallPart, String source) {
        return Pattern.compile(Pattern.quote(smallPart), Pattern.CASE_INSENSITIVE).matcher(source).find();
    }

    public boolean itemBookTitleContainsWord(String titlePart) {
        Boolean matcherText = this.itemContainsWord(titlePart, this.Name);
        return matcherText && this.getItemType() == itemTypeValues.BOOK.ordinal();
    }

    public boolean  itemBookAuthorContainsWord(String authorName) {
        Boolean matcherText = this.itemContainsWord(authorName, this.Author);
        return matcherText && this.getItemType() == itemTypeValues.BOOK.ordinal();
    }

    public boolean  itemGameNameContainsWord(String gameName) {
        Boolean matcherText = this.itemContainsWord(gameName, this.Name);
        return matcherText && this.getItemType() == itemTypeValues.BOARDGAME.ordinal();
    }

    public boolean  itemGameSuitableForPlayers(Integer gameNOP) {
        return this.NumOfPlayersMin<= gameNOP && this.NumOfPlayersMax>= gameNOP && this.getItemType() == itemTypeValues.BOARDGAME.ordinal();
    }


}
