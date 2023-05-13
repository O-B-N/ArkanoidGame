package gui;
// ID: 209083682

import game.GameLevel;
import biuoop.DrawSurface;

/**
 * @author Or Ben Naim
 * a sprite interface - will be implemented on classes that have an object that can be in a game.
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d the given surface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * method that will add the collidable to the given game.
     * @param g the given game
     */
    void addToGame(GameLevel g);
}