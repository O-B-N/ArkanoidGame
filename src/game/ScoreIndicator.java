package game;
// ID: 209083682

import gui.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * counter indicator class.
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * create a counter indicator object.
     * @param c the score
     */
    public ScoreIndicator(Counter c) {
        score = c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(340, 14, "Score:", 15);
        d.drawText(390, 14, Integer.toString(score.getValue()), 15);
    }

    @Override
    public void timePassed() { }

    @Override
    public void addToGame(GameLevel g) {
    }
}
