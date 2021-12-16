package game;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class GameEngine {
    private URL url = null;
    private int solution = -1;

    public void generateGame() throws MalformedURLException {
        int randomGame =  new Random().nextInt(1000);
        url = new URL("https://sanfoh.com/heartgame/sixeqgame_" + randomGame + ".png");
        solution  = randomGame % 10;
    }

    public boolean checkGameResults(int givenAnswer) {
        boolean gameWon = false;

        if (givenAnswer == solution) gameWon = true;
        else gameWon = false;

        return gameWon;
    }

    public URL getCurrentGame() {
        return url;
    }

}
