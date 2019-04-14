package SQLiteManagement;

import LABELS.SQLStatements;
import model.Customers;
import model.InventTable;

import java.sql.*;
import java.util.ArrayList;

public class CustomerStogageManager {

    Connection conn = null;

    public ArrayList getAllCustomers() throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        conn = dc.getConnection();
        Statement stmt = null;
        try {
            String selectString = SQLStatements.STATEMENT_SELECT_SQL_CustTable;
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(selectString);
            ArrayList itemsList = new ArrayList();
            Customers itObject;
            while (rs.next()) {
                Integer ID = rs.getInt(SQLStatements.CUSTTABLE_ID);
                String NAME = rs.getString(SQLStatements.CUSTTABLE_NAME);

                itObject = new Customers(NAME, ID);
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

    public void printAllCustomers() throws SQLException {
        for (Object item: this.getAllCustomers()) {
            InventTable o = (InventTable) item;
            o.print();
        }
    }

    public void insertInventTable(Customers customer) throws SQLException {
        String sql = SQLStatements.STATEMENT_INSERT_SQL_CUSTTABLE;
        Connection conn = null;
        DatabaseConnection dc = DatabaseConnection.getInstance();
        conn = dc.getConnection();
        Statement stmt = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customer.getId());
            pstmt.setString(2, customer.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createCustomerTable() throws SQLException {
        Connection conn = null;
        DatabaseConnection dc = DatabaseConnection.getInstance();
        conn = dc.getConnection();
        Statement stmt = null;
        try {
            String selectString = SQLStatements.STATEMENT_CREATE_SQL_CUSTTABLE;
            stmt = conn.createStatement();
            stmt.executeQuery(selectString);
        }

        catch (SQLException e ) {
            System.out.println(e.getMessage());
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }

    public Integer getNextCustId() throws SQLException {
        DatabaseConnection dc = DatabaseConnection.getInstance();
        conn = dc.getConnection();
        Statement stmt = null;
        Integer NextId=0;
        try {
            String selectString = SQLStatements.STATEMENT_GETMAXID_SQL_CUSTTABLE;
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
