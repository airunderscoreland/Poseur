package poseur.files;

import java.awt.Color;
import poseur.state.ColorPalletState;

/**
 * This class can be used to load color pallet data from an XML 
 * file into a constructed ColorPalletState. Note that the XML
 * file must validate against the color_pallet_settings.xsd
 * schema. This class demonstrates how application settings can
 * be loaded dynamically from a file.
 * 
 * @author  Richard McKenna
 *          Debugging Enterprises
 * @version 1.0
 */
public class ColorPalletLoader 
{    
    /**
     * This method will extract the data found in the provided
     * XML file argument and use it to load the color pallet
     * argument.
     * 
     * @param colorPalletXMLFile Path and file name to an XML
     * file containing information about a custom color pallet. Note
     * this XML file must validate against the aforementioned schema.
     * 
     * @param colorPalletState The state manager for the color
     * pallet, we'll load all the data found in the XML file
     * inside here.
     */
    public void initColorPallet( String colorPalletXMLFile,
                                 ColorPalletState colorPalletState)
    {
        Color[] colorPallet = new Color[20];
        for (int i = 0; i < 20; i++)
        {
            colorPallet[i] = Color.GRAY;
        }
        colorPalletState.loadColorPalletState(  colorPallet,
                                                2,
                                                12,
                                                Color.GRAY);
    }
}