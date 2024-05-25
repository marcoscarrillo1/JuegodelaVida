module org.example.trabajo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires org.apache.logging.log4j;

    opens Tablero to com.google.gson;
    opens Individuo to com.google.gson;
    opens Json to com.google.gson;
    opens Recursos to com.google.gson;

    exports Estructuras;
    exports Tablero;

    opens org.example.trabajo to javafx.fxml;
    exports org.example.trabajo;
}