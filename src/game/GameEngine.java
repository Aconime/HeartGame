package game;

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

    public int checkGameResults() {
        // -1 -> Other (game cancelled -> lost)
        //  0 -> Game Lost
        //  1 -> Game Won
        int gameRes = 0;

        return gameRes;
    }

    public URL getCurrentRound() {
        return url;
    }

    public int getRoundSolution() {
        return solution;
    }

}
