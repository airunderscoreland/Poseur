package poseur.events.colors;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import poseur.Poseur;
import poseur.state.PoseurStateManager;

/**
 *
 * @author Andrew Ireland
 */
public class AlphaSliderHandler implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) 
    {
        Poseur singleton = Poseur.getPoseur();
        PoseurStateManager sm = singleton.getStateManager();
        sm.updateSelectedShapeAlpha();
    }
    
}
