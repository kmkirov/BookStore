package LABELS;

public class SQLStatements {

    public static final String CUSTTABLE      = "CustTable";
    public static final String CUSTTABLE_NAME  = "NAME";
    public static final String CUSTTABLE_ID    = "ID";//dasdas
    public static final String STATEMENT_CREATE_SQL_CUSTTABLE =
            "CREATE TABLE IF NOT EXISTS " + SQLStatements.CUSTTABLE + "( " +
                    SQLStatements.CUSTTABLE_ID + " integer PRIMARY KEY ,\n" +
                    SQLStatements.CUSTTABLE_NAME +  " text NOT NULL\n" +
            ");";
    public static final String STATEMENT_INSERT_SQL_CUSTTABLE = "INSERT INTO " + SQLStatements.CUSTTABLE  +
            " (" + SQLStatements.CUSTTABLE_ID + ", " +
                 SQLStatements.CUSTTABLE_NAME +
            " ) VALUES(?,?);";
    public static final String STATEMENT_DELETE_SQL_CUSTTABLE = "DELETE FROM " + SQLStatements.CUSTTABLE; ///" WHERE prod_id=4;

    public static final String INVENTTABLE      = "INVENTTABLE";
    public static final String INVENTTABLE_ITEMID  = "ITEMID";
    public static final String INVENTTABLE_NAME  = "NAME";
    public static final String INVENTTABLE_DESCRIPTION  = "DESCRIPTION";
    public static final String INVENTTABLE_AUTHOR  = "AUTHOR";
    public static final String INVENTTABLE_NOPMIN  = "NOPMIN";
    public static final String INVENTTABLE_NOPMAX  = "NOPMAX";
    public static final String INVENTTABLE_PRICE  = "PRICE";
    public static final String INVENTTABLE_RFS  = "RFS";
    public static final String INVENTTABLE_QTY  = "PRICE";
    public static final String INVENTTABLE_ITEMTYPE  = "ITEMTYPE";

    public static final String STATEMENT_SELECT_SQL_INVENTTABLE = "SELECT "
            + SQLStatements.INVENTTABLE_ITEMID + ','
            + SQLStatements.INVENTTABLE_NAME + ','
            + SQLStatements.INVENTTABLE_DESCRIPTION + ','
            + SQLStatements.INVENTTABLE_AUTHOR + ','
            + SQLStatements.INVENTTABLE_NOPMIN + ','
            + SQLStatements.INVENTTABLE_NOPMAX + ','
            + SQLStatements.INVENTTABLE_PRICE + ','
            + SQLStatements.INVENTTABLE_RFS + ','
            + SQLStatements.INVENTTABLE_QTY + ','
            + SQLStatements.INVENTTABLE_ITEMTYPE + ','
            + " FROM " + SQLStatements.INVENTTABLE ;

    public static final String STATEMENT_CREATE_SQL_INVENTTABLE =
            "CREATE TABLE IF NOT EXISTS " + SQLStatements.INVENTTABLE + "( " +
                    SQLStatements.INVENTTABLE_ITEMID + " integer PRIMARY KEY AUTOINCREMENT ,\n" +
                    SQLStatements.INVENTTABLE_NAME +  " text NOT NULL\n" +
                    SQLStatements.INVENTTABLE_DESCRIPTION +  " text NOT NULL\n" +
                    SQLStatements.INVENTTABLE_AUTHOR +  " text \n" +
                    SQLStatements.INVENTTABLE_NOPMIN +  " integer NOT NULL\n" +
                    SQLStatements.INVENTTABLE_NOPMAX +  " integer NOT NULL\n" +
                    SQLStatements.INVENTTABLE_PRICE +  " real NOT NULL\n" +
                    SQLStatements.INVENTTABLE_RFS +  " boolean NOT NULL\n" +
                    SQLStatements.INVENTTABLE_QTY +  " int NOT NULL\n" +
                    SQLStatements.INVENTTABLE_ITEMTYPE +  " integer NOT NULL\n" +
                    ");";
    public static final String STATEMENT_INSERT_SQL_INVENTTABLE = "INSERT INTO " + SQLStatements.INVENTTABLE   +
            " (" + SQLStatements.INVENTTABLE_ITEMID + ", " +
            SQLStatements.INVENTTABLE_NAME + ", " +
            SQLStatements.INVENTTABLE_DESCRIPTION  + ", " +
            SQLStatements.INVENTTABLE_AUTHOR + ", " +
            SQLStatements.INVENTTABLE_NOPMIN + ", " +
            SQLStatements.INVENTTABLE_NOPMAX  + ", " +
            SQLStatements.INVENTTABLE_PRICE  + ", " +
            SQLStatements.INVENTTABLE_RFS  + ", " +
            SQLStatements.INVENTTABLE_ITEMTYPE  + ", " +
            SQLStatements.INVENTTABLE_QTY  +
            " ) VALUES(?,?,?,?,?,?,?,?,?,?);";
    public static final String STATEMENT_DELETE_SQL_INVENTTABLE = "DELETE FROM " + SQLStatements.INVENTTABLE  ; ///" WHERE prod_id=4;

    public static final String SALESORDER      = "SALESORDER";
    public static final String SALESORDER_SALESID  = "SALESID";
    public static final String SALESORDER_CUSTID    = "CUSTID";
    public static final String SALESORDER_SALESTYPE    = "SALESTYPE";
    public static final String SALESORDER_ITEMID    = "ITEMID";
    public static final String SALESORDER_QTY    = "QTY";
    public static final String STATEMENT_CREATE_SQL_SALESORDER =
            "CREATE TABLE IF NOT EXISTS " + SQLStatements.SALESORDER + "( " +
                    SQLStatements.SALESORDER_SALESID  + " integer PRIMARY KEY AUTOINCREMENT ,\n" +
                    "FOREIGN KEY (" + SQLStatements.SALESORDER_CUSTID + ") REFERENCES "+ SQLStatements.CUSTTABLE + "("+ SQLStatements.CUSTTABLE_ID +")" +
                    "FOREIGN KEY (" + SQLStatements.SALESORDER_ITEMID  + ") REFERENCES "+ SQLStatements.INVENTTABLE + "("+ SQLStatements.INVENTTABLE_ITEMID +")" +
                    SQLStatements.SALESORDER_SALESTYPE +  " integer NOT NULL\n" +
                    SQLStatements.SALESORDER_QTY +  " integer NOT NULL\n"+
                    ");";
    public static final String STATEMENT_INSERT_SQL_SALESORDER = "INSERT INTO " + SQLStatements.SALESORDER   +
            " (" + SQLStatements.SALESORDER_SALESID + ", " +
            SQLStatements.SALESORDER_CUSTID + ", " +
            SQLStatements.SALESORDER_SALESTYPE  + ", " +
            SQLStatements.SALESORDER_ITEMID + ", " +
            SQLStatements.SALESORDER_QTY +
            " ) VALUES(?,?,?,?,?);";
}
