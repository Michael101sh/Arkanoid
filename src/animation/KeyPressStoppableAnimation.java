package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The class KeyPressStoppableAnimation - Animation which manage
 * the stoping of the animation.
 * @author Michael Shachar and Hila Zecharia. */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;

    /**
     * Constructor KeyPressStoppableAnimation.
     * @param keyboard - the keyboard.
     * @param key - the key which will exit the animation.
     * @param animation - the animation which we manage it stoping. */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String key,
            Animation animation) {
        this.keyboard = keyboard;
        this.key = key;
        this.animation = animation;
        this.animation.setStop(false);
    }

    /**
     * The function doOneFrame - do one frame of the level.
     * @param dt - .
     * @param d - the surface which we draw on. */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.keyboard.isPressed(this.key)) {
            this.animation.setStop(true);
        }
    }


    /**
     * The function setStop - set the stoppable condition to the given value.
     * @param newStop - the given stop. */
    public void setStop(boolean newStop) {
        this.animation.setStop(newStop);
    }
    /**
     * The function shouldStop - return boolean variable which indicate
     *  when we should stop the animation.
     * @return boolean variable. */
    public boolean shouldStop() {
        return this.animation.shouldStop();
    }
}
