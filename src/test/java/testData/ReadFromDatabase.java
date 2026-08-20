package testData;

import utils.ReadFromProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFromDatabase {

    public static String getUsername;
    public static String getPassword;

    public static void dbConnection() {
        String dbUrl = ReadFromProperty.getRequiredProperty("dbUrl");
        String dbUsername = ReadFromProperty.getRequiredProperty("dbUsername");
        String dbPassword = ReadFromProperty.getRequiredProperty("dbPassword");

        try (Connection connection = DatabaseConnection.getDBConnection(dbUrl, dbUsername, dbPassword)) {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE id = 2")){

                while (resultSet.next()){
                    getUsername = resultSet.getString("email");
                    getPassword = resultSet.getString("password");
                    System.out.println("Email "+getUsername + ", Password "+getPassword);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
    }

    /**
     * Returns all user login credentials from the database as a 2D array
     * for use with TestNG @DataProvider. Each row is {email, password}.
     */
    public static Object[][] getLoginData() {
        String dbUrl = ReadFromProperty.getRequiredProperty("dbUrl");
        String dbUsername = ReadFromProperty.getRequiredProperty("dbUsername");
        String dbPassword = ReadFromProperty.getRequiredProperty("dbPassword");

        List<Object[]> loginData = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getDBConnection(dbUrl, dbUsername, dbPassword)) {
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT email, password FROM users")) {

                while (resultSet.next()) {
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    loginData.add(new Object[]{email, password});
                    System.out.println("Loaded credentials - Email: " + email);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving login data: " + e.getMessage());
        }

        return loginData.toArray(new Object[0][0]);
    }
}
