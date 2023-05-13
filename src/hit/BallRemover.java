package hit;
// ID: 209083682

import game.GameLevel;
import gui.Ball;
import gui.collision.Block;
import game.Counter;

/**
 * a ball remover lister that remove balls that fall outside the frame, and keeps count of given numbers of balls left.
 */
public class BallRemover implements HitListener {
    private final GameLevel game;
    private final Counter balls;

    /**
     * creates a ball remover object.
     * @param game the given game to remove the block from
     * @param lives number of balls the players have
     */
    public BallRemover(GameLevel game, Counter lives) {
        this.game = game;
        this.balls = lives;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        game.removeSprite(hitter);
        balls.decrease(1);
    }
}
