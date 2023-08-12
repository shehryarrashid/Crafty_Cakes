package Assignment.Database;

public class userData {
    private String username = "user";
    private String password = "yourPassword";
    private String url = "jdbc:mysql://127.0.0.1:3306/YourDatabaseName?allowMultiQueries=true";

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
