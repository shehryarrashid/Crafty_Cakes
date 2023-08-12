package Assignment.GUI.Controller;

import Assignment.Class.Employee;
import Assignment.Class.Team;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;



public class AllEmployeesScreenController {

    // ----- GLOBAL VARIABLES ----- //

    // --- FXML VARIABLES --- //
    @FXML private TableView tblTeam;
    @FXML private Button back;
    @FXML private Label lblSummary;

    // --- NORMAL VARIABLES --- //

    private Team team;
    private final Stage stage;
    private final UiUtils uiUtils;


    // ----- CONSTRUCTOR ----- //
    public AllEmployeesScreenController() {
        this.stage = new Stage();
        this.uiUtils = new UiUtils();
        try{
            this.team = new Team();

        }catch (Exception e){
            uiUtils.showAlert(Alert.AlertType.INFORMATION,"COULDN'T LOAD TEAM",true);
        }
    }

    // ----- DISPLAY SCREEN METHODS -----  //

    public void showEmployeeTable(Stage stage){

        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/AllEmployeesScreen.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));


        }catch (Exception e){
            uiUtils.showAlert(Alert.AlertType.ERROR,"ERROR LOADING PAGE",true);
        }

    }

    public void showEmployeeTable(){
        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/AllEmployeesScreen.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("All Employees");
            this.stage.show();


        }catch (Exception e){
            uiUtils.showAlert(Alert.AlertType.ERROR,"ERROR LOADING PAGE",true);
        }

    }

    // ----- Setting Logic Of Screen ----- //

    // --- Button Handling --- //

    public void handleBackButton(ActionEvent event){
        WelcomeScreenController welcome = new WelcomeScreenController();
        welcome.showWelcomeScene();
        this.stage.close();
    }

    // --- Table Defining ---//
    private void addColumns(){

        TableColumn<Employee,Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Employee, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Employee,Integer> colCakesCovered = new TableColumn<>("Cakes Covered");
        colCakesCovered.setCellValueFactory(new PropertyValueFactory<>("cakesCovered"));

        TableColumn<Employee,String> colWage = new TableColumn<>("Wage");
        colWage.setCellValueFactory(cellData ->{
            Employee employee = cellData.getValue();
            return new SimpleStringProperty(employee.getUtil().printCurrency(employee.getWage()));
        });

        TableColumn<Employee,String> colRole = new TableColumn<>("Role");
        colRole.setCellValueFactory(cellData ->{
            Employee employee = cellData.getValue();
            return new SimpleStringProperty(employee.getRole());
        });


        this.tblTeam.setItems(this.team.getTeamOAL());
        this.tblTeam.getColumns().addAll(colId,colName,colCakesCovered,colWage,colRole);
    }

    public void setLabels(){
        this.lblSummary.setText(this.team.summary());
    }


    // ----- Loads the Scene ----- //
    @FXML
    private void initialize(){
        addColumns();
        setLabels();
    }
}
