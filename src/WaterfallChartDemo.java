/*
 *For Non-preemitive Priority Priority  & Non-preemitive SJF
 * */

import java.awt.Color;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A sample waterfall chart.
 */
public class WaterfallChartDemo extends ApplicationFrame {
	
	static process p;

    /**
     * Creates a new WaterFall Chart demo.
     * 
     * @param title  the frame title.
     */
    public WaterfallChartDemo(final String title) {

        super(title);
        
        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setEnforceFileExtensions(false);
        setContentPane(chartPanel);
       
    }

    /**
     * Creates a sample dataset for the demo.
     * 
     * @return A sample dataset.
     */
    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(p.gui.get(0).btime, "", p.gui.get(0).name);
        for(int i=1;i<p.gui.size();i++) {
    	dataset.addValue(p.gui.get(i).btime, "", p.gui.get(i).name);
    	
        }

        
        dataset.addValue(0, "", "");
        return dataset;
    }
    
    
    private JFreeChart createChart(final CategoryDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createWaterfallChart(
            "GanttChart",
            "processes",
            "Time",
            dataset,
            PlotOrientation.HORIZONTAL,
            true,
            true,
            false
        );
        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        plot.setRangeGridlinesVisible(true);
//        plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        
        final ValueAxis rangeAxis = plot.getRangeAxis();
        
        // create a custom tick unit collection...
        final DecimalFormat formatter = new DecimalFormat("##,###");
        formatter.setNegativePrefix("(");
        formatter.setNegativeSuffix(")");
        final TickUnits standardUnits = new TickUnits();
        standardUnits.add(new NumberTickUnit(5, formatter));
        standardUnits.add(new NumberTickUnit(10, formatter));
        standardUnits.add(new NumberTickUnit(15, formatter));
        standardUnits.add(new NumberTickUnit(16, formatter));
        
        standardUnits.add(new NumberTickUnit(20, formatter));
       

        rangeAxis.setStandardTickUnits(standardUnits);

        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        final DecimalFormat labelFormatter = new DecimalFormat("$##,###.00");
        labelFormatter.setNegativePrefix("(");
        labelFormatter.setNegativeSuffix(")");

        renderer.setItemLabelsVisible(true);

        return chart;
    }

    
    public static void main() {
        final WaterfallChartDemo demo = new WaterfallChartDemo("GanttChart");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
    
}