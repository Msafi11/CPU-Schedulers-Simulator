

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
public class WaterfallChartDemo1 extends ApplicationFrame {
	
	static process p;
	schedulers s;
    /**
     * Creates a new WaterFall Chart demo.
     * 
     * @param title  the frame title.
     */
    public WaterfallChartDemo1(final String title) {

        super(title);
        
        final CategoryDataset dataset = createDataset1();
        final JFreeChart chart = createChart1(dataset);
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
    private CategoryDataset createDataset1() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       dataset.addValue(s.outputtime.get(0), "", s.output.get(0).name);
        for(int i=1;i<s.output.size();i++) {
        	for(int c=1;c<s.outputtime.size();c++) {
           	 dataset.addValue(s.outputtime.get(i), "", s.output.get(i).name);
       	
           }
        	 
    	
        }

        
        dataset.addValue(0, "", "");
        return dataset;
    }
    
    
    private JFreeChart createChart1(final CategoryDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createWaterfallChart(
            "AG GanttChart",
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
        standardUnits.add(new NumberTickUnit(3, formatter));
        standardUnits.add(new NumberTickUnit(5, formatter));
        standardUnits.add(new NumberTickUnit(9, formatter));
        standardUnits.add(new NumberTickUnit(12, formatter));
        standardUnits.add(new NumberTickUnit(17, formatter));
        standardUnits.add(new NumberTickUnit(20, formatter));
        standardUnits.add(new NumberTickUnit(21, formatter));
        standardUnits.add(new NumberTickUnit(25, formatter));
        
        standardUnits.add(new NumberTickUnit(33, formatter));
        
        standardUnits.add(new NumberTickUnit(37, formatter));

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
        final WaterfallChartDemo1 demo = new WaterfallChartDemo1("AG GanttChart");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
    
}