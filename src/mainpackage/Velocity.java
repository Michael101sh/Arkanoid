package mainpackage;

import geoshapes.Point;

/**
 * The class Velocity - define a velocity, moving from point to point,
 * calculate the ball speed (by it velocity) and converting
 * angle and speed values do dx and dy (change in position on the
 * `x` and the `y` axes).
 * @author Michael Shachar and Hila Zecharia. */
public class Velocity {
    private double dx, dy;

    /**
     * The constructor Velocity - constructing the velocity by dx ,dy
     * (change in position on the `x` and the `y` axes).
     * @param dx - change in position on the `x` axe.
     * @param dy - change in position on the `y` axe. */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
   }

    /**
     * The function applyToPoint - Take a point with position (x,y) and
     *  return a new point with position (x+dx, y+dy).
     * @param p - the point which from it we get on.
     * @param dt - .
     * @return newPosition - the new position point. */
    public Point applyToPoint(Point p, double dt) {
        Point newPosition = new Point(p.getX() + this.dx * dt, p.getY()
                + this.dy * dt);
        return newPosition;
    }

    /**
     * The function getDx.
     * @return the dx coordinate. */
    public double getDx() {
        return this.dx;
    }

    /**
     * The function getDy.
     * @return the dy coordinate. */
    public double getDy() {
        return this.dy;
    }

    /**
     * The function speed.
     * calculate the ball speed by it velocity.
     * @return speed. */
    public double speed() {
        return Math.sqrt((this.dx * this.dx) + (this.dy * this.dy));
    }

    /** The function fromAngleAndSpeed - calculate the dx,dy coordinate
     * by trigonometry.
     * @param angle - angle of the ball's moving direction .
     * @param speed - the ball's moving direction (distance moving).
     * @return velocity - in dx and dy values (change in position on the
    * `x` and the `y` axes). */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = Math.round(speed * (Math.sin(angle)));
        double dy = Math.round(-1 * speed * (Math.cos(angle)));
        return new Velocity(dx, dy);
    }
}