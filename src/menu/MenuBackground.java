package menu;

import java.awt.Color;
import java.util.List;
import mainpackage.GameLevel;
import mainpackage.Sprite;
import biuoop.DrawSurface;

/**
 * The class MenuBackground - the background of menus.
 * @param <T> - generic class.
 * @author Michael Shachar and Hila Zecharia. */
public class MenuBackground<T> implements Sprite {
    private String menuTitle;
    private List<Selection<T>> selectionList;

    /**
     * Constructor MenuBackground.
     * @param menuTitle - .
     * @param selectionList - . */
    public MenuBackground(String menuTitle, List<Selection<T>> selectionList) {
        this.menuTitle = menuTitle;
        this.selectionList = selectionList;
    }

    /**
     * The function drawOn - draw the background on the given surface drawing.
     * @param d - the surface which we drawing on. */
    public void drawOn(DrawSurface d) {
        // the screen background
        d.setColor(Color.decode("#a8a8a8"));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.yellow);
        d.drawText(80, 80, this.menuTitle , 32);
        d.setColor(Color.decode("#00ff00"));
        int y = 150;
        for (int i = 0; i < this.selectionList.size(); i++) {
            d.drawText(135, y, "(" + this.selectionList.get(i).getKey()
                    + ")" + " " + this.selectionList.get(i).getMessage() , 30);
            y += 40;
        }
    }

    /**
     * The function timePassed - notify the sprite that time has passed.
     * @param dt - . */
    public void timePassed(double dt) {
    }

    /**
     * The function addToGame - add a sprite to the GameLevel.
     @param g - the GameLevel. */
    public void addToGame(GameLevel g) {

    }
}
