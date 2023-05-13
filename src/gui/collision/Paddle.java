package gui.collision;
// ID: 209083682

import game.GameLevel;
import gui.Ball;
import gui.Sprite;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import movement.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Or Ben Naim
 * a paddle class that implements both the collidable and sprite interfaces.
 * the paddle can be controled by the player using the a/left arrow to move left and d/right arrow to move right
 * and is a collidable in a game.
 */
public class Paddle implements Sprite, Collidable {
    private Point startingPoint;
    private Rectangle rec;
    private final biuoop.KeyboardSensor keyboard;
    private final int speed;
    private final int borderSize;
    private final int width;

    /**
     * construct a paddle using a rectangle, biuoop.KeyboardSensor, speed, boarder size and width.
     * @param rec the given rectangle
     * @param keyboard the given biuoop.KeyboardSensor
     * @param speed the given speed
     * @param borderSize the given boarder size
     * @param width the given width
     */
    public Paddle(Rectangle rec, biuoop.KeyboardSensor keyboard, int speed, int borderSize, int width) {
        this.rec = rec;
        this.startingPoint = rec.getUpperLeft();
        this.keyboard = keyboard;
        this.speed = speed;
        this.borderSize = borderSize;
        this.width = width;
    }

    /**
     * if there is enough space move the paddle to the left.
     */
    public void moveLeft() {
        //create a point that is have the maximum x value of the border size (so the paddle stays inside the boarder)
        // and the current x value of the upper left point of the rectangle minus the speed value
        Point p = new Point(Math.max((int) rec.getUpperLeft().getX() - speed, borderSize), rec.getUpperLeft().getY());
        //set the paddle's rectangle to the new rectangle with the new upper left point p
        this.changedRec(p);
    }

    /**
     * if there is enough space move the paddle to the right.
     */
    public void moveRight() {
        //create a point that is have the minimum x value of the width - borderSize - rec.getWidth()
        // (so the paddle stays inside the boarder)
        // and the current x value of the upper left point of the rectangle plus the speed value
        Point p = new Point(Math.min((int) rec.getUpperLeft().getX() + speed, width - borderSize - rec.getWidth())
                , rec.getUpperLeft().getY());
        //set the paddle's rectangle to the new rectangle with the new upper left point p
        this.changedRec(p);
    }

    /**
     * change the paddle's rectangle to a new one with a new upper left point.
     * @param p the given new upper left point of the rectangle.
     */
    private void changedRec(Point p) {
        this.rec = new Rectangle(p, this.rec.getWidth(), this.rec.getHeight(), this.rec.getColor());
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.rec.drawOn(d);
    }

    @Override
    public void timePassed() {
        //the 'a' or left arrow key is pressed then go to moveLeft
        if (keyboard.isPressed("a") || keyboard.isPressed(KeyboardSensor.LEFT_KEY) || keyboard.isPressed("left")) {
            moveLeft();
        }
        //the 'd' or right arrow key is pressed then go to moveRight
        if (keyboard.isPressed("d") || keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
                || keyboard.isPressed("right")) {
            moveRight();
        }
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rec;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //get the line array of the rectangle's sides
        Line[] lines = rec.getLineArray();
        //get the current velocity to a new v velocity
        Velocity v = currentVelocity;
        //if the collision point is on the upper side then find the new correct velocity to return
        if (lines[0].pointInLine(collisionPoint)) {
            //get fifth of the value of the paddle's width
            double fifth = rec.getWidth() / 5;
            //get the x value of the upper left point of the rectangle
            double recX = rec.getUpperLeft().getX();
            //get the x value of the collision point
            double x = collisionPoint.getX();
            //get the units of the balls speed
            double ballSpeed = v.getUnits();
            //find at what fifth of the upper side line the collision point is (starting from the left)
            if (x < recX + fifth) {
                //the first fifth, so send it a lot to the left
                v = Velocity.fromAngleAndSpeed(300, ballSpeed);
            } else  if (x < recX + fifth * 2) {
                //the second fifth, so send it a little to the left
                v = Velocity.fromAngleAndSpeed(330, ballSpeed);
            } else if (x > recX + fifth * 4) {
                //the fifth fifth, so send it a lot to the right
                v = Velocity.fromAngleAndSpeed(30, ballSpeed);
            } else if (x > recX + fifth * 3) {
                //the forth fifth, so send it a little to the right
                v = Velocity.fromAngleAndSpeed(60, ballSpeed);
            } else {
                //the third fifth, so just invert the dy value (change direction of the y axis)
                v.invertDy();
            }
        }
        //if the collision point is on the left or right side then invert the dx value (change direction of the x axis)
        if (lines[2].pointInLine(collisionPoint) || lines[3].pointInLine(collisionPoint)) {
            v.invertDx();
        }
        //return the new, updated velocity
        return v;
    }

    /**
     * a method that reset the paddle to the center of the gui.
     */
    public void reset() {
        changedRec(startingPoint);
    }

    @Override
    public void addToGame(GameLevel g) {
        // Add this paddle to the game.
        g.addSprite(this);
        g.addCollidable(this);
    }
}