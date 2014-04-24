package keyCommands;

import entity.Model;

/**
 * Class that handles the command when the key 'C' is pressed. It clears all 
 * assemblies that are active in the world. 
 * 
 * @author Team15
 *
 */
public class CommandC implements KeyCommand {

    private Model myModel;
    
    /**
     * Constructor for CommandC that sets myModel.
     * @param model the model that the command is performed on
     */
    public CommandC(Model model) {
        myModel = model;
    }

    @Override
    public void performKeyCommand() {
        myModel.clear();
    }

}
