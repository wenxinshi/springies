package entity;

import jboxGlue.PhysicalObjectCircle;
import jgame.JGColor;
import jgame.JGObject;

/**
 * Class representing fixed mass
 * @author Team15
 *
 */
public class FixedMass extends PhysicalObjectCircle {

    /**
     * Calls a super constructor for PhysicalObjectCircle
     * @param id ID of mass
     * @param collisionId collisionId of mass
     * @param color color of mass
     * @param radius radius of mass
     */
    public FixedMass (String id, int collisionId, JGColor color, double radius) {
        super(id, collisionId, color, radius);
    }

    @Override
    public void hit (JGObject object) {

    }
    

}
