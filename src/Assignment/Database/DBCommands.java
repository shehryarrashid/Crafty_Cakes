package Assignment.Database;

import Assignment.Class.Employee;
import Assignment.Class.Team;
import Assignment.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBCommands {
    private final DBConnector dbConnector;
    private final DBUtils dbUtils;


    public DBCommands(){
        dbConnector = new DBConnector();
        dbUtils = new DBUtils();
    }


    // ----- DATABASE INSERT COMMANDS ----- //

    public String insertEmployee(Employee... employees) {
        String result = "EXECUTED SUCCESSFULLY";

        try {

            Connection connection = this.dbConnector.createConnection();

            String insertEmployeeSQL = "INSERT INTO `Employee`(`name`, `cakes_covered`, `role`) VALUES (?,?,?)";
            PreparedStatement insertEmployeePS = connection.prepareStatement(insertEmployeeSQL);

            for(Employee emp: employees) {
                //update values for prepared statement
                insertEmployeePS.setString(1, emp.getName());
                insertEmployeePS.setInt(2, emp.getCakesCovered());
                insertEmployeePS.setString(3, emp.getRole());
                insertEmployeePS.executeUpdate();
            }

            insertEmployeePS.close();
            dbUtils.closeConnections(this.dbConnector.getConnection(),connection);
        } catch (Exception e) {
            result = "FAILED";

        }
        return result;
    }


    public String insertTeam(Team team) {
        String result = "EXECUTED SUCCESSFULLY";

        try {

            Connection connection = this.dbConnector.createConnection();

            String insertEmployeeSQL = "INSERT INTO `Employee`(`name`, `cakes_covered`, `role`) VALUES (?,?,?)";
            PreparedStatement insertEmployeePS = connection.prepareStatement(insertEmployeeSQL);

            for(Employee emp : team.getTeam()) {
                //update values for prepared statement
                insertEmployeePS.setString(1, emp.getName());
                insertEmployeePS.setInt(2, emp.getCakesCovered());
                insertEmployeePS.setString(3, emp.getRole());
                insertEmployeePS.executeUpdate();
            }

            insertEmployeePS.close();
            dbUtils.closeConnections(this.dbConnector.getConnection(),connection);
        } catch (Exception e) {
            result = "FAILED";
            System.out.println(e.getMessage());

        }
        return result;
    }


    // ----- DATABASE DELETE COMMANDS ----- //
    public String deleteEmployee(Employee employee) {
        String result = "EXECUTED SUCCESSFULLY";

        try {

            Connection connection = this.dbConnector.createConnection();

            String deleteEmployeeSQL = "DELETE FROM `Employee` WHERE `name` = ?";
            PreparedStatement deleteEmployeePS = connection.prepareStatement(deleteEmployeeSQL);

            // Set the parameter value for the employee name
            deleteEmployeePS.setString(1, employee.getName());
            deleteEmployeePS.executeUpdate();

            deleteEmployeePS.close();
            this.dbConnector.closeConnection();
        } catch (Exception e) {
            result = "FAILED";
        }
        return result;
    }
    public String deleteEmployees(ArrayList<Employee> employees) {
        String result = "EXECUTED SUCCESSFULLY";

        try {
            Connection connection = this.dbConnector.createConnection();

            String deleteEmployeeSQL = "DELETE FROM `Employee` WHERE `employee_id` = ?";
            PreparedStatement deleteEmployeePS = connection.prepareStatement(deleteEmployeeSQL);

            for (Employee emp : employees) {
                // Set the parameter value for the employee name
                if (emp.getId() != -1){
                    deleteEmployeePS.setInt(1, emp.getId());
                    deleteEmployeePS.executeUpdate();
                }
            }

            deleteEmployeePS.close();
            this.dbConnector.closeConnection();
        } catch (Exception e) {
            result = "FAILED";
        }
        return result;
    }



    // ----- DATABASE UPDATE COMMANDS ----- //


    public String setNameDB(int employeeId,String newName){
        String result = "EXECUTED SUCCESSFULLY";

        try {

            Connection connection = this.dbConnector.createConnection();

            String setNameSQL = "UPDATE `Employee` " + "SET `name` = ? " + "WHERE `employee_id` = ?;";
            PreparedStatement setNamePS = connection.prepareStatement(setNameSQL);

            setNamePS.setString(1,newName);
            setNamePS.setInt(2,employeeId);

            setNamePS.executeUpdate();

            setNamePS.close();
            this.dbConnector.closeConnection();
        } catch (Exception e) {
            result = "FAILED";
        }
        return result;
    }

    public String setCakesCoveredDB(int employeeId,int newCakesCovered){
        String result = "EXECUTED SUCCESSFULLY";

        try {

            Connection connection = this.dbConnector.createConnection();

            String setNameSQL = "UPDATE `Employee` " + "SET `cakes_covered` = ? " + "WHERE `employee_id` = ?;";
            PreparedStatement setNamePS = connection.prepareStatement(setNameSQL);

            setNamePS.setInt(1,newCakesCovered);
            setNamePS.setInt(2,employeeId);
            setNamePS.executeUpdate();
            setNamePS.close();
            this.dbConnector.closeConnection();
        } catch (Exception e) {
            result = "FAILED";
        }
        return result;
    }

    public String changeRoleDB(int employeeId, Role newRole){
        String result = "EXECUTED SUCCESSFULLY";

        try {

            Connection connection = this.dbConnector.createConnection();

            String setNameSQL = "UPDATE `Employee` " + "SET `role` = ? " + "WHERE `employee_id` = ?;";
            PreparedStatement setNamePS = connection.prepareStatement(setNameSQL);

            setNamePS.setString(1, newRole.name());
            setNamePS.setInt(2,employeeId);

            setNamePS.close();
            this.dbConnector.closeConnection();
        } catch (Exception e) {
            result = "FAILED";
        }
        return result;
    }

    // ----- DATABASE SELECT COMMANDS ----- //


        // --- FUNCTION USED IN RETRIEVING EMPLOYEE TYPE --- //

    public ArrayList<Employee> teamLoader(){

        ArrayList<Employee> teamArray;

        try{
            Connection connection = this.dbConnector.createConnection();

            String loadSQL = "SELECT * FROM Employee;";
            PreparedStatement loadSQLPS = connection.prepareStatement(loadSQL);

            ResultSet rs = loadSQLPS.executeQuery();
            teamArray = dbUtils.insertEmployees(rs);

            loadSQLPS.close();
            dbUtils.closeConnections(this.dbConnector.getConnection(),connection);

        }catch (Exception e){
            return null;
        }

        return teamArray;
    }
    // ----- LOADS ALL EMPLOYEES ----- //



    // ----- LOADS EMPLOYEES BASED ON THEIR ROLE ----- //

    public ArrayList<Employee> loadRole(Role role){

        ArrayList<Employee> teamArray;
        try {
            Connection connection = this.dbConnector.getConnection();

            String loadSQL = "SELECT * FROM `Employee` WHERE `role` = \"" + role.name() + "\";";
            PreparedStatement loadSQLPS = connection.prepareStatement(loadSQL);

            ResultSet rs = loadSQLPS.executeQuery();
            teamArray = dbUtils.insertEmployees(rs);

            loadSQLPS.close();
            dbUtils.closeConnections(this.dbConnector.getConnection(),connection);

        } catch (Exception e){
            return null;
        }
        return teamArray;
    }


    // ----- GETS ID TO BE ASSIGNED NEW EMPLOYEE ----- //


    public int getNextID(){
        int id = -1;
        try {

            Connection connection = this.dbConnector.createConnection();

            String loadSQL = "SELECT AUTO_INCREMENT " +
                    "FROM information_schema.TABLES " +
                    "WHERE TABLE_SCHEMA = 'Crafty_Cakes' " +
                    "AND TABLE_NAME = 'Employee';";
            PreparedStatement loadSQLPS = connection.prepareStatement(loadSQL);
            ResultSet rs = loadSQLPS.executeQuery();

            id = rs.getInt("AUTO_INCREMENT");
            loadSQLPS.close();
            this.dbConnector.closeConnection();
        } catch (Exception ignored){}
        return id;
    }

}
