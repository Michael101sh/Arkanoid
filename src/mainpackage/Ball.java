package mainpackage;

import geoshapes.Line;
import geoshapes.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import listeners.HitListener;
import biuoop.DrawSurface;

/**
 * The class Ball - define a ball, drawing it, calculate "almost" point
 * (the point which almost the collision point, as we asked),
 * moving the ball forward, and add the ball to the game.
 * @author Michael Shachar and Hila Zecharia. */
public class Ball implements Sprite {
    private int radius; //the radius of the circle (ball)
    private Point ballCenter; // the centre point of the circle (ball)
    private Color color; //the color of the circle (ball)
    private Velocity velocity; //the speed movement of the circle (ball)
    private static final int DEFAULT_VALUE = 0;
    private GameEnvironment gameEnvironment;
    private List<HitListener> hitListeners;

    /**
     * Constructor Ball nu.1.
     * @param x - X coordinate.
     * @param y - Y coordinate.
     * @param radius - the radius of the ball.
     * @param color - the color of the ball.
     * @param gameEnvironment - the game environment. */
    public Ball(int x, int y, int radius, java.awt.Color color,
            GameEnvironment gameEnvironment) {
        this.ballCenter = new Point((int) x, (int) y);
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(DEFAULT_VALUE, DEFAULT_VALUE);
        this.gameEnvironment = gameEnvironment;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Constructor Ball nu.2.
     * @param ballCenter - the centre ball point.
     * @param radius - the radius of the ball .
     * @param color - the color of the ball.
     * @param gameEnvironment - the game environment. */
    public Ball(Point ballCenter, int radius, java.awt.Color color,
            GameEnvironment gameEnvironment) {
        this.ballCenter = new Point((int) ballCenter.getX(),
                                    (int) ballCenter.getY());
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(DEFAULT_VALUE, DEFAULT_VALUE);
        this.gameEnvironment = gameEnvironment;
        this.hitListeners = new ArrayList<HitListener>();

    }

    /**
     * Constructor Ball nu.3.
     * @param x - X coordinate.
     * @param y - Y coordinate.
     * @param radius - the radius of the ball.
     * @param color - the color of the ball.
     * @param width - the width limit border which the ball can move in.
     * @param height - the height limit border which the ball can move in.
     * @param gameEnvironment - the game environment. */
    public Ball(int x, int y, int radius, java.awt.Color color, int width,
                int height, GameEnvironment gameEnvironment) {
        this.ballCenter = new Point((int) x, (int) y);
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(DEFAULT_VALUE, DEFAULT_VALUE);
        this.gameEnvironment = gameEnvironment;
        this.hitListeners = new ArrayList<HitListener>();

    }

    /**
     * Constructor Ball nu.4.
     * @param ballCenter - the centre ball point.
     * @param radius - the radius of the ball.
     * @param color - the color of the ball.
     * @param width - the width limit border which the ball can move in.
     * @param height - the height limit border which the ball can move in.
     * @param gameEnvironment - the game environment. */
    public Ball(Point ballCenter, int radius, java.awt.Color color, int width,
                int height, GameEnvironment gameEnvironment) {
        this.ballCenter = new Point((int) ballCenter.getX(),
                                    (int) ballCenter.getY());
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(DEFAULT_VALUE, DEFAULT_VALUE);
        this.gameEnvironment = gameEnvironment;
        this.hitListeners = new ArrayList<HitListener>();

    }

    /**
     * The function getX.
     * @return the x coordinate. */
    public int getX() {
        return (int) this.ballCenter.getX();
    }

    /**
     * The function getY.
     * @return the y coordinate. */
    public int getY() {
        return (int) this.ballCenter.getY();
    }

    /**
     * The function drawOn - draw the ball on the given surface drawing.
     * @param surface  */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
       surface.drawCircle((int) this.ballCenter.getX(),
                (int) this.ballCenter.getY(), this.radius);
       surface.setColor(this.color);
       surface.fillCircle((int) this.ballCenter.getX(),
                (int) this.ballCenter.getY(), this.radius);
    }

    /**
     * The function setVelocity - set the ball's velocity .
     * @param velo - the velocity which we setting */
    public void setVelocity(Velocity velo) {
        this.velocity = velo;
    }

