module edu.lk.ijse.ganewaththalatex.ganewaththalatex {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires net.sf.jasperreports.core;
    requires static lombok;


    opens edu.lk.ijse.ganewaththalatex.ganewaththalatex.controller to javafx.fxml;
    opens edu.lk.ijse.ganewaththalatex.ganewaththalatex.dto.tm to javafx.base;

    exports edu.lk.ijse.ganewaththalatex.ganewaththalatex;
}

/*module lk.ijse.supermarketfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens lk.ijse.supermarketfx.controller to javafx.fxml;
    exports lk.ijse.supermarketfx;
}*/