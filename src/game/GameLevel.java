package game;
// ID: 209083682

import biuoop.KeyboardSensor;
import game.animation.Animation;
import game.animation.AnimationRunner;
import game.animation.CountdownAnimation;
import game.animation.PauseScreen;
import game.animation.KeyPressStoppableAnimation;
import game.levels.LevelInformation;
import geometry.Point;
import geometry.Rectangle;
import gui.collision.Block;
import gui.collision.Collidable;
import gui.collision.GameEnvironment;
import gui.collision.Paddle;
import hit.BallRemover;
import hit.BlockRemover;
import movement.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import gui.SpriteCollection;
import gui.Sprite;
import gui.Ball;

/**
 * @author Or Ben Naim
 * an Arkanoid game class.
 */
public class GameLevel implements Animation {
    private final AnimationRunner runner;
    private boolean running;
    private final SpriteCollection sprites = new SpriteCollection();;
    private final GameEnvironment environment = new GameEnvironment();
    private Counter blocksLeft;
    private final Counter lives;
    private final Counter ballsLeft = new Counter();
    private final Counter scoreCounter;
    private final ScoreTrackingListener scoreTracker;
    private final biuoop.KeyboardSensor keyboard;
    private final LevelInformation info;
    private Paddle paddle;

    /**
     * create a new game level object.
     * @param info the given level information
     * @param keyboard the given keyboard sensor
     * @param runner the given animation runner
     * @param score the given score of the game
     * @param lives the given lives in the game
     */
    public GameLevel(LevelInformation info, KeyboardSensor keyboard
            , AnimationRunner runner, Counter score, Counter lives) {
        this.info = info;
        this.keyboard = keyboard;
        this.runner = runner;
        this.scoreCounter = score;
        this.scoreTracker = new ScoreTrackingListener(scoreCounter);
        this.lives = lives;
    }

    /**
     * adds a given sprite to the game.
     * @param c a collidable to add to the game
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * adds a given sprite to the game.
     * @param s a sprite to add to the game
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * add every collidable in the list using a for loop.
     * @param list a given list of collidable to add
     */
    public void addCollidables(java.util.List<Collidable> list) {
        for (Collidable c : list) {
            c.addToGame(this);
        }
    }

    /**
     *
     * add every sprite in the list using a for loop.
     * @param list a given list of sprites to add
     */
    public void addSprites(java.util.List<Sprite> list) {
        for (Sprite s : list) {
            s.addToGame(this);
        }
    }


    /**
     * create borders to the given window.
     * @param width the width of the window
     * @param height the height of the window
     * @param size the size of the border
     * @param color the color of the border blocks
     */
    public void createBorders(int width, int height, int size, java.awt.Color color) {
        java.util.List<Collidable> blocks = new ArrayList<>();
        int scoreBlockSize = 16;
        Point start = new Point(0, scoreBlockSize);
        blocks.add(new Block(new Rectangle(new Point(0, 0), width, scoreBlockSize, Color.WHITE)));
        blocks.add(new Block(new Rectangle(start, size, height, color)));
        blocks.add(new Block(new Rectangle(new Point(width - size, scoreBlockSize), size, height, color)));
        blocks.add(new Block(new Rectangle(start, width, size, color)));
        Block bottom = new Block(new Rectangle(new Point(0, height), width, size + 10, Color.black));
        bottom.addHitListener(new BallRemover(this, ballsLeft));
        blocks.add(bottom);
        addCollidables(blocks);
    }

