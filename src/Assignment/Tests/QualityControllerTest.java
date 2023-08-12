package Assignment.Tests;

import Assignment.Class.QualityController;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QualityControllerTest {

    QualityController qualityController;
    @Test
    public void getWage() throws Exception {
        // Uses same constructor as Employee
        // Previously tested in EmployeeTest
        qualityController = new QualityController("qc",50);
        assertEquals(5.6,qualityController.getWage(),0.1);
    }
}