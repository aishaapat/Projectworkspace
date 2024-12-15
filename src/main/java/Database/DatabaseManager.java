package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class DatabaseManager {

    public Connection connect() {
        DatabaseConnection dbConn = new DatabaseConnection();
        Map<String, String> dbDetails = dbConn.getDbDetails();

        try {
            // Attempt to establish a connection using the details from DatabaseConnection
            return DriverManager.getConnection(dbDetails.get("url"), dbDetails.get("user"), dbDetails.get("password"));
        } catch (SQLException e) {
            // Log the exception message and stack trace if the connection fails
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
            return null; // Return null to signify failure to connect
        }
    }
}