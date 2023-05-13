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
public class Background4 implements Sprite {
    private List<Block> l;

    @Override
    public void drawOn(DrawSurface d) {
        int x = 70, y = 300, width = 95, height = 20;
        this.l = new ArrayList<>();
        this.l.add(new Block(new geometry.Rectangle(new Point(0, 0), 800, 600, Color.cyan)));

        buildPipes(x, y);

        x += 200;
        y += 100;

        buildPipes(x, y);

        x += 200;
        y += 150;

        buildPipes(x, y);
        x += 200;
        y -= 100;

        buildPipes(x, y);

        for (Block block : this.l) {
            block.drawOn(d);
        }

    }

    /**
     * method to build pipes in the background based on x and y coordinate.
     * @param x the given x coordinate
     * @param y the given y coordinate
     */
    private void buildPipes(int x, int y) {
        int width = 95, height = 20;
        this.l.add(new Block(new Rectangle(new Point(x, y), width, 600 - y, Color.green)));
        this.l.add(new Block(new Rectangle(new Point(x - 10, y), width + 20, height, Color.green)));
        this.l.add(new Block(new Rectangle(new Point(x, 0), width, y - 225, Color.green)));
        this.l.add(new Block(new Rectangle(new Point(x - 10, y - 225), width + 20, height, Color.green)));
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }
}
