package menu;

/**
 * The class Selection - combines a number of items to one  object.
 * @param <T> - class with generics.
 * @author Michael Shachar and Hila Zecharia. */
public class Selection<T> {
    private String key;
    private String message;
    private T returnVal;

    /**
     * Constructor Selection.
     * @param key - key to wait for
     * @param message - line to print
     * @param returnVal - what to return */
    public Selection(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * The function getKey.
     * @return the key. */
    public String getKey() {
        return this.key;
    }

    /**
     * The function getMessage.
     * @return the message. */
    public String getMessage() {
        return this.message;
    }

    /**
     * The function getMgetReturnValessage.
     * @return the return val. */
    public T getReturnVal() {
        return this.returnVal;
    }
}
