package engine;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class GameEngine {
    private URL url = null;
    private int solution = -1;

    private void generateRound() throws MalformedURLException {
        int randomGame =  new Random().nextInt(1000);
        url = new URL("https://sanfoh.com/heartgame/sixeqgame_" + randomGame + ".png");
        solution  = randomGame % 10;
    }

    public int roundResults() {
        // -1 -> Exception (game cancelled -> lost)
        //  0 -> Game Lost
        //  1 -> Game Won
        int gameWin = 0;

        return gameWin;
    }

    public void gameRoundWon() {}

    public void gameRoundLost() {}

    public void gameRoundCancelled() {}

    public void newRound() {}

    private void checkPlayerCoins() {}

    private void checkPlayerRank() {}

    private void checkPlayerXP() {}

    public URL getCurrentRound() {
        return url;
    }

    public int getRoundSolution() {
        return solution;
    }

}
