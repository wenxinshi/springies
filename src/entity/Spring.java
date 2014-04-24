package entity;

import jboxGlue.PhysicalObjectCircle;
import jgame.JGColor;


/**
 * Class for the Spring object, extends PhysicalObject
 * 
 * @author Team15
 * 
 */
public class Spring extends PhysicalObjectCircle {

    private double mySpringConstant;
    private Mass myMassA;
    private Mass myMassB;
    private double myRestLength;

    /**
     * Spring constructor. Makes a call to the super constructor for PhysicalObject
     * 
     * @param name of spring
     * @param collisionId of group spring
     * @param color of spring
     * @param restLength of spring
     */

    public Spring (String name, int collisionId, JGColor color, double restLength) {
        super(name, collisionId, color, 0);
        this.myRestLength = restLength;
    }

    /**
     * Sets the rest length of the spring
     * 
     * @param restLength to set the spring to
     */
    public void setRestLength (double restLength) {
        this.myRestLength = restLength;
    }

    /**
     * Get massA
     * 
     * @return massA
     */
    public Mass getMassA () {
        return myMassA;
    }

    /**
     * Get massB
     * 
     * @return massB
     */
    public Mass getMassB () {
        return myMassB;
    }

    /**
     * Get restLength
     * 
     * @return restLength
     */
    public double getRestLength () {
        return myRestLength;
    }

    /**
     * Set spring constant
     * 
     * @param kConstant is spring constant
     */

    public void setSpringConstant (double kConstant) {
        mySpringConstant = kConstant;
    }

    /**
     * Sets the mass of massA, one of the end points of the spring
     * 
     * @param mass to set
     */
    public void setMassA (Mass mass) {
        myMassA = mass;
    }

    /**
     * Sets the mass of massB, one of the end points of the spring
     * 
     * @param mass to set
     */
    public void setMassB (Mass mass) {
        myMassB = mass;
    }

    /**
     * calculates the current length of the spring by using the distance formula.
     * 
     * @return current length of spring
     */
    public double currentLength () {
        return Math.sqrt(Math.pow(myMassA.x - myMassB.x, 2)
                         + Math.pow(myMassA.y - myMassB.y, 2));
    }

    private void setForce () {
        double diffLength = currentLength() - myRestLength;
        double amplitude = mySpringConstant * diffLength;
        double xDir = myMassB.x - myMassA.x;
        double yDir = myMassB.y - myMassA.y;

        float xForce = (float) (amplitude * xDir / Math.sqrt(xDir * xDir + yDir
                                                             * yDir));
        float yForce = (float) (amplitude * yDir / Math.sqrt(xDir * xDir + yDir
                                                             * yDir));
        
      
        myMassA.setForce(xForce / 2, yForce / 2);
        myMassB.setForce(-xForce / 2, -yForce / 2);
    }

    @Override
    public void paintShape () {
        if (myMassA != null && myMassB != null) {
            myEngine.setColor(myColor);
            myEngine.drawLine(myMassA.x, myMassA.y, myMassB.x, myMassB.y);
        }
    }

    /**
     * Set the force to mass
     */
    public void move () {
        setForce();
    }

}
