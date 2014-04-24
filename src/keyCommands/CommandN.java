package keyCommands;

import entity.Model;

/**
 * Class that handles the command when the key 'N' is pressed. It opens up a dialog
 * box and allows the user to create a new assembly. 
 * 
 * @author Team15
 *
 */
public class CommandN implements KeyCommand {

    private Model myModel;
    
    /**
     * Constructor for CommandN that sets myModel.
     * @param model the model that the command is performed on
     */
    public CommandN(Model model) {
        myModel = model;
    }

    @Override
    public void performKeyCommand() {
        myModel.parser();
    }

}
