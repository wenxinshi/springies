package keyCommands;

import entity.Model;

/**
 * Class that handles the command when the key '4' is pressed. It toggles whether 
 * wall 4 should be on or off. 
 * 
 * @author Team15
 *
 */
public class Command4 implements KeyCommand {

    private Model myModel;
    
    /**
     * Constructor for Command4 that sets myModel.
     * @param model the model that the command is performed on
     */
    public Command4(Model model) {
        myModel = model;
    }

    @Override
    public void performKeyCommand() {
        myModel.onOrOffWall('4');
    }

}
