package Assignment.Database;

public class userData {
    private String username = "root";
    private String password = "All@hisgreat2002";
    private String url = "jdbc:mysql://127.0.0.1:3306/Crafty_Cakes?allowMultiQueries=true";

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
