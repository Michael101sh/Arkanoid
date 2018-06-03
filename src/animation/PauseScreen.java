package animation;

import java.awt.Color;
import biuoop.DrawSurface;

/**
 * The class PauseScreen - an animation of pause screen - option to pause the
 * game when pressing the p key.
 * @author Michael Shachar and Hila Zecharia. */
public class PauseScreen implements Animation {
    private boolean stop;


    /**
     * Constructor PauseScreen. */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * The function doOneFrame - do one frame of the level -
     *  the frame of the pause screen.
     *  @param dt - .
     * @param d - the surface which we draw on. */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.GRAY);
        d.drawCircle(400, 300, 100);
        d.setColor(Color.WHITE);
        d.fillCircle(400, 300, 99);
        d.setColor(Color.decode("#6495ED"));
        d.fillCircle(400, 300, 89);
        d.setColor(Color.BLACK);
        d.fillCircle(400, 300, 86);
        d.setColor(Color.decode("#6495ED"));
        d.fillRectangle(352, 252, 38, 100);
        d.fillRectangle(412, 252, 38, 100);
        d.drawText(245, d.getHeight() * 13 / 16, "press space to continue", 32);
    }

    /**
     * The function setStop - set the stoppable condition to the given value.
     * @param newStop - the given stop. */
    public void setStop(boolean newStop) {
        this.stop = newStop;
    }
    /**
     * The function shouldStop - return boolean variable which
     * indicate when we should stop the animation. Here is to
     *  return back to the game.
     *  @return boolean variable.*/
    public boolean shouldStop() {
        return this.stop;
    }
}