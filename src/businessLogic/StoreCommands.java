package businessLogic;

import SQLiteManagement.InventoryStorageManager;

import java.sql.SQLException;

public class StoreCommands {
    public void insertCustomer() {}

    public void insertItem()// new items will be created with quantity no restocking needed
     {}
    public void restockExistingItem(){}
    public void requestNotExistingItem(){}
    public void purchItem(){}
    public void listCustomerRequest(){}
    public void deleteCustomerRequest(){}
    public void purchNotExistingItem()// creates request and purch
    {}

    public void listCustomers(){}
    // implementation of 1.
    public void listItems() throws SQLException {
        InventoryStorageManager ism = new InventoryStorageManager();
        ism.printAllItems();
    }

    public void searchBooksByTitle(String titlePart) throws SQLException {
        InventoryStorageManager ism = new InventoryStorageManager();
        ism.searchItemBookTitles(titlePart);
    }

    public void searchBooksByAuthor(String author) throws SQLException {
        InventoryStorageManager ism = new InventoryStorageManager();
        ism.searchBooksByAuthor(author);
    }

    public void searchGamesByName(String gameName) throws SQLException {
        InventoryStorageManager ism = new InventoryStorageManager();
        ism.searchGamesByName(gameName);
    }

    public void searchGamesByNumberOfPlayers(Integer gameNOP) throws SQLException {
        InventoryStorageManager ism = new InventoryStorageManager();
        ism.searchGamesByNumberOfPlayers(gameNOP);
    }


}
