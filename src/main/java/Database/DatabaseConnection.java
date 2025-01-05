package Database;

// Importing the Dotenv class for loading environment variables
import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashMap;
import java.util.Map;

public class DatabaseConnection {

    // Declaring a Dotenv object to hold the environment variables
    private final Dotenv dotenv;

    // Constructor that initializes the Dotenv object by loading the environment variables from a ..env file
    public DatabaseConnection() {
        this.dotenv = Dotenv.configure().load(); // Loads the ..env file and its variables
    }

    // Method that returns a Map with database connection details (url, user, and password)
    public Map<String, String> getDbDetails() {
        // Creating a new HashMap to store the database details
        Map<String, String> map = new HashMap<>();

        // Adding the database connection details (URL, user, and password) to the map
        // These values are fetched from the ..env file using the dotenv object
        map.put("url", this.dotenv.get("DB_URL")); // Fetching the DB URL
        map.put("user", this.dotenv.get("DB_USER")); // Fetching the DB user
        map.put("password", this.dotenv.get("DB_PASSWORD")); // Fetching the DB password

        // Returning the map with all the database connection details
        return map;
    }
}