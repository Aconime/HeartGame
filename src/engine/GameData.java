package engine;

import lib.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameData {
    private Database db;
    private Connection dbConn;

    public GameData() {
        db = new Database();
        dbConn = db.connect();
    }

    public String[] getPlayerDetails(int id) {
        String[] playerName = new String[8];

        if (dbConn == null) return playerName;

        try {
            String sqlQuery = "SELECT * FROM players WHERE player_id='"+id+"'";
            PreparedStatement pStatement = dbConn.prepareStatement(sqlQuery);
            ResultSet results = pStatement.executeQuery();

            // Check if an entry with that username was found in the database
            if (results.next()) {
                playerName[0] = results.getString("player_uname");
                playerName[1] = results.getString("player_email");
                playerName[2] = results.getString("player_xp");
                playerName[3] = results.getString("player_wins");
                playerName[4] = results.getString("player_losses");
                playerName[5] = results.getString("player_gp");
                playerName[6] = results.getString("player_rank");
                playerName[7] = results.getString("player_coins");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        db.disconnect(); // Disconnect from the database

        return playerName;
    }

    public void setGameWins() {}

    public void setGameLosses() {}

    public void setGamesPlayed() {}

    public void setPlayerRank() {}

    public void setPlayerXP() {}

    public void setPlayerCoins() {}

    public void getGameWins() {}

    public void getGameLosses() {}

    public void getGamesPlayed() {}

    public void getPlayerRank() {}

    public void getPlayerXP() {}

    public void getPlayerCoins() {}
}
