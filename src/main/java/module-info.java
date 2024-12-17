module org.example.projectworkspace {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    exports org.example.projectworkspace.GUI; // Export the package containing TestScreen

    opens org.example.projectworkspace.GUI to javafx.fxml; // Optionally open it for reflection if needed
}