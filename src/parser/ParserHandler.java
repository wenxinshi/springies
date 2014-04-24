package parser;

import entity.Mass;
import entity.Model;
import entity.Muscle;
import entity.Spring;
import force.Wall;
import jgame.JGColor;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import springies.CollisionIDs;
import springies.Springies;


/**
 * Class that specifies how to parse an XML file
 * 
 * @author Team15
 * 
 */
public class ParserHandler extends DefaultHandler {

    /**
     * SerialID necessary for parser.
     */
    private static int ourSerialID = 0;

    private static final String ENVIRONMENTTAG = "environment";
    private static final double WALLMARGINDEFAULT = 3;
    private static final double WALLTHICKNESSDEFAULT = 5;
    private static final double RESTLENGTHDEFAULT = 50;
    private static final double RADIUSDEFAULT = 5;
    private static final double MASSDEFAULT = 1;
    private static final double VISCOSITYDEFAULT = 0.3;

    private static final String GRAVITYTAG = "gravity";
    private static final String CENTERMASSTAG = "centermass";
    private static final String WALLTAG = "wall";
    private static final String VISCOSITYTAG = "viscosity";

    private static final String MODELTAG = "model";
    private static final String MASSTAG = "mass";
    private static final String SPRINGTAG = "spring";
    private static final String MUSCLETAG = "muscle";

    private static final String IDTAG = "id";
    private static final String XPOSTAG = "x";
    private static final String YPOSTAG = "y";
    private static final String RESTLENGTHTAG = "restlength";
    private static final String CONSTANTTAG = "constant";
    private static final String ATAG = "a";
    private static final String BTAG = "b";
    private static final String FIXEDMASSTAG = "fixed";

    private static final String DIRECTIONTAG = "direction";
    private static final String MAGNITUDETAG = "magnitude";
    private static final String EXPONENTTAG = "exponent";
    private static final String AMPLITUDETAG = "amplitude";

    private static final String SERIES = "SERIES";

    // private static final String

    private double myRestLength;
    /**
     * Boolean flag for environment file.
     */
    private boolean myEnvironment = false;
    /**
     * Boolean flag for assembly file.
     */
    private boolean myModel = false;

    /**
     * Model to which parser adds
     * environment and assemblies.
     */
    private Model mySimulation;

    /**
     * Empty constructor; no parameters necessary.
     */
    public ParserHandler () {
    }

    /**
     * Simulation if realization of assembling
     * 
     * @param simulation is realization of assembling
     */
    public ParserHandler (Model simulation) {
        this.mySimulation = simulation;
        ourSerialID++;
    }

    @Override
    public void startElement (String uri, String localName, String qName,
                              Attributes attributes) throws SAXException {
        if (myEnvironment) {
            setEnvironment(qName, attributes);
        }
        else if (myModel) {
            setModel(qName, attributes);
        }
        else if (qName.equals(ENVIRONMENTTAG)) {
            myEnvironment = true;
        }
        else if (qName.equals(MODELTAG)) {
            myModel = true;
        }
    }

    /**
     * Sets the environment variables in the model.
     * 
     * @param qName
     * @param attributes
     */
    private void setEnvironment (String qName, Attributes attributes) {
        if (qName.equals(GRAVITYTAG)) {
            setGravity(attributes);
        }
        else if (qName.equals(VISCOSITYTAG)) {
            setViscosity(attributes);
        }
        else if (qName.equals(CENTERMASSTAG)) {
            setCentermass(attributes);
        }
        else if (qName.equals(WALLTAG)) {
            setWall(attributes);
        }
    }

    /**
     * Adds entities to the model.
     * 
     * @param qName
     * @param attributes
     */
    private void setModel (String qName, Attributes attributes) {
        if (qName.equals(MASSTAG)) {
            setMass(attributes);
        }
        else if (qName.equals(FIXEDMASSTAG)) {
            setFixed(attributes);
        }
        else if (qName.equals(SPRINGTAG)) {
            setSpring(attributes);
        }
        else if (qName.equals(MUSCLETAG)) {
            setMuscle(attributes);
        }
    }

    /**
     * Sets the center of mass force for the
     * simulation.
     * 
     * @param attributes
     */
    private void setCentermass (Attributes attributes) {
        double magnitude = Double.parseDouble(attributes.getValue(MAGNITUDETAG));
        double exponent = Double.parseDouble(attributes.getValue(EXPONENTTAG));
        mySimulation.setCenterOfMassForce(magnitude, exponent);
        mySimulation.onOrOffCenterOfMass();
    }

    /**
     * Sets the viscosity force for the
     * simulation.
     * 
     * @param attributes
     */
    private void setViscosity (Attributes attributes) {
        double magnitude = Double.parseDouble(attributes.getValue(MAGNITUDETAG));
        mySimulation.setViscosityForce(VISCOSITYDEFAULT, magnitude);
        mySimulation.onOrOffViscosity();
    }

    /**
     * Sets the gravitational force for the
     * simulation.
     * 
     * @param attributes
     */
    private void setGravity (Attributes attributes) {
        double direction = Double.parseDouble(attributes.getValue(DIRECTIONTAG));
        double magnitude = Double.parseDouble(attributes.getValue(MAGNITUDETAG));
        mySimulation.setGravityForce(direction, magnitude);
        mySimulation.onOrOffGravity();
    }

    @Override
    public void endElement (String uri, String localName,
                            String qName) throws SAXException {
        if (qName.equals(ENVIRONMENTTAG)) {
            myEnvironment = false;
        }
        else if (qName.equals(MODELTAG)) {
            myModel = false;
        }
    }

