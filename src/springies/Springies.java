package springies;

import entity.Model;
import java.util.HashMap;
import java.util.Map;
import jboxGlue.WorldManager;
import jgame.JGPoint;
import jgame.platform.JGEngine;
import keyCommands.Command1;
import keyCommands.Command2;
import keyCommands.Command3;
import keyCommands.Command4;
import keyCommands.CommandC;
import keyCommands.CommandG;
import keyCommands.CommandKeyDown;
import keyCommands.CommandKeyUp;
import keyCommands.CommandM;
import keyCommands.CommandN;
import keyCommands.CommandV;
import keyCommands.KeyCommand;


/**
 * Class that is called by main and it initiates and drives the simulation
 * 
 * @author Team15
 * 
 */
public class Springies extends JGEngine {
    
    /**
     * A given value for the height of the 
     * simulation viewport.
     */
    public static final int HEIGHT = 600;
    /**
     * Calculates the 16:9 width of the view port 
     * given a height.
     */
    public static final int WIDTH = HEIGHT * 16 / 9;
    
    private static final double FPS = 60;
    private static final double MAX_FRAME_SKIP = 2;
    
    /**
     * Serial verision UID necessary
     * to extend JGEngine.
     */
    private static final long serialVersionUID = 1L;

    private Model myModel;
    
    /**
     * Maps commands to given integer and key values.
     */
    private Map<Integer, KeyCommand> myCommandMap;
    
    /**
     * Constructor that initiates the engine
     */
    public Springies () {
        initEngine(WIDTH, HEIGHT);

    }

    @Override
    public void initCanvas() {
        setCanvasSettings(1, 1, displayWidth(), displayHeight(), null, null,
                          null);

    }
    
    @Override
    public void initGame() {
        setFrameRate(FPS, MAX_FRAME_SKIP);
        WorldManager.initWorld(this);

        myModel = new Model();
        myCommandMap = new HashMap<Integer, KeyCommand>();
        createCommandMap();
    }

    @Override
    public void doFrame() {
        // update game objects
        WorldManager.getWorld().step(1f, 1);

        moveObjects();
        checkCollision(CollisionIDs.WALL_ID, CollisionIDs.MASS_ID);
        checkCollision(CollisionIDs.MASS_ID, CollisionIDs.MASS_ID);

        checkKeyInput();
        checkMouseInput();
        myModel.centerOfMassUpdate();
    }

    private void checkMouseInput() {
        if (this.getMouseButton(1)) {
            JGPoint pos = this.getMousePos();
            myModel.createDragMass(pos.x, pos.y);
        } 
        else if (!this.getMouseButton(1)) {
            myModel.deleteDragMass();
        }
    }

    private void createCommandMap() {
        myCommandMap.put((int) 'N', new CommandN(myModel));
        myCommandMap.put((int) 'C', new CommandC(myModel));
        myCommandMap.put((int) 'G', new CommandG(myModel));
        myCommandMap.put((int) 'V', new CommandV(myModel));
        myCommandMap.put((int) 'M', new CommandM(myModel));
        myCommandMap.put((int) '1', new Command1(myModel));
        myCommandMap.put((int) '2', new Command2(myModel));
        myCommandMap.put((int) '3', new Command3(myModel));
        myCommandMap.put((int) '4', new Command4(myModel));
        myCommandMap.put(KeyUp, new CommandKeyUp(myModel));
        myCommandMap.put(KeyDown, new CommandKeyDown(myModel));
    }

    private void checkKeyInput() {
        if (myCommandMap.get(getLastKey()) != null) {
            myCommandMap.get(getLastKey()).performKeyCommand();
            clearLastKey();
        }
    }

    @Override
    public void paintFrame() {
        // nothing to do
        // the objects paint themselves
    }
}
