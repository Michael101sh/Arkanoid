package indicators;

import geoshapes.Point;
import java.awt.Color;
import mainpackage.Counter;
import mainpackage.GameLevel;
import mainpackage.Sprite;
import biuoop.DrawSurface;

/**
* The class LivesIndicator - indicate the number of lives.
* @author Michael Shachar and Hila Zecharia. */
public class LivesIndicator implements Sprite {
    private Counter currentLives;


    /**
     * Constructor LivesIndicator.
     * @param currentLives - current lives. */
    public LivesIndicator(Counter currentLives) {
        this.currentLives = currentLives;
    }

    /**
     * The function drawOn - draw the ball on the given surface drawing.
     * @param surface  */
    public void drawOn(DrawSurface surface) {
        String lives = "Lives:" + String.valueOf(this.currentLives.getValue());
        Point leftPoint = new Point(50, 13);
        surface.setColor(Color.BLACK);
        int fontSize = 13;
        surface.drawText((int) leftPoint.getX(), (int) leftPoint.getY(), lives,
                fontSize);
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
