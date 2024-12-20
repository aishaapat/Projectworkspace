module org.example.projectworkspace {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    exports org.example.projectworkspace to javafx.fxml;
    exports org.example.projectworkspace.GUI to javafx.graphics;
}