package mainpackage;

import geoshapes.Point;
import indicators.LevelIndicator;
import indicators.LivesIndicator;
import indicators.ScoreIndicator;
import java.awt.Color;
import java.util.List;
import levelbuild.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * The class GameLevel - initialize game level and play one turn.
 * Manage any level each time.
 * @author Michael Shachar and Hila Zecharia. */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter availableBalls;
    private Counter remainingBlocks;
    private Counter score;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private GUI gui;
    private boolean running;
    private Paddle paddle;
    private LevelInformation levelInformation;
    private Counter lives;

    /**
     * Constructor GameLevel.
     * @param runner - the object which run animations.
     * @param gui - the gui of the game.
     * @param keyboard - keyboard.
     * @param levelInformation - the level which we run.
     * @param remainingBlocks - the game environment.
     * @param score - the score of the player.
     * @param lives - the lives of the player. */
    public GameLevel(LevelInformation levelInformation, AnimationRunner runner,
            GUI gui, KeyboardSensor keyboard, Counter remainingBlocks,
            Counter score, Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.levelInformation = levelInformation;
        this.runner = runner;
        this.gui = gui;
        this.keyboard = keyboard;
        this.availableBalls = new Counter(0);
        this.remainingBlocks = remainingBlocks;
        this.score = score;
        this.lives = lives;
    }

    /**
     * The function addCollidable - add the given collidable
     *  to the environment.
     * @param c - the collidable which we want to add. */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * The function addSprite - add sprite to the sprites list.
     * @param s - the sprite which we want to add. */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * The function removeCollidable - remove the given collidable
     *  from the environment.
     * @param c - the collidable which we want to remove. */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * The function addSprremoveSpriteite - add sprite from the sprites list.
     * @param s - the sprite which we want to remove. */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * The function initialize - setting up the game.
     * adding the game blocks (include the borders blocks), and the paddle */
    public void initialize() {
        final int screenWidth = 800, screenHeight = 600, bordersBlocksSize = 25;
        this.levelInformation.getBackground().addToGame(this);
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(
                this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.lives);
        livesIndicator.addToGame(this);
        LevelIndicator levelIndicator = new LevelIndicator(
                this.levelInformation.levelName());
        levelIndicator.addToGame(this);
        Block upBlock = new Block(new Point(0, 15), screenWidth, 20,
                Color.GRAY);
        upBlock.addToGame(this);
        Block rightBLock = new Block(new Point(screenWidth - bordersBlocksSize,
                bordersBlocksSize - 8), bordersBlocksSize, screenHeight,
                Color.GRAY);
        rightBLock.addToGame(this);
        Block leftBlock = new Block(new Point(0, bordersBlocksSize - 8),
                bordersBlocksSize, screenHeight, Color.GRAY);
        leftBlock.addToGame(this);
        Block deathRegion = new Block(new Point(bordersBlocksSize - 1,
                screenHeight - 5), screenWidth - (2 * bordersBlocksSize) + 2,
                bordersBlocksSize, Color.GRAY);
        deathRegion.addToGame(this);
        BallRemover ballremover = new BallRemover(this, this.availableBalls);
        deathRegion.addHitListener(ballremover);
        this.remainingBlocks.increase(this.levelInformation
                .numberOfBlocksToRemove());
        BlockRemover blockRemover = new BlockRemover(this,
                this.remainingBlocks);
        List<Block> blocksList = this.levelInformation.blocks();
        if (!blocksList.isEmpty()) {
            for (int i = 0; i < blocksList.size(); i++) {
                Block block = blocksList.get(i);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTracking);
                block.addToGame(this);
            }
        }
        int paddleHeight = 17;
        Point paddleUpperLeft = new Point(350, 560);
        this.paddle = new Paddle(paddleUpperLeft,
                this.levelInformation.paddleWidth(), paddleHeight,
                Color.YELLOW, this.gui.getKeyboardSensor(),
                screenWidth, this.levelInformation.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * The function createBallsOnTopOfPaddle - create the balls of the level
     *  on top of the paddle. */
    public void createBallsOnTopOfPaddle() {
        final int screenWidth = 800;
        double paddleWidth = this.paddle.getCollisionRectangle().getWidth();
        this.paddle.getCollisionRectangle().getUpperLeft()
                .setX((screenWidth - paddleWidth) / 2);
        // create the balls
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Point ballCenter = new Point(400, 550);
            int ballRadius = 5;
            Ball ball = new Ball(ballCenter, ballRadius, Color.WHITE,
                    this.environment);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(
                    i));
            ball.addToGame(this);
        }

    }

    /**
     * The function setStop - set the stoppable condition to the given value.
     * @param stop - the given stop. */
    public void setStop(boolean stop) {
       this.running = stop;
    }
    /**
     * The function shouldStop - return boolean variable which
     * indicate when we should stop the animation.
     *  @return boolean variable.*/
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * The function playOneTurn - play one turn
        (numbers of turns = numbers of lives). */
    public void playOneTurn() {
        this.availableBalls.increase(this.levelInformation.numberOfBalls());
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * The function doOneFrame - do one frame of the level (one
     * iteration of the while loop in run method in AnimationRunner class).
     * @param d - the surface which we draw on.
     * @param dt - . */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.remainingBlocks.getValue() == 0) {
            this.running = false;
        }
        if (this.availableBalls.getValue() == 0) {
            this.lives.decrease(1);
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner
            .run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }
}