    /**
     * The function setVelocity - set the ball's velocity by dx and dy.
     * @param dx - the change in position on the 'x' axes.
     * @param dy - the change in position on the 'y' axes. */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * The function almostPoint - calculate "almost" point
     * (the point which almost the collision point, as we asked).
     * @param collisionPoint - the collision point with rectangle.
     * @param slope - the slope of the line.
     * @param b - b is the free number in the equation (of a straight Line).
     * @return the "almost" point */
    private Point almostPoint(Point collisionPoint, double slope, double b) {
        double x, y;
        Point almostPoint;
        if ((this.velocity.getDx() > 0 && this.velocity.getDy() > 0)
               || (this.velocity.getDx() > 0 && this.velocity.getDy() < 0)) {
            x = (Math.round(collisionPoint.getX()) - 1);
            y = Math.round((slope * x) + b);
            almostPoint = new Point(x, y);
        } else if ((this.velocity.getDx() < 0 && this.velocity.getDy() > 0)
                || this.velocity.getDx() < 0 && this.velocity.getDy() < 0) {
            x = (Math.round(collisionPoint.getX()) + 1);
            y = Math.round((slope * x) + b);
            almostPoint = new Point(x, y);
        } else if (this.velocity.getDy() > 0) {
            x = (Math.round(collisionPoint.getX()));
            y = (Math.round(collisionPoint.getY()) - 1);
            almostPoint = new Point(x, y);
        } else {
            x = (Math.round(collisionPoint.getX()));
            y = (Math.round(collisionPoint.getY()) + 1);
            almostPoint = new Point(x, y);
        }
       return almostPoint;
   }

    /**
     * The function moveOneStep - moving the ball forward, include tending
     *  in reaching to blocks.
     *  @param dt - . */
    public void moveOneStep(double dt) {
        Point startOfTrajectory = new Point(Math.round(this.ballCenter.getX()),
                Math.round(this.ballCenter.getY()));
        Point nextPoint = this.velocity.applyToPoint(this.ballCenter, dt);
        Point endOfTrajectory = new Point(Math.round(nextPoint.getX()),
                Math.round(nextPoint.getY()));
        Line trajectory = new Line(startOfTrajectory, endOfTrajectory);
        CollisionInfo collisionInfo =
                this.gameEnvironment.getClosestCollision(trajectory);
        Point collisionPoint = collisionInfo.collisionPoint();
        if (collisionPoint == null) {
            this.ballCenter = endOfTrajectory;
            return;
        }
        for (int i = 0; i < 50 && collisionPoint != null; i++) {
            double slope =
                    ((endOfTrajectory.getY() - startOfTrajectory.getY())
                      / (endOfTrajectory.getX() - startOfTrajectory.getX()));
            double b = startOfTrajectory.getY()
                    - (slope * endOfTrajectory.getX());
            collisionPoint = new Point(Math.round(collisionPoint.getX()),
                    Math.round(collisionPoint.getY()));
            Point almostPoint = almostPoint(collisionPoint, slope, b);
            this.ballCenter = almostPoint;
            Collidable collisionObject = collisionInfo.collisionObject();
            this.velocity = collisionObject.hit(this, collisionPoint,
                    this.velocity);
            startOfTrajectory = new Point(Math.round(this.ballCenter.getX()),
                    Math.round(this.ballCenter.getY()));
            nextPoint = this.velocity.applyToPoint(this.ballCenter, dt);
            endOfTrajectory = new Point(Math.round(nextPoint.getX()),
                    Math.round(nextPoint.getY()));
            trajectory = new Line(startOfTrajectory, endOfTrajectory);
            collisionInfo = this.gameEnvironment.
                    getClosestCollision(trajectory);
            collisionPoint = collisionInfo.collisionPoint();
        }
        this.ballCenter = this.velocity.applyToPoint(this.ballCenter, dt);
    }

    /**
     * The function timePassed - call the function moveOneStep.
     * @param dt - .*/
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * The function addToGame - adding the ball to the game.
     * @param g - the game. */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * The function removeFromGame - remove the ball from the game.
     * @param g - the game. */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * The function removeHitListener - remove the listener of the ball
     * from the listeners list.
     * @param hl - the listener. */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}