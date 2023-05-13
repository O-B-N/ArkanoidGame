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
public class Background2 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        Block b = new Block(new geometry.Rectangle(new Point(0, 0)
                , 800, 600, Color.white));
        b.drawOn(d);
        Point start = new Point(150, 150);
        d.setColor(Color.orange);
        int x = 0;
        while (x < 750) {
            d.drawLine((int) start.getX(), (int) start.getY(), x, 250);
            x += 10;
        }
        d.drawCircle(150, 150, 50);
        d.fillCircle(150, 150, 50);
        d.setColor(Color.YELLOW);
        d.drawCircle(150, 150, 40);
        d.fillCircle(150, 150, 40);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
