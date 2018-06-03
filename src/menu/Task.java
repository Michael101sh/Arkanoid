package menu;

/**
 * The interface Task -  A task is something that needs to happen,
 * or something that we can run() and return a value.
 * @param <T> - generics interface.
 * @author Michael Shachar and Hila Zecharia. */
public interface Task<T> {
    /**
     * The function run - Run a task.
     * @return T. */
    T run();
 }
