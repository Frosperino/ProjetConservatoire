module carpentier.proj.conservatoire.conservatoire {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens carpentier.proj.conservatoire.conservatoire to javafx.fxml;
    exports carpentier.proj.conservatoire.conservatoire;
}
