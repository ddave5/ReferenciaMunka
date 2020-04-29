package hu.adatb.utils;

import javafx.scene.control.Alert;

public class Utils {
    public static void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hiba");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
    public static void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,message);
        alert.setTitle("Siker");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}


