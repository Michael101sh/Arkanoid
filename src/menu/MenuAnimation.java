package menu;
import java.util.ArrayList;
import java.util.List;
import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The interface MenuAnimation - the menu animation.
 * @param <T> - generic class.
 * @author Michael Shachar and Hila Zecharia. */
public class MenuAnimation<T> implements Menu<T> {
    private String menuTitle;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private List<Selection<T>> selectionList;
    private List<Menu<T>> subMenuList;
    private List<Boolean> isOption;
    private T status;
    private boolean stop;

    /**
     * Constructor BlockMenuAnimationRemover.
     * @param menuTitle - .
     * @param runner - .
     * @param keyboard - . */
    public MenuAnimation(String menuTitle, AnimationRunner runner,
            KeyboardSensor keyboard) {
        this.menuTitle = menuTitle;
        this.runner = runner;
        this.keyboard = keyboard;
        this.selectionList = new ArrayList<Selection<T>>();
        this.subMenuList = new ArrayList<Menu<T>>();
        this.isOption = new ArrayList<Boolean>();
        this.stop = false;
    }

    /**
     * The function addSelection - add selection.
     * @param key - key to wait for.
     * @param message - line to print.
     * @param returnVal - what to return. */
    public void addSelection(String key, String message, T returnVal) {
        Selection<T> selection = new Selection<T>(key, message, returnVal);
        this.selectionList.add(selection);
        this.isOption.add(true);
        this.subMenuList.add(null);
    }

    /**
     * The function getStatus - return the status.
     * @return T. */
    public T getStatus() {
        return this.status;
    }

    /**
     * The function doOneFrame - do one frame of the animation.
     * @param d - the surface which we draw on.
     * @param dt -  */
    public void doOneFrame(DrawSurface d, double dt) {
        MenuBackground<T> menuBackground = new MenuBackground<T>(
                this.menuTitle, this.selectionList);
        menuBackground.drawOn(d);
        for (int i = 0; i < this.selectionList.size(); i++) {
            if (this.keyboard.isPressed(this.selectionList.get(i).getKey())) {
                if (this.isOption.get(i)) {
                    this.status = this.selectionList.get(i).getReturnVal();
                    this.stop = true;
                } else {
                    this.runner.run(this.subMenuList.get(i));
                    this.status = this.subMenuList.get(i).getStatus();
                    this.subMenuList.get(i).reset();
                    this.stop = true;
                    break;
                }
            }
        }
    }

    /**
     * The function setStop - set the stoppable condition to the given value.
     * @param newStop - the given stop. */
    public void setStop(boolean newStop) {

    }


    /**
     * The function shouldStop - return boolean variable which
     * indicate when we should stop the animation.
     *  @return boolean variable.*/
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * The function reset - reset the situation of the menu. */
    public void reset() {
        this.stop = false;
        this.status = null;
    }

    /**
     * The function addSubMenu.
     * @param key - key to wait for.
     * @param message - line to print.
     * @param subMenu - sub menu - Menu object*/
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        Selection<T> selection = new Selection<T>(key, message, null);
        this.selectionList.add(selection);
        this.subMenuList.add(subMenu);
        this.isOption.add(false);
    }

}
