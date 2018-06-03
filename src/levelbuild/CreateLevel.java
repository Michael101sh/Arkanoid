package levelbuild;

import java.awt.Color;
import java.awt.Image;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mainpackage.Block;
import mainpackage.Sprite;
import mainpackage.Velocity;

/**
 * The class CreateLevel - create level by the definitions files.
 * collisions and direct its movement.
 * @author Michael Shachar and Hila Zecharia. */
public class CreateLevel implements LevelInformation {
    private ParseLevelSpecification level;

    /**
     * Constructor CreateLevel.
     * @param level - the parsed level definitions. */
    public CreateLevel(ParseLevelSpecification level) {
        this.level = level;
    }

    /**
     * The function numberOfBalls - decide how much balls will be in the level.
     * @return the number of balls. */
    public int numberOfBalls() {
        return this.level.getNumberOfBalls();
    }

    /**
     * The function initialBallVelocities - decide initial velocity of
     * each ball.
     * @return velocities of the balls. */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> initialBallVelocities = new ArrayList<Velocity>();
        List<Double> velocitiesList = this.level.getIntitialBallVelcoities();
        for (int i = 0; i < velocitiesList.size(); i += 2) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(
                    velocitiesList.get(i), velocitiesList.get(i + 1)));
        }
        return initialBallVelocities;
    }

    /**
     * The function paddleSpeed - decide what will be the paddle speed.
     * @return the paddle speed. */
    public int paddleSpeed() {
        return this.level.getPaddleSpeed();
    }

    /**
     * The function paddleWidth - decide what will be the paddle width.
     * @return the paddle width. */
    public int paddleWidth() {
        return this.level.getPaddleWidth();
    }

    /**
     * The function levelName - decide what will be the level name to show.
     * @return the level name. */
    public String levelName() {
        return this.level.getLevelName();
    }

    /**
     * The function getBackground - decide what background will be for
     *  Direct Hit level.
     * @return the background of the level. */
    public Sprite getBackground() {
        if (this.level.getBackground().contains("image")) {
            ImageParser imageParser = new ImageParser();
            Image levelBackground = imageParser
                    .getImageFromDef(this.level.getBackground());
            return new LevelBackground(levelBackground);
        } else {
            ColorsParser colorsParser = new ColorsParser();
            Color color = colorsParser.
                    colorFromString(this.level.getBackground());
            return new LevelBackground(color);
          }
    }

    /**
     * The function LineNumberReader - create LineNumberReader.
     * @param path - the path of the file which we wrapped.
     * @return the LineNumberReader. */
    private LineNumberReader createLNReader(String path) {
        LineNumberReader lineNumberReader = null;
        InputStream is = null;
        try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
                lineNumberReader = new LineNumberReader(
                        new InputStreamReader(is));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineNumberReader;
    }

    /**
     * The function blocks - The Blocks that make up this level,
     *  each block contains its size, color and location.
     * @return the block in list. */
    public List<Block> blocks() {
        String blockDefinitionsPath =
                this.level.getBlockDefinitionsPath();
        LineNumberReader reader = this.createLNReader(blockDefinitionsPath);
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = null;
        try {
            blocksFromSymbolsFactory =
                    BlocksDefinitionReader.fromReader(reader);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<String> drawSomething = this.level.getDrawSomething();
        double startX = this.level.getBlockStartX(),
                ypos = this.level.getBlockStartY();
        double xpos = startX;
        List<Block> blocksList = new ArrayList<Block>();
        Map<String, Integer> spacerWidths =
                blocksFromSymbolsFactory.getSpacerWidths();
        Map<String, BlockCreator> blockCreators =
                blocksFromSymbolsFactory.getBlockCreators();
        for (String line : drawSomething) {
             for (int i = 0; i < line.length(); i++) {
                if (blocksFromSymbolsFactory.
                        isSpaceSymbol(String.valueOf(line.charAt(i)))) {
                    xpos += spacerWidths.get(String.valueOf(line.charAt(i)));
                } else if (blocksFromSymbolsFactory.
                        isBlockSymbol(String.valueOf(line.charAt(i)))) {
                    blocksList.add(blocksFromSymbolsFactory.getBlock(
                            String.valueOf(line.charAt(i)),
                            (int) xpos, (int) ypos));
                    xpos += blockCreators.
                            get(String.valueOf(line.charAt(i))).getWidth();
                }
            }
            // we in new line.
            ypos += this.level.getRowHeight();
            xpos = startX;
        }

        return blocksList;
    }

    /**
     * The function numberOfBlocksToRemove - decide how much blocks
     *  the player should destroy until he win.
     * @return the number of the block that should be removed. */
    public int numberOfBlocksToRemove() {
        return this.level.getNumberOfBlocksToRemove();
    }

}
