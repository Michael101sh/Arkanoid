package indicators;

import geoshapes.Point;
import java.awt.Color;
import mainpackage.Counter;
import mainpackage.GameLevel;
import mainpackage.Sprite;
import biuoop.DrawSurface;

/**
* The class ScoreIndicator - indicate the number of score.
* @author Michael Shachar and Hila Zecharia. */
public class ScoreIndicator implements Sprite {
    private Counter currentScore;

    /**
     * Constructor LivesIndicator.
     * @param currentScore - current score. */
    public ScoreIndicator(Counter currentScore) {
        this.currentScore = currentScore;
    }

    /**
     * The function drawOn - draw the ball on the given surface drawing.
     * @param surface  */
    public void drawOn(DrawSurface surface) {
        double width = 800;
        double height = 20;
        surface.setColor(Color.WHITE);
        surface.fillRectangle(0, 0, 800, 20);
        String score = "Score:" + String.valueOf(currentScore.getValue());
        Point middlePoint = new Point((width / 2) - 15, 0
                + (height / 2) + 3);
        surface.setColor(Color.BLACK);
        int fontSize = 13;
        surface.drawText((int) middlePoint.getX(),
                (int) middlePoint.getY(), score, fontSize);
        }

    /**
     * The function timePassed - do nothing, at least currently.
     * @param dt - . */
    public void timePassed(double dt) {

    }
    /**
     * The function addToGame - add this sprite to the game.
     @param g - the game. */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
