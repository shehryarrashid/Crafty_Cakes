package Assignment.Class;


import Assignment.Database.DBCommands;
import Assignment.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;


public class Team {
    Utils util;
    DBCommands dbCommands = new DBCommands();
    private ArrayList<Employee> team;

    // Constructor
    public Team(){

        util = new Utils();
        team = dbCommands.teamLoader();
        if(team == null){
            team.add(new Employee("null"));
        }
        teamSort();
    }

    public Team(Role role) {
        util = new Utils();
        team = dbCommands.loadRole(role);
        if(team == null){
            team.add(new Employee("null"));
        }
        teamSort();
    }


    // -----------  Methods ---------------

    // Allows
    public void addEmployee(Employee... worker){
        Collections.addAll(this.team, worker);
    }

    public void teamSort(){
        Collections.sort(this.team);
    }

    public String summary(){
        double[] values = calculateTotals();

        return util.line() + String.format("%50s\n", "SUMMARY") + util.line() +
                String.format("%-15s| %87s\n", "Payable Cakes", (int) values[1]) +
                String.format("%-15s| %87s\n", "Total Wage", util.printCurrency(values[0]));
    }

    public String summaryGui(){
        double[] values = calculateTotals();
        return "SUMMARY\n" +
                String.format("%-25s %80s\n", "Payable Cakes", (int) values[1]) +
                String.format("%-25s %80s\n", "Total Wage", util.printCurrency(values[0]));
    }

    public double[] calculateTotals(){
        double[] totals = {0,0};
        for (Employee employee : this.team) {
            totals[0] += employee.getWage();
            totals[1] += employee.getCakesCovered();
        }
        return totals;
    }

    // --------------- End Of User Created Methods --------------- //

    // Setters

    public void setTeam(ArrayList<Employee> team) {
        this.team = team;
    }

    // Getter

    public ArrayList<Employee> getTeam() {
        return team;
    }

    public ObservableList<Employee> getTeamOAL(){
        return FXCollections.observableArrayList(this.team);
    }

    // Override

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder(util.tableHead());
        for (Employee employ : this.team){
            table.append(employ.toString());
        }
        table.append(util.line());
        return table.toString();
    }
}
