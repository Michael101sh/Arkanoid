package levelbuild;

import java.util.List;
import mainpackage.Block;
import mainpackage.Sprite;
import mainpackage.Velocity;

/**
 * The interface LevelInformation
 * specifies the information required to fully describe a level.
 * @author Michael Shachar and Hila Zecharia. */
public interface LevelInformation {
    /**
     * The function numberOfBalls - decide how much balls will be in the level.
     * @return the number of balls. */
    int numberOfBalls();

    /**
     * The function initialBallVelocities - decide initial velocity of
     * each ball.
     * @return velocities of the balls. */
    List<Velocity> initialBallVelocities();

    /**
     * The function paddleSpeed - decide what will be the paddle speed.
     * @return the paddle speed. */
    int paddleSpeed();

    /**
     * The function paddleWidth - decide what will be the paddle width.
     * @return the paddle width. */
    int paddleWidth();

    /**
     * The function levelName - decide what will be the level name to show.
     * @return the level name. */
    String levelName();

    /**
     * The function getBackground - decide what background will be for
     *  Direct Hit level.
     * @return the background of the level. */
    Sprite getBackground();

    /**
     * The function blocks - The Blocks that make up this level,
     *  each block contains its size, color and location.
     * @return the block in list. */
    List<Block> blocks();

    /**
     * The function numberOfBlocksToRemove - decide how much blocks
     *  the player should destroy until he win.
     * @return the number of the block that should be removed. */
    int numberOfBlocksToRemove();
}