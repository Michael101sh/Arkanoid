package animation;

import java.awt.Color;
import mainpackage.SpriteCollection;
import biuoop.DrawSurface;

/**
 * The class Ball - display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 * @author Michael Shachar and Hila Zecharia. */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private long startTime;
    private long timeUntilReplacement;

    /**
     * Constructor CountdownAnimation.
     * @param numOfSeconds - the total time which the counting will be.
     * @param countFrom - the number which we count from.
     * @param gameScreen - the game screen which we draw.  */
    public CountdownAnimation(double numOfSeconds, int countFrom,
            SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.startTime = System.currentTimeMillis();
        // we multiply in 1000 because we want to see the counting in
        //  seconds, not in milliseconds.
        this.timeUntilReplacement = (long) ((this.numOfSeconds)
                / (this.countFrom) * 1000);
    }

    /**
     * The function doOneFrame - do one frame of the level
     * - here is the frames of the counting.
     * @param dt - the dt.
     * @param d - the surface which we draw on. */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        if (this.countFrom > 0) {
            // if the time doesn't pass yet
            if (System.currentTimeMillis() - this.startTime
                    <= this.timeUntilReplacement) {
                d.setColor(Color.YELLOW);
                String downCounter = String.valueOf(this.countFrom);
                d.drawText(390, (int) (d.getHeight() / (1.5)), downCounter, 80);
                d.setColor(Color.RED);
                d.drawText(385, (int) (d.getHeight() / (1.5)), downCounter, 80);
            } else {
                this.countFrom--;
                this.startTime = System.currentTimeMillis();
            }
        } else {
            this.stop = true;
        }
    }
    /**
     * The function setStop - set the stoppable condition to the given value.
     * @param newStop - the given stop. */
    public void setStop(boolean newStop) {

    }
    /**
     * The function shouldStop - return boolean variable which
     * indicate when we should stop the animation.
     *  @return boolean variable.*/
    public boolean shouldStop() {
        return this.stop;
    }
}