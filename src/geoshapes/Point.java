package geoshapes;

/**
 * The class Point - define a point, calculate distance between two points
 * and check if two points equals.
 * @author Michael Shachar and Hila Zecharia. */
public class Point {
    private double x;
    private double y;

    /**
     * Constructing a point with given x and y coordinates.
     * @param x - he x coordinate.
     * @param y - the y coordinate. */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The function distance - return the distance between two points.
     * @param other - the second number.
     * @return the distance between the points */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * The function getX.
     * @return the x coordinate */
    public double getX() {
        return this.x;
    }

    /**
     * The function setX - setting an new value to the x coordinate.
     * @param xCord - the new coordinate. */
    public void setX(double xCord) {
        this.x = xCord;
    }
    /**
     * The function getY.
     * @return the y coordinate */
    public double getY() {
        return this.y;
    }

    /**
     * The function equals - compare between two points.
     * @param other - the second point.
     * @return boolean value - true if the two points are equals,
     * otherwise return false */
    public boolean equals(Point other) {
        if ((this.x == other.getX() && this.y == other.getY())
                || this.x == other.getY() && this.y == other.getX()) {
            return true;
        }
        return false;
    }
}