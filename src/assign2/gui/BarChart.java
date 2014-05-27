package assign2.gui;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import assign2.ngram.NGramException;
import assign2.ngram.NGramStore;

public class BarChart {
	private JFreeChart jFreeChart;
	
	public BarChart(String[] phrases,NGramStore nGramStore) throws NGramException {	
		CategoryDataset dataset = createDataset(phrases,nGramStore);
        jFreeChart = createChart(dataset);
    }

	
	public JFreeChart getJFreeChart(){
		return (jFreeChart==null)?null:jFreeChart;
	}

    private  DefaultCategoryDataset createDataset(String[] phrases,NGramStore nGramStore) throws NGramException {
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String[] predictions;
		Double[] probabilities;
		for(int i=0; i < phrases.length;i++){
			if(nGramStore.getNGram(phrases[i]) != null){
				predictions=nGramStore.getNGram(phrases[i]).getPredictions();
				probabilities=nGramStore.getNGram(phrases[i]).getProbabilities();
				for(int resultNum=0; resultNum < predictions.length;resultNum++)
					dataset.setValue(probabilities[resultNum], phrases[i], predictions[resultNum]);
			}
		}
		return dataset;  	
    }
    
   
    private JFreeChart createChart(CategoryDataset dataset) {
    	String title="Search Suggestion";
    	JFreeChart chart = ChartFactory.createBarChart3D(
    		title, 
     		"Phrase _____",
    		"Probability",
    		dataset, 
    		PlotOrientation.VERTICAL, 
    		true, 
    		false, 
    		false
    	);
    	
    	chart.setBackgroundPaint(Color.LIGHT_GRAY);
    	chart.setBorderVisible(true);
    	chart.setBorderPaint(Color.BLACK);
    	chart.getTitle().setPaint(Color.BLUE); 
    	CategoryPlot p = chart.getCategoryPlot(); 
    	p.setRangeGridlinePaint(Color.red); 
        return chart;
    }
}