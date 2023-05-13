package movement;
// ID: 209083682

import geometry.Point;

/**
 * @author Or Ben Naim
 * a velocity class.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Construct a point given x and y coordinates.
     *
     * @param dx the change in the x direction
     * @param dy the change in the x direction
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return the units value of the velocity calculated out of the dx and dy values.
     */
    public double getUnits() {
        return Math.sqrt(Math.pow(this.getDx(), 2) + Math.pow(this.getDy(), 2));
    }

    /**
     * @return the dx of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the dx of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param angle the given angle
     * @param units the units of the velocity
     * @return the velocity calculated with the angle and units given
     */
    public static Velocity fromAngleAndSpeed(double angle, double units) {
        //create new angle then is less then 360 degrees
        double newAngle = angle % 360;
        //create radians of the angle and calculate the x and y speeds
        double red = Math.toRadians(newAngle);
        double dx = Math.sin(red) * units;
        double dy = Math.cos(red) * units;
        //if needed by the angle value flip the positivity of the y speed
        if ((newAngle >= 90 && newAngle <= 270) || (newAngle >= 0 && newAngle <= 90)
                || (newAngle >= 180 && newAngle <= 360)) {
            dy *= -1;
        }
        return new Velocity(dx, dy);
    }

    /**
     * make the speed in x direction to the opposite direction with the same value.
     */
    public void invertDx() {
        this.dx *= -1;
    }

    /**
     * make the speed in y direction to the opposite direction with the same value.
     */
    public void invertDy() {
        this.dy *= -1;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p the given point
     * @return the new point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}