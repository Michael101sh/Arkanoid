package animation;

import java.awt.Color;
import mainpackage.Counter;
import biuoop.DrawSurface;

/**
 * The class EndScreen - an animation of end screen.
 * @author Michael Shachar and Hila Zecharia. */
public class EndScreen implements Animation {
    private Counter score;
    private boolean messageFlag;
    private boolean stop;

    /**
     * Constructor EndScreen.
     * @param score - the final score.
     * @param messageFlag - flag we indicates whether
     *  the player win or lose.  */
    public EndScreen(Counter score,
            boolean messageFlag) {
        this.score = score;
        this.messageFlag = messageFlag;
        this.stop = false;
    }

    /**
     * The function doOneFrame - do one frame of the level -
     *  the frame of the summary.
     * @param dt - .
     * @param d - the surface which we draw on. */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.decode("#4c8bff"));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.decode("#cc3333"));
        d.fillRectangle(70, 100, 650, 380);

        if (!messageFlag) { // You lose
            d.setColor(Color.decode("#f4da70"));
            d.drawText(300, d.getHeight() * 6 / 15, "You Lost.", 32);
            d.drawText(280, d.getHeight() * 9 / 15,
                    "Your score is " + String.valueOf(this.score.getValue()
                            + "."),
                    32);
        } else { // You win
            d.setColor(Color.BLUE);
            d.drawText(320, d.getHeight() * 6 / 15, "You Won!", 32);
            d.drawText(280, d.getHeight() * 9 / 15,
                    "Your score is " + String.valueOf(this.score.getValue())
                    + ".",
                    32);
        }
        d.setColor(Color.RED);
        d.drawText(245, d.getHeight() * 14 / 16, "press space to continue", 32);
    }

    /**
     * The function setStop - set the stoppable condition to the given value.
     * @param newsStop - the given stop. */
    public void setStop(boolean newsStop) {
        this.stop = newsStop;
    }

    /**
     * The function shouldStop - return boolean variable which
     * indicate when we should stop the animation. Here is to tell
     * that we should to terminate the GUI.
     *  @return boolean variable.*/
    public boolean shouldStop() {
        return this.stop;
    }
}