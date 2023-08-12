package Assignment.GUI.Controller;

import Assignment.Class.Employee;
import Assignment.Database.DBCommands;
import Assignment.Loggs.MyLogger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EmployeeDataScreenController {

    // ----- GLOBAL VARIABLES ----- //
    private Employee employee;
    private Stage stage;
    private final UiUtils uiUtils;

    // --- FXML VARIABLES --- //
    @FXML private Label lblTitle;
    @FXML private Label lblName;
    @FXML private  Label lblCakes;
    @FXML private TextField txtName;
    @FXML private TextField txtCakes;

    public EmployeeDataScreenController(Employee employee){
        this.employee = employee;
        this.stage = new Stage();
        this.uiUtils = new UiUtils();
    }

    public void showEmployeeDataScene(){
        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/EmployeeDataScreen.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("CC Management System");
            this.stage.show();

        }catch (Exception e){
            uiUtils.showAlert(Alert.AlertType.ERROR,"ERROR LOADING PAGE",true);
            MyLogger.log("ERROR LOADING EMPLOYEE DATA PAGE");

        }
    }

    public void handePlusButton(){
        this.lblCakes.setText(String.valueOf(Integer.parseInt(this.lblCakes.getText()) + 1));
    }

    public void handeMinusButton(){
        if(Integer.parseInt(this.lblCakes.getText()) > 0){
            this.lblCakes.setText(String.valueOf(Integer.parseInt(this.lblCakes.getText()) - 1));
        }
        else{
            uiUtils.showAlert(Alert.AlertType.WARNING,"NUMBER OF CAKES COVERED CANT BE NEGATIVE",false);
        }
    }

    public void handleNameEnter(){
        String newName = this.txtName.getText();
        if(uiUtils.nameChecks(newName)){
            this.lblName.setText(newName);
        }
        else{
            uiUtils.showAlert(Alert.AlertType.ERROR,"INVALID CHARACTERS IN NAME",false);
            this.txtName.setText("");
        }

    }

    public void handleCakesEnter(){
        int newCakes = 1;
        try{
            newCakes = Integer.parseInt(this.txtCakes.getText());
        }catch (Exception e){
            uiUtils.showAlert(Alert.AlertType.INFORMATION,"INSERT A VALID NUMBER",false);
        }
        if(newCakes >= 0){
            this.lblCakes.setText(String.valueOf(newCakes));
        }
        else {
            uiUtils.showAlert(Alert.AlertType.WARNING,"NUMBER OF CAKES COVERED CANT BE NEGATIVE",false);
            this.txtCakes.setText("");
        }

    }

    public void handleBackButton(ActionEvent event){
        this.uiUtils.showSearchScreen(this.stage);
    }

    public void handleConfirmChanges(){
        DBCommands dbCommands = new DBCommands();
        System.out.println(dbCommands.setNameDB(this.employee.getId(),this.lblName.getText()));
        System.out.println(dbCommands.setCakesCoveredDB(this.employee.getId(),Integer.parseInt(this.lblCakes.getText())));
        this.uiUtils.showWelcomeScreen(this.stage);
    }

    public void logApplicationClose(){
        this.uiUtils.logApplicationClose();
    }

    @FXML
    public void initialize(){
        this.lblName.setText(this.employee.getName());
        this.lblCakes.setText(String.valueOf(this.employee.getCakesCovered()));
        this.lblTitle.setText("Edit Employee: " + this.employee.getName());
    }
}
