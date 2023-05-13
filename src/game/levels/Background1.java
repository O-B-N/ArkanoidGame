package game.levels;
// ID: 209083682

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import gui.Sprite;
import gui.collision.Block;
import java.awt.Color;

/**
 * a background class.
 */
public class Background1 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        Block b = new Block(new geometry.Rectangle(new Point(0, 0)
                , 800, 600, Color.BLACK));
        b.drawOn(d);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 165, 70);
        d.drawCircle(400, 165, 100);
        d.drawCircle(400, 165, 125);
        d.drawLine(240, 165, 380, 165);
        d.drawLine(420, 165, 560, 165);
        d.drawLine(400, 145, 400, 0);
        d.drawLine(400, 185, 400, 305);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
