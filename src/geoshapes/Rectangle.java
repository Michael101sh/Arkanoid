package geoshapes;

import java.util.ArrayList;

/**
 * The class Rectangle - define a Rectangle and calculate intersection points
 * with a rectangle and line.
 * @author Michael Shachar and Hila Zecharia. */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor Rectangle.
     * @param upperLeft - the upper left point of the rectangle.
     * @param width - the rectangle width.
     * @param height - the rectangle height.*/
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
    }

    /**
     * The function getWidth.
     * @return the rectangle width */
    public double getWidth() {
        return this.width;
    }

    /**
     * The function getHeight.
     * @return the rectangle height */
    public double getHeight() {
        return this.height;
    }

    /**
     * The function getUpperLeft.
     * @return the upper left point */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * The function getUpperRightPoint.
     * @return the upper right point */
    public Point getUpperRightPoint() {
        double x, y;
        x = this.upperLeft.getX() + this.width;
        y = upperLeft.getY();
        return new Point(x, y);
    }

    /**
     * The function getLowerRightPoint.
     * @return the lower right point */
    public Point getLowerRightPoint() {
        double x, y;
        x = this.upperLeft.getX() + this.width;
        y = this.upperLeft.getY() + this.height;
        return new Point(x, y);
    }

    /**
     * The function getLowerLeftPoint.
     * @return the lower left point */
    public Point getLowerLeftPoint() {
        double x, y;
        x = this.upperLeft.getX();
        y = this.upperLeft.getY() + this.height;
        return new Point(x, y);
    }

    /**
     * The function getUpperLine.
     * @return the upper line */
    public Line getUpperLine() {
        return new Line(this.upperLeft, getUpperRightPoint());
    }

    /**
     * The function getLeftLine.
     * @return the left line */
    public Line getLeftLine() {
        return new Line(this.upperLeft, getLowerLeftPoint());
    }

    /**
     * The function getLowerLine.
     * @return the lower line */
    public Line getLowerLine() {
        return new Line(getLowerLeftPoint(), getLowerRightPoint());
    }

    /**
     * The function getRightLine.
     * @return the right line */
    public Line getRightLine() {
        return new Line(getUpperRightPoint(), getLowerRightPoint());
    }

    /**
     * The function intersectionPoints - calculate intersection points
     * with a rectangle and line.
     * @param line - the collide line.
     * @return list of the intersection points. */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionPointsList = new ArrayList<Point>();
        Point[] interPoint = new Point[4];
        interPoint[0] = getUpperLine().intersectionWith(line);
        interPoint[1] = getLeftLine().intersectionWith(line);
        interPoint[2] = getLowerLine().intersectionWith(line);
        interPoint[3] = getRightLine().intersectionWith(line);
        for (int i = 0; i < 4; i++) {
            if (interPoint[i] != null) {
                intersectionPointsList.add(interPoint[i]);
            }
        }
        return intersectionPointsList;
    }
}