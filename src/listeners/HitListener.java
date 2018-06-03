package listeners;

import mainpackage.Ball;
import mainpackage.Block;

/**
 * The interface HitListener - Objects that want to be notified
 * of hit events,
 * should implement this inteface. In this assignment, this means the classes
 * BlockRemover, PrintingHitListener and ScoreTrackingListener.
 * @author Michael Shachar and Hila Zecharia. */
public interface HitListener {
    /**
     * The function hitEvent - This method is called whenever
     * the beingHit object is hit.
     * @param beingHit - the block which being hit.
     * @param hitter - the Ball that's doing the hitting. */
    void hitEvent(Block beingHit, Ball hitter);
}