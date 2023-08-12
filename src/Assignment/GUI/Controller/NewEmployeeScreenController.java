package Assignment.GUI.Controller;

import Assignment.Class.Employee;
import Assignment.Database.DBCommands;
import Assignment.Loggs.MyLogger;
import Assignment.Role;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewEmployeeScreenController {
    private Stage stage;
    private UiUtils uiUtils;
    private DBCommands dbCommands;

    @FXML private TextField cakesCovered;
    @FXML private TextField empName;
    @FXML private SplitMenuButton smbType;




    public NewEmployeeScreenController(){
        this.stage = new Stage();
        this.uiUtils = new UiUtils();
        this.dbCommands = new DBCommands();
    }

    public void showNewEmployeeScreen(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/NewEmployeeScreen.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("CC Management System");
            this.stage.show();

        }catch (Exception e){
            uiUtils.showAlert(Alert.AlertType.ERROR,"ERROR LOADING PAGE",true);
            MyLogger.log("ERROR OPENING NEW EMPLOYEE SCREEN");
        }
    }

    public void handleRegular(){
        smbType.setText(Role.REGULAR.name());
    }

    public void handleQualityController(){
        smbType.setText(Role.QUALITY_CONTROLLER.name());
    }


    public void handleCreateButton(){
        String name = this.empName.getText();
        int cakes = uiUtils.cakesCheck(this.cakesCovered.getText());
        if(uiUtils.nameChecks(name) && cakes != -1){
            Employee employee = uiUtils.employeeCreator(name,cakes,this.smbType.getText());
            dbCommands.insertEmployee(employee);
            this.uiUtils.showAlert(Alert.AlertType.INFORMATION,"Employee Successfully Added",true);
            MyLogger.log("New Employee Added");
            this.uiUtils.showWelcomeScreen(this.stage);
        }
        else{
            this.uiUtils.showAlert(Alert.AlertType.ERROR,"FAILED : INVALID DATA ENTERED",true);
            this.uiUtils.showWelcomeScreen(this.stage);
        }
    }

    @FXML
    public void initialize(){

    }
}
