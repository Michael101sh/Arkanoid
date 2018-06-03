package listeners;

import mainpackage.Ball;
import mainpackage.Block;

/**
 * The class PrintingHitListener - simple HitListener that prints a message to
 * the screen whenever a block is hit.
 * @author Michael Shachar and Hila Zecharia. */
public class PrintingHitListener implements HitListener {
    /**
     * The function hitEvent - This method is called whenever
     * the beingHit object is hit.
     * @param beingHit - the block which being hit.
     * @param hitter - the Ball that's doing the hitting. */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getLeftHitsTimes()
                + " points was hit.");
    }
}