package listeners;

import mainpackage.Ball;
import mainpackage.Block;
import mainpackage.Counter;
import mainpackage.GameLevel;

/**
 * The class BlockRemover - a BlockRemover is in charge of removing blocks from
 * the game and update the number of blocks that remained.
 * @author Michael Shachar and Hila Zecharia. */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor BlockRemover.
     *  @param game - the game level object.
     * @param remainingBlocks - the remaining blocks counter. */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * The function hitEvent - This method is called whenever the beingHit
     *  object is hit and remove the block that are hit and reach 0 hit-points
     *   from the game and it listener.
     * @param beingHit - the block which being hit.
     * @param hitter - the Ball that's doing the hitting. */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getLeftHitsTimes() == 0) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}