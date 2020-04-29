package hu.alkfejl.Utils;

import javafx.scene.control.Alert;

public class Utils {

    public static void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Hiba!");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vége!");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.show();
    }
    public static void showTimesUp(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setTitle("Time's up!");
        alert.show();
    }

}
