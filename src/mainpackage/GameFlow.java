package mainpackage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import levelbuild.LevelInformation;
import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * The class GameFlow - the class which manage the game.
 * @author Michael Shachar and Hila Zecharia. */
public class GameFlow {
    private GUI gui;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private Counter remainingBlocks;
    private Counter score;
    private Counter lives;
    private boolean flag;
    private HighScoresTable hst;

    /**
     * Constructor GameFlow nu.1.
     * @param runner - the object which run animations.
     * @param keyboard - the keyboard.
     * @param gui - the gui of the game.
     * @param defaultLivesValue - the default lives of the game. */
    public GameFlow(AnimationRunner runner, KeyboardSensor keyboard,
            GUI gui, int defaultLivesValue) {
        this.runner = runner;
        this.keyboard = keyboard;
        this.gui = gui;;
        this.remainingBlocks = new Counter(0);
        this.lives = new Counter(defaultLivesValue);
        this.score = new Counter(0);
        this.flag = false;
    }

    /**
     * Constructor GameFlow nu.2.
     * @param runner - the object which run animations.
     * @param keyboard - the keyboard.
     * @param gui - the gui of the game.
     * @param hst - the HighScoresTable object.
     * @param defaultLivesValue - the default lives of the game. */
    public GameFlow(AnimationRunner runner, KeyboardSensor keyboard,
            GUI gui, int defaultLivesValue, HighScoresTable hst) {
        this.runner = runner;
        this.keyboard = keyboard;
        this.gui = gui;
        this.remainingBlocks = new Counter(0);
        this.score = new Counter(0);
        this.lives = new Counter(defaultLivesValue);
        this.flag = false;
        this.hst = hst;
    }

    /**
     * The function runLevels.
     * @param levels - the levels which will be run.*/
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.runner, this.gui,
                    this.keyboard, this.remainingBlocks, this.score,
                    this.lives);
            level.initialize();
            while (this.remainingBlocks.getValue() != 0
                    && this.lives.getValue() != 0) {
                level.playOneTurn();
            }
            if (this.remainingBlocks.getValue() == 0) {
                this.score.increase(100);
            }
            if (this.lives.getValue() == 0) {
                break;
            }
        }

        File highscores = new File("highscores");
        // check if the player should get to the high score table.
        if (hst.getHighScores().size() < hst.size()) {
            DialogManager dialog = this.gui.getDialogManager();
            String name = dialog.showQuestionDialog("Enter Name",
                    "What is your name?", "Anonymous");
            ScoreInfo s = new ScoreInfo(name, this.score.getValue());
            hst.add(s);
        } else if (this.score.getValue() > hst.getHighScores()
                .get(hst.getHighScores()
                        .size() - 1).getScore()) {
            DialogManager dialog = this.gui.getDialogManager();
            String name = dialog.showQuestionDialog("Enter Name",
                    "What is your name?", "Anonymous");
            ScoreInfo s = new ScoreInfo(name, this.score.getValue());
            hst.add(s);
        }
        // save the table to the file highscores.
        try {
            hst.save(highscores);
        } catch (IOException e) {
            System.err.println("Failed saving to file" + highscores);
        }
        if (this.lives.getValue() == 0) {
            this.runner
            .run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new EndScreen(this.score,
                            this.flag)));
        } else { // The user have some life
            this.flag = true;
            this.runner
            .run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new EndScreen(this.score,
                            this.flag)));
        }
        this.runner
        .run(new KeyPressStoppableAnimation(this.keyboard,
                KeyboardSensor.SPACE_KEY, new HighScoresAnimation(this.hst)));
    }

}
