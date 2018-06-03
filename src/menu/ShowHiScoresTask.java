package menu;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;

/**
 * The class ShowHiScoresTask - the task of the high scores table.
 * @author Michael Shachar and Hila Zecharia. */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private HighScoresAnimation highScoresAnimation;
    private KeyboardSensor keyboard;

    /**
     * Constructor ShowHiScoresTask.
     * @param runner - the animation ruuner.
     * @param highScores - the high scores animation
     * @param keyboard - the keyboard. */
    public ShowHiScoresTask(AnimationRunner runner,
            HighScoresAnimation highScores, KeyboardSensor keyboard) {
       this.runner = runner;
       this.highScoresAnimation = highScores;
       this.keyboard = keyboard;
    }

    /**
     * The function run - Run a HiScoresTask task.
     * @return null. */
    public Void run() {
       this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
               KeyboardSensor.SPACE_KEY, this.highScoresAnimation));
       return null;
    }
 }
