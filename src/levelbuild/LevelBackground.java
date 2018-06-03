package levelbuild;

import java.awt.Color;
import java.awt.Image;
import mainpackage.GameLevel;
import mainpackage.Sprite;
import biuoop.DrawSurface;

/**
 * The interface LevelInformation
 * specifies the information required to fully describe a level.
 * @author Michael Shachar and Hila Zecharia. */
public class LevelBackground implements Sprite {
    private Image levelBackground;
    private Color color;

    /**
     * Constructor LevelBackground nu.1.
     * @param levelBackground - the image of the background. */
    public LevelBackground(Image levelBackground) {
        this.levelBackground = levelBackground;
        this.color = null;
    }

    /**
     * Constructor LevelBackground nu.2.
     * @param color - the color of the background. */
    public LevelBackground(Color color) {
        this.levelBackground = null;
        this.color = color;
    }

    /**
     * The function drawOn - draw the background on the given surface drawing.
     * @param d - the surface which we drawing on. */
    public void drawOn(DrawSurface d) {
        if (this.levelBackground != null) {
            d.drawImage(0, 0 , this.levelBackground);
        } else { // we need to draw a color for the background.
            d.setColor(this.color);
            d.fillRectangle(0, 0, 800, 600);
        }
    }

    /**
     * The function timePassed - notify the sprite that time has passed.
     * @param dt - . */
    public void timePassed(double dt) {
    }

    /**
     * The function addToGame - add a sprite to the GameLevel.
     @param g - the GameLevel. */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
