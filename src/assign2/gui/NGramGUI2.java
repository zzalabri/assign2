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
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import assign2.ngram.NGramException;
import assign2.ngram.NGramStore;


public class NGramGUI2 extends JFrame implements ActionListener {

	private static final long serialVersionUID = -7031008862559936404L;
	private static final String REGEX = "[\\w[']]*";
	private static final Integer MAX_RESULTS = 5;
	
	private JButton btnSearch = new JButton("Search");
    private JButton btnChart = new JButton("Chart");
    private JButton btnResult = new JButton("Result");
	//private JButton btnExit = new JButton("Exit");
	
    private ResultPanel textResults;
	private ChartPanel chartResults;
    
	private JTextField txtContext = new JTextField();
	
    private JLabel lblSearch = new JLabel("Enter text :");
	
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMI = new JMenu("File");
	
    private JMenuItem openFileMenu = new JMenuItem("Search");
    private JMenuItem exitMenu = new JMenuItem("Exit");
    
    private JMenu helpMI = new JMenu("Help");
    private JMenuItem aboutMenu = new JMenuItem("About");
    //private JTable table = new JTable(5, 2);
    private JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    private JPanel resultPanel = new JPanel(new BorderLayout());
    private JPanel resultChartPanel = new JPanel(new BorderLayout());
    private JPanel headPanel = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 10, 10));
    GridBagConstraints grid = new GridBagConstraints();
    
    private NGramStore ngramsMap;
    
  //  private int predictionsNumber = 0;
    
    public NGramGUI2(String guiName) {
		super(guiName);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 500));
        setResizable(true);

        setLayout(new BorderLayout(10, 10));

        fileMI.add(openFileMenu);
        fileMI.add(exitMenu);
        helpMI.add(aboutMenu);

        menuBar.add(fileMI);
        menuBar.add(helpMI);

        setJMenuBar(menuBar);
        
     
        resultPanel = new ResultPanel();
     	this.getContentPane().add(resultPanel, BorderLayout.CENTER);
     	resultChartPanel = new ChartPanel();
     	this.getContentPane().add(resultChartPanel, BorderLayout.CENTER);
        

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
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel);
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg) {
		String buttonString = arg.getActionCommand();
		String result = "";
		NGramStore store = new NGramStore();
		String[] contexts = {};
		
		if (buttonString.equals("Search")) {
            if(txtContext.getText() == null || txtContext.getText().equals("") || Pattern.matches(REGEX, txtContext.getText())  ) {
                result += "Invalid Input ";
            } else {

                contexts = txtContext.getText().split(",");
                if (contexts.length > MAX_RESULTS  ) {
                  result += "Invalid context ";
                } else {
                    for (String context : contexts) {
                       result += "NGram Results for Query: " + context + "\n";
                        try {
                        	if (!(store.getNGramsFromService(context, MAX_RESULTS))){
                        		result += "No results for this contexts.";
                        	} else {
                        		result += store.getNGram(context).toString();
                        	}
                        } catch (NGramException nex) {
                            result += "No results for this contexts.";
                        }
                    }
                }
            }

			((ResultPanel) resultPanel).updateText(result);
			
		} else if (buttonString.equals("Result")) {
			resultPanel.setVisible(true);
			btnChart.setVisible(false);
//			textPanel.setVisible(true);
//			chartPanel.setVisible(false);
		} else if (buttonString.equals("Chart")) {
			resultChartPanel.setVisible(true);
			resultPanel.setVisible(false);
		} 
	}
}
