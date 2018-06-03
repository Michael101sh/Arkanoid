package geoshapes;

/**
 * The class Line - define a line segment, calculate it's length, middle,
 * check if two lines segments are intersecting, calculate intersection
 *  point, check if two lines are equals, and find the closest
 *  intersection point to start of line.
 * @author Michael Shachar and Hila Zecharia. */
public class Line {
    private Point start, end;

    /**
     * constructor nu.1 - Constructing a line with two points.
     * @param start - the start point of the line segment.
     * @param end - the end point of the line segment. */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor nu.2 - Constructing a line with two x,y coordinate.
     * @param x1 - x coordinate of the start point.
     * @param y1 - y coordinate of the start point.
     * @param x2 - x coordinate of the end point.
     * @param y2 - y coordinate of the end point. */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * The function length - calculate the length of line segment.
     * @return the line's length. */
    public double length() {
        double lineLength = this.start.distance(this.end);
        return lineLength;
    }

    /**
     * The function start.
     * @return the start point of the line. */
    public Point start() {
        return this.start;
    }

    /**
     * The function end.
     * @return the end point of the line. */
    public Point end() {
        return this.end;
    }

    /**
     * The function middle.
     * @return the middle point of the line. */
    public Point middle() {
        double x1, y1, x2, y2, middleX, middleY;
        x1 = this.start.getX();
        y1 = this.start.getY();
        x2 = this.end.getX();
        y2 = this.end.getY();
        middleX = (x1 + x2) / 2;
        middleY = (y1 + y2) / 2;
        Point middle = new Point(middleX, middleY);
        return middle;
    }

    /**
     * The function isIntersecting - check if two lines are intersecting .
     * @param other - the second line.
     * @return boolean value: True if the two line segments are intersect
     *  false otherwise. */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) == null) {
            return false;
        }
        return true;
    }

    /**
     * The function intersectionWith - calculate intersection point of
     *  two line segments.
     * @param other - the second line.
     * @return interPoint the intersection point if there is,
     *  otherwise return null. */
    public Point intersectionWith(Line other) {
       double line1X1 = this.start.getX();
       double line1Y1 = this.start.getY();
       double line1X2 = this.end.getX();
       double line1Y2 = this.end.getY();
       double line2X1 = other.start.getX();
       double line2Y1 = other.start.getY();
       double line2X2 = other.end.getX();
       double line2Y2 = other.end.getY();
       double interMonom = (line1X1 * line1Y2 - line1Y1 * line1X2)
               * (line2X1 - line2X2) - (line1X1 - line1X2)
               * (line2X1 * line2Y2 - line2Y1 * line2X2);
         double interDenom = (line1X1 - line1X2) * (line2Y1 - line2Y2)
               - (line1Y1 - line1Y2) * (line2X1 - line2X2);
       if (interDenom == 0) {
           return null; // the line parallel or converge.
       }
       double interX = interMonom / interDenom;
       interMonom = (line1X1 * line1Y2 - line1Y1 * line1X2)
               * (line2Y1 - line2Y2)
               - (line2X1 * line2Y2 - line2Y1 * line2X2) * (line1Y1 - line1Y2);
       double interY = interMonom / interDenom;
       // check if the the intersection point is in the segment.
       if (interX >= Math.min(line1X1, line1X2)
               && interX <= Math.max(line1X1, line1X2)
               && interX >= Math.min(line2X1, line2X2)
               && interX <= Math.max(line2X1, line2X2)
               && interY >= Math.min(line1Y1, line1Y2)
               && interY <= Math.max(line1Y1, line1Y2)
               && interY >= Math.min(line2Y1, line2Y2)
               && interY <= Math.max(line2Y1, line2Y2)) {
           return new Point(interX, interY);
       }
       return null;
    }

    /**
     * The function equals - check if two lines segments are equals .
     * @param other - the second number.
     * @return boolean value: true if the two lines segments are equals
     *  false otherwise. */
    public boolean equals(Line other) {
        Point p1, p2, p3, p4;
        p1 = this.start;
        p2 = this.end;
        p3 = other.start;
        p4 = other.end;
        if (p1.equals(p3) && p2.equals(p4) || p1.equals(p4) && p2.equals(p4)) {
            return true;
        }
        return false;
    }

    /**
     * The function closestIntersectionToStartOfLine - find the closest
     * intersection point to start of line.
     * @param rect - the rectangle which we collision with.
     * @return Point - the closest point, if there is. */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersectionPointsList;
        intersectionPointsList = rect.intersectionPoints(this);
        if (intersectionPointsList.size() == 0) {
            return null;
        } else if (intersectionPointsList.size() == 1) {
            return intersectionPointsList.get(0);
        } else {
            double distance1, distance2;
            distance1 = this.start.distance(intersectionPointsList.get(0));
            distance2 = this.start.distance(intersectionPointsList.get(1));
            if (distance1 < distance2) {
                return intersectionPointsList.get(0);
            }
            return intersectionPointsList.get(1);
        }
    }
}
