package listeners;

import mainpackage.Ball;
import mainpackage.Block;
import mainpackage.Counter;
import mainpackage.GameLevel;

/**
 * The class BallRemover - a BallRemover is in charge of removing
 * balls from the game and update the number of balls that remained.
 * @author Michael Shachar and Hila Zecharia. */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter availableBalls;

    /**
     * Constructor BallRemover.
     *  @param game - the game level object.
     * @param availableBalls - the available balls counter. */
    public BallRemover(GameLevel game, Counter availableBalls) {
        this.game = game;
        this.availableBalls = availableBalls;
    }

    /**
     * The function hitEvent - This method is called whenever
     * the beingHit object is hit and remove the ball from the game
     * and it listener.
     * @param beingHit - the block which being hit.
     * @param hitter - the Ball that's doing the hitting. */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        hitter.removeHitListener(this);
        this.availableBalls.decrease(1);
    }

}
