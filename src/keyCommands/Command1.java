package keyCommands;

import entity.Model;

/**
 * Class that handles the command when the key '1' is pressed. It toggles whether 
 * wall 1 should be on or off. 
 * 
 * @author Team15
 *
 */
public class Command1 implements KeyCommand {
    
    private Model myModel;
    
    /**
     * Constructor for Command1 that sets myModel.
     * @param model the model that the command is performed on
     */
    public Command1(Model model) {
        myModel = model;
    }

    @Override
    public void performKeyCommand() {
        myModel.onOrOffWall('1');
    }

}
