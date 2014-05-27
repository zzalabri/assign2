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
		GridBagConstraints c = new GridBagConstraints();
	    textResults = new ResultPanel();
	    inputField = new JTextField("");
	    submitButton = new JButton("Submit");
	    //Create ngram store
		store = new NGramStore();		
	    c = setTextResultsPosition();
	    submitButton.addActionListener(this);
	    
	    add(textResults, c);
	    c.gridx = 1;
	    c.gridy = 3;
	    c.gridheight = 1;
	    c.gridwidth = 1;
	    c.weightx = 0.4;
	    c.weighty = 1;
	    c.insets = new Insets(0, 5, 0, 0);
	    add(inputField, c);
	    c.gridx = 2;
	    c.gridy = 3;
	    c.weightx = 0.2;
	    c.insets = new Insets(0, 10, 0, 50);
	    add(submitButton, c );
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonString = e.getActionCommand();
		
		  if (buttonString.equals("Submit")) {
			try {
				if (inputField.getText().matches(REGEX)) {
					String[] contexts = inputField.getText().split(",");
					for (String context : contexts) {
						store.getNGramsFromService(context, MAX_RESULTS);
					}
				} else {
					throw new NGramException();
				}
			
			} catch (NGramException e1) {
				e1.printStackTrace();
			}
			addNewResults();
		  }		
	}
	
	private void addNewResults() {
		String queryResult = store.toString();
		GridBagConstraints c = new GridBagConstraints();
		remove(textResults);
		textResults = new ResultPanel(inputField.getText(), queryResult);
		c = setTextResultsPosition();
		add(textResults, c);
		validate();
	}
	
	private GridBagConstraints setTextResultsPosition() {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
	    c.anchor = GridBagConstraints.FIRST_LINE_START;
	    c.gridwidth = 3;
	    c.gridheight = 3;
	    c.weightx = 0.5;
	    c.weighty = 0.1;
	    c.gridx = 0;
	    c.gridy = 0;
	    c.insets = new Insets(5, 5, 5, 5);
	    return c;
	}
}
