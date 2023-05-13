package gui.collision;
// ID: 209083682

import game.GameLevel;
import gui.Ball;
import gui.Sprite;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import movement.Velocity;
import biuoop.DrawSurface;
import hit.HitNotifier;
import hit.HitListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Or Ben Naim
 * a Block class that implements both the collidable and sprite interfaces - an object that can be collidable in a game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final List<HitListener> hitListeners = new ArrayList<>();
    private final Rectangle rec;

    /**
     * Construct a block given a rectangle.
     *
     * @param rectangle a rectangle that will represent the block
     */
    public Block(Rectangle rectangle) {
        this.rec = rectangle;
    }

    /**
     * @return all listeners of the block.
     */
    public List<HitListener> getListeners() {
        return this.hitListeners;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * notifies all of the block listeners of a hit.
     * @param hitter the given ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //getting an array of every side of the collision rectangle
        Line[] lines = getCollisionRectangle().getLineArray();
        //if the collision point is in the upper or bottom sides then invert the dy change in the velocity
        if (lines[0].pointInLine(collisionPoint) || lines[1].pointInLine(collisionPoint)) {
            currentVelocity.invertDy();
        }
        //if the collision point is in the left or right sides then invert the dx change in the velocity
        if (lines[2].pointInLine(collisionPoint) || lines[3].pointInLine(collisionPoint)) {
            currentVelocity.invertDx();
        }
        //return the new, updated velocity
     //  if (hitter != null) {
           this.notifyHit(hitter);
     //  }
        return currentVelocity;
    }

    /**
     * removes blocks that got hit in the game.
     * @param game the game to remove the block from the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.rec.drawOn(d);
    }

    @Override
    public void timePassed() { }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
