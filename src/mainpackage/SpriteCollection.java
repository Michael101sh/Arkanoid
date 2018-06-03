package mainpackage;

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * The class SpriteCollection - define a list of sprites,
 * notify all of them that time has passed and draw them all.
 * moving the ball forward, and add the ball to the game.
 * @author Michael Shachar and Hila Zecharia. */
public class SpriteCollection {
    private List<Sprite> spriteCollection;

    /**
     * Constructor SpriteCollection - create new array list of sprites. */
    public SpriteCollection() {
        this.spriteCollection = new ArrayList<Sprite>();
    }
    /**
     * The function addSprite - add sprite to the sprites list.
     * @param s - the sprite which we add. */
    public void addSprite(Sprite s) {
         this.spriteCollection.add(s);
    }

    /**
     * The function removeSprite - remove sprite from the sprites list.
     * @param s - the sprite which we removeremoveSprite. */
    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);
    }

    /**
     * The function notifyAllTimePassed - notify all of the sprites that
     *  time has passed. Call timePassed() on all the sprites.
     * @param dt - . */
    public void notifyAllTimePassed(double dt) {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spriteCollection);
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed(dt);
        }
        return;
    }

    /**
     * The function drawAllOn -draw all of the sprites.
     *  call drawOn() on all sprites.
     *  @param d - the draw surface. */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spriteCollection);
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).drawOn(d);
        }
    }
}