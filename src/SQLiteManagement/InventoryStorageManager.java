package SQLiteManagement;

import LABELS.SQLStatements;
import model.InventTable;

import java.sql.*;
import java.util.*;

import SQLiteManagement.DatabaseConnection;
public class InventoryStorageManager {

    Connection conn = null;

    public InventoryStorageManager() throws SQLException {
        //this.conn = null;
        this.createInventTable();
    }

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
/// better if a query was made to filter the result set ....
    public void searchItemBookTitles(String titlePart) throws SQLException {
        for (Object item: this.getAllItems()) {
            InventTable o = (InventTable) item;
            if( o.itemBookTitleContainsWord(titlePart)){
                o.print();
            }
        }
    }
    /// better if a query was made to filter the result set ....
    public void searchBooksByAuthor(String author) throws SQLException {
        for (Object item: this.getAllItems()) {
            InventTable o = (InventTable) item;
            if( o.itemBookAuthorContainsWord(author)){
                o.print();
            }
        }
    }
    /// better if a query was made to filter the result set ....
    public void searchGamesByName(String gameName) throws SQLException {
        for (Object item: this.getAllItems()) {
            InventTable o = (InventTable) item;
            if( o.itemGameNameContainsWord(gameName)) {
                o.print();
            }
        }
    }
    /// better if a query was made to filter the result set ....
    public void searchGamesByNumberOfPlayers(Integer gameNOP) throws SQLException {
        for (Object item: this.getAllItems()) {
            InventTable o = (InventTable) item;
            if( o.itemGameSuitableForPlayers(gameNOP)) {
                o.print();
            }
        }
    }

    private void createInventTable() throws SQLException {
        Connection conn = null;
        DatabaseConnection dc = DatabaseConnection.getInstance();
        conn = dc.getConnection();
        Statement stmt = null;
        try {
            String selectString = SQLStatements.STATEMENT_CREATE_SQL_INVENTTABLE;
            stmt = conn.createStatement();
            stmt.executeUpdate(selectString);
            }

         catch (SQLException e ) {
            System.out.println(e.getMessage());
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }
//update https://docs.oracle.com/javase/tutorial/jdbc/basics/retrieving.html#rs_update
    public void insertInventTable(InventTable inventTableObject) throws SQLException {
        String sql = SQLStatements.STATEMENT_INSERT_SQL_INVENTTABLE;
        Connection conn = null;
        DatabaseConnection dc = DatabaseConnection.getInstance();
        conn = dc.getConnection();
        Statement stmt = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, inventTableObject.getItemId());
            pstmt.setString(2, inventTableObject.getName());
            pstmt.setString(3, inventTableObject.getDescription());
            pstmt.setString(4, inventTableObject.getAuthor());
            pstmt.setInt(5, inventTableObject.getNumOfPlayersMin());
            pstmt.setInt(6, inventTableObject.getNumOfPlayersMax());
            pstmt.setDouble(7, inventTableObject.getPrice());
            pstmt.setBoolean(8,inventTableObject.isReadyForSale());
            pstmt.setInt(9,inventTableObject.getItemType());
            pstmt.setInt(10,inventTableObject.getQuantity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateInventTableQTY(Integer itemId,  Integer itemQty) throws SQLException {
        String sql = SQLStatements.STATEMENT_UPDATE_SQL_INVENTTABLE;
        Connection conn = null;
        DatabaseConnection dc = DatabaseConnection.getInstance();
        conn = dc.getConnection();
        Statement stmt = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, itemQty);
            pstmt.setInt(2, itemId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Integer getNextItemId() throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        conn = dc.getConnection();
        Statement stmt = null;
        Integer NextId=0;
        try {
            String selectString = SQLStatements.STATEMENT_GETMAXID_SQL_INVENTTABLE;
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(selectString);


            while (rs.next()) {
                Integer ITEMID = rs.getInt(SQLStatements.INVENTTABLE_MAXID);
                NextId=ITEMID+1;
            }
            return  NextId;
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        } finally {
            if (stmt != null) { stmt.close(); }
        }
        return 0;
    }

}

