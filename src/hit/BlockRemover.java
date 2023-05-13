package hit;
// ID: 209083682

import game.GameLevel;
import gui.Ball;
import gui.collision.Block;
import game.Counter;

/**
 * a block remover class, and keeps count of remaining number of blocks.
 */
public class BlockRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * creates a block Ramover object.
     * @param game the given game to remove from
     * @param removedBlocks a counter of blocks to remove
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.game);
    }
}
