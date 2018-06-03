package mainpackage;

import geoshapes.Point;
import geoshapes.Rectangle;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import levelbuild.BlockCreator;
import listeners.HitListener;
import biuoop.DrawSurface;

/**
 * The class Block - define block, implements Collidable and Sprite interfaces
 * (see documentation there), and in addition add block to the game.
 * @author Michael Shachar and Hila Zecharia. */
public class Block implements Collidable, Sprite, HitNotifier, BlockCreator {
    private Rectangle rect;
    private Map<Integer, Color> colorPerHitPoints;
    private Map<Integer, Image> imagePerHitPoints;
    private Color defaultColor;
    private Color stroke;
    //private Image image;
    // left hits times we the ball can hit the block, until "X".
    private int leftHitsTimes;
    private List<HitListener> hitListeners;

    /**
     * Constructor Block nu.1.
     * @param upperLeft - the upper left point of the block.
     * @param width - the block width.
     * @param height - the block height. */
    public Block(Point upperLeft, double width, double height) {
        this.rect = new Rectangle(upperLeft, width, height);
        this.hitListeners = new ArrayList<HitListener>();
        this.colorPerHitPoints = new TreeMap<Integer, Color>();
        this.imagePerHitPoints = new TreeMap<Integer, Image>();
    }

    /**
     * Constructor Block nu.2.
     * @param upperLeft - the upper left point of the block.
     * @param width - the block width.
     * @param height - the block height.
     * @param color - the color of the block. */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.rect = new Rectangle(upperLeft, width, height);
        this.defaultColor = color;
        //this.image = null;
        this.colorPerHitPoints = new TreeMap<Integer, Color>();
        this.imagePerHitPoints = new TreeMap<Integer, Image>();
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Constructor Block nu.3.
     * @param upperLeft - the upper left point of the block.
     * @param width - the block width.
     * @param height - the block height.
     * @param color - the color of the block.
     * @param leftHitsTimes - left hits times we the ball can hit the block */
    public Block(Point upperLeft, double width, double height, Color color,
            int leftHitsTimes) {
        this.rect = new Rectangle(upperLeft, width, height);
        this.defaultColor = color;
        this.colorPerHitPoints = new TreeMap<Integer, Color>();
        this.imagePerHitPoints = new TreeMap<Integer, Image>();
        this.leftHitsTimes = leftHitsTimes;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Constructor Block nu.4.
     * @param upperLeft - the upper left point of the block.
     * @param width - the block width.
     * @param height - the block height.
     * @param generalColor - the color of the block.
     * @param leftHitsTimes - left hits times we the ball can hit the block
     * @param colorPerHitPoints - map of color per hit points.
     * @param imagePerHitPoints  - map of image per hit points. */
    public Block(Point upperLeft, double width, double height,
            Color generalColor, int leftHitsTimes,
            Map<Integer, Color> colorPerHitPoints,
            Map<Integer, Image> imagePerHitPoints) {
        this.rect = new Rectangle(upperLeft, width, height);
        this.defaultColor = generalColor;
        this.leftHitsTimes = leftHitsTimes;
        this.colorPerHitPoints = colorPerHitPoints;
        this.imagePerHitPoints = imagePerHitPoints;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * The function create - Create a block at the specified location.
     * @param newStroke - the stroke color of the block. */
    public void setStroke(Color newStroke) {
        this.stroke = newStroke;
    }

    /**
     * The function create - Create a block at the specified location.
     * @param xpos - .
     * @param ypos - .
     * @return Return the block. */
    public Block create(int xpos, int ypos) {
        Point upperLeft = new Point(xpos, ypos);
        Block block = new Block(upperLeft, this.rect.getWidth(),
                this.rect.getHeight(), this.defaultColor,
                this.leftHitsTimes, this.colorPerHitPoints,
                this.imagePerHitPoints);
        block.setStroke(this.stroke);
        return block;
    }

    /**
     * The function getWidth.
     * @return Return the width of the block. */
    public double getWidth() {
        return this.rect.getWidth();
    }

    /**
     * The function getCollisionRectangle.
     * @return the rectangle which we collide with */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * The function getLeftHitsTimes.
     * @return the left hits times we the ball can hit the block */
    public int getLeftHitsTimes() {
        return this.leftHitsTimes;
    }

    /**
     * The function addHitListener - add hl as a listener to hit events.
     * @param hl - listener object.*/
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * The function removeHitListener - Remove hl from the list of
     *  listeners to hit events.
     @param hl - listener object. */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * The function notifyHit - notifiers all of the registered HitListener
     *  objects that hit occurs.
     * @param hitter - the Ball that's doing the hitting. */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(
        this.hitListeners);
        // Notify all listeners about a hit event.
        for (HitListener hl : listeners) {
           hl.hitEvent(this, hitter);
        }
    }

