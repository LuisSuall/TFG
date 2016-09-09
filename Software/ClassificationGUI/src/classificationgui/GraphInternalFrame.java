package classificationgui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import sri.feature.ContourFeature;

/**
 *
 * @author Luis Suárez Lloréns
 * @author Jesús Chamorro Martínez (jesus@decsai.ugr.es)
 */
public class GraphInternalFrame extends javax.swing.JInternalFrame {
 
    
    protected JFrame parent=null;
    private ContourFeature contourFeature;
    
    public GraphInternalFrame(JFrame parent, ContourFeature cfeature) {
        initComponents();
        
        this.parent = parent;
        this.contourFeature = cfeature;
        
        this.setSize(new Dimension(500,500));
        JFreeChart lineChart = ChartFactory.createXYLineChart(null, null,null, createDataset(cfeature),PlotOrientation.VERTICAL, false,true,false);    
        XYPlot plot =  lineChart.getXYPlot();
        plot.getRangeAxis().setRange(0.0, 1.0);
        ChartPanel cpanel = new ChartPanel(lineChart);
        this.getContentPane().add(cpanel,BorderLayout.CENTER);   
    }

    private XYDataset createDataset(ContourFeature cfeature) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        XYSeries serie = new XYSeries("XY Data");
        int pos = 0;
        for (double value: cfeature) {
            serie.add(pos++,(Number)value);
        }
        dataset.addSeries("Degree",serie.toArray());
        return dataset;
    }
    
    public ContourFeature getContourFeature(){
        return contourFeature;
    }
    
    /*
     * Código generado por Netbeans para el diseño del interfaz
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
