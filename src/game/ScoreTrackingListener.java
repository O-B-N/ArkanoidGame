package game;
// ID: 209083682

import gui.Ball;
import gui.collision.Block;
import hit.HitListener;

/**
 * a counter indicator.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * create a counter indicator object.
     * @param scoreCounter the given score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * increase the score after a block hit.
     * @param beingHit the block that got hit
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}