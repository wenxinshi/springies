package keyCommands;

import entity.Model;

/**
 * Class that handles the command when the key 'V' is pressed. It toggles whether 
 * the viscosity force should be on or off. 
 * 
 * @author Team15
 *
 */
public class CommandV implements KeyCommand {

    private Model myModel;
    
    /**
     * Constructor for CommandV that sets myModel.
     * @param model the model that the command is performed on
     */
    public CommandV(Model model) {
        myModel = model;
    }

    @Override
    public void performKeyCommand() {
        myModel.onOrOffViscosity();
    }

}
