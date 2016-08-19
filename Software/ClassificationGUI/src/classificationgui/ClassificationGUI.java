/**
 * Class representing the main classification GUI.
 *
 * @author Luis Suárez Lloréns
 */
package classificationgui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ClassificationGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            JFrame.setDefaultLookAndFeelDecorated(true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }      
        MainWindow mainWindow =  new MainWindow();
        mainWindow.setVisible(true); 
    }
    
}
