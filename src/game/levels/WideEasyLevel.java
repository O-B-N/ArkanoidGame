package game.levels;
// ID: 209083682

import geometry.Point;
import geometry.Rectangle;
import gui.Sprite;
import gui.collision.Block;
import movement.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * wide easy level class.
 */
public class WideEasyLevel implements LevelInformation {
    private final List<Block> l;
    private int blocks;

    /**
     * create a wide easy level.
     */
    public WideEasyLevel() {
        Map<Integer, Color> map = new HashMap<>();
        map.put(3, Color.orange);
        map.put(5, Color.yellow);
        map.put(7, Color.green);
        map.put(10, Color.blue);
        map.put(12, Color.pink);
        map.put(14, Color.cyan);
        this.blocks = 0;
        int start = 25, height = 20, blockSize = 50;
        Color c = Color.red;
        this.l = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            if (map.containsKey(i)) {
                c = map.get(i);
            }
            blocks++;
            this.l.add(new Block(new Rectangle(new Point(start, 250), blockSize, height, c)));
            start += blockSize;
        }
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        int angle = 330;
        for (int i = 0; i < 10; i++) {
            v.add(Velocity.fromAngleAndSpeed(angle, 10));
            angle += 5;
            if (i == 5) {
                angle = 370;
            }
        }
        return v;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 550;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background2();
    }

    @Override
    public List<Block> blocks() {
        return this.l;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks;
    }
}
