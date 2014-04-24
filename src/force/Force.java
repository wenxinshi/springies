package force;

import entity.Mass;

/**
 * An abstract class that has as subclasses Gravity, Viscosity, Center of Mass, and
 * Wall repulsion forces. It has a method setOnOrOff which toggles whether a 
 * particular force is in effect, and also a method setForce, which is inherited 
 * to its subclasses. 
 * @author User
 *
 */
public abstract class Force {
    protected boolean isOn=false;
    
    /**
     * Toggle whether or not a particular force is on or off, depending on the 
     * 
     */
    public void setOnOrOff () {
        if (isOn) {
            isOn = false;
        }
        else {
            isOn = true;
        }
    }
    
    /**
     * Method that sets a particular force on the given mass. Called by the subclasses
     * of Force.
     * @param mass mass on which the force is acted
     */
    public abstract void setForce(Mass mass);
}
