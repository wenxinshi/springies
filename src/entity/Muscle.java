package entity;

import jgame.JGColor;


/**
 * Muscle class which extends the spring class
 * 
 * @author team15
 * 
 */
public class Muscle extends Spring {
    private static final long CONVERTER = 1000;
    private double myMuscleLength;
    private double myAmplitude;
    private double myPeriod;
    private double myMuscleConstant = 1;
    private long myStartTime;

    /**
     * Calls super constructor for spring class
     * 
     * @param name name of muscle
     * @param collisionId collision id of muscle
     * @param color color of muscle
     * @param restLength restlength of muscle
     */
    public Muscle (String name, int collisionId, JGColor color, double restLength) {
        super(name, collisionId, color, restLength);
        myStartTime = System.currentTimeMillis();
    }

    /**
     * Sets the wave for the muscle
     * 
     * @param amp amplitude of wave
     * @param speed period of the wave
     */
    public void setWave (double amp, double speed) {
        myAmplitude = amp;
        myPeriod = speed;
    }

    private void updateMuscleLength () {
        // timing need to finish
        Double myRestLength = this.getRestLength();

        long secondTime = (System.currentTimeMillis() - myStartTime) / CONVERTER;
        myMuscleLength = myRestLength
                         * (1 + myAmplitude * Math.sin(secondTime * Math.PI * 2 / myPeriod));
    }

    private void setForce () {
        updateMuscleLength();
        Mass myMassA = this.getMassA();
        Mass myMassB = this.getMassB();
        double currentLength = currentLength();
        double xForce = myMuscleConstant * (currentLength - myMuscleLength)
                        * (myMassB.x - myMassA.x) / currentLength;
        double yForce = myMuscleConstant * (currentLength - myMuscleLength)
                        * (myMassB.y - myMassA.y) / currentLength;

        myMassA.setForce(xForce / 2, yForce / 2);
        myMassB.setForce(-xForce / 2, -yForce / 2);
    }

    @Override
    public void move () {
        setForce();
    }

}
