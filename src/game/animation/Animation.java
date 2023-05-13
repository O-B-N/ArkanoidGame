package game.animation;
// ID: 209083682

import biuoop.DrawSurface;

/**
 * an animation interface that can draw a frame and can returns if it should stop.
 */
public interface Animation {

    /**
     * draws a frame on the given draw surface.
     * @param d the given draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return a boolean. true if it should stop and false otherwise.
     */
    boolean shouldStop();
}
