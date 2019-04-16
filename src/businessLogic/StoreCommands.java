package businessLogic;

import SQLiteManagement.CustomerStogageManager;
import SQLiteManagement.InventoryStorageManager;
import SQLiteManagement.SalesOrderStorageManager;
import model.Customers;
import model.InventTable;
import model.SalesOrder;

import java.sql.SQLException;

public class StoreCommands {
    public void insertCustomer(String custName) throws SQLException {
        CustomerStogageManager csm = new CustomerStogageManager();
        csm.insertCustomerTable(new Customers(custName, this.nextCustId()));
    }

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

    public void purchExistingItem(Integer salesId, Integer itemId, Integer custId, Integer qty) throws SQLException {
        SalesOrderStorageManager ssm = new SalesOrderStorageManager();
        ssm.createSalesOrderTable();
        SalesOrder so = new SalesOrder(itemId,salesId,custId, SalesOrder.SalesOrderTupe.Purched.ordinal(),qty);
        ssm.insertSalesOrderTable(so);
    }

    public void requestItem(Integer salesId, Integer itemId, Integer custId, Integer qty) throws SQLException {
        SalesOrderStorageManager ssm = new SalesOrderStorageManager();
        ssm.createSalesOrderTable();
        SalesOrder so = new SalesOrder(itemId,salesId,custId, SalesOrder.SalesOrderTupe.Requested.ordinal(),qty);
        ssm.insertSalesOrderTable(so);
    }
    /// to do check if exists a record instead of creating a table  :)
    public void deleteSalesOrder(Integer salesId) throws SQLException {
        SalesOrderStorageManager ssm = new SalesOrderStorageManager();
        ssm.createSalesOrderTable();
        ssm.deleteIdFromSalesOrderes(salesId);
    }

    public void getAllSalesOrderRequests() throws SQLException {
        SalesOrderStorageManager ssm = new SalesOrderStorageManager();
        ssm.createSalesOrderTable();
        ssm.printAllSalesOrderRequests();
    }

    public void insertItem(InventTable it) throws SQLException {
        InventoryStorageManager ssm = new InventoryStorageManager();
        ssm.insertInventTable(it);
    }

    public int nextSalesId() throws SQLException {
        SalesOrderStorageManager ssm = new SalesOrderStorageManager();
        return ssm.getNextSalesId();

    }
    public int nextItemId() throws SQLException {
        InventoryStorageManager ssm = new InventoryStorageManager();
        return ssm.getNextItemId();

    }
    public int nextCustId() throws SQLException {
        CustomerStogageManager ssm = new CustomerStogageManager();
        return ssm.getNextCustId();

    }

    public void updateQtyOfItem(Integer itemId, Integer qty) throws SQLException {
        InventoryStorageManager ssm = new InventoryStorageManager();
        ssm.updateInventTableQTY(itemId, qty);
    }

    public void cleanRequestedItemSalesOrders(Integer itemId, Integer qty) throws SQLException {
        SalesOrderStorageManager ssm = new SalesOrderStorageManager();
        ssm.cleanRequestsAfterRestock(itemId, qty);
    }

}
