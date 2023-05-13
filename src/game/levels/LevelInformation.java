package game.levels;
// ID: 209083682

import gui.Sprite;
import gui.collision.Block;
import movement.Velocity;
import java.util.List;

/**
 * an interface for level information.
 * holds number of ball with velocities, paddle speed and with, blocks and their number, background and name of level.
 */
public interface LevelInformation  {

    /**
     * @return number of balls in the level.
     */
    int numberOfBalls();

    /**
     * @return the initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * @return the level name.
     */
    String levelName();

    /**
     * @return a background sprite.
     */
    Sprite getBackground();

    /**
     * @return list of blocks in the level.
     */
    List<Block> blocks();

    /**
     * @return the number of blocks to remove to pass the level.
     */
    int numberOfBlocksToRemove();
}
