package Database;

import java.sql.Connection;

public class TestDatabase {
    public static void main(String[] args) {
        DatabaseManager manager = new DatabaseManager();
        Connection connection = manager.connect();

        if (connection != null) {
            System.out.println("Database connected successfully");
        } else {
            System.out.println("Database connection failed");
        }
    }
}
