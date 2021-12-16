package player;

import lib.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerGetData {
    private Database db;
    private Connection dbConn;

    public PlayerGetData() {
        db = new Database();
    }

    private String[] getPlayerData(int id) {
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
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        db.disconnect(); // Disconnect from the database

        return playerDetails;
    }

    public String getPlayerUsername(int id) {
        return getPlayerData(id)[0];
    }

    public String getPlayerEmail(int id) {
        return getPlayerData(id)[1];
    }

    public int getPlayerXP(int id) {
        return Integer.parseInt(getPlayerData(id)[2]);
    }

    public int getPlayerWins(int id) {
        return Integer.parseInt(getPlayerData(id)[3]);
    }

    public int getPlayerLosses(int id) {
        return Integer.parseInt(getPlayerData(id)[4]);
    }

    public int getPlayerGP(int id) {
        return Integer.parseInt(getPlayerData(id)[5]);
    }

    public String getPlayerRank(int id) {
        int xpValue = getPlayerXP(id);
        String rankName = "";

        if (xpValue <= 1500) {
            rankName = "Beginner";
        } else if (xpValue > 1500 && xpValue <= 4000) {
            rankName = "Advanced";
        } else if (xpValue > 4000 && xpValue <= 10000) {
            rankName = "Professional";
        } else if (xpValue > 10000 && xpValue <= 15000) {
            rankName = "Legend";
        } else if (xpValue > 15000 && xpValue <= 50000) {
            rankName = "Genius";
        } else if (xpValue > 50000 && xpValue <= 100000) {
            rankName = "Undefeated";
        } else if (xpValue > 100000) {
            rankName = "Mastermind";
        }

        return rankName;
    }

    public int getPlayerXPMax(int id) {
        String rank = getPlayerRank(id);
        int maxPoints = 1500;
        switch (rank) {
            case "Beginner":
                maxPoints = 1500;
                break;
            case "Advanced":
                maxPoints = 4000;
                break;
            case "Professional":
                maxPoints = 10000;
                break;
            case "Legend":
                maxPoints = 15000;
                break;
            case "Genius":
                maxPoints = 50000;
                break;
            case "Undefeated":
                maxPoints = 100000;
                break;
            case "Mastermind":
                maxPoints = 999999;
                break;
        }
        return maxPoints;
    }

}
