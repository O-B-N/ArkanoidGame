package gui.collision;
// ID: 209083682

import geometry.Line;
import geometry.Point;

import java.util.ArrayList;

/**
 * @author Or Ben Naim
 * a game enviroment class that has a collidable list and can get infomation of a collision point within the envirment.
 */
public class GameEnvironment {
    private final java.util.List<Collidable> collidableList = new ArrayList<>();

    /**
     * add the given collidable to the Collidable list.
     * @param c the given Collidable
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * a method to get the collision info of a trajectory line with collidable in the environment (could be null).
     * Assumes that the object is moving from line.start() to line.end().
     * If this object will not collide with any of the collidables in this collection, return null.
     * else, return the information bout the closest collision that is going to occur.
     * @param trajectory the given trajectory line
     * @return a CollisionInfo object (could be null) that hold the collidable and the collision point with the line
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //set closest and temp points to null and start to the start of the trajectory line
        Point closest = null, temp, start = trajectory.start();
        // create a collidable variable c
        Collidable c = null;
        //for every collidable in the collidableList get the closest interaction point (if exist)
        for (Collidable collidable : collidableList) {
            temp = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            //if the given point is not null and the closest is null or closest distance to start if greater then the
            //new temp point set closest to the temp point and the collidable
            if (temp != null && (closest == null || closest.distance(start) > temp.distance(start))) {
                closest = temp;
                c = collidable;
            }
        }
        //if the closest is still null return null
        if (closest == null) {
            return null;
        }
        //else return a new collisions object with the collision collidable and and closest intersection point
        return new CollisionInfo(c, closest);
    }

    /**
     * removes collidables that got hit in the game.
     * @param c the collidable to remove the block from the game
     */
    public void removeCollidable(Collidable c) {
        java.util.List<Collidable> newCollidableList = new ArrayList<>(collidableList);
        for (Collidable collidable : newCollidableList) {
            if (c.equals(collidable)) {
                this.collidableList.remove(c);
            }
        }
    }
}