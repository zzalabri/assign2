package assign2.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.jfree.chart.ChartPanel;

import assign2.ngram.NGramException;
import assign2.ngram.NGramStore;


public class NGramGUI extends JFrame implements ActionListener {

	private static final String REGEX = "[\\w[']]*";
	private static final int MAX_RESULTS = 5;
	private static final long serialVersionUID = -7031008862559936404L;
	private ResultPanel textResults;
	private ChartPanel chartResults;
	private JTextField inputField;
	private JButton submitButton;
	private NGramStore store;
	
	
	public NGramGUI() {
		super("NGram GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)	;
		setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
	    textResults = new ResultPanel();
	    inputField = new JTextField("");
	    submitButton = new JButton("Submit");
	    //Create ngram store
		store = new NGramStore();		
	    gridBagConstraints = setTextResultsPosition();
	    submitButton.addActionListener(this);
	    
	    add(textResults, gridBagConstraints);
	    gridBagConstraints.gridx = 1;
	    gridBagConstraints.gridy = 3;
	    gridBagConstraints.gridheight = 1;
	    gridBagConstraints.gridwidth = 1;
	    gridBagConstraints.weightx = 0.4;
	    gridBagConstraints.weighty = 1;
	    gridBagConstraints.insets = new Insets(0, 5, 0, 0);
	    add(inputField, gridBagConstraints);
	    gridBagConstraints.gridx = 2;
	    gridBagConstraints.gridy = 3;
	    gridBagConstraints.weightx = 0.2;
	    gridBagConstraints.insets = new Insets(0, 10, 0, 50);
	    add(submitButton, gridBagConstraints );
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonString = e.getActionCommand();
		String[] contexts;
		
		  if (buttonString.equals("Submit")) {
			try {
				//if (inputField.getText().matches(REGEX)) {
					contexts = parseInput(inputField.getText());
					//==================================
					textResults = new ResultPanel("   ,,,  Wrong Entry222   ,,,", "   ,,,  Wrong Entry   ,,,");
					GridBagConstraints gridBagConstraints = setTextResultsPosition();
					add(textResults, gridBagConstraints);
					validate();
					//==================================
					for (String context : contexts) {
						store.getNGramsFromService(context, MAX_RESULTS);
					//}
				//} else {
				//	throw new NGramException();
				}
			
			} catch (NGramException e1) {
				e1.printStackTrace();
			}
			//addNewResults();
		  }		
	}
	
	private String[] parseInput(String text) throws NGramException {
		
		String[] contexts = inputField.getText().split(",");
		
		return contexts;
	}
	
	private void addNewResults() {
		String queryResult = store.toString();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		remove(textResults);
		textResults = new ResultPanel(inputField.getText(), queryResult);
		gridBagConstraints = setTextResultsPosition();
		add(textResults, gridBagConstraints);
		validate();
	}
	
	private GridBagConstraints setTextResultsPosition() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
	    gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
	    gridBagConstraints.gridwidth = 3;
	    gridBagConstraints.gridheight = 3;
	    gridBagConstraints.weightx = 0.5;
	    gridBagConstraints.weighty = 0.1;
	    gridBagConstraints.gridx = 0;
	    gridBagConstraints.gridy = 0;
	    gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    return gridBagConstraints;
	}
}
