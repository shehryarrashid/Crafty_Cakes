package Assignment.Class;

import java.text.NumberFormat;

public class Utils{



    public String printCurrency(double value){
        NumberFormat formatter= NumberFormat.getCurrencyInstance();
        return formatter.format(value);
    }

    public double getEnhanced() {
        return 0.15;
    }

    public double getNormal() {
        return 0.10;
    } // Till 50 cakes



    public String tableFormat(){
        return "%-35s | %-20s | %-20s | %20s|\n";
    } // table format

    public String tableHead(){
        return this.line() +
                String.format(this.tableFormat(), "Name", "Suitable Cakes", "Employee", "Wage") +
                this.line();
    }

    public String cakesErrorMessage(){
        return "INVALID NUMBER OF CAKES";
    } // USED IN EXCEPTIONS

    public String line(){
        return "---------------------------------------------------------------------------------------------------------\n";
    }

}
