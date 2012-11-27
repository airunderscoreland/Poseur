
package poseur.events.shapes;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import poseur.Poseur;
import poseur.state.PoseurStateManager;

/**
 * This handler is for the outline thickness combobox. When the user selects a 
 * line thickness from the combobox, the method itemStateChanged is called
 * but the new outline thickness gets set only in the SHAPE_SELECTED_STATE
 * @author Andrew Ireland
 */
public class OutlineThicknessSelectionHandler implements ItemListener 
{   
    /**
     * Called when a new selected is made in the combobox. The selected
     * shape's line thickness is updated. If no shape is selected, nothing 
     * happens.
     * @param e 
     */
    @Override
    public void itemStateChanged(ItemEvent e)
    {
        Poseur singleton = Poseur.getPoseur();
        PoseurStateManager stateManager = singleton.getStateManager();
        stateManager.setSelectedShapeOutlineThickness();     
    }
}
