package Assignment.Tests;

import Assignment.Class.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    Employee employee;

    @Test
    void Employee() throws Exception{
        assertThrows(Exception.class,()->{new Employee("Huss",-200);});
    }

    @Test
    void getWage() throws Exception {
        employee = new Employee("Pepe", 50);
        assertEquals(5.0, employee.getWage(),0.5);
        employee.addWrongCakes(25);
        assertEquals(0.0, employee.getWage(),0.5);
    }

    @Test
    void addWrongCakes() throws Exception {
        employee = new Employee("Hussain",200);
        employee.addWrongCakes(50);
        assertEquals(100,employee.getCakesCovered());
        employee.addWrongCakes(200);
        assertEquals(0,employee.getCakesCovered());
        assertThrows(Exception.class,()->{employee.addWrongCakes(-200);});
    }

    @Test
    void addCakesCovered() throws Exception {
        employee = new Employee("Hussain",200);
        assertThrows(Exception.class,()->{employee.addCakesCovered(-200);});
        employee.addCakesCovered(50);
        assertEquals(250,employee.getCakesCovered());
    }
}

