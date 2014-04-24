package force;

import jboxGlue.PhysicalObjectRect;
import jgame.JGColor;
import entity.Mass;


/**
 * Class that defines a wall in the simulation.
 * 
 * @author Team15
 * 
 */
public class Wall extends PhysicalObjectRect {

    private double myMagnitude;
    private double myExponent;
    private String myId;
    private boolean isOn = false;

    /**
     * Constructor that calls the super constructor, PhysicalObjectRect
     * 
     * @param id of wall
     * @param collisionId of wall
     * @param color of wall
     * @param width of wall
     * @param height of wall
     */
    public Wall (String id, int collisionId, JGColor color, double width,
                 double height) {
        super(id, collisionId, color, width, height);
        myId = id;
    }

    /**
     * Gets the id of a wall
     * 
     * @return a String representing the wall ID
     */
    public String getId () {
        return myId;
    }

    /**
     * Gets the magnitude of the wall's repulsive force
     * 
     * @return a double for wall's repulsive magnitude
     */
    public double getMagnitude () {
        return myMagnitude;
    }

    /**
     * Sets the magnitude of the wall's repulsive force
     * 
     * @param magnitude Desired magnitude of wall.
     */
    public void setMagnitude (double magnitude) {
        this.myMagnitude = magnitude;
    }

    /**
     * Gets the exponent used to calculate wall repulsion for this wall
     * 
     * @return a double representing the exponent.
     */
    public double getExponent () {
        return myExponent;
    }

    /**
     * Sets the exponent used to calculate repulsion for this wall.
     * 
     * @param exponent to set to this wall
     */
    public void setExponent (double exponent) {
        this.myExponent = exponent;
    }

    /**
     * Calculates the wall repulsion force for this wall
     * 
     * @param mass used to calculate repulsion force
     */
    public void setForce (Mass mass, String Id) {

        double xforce = 0;
        double yforce = 0;
        if (isOn) {
            if (Id.equals("1")) {
                xforce = 0;
                yforce = myMagnitude
                         * Math.pow(Math.abs(mass.y - this.y), -myExponent);
            }
            else if (Id.equals("2")) {
                xforce = -myMagnitude
                         * Math.pow(Math.abs(mass.x - this.x), -myExponent);
                yforce = 0;
            }

            else if (Id.equals("3")) {
                xforce = 0;
                yforce = -myMagnitude
                         * Math.pow(Math.abs(mass.y - this.y), -myExponent);
            }

            else if (Id.equals("4")) {
                xforce = myMagnitude
                         * Math.pow(Math.abs(mass.x - this.x), -myExponent);
                yforce = 0;
            }
            mass.setForce(xforce, yforce);
        }
    }

    /**
     * Sets the wall repulsion force on the given mass
     * @param mass that the force is acted on
     */
    public void setForce (Mass mass) {
        setForce(mass, myId);
    }

    /**
     * toggles whether the wall repulsion force is on or off
     */
    public void setOnOrOff () {
        if (isOn) {
            isOn = false;
            this.setColor(JGColor.gray);
        }
        else {
            isOn = true;
            this.setColor(JGColor.green);
        }
    }
    
    /**
     * Sets the BBox of the walls based on the parameter pixel
     * @param pixel amount by which to set the bbox of the wall 
     */
    public void setBBox (int pixel) {
        double height = super.getHeight();
        double width = super.getWidth();
        if (height + pixel > 0 && width + pixel > 0) {
            if (myId.equals("1")) {
                height += pixel;
                // width-=pixel*2;

                super.init(width, height, 0);
                this.setPos(x, y + pixel / 2);
            }
            else if (myId.equals("2")) {
                // height-=pixel*2;
                width += pixel;
                super.init(width, height, 0);
                this.setPos(x - pixel / 2, y);
            }
            else if (myId.equals("3")) {
                height += pixel;
                // width-=pixel*2;
                super.init(width, height, 0);
                this.setPos(x, y - pixel / 2);
            }
            else if (myId.equals("4")) {
                // height-=pixel*2;
                width += pixel;
                super.init(width, height, 0);
                this.setPos(x + pixel / 2, y);
            }
        }
    }
}
