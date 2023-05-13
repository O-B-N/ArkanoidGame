package gui.collision;
// ID: 209083682

import geometry.Point;

/**
 * @author Or Ben Naim
 * a line class.
 */
public class CollisionInfo {
    private final Collidable c;
    private final Point collision;

    /**
     * Construct a collisionInfo object that hold a collidable and a the point of collision.
     *
     * @param collidable the collidable object
     * @param collisionPoint the collision point
     */
    public CollisionInfo(Collidable collidable, Point collisionPoint) {
        this.c = collidable;
        this.collision = collisionPoint;
    }

    /**
     * @return the point of collision
     */
    public Point collisionPoint() {
        return collision;
    }

    /**
     * @return collidable object that was involved
     */
    public Collidable collisionObject() {
        return c;
    }
}