package geometry;
// ID: 209083682

/**
 * @author Or Ben Naim
 * a point class.
 */
public class Point {
    private final double x;
    private final double y;


    /**
     * Construct a point given x and y coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other the second point
     * @return the distance to the other point
     */
    public double distance(Point other) {
        //calculating the distance and returning it
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }

    /**
     * equals -- return a boolean, true is the points are equal, false otherwise.
     *
     * @param other the second point
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        double epsilon = Math.pow(10, -5);
        return (Math.abs(this.x - other.x) < epsilon && Math.abs(this.y - other.y) < epsilon);
    }

    /**
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }
}
