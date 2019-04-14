package SQLiteManagement;

import LABELS.SQLStatements;
import model.InventTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import SQLiteManagement.DatabaseConnection;
public class InventoryStorageManager {

    Connection conn = null;
    public ArrayList getAllItems() throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        conn = dc.getConnection();
        Statement stmt = null;
        try {
            String selectString = SQLStatements.STATEMENT_SELECT_SQL_INVENTTABLE;
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(selectString);
            ArrayList itemsList = new ArrayList();
            InventTable itObject;
            while (rs.next()) {
                Integer ITEMID = rs.getInt(SQLStatements.INVENTTABLE_ITEMID);
                String DESCRIPTION = rs.getString(SQLStatements.INVENTTABLE_DESCRIPTION);
                String AUTHOR = rs.getString(SQLStatements.INVENTTABLE_AUTHOR);
                String NAME = rs.getString(SQLStatements.INVENTTABLE_NAME);
                Integer NOPMAX = rs.getInt(SQLStatements.INVENTTABLE_NOPMAX);
                Integer NOPMIN = rs.getInt(SQLStatements.INVENTTABLE_NOPMIN);
                Integer QTY = rs.getInt(SQLStatements.INVENTTABLE_QTY);
                Integer ITEMTYPE = rs.getInt(SQLStatements.INVENTTABLE_ITEMTYPE);
                double PRICE = rs.getDouble(SQLStatements.INVENTTABLE_PRICE);
                Boolean RFS = rs.getBoolean(SQLStatements.INVENTTABLE_RFS);
                itObject = new InventTable(ITEMID, DESCRIPTION, NAME, AUTHOR, NOPMIN,NOPMAX, PRICE, RFS, QTY, ITEMTYPE );
                itemsList.add(itObject);
            }
            return  itemsList;
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        } finally {
            if (stmt != null) { stmt.close(); }
        }
        return null;
    }

    public void printAllItems() throws SQLException {
        for (Object item: this.getAllItems()) {
            InventTable o = (InventTable) item;
            o.print();
        }
    }

    public void searchItemBookTitles(String titlePart) throws SQLException {
        for (Object item: this.getAllItems()) {
            InventTable o = (InventTable) item;
            if( o.itemBookTitleContainsWord(titlePart)){
                o.print();
            }
        }
    }

    public void searchBooksByAuthor(String author) throws SQLException {
        for (Object item: this.getAllItems()) {
            InventTable o = (InventTable) item;
            if( o.itemBookAuthorContainsWord(author)){
                o.print();
            }
        }
    }

    public void searchGamesByName(String gameName) throws SQLException {
        for (Object item: this.getAllItems()) {
            InventTable o = (InventTable) item;
            if( o.itemGameNameContainsWord(gameName)) {
                o.print();
            }
        }
    }

    public void searchGamesByNumberOfPlayers(Integer gameNOP) throws SQLException {
        for (Object item: this.getAllItems()) {
            InventTable o = (InventTable) item;
            if( o.itemGameSuitableForPlayers(gameNOP)) {
                o.print();
            }
        }
    }
}
