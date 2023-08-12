package Assignment.Class;


import Assignment.Database.DBCommands;
import Assignment.Role;

public class Employee implements Comparable<Employee>{
    private String name;
    private int cakesCovered;
    private final Utils util = new Utils();
    private int id = -1;
    private final DBCommands dbUpdate = new DBCommands();

    // Constructors

    public Employee(String name){
        this.name = name;
        this.cakesCovered = 0;
        this.id = dbUpdate.getNextID();
    }

    public Employee(String name, int cakesCovered)throws Exception{
        this.name = name;
        if(cakesCovered < 0){
            throw new Exception(util.cakesErrorMessage());
        }
        this.cakesCovered = cakesCovered;
        this.id = dbUpdate.getNextID();
    }

    // ----- USE FOR DATABASE SELECTS ONLY ----- //
    public Employee(int id,String name,int cakesCovered) throws Exception {
        this.name = name;
        if(cakesCovered < 0 || id < 0){
            throw new Exception(util.cakesErrorMessage());
        }
        this.cakesCovered = cakesCovered;
        this.id = id;
    }

    // WAGE FUNCTION

    public double getWage(){
        if(this.cakesCovered > 50){
            return 50 * util.getNormal()  + ((this.cakesCovered - 50) * util.getEnhanced());
            // 50 cakes are paid normal rate and above 50 are paid the enhanced rate
        }
        return util.getNormal() * this.cakesCovered; // less than 50 are paid normal rate
    }

    //------ Cupcakes Methods-------

    public void addWrongCakes(int value) throws Exception {
        if(value > 0){
            this.cakesCovered = Math.max(0,(this.cakesCovered - (2 * value)));
            // Value of cupcakes cant be negative
            if(this.id != -1){
                this.dbUpdate.setCakesCoveredDB(this.id,this.cakesCovered);
            }
        }
        else{
            throw new Exception(util.cakesErrorMessage());
        }
    }

    public void addCakesCovered(int cakesCovered)throws Exception{
        if(cakesCovered < 0){
            throw new Exception(util.cakesErrorMessage());
        }
        this.cakesCovered += cakesCovered;
        if(this.id != -1){
            this.dbUpdate.setCakesCoveredDB(this.id,this.cakesCovered);
        }
    }

    // ------ End of Cupcakes Methods-----------

    // Getters

    public String getName() {
        return name;
    }

    public int getCakesCovered() {
        return cakesCovered;
    }

    public Utils getUtil() {
        return util;
    }

    public String getRole(){
        return Role.REGULAR.name();
    }

    public int getId() {
        return id;
    }

    public DBCommands getDbUpdate() {
        return dbUpdate;
    }

    // Setters

    public void setCakesCovered(int cakesCovered) throws Exception {
        if(cakesCovered > 0){
            this.cakesCovered = cakesCovered;
        }
        else throw new Exception(util.cakesErrorMessage());
    }

    public void setName(String name) {
        this.name = name;
        if(id != -1){
            this.dbUpdate.setNameDB(this.id,name);
        }
    }

    public void setId(int id) {
        if(id > 0){
            this.id = id;
        }
    }

// Override Functions

    @Override
    public String toString() {
        return String.format(util.tableFormat(), getName(), getCakesCovered(),this.getRole(),util.printCurrency(getWage()));
    }

    // Comparable implementation
    @Override
    public int compareTo(Employee emp) {
        int cakes =  emp.getCakesCovered() - this.cakesCovered;
        if(cakes == 0){
            return this.name.compareTo(emp.getName());

        }
        return cakes;
    }
}
