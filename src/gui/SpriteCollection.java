package gui;
// ID: 209083682

import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * @author Or Ben Naim
 * a class of a sprite collection - holds a list of sprites and can draw them, notifey that time has passed
 * and add a new sprite to the collection.
 */
public class SpriteCollection {
    private final java.util.List<Sprite> sprites = new ArrayList<>();

    /**
     * add a given sprite to the sprite collection.
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * removes a given sprite from the sprite list.
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        java.util.List<Sprite> newSprites = new ArrayList<>(sprites);
        for (Sprite sprite : newSprites) {
            if (s.equals(sprite)) {
                this.sprites.remove(s);
            }
        }
    }

    /**
     * notifies every sprite in the sprite list the time has passed.
     */
    public void notifyAllTimePassed() {
        //loop in the list and send notify the sprites that time has passed
        java.util.List<Sprite> newSprites = new ArrayList<>(sprites);
        for (Sprite s : newSprites) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d the draw surface to draw on and send to every drawOn of the sprites.
     */
    public void drawAllOn(DrawSurface d) {
        //loop in the list and send the draw surface to drawOn
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}