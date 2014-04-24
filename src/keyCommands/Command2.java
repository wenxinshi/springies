package keyCommands;

import entity.Model;

/**
 * Class that handles the command when the key '2' is pressed. It toggles whether 
 * wall 2 should be on or off. 
 * 
 * @author Team15
 *
 */
public class Command2 implements KeyCommand {
   
    private Model myModel;
    
    /**
     * Constructor for Command2 that sets myModel.
     * @param model the model that the command is performed on
     */
    public Command2(Model model) {
        myModel = model;
    }

    @Override
    public void performKeyCommand() {
        myModel.onOrOffWall('2');
    }

}
