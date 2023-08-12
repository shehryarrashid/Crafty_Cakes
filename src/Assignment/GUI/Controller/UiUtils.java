package Assignment.GUI.Controller;

import Assignment.Class.Employee;
import Assignment.Class.QualityController;
import Assignment.Loggs.MyLogger;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

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

    public boolean nameChecks(String name){

        if(name.isBlank() || name.isEmpty()){
            return false;
        }
        for (int i = 0 ; i < name.length(); i++){
            if (!Character.isLetter(name.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public int cakesCheck(String numCakes){
        if(numCakes.isEmpty()){
            return 0;
        }
        try{
            int num = Integer.parseInt(numCakes,10);
            if(num < 0){
                throw new Exception();
            }
            return num;
        }catch (Exception e){
            return -1;
        }

    }

    public Employee employeeCreator(String name,int cakesCovered,String role){
        try {
            switch (role) {
                case "REGULAR" -> {
                    return new Employee(name, cakesCovered);
                }
                case "QUALITY_CONTROLLER" -> {
                    return new QualityController(name,cakesCovered);
                }
            }
        }catch (Exception e){
            throw new RuntimeException("PROBLEM DURING THE EMPLOYEE DATA CREATION");
        }
        return null;
    }

    public void logApplicationClose(){
        MyLogger.log("APPLICATION CLOSED");
    }

    public void showWelcomeScreen(Stage stage){
        WelcomeScreenController wsc = new WelcomeScreenController();
        wsc.showWelcomeScene();
        stage.close();
    }

    public void showSearchScreen(Stage stage){
        SearchScreenController ssc = new SearchScreenController();
        ssc.showSearchScene();
        stage.close();
    }

    public void showAllEmployeesScreen(Stage stage){
        AllEmployeesScreenController allEmployeesScreenController = new AllEmployeesScreenController();
        allEmployeesScreenController.showEmployeeTable();
        stage.close();
    }

    public void showEmployeeDataScreen(Employee employee,Stage stage){
        EmployeeDataScreenController edc = new EmployeeDataScreenController(employee);
        edc.showEmployeeDataScene();
        stage.close();
    }

    public void showNewEmployeeScreen(Stage stage){
        NewEmployeeScreenController nesc = new NewEmployeeScreenController();
        nesc.showNewEmployeeScreen();
        stage.close();
    }
}
