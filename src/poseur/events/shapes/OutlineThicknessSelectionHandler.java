
package poseur.events.shapes;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import poseur.Poseur;
import poseur.state.PoseurStateManager;

/**
 * This handler is for the outline thickness combobox.
 * @author Andrew Ireland
 */
public class OutlineThicknessSelectionHandler implements ItemListener 
{
    private int count;
    
    public OutlineThicknessSelectionHandler()
    {
        count = 0;
    }
    
    @Override
    public void itemStateChanged(ItemEvent e)
    {
        Poseur singleton = Poseur.getPoseur();
        PoseurStateManager stateManager = singleton.getStateManager();
        stateManager.setSelectedShapeOutlineThickness();     
    }
}
