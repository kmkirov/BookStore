package SQLiteManagement;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import LABELS.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Deny Prasetyo https://gist.github.com/jasoet/3843797
 */
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    String url = "jdbc:sqlite:test.db";

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url);

            if(this.connection == null){
                System.out.println(Constants.SQL_CONNECTION_ERROR);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }


    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
}