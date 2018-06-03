package menu;

import animation.Animation;

/**
 * The interface Menu - define the game menu.
 * @param <T> - generic interface.
 * @author Michael Shachar and Hila Zecharia. */
public interface Menu<T> extends Animation {
    /**
     * The function addSelection - add selection.
     * @param key - key to wait for.
     * @param message - line to print.
     * @param returnVal - what to return. */
    void addSelection(String key, String message, T returnVal);

    /**
     * The function getStatus.
     * @return the status. */
    T getStatus();

    /**
     * The function addSubMenu.
     * @param key - key to wait for.
     * @param message - line to print.
     * @param subMenu - sub menu - Menu object. */
    void addSubMenu(String key, String message, Menu<T> subMenu);
    /**
     * The function reset - reset the situation of the menu. */
    void reset();
 }
