package entity;

import force.CenterOfMass;
import force.Gravity;
import force.Viscosity;
import force.Wall;
import java.io.IOException;
import java.util.Vector;
import jboxGlue.PhysicalObject;
import jgame.JGColor;
import org.xml.sax.SAXException;
import parser.ParserFactory;
import springies.CollisionIDs;


/**
 * Class for collections of Mass, Springs, and Muscles. These are represented as 
 * vectors of PhsicalObjects. 
 * 
 * @author Team15
 * 
 */
public class Model {

    private Vector<PhysicalObject> myMassCollection = new Vector<PhysicalObject>();
    private Vector<PhysicalObject> mySpringCollection = new Vector<PhysicalObject>();
    private Vector<PhysicalObject> myMuscleCollection = new Vector<PhysicalObject>();
    private Vector<PhysicalObject> myFixedMassCollection = new Vector<PhysicalObject>();
    private Vector<PhysicalObject> myWallCollection = new Vector<PhysicalObject>();
    private ParserFactory myParser = new ParserFactory();
    private Mass myDragMass;
    private Spring myDragSpring;

    private CenterOfMass myCenterForce = new CenterOfMass();
    private Gravity myGravityForce = new Gravity();
    private Viscosity myViscosityForce = new Viscosity();

    private final int myDRAGMASSRADIUS = 3;
    private final int mySPRINGCONSTANT = 1;

    /**
     * Gets myMassCollection
     * 
     * @return myMassCollection
     */
    public Vector<PhysicalObject> getMassCollection () {
        return myMassCollection;
    }

    /**
     * Sets Mass Collection
     * 
     * @param massCollection massCollection to set
     */
    public void setMassCollection (Vector<PhysicalObject> massCollection) {
        this.myMassCollection = massCollection;
    }

    /**
     * Adds spring to mySpringCollection
     * 
     * @param spring spring to add
     */
    public void add (Spring spring) {
        mySpringCollection.add(spring);
    }

    /**
     * Adds FixedMass to myFixedMassCollection
     * 
     * @param entity fixed mass to add
     */
    public void add (FixedMass entity) {
        myFixedMassCollection.add(entity);
    }

    /**
     * Adds muscle to myMuscleCollection
     * 
     * @param entity muscle to add
     */
    public void add (Muscle entity) {
        myMuscleCollection.add(entity);
    }

    /**
     * Add the wall into collection
     * 
     * @param entity is wall reference
     */
    public void add (Wall entity) {
        myWallCollection.add(entity);
    }

    /**
     * Adds mass to myMassCollection
     * 
     * @param entity mass to add
     */
    public void add (Mass entity) {
        myMassCollection.add(entity);
        entity.setGravity(myGravityForce);
        entity.setCenterOfMass(myCenterForce);
        entity.setViscosity(myViscosityForce);
        entity.setWalls(myWallCollection);
    }

    /**
     * Gets the mass defined by name
     * 
     * @param name name of mass
     * @return
     */
    public Mass getMass (String name) {
        for (PhysicalObject mass : myMassCollection) {
            if (((Mass) mass).getId().equals(name)) { return (Mass) mass; }
        }
        return null;
    }

    /**
     * Set the gravity force
     * 
     * @param direction is direction of gravity
     * @param magnitude is magnitude of gravity
     */
    public void setGravityForce (double direction, double magnitude) {
        myGravityForce.setGravity(direction, magnitude);
    }

    /**
     * Set the viscsity force
     * 
     * @param kConstant is constant of air viscosity
     * @param magnitude is the magnitude of air viscosity
     */
    public void setViscosityForce (double kConstant, double magnitude) {
        myViscosityForce.setViscosity(kConstant, magnitude);
    }

    /**
     * Set the center of mass force
     * 
     * @param magnitude is magnitude of center force
     * @param exponent is power number of center force
     */
    public void setCenterOfMassForce (double magnitude, double exponent) {
        myCenterForce.setAttributionOfCenter(magnitude, exponent);

    }