    /**
     * The function hit - change, when collision is going to occur,
     * a new velocity for the ball, update the left hit times of the ball,
     * and call to notifyHit function.
     * @param hitter - the Ball that's doing the hitting.
     *  @param collisionPoint - the collision point.
     * @param currentVelocity - the current velocity of the ball.
     * @return Return the new velocity. */
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        if (this.leftHitsTimes > 0) {
            this.leftHitsTimes--;
        }
        Point upperLeft = this.rect.getUpperLeft();
        // if the ball is touching the right or left line of the rectangle,
        // change the direction to the opposite direction.
        Velocity velo = currentVelocity;
        // we didn't rounded the upper left point because we assume the values
        // will be integers.
        if (Math.round(collisionPoint.getX()) == upperLeft.getX()
                || Math.round(collisionPoint.getX()) == upperLeft.getX()
                + this.rect.getWidth()) {
            velo = new Velocity(-(velo.getDx()), velo.getDy());
         // if the ball is touching the upper or lower line of the rectangle,
         // change direction to the opposite direction.
        }
        if (Math.round(collisionPoint.getY()) == upperLeft.getY()
                || Math.round(collisionPoint.getY()) == upperLeft.getY()
                + this.rect.getHeight()) {
            velo = new Velocity(velo.getDx(), -(velo.getDy()));
        }
        // An hit occur, so we notify about it.
        this.notifyHit(hitter);
        return velo; // The new velocity.
    }

     /**
      * The function drawOn - draw the block.
      * @param surface - the surface which we draw on. */
    public void drawOn(DrawSurface surface) {
        Point upperLeft = this.rect.getUpperLeft();
        double x = Math.round(upperLeft.getX());
        double y = Math.round(upperLeft.getY());
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();
        /* check if there is a special color for specific hit points,
        if there is so we draw the suitable image, otherwise we draw
        the default color is exist. The same checks with images. */
        if (this.colorPerHitPoints.containsKey(this.leftHitsTimes)) {
            surface.setColor(this.colorPerHitPoints.get(this.leftHitsTimes));
            surface.fillRectangle((int) x, (int) y, (int) width, (int) height);
        } else if (this.defaultColor != null) {
            surface.setColor(this.defaultColor);
            surface.fillRectangle((int) x, (int) y, (int) width, (int) height);
        } else if (this.imagePerHitPoints.containsKey(this.leftHitsTimes)) {
            surface.drawImage((int) x, (int) y,
                    this.imagePerHitPoints.get(this.leftHitsTimes));
        } else {
            surface.drawImage((int) x, (int) y,
                            // -1 is the default image
                            this.imagePerHitPoints.get(-1));
        }

        // draw the stroke.
        if (this.stroke != null) {
            surface.setColor(this.stroke);
        }
        surface.drawRectangle((int) x, (int) y, (int) width, (int) height);
    }

    /**
     * The function timePassed - do nothing, at least currently.
     * @param dt -  */
    public void timePassed(double dt) {

     }

    /**
     * The function addToGame - adding this sprites and collidable
     *  to the game.
     *  @param g - the game.*/
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * The function removeFromGame - removing the sprites and collidables
     *  (one at a time) to the game.
     *  @param game - the game.*/
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}
