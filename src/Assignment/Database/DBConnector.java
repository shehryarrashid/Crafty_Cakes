package Assignment.Database;

import java.sql.*;

public class DBConnector {
    private Connection connection;
    private userData userData;

    public Connection createConnection() throws SQLException, ClassNotFoundException {
        // Check if connection is already established or closed
        if (connection == null || connection.isClosed()) {
            // Create a new connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            userData = new userData();
            connection = DriverManager.getConnection(userData.getUrl(), userData.getUsername(), userData.getPassword());
        }
        return connection;
    }

    public void closeConnection(Connection... conns) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }

        } catch (SQLException e) {
            // Handle any exceptions
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
