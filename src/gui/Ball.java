package gui;
// ID: 209083682

import game.GameLevel;
import geometry.Line;
import geometry.Point;
import gui.collision.CollisionInfo;
import gui.collision.GameEnvironment;
import movement.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * @author Or Ben Naim
 * a point class.
 */
public class Ball implements Sprite {
    private Point center;
    private final int r;
    private final java.awt.Color color;
    private Velocity v = new Velocity(1, 1);
    private GameEnvironment game = new GameEnvironment();
    private Line trajectory = null;
    private final double e = Math.pow(10, -10);

    /**
     * Construct given a center point, radios and a color.
     *
     * @param center the center point of the ball
     * @param r      the radios if the ball
     * @param velocity the velocity of the ball
     * @param color  the given color
     * @param game the game environment that the ball is in
     */
    public Ball(Point center, int r, Velocity velocity, java.awt.Color color, GameEnvironment game) {
        this.game = game;
        this.v = velocity;
        this.center = center;
        this.r = r;
        this.color = color;
        this.trajectoryLine();
    }

    /**
     * change the ball velocity to the given velocity.
     *
     * @param velocity the velocity for the ball
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * @return the x coordinate of the center of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the y coordinate of the center of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the radios of the ball
     */
    public int getSize() {
        return this.r;
    }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * @return the center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param d the surface to draw on
     */
    public void drawOn(DrawSurface d) {
        //set the color the the color of the ball and draw the ball on the surface and fill it
        int x = this.getX();
        int y = this.getY();
        d.setColor(Color.black);
        d.drawCircle(x, y, this.r);
        d.setColor(this.color);
        d.fillCircle(x, y, this.r);
    }


    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * make the ball move one step using the velocity and the center point of the ball.
     * using the game field and the trajectory field of the ball and if it will hit anything.
     * then change the position of the ball so it will not be inside a block and change to the correct velocity/
     */
    public void moveOneStep() {
        //setting the trajectory line of the ball
        this.trajectoryLine();
        //getting the info of the closest collision
        CollisionInfo info = this.game.getClosestCollision(this.trajectory);
        //checking of there is a collision and if so
        if (info != null) {
            //getting line list of the rectangle that the ball hit
            Line[] lines = info.collisionObject().getCollisionRectangle().getLineArray();
            //getting the collision point and the x and y values of it
            Point collision = info.collisionPoint();
            double x = collision.getX();
            double y = collision.getY();
            //checking at wait side the the ball hit
            // then change the position so that the ball will not be inside a block
            //upper side
            if (lines[0].pointInLine(collision)) {
                y -= this.r;
                //bottom side
            } else if (lines[1].pointInLine(collision)) {
                y += this.r;
            }
            //left side
            if (lines[2].pointInLine(collision)) {
                x -= this.r;
                //right side
            } else if (lines[3].pointInLine(collision)) {
                x += this.r;
            }

            //set the new velocity and center to the ball
            this.v = info.collisionObject().hit(this, info.collisionPoint(), this.v);
            this.center = new Point(x, y);
            return;
        }
        //set the center to the next point
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * calculate the trajectory line of the ball and set the trajectory to that line.
     */
    public void trajectoryLine() {
        Point end = this.v.applyToPoint(this.center);
        this.trajectory = new Line(this.center, end);
    }

}