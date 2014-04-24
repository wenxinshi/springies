package keyCommands;

import entity.Model;

/**
 * Class that handles the command when the key M is pressed. It toggles whether 
 * the center of mass force should be on or off. 
 * 
 * @author Team15
 *
 */
public class CommandM implements KeyCommand {

    private Model myModel;
    
    /**
     * Constructor for CommandM that sets myModel.
     * @param model the model that the command is performed on
     */
    public CommandM(Model model) {
        myModel = model;
    }

    @Override
    public void performKeyCommand() {
        myModel.onOrOffCenterOfMass();   
    }

}
