package businessLogic;

import LABELS.SQLStatements;
import com.sun.corba.se.impl.encoding.BufferManagerRead;

import java.io.*;
import java.sql.*;

public class Main {

    public static void main( String args[] ) throws IOException, SQLException {
        StoreCommands st = new StoreCommands();
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please select option by typing a number:");
        System.out.println("1. Show items list. "); // ready not tested
        System.out.println("2. Search for book by title (partialy).");// ready not tested
        System.out.println("3. Search for book by author (partialy).");// ready not tested
        System.out.println("4. Search for board game by name (partialy).");// ready not tested
        System.out.println("5. Search for board game by number of player.");// ready not tested

        System.out.println("6. Purch Item by catalog number.(catalog changes)");
        System.out.println("7. Order not existing item."); // add new item to items, create purch order and request
        System.out.println("8. Restock existing item.");
        System.out.println("8. Restock not existing item.");
        System.out.println("9. List clients request for new item.");
        System.out.println("10. Remove client request. (return moneq and deelte bot orders)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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

    }
}