package menu;

import java.util.List;
import java.util.Map;
import levelbuild.LevelInformation;
import mainpackage.GameFlow;
import mainpackage.HighScoresTable;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * The class GameMenu - class which manage the menu of the game.
 * @author Michael Shachar and Hila Zecharia. */
public class GameMenu {
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private GUI gui;
    private int defaultLivesValue;
    private HighScoresTable hiScoTable;
    private Map<String, String> levelSetNames;
    private List<List<LevelInformation>> levelSets;

    /**
     * Constructor GameMenu.
     * @param runner - .
     * @param keyboard - .
     * @param gui - .
     * @param defaultLivesValue - .
     * @param hiScoTable - .
     * @param levelSetNames - .
     * @param levelSets - .*/
    public GameMenu(AnimationRunner runner, KeyboardSensor keyboard,
             GUI gui, int defaultLivesValue, HighScoresTable hiScoTable,
             Map<String, String> levelSetNames
             , List<List<LevelInformation>> levelSets) {
        this.runner = runner;
        this.keyboard = keyboard;
        this.gui = gui;
        this.defaultLivesValue = defaultLivesValue;
        this.hiScoTable = hiScoTable;
        this.levelSetNames = levelSetNames;
        this.levelSets = levelSets;
    }

    /**
     * The function buildMenu - build the menu.
     * @throws Exception - . */
    public void buildMenu() throws Exception {
        GameFlow gameFlow = new GameFlow(this.runner, this.keyboard, this.gui,
                this.defaultLivesValue, this.hiScoTable);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid",
               this.runner, this.keyboard);
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>("Level Sets",
                this.runner, this.keyboard);
        menu.addSubMenu("s", "Play Game", subMenu);
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(
                this.runner, new HighScoresAnimation(this.hiScoTable),
                this.keyboard));
        menu.addSelection("e", "Exit", new ExitTask());
        int i = 0;
        for (Map.Entry<String, String> entry : this.levelSetNames.entrySet()) {
            String key = entry.getKey();
            String message = entry.getValue();
            subMenu.addSelection(key, message,
                    new GameTask(gameFlow, this.levelSets.get(i)));
            i++;
          }
        while (true) { // run the menu
           runner.run(menu);
           // wait for user selection
           Task<Void> task = menu.getStatus();
           if (task != null) {
               task.run();
           }
           // after we change the boolean stop variable to true,
           // we reset it to false for the next animations.
           menu.reset();
        }
    }
}

