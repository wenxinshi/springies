package keyCommands;

/**
 * The interface for the different types of command that can be performed by pressing
 * a keyboard button. All classes implementing this interface will have a 
 * performKeyCommand() method, which will have different functionalities for 
 * different classes.
 * 
 * @author Team15
 *
 */
public interface KeyCommand {
    
    /**
     * A method that is inherited by all classes implementing the KeyCommand interface.
     * It performs the command for when a specific keyboard key is pressed.
     * The contents of this method will vary depending on which class is implementing 
     * this interface.  
     */
    public void performKeyCommand();
}
