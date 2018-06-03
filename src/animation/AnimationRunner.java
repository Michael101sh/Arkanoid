package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The class AnimationRunner - Running an animation.
 * @author Michael Shachar and Hila Zecharia. */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper;
    private int framesPerSecond;

    /**
    * Constructor AnimationRunner.
    * @param gui - the gui window which the game will show on.
    * @param framesPerSecond - .*/
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.sleeper = new Sleeper();
        this.framesPerSecond = framesPerSecond;

    }

    /**
     * The function run - run the animation.
     * @param animation - the animation which we run. */
    public void run(Animation animation) {
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            double dt = 1.0 / this.framesPerSecond;
            animation.doOneFrame(d, dt);

            this.gui.show(d);
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep =
                    1000 / this.framesPerSecond - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
