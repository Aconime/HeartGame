package game;

import player.PlayerGetData;
import player.PlayerSetData;

public class GameLogic {
    public final int gameMaxLives = 3; // Default 3 - 3 lives total

    private int gameLives = 0; // Start @ 0

    // Get only the amount of XP that is to be removed to the total amount
    // This method is used for displaying a custom message when the user losses the game
    private int playerXPLost(int playerId) {
        String playerRank = new PlayerGetData().getPlayerRank(playerId);
        int xpLost = 0;

        switch (playerRank) {
            case "Beginner":
                xpLost = 250;
                break;
            case "Advanced":
                xpLost = 400;
                break;
            case "Professional":
                xpLost = 650;
                break;
            case "Legend":
                xpLost = 800;
                break;
            case "Genius":
                xpLost = 3500;
                break;
            case "Undefeated":
                xpLost = 8500;
                break;
            case "Mastermind":
                xpLost = 15000;
                break;
        }
        return xpLost;
    }

    // Get only the amount of XP that is to be added to the total amount
    // This method is used for displaying a custom message when the user wins the game
    private int playerXPGained(int playerId) {
        String playerRank = new PlayerGetData().getPlayerRank(playerId);
        int xpGained = 0;

        switch (playerRank) {
            case "Beginner":
                xpGained = 550;
                break;
            case "Advanced":
                xpGained = 600;
                break;
            case "Professional":
                xpGained = 800;
                break;
            case "Legend":
                xpGained = 1500;
                break;
            case "Genius":
                xpGained = 4500;
                break;
            case "Undefeated":
                xpGained = 10000;
                break;
            case "Mastermind":
                xpGained = 50000;
                break;
        }
        return xpGained;
    }

    // Calculate how many Experience Points must be deducted from the overall amount
    // return the value to be updated to the database for the current user
    private int calculatePlayerXP(int playerId, int gameResult) {
        String playerRank = new PlayerGetData().getPlayerRank(playerId);
        int playerXP = new PlayerGetData().getPlayerXP(playerId);

        int totalXP = playerXP;

        if (gameResult == 0) {
            switch (playerRank) {
                case "Beginner":
                    totalXP -= 250;
                    break;
                case "Advanced":
                    totalXP -= 400;
                    break;
                case "Professional":
                    totalXP -= 650;
                    break;
                case "Legend":
                    totalXP -= 800;
                    break;
                case "Genius":
                    totalXP -= 3500;
                    break;
                case "Undefeated":
                    totalXP -= 8500;
                    break;
                case "Mastermind":
                    totalXP -= 15000;
                    break;
            }
        } else {
            switch (playerRank) {
                case "Beginner":
                    totalXP += 550;
                    break;
                case "Advanced":
                    totalXP += 600;
                    break;
                case "Professional":
                    totalXP += 800;
                    break;
                case "Legend":
                    totalXP += 1500;
                    break;
                case "Genius":
                    totalXP += 4500;
                    break;
                case "Undefeated":
                    totalXP += 10000;
                    break;
                case "Mastermind":
                    totalXP += 50000;
                    break;
            }
        }

        if (totalXP <= 0) totalXP = 0;

        return totalXP;
    }

    // Add +1 game player to the database for the current player
    private int incrementGamesPlayed(int playerId) {
        System.out.println(new PlayerGetData().getPlayerGP(playerId) + 1);
        return new PlayerGetData().getPlayerGP(playerId) + 1;
    }

    // Add +1 game won to the database for the current player
    private int incrementGamesWon(int playerId) {
        return new PlayerGetData().getPlayerWins(playerId) + 1;
    }

    // Add +1 game lost to the database for the current player
    private int incrementGamesLost(int playerId) {
        return new PlayerGetData().getPlayerLosses(playerId) + 1;
    }

    // Init lives, 3 lives is the max and the default.
    public int gameInitLives() {
        gameLives = gameMaxLives;
        return gameLives;
    }

    // Update Lives while playing
    public int gameUpdateLives() {
        gameLives -= 1;
        return gameLives;
    }

    // Game Won method, apply all changes required to the database and player info when called
    public int[] gameWon(int playerId) {
        int totalXPAmount = calculatePlayerXP(playerId, 1);
        int gamesPlayed = incrementGamesPlayed(playerId);
        int gamesWon = incrementGamesWon(playerId);

        PlayerSetData playerSetData = new PlayerSetData();
        playerSetData.setPlayerXP(playerId, totalXPAmount);
        playerSetData.setPlayerGP(playerId, gamesPlayed);
        playerSetData.setPlayerWins(playerId, gamesWon);

        playerSetData.closeDBConnection();

        int[] backInfo = {playerXPGained(playerId), totalXPAmount};

        return backInfo;
    }

    // Game Lost method, apply all changes required to the database and player info when called
    public int[] gameLost(int playerId) {
        int totalXPAmount = calculatePlayerXP(playerId, 0);
        int gamesPlayed = incrementGamesPlayed(playerId);
        int gamesLost = incrementGamesLost(playerId);

        PlayerSetData playerSetData = new PlayerSetData();
        playerSetData.setPlayerXP(playerId, totalXPAmount);
        playerSetData.setPlayerGP(playerId, gamesPlayed);
        playerSetData.setPlayerLosses(playerId, gamesLost);

        playerSetData.closeDBConnection();

        int[] backInfo = {playerXPLost(playerId), totalXPAmount};

        return backInfo;
    }

}
