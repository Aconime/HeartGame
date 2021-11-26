package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // Database details
    private String serverName = "jdbc:mysql://localhost/heartgame_db"; // DB Name: heartgame_db
    private String userName = "root";
    private String userPassword = ""; // No password

    // Default database constructor, must be included to separate from the second constructor
    public Database() { }

    // Optional testing constructor to connect to a different database (not used for the final application)
    public Database(String serverName, String userName, String userPassword) {
        this.serverName = serverName;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    // Connection string, set to null from object
    private Connection connectionString = null;

    // Connect to the database and return the connection string
    public Connection connect() {
        try {
            this.connectionString = DriverManager.getConnection(this.serverName, this.userName, this.userPassword);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return this.connectionString;
    }

    // Disconnect from the database if connected
    public void disconnect() {
        if (this.connectionString != null) {
            try {
                this.connectionString.close();
                this.connectionString = null;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Check if the connection to the database was successful
    public boolean checkConnection() {
        boolean connectionResults;

        if (this.connectionString != null) connectionResults = true;
        else connectionResults = false;

        return connectionResults;
    }
}
