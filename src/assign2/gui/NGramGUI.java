/**  
 * @title NGramNode.java  
 * @package assign2.gui  
 * @author khaled  
 * @version V1.0  
 * created 22/05/2014  
 */
package assign2.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import assign2.ngram.NGramException;
import assign2.ngram.NGramStore;


public class NGramGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7031008862559936404L;
	private static final Integer MAX_RESULTS = 5;
	
	private static final int DIMENSION_WIDTH = 600;
	private static final int DIMENSION_HEIGHT = 500;
	private static final int BORDER_lAYOUT = 10;
	
	private JButton btnSearch = new JButton("Search");
    private JButton btnChart = new JButton("Chart");
    private JButton btnResult = new JButton("Result");
	
	private BarChart chartResults;
	private ChartPanel chart;
	
    
	private JTextField txtContext = new JTextField();
	
    private JLabel lblSearch = new JLabel("Enter text :");
	
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMI = new JMenu("File");
	
    private JMenuItem openFileMenu = new JMenuItem("Search");
    private JMenuItem exitMenu = new JMenuItem("Exit");
    
    private JMenu helpMI = new JMenu("Help");
    private JMenuItem aboutMenu = new JMenuItem("About");
    private JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    private JPanel resultPanel = new JPanel(new BorderLayout());
    private JPanel resultChartPanel = new JPanel(new BorderLayout());
    private JPanel headPanel = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
    GridBagConstraints grid = new GridBagConstraints();
    
    
    public NGramGUI(String guiName) {
		super(guiName);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT));
        setResizable(true);

        setLayout(new BorderLayout(BORDER_lAYOUT, BORDER_lAYOUT));

        fileMI.add(openFileMenu);
        fileMI.add(exitMenu);
        helpMI.add(aboutMenu);

        menuBar.add(fileMI);
        menuBar.add(helpMI);

        setJMenuBar(menuBar);
        
     
        resultPanel = new ResultPanel();
     	this.getContentPane().add(resultPanel, BorderLayout.CENTER);
     	
        headPanel.add(lblSearch);
        headPanel.add(txtContext);
		headPanel.add(btnSearch);
		btnSearch.addActionListener(this);
		this.getContentPane().add(headPanel, BorderLayout.NORTH);
		
        bottomPanel.add(btnChart);
        btnChart.addActionListener(this);
        bottomPanel.add(btnResult);
        btnResult.addActionListener(this);
        this.getContentPane().add(headPanel, BorderLayout.SOUTH);
        

        mainPanel.add(headPanel, BorderLayout.NORTH);
        mainPanel.add(resultPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_lAYOUT, BORDER_lAYOUT, BORDER_lAYOUT, BORDER_lAYOUT));
        add(mainPanel);
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg) {
		String buttonString = arg.getActionCommand();
		String result = "";
		Pattern pattern = Pattern.compile("[^\\w\\s,]");	//Check for Char Not in (words,numbers,space or comma)
	    Matcher matcher = pattern.matcher("");
	    boolean invalidInput= false;
		NGramStore store = new NGramStore();
		String[] contexts = {};
		
		if (buttonString.equals("Search")) {
            if(txtContext.getText() == null || txtContext.getText().equals("") )
            	result+= "Please enter some text...";
            
            else{
			    matcher = pattern.matcher(txtContext.getText());
			    invalidInput= matcher.find();
			    
				if( invalidInput  ) {
	                result += "Invalid Input ";
	            } else {
	            	
	                contexts = txtContext.getText().split(",");
	                if (contexts.length > MAX_RESULTS  ) {
	                  result += "Invalid context ";
	                } else {
	                    for (String context : contexts) {
	                       result += "\nNGram Results for Query: " + context + "\n\n";
	                        try {
	                        	if (!(store.getNGramsFromService(context, MAX_RESULTS))){
	                        		result += "No results for this contexts.";
	                        	} else {
	                        		result += store.getNGram(context).toString();
	                        	}
	                        	
	                        	//produce the bar chart
	                		  	chartCereator(store, contexts);
	                		  	
	                        } catch (NGramException nex) {
	                        	result += "No results for this contexts.";
	                        	}
	                    }
	                }
	            }
	
				((ResultPanel) resultPanel).updateText(result);
            }
            
		} else if (buttonString.equals("Result")) {
			resultPanel.setVisible(true);
			btnChart.setVisible(false);
		} else if (buttonString.equals("Chart")) {
			resultChartPanel.setVisible(true);
			resultPanel.setVisible(false);
		} 
	}
	
	private void chartCereator(NGramStore store, String[] contexts)
			throws NGramException {
		chartResults=new BarChart(contexts, store);
		
		if(this.chart != null){
			chart.removeAll();
			chart.revalidate();
			chart.setChart(chartResults.getJFreeChart());
			this.getContentPane().add(chart, BorderLayout.CENTER);
			this.getContentPane().repaint();
		}else{
			chart=new ChartPanel(chartResults.getJFreeChart());
			chart.setPreferredSize(new java.awt.Dimension(600, 500)); 
			this.getContentPane().add(this.chart, BorderLayout.CENTER);
			this.getContentPane().repaint();
		} // end else 

	}

}
