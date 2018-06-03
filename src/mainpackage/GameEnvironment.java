package mainpackage;

import geoshapes.Line;
import geoshapes.Point;
import geoshapes.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * The class GameEnvironment a collection of objects a Ball can collide with.
 * The ball will know the game environment, and will use it to check for
 * collisions and direct its movement.
 * @author Michael Shachar and Hila Zecharia. */
public class GameEnvironment {
    private List<Collidable> gameEnvironment;

    /**
     * Constructor GameEnvironment - create a list of collidables. */
    public GameEnvironment() {
        this.gameEnvironment = new ArrayList<Collidable>();
    }

    /**
     * The function addCollidable - add the given collidable to the
     *  environment.
     * @param c - the collidable which we want to add. */
    public void addCollidable(Collidable c) {
        this.gameEnvironment.add(c);
    }

    /**
     * The function removeCollidable - remove the given collidable from the
     *  environment.
     * @param c - the collidable which we want to remove. */
    public void removeCollidable(Collidable c) {
        this.gameEnvironment.remove(c);
    }

    /**
     * The function getClosestCollision - Assume an object moving from
     *  line.start() to line.end(). If this object will not collide with
     *  any of the collidables in this collection, return null.
     *  Else, return the information about the closest collision that
     *  is going to occur.
     * @param trajectory - the trajectory of the ball.
     * @return CollisionInfo - the collision info.*/
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestCollid = null;
        Rectangle rect = null;
        Point closestCollisionPoint = null;
        double minDistance = 800;
        List<Collidable> environment = new ArrayList<Collidable>(
                this.gameEnvironment);
        for (int i = 0; i < environment.size(); i++) {
            if (closestCollisionPoint == null) {
                closestCollid = environment.get(i);
                rect = closestCollid.getCollisionRectangle();
                closestCollisionPoint = trajectory
                        .closestIntersectionToStartOfLine(rect);
                if (closestCollisionPoint != null) {
                    minDistance = trajectory.start()
                            .distance(closestCollisionPoint);
                }
            } else {
                Collidable nextCollid = environment.get(i);
                rect = nextCollid.getCollisionRectangle();
                Point nextCollisionPoint = trajectory
                        .closestIntersectionToStartOfLine(rect);
                if (nextCollisionPoint != null) {
                    double nextDistance = trajectory.start()
                            .distance(nextCollisionPoint);
                    if (nextDistance < minDistance) {
                        closestCollisionPoint = nextCollisionPoint;
                        closestCollid = nextCollid;
                    }
                }
            }
        }
        CollisionInfo collisionInfo =
                new CollisionInfo(closestCollisionPoint, closestCollid);
        return collisionInfo;
    }
}