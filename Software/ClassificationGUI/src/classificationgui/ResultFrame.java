/**
 * Internal frame for the result of a search
 * 
 * @author Luis Suárez Lloréns
 */

package classificationgui;

import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import jmr.result.ResultList;

public class ResultFrame extends javax.swing.JInternalFrame {

    /**
     * Reference to the window that launched this internal frame.
     */
    protected JFrame parent=null;
    
    /**
     * Creates new form ResultFrame.
     * 
     * @param parent window that launched this internal frame
     * @param list list to display
     */
    public ResultFrame(JFrame parent, ResultList list) {
        initComponents();
        this.parent = parent;
        if(list!=null)
            imageListPanel.add(list);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageListPanel = new jmr.iu.ImageListPanel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().add(imageListPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private jmr.iu.ImageListPanel imageListPanel;
    // End of variables declaration//GEN-END:variables
}
