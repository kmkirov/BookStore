package SQLiteManagement;

import LABELS.SQLStatements;
import model.Customers;
import model.InventTable;
import model.SalesOrder;

import java.sql.*;
import java.util.ArrayList;

public class SalesOrderStorageManager {
    Connection conn = null;

    public ArrayList getAllSalesOrders() throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        conn = dc.getConnection();
        Statement stmt = null;
        try {
            String selectString = SQLStatements.STATEMENT_SELECT_SQL_SALESORDER;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectString);
            ArrayList itemsList = new ArrayList();
            SalesOrder itObject;
            while (rs.next()) {
                Integer SALESID = rs.getInt(SQLStatements.SALESORDER_SALESID);
                Integer ITEMID = rs.getInt(SQLStatements.SALESORDER_ITEMID);
                Integer QTY = rs.getInt(SQLStatements.SALESORDER_QTY);
                Integer SALESTYPE = rs.getInt(SQLStatements.SALESORDER_SALESTYPE);
                Integer CUSTID = rs.getInt(SQLStatements.SALESORDER_CUSTID);
                itObject = new SalesOrder(ITEMID, SALESID, CUSTID, SALESTYPE, QTY);
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

    public void printAllSalesOrders() throws SQLException {
        for (Object item: this.getAllSalesOrders()) {
            SalesOrder o = (SalesOrder) item;
            o.print();
        }
    }

    public ArrayList printAllSalesOrderRequests() throws SQLException {
        ArrayList oredersList = new ArrayList();
        for (Object item: this.getAllSalesOrders()) {
            SalesOrder so = (SalesOrder) item;
            if (so.getSalesType() == SalesOrder.SalesOrderTupe.Requested.ordinal()){
                so.print();
                oredersList.add(so);
            }
        }
        return oredersList;
    }

    /// support only full clearance, not partial
    public void cleanRequestsAfterRestock(Integer itemId, Integer qtyReceived) throws SQLException {
        ArrayList allRequest = this.printAllSalesOrderRequests();
        Integer remainingQty = qtyReceived;
        /// array needs to be sorted in order ... bug now
        for (Object so: allRequest) {
            SalesOrder soRequest = (SalesOrder) so;
            if(soRequest.getItemId() == itemId && soRequest.getQty()<= remainingQty)
            {
                remainingQty = remainingQty - soRequest.getQty();
                this.deleteIdFromSalesOrderes(soRequest.getSalesId());
            }
        }

    }

    public void insertSalesOrderTable(SalesOrder salesOrder) throws SQLException {
        String sql = SQLStatements.STATEMENT_INSERT_SQL_SALESORDER;
        Connection conn = null;
        DatabaseConnection dc = DatabaseConnection.getInstance();
        conn = dc.getConnection();
        Statement stmt = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, salesOrder.getSalesId());
            pstmt.setInt(2, salesOrder.getCustId());
            pstmt.setInt(3, salesOrder.getItemId());
            pstmt.setInt(4, salesOrder.getSalesType());
            pstmt.setInt(5, salesOrder.getQty());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteIdFromSalesOrderes(int id) throws SQLException {

        String sql = SQLStatements.STATEMENT_DELETE_SQL_SALESORDER;
        Connection conn = null;
        DatabaseConnection dc = DatabaseConnection.getInstance();
        conn = dc.getConnection();
        Statement stmt = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void createSalesOrderTable() throws SQLException {
        Connection conn = null;
        DatabaseConnection dc = DatabaseConnection.getInstance();
        conn = dc.getConnection();
        Statement stmt = null;
        try {
            String selectString = SQLStatements.STATEMENT_CREATE_SQL_SALESORDER;
            stmt = conn.createStatement();
            stmt.executeQuery(selectString);
        }

        catch (SQLException e ) {
            System.out.println(e.getMessage());
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }

    public Integer getNextSalesId() throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        conn = dc.getConnection();
        Statement stmt = null;
        Integer NextId=0;
        try {
            String selectString = SQLStatements.STATEMENT_GETMAXID_SQL_SALESORDER;
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(selectString);


            while (rs.next()) {
                Integer ID = rs.getInt(SQLStatements.INVENTTABLE_MAXID);
                NextId=ID+1;
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
