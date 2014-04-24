import springies.Springies;

/**
 * The main class that starts Springies
 * @author Team15
 *
 */
final class Main {

    private Main() {
    }
    /**
     * Global main method. Calls Springies to
     * initialize the simulation.
     * @param args Command-line arguments.
     */
    public static void main (String[] args) {
        new Springies();
    }
}
