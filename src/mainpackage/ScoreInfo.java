package mainpackage;

import java.io.Serializable;

/**
 * The class ScoreInfo - define the info of player score.
 * @author Michael Shachar and Hila Zecharia. */
public class ScoreInfo implements Serializable {

    /**
     * Constructor ScoreInfo.
     * Initialize to default values
     * @param name - player name.
     * @param score - player score. */
    private static final long serialVersionUID = 2726813638627735225L;
    private String name;
    private int score;

    /**
     * Constructor ScoreInfo.
     * @param name - .
     * @param score - . */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * The function getName.
     * @return the player's name */
    public String getName() {
        return this.name;
    }

    /**
     * The function getScore.
     * @return the player's score */
    public int getScore() {
        return this.score;
    }
 }