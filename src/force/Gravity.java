package force;

import entity.Mass;


/**
 * Class that defines gravitational force
 * 
 * @author Team15
 * 
 */

public class Gravity extends Force {

    private static final double DIRECTION_FACTOR = 180;
    private double myDirection;
    private double myMagnitude;

    /**
     * Constructor for gravity that initializes direction and magnitude
     * @param direction of the gravitational force
     * @param magnitude of the gravitational force
     */

    public Gravity (double direction, double magnitude) {
        myDirection = direction;
        myMagnitude = magnitude;
    }

    /**
     * Empty constructor for Gravity that takes in no parameters
     */
    public Gravity () {
    }

    /**
     * Inherited method that sets the gravitational force on the given mass
     * @param mass that the force acts on
     */
    public void setForce (Mass mass) {
        if (isOn) {
            mass.setForce(Math.cos(Math.PI * myDirection / DIRECTION_FACTOR) * myMagnitude,
                          Math.sin(Math.PI * myDirection / DIRECTION_FACTOR) * myMagnitude);
        }
    }

    /**
     * Sets the direction and magnitude of the gravitational force 
     * @param direction of the gravitational force
     * @param magnitude of the gravitational force
     */
    public void setGravity (double direction, double magnitude) {
        myDirection = direction;
        myMagnitude = magnitude;
    }
}
