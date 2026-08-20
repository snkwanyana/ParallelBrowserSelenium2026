package testData;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getDBConnection(String dbUrl, String dbUsername, String dbPassword) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            System.out.println("Database connection established successfully.");

        } catch (Exception e) {
            System.out.println("Failed to establish database connection.");
            e.printStackTrace();
        }

        return connection;
    }
}
