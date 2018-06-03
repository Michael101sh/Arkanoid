package indicators;

import geoshapes.Point;
import java.awt.Color;
import mainpackage.GameLevel;
import mainpackage.Sprite;
import biuoop.DrawSurface;

/**
 * The class LevelIndicator - in charge of displaying the current level.
 * @author Michael Shachar and Hila Zecharia. */
public class LevelIndicator implements Sprite {
    private String currentLevel;

    /**
     * Constructor LevelIndicator.
     * @param currentLevel - the current level name. */
    public LevelIndicator(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * The function drawOn - draw the ball on the given surface drawing.
     * @param surface  */
    public void drawOn(DrawSurface surface) {
        double width = 800;
        double height = 20;
        String levelName = "Level Name: " + this.currentLevel;
        Point rightPoint = new Point((width) - 230, 0 + (height / 2) + 3);
        surface.setColor(Color.BLACK);
        int fontSize = 13;
        surface.drawText((int) rightPoint.getX(), (int) rightPoint.getY(),
                levelName, fontSize);
    }

    /**
     * The function timePassed - do nothing, at least currently.
     * @param dt - . */
    public void timePassed(double dt) {

    }

    /**
     * The function addToGame - adding this sprite to the game.
     * @param g - the game. */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
