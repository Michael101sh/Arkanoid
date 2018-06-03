package mainpackage;

import geoshapes.Point;
import geoshapes.Rectangle;
import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The class Paddle - define a paddle, moving it right and left, drawing it,
 * and change the velocity of the ball when it collide with it.
 * @author Michael Shachar and Hila Zecharia. */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Block block;
    private Color color;
    private double screenWidth;
    private double speed;

    /**
     * Constructor Paddle.
     * @param upperLeft - paddle upper left point.
     * @param width - paddle width.
     * @param height - paddle height.
     * @param color - paddle color.
     * @param keyboard .
     * @param screenWidth - the width of the screen
     * @param speed - the speed of the paddle. */
    public Paddle(Point upperLeft, double width, double height,
            Color color, KeyboardSensor keyboard, double screenWidth,
            int speed) {
        this.block = new Block(upperLeft, width, height);
        this.keyboard = keyboard;
        this.color = color;
        this.screenWidth = screenWidth;
        this.speed = speed;
       }

    /**
     * The function moveLeft - move the paddle left.
     * @param dt - . */
    public void moveLeft(double dt) {
        double localSpeed = this.speed * dt;
        Rectangle rect = this.block.getCollisionRectangle();
        Point upperLeft =  rect.getUpperLeft();
        double x = Math.round(upperLeft.getX());
        // check if we can still move the paddle left.
        if (x >= 32) {
            double y = Math.round(upperLeft.getY());
            double width = rect.getWidth();
            double height = rect.getHeight();
            upperLeft = new Point(x - localSpeed, y);
            this.block = new Block(upperLeft, width, height);
        }
    }
    /**
     * The function moveRight - move the paddle right.
     * @param dt - . */
    public void moveRight(double dt) {
        double localSpeed = this.speed * dt;
        Rectangle rect = this.block.getCollisionRectangle();
        Point upperLeft =  rect.getUpperLeft();
        double x = Math.round(upperLeft.getX());
        double width = rect.getWidth();
        // check if we can still move the paddle right.
        if (x + width < this.screenWidth - 35) {
            double y = Math.round(upperLeft.getY());
            double height = rect.getHeight();
            upperLeft = new Point(x + localSpeed, y);
            this.block = new Block(upperLeft, width, height);
        }
    }

    /**
     * The function timePassed - move the paddle.
     * call move left or right function as the user key choose.
     * @param dt - / */
    public void timePassed(double dt) {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }

   /**
    * The function drawOn - drawing the paddle.
    * call move left or right function as the user key choose
    * @param d - the draw surface. */
    public void drawOn(DrawSurface d) {
        Rectangle rect = this.block.getCollisionRectangle();
        Point upperLeft =  rect.getUpperLeft();
        double x = Math.round(upperLeft.getX());
        double y = Math.round(upperLeft.getY());
        double width = rect.getWidth();
        double height = rect.getHeight();
        d.setColor(this.color);
        d.fillRectangle((int) x, (int) y, (int) width, (int) height);
    }

   /**
    * The function getCollisionRectangle.
    * @return the rectangle that we collide with. */
    public Rectangle getCollisionRectangle() {
        return this.block.getCollisionRectangle();
       }

   /**
    * The function hit - change, when collision is occur,
    * a new velocity for the ball.
    * @param hitter - the Ball that's doing the hitting.
    * @param collisionPoint - the collision point.
    * @param currentVelocity - the current velocity of the ball.
    * @return the new velocity. */
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        double ballSpeed = currentVelocity.speed();
        Rectangle rect = this.block.getCollisionRectangle();
        Point upperLeft =  rect.getUpperLeft();
        double upperLeftX = Math.round(upperLeft.getX());
        double width = rect.getWidth();
        // we did rounded only the collision point because we assume
        // the values of the other points will be integers.
        double collisionPointX = Math.round(collisionPoint.getX());
        double upperLeftY = Math.round(upperLeft.getY());
        Velocity velo = null;
        if (Math.round(collisionPoint.getY()) == upperLeftY) {
            if (collisionPointX >= upperLeftX
                    && collisionPointX <= upperLeftX + (width / 5)) {
                velo = Velocity.fromAngleAndSpeed(300, ballSpeed);
            } else if (collisionPointX >= upperLeftX + (width / 5)
                    && collisionPointX <= upperLeftX + (width * 2 / 5)) {
                velo = Velocity.fromAngleAndSpeed(330, ballSpeed);
            } else if (collisionPointX >= upperLeftX + (width * 2 / 5)
                    && collisionPointX <= upperLeftX + (width * 3 / 5)) {
                velo = Velocity.fromAngleAndSpeed(360, ballSpeed);
            } else if (collisionPointX >= upperLeftX + (width * 3 / 5)
                    && collisionPointX <= upperLeftX + (width * 4 / 5)) {
                velo = Velocity.fromAngleAndSpeed(30, ballSpeed);
            } else if (collisionPointX >= upperLeftX + (width * 4 / 5)
                    && collisionPointX <= upperLeftX + width) {
                velo = Velocity.fromAngleAndSpeed(60, ballSpeed);
            }
            return velo;
        }
        return block.hit(hitter, collisionPoint, currentVelocity);
    }

    /**
     * The function addToGame - add the paddle to the game.
     *  @param g - the game.*/
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
