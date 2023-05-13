package geometry;
// ID: 209083682

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Or Ben Naim
 * a Rectangle class. a rectangule have a uuper left pointm width, height, color and holds a list of every side of it.
 * can return a point list of inersection points with a line.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;
    private final java.util.List<Line> lineList;
    private java.awt.Color color = Color.BLACK;

    /*
    // Create a new rectangle with location and width/height.
    public Geometry.Rectangle(Geometry.Point upperLeft, double width, double height) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        Geometry.Point upperRight = new Geometry.Point(upperLeft.getX() + width, upperLeft.getY());
        Geometry.Point bottomLeft = new Geometry.Point(upperLeft.getX(), upperLeft.getY() + height);
        Geometry.Point bottomRight = new Geometry.Point(upperLeft.getX() + width, upperLeft.getY() + height);
        lineList = new ArrayList<>();
        this.lineList.add(new Geometry.Line(this.upperLeft, upperRight));
        this.lineList.add(new Geometry.Line(bottomLeft, bottomRight));
        this.lineList.add(new Geometry.Line(this.upperLeft, bottomLeft));
        this.lineList.add(new Geometry.Line(upperRight, bottomRight));
    }
     */

    /**
     * Construct a rectangle given an upper left point, width, height and a color.
     *
     * @param upperLeft the upper left point
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, java.awt.Color color) {
        this.height = height;
        this.width = width;
        this.upperLeft = upperLeft;
        this.color = color;
        //create the corner points and from them create a side list and put them is a line list and the correct field
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point bottomLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point bottomRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        //create the list and insert every line to it
        lineList = new ArrayList<>();
        this.lineList.add(new Line(this.upperLeft, upperRight));
        this.lineList.add(new Line(bottomLeft, bottomRight));
        this.lineList.add(new Line(this.upperLeft, bottomLeft));
        this.lineList.add(new Line(upperRight, bottomRight));
    }

    /**
     * returns a List of intersection points of a given line with the rectangle (could be with only null points).
     * @param line the given line to check intersection points with
     * @return the intersection point list.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> points = new ArrayList<>();
        for (Line l : this.lineList) {
                points.add(line.intersectionWith(l));
        }
            return points;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the Color of the rectangle.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * @return side lines list of the rectangle as an array.
     */
    public Line[] getLineArray() {
        Line[] lineArray = new Line[4];
        return lineList.toArray(lineArray);
    }

    /**
     * draw the rectangle of the given surface.
     * @param d the draw surface to draw on
     */
    public void drawOn(DrawSurface d) {
        //set the rectangle's color and fill the inside of it
        d.setColor(this.color);
        d.fillRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY()
                , (int) this.getWidth(), (int) this.getHeight());
        //set the color to black and draw the outline of the rectangle
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.getUpperLeft().getX(), (int) this.getUpperLeft().getY()
                , (int) this.getWidth(), (int) this.getHeight());
    }
}