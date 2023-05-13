package hit;
// ID: 209083682

import gui.Ball;
import gui.collision.Block;

/**
 * hit listener interface.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the GUI.Ball that's doing the hitting.

    /**
     * when an object is hit this method is being called, gives the black and the ball of the collision.
     * @param beingHit the block that got hit
     * @param hitter the ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}