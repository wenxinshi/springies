package keyCommands;

import entity.Model;

/**
 * Class that handles the command when the key 'G' is pressed. It toggles whether 
 * the gravity force should be on or off. 
 * 
 * @author Team15
 *
 */
public class CommandG implements KeyCommand {

    private Model myModel;
    
    /**
     * Constructor for CommandG that sets myModel.
     * @param model the model that the command is performed on
     */
    public CommandG(Model model) {
        myModel = model;
    }

    @Override
    public void performKeyCommand() {
        myModel.onOrOffGravity();
    }

}
