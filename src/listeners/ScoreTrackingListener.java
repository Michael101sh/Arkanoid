package listeners;

import mainpackage.Ball;
import mainpackage.Block;
import mainpackage.Counter;

/**
* The class ScoreTrackingListener - update the score counter counter
* when blocks are being hit and removed.
* @author Michael Shachar and Hila Zecharia. */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor ScoreTrackingListener.
     * @param scoreCounter - the score counter. */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * The function hitEvent - This method is called whenever
     * the beingHit object is hit.
     * @param beingHit - the block which being hit.
     * @param hitter - the Ball that's doing the hitting. */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getLeftHitsTimes() != 0) {
            this.currentScore.increase(5);
        } else {
            this.currentScore.increase(15);
        }
    }
}