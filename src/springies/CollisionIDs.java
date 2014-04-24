package springies;

/**
 * Class defining collision IDs for
 * objects in the simulation.
 * @author Team15
 *
 */
public class CollisionIDs {
    
    /**
     * Collision ID for masses.
     */
    public static final int MASS_ID = 1;
    /**
     * Collision ID for fixed masses.
     */
    public static final int FIXED_MASS_ID = 2;
    /**
     * Collision ID for walls.
     */
    public static final int WALL_ID = 3;
    /**
     * Collision ID for springs.
     */
    public static final int SPRING_ID = 4;
    /**
     * Collision ID for muscles.
     */
    public static final int MUSCLE_ID = 4;
    
    private CollisionIDs() {
        // Prevent instantiation
        // Optional: throw an exception e.g. AssertionError
        // if this ever *is* called
    }

}
