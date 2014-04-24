package entity;

import force.CenterOfMass;
import force.Gravity;
import force.Viscosity;
import force.Wall;
import java.util.Vector;
import jboxGlue.PhysicalObject;
import jboxGlue.PhysicalObjectCircle;
import jboxGlue.WorldManager;
import jgame.JGColor;
import jgame.JGObject;
import org.jbox2d.common.Vec2;
import springies.CollisionIDs;



/**
 * Class representing Mass that extends PhysicalObjectCircle
 * 
 * @author User
 * 
 */
public class Mass extends PhysicalObjectCircle {
    private double myWeight;
    private String myId;

    private CenterOfMass myCenter;
    private Gravity myGravity;
    private Viscosity myViscosity;
    private Vector<PhysicalObject> myWalls;

    /**
     * Calls a super constructor for PhysicalObjectCircle
     * 
     * @param id ID of mass
     * @param collisionId collision ID for mass
     * @param color color of mass
     * @param radius radius of mass
     * @param mass weight of mass
     */
    public Mass (String id, int collisionId, JGColor color, double radius,
                 double mass) {
        super(id, collisionId, color, radius, mass);
        // this.mass=Math.PI*Math.pow(radius, 2);
        this.myId = id;
        myWeight = mass;
    }

    /**
     * Gets the mass ID
     * 
     * @return myID
     */
    public String getId () {
        return myId;
    }

    /**
     * Gets the weight of the mass
     * 
     * @return myWeight
     */
    public double getWeight () {
        return myWeight;
    }

    @Override
    public void hit (JGObject object) {

        if (object.colid == CollisionIDs.WALL_ID) {

            Vec2 velocity = myBody.getLinearVelocity();
            final double DAMPING_FACTOR = 0.5;
            boolean isSide = object.getBBox().height > object.getBBox().width;
            if (isSide) {
                velocity.x *= -DAMPING_FACTOR;
            }
            else {
                velocity.y *= -DAMPING_FACTOR;
            }

            // apply the change
            myBody.setLinearVelocity(velocity);
        }
    }

    @Override
    public void move () {
        if (myBody.m_world != WorldManager.getWorld()) {
            remove();
            return;
        }

        // copy the position and rotation from the JBox world to the JGame world
        Vec2 position = myBody.getPosition();
        x = position.x;
        y = position.y;

        myRotation = -myBody.getAngle();

        myCenter.setForce(this);
        myGravity.setForce(this);
        myViscosity.setForce(this);
        setWallRepulsion(this);
    }

    private void setWallRepulsion (Mass mass) {
        if (myWalls != null) {
            for (PhysicalObject wall : myWalls) {
                ((Wall) wall).setForce(mass);
            }
        }

    }

    @Override
    public String toString () {
        return myId;
    }
/**
 * Set Gravity
 * @param gravityForce for gravity input
 */
    public void setGravity (Gravity gravityForce) {
        myGravity = gravityForce;

    }
/**
 * Set the center of mass
 * @param centerForce is force from center of mass
 */
    public void setCenterOfMass (CenterOfMass centerForce) {
        myCenter = centerForce;

    }
/**
 * Set viscosity 
 * @param viscosityForce is force from viscosity
 */
    public void setViscosity (Viscosity viscosityForce) {
        myViscosity = viscosityForce;

    }

    /**
     * Set the walls collection
     * @param wallCollection is collection of walls
     */
    public void setWalls (Vector<PhysicalObject> wallCollection) {
        myWalls = wallCollection;
    }

}
