package keyCommands;

import entity.Model;

/**
 * Class that handles the command when the down key is pressed. It resizes the walls 
 *  so that the width of the wall becomes thinner.
 * 
 * @author Team15
 *
 */
public class CommandKeyDown implements KeyCommand {

    private static final int WALL_VALUE = -10;
    private Model myModel;
    
    /**
     * Constructor for CommandKeyDown that sets myModel.
     * @param model the model that the command is performed on
     */
    public CommandKeyDown(Model model) {
        myModel = model;
    }

    @Override
    public void performKeyCommand() {
        myModel.changeWalls(WALL_VALUE);
    }

}
