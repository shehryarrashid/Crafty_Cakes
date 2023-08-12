package Assignment.Database;

import Assignment.Class.Employee;
import Assignment.Class.QualityController;
import Assignment.Loggs.MyLogger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBUtils {
    MyLogger logger;


    private Employee employeeCreator(int id, String role, String name, int cakes) throws RuntimeException{
        try {
            switch (role) {
                case "REGULAR" -> {
                    return new Employee(id,name, cakes);
                }
                case "QUALITY_CONTROLLER" -> {
                    return new QualityController(id,name, cakes);
                }
            }
        }catch (Exception e){
            throw new RuntimeException("PROBLEM DURING THE EMPLOYEE DATA CREATION");
        }
        return null;
    }


    // --- DEALS WITH DATA RECEIVED FROM SELECT COMMAND --- //


    public ArrayList<Employee> insertEmployees(ResultSet rs){
        ArrayList<Employee> arr = new ArrayList<>();
        try{
            while (rs.next()){
                Employee emp = employeeCreator(rs.getInt("employee_id"),rs.getString("role"),rs.getString("name"),rs.getInt("cakes_covered"));
                if(emp != null){
                    arr.add(emp);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return arr;
    }

    public void closeConnections(Connection... connections){

        try {

            for(Connection connection : connections) {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            }

        } catch (SQLException e) {
            // Handle any exceptions
            System.out.println(e.getMessage());
        }
    }

    public MyLogger getLogger() {
        return logger;
    }
}
