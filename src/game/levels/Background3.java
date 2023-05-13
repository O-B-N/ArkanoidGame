package game.levels;
// ID: 209083682

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import gui.Sprite;
import gui.collision.Block;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * a background class.
 */
public class Background3 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        List<Block> l = new ArrayList<>();
        l.add(new Block(new Rectangle(new Point(0, 0), 800, 600, Color.green)));
        int x = 75, y = 450, xCircle = 115, yCircle = 250;
        l.add(new Block(new Rectangle(new Point(111, 250), 7, 500, Color.lightGray)));
        l.add(new Block(new Rectangle(new Point(100, 400), 30, 500, Color.darkGray)));
        l.add(new Block(new Rectangle(new Point(70, 445), 95, 200, Color.black)));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                l.add(new Block(new Rectangle(new Point(x, y), 13, 25, Color.white)));
                x += 18;
            }
            x = 75;
            y += 30;
        }

        for (Block block : l) {
            block.drawOn(d);
        }

        d.setColor(Color.orange);
        d.drawCircle(xCircle, yCircle, 15);
        d.fillCircle(xCircle, yCircle, 15);
        d.setColor(Color.red);
        d.drawCircle(xCircle, yCircle, 10);
        d.fillCircle(xCircle, yCircle, 10);
        d.setColor(Color.white);
        d.drawCircle(xCircle, yCircle, 5);
        d.fillCircle(xCircle, yCircle, 5);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
