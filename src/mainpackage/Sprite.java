package mainpackage;

import biuoop.DrawSurface;

/**
 * The interface Sprite -  will be used by GameLevel object that can be
 * drawn to the screen.
 * @author Michael Shachar and Hila Zecharia. */
public interface Sprite {
   /**
     * The function drawOn - draw the sprites on the given surface drawing.
     * @param surface - the surface which we drawing on. */
    void drawOn(DrawSurface surface);

    /**
     * The function timePassed - notify the sprite that time has passed.
     * @param dt - .  */
    void timePassed(double dt);

    /**
    * The function addToGame - add a sprite to the GameLevel.
    @param g - the GameLevel. */
    void addToGame(GameLevel g);
}