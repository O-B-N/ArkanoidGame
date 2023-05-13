package game.levels;
// ID: 209083682

import gui.Sprite;
import gui.collision.Block;
import movement.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * empire state level class.
 */
public class EmpireStateLevel implements LevelInformation {
    private final List<Block> l;
    private int blocks;

    /**
     * create a empire state level.
     */
    public EmpireStateLevel() {
        this.blocks = 0;
        //get a list of colors to the blocks
        java.util.List<Color> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.BLUE);
        colors.add(Color.WHITE);
        // create correct ints of the starting block number, block rows, start x value and start y value
        int width = 50, height = 20, borderSize = 25, blocksNumber = 12, blockRows = 5,
                startX = 800 - borderSize - width, startY = 145;
        //create a new list of Blocks
        this.l = new ArrayList<>();
        //the starting point of the blocks
        geometry.Point start = new geometry.Point(startX, startY);
        //run the number of rows times
        for (int i = 0; i < blockRows; i++) {
            //run number of block number minus the current row number
            for (int j = i; j < blocksNumber; j++) {
                //add a new rectangle and add it in a correct position
                l.add(new Block(new geometry.Rectangle(start, width, height, colors.get(i))));
                this.blocks++;
                //setting the next starting point for the next block
                start = new geometry.Point(start.getX() - width, start.getY());
            }
            //setting the next starting point for the next block in the next row
            start = new geometry.Point(startX, start.getY() + height);
        }
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> v = new ArrayList<>();
        v.add(Velocity.fromAngleAndSpeed(350, 10));
        v.add(Velocity.fromAngleAndSpeed(370, 10));
        return v;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 75;
    }

    @Override
    public String levelName() {
        return "Empire State";
    }

    @Override
    public Sprite getBackground() {
        return new Background3();
    }

    @Override
    public List<Block> blocks() {
        return l;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks;
    }
}
