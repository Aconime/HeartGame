package lib;

import java.sql.*;
import lib.bcrypt.BCrypt; // External library from <GitHub Account Here>

public class Authentication {

    public boolean authenticateUsername(String uname) {
        boolean unameExists = false;

        // Create new database object and connect to it
        Database db = new Database();
        Connection dbConn = db.connect();

        // If the connection failed return false
        if (dbConn == null) {
            unameExists = false;
        }

        try {
            // Check if there are other rows in the table on the database with the username provided
            String sqlQuery = "SELECT * FROM players WHERE player_uname='"+uname+"'";
            PreparedStatement statement = dbConn.prepareStatement(sqlQuery);
            ResultSet results = statement.executeQuery();

            // If username exists in database the return true
            if (results.next()) {
                unameExists = true;
            }
        } catch (SQLException e) { }

        db.disconnect(); // Disconnect from the database

        return unameExists; // Return results
    }

    // Authenticate login credentials from player (Used: LoginGUI)
    public int[] authenticateLogin(String uname, String pwd) {
        int[] verified = {0, 0};

        // Create new database object and connect to it
        Database db = new Database();
        Connection dbConn = db.connect();

        // If the connection failed return and display error
        if (dbConn == null) {
            verified = new int[]{-1, 0};
            return verified;
        }

        // Search for the given username with an SQL Query and prepare the statement for execution.
        // PreparedStatement is better than Statement -> Protects against SQL Injections
        try {
            String sqlQuery = "SELECT * FROM players WHERE player_uname='"+uname+"'";
            PreparedStatement statement = dbConn.prepareStatement(sqlQuery);
            ResultSet results = statement.executeQuery();

            // Check if an entry with that username was found in the database
            if (results.next()) {
                // Check if the password of the user found is equal to the password entered.
                // Passwords are hashed so BCrypt is used to compare in this case.
                boolean matchPwd = BCrypt.checkpw(pwd, results.getString("player_pwd"));
                if (matchPwd) {
                    // Pass the status and the player_id to this object array
                    verified = new int[]{1, Integer.parseInt(results.getString("player_id"))};
                }
            }
        } catch (SQLException e) { }

        db.disconnect(); // Disconnect from the database

        // Return the object array back to verify the status of the authentication
        return verified;
    }

    public int authenticateRegistration(String uname, String email, String pwd) {
        // -1 : Fatal Error - Unknown (Failed Connection, No WiFi, Offline, MySQL Down, Wrong DNS, Wrong URL, etc)
        // -2 : Username Exists
        // -3 : Email Exists
        //  0 : Registration Failed (Insert failed to be executed to the MySQL Database - For any reason)
        //  1 : Registration Successful
        int verified = -1;

        // Create new database object and connect to it
        Database db = new Database();
        Connection dbConn = db.connect();

        // If the connection failed return '-1'
        if (dbConn == null) return verified;

        // Check if the given username and/or email address already exists in the database
        try {
            // SQL Query & Statement Execution for checking if the 'username' exists
            String checkUnameSqlQuery = "SELECT * FROM players WHERE player_uname='"+uname+"'";
            PreparedStatement checkUnameStatement = dbConn.prepareStatement(checkUnameSqlQuery);
            ResultSet checkUnameResults = checkUnameStatement.executeQuery();

            if (checkUnameResults.next()) verified = -2; // Username exists
            else {
                // SQL Query & Statement Execution for checking if the 'email' exists
                String checkEmailSqlQuery = "SELECT * FROM players WHERE player_email='"+email+"'";
                PreparedStatement checkEmailStatement = dbConn.prepareStatement(checkEmailSqlQuery);
                ResultSet checkEmailResults = checkEmailStatement.executeQuery();

                if (checkEmailResults.next()) verified = -3; // Email exists
                else {
                    // Hash the password given using BCrypt
                    String hashedPassword = BCrypt.hashpw(pwd, BCrypt.gensalt(10));

                    // Insert the data provided by the user into the database
                    String sqlRegisterQuery = "INSERT INTO players (player_uname, player_pwd, player_email, player_xp, player_wins, player_losses, player_gp)"+
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement registerStatement = dbConn.prepareStatement(sqlRegisterQuery);
                    // Pass all the required parameters from the SQL Query
                    registerStatement.setString(1, uname);
                    registerStatement.setString(2, hashedPassword);
                    registerStatement.setString(3, email);
                    registerStatement.setInt(4, 0);
                    registerStatement.setInt(5, 0);
                    registerStatement.setInt(6, 0);
                    registerStatement.setInt(7, 0);
                    int registerResults = registerStatement.executeUpdate(); // Execute the statement

                    // If execution was successful assign value '1' if not assign value '0' to the 'verified' variable.
                    if (registerResults > 0) verified = 1;
                    else verified = 0;
                }
            }
        } catch (SQLException e) { }

        db.disconnect(); // Disconnect from the database

        // Return the object array back to verify the status of the authentication
        return verified;
    }
}
