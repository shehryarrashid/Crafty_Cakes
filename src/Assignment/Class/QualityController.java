package Assignment.Class;

import Assignment.Class.Employee;
import Assignment.Role;

public class QualityController extends Employee {
    // Constructors

    public QualityController(String name, int cakesCovered) throws Exception {
        super(name, cakesCovered);
    }
    public QualityController(String name){
        super(name);
    }

    public QualityController(int id, String name, int cakesCovered) throws Exception {
        super(id, name, cakesCovered);
    }

    // Override Functions

    @Override
    public String getRole() {
        return Role.QUALITY_CONTROLLER.name();
    }

    @Override
    public double getWage() {
        double wage = super.getWage() * 1.12;
        return Math.round(wage * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return String.format(super.getUtil().tableFormat(), getName(), getCakesCovered(),this.getRole(),super.getUtil().printCurrency(this.getWage()));
    }
}
