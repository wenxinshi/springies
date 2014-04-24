package keyCommands;

import entity.Model;

/**
 * Class that handles the command when the up key is pressed. It resizes the walls 
 * so that the width of the walls become thicker.
 * 
 * @author Team15
 *
 */
public class CommandKeyUp implements KeyCommand {
    
    private static final int WALL_VALUE = 10;
    private Model myModel;
    
    /**
     * Constructor for CommandKeyUp that sets myModel.
     * @param model the model that the command is performed on
     */
    public CommandKeyUp(Model model) {
        myModel = model;
    }

    @Override
    public void performKeyCommand() {
        myModel.changeWalls(WALL_VALUE);
    }
}