package restaurant.ui;

import javafx.scene.control.Alert;

public class Notification {
    void showAlert(String invalidInput, String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(invalidInput);
        alert.setHeaderText(null);
        alert.setContentText(s);

        alert.show();
    }
}
