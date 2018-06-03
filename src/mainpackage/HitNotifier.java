package mainpackage;

import listeners.HitListener;

/**
 * The interface HitNotifier - indicate that objects that implement it send
 * notifications when they are being hit.
 * @author Michael Shachar and Hila Zecharia. */
public interface HitNotifier {
    /**
     * The function addHitListener - Add hl as a listener to hit events.
     * @param hl - the listener which we add. */
    // Add hl as a listener to hit events.
    void addHitListener(HitListener hl);

    /**
     * The function removeHitListener - Remove hl from the list of listeners
     * to hit events.
     * @param hl - the listener which we remove. */
    void removeHitListener(HitListener hl);
}