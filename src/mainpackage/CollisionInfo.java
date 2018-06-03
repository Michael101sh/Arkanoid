package mainpackage;

import geoshapes.Point;

/**
 * The class CollisionInfo - define new type of object which
 * composed from collision point and collision object.
 * @author Michael Shachar and Hila Zecharia. */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Constructor CollisionInfo.
     * @param collisionPoint - collision point on a collidable.
     * @param collisionObject - collision object which we collide with. */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * The function collisionPoint - get the point at
     *  which the collision occurs.
     * @return the collision point */
   public Point collisionPoint() {
       return this.collisionPoint;
   }

   /**
    * The function collisionObject - get the collidable
    * object involved in the collision.
    * @return the collision object */
    public Collidable collisionObject() {
       return this.collisionObject;
   }
}