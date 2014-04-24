package keyCommands;

import entity.Model;

/**
 * Class that handles the command when the key '3' is pressed. It toggles whether 
 * wall 3 should be on or off. 
 * 
 * @author Team15
 *
 */
public class Command3 implements KeyCommand {

    private Model myModel;
    
    /**
     * Constructor for Command3 that sets myModel.
     * @param model the model that the command is performed on
     */
    public Command3(Model model) {
        myModel = model;
    }

    @Override
    public void performKeyCommand() {
        myModel.onOrOffWall('3');
    }

}
