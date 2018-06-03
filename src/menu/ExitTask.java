package menu;

/**
 * The class ExitTask - simple task which exit from the program.
 * @author Michael Shachar and Hila Zecharia. */
public class ExitTask implements Task<Void> {

    /**
     * The function run - Run an exit task.
     * @return null. */
    public Void run() {
       System.exit(1);;
       return null;
    }
 }
