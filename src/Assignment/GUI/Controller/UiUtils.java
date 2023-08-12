package Assignment.GUI.Controller;

import javafx.scene.control.Alert;

public class UiUtils {

    private Alert alert;
    private final String AlertTitle = "SYSTEM ALERT";

    public UiUtils(){
        this.alert = new Alert(Alert.AlertType.INFORMATION);

    }

    public void showAlert(Alert.AlertType alertType, String message,boolean isImportant){
        this.alert.setAlertType(alertType);
        alert.setTitle(this.AlertTitle);
        if(isImportant){
            alert.setHeaderText("IMPORTANT");
        }
        else alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public void closeAlert(){
        this.alert.close();
    }
}
