package force;

import entity.Mass;


/**
 * Class defining viscous force
 * 
 * @author Team15
 * 
 */
public class Viscosity extends Force {
    private double myKConstant = 0.3;
    private double myMagnitude;

    /**
     * Constructor for viscosity that initializes the k constant and magnitude
     * @param kConstant of the viscosity force
     * @param magnitude of the viscosity force
     */
    public Viscosity (double kConstant, double magnitude) {
        myKConstant = kConstant;
        myMagnitude = magnitude;
    }

    /**
     * Empty constructor for viscosity that takes in no parameters
     */
    public Viscosity () {
    }

    /**
     * Calculates the viscosity force on a mass.
     * 
     * @param mass on which to calculate viscosity
     */
    public void setForce (Mass mass) {
        if (isOn) {
            float xVis = (float) ((-myKConstant * mass.xspeed) * myMagnitude);
            float yVis = (float) ((-myKConstant * mass.yspeed) * myMagnitude);
            mass.setForce(xVis, yVis);
        }
    }

    /**
     * Sets the viscosity force given a k value and the magnitude
     * @param kConstant of the viscosity force
     * @param magnitude of the viscosity force
     */
    public void setViscosity (double kConstant, double magnitude) {
        myKConstant = kConstant;
        myMagnitude = magnitude;
    }
}
