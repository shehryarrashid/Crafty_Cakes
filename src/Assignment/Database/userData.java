package Assignment.Database;

public class userData {
    private String username = "root"; //Change
    private String password = "yourPassword"; // Change
    private String url = "jdbc:mysql://127.0.0.1:3306/Crafty_Cakes?allowMultiQueries=true"; // Change Crafty_Cakes with your database name

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getUrl() {
        return url;
    }
}
