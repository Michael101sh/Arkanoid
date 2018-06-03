package mainpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * The class HighScoresTable - define high score table.
 * @author Michael Shachar and Hila Zecharia. */
public class HighScoresTable implements Serializable {
    private static final long serialVersionUID = 2072088943978021606L;
    private int size;
    private List<ScoreInfo> highScoresTable;

    /**
     * Constructor HighScoresTable.
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     * @param size - .*/
    public HighScoresTable(int size) {
        this.size = size;
        this.highScoresTable = new LinkedList<ScoreInfo>();
    }

    /**
     * The function getSymbol - Add a high-score.
     * @param score - . */
    public void add(ScoreInfo score) {
        int newScore = score.getScore();
        this.highScoresTable.add(this.getRank(newScore) - 1, score);
        // there is deviation in the number of elements,
        // so we remove the last element.
        if (this.highScoresTable.size() == this.size + 1) {
            this.highScoresTable.remove(this.size);
        }
    }

    /**
     * The function size.
     * @return the table size. */
    public int size() {
        return this.size;
    }

    /**
     * The function getHighScores.
     * @return  Return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     * Return the current high scores.
     * The list is sorted such that the highest scores come first */
    public List<ScoreInfo> getHighScores() {
        return this.highScoresTable;
    }

    /**
    * The function getRank.
    * @return return the rank of the current score: where will it
    * be on the list if added?
    * Rank 1 means the score will be highest on the list.
    * Rank `size` means the score will be lowest.
    * Rank > `size` means the score is too low and will not
    * be added to the list.
    * @param score - . */
    public int getRank(int score) {
        // The high scores list is empty or the element big than the first
        // element on the list.
        if (this.highScoresTable.isEmpty()
                || score > this.highScoresTable.get(0).getScore()) {
            return 1;
        }

        // in the middle of the list.
        for (int i = 1; i < this.highScoresTable.size(); i++) {
            ScoreInfo scoreInfo = this.highScoresTable.get(i);
            if (score > scoreInfo.getScore()) {
                return i + 1;
            }
        }
        // Big than the last element.
        ScoreInfo scoreInfo = this.highScoresTable
                .get(this.highScoresTable.size() - 1);
        if (score > scoreInfo.getScore()) {
            return this.size;
        }
        // if it is not big than any element but we still have
        // free space in the table, just add the element to the
        // end of the list.
        if (this.highScoresTable.size() < this.size) {
            return this.highScoresTable.size() + 1;
        }
        //the score is too low and will not
        // be added to the list.
        return this.size + 1;
    }

    /**
     * The function clear - Clears the table. */
    public void clear() {
        this.highScoresTable.clear();
    }

    /**
     * The function load - Load table data from file.
     * @param filename - the file we load from.
     * @throws IOException - . */
    public void load(File filename) throws IOException {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(filename));
            HighScoresTable hst = (HighScoresTable) objectInputStream
                    .readObject();
            this.highScoresTable = hst.getHighScores();
            this.size = hst.size();
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            return;
        } catch (ClassNotFoundException e) { // The class in the stream is
                                             // unknown to the JVM
            System.err.println("Unable to find class for object in file: "
                    + filename);
            return;
        } catch (IOException e) { // Some other problem
            System.err.println("Failed read from file:" + filename);
            e.printStackTrace(System.err);
            return;
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * The function save - Save table data to the specified file.
     * @param filename - the file we save to.
     * @throws IOException - . */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        // create output stream with writer wrappers
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(
                    filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving to file" + filename);
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * The function loadFromFile - Read a table from file and return it.
     *  If the file does not exist, or there is a problem with
     *  reading it, an empty table is returned.
     * @param filename - the file e load from.
     * @return HighScoresTable. */
    public static HighScoresTable loadFromFile(File filename) {
        int size = 0;
        HighScoresTable highScoresTable = new HighScoresTable(size);
        try {
            highScoresTable.load(filename);
        } catch (IOException e) {
            System.err.println("Failed reading from file:" + filename);
        }
        return highScoresTable;
    }
}