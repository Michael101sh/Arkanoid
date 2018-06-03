package levelbuild;

import java.util.ArrayList;
import java.util.List;

/**
 * The class ParseLevelSpecification - parse some level specification.
 * @author Michael Shachar and Hila Zecharia. */
public class ParseLevelSpecification {
    private int numberOfBalls;
    private List<Double> intitialBallVelcoities;
    private String background;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private String blockDefinitionsPath;
    private double blocksStartX;
    private double blocksStartY;
    private double rowHeight;
    private int blocksNumber;
    private int numberOfBlocksToRemove;
    private List<String> drawSomething;

    /**
     * Constructor ParseLevelSpecification.
      *  Initialize to default values.*/
    public ParseLevelSpecification() {
        this.numberOfBalls = 0;
        this.intitialBallVelcoities = new ArrayList<Double>();
        this.background = "";
        this.paddleSpeed = 0;
        this.paddleWidth = 0;
        this.levelName = "";
        this.blockDefinitionsPath = "";
        this.blocksStartX = 0;
        this.blocksStartY = 0;
        this.rowHeight = 0;
        this.blocksNumber = 0;
        this.numberOfBlocksToRemove = 0;
        this.drawSomething = new ArrayList<String>();
    }

    /**
     * The function collectInformaOfLevel - collect the information on
     *  the level and inserts it to the members of the class.
     * @param levelSpecification - the line which defines the levels. */
    public void collectInformaOfLevel(ArrayList<String> levelSpecification) {
        boolean drawSomethingFlag = false;
        for (String line : levelSpecification) { // for each line in the level.
            if (line.contains("level_name")) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ':') {
                        i++;
                        // cut the line and take the values from the line.
                        this.levelName = line.substring(i);
                        break;
                    }
                }
            } else if (line.contains("ball_velocities")) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ':') {
                        i++;
                        String substring = line.substring(i);
                        substring = substring.replaceAll(",", " ");
                        for (i = 0; i < substring.length(); i++) {
                            double num = 0;
                            double single = 0;
                            while (i < substring.length()
                                    && substring.charAt(i) != ' ') {
                                single = Character
                                        .getNumericValue(substring.charAt(i));
                                num = single + num * 10;
                                i++;
                            }
                            this.intitialBallVelcoities.add(num);
                        }
                        break;
                    }
                }
                this.numberOfBalls = this.intitialBallVelcoities.size() / 2;
            } else if (line.contains("background")) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '(') {
                        i++;
                        String substring = line.substring(i);
                        substring = substring.replace(")", "");
                        if (line.contains("image")) {
                            this.background = substring;
                        } else {
                            this.background = substring;
                        }
                        break;
                    }
                }
            } else if (line.contains("paddle_speed")) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ':') {
                        i++;
                        String str = line.substring(i);
                        this.paddleSpeed = Integer.parseInt(str);
                        break;
                    }
                }
            } else if (line.contains("paddle_width")) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ':') {
                        i++;
                        String str = line.substring(i);
                        this.paddleWidth = Integer.parseInt(str);
                        break;
                    }
                }
            } else if (line.contains("block_definitions")) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ':') {
                        i++;
                        String str = line.substring(i);
                        this.blockDefinitionsPath = str;
                        break;
                    }
                }
            } else if (line.contains("blocks_start_x")) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ':') {
                        i++;
                        String str = line.substring(i);
                        this.blocksStartX = Double.parseDouble(str);
                        break;
                    }
                }
            } else if (line.contains("blocks_start_y")) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ':') {
                        i++;
                        String str = line.substring(i);
                        this.blocksStartY = Double.parseDouble(str);
                        break;
                    }
                }
            } else if (line.contains("row_height")) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ':') {
                        i++;
                        String str = line.substring(i);
                        this.rowHeight = Double.parseDouble(str);
                        break;
                    }
                }
            } else if (line.contains("num_blocks")) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == ':') {
                        i++;
                        String str = line.substring(i);
                        this.blocksNumber = Integer.parseInt(str);
                        this.numberOfBlocksToRemove = Integer.parseInt(str);
                        break;
                    }
                }
            } else if (line.contains("START_BLOCKS")) {
                drawSomethingFlag = true;
            } else if (line.contains("END_BLOCKS")) {
                drawSomethingFlag = false;
            } else if (drawSomethingFlag) {
                this.drawSomething.add(line);
            }
        }
    }

    /**
     * The function getNumberOfBalls.
     * @return the number of Balls. */
    public int getNumberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * The function getIntitialBallVelcoities.
     * @return the intitial ball velcoities. */
    public List<Double> getIntitialBallVelcoities() {
        return this.intitialBallVelcoities;
    }

    /**
     * The function getBackground.
     * @return the level string background. */
    public String getBackground() {
        return this.background;
    }

    /**
     * The function getPaddleSpeed.
     * @return the paddle's speed. */
    public int getPaddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * The function getPaddleWidth.
     * @return the paddle's width. */
    public int getPaddleWidth() {
        return this.paddleWidth;
    }

    /**
     * The function getLevelName.
     * @return the level name. */
    public String getLevelName() {
        return this.levelName;
    }

    /**
     * The function getBlockDefinitionsPath.
     * @return the block definitions path*/
    public String getBlockDefinitionsPath() {
        return this.blockDefinitionsPath;
    }

    /**
     * The function getBlockStartX.
     * @return the blocks start x. */
    public double getBlockStartX() {
        return this.blocksStartX;
    }

    /**
     * The function getBlockStartY.
     * @return the blocks start y. */
    public double getBlockStartY() {
        return this.blocksStartY;
    }

    /**
     * The function getRowHeight.
     * @return the row height. */
    public double getRowHeight() {
        return this.rowHeight;
    }

    /**
     * The function getBlocksNumber.
     * @return the numbers of blocks */
    public int getBlocksNumber() {
        return this.blocksNumber;
    }

    /**
     * The function getNumberOfBlocksToRemove.
     * @return the number of blocks to remove. */
    public int getNumberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    /**
     * The function getDrawSomething.
     * @return .*/
    public List<String> getDrawSomething() {
        return this.drawSomething;
    }
}
