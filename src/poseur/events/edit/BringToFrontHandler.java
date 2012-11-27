package poseur.events.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.Poseur;
import poseur.state.PoseurStateManager;

/**
 * Handler class for the ActionListener added to the Move Selected Object to
 * Front button. When the button is clicked, the selected object moves on top
 * of all other shapes.
 * 
 * @author Andrew Ireland
 */
public class BringToFrontHandler implements ActionListener {

    /**
     * Called when the Move Selected Shape To Front button is pressed. At the 
     * end of this method, the selected shape will be on top of all other 
     * shapes.
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Poseur singleton = Poseur.getPoseur();
        PoseurStateManager poseurStateManager = singleton.getStateManager();
        poseurStateManager.bringSelectedItemUp();
    }
    
}
