package animation;

import biuoop.DrawSurface;

/**
 * The interface Animation -  will be used for animation classes, means
 * the classes: CountdownAnimation, PauseScreen, GameLevel and EndScreen,
 * and all the classes which will be in the relation 'is a'(animation).
 * @author Michael Shachar and Hila Zecharia. */
public interface Animation {

    /**
     * The function doOneFrame - do one frame of the animation.
     * @param dt - the dt.
     * @param d - the surface which we draw on. */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * The function setStop - set the stoppable condition to the given value.
     * @param newStop - the given stop. */
    void setStop(boolean newStop);

    /**
     * The function shouldStop - return boolean variable which
     * indicate when we should stop the animation.
     *  @return boolean variable.*/
    boolean shouldStop();
}