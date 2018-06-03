package mainpackage;

import geoshapes.Point;
import geoshapes.Rectangle;

/**
 * The interface Collidable -  will be used by things that can be collided with.
 * In this assignment, this means the Blocks and the Paddle.
 * @author Michael Shachar and Hila Zecharia. */
public interface Collidable {
    /**
     * The function getCollisionRectangle.
     * @return Return the "collision shape" of the object. */
    Rectangle getCollisionRectangle();

    /**
     * The function hit - Notify the object that we collided with
     *  it at collisionPoint with a given velocity.
     *  The return is the new velocity expected after the hit
     *  (based on the force the object inflicted on us).
     * @param collisionPoint - the collision point.
     * @param currentVelocity - the current velocity of the ball.
     * @param hitter - the Ball that's doing the hitting.
     * @return Return the new velocity. */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * The function addToGame - add a collidable to the game.
     * @param g - the game. */
    void addToGame(GameLevel g);
}
