/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.events.edit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import poseur.Poseur;
import poseur.state.PoseurStateManager;

/**
 *
 * @author Andrew Ireland
 */
public class BringToFrontHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Poseur singleton = Poseur.getPoseur();
        PoseurStateManager poseurStateManager = singleton.getStateManager();
        poseurStateManager.bringSelectedItemUp();
    }
    
}
