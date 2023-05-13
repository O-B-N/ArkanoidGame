package game;
// ID: 209083682

import gui.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * counter indicator class.
 */
public class LivesIndicator implements Sprite {
    private final Counter lives;

    /**
     * create a counter indicator object.
     * @param c the score
     */
    public LivesIndicator(Counter c) {
        lives = c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(140, 14, "Lives:", 15);
        d.drawText(185, 14, Integer.toString(lives.getValue()), 15);
    }

    @Override
    public void timePassed() { }

    @Override
    public void addToGame(GameLevel g) {
    }
}
