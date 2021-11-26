package player;

import lib.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerData {
    private Database db;
    private Connection dbConn;

    public PlayerData() {
        db = new Database();
    }

    private String[] getPlayerDetails(int id) {
        dbConn = db.connect();

        String[] playerDetails = new String[8];

        if (dbConn == null) return playerDetails;

        try {
            String sqlQuery = "SELECT * FROM players WHERE player_id='"+id+"'";
            PreparedStatement pStatement = dbConn.prepareStatement(sqlQuery);
            ResultSet results = pStatement.executeQuery();

            // Get all results from database for userId and store into array 'playerDetails'
            if (results.next()) {
                playerDetails[0] = results.getString("player_uname");
                playerDetails[1] = results.getString("player_email");
                playerDetails[2] = results.getString("player_xp");
                playerDetails[3] = results.getString("player_wins");
                playerDetails[4] = results.getString("player_losses");
                playerDetails[5] = results.getString("player_gp");
                playerDetails[6] = results.getString("player_rank");
                playerDetails[7] = results.getString("player_coins");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        db.disconnect(); // Disconnect from the database

        return playerDetails;
    }

    public String getPlayerUsername(int id) {
        return getPlayerDetails(id)[0];
    }

    public String getPlayerEmail(int id) {
        return getPlayerDetails(id)[1];
    }

    public int getPlayerXP(int id) {
        return Integer.parseInt(getPlayerDetails(id)[2]);
    }

    public int getPlayerWins(int id) {
        return Integer.parseInt(getPlayerDetails(id)[3]);
    }

    public int getPlayerLosses(int id) {
        return Integer.parseInt(getPlayerDetails(id)[4]);
    }

    public int getPlayerGP(int id) {
        return Integer.parseInt(getPlayerDetails(id)[5]);
    }

    public int getPlayerRank(int id) {
        return Integer.parseInt(getPlayerDetails(id)[6]);
    }

    public String getPlayerRankName(int id) {
        int rankValue = getPlayerRank(id);
        String rankName = "";
        switch (rankValue) {
            case 0:
                rankName = "Beginner";
                break;
            case 1:
                rankName = "Advanced";
                break;
            case 2:
                rankName = "Professional";
                break;
            case 3:
                rankName = "Legend";
                break;
            case 4:
                rankName = "Genius";
                break;
            case 5:
                rankName = "Undefeated";
                break;
            case 6:
                rankName = "Mastermind";
                break;
        }
        return rankName;
    }

}