    /**
     * Update the position of center of mass
     */
    public void centerOfMassUpdate () {
        if (myMassCollection == null) { return; }
        int xPos = 0;
        int yPos = 0;

        int numberOfMass = myMassCollection.size();
        if (numberOfMass != 0) {
            for (PhysicalObject mass : myMassCollection) {
                xPos += mass.x;
                yPos += mass.y;
            }
            int myX = xPos / numberOfMass;
            int myY = yPos / numberOfMass;
            // System.out.println(myX+" "+myY);
            myCenterForce.setPostionOfCenter(myX, myY);
        }
    }

    /**
     * Do parser the XML FILE;
     */
    public void parser () {
        try {
            myParser.parser(this);
        }
        catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private void clear (Vector<PhysicalObject> entityCollection) {
        if (entityCollection != null) {
            for (PhysicalObject entity : entityCollection) {
                entity.remove();
            }
        }
        entityCollection.clear();
    }

    /**
     * Clear all the collection of entity;
     */

    public void clear () {
        clear(myMassCollection);
        clear(myFixedMassCollection);
        clear(myWallCollection);
        clear(mySpringCollection);
        clear(myMuscleCollection);

        myCenterForce = new CenterOfMass();
        myGravityForce = new Gravity();
        myViscosityForce = new Viscosity();
    }

    /**
     * Gravity alternatively on or off by key input
     */
    public void onOrOffGravity () {
        myGravityForce.setOnOrOff();
    }

    /**
     * Viscosity alternatively on or off by key input
     */

    public void onOrOffViscosity () {
        myViscosityForce.setOnOrOff();
    }

    /**
     * Center force alternatively on or off by key input
     */
    public void onOrOffCenterOfMass () {
        myCenterForce.setOnOrOff();

    }

    /**
     * Wall repulsion alternatively on or off by key input
     * 
     * @param c is responded by pushing the key in keyboard
     */
    public void onOrOffWall (char c) {
        int index = Character.getNumericValue(c) - 1;
        ((Wall) myWallCollection.get(index)).setOnOrOff();

    }

    /**
     * Change the wall size
     * 
     * @param pixel is number of pixel should be changed for wall thickness.
     */
    public void changeWalls (int pixel) {
        for (PhysicalObject wall : myWallCollection) {
            ((Wall) wall).setBBox(pixel);
        }

    }

    /**
     * Create the mass at the position of mouse clicked
     * 
     * @param xPos position in x axis
     * @param yPos position in y axis
     */
    public void createDragMass (double xPos, double yPos) {
        if (myDragMass == null) {
            myDragMass = new Mass("dragMass", 
                                  CollisionIDs.MASS_ID, 
                                  JGColor.green,
                                  myDRAGMASSRADIUS,
                                  0);
            myDragMass.setGravity(myGravityForce);
            myDragMass.setViscosity(myViscosityForce);
            myDragMass.setCenterOfMass(myCenterForce);
            myDragMass.setWalls(myWallCollection);

            Mass massA = getNearestMass(xPos, yPos);
            if (massA != null) {
                myDragSpring =
                        new Spring("dragSpring", CollisionIDs.SPRING_ID, JGColor.blue,
                                   getDistance(xPos, yPos, massA));
                myDragSpring.setMassA(massA);
                myDragSpring.setMassB(myDragMass);
                myDragSpring.setSpringConstant(mySPRINGCONSTANT);
            }
        }
        myDragMass.setPos(xPos, yPos);
    }

    private Mass getNearestMass (double x, double y) {
        if (!myMassCollection.isEmpty()) {
            double min = Double.POSITIVE_INFINITY;
            PhysicalObject nearestMass = null;
            for (PhysicalObject m : myMassCollection) {
                if (getDistance(x, y, m) < min) {
                    min = getDistance(x, y, m);
                    nearestMass = m;
                }
            }
            return (Mass) nearestMass;
        }
        return null;
    }

    private double getDistance (double aX, double aY, PhysicalObject m) {
        return Math.sqrt(Math.pow(aX - m.x, 2)
                         + Math.pow(aY - m.y, 2));
    }

    /**
     * Delete the drag mass
     */
    public void deleteDragMass () {
        if (myDragMass != null) {
            myDragMass.remove();
            myDragMass = null;
        }
        if (myDragSpring != null) {
            myDragSpring.remove();
            myDragSpring = null;
        }

    }

}
