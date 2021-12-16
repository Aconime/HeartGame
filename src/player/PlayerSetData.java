package player;

import lib.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerSetData {
    private Database db;
    private Connection dbConn;

    // Init connection - Different style - Fewer requirements
    public PlayerSetData() {
        db = new Database();
        dbConn = db.connect();
    }

    // Set the players 'Games Player' amount - Incremented by 1 from the GameLogic
    public void setPlayerGP(int id, int gp) {
        try {
            String sqlQuery = "UPDATE players SET player_gp = ? WHERE player_id='"+id+"'";
            PreparedStatement pStatement = dbConn.prepareStatement(sqlQuery);
            pStatement.setInt(1, gp);

            pStatement.executeUpdate();
        } catch (SQLException e) {}
    }

    // Set the players amount of 'Games Won' - Incremented by 1 from the GameLogic
    public void setPlayerWins(int id, int wins) {
        try {
            String sqlQuery = "UPDATE players SET player_wins = ? WHERE player_id='"+id+"'";
            PreparedStatement pStatement = dbConn.prepareStatement(sqlQuery);
            pStatement.setInt(1, wins);

            pStatement.executeUpdate();
        } catch (SQLException e) {}
    }

    // Set the players amount of 'Games Lost' - Incremented by 1 from the GameLogic
    public void setPlayerLosses(int id, int losses) {
        try {
            String sqlQuery = "UPDATE players SET player_losses = ? WHERE player_id='"+id+"'";
            PreparedStatement pStatement = dbConn.prepareStatement(sqlQuery);
            pStatement.setInt(1, losses);

            pStatement.executeUpdate();
        } catch (SQLException e) {}
    }

    // Set the players amount of 'XP' - Calculated by the GameLogic
    public void setPlayerXP(int id, int xp) {
        try {
            String sqlQuery = "UPDATE players SET player_xp = ? WHERE player_id='"+id+"'";
            PreparedStatement pStatement = dbConn.prepareStatement(sqlQuery);
            pStatement.setInt(1, xp);

            pStatement.executeUpdate();
        } catch (SQLException e) {}
    }

    public void closeDBConnection() {
        db.disconnect(); // Disconnect from the database
    }
}
