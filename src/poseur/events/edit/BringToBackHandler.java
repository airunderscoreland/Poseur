package poseur.events.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.Poseur;
import poseur.state.PoseurStateManager;

/**
 * Handler class for the ActionListener added to the Move Selected Object to
 * Back button. When the button is clicked, the selected object moves behind
 * all other shapes.
 * 
 * @author Andrew Ireland
 */
public class BringToBackHandler implements ActionListener {

    /**
     * Called when the user presses the Move Selected Shape To Back button. 
     * This method will bring the selected shape to the back of the shapes list
     * so the shape will be under all other shapes. This does nothing if no
     * shape is selected.
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Poseur singleton = Poseur.getPoseur();
        PoseurStateManager poseurStateManager = singleton.getStateManager();
        poseurStateManager.bringSelectedItemDown();
    }
    
}
