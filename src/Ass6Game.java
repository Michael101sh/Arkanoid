import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import levelbuild.LevelInformation;
import levelbuild.LevelSpecificationReader;
import mainpackage.HighScoresTable;
import menu.GameMenu;
import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * The class Ass6Game - create the game (include level sets of the game),
 * high scores table, menu (see documentation of menu in the class GameMenu).
 * @author Michael Shachar and Hila Zecharia. */
public class Ass6Game {
    /**
     * The main method. Does what we write above.
     * @param args - array of strings - the player can choose
     *  file tof level sets (optional). */
    public static void main(String[] args) {
        Ass6Game assSixGame = new Ass6Game();
        final int screenWidth = 800, screenHeight = 600;
        GUI gui = new GUI("Arkanoid", screenWidth, screenHeight);
        AnimationRunner runner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        int defaultLivesValue = 7;
        HighScoresTable hiScoTable = new HighScoresTable(5);
        File highscores = new File("highscores");
        if (highscores.isFile()) {
            try {
                hiScoTable.load(highscores);
            } catch (IOException e) {
                System.err.println("Failed reading file: " + highscores);
            }
        }
        String levelSetsFile = null;
        if (args.length != 0) {
            if (args[0].equals("level_sets.txt")) {
                levelSetsFile = args[0];
            }
        } else {
            levelSetsFile = "level_sets.txt";
        }
        LineNumberReader lineNumberReader =
                assSixGame.createLNReader(levelSetsFile);
        Map<String, String> levelSetsNames = new TreeMap<String, String>();
        List<String> levelPathes = new ArrayList<String>();
        try {
            String line = lineNumberReader.readLine();
            while (line != null) {
                if (lineNumberReader.getLineNumber() % 2 == 1) {
                    String[] setNames = line.split(":");
                    levelSetsNames.put(setNames[0], setNames[1]);
                    line = lineNumberReader.readLine();
                } else {
                    levelPathes.add(line);
                    line = lineNumberReader.readLine();
                }

            }
        } catch (IOException e) {
            System.err.println("Failed reading file: "
                    + levelSetsFile);
           // e.printStackTrace(System.err);
        } finally {
            try {
                if (lineNumberReader != null) {
                    lineNumberReader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: "
                       + levelSetsFile);
            }
        }
        List<List<LevelInformation>> levelSets =
                new ArrayList<List<LevelInformation>>();
        for (int i = 0; i < levelPathes.size(); i++) {
            LineNumberReader inputStreamReader =
                    assSixGame.createLNReader(levelPathes.get(i));
            LevelSpecificationReader levelSpecificationReader =
                    new LevelSpecificationReader();
            try {
            levelSets.add(levelSpecificationReader
                        .fromReader(inputStreamReader));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: "
                                        + levelPathes.get(i));
            }
        }

        GameMenu gameMenu = new GameMenu(runner, keyboard,
                gui, defaultLivesValue, hiScoTable, levelSetsNames, levelSets);
        try {
            gameMenu.buildMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * The function LineNumberReader.
     * @param path - the path of the file.
     * @return LineNumberReader object. */
    private LineNumberReader createLNReader(String path) {
        LineNumberReader lineNumberReader = null;
        InputStream is = null;
        try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
                lineNumberReader = new LineNumberReader(
                        new InputStreamReader(is));
        } catch (Exception e) {
            System.err.println("Failed reading fron file: " +  path);
        }
        return lineNumberReader;
    }

}