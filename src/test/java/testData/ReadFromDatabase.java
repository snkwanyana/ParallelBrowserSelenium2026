package testData;

import java.sql.*;

public class ReadFromDatabase {

    public static String getUsername;
    public static String getPassword;

    public static void dbConnection() {

        String dbUrl = "jdbc:mysql://102.222.124.22:3306/ndosian6b8b7_teaching";
        String dbUsername = "ndosian6b8b7_teaching";
        String dbPassword = "^{SF0a=#~[~p)@l1";

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
}
