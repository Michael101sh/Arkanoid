package mainpackage;

/**
 * The class Counter - simple class that is used for counting things.
 * @author Michael Shachar and Hila Zecharia. */
public class Counter {
    private int value;

    /**
     * Constructor Counter.
     * @param value - the starting value of the counter. */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * The function increase - add number to current count.
     * @param number - the number which we increase with. */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * The function decrease - subtract number from current count.
     * * @param number - the number which we decrease with. */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * The function getValue -  get current count.
     * @return the current value of the counter. */
    public int getValue() {
        return this.value;
    }
}