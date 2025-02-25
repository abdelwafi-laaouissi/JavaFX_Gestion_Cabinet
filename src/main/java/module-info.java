module ma.enset.gestionconsultationdbcc {
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.controls;


    opens ma.enset.gestionconsultationdbcc.controllers to javafx.fxml;
    opens ma.enset.gestionconsultationdbcc.entities to javafx.base;
    exports ma.enset.gestionconsultationdbcc;
}