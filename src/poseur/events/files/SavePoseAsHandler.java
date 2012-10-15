package poseur.events.files;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.Poseur;
import poseur.files.PoseurFileManager;

/**
 * This handler responds to when the user wants to save the
 * Pose currently being edited.
 * 
 * @author  Richard McKenna
 *          Debugging Enterprises
 *          Andrew Ireland
 * @version 1.0
 */
public class SavePoseAsHandler implements ActionListener
{
    /**
     * Called when the user requests to exit via the
     * exit button, this will make sure no work is lost
     * and then close the application.
     * 
     * @param ae The Event Object.
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        // FORWARD THE REQUEST TO THE FILE MANAGER
        Poseur singleton = Poseur.getPoseur();
        PoseurFileManager poseurFileManager = singleton.getFileManager();
        poseurFileManager.requestSaveAsPose();
    }
}