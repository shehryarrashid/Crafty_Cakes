package Assignment.GUI.Controller;

import Assignment.Loggs.MyLogger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WelcomeScreenController {

    // -----  Global Variables  ----- //

    private final Stage stage;
    private final UiUtils uiUtils;

    // ----- FXML VARIABLES ----- //
    @FXML
    private Button btnShowEmployees;


    // -----  Constructors  ----- //
    public WelcomeScreenController(){
        this.uiUtils = new UiUtils();
        this.stage = new Stage();
    }


    // -----  Methods  ----- //

    public void showWelcomeScene(Stage stage){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/WelcomeScreen.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));

        }catch (Exception e){
            uiUtils.showAlert(Alert.AlertType.ERROR,"ERROR LOADING PAGE",true);
        }

    }

    public void showWelcomeScene(){
        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/WelcomeScreen.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("CC Management System");
            this.stage.show();

        }catch (Exception e){
            uiUtils.showAlert(Alert.AlertType.ERROR,"ERROR LOADING PAGE",true);
            MyLogger.log("ERROR OPENING WELCOME SCREEN");
        }
    }



    // -----  Button Handlers  ----- //


    public void handleModifyData(){
        this.stage.close();
        SearchScreenController searchScreenController = new SearchScreenController();
        searchScreenController.showSearchScene();
    }

    public void handleShowEmployees(ActionEvent event){
        AllEmployeesScreenController allEmployeesScreenController = new AllEmployeesScreenController();
        this.stage.close();
        allEmployeesScreenController.showEmployeeTable();

    }

    public void handleAddNewEmployee(){
        this.uiUtils.showNewEmployeeScreen(this.stage);
    }

    public void handleQuit(ActionEvent event){
        this.stage.close();
        MyLogger.log("Application Closed");
    }

    public void logApplicationClose(){
        this.uiUtils.logApplicationClose();
    }


    // -----  START UP  ----- //

    @FXML
    private void initialize(){

    }
}
