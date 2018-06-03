package animation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import mainpackage.HighScoresTable;
import mainpackage.ScoreInfo;
import biuoop.DrawSurface;

/**
 * The class HighScoresAnimation - Animation of the high scores table.
 * @author Michael Shachar and Hila Zecharia. */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;
    private boolean stop;

    /**
     * Constructor HighScoresAnimation.
     * @param scores - the high scores table. */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
        this.stop = false;
    }

    /**
     * The function doOneFrame - do one frame of the level.
     * @param dt - .
     * @param d - the surface which we draw on. */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.decode("#bdbdbd"));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.decode("#ffff00"));
        int startXLocation = 50;
        int startYLocation = 50;
        d.drawText(startXLocation, startYLocation,
                "High Scores", 30);
        d.setColor(Color.decode("#458B00"));
        startXLocation = 120;
        startYLocation = 110;
        d.drawText(startXLocation, startYLocation,
                        "Player Name", 25);
        d.drawText(startXLocation + 3 * startXLocation, startYLocation,
                "Score", 25);
        d.drawLine(startXLocation, startYLocation + 5, 700, startYLocation + 5);
        d.setColor(Color.decode("#00868B"));
        startYLocation = 150;
        List<ScoreInfo> highScoresTable = new ArrayList<ScoreInfo>();
        highScoresTable = this.scores.getHighScores();
        for (int i = 0; i < highScoresTable.size(); i++) {
        ScoreInfo scoreInfo = highScoresTable.get(i);
        d.drawText(startXLocation, startYLocation + 35 * i,
                scoreInfo.getName(), 25);
        d.drawText(startXLocation + 3 * startXLocation ,
                startYLocation + 35 * i,
                String.valueOf(scoreInfo.getScore()), 25);
        }
        d.setColor(Color.decode("#ff3232"));
        d.drawText(245, 489, "Press space to continue", 32);
        d.setColor(Color.BLACK);
        d.drawText(248, 490, "Press space to continue", 32);
    }

    /**
     * The function setStop - set the stoppable condition to the given value.
     * @param newStop - the given stop. */
    public void setStop(boolean newStop) {
        this.stop = newStop;
    }

    /**
     * The function shouldStop - return boolean variable which
     * indicate when we should stop the animation.
     *  @return boolean variable.*/
    public boolean shouldStop() {
        return this.stop;
    }

 }