    /**
     * Adds a new fixed mass to
     * the simulation.
     * 
     * @param attributes
     */
    private void setFixed (Attributes attributes) {
        String id = attributes.getValue(IDTAG);
        int collisionId = CollisionIDs.FIXED_MASS_ID;
        JGColor color = JGColor.orange;
        Mass newMass = new Mass(id + SERIES + ourSerialID,
                                collisionId, color, RADIUSDEFAULT, 0);

        Double xPos = Double.parseDouble(attributes.getValue(XPOSTAG));
        Double yPos = Double.parseDouble(attributes.getValue(YPOSTAG));

        newMass.setPos(xPos, yPos);

        mySimulation.add(newMass);

    }

    /**
     * Adds a new mass to the simulation.
     * 
     * @param attributes
     */
    private void setMass (Attributes attributes) {
        String id = attributes.getValue(IDTAG);
        int collisionId = CollisionIDs.MASS_ID;
        JGColor color = JGColor.green;

        double mass = MASSDEFAULT;
        if (attributes.getIndex(MASSTAG) != -1) {
            mass = Double.parseDouble(attributes.getValue(MASSTAG));
        }
        Mass newMass = new Mass(id + SERIES + ourSerialID,
                                collisionId, color, RADIUSDEFAULT, mass);

        Double xPos = Double.parseDouble(attributes.getValue(XPOSTAG));
        Double yPos = Double.parseDouble(attributes.getValue(YPOSTAG));
        newMass.setPos(xPos, yPos);

        mySimulation.add(newMass);

    }

    /**
     * Adds a new spring to the simulation.
     * 
     * @param attributes
     */
    private void setSpring (Attributes attributes) {
        String nameA = attributes.getValue(ATAG) + SERIES + ourSerialID;
        String nameB = attributes.getValue(BTAG) + SERIES + ourSerialID;
        Mass massA = mySimulation.getMass(nameA);
        Mass massB = mySimulation.getMass(nameB);

        myRestLength = RESTLENGTHDEFAULT;
        if (attributes.getIndex(RESTLENGTHTAG) != -1) {
            String stringRestLength = attributes.getValue(RESTLENGTHTAG);
            myRestLength = Double.parseDouble(stringRestLength);
        }

        Spring spring = new Spring(null, CollisionIDs.SPRING_ID, JGColor.blue,
                                   myRestLength);
        double springConstant = 1;
        if (attributes.getIndex(CONSTANTTAG) != -1) {
            String stringKConstant = attributes.getValue(CONSTANTTAG);
            springConstant = Double.parseDouble(stringKConstant);
        }
        spring.setSpringConstant(springConstant);
        System.out.println(attributes.getIndex(CONSTANTTAG));
        spring.setMassA(massA);
        spring.setMassB(massB);

        mySimulation.add(spring);

    }

    /**
     * Adds a new muscle to the
     * simulation.
     * 
     * @param attributes
     */
    private void setMuscle (Attributes attributes) {
        String nameA = attributes.getValue(ATAG) + SERIES + ourSerialID;
        String nameB = attributes.getValue(BTAG) + SERIES + ourSerialID;
        String stringRestLength = attributes.getValue(RESTLENGTHTAG);
        String stringAmplitude = attributes.getValue(AMPLITUDETAG);

        Mass massA = mySimulation.getMass(nameA);
        Mass massB = mySimulation.getMass(nameB);
        Double restLength = Double.parseDouble(stringRestLength);
        Double amplitude = Double.parseDouble(stringAmplitude);

        Muscle muscle = new Muscle(null, CollisionIDs.MUSCLE_ID, JGColor.gray,
                                   restLength);
        muscle.setMassA(massA);
        muscle.setMassB(massB);
        muscle.setWave(amplitude, 1);

        mySimulation.add(muscle);

    }

    /**
     * Creates the walls for the simulation.
     * 
     * @param attributes
     */
    private void setWall (Attributes attributes) {
        double wallWidth = Springies.WIDTH - WALLMARGINDEFAULT * 2 + WALLTHICKNESSDEFAULT;
        double wallHeight = Springies.HEIGHT - WALLMARGINDEFAULT * 2
                            + WALLTHICKNESSDEFAULT;
        String id = attributes.getValue(IDTAG);
        int collisionId = CollisionIDs.WALL_ID;
        JGColor color = JGColor.green;
        String magnitude = attributes.getValue(MAGNITUDETAG);
        String exponent = attributes.getValue(EXPONENTTAG);
        Wall wall;

        if (id.equals("1")) {
            wall = new Wall(id, collisionId, color, wallWidth, WALLTHICKNESSDEFAULT);
            wall.setPos(wall.eng.displayWidth() / 2, WALLMARGINDEFAULT);
        }
        else if (id.equals("2")) {
            wall = new Wall(id, collisionId, color, WALLTHICKNESSDEFAULT, wallHeight);
            wall.setPos(wall.eng.displayWidth() - WALLMARGINDEFAULT,
                        wall.eng.displayHeight() / 2);
        }
        else if (id.equals("3")) {
            wall = new Wall(id, collisionId, color, wallWidth, WALLTHICKNESSDEFAULT);
            wall.setPos(wall.eng.displayWidth() / 2, wall.eng.displayHeight()
                                                     - WALLMARGINDEFAULT);
        }
        else if (id.equals("4")) {
            wall = new Wall(id, collisionId, color, WALLTHICKNESSDEFAULT, wallHeight);
            wall.setPos(WALLMARGINDEFAULT, wall.eng.displayHeight() / 2);
        }
        else {
            wall = null;
        }

        if (wall != null) {
            mySimulation.add(wall);
            mySimulation.onOrOffWall(id.charAt(0));
            wall.setMagnitude(Double.parseDouble(magnitude));
            wall.setExponent(Double.parseDouble(exponent));
        }
    }

}
