package geometry;
// ID: 209083682

/**
 * @author Or Ben Naim
 * a line class.
 */
public class Line {
    private final Point start;
    private final Point end;
    private double slope = 0;
    private double intercept = 0;
    private boolean undefinedSlope = false;
    private final double e = Math.pow(10, -2);


    /**
     * Construct a point given x and y coordinates.
     *
     * @param start the first point
     * @param end the second point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        //setting the slope, intercept and boolean undefinedSlope values
        this.linearLine();
    }

    /**
     * Construct a point givne x and y coordinates.
     *
     * @param x1 the x coordinate of the start point
     * @param y1 the y coordinate of the start point
     * @param x2 the x coordinate of the end point
     * @param y2 the y coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        //setting the slope, intercept and boolean undefinedSlope values
        this.linearLine();
    }

    /**
     * create a slope and intercept value to the line.
     */
    public void linearLine() {
        this.slope();
        this.intercept = this.intercept();
    }

    /**
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        //calculating the mid point of the x coordinate and mid point of the y coordinate and return the point
        double x = (this.end.getX() + this.start.getX()) / 2;
        double y = (this.end.getY() + this.start.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * @return the intercept of the line.
     */
    public double getIntercept() {
        return this.intercept;
    }

    /**
     * @return the slope of the line.
     */
    public double getSlope() {
        return this.slope;
    }

    /**
     * @return the boolean undefinedSlope of the line.
     */
    public boolean getUndefinedSlope() {
        return this.undefinedSlope;
    }

    /**
     * calculate a slope value and if it is undefined change the undefinedSlope value to true.
     */
    public void slope() {
        //if the start and end point have the same value then the slope is undefined so set the boolean so and return
        if (this.start.getX() == this.end.getX()) {
            this.undefinedSlope = true;
            return;
        }
        //else set the boolean to false and return the calculation of the slope
        this.undefinedSlope = false;
        this.slope = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * @return the intercept value.
     */
    public double intercept() {
        //if then slope is undefined then it have no intercept with the y axis so return zero
        if (this.undefinedSlope) {
            return 0;
        }
        //else return the calculation of the intercept with the y axis
        return slope * -start.getX() + start.getY();
    }


    /**
     * @param other the second line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        ///if intersectionWith returns true or one of the points is in the line of the other return true, if not, false
        return this.intersectionWith(other) != null || this.pointInLine(other.start) || this.pointInLine(other.end)
                || other.pointInLine(this.start) || other.pointInLine(this.end);
    }

    /**
     * @param other the second line.
     * @return he intersection point if the lines intersect, and null otherwise.
     * (if the lines have more then one point in common returns null)
     */
    public Point intersectionWith(Line other) {
        double x;
        double y;
        //if the lines have the same slope then return the value from sameSlope
        if ((this.undefinedSlope && other.undefinedSlope)
                || (this.slope == other.slope && !(this.undefinedSlope || other.undefinedSlope))) {
            return this.sameSlope(other);
            //if one line have undefined slope calculate the result of y coordinate with the x coordinate
        } else if (this.undefinedSlope) {
            x = this.start.getX();
            y = x * other.slope + other.intercept;
        } else if (other.undefinedSlope) {
              x = other.start.getX();
              y = x * this.slope + this.intercept;
              //calculate where the lines would match if there were infinite
        } else {
              x = (other.intercept - this.intercept) / (this.slope - other.slope);
              y = x * this.slope + this.intercept;
        }
        //create new point and check if it is in both lines if so return true the point, else null
            Point p = new Point(x, y);
        if (this.pointInLine(p) && other.pointInLine(p)) {
            return p;
        }
        return null;
    }

    /**
     * @param other the second line.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        //if the start and end points are the same as the start and end point of the other line return true else false
        return (this.start.equals(other.start()) && this.end.equals(other.end())
                || (this.start.equals(other.end()) && this.end.equals(other.start())));
    }

    /**
     * @param p the given point.
     * @return true if the given point is in the line, false otherwise.
     */
    public boolean pointInLine(Point p) {
        double xStart = this.start.getX();
        double yStart = this.start.getY();
        double xEnd = this.end.getX();
        double yEnd = this.end.getY();
        double xPoint = p.getX();
        double yPoint = p.getY();

        //check if the x coordinate is off the line or the y coordinate is off the line, if so return false
        if ((xPoint - xEnd > e && xPoint - xStart > e) || (xEnd - xPoint > e && xStart - xPoint > e)
               || (yPoint - yEnd  > e && yPoint - yStart > e) || (yEnd - yPoint > e && yStart -  yPoint > e)) {
           return false;
           //if the slope is undefined and the point is in range then return true
        } else if (this.undefinedSlope) {
            return true;
        }
        //calculate if the two lines lines meet and if so return true, else false
        return Math.abs(this.slope * p.getX() + this.intercept - p.getY()) < e;
    }

    /**
     * @param other the second line
     * this method calculate if the two lines that have the same slope intersect and if so return the point
     * (if the lines have more then one point in common returns null)
     * @return the intersect point
     * (if the lines have more then one point in common returns null)
     */
    public Point sameSlope(Line other) {
        //check if one of the lines if actually a point and return if one is in the other
        /*
        boolean firstPoint = this.start.getX() - this.end.getX() < e && this.start.getY() - this.end.getY() < e;
        boolean secondPoint = other.start.getX() - other.end.getX() < e && other.start.getY() - other.end.getY() < e;
         */
        boolean firstPoint = this.start.getX() == this.end.getX() && this.start.getY() == this.end.getY();
        boolean secondPoint = other.start.getX() == other.end.getX() && other.start.getY() == other.end.getY();
        int count = 0;
        if (other.pointInLine(this.start)) {
            count += 1;
        }
        if (other.pointInLine(this.end)) {
            count += 1;
        }
        if (this.pointInLine(other.start)) {
            count += 1;
        }
        if (this.pointInLine(other.end)) {
            count += 1;
        }
        if (firstPoint && other.pointInLine(this.end)) {
            return this.end;
        } else if (secondPoint && this.pointInLine(other.end)) {
            return other.end;
            //if the start\end point is in the other and it is not equal to it the start\end of the other return false
        } else if (this.end.equals(other.end) && !other.pointInLine(this.start) && !this.pointInLine(other.start)) {
            return other.end;
        } else if (this.end.equals(other.start) && !other.pointInLine(this.start) && !this.pointInLine(other.end)) {
            return other.start;
        } else if (this.start.equals(other.start) && !other.pointInLine(this.end) && !this.pointInLine(other.end)) {
            return other.start;
        } else if (this.start.equals(other.end) && !other.pointInLine(this.end) && !this.pointInLine(other.start)) {
            return other.end;
        } else if (count > 1) {
                return null;
            } else if (other.pointInLine(this.start)) {
                return this.start;
            } else if (other.pointInLine(this.end)) {
                return this.end;

        }
        return null;
    }

    /**
     * @param rec a rectangle
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @return closest point, or null of there is none
     */
    public Point closestIntersectionToStartOfLine(Rectangle rec) {
        //getting a point list where the line intercept with the rectangle
        java.util.List<Point> points = rec.intersectionPoints(this);
        //creating a point to return and setting it to null
        Point point = null;
        //creating a double temp that will hold the current distance the closest that will hold the the closest distance
        double temp, closest = -1;
        //loop for every point, check if the current point is closer then the current closest point
        //if so set closest to the new distance and the point to the current point
        for (Point p : points) {
            if (p != null) {
                temp = this.start.distance(p);
                if (closest == -1 || closest > temp) {
                    closest = temp;
                    point = p;
                }
            }
        }
        return point;
    }
}
