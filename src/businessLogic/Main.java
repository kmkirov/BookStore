package businessLogic;

import LABELS.SQLStatements;
import com.sun.corba.se.impl.encoding.BufferManagerRead;
import model.Customers;
import model.InventTable;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Main {


    public static void main( String args[] ) throws IOException, SQLException {
        StoreCommands st = new StoreCommands();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //do{
            System.out.println("Please select option by typing a number:");
            System.out.println("1. Show items list. "); // ready not tested
            System.out.println("2. Search for book by title (partialy).");// ready not tested
            System.out.println("3. Search for book by author (partialy).");// ready not tested
            System.out.println("4. Search for board game by name (partialy).");// ready not tested
            System.out.println("5. Search for board game by number of player.");// ready not tested

            System.out.println("6. Purch Item by catalog number.(catalog changes)");
            System.out.println("7. Order not existing item."); // add new item to items, create purch order and request
            System.out.println("8. Restock existing item.");
            System.out.println("9. Restock not existing item.");
            System.out.println("10. List clients request for new item.");
            System.out.println("11. Remove client request. (return moneq and delete bot orders)");

            // user Input
            System.out.print("Enter a command: ");
            String command = br.readLine();
            System.out.println("You selected command: " + command);
            if(command.equals("1"))
            {
                System.out.println("Selected: 1.Show items list. ");
                st.listItems();
            }
            else if(command.equals("2"))
            {
                System.out.println("Selected: 2. Search for book by title (partialy). Enter title of the book: ");
                String title = br.readLine();
                st.searchBooksByTitle(title);
            }
            else if(command.equals("3"))
            {
                System.out.println("Selected:  Search for book by author (partialy). Enter author of the book: ");
                String author = br.readLine();
                st.searchBooksByAuthor(author);
            }
            else if(command.equals("4"))
            {
                System.out.println("Selected:  Search for board game by name (partialy). Enter game name: ");
                String gameName = br.readLine();
                st.searchGamesByName(gameName);
            }
            else if(command.equals("5"))
            {
                System.out.println("Selected:  Search for board game by number of player.. Enter game Number of player(1,2,3...): ");
                String gameNOP = br.readLine();
                st.searchGamesByNumberOfPlayers(Integer.parseInt(gameNOP));
            }
            else if(command.equals("6"))
            {// implement checks for Items and customer
                System.out.println("Selected:  Purch Item by catalog number. Enter ItemId: ");
                String ItemId = br.readLine();
                System.out.println("Selected:  Purch Item by catalog number. Enter CustId: ");
                String CustId = br.readLine();
                System.out.println("Selected:  Purch Item by catalog number. Enter Qty: ");
                String QTY = br.readLine();
                Integer salesIdNext = st.nextSalesId();
                st.purchExistingItem(salesIdNext,Integer.parseInt(ItemId), Integer.parseInt(CustId), Integer.parseInt(QTY));
            }
            else if(command.equals("7"))
            {
                Integer itemIdNext = st.nextItemId();
                Integer salesIdNext = st.nextSalesId();
                System.out.println("Selected:  Enter CustId: ");
                String custId = br.readLine();
                Integer qty = Main.createNotExistingItem(st,br,itemIdNext);
                st.requestItem(salesIdNext,itemIdNext, Integer.parseInt(custId), qty);
                st.purchExistingItem(salesIdNext+1,itemIdNext, Integer.parseInt(custId), qty);
            }
            else if(command.equals("8"))
            {
                System.out.println("Selected:  Restock existing item. Please enter ItemId:");
                String itemId = br.readLine();
                System.out.println("enter new qty for the item (new qty + old qty)");
                String itemQTy= br.readLine();
                st.updateQtyOfItem(Integer.parseInt(itemId),Integer.parseInt(itemQTy));
                st.cleanRequestedItemSalesOrders(Integer.parseInt(itemId),Integer.parseInt(itemQTy));
            }
            else if(command.equals("9"))
            {
                System.out.println("Selected:  Restock not existing item. Please enter ItemId:");
                Integer itemIdNext = st.nextItemId();
                Main.createNotExistingItem(st,br,itemIdNext);// no need for update of qty and clean in salesOrders
            }
            else if(command.equals("10"))
            {
                System.out.println("Selected:  List clients request for new item.");
                st.getAllSalesOrderRequests();
            }
            else if(command.equals("11"))
            {
                System.out.println("Selected:  Remove client request. Please enter SalesId to delete it:");
                String salesId = br.readLine();
                st.deleteSalesOrder(Integer.parseInt(salesId));
            }
            else if(command.equals("12"))
            {
                System.out.println("Selected:  insert Customer with name:");
                String name = br.readLine();
                st.insertCustomer(name);
            }
            //else {break;}
        //}while(true);

    }

    private static Integer createNotExistingItem(StoreCommands st,  BufferedReader br, Integer itemIdNext ) throws IOException, SQLException {
        System.out.println("Selected:  Order not existing item. Insert the item first. What type of item it is {boardgame-0 or book-1 }: ");
        String itemType = br.readLine();
        InventTable it=null;
        String qty ="0";
        String name="";
        String price="0";
        String desc = "";
        if(Integer.parseInt(itemType) == InventTable.itemTypeValues.BOOK.ordinal())
        {
            System.out.println("Please enter a string in the filling the format:");
            System.out.println("Enter separated by enter the following Description/Name/Author/Price/OrderQtyValueNumber");
            desc = br.readLine();
            name = br.readLine();
            String author = br.readLine();
            price = br.readLine();
            qty = br.readLine();
            it = new InventTable(itemIdNext,desc, name, author,0,0,Double.parseDouble(price),
                    false,Integer.parseInt(qty), InventTable.itemTypeValues.BOOK.ordinal());
            st.insertItem(it);
            return Integer.parseInt(qty);
        }
        else if(Integer.parseInt(itemType) == InventTable.itemTypeValues.BOARDGAME.ordinal())
        {
            System.out.println("Please enter a string in the filling the format:");
            System.out.println("Enter separated by enter the following  Description/Name//Price/OrderQtyValueNumber/MinNumberOfPlayer/MaxNumberOfPlayers/QTY");
            desc = br.readLine();
            name = br.readLine();
            price = br.readLine();
            String minplayers = br.readLine();
            String maxPlayers = br.readLine();
            qty = br.readLine();
            it = new InventTable(itemIdNext,desc, name, "",Integer.parseInt(minplayers),Integer.parseInt(maxPlayers),Double.parseDouble(price),
                    false,Integer.parseInt(qty), InventTable.itemTypeValues.BOARDGAME.ordinal());
            st.insertItem(it);
            return Integer.parseInt(qty);
        }
        else{
            System.out.println("Selected:  Is not supported type ");
        }
        return 0;
    }

}