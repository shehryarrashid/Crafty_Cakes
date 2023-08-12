package Assignment.Tests;

import Assignment.Class.Employee;
import Assignment.Class.QualityController;
import Assignment.Class.Team;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {

    Team team;
    @Test
    void addEmployee() throws Exception {
        Employee hammad = new Employee("hammad",120);
        Employee hussain = new Employee("hussain",130);
        team = new Team();

        // Testing by adding 2 employees at a time
        team.addEmployee(hammad,hussain);
        assertEquals(hammad,team.getTeam().get(0));
        assertEquals(hussain,team.getTeam().get(1));

        // Testing by adding one employee at a time
        QualityController ali = new QualityController("Ali",140);
        team.addEmployee(ali);
        assertEquals(ali,team.getTeam().get(2));
    }

    @Test
    void calculateTotals()throws Exception {
        Employee pepe = new Employee("Pepe",50);
        Employee miriam = new Employee("Miriam",50);
        team = new Team();
        team.addEmployee(pepe,miriam);
        assertArrayEquals(new double[] {10.0,100.0}, team.calculateTotals(), 0.5);
    }
}