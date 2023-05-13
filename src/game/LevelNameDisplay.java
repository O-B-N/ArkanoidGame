package game;
// ID: 209083682

import biuoop.DrawSurface;
import gui.Sprite;
import java.awt.Color;

/**
 * a level name display class that displays the level name in the level.
 */
public class LevelNameDisplay implements Sprite {
    private final String name;

    /**
     * create a level name indicator object.
     * @param s the level name
     */
    public LevelNameDisplay(String s) {
        this.name = s;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(600, 14, "Level Name:", 15);
        d.drawText(690, 14, this.name, 15);
    }

    @Override
    public void timePassed() { }

    @Override
    public void addToGame(GameLevel g) {
    }
}
