package force;

import entity.Mass;


/**
 * Class defining the center of mass force
 * 
 * @author Team15
 * 
 */
public class CenterOfMass extends Force {
    private static final double AMPLITUDE_FACTOR = 0.01;
    private double myX;
    private double myY;
    private double myMagnitude;
    private double myExponent;

    /**
     * Empty Constructor for CenterOfMass that takes in no parameters
     */
    public CenterOfMass () {
    }

    /**
     * Sets the amount of COM force on a given mass
     * 
     * @param mass given to calculate COM
     */
    public void setForce (Mass mass) {
        if (isOn) {
            if (myExponent == 0) {
                return;
            }
            double diff = Math.sqrt(Math.pow(myX - mass.x, 2) + Math.pow(myY - mass.y, 2));
            double amplitude = Math.abs(Math.pow(diff, -myExponent)) 
                 * myMagnitude * AMPLITUDE_FACTOR;

            double xForce = (myX - mass.x) * amplitude / diff;
            double yForce = (myY - mass.y) * amplitude / diff;

            mass.setForce(xForce, yForce);
        }
    }

    /**
     * Method that sets the magnitude and exponent of the center of mass force
     * @param magnitude of center of mass
     * @param exponent of center of mass
     */
    public void setAttributionOfCenter (double magnitude, double exponent) {
        myMagnitude = magnitude;
        myExponent = exponent;
    }

    /**
     * Method that sets the position of the center of mass force
     * @param x position
     * @param y position
     */
    public void setPostionOfCenter (double x, double y) {
        myX = x;
        myY = y;
    }
}
