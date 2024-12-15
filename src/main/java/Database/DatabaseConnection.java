package Database;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashMap;
import java.util.Map;

public class DatabaseConnection {

    private final Dotenv dotenv;

    public DatabaseConnection() {
        this.dotenv = Dotenv.configure().load();
    }

    public Map<String, String> getDbDetails() {
        Map<String, String> map = new HashMap<>();

        map.put("url", this.dotenv.get("DB_URL"));
        map.put("user", this.dotenv.get("DB_USER"));
        map.put("password", this.dotenv.get("DB_PASSWORD"));

        return map;
    }
}
