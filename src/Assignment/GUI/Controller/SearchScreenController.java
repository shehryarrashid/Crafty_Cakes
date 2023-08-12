package Assignment.GUI.Controller;

import Assignment.Class.Employee;
import Assignment.Class.Team;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SearchScreenController {
    // ----- FXML VARIABLES ----- //
    @FXML private TextField txtSearch;
    @FXML private TableView tblEmployees;

    // ----- GLOBAL VARIABLES ----- //
    private Team team;

    private Stage stage;

    private final UiUtils uiUtils;

    public SearchScreenController(){
        this.uiUtils = new UiUtils();
        try {
            this.team = new Team();
            this.stage = new Stage();

        }catch (Exception e){
            uiUtils.showAlert(Alert.AlertType.WARNING,"COULDN'T LOAD TEAM",true);
        }
    }
    

    public void showSearchScene(){
        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/SearchScreen.fxml"));
            loader.setController(this);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("CC Management System");
            this.stage.show();

        }catch (Exception e){
            uiUtils.showAlert(Alert.AlertType.ERROR,"ERROR LOADING PAGE",true);

        }
    }

    public void handleBackButton(ActionEvent event){
        WelcomeScreenController welcome = new WelcomeScreenController();
        welcome.showWelcomeScene();
        this.stage.close();
    }




    public void handleEditButton(){

        Employee employee = (Employee) this.tblEmployees.getSelectionModel().getSelectedItem();

        if(this.tblEmployees.getSelectionModel().isEmpty()){
            uiUtils.showAlert(Alert.AlertType.INFORMATION,"NO EMPLOYEE SELECTED, PLEASE SELECT AN EMPLOYEE TO CONTINUE",false);
        }
        else{
            EmployeeDataScreenController dataScreen = new EmployeeDataScreenController(employee);
            dataScreen.showEmployeeDataScene();
            this.stage.close();
        }

    }

    private ObservableList<Employee> getByName(String name){
        ArrayList<Employee> arrayList = new ArrayList<>();
        for(Employee employee: this.team.getTeam()){
            if(Objects.equals(employee.getName().toLowerCase(), name.toLowerCase())){
                arrayList.add(employee);
            }
        }
        if (arrayList.isEmpty()){
            uiUtils.showAlert(Alert.AlertType.INFORMATION,"COULDN'T FIND ANY EMPLOYEES",false);
        }
        return FXCollections.observableArrayList(arrayList);
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
    public void handleSearch(ActionEvent event){
        if (txtSearch.getText() == null || !nameChecks(txtSearch.getText())){
            uiUtils.showAlert(Alert.AlertType.INFORMATION,"INVALID CHARACTERS IN NAME",false);

        }else {
            String search = this.txtSearch.getText();
            this.tblEmployees.setItems(getByName(search));
        }

    }

    public void createColumns(){
        TableColumn<Employee,String> colName = new TableColumn<>("Name");
        TableColumn<Employee,Integer> colCakes = new TableColumn<>("Cakes Covered");
        TableColumn<Employee,String> colWage = new TableColumn<>("Wage");
        TableColumn<Employee,String> colRole = new TableColumn<>("Role");

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setMinWidth(200);
        colCakes.setCellValueFactory(new PropertyValueFactory<>("cakesCovered"));
        colCakes.setMinWidth(150);
        colWage.setCellValueFactory(cellData -> {
            Employee emp = cellData.getValue();
            return new SimpleStringProperty(emp.getUtil().printCurrency(emp.getWage()));
        });
        colWage.setMinWidth(150);
        colRole.setCellValueFactory(cellData -> {
            Employee emp = cellData.getValue();
            return new SimpleStringProperty(emp.getRole());
        });
        colRole.setMinWidth(200);

        this.tblEmployees.getColumns().addAll(colName,colCakes,colWage,colRole);

    }
    
    @FXML
    public void initialize() throws IOException {
        createColumns();
    }
}
