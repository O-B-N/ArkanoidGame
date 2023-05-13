package gui.collision;
// ID: 209083682

import game.GameLevel;
import gui.Ball;
import geometry.Point;
import geometry.Rectangle;
import movement.Velocity;

/**
 * @author Or Ben Naim
 * a collidable interface - will be implemented on classes that an object(sprite) can collide with.
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * method that will add the collidable to the given game.
     * @param g the given game
     */
    void addToGame(GameLevel g);

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity then return that velocity.
     * @param hitter the ball that hit the collidibale
     * @param collisionPoint the given collision point
     * @param currentVelocity the current velocity of the moving object
     * @return the velocity of the new, updated velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}