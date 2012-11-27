package poseur.events.colors;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import poseur.Poseur;
import poseur.state.PoseurStateManager;

/**
 * Manged the transparency slider. Whenever the value of the slider changes 
 * (the user is moving the slider), the stateChanged method will update the
 * alpha transparency of the selected shape.
 * @author Andrew Ireland
 */
public class AlphaSliderHandler implements ChangeListener {

    /**
     * Called when the state of the transparency slider changes. Updates the 
     * alpha transparency of the selected shape.
     * @param e 
     */
    @Override
    public void stateChanged(ChangeEvent e) 
    {
        Poseur singleton = Poseur.getPoseur();
        PoseurStateManager sm = singleton.getStateManager();
        sm.updateSelectedShapeAlpha();
    }
    
}