    /**
     * initializing the game
     * create the Blocks, Balls and GUI.Collision.Paddle and add them to the game.
     */
    public void initialize() {
        this.running = true;
        //set the starting parameters of the game
        int width = 800, height = 600, paddleWidth = this.info.paddleWidth(), borderSize = 25, paddleHeight = 15
                , paddleSpeed = this.info.paddleSpeed();
        //set the colors of the paddle and the boarder blocks color
        Color paddleColor = Color.yellow, boarderColor = Color.GRAY;
        //ScoreIndicator s = new ScoreIndicator(this.scoreCounter);
        addSprite(this.info.getBackground());
        //LivesIndicator lives = new LivesIndicator(this.lives);
        List<Collidable> l = new ArrayList<>();
        this.blocksLeft = new Counter(this.info.numberOfBlocksToRemove());
        BlockRemover remover = new BlockRemover(this, this.blocksLeft);
        for (Block b : this.info.blocks()) {
            b.addHitListener(remover);
            b.addHitListener(scoreTracker);
            l.add(b);
        }
        addCollidables(l);
        //create a rectangle for the paddle and create the paddle, then add it to the game
        Rectangle pad = new Rectangle(new Point((int) ((width - paddleWidth) / 2)
                , height - borderSize - paddleHeight), paddleWidth  , paddleHeight, paddleColor);
        Paddle pa = new Paddle(pad, this.keyboard, paddleSpeed, borderSize, width);
        pa.addToGame(this);
        this.paddle = pa;
        createBalls();
        createBorders(width, height, borderSize, boarderColor);
        addSprite(new ScoreIndicator(this.scoreCounter));
        addSprite(new LevelNameDisplay(this.info.levelName()));
        addSprite(new LivesIndicator(this.lives));
    }

    /**
     * creates new ball to the game.
     */
    private void createBalls() {
        //set the starting point of the balls
        this.paddle.reset();
        List<Velocity> v = this.info.initialBallVelocities();
        this.ballsLeft.increase(this.info.numberOfBalls());
        Point start = new Point(400, 550);
        int r = 5;
        Color c = Color.WHITE;
        for (int i = 0; i < this.ballsLeft.getValue(); i++) {
            this.addSprite(new Ball(new Point(start.getX(), start.getY()), r, v.get(i), c, environment));
        }
    }

    /**
     * create blocks to the game.
     * @param width the width of the blocks
     * @param height the height of the blocks
     * @param borderSize the border size in the sides
     */
    public void createBlocks(int width, int height, int borderSize) {
        //create a new list of Collidables
        java.util.List<Collidable> blocks = new ArrayList<>();
        // create correct ints of the starting block number, block rows, start x value and start y value
        int blocksNumber = 12, blockRows = 6, startX = 800 - borderSize - width, startY = 145;
        BlockRemover remover = new BlockRemover(this, this.blocksLeft);
        //get a list of colors to the blocks
        java.util.List<Color> colors = generateColors();
        //the starting point of the blocks
        Point start = new Point(startX, startY);
        //run the number of rows times
        for (int i = 0; i < blockRows; i++) {
            //run number of block number minus the current row number
            for (int j = i; j < blocksNumber; j++) {
                //add a new rectangle and add it in a correct position
                Block b = new Block(new Rectangle(start, width, height, colors.get(i)));
                b.addHitListener(remover);
                b.addHitListener(scoreTracker);
                blocks.add(b);
                blocksLeft.increase(1);
                //setting the next starting point for the next block
                start = new Point(start.getX() - width, start.getY());
            }
            //setting the next starting point for the next block in the next row
            start = new Point(startX, start.getY() + height);
        }
        //add all the blocks to the game
        addCollidables(blocks);
    }

    /**
     * getting a list of colors.
     * @return a color list
     */
    public java.util.List<Color> generateColors() {
        java.util.List<Color> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.CYAN);
        colors.add(Color.WHITE);
        colors.add(Color.GREEN);
        return colors;
    }

    /**
     * method that runs the game with with 60 fps.
     */
    public void run() {
        //this.createBallsOnTopOfPaddle(); // or a similar method
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * removes a given collidable from the game.
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * removes a given sprite from the game.
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);

        //finished level
        if (blocksLeft.getValue() == 0) {
            scoreCounter.increase(100);
            this.running = false;
        }

        //if there are no more balls
        if (this.ballsLeft.getValue() == 0) {
            this.lives.decrease(1);
            if (this.lives.getValue() == 0) {
                this.running = false;
                return;
            }
            createBalls();
            this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return if there are any balls left.
     */
    public int ballsLeft() {
        return this.lives.getValue();
    }
}