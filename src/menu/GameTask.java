package menu;
import java.util.List;
import levelbuild.LevelInformation;
import mainpackage.GameFlow;

/**
 * The class GameTask - task of game.
 * @author Michael Shachar and Hila Zecharia. */
public class GameTask implements Task<Void> {
    private GameFlow gameFlow;
    private List<LevelInformation> levels;

    /**
     * Constructor GameTask.
     * @param gameFlow - object of the class which manage the game levels.
     * @param levels - the levels of the game. */
    public GameTask(GameFlow gameFlow, List<LevelInformation> levels) {
        this.gameFlow = gameFlow;
        this.levels = levels;
    }

    /**
     * The function run - Run a game task.
     * @return null. */
    public Void run() {
       this.gameFlow.runLevels(this.levels);
        return null;
    }
 }
