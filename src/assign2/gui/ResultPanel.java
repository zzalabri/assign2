package assign2.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ResultPanel extends JPanel {

	private static final long serialVersionUID = -7031008862559936404L;
	private JTextArea headingQuery;
	private JTextArea results;
	private JTextArea heading;
	public ResultPanel() {
		
		heading = new JTextArea("NGram Results of Query: ");
		results = new JTextArea("");
		headingQuery = new JTextArea("");
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		setBackground(Color.white);
		heading.setEditable(false);
		headingQuery.setEditable(false);
		results.setEditable(false);		
		c.gridy = 0;
		c.gridx = 0;
		c.insets = new Insets(5, 5, 5, 0);
		add(heading, c);
		c.weightx = 0.3;
		c.gridy = 0;
		c.gridx = 1;
		c.gridwidth = 3;
		add(headingQuery, c);
		c.gridy = 1;
		c.gridx = 0;
		c.gridheight = 3;
		c.insets = new Insets(0, 10, 10, 10);
		add(results, c);
	}
	
public ResultPanel(String query, String searchResults) {
		
		heading = new JTextArea("NGram Results of Query: ");
		results = new JTextArea(searchResults);
		headingQuery = new JTextArea(query);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		setBackground(Color.white);
		heading.setEditable(false);
		headingQuery.setEditable(false);
		results.setEditable(false);		
		c.gridy = 0;
		c.gridx = 0;
		c.insets = new Insets(5, 5, 5, 0);
		add(heading, c);
		c.weightx = 0.3;
		c.gridy = 0;
		c.gridx = 1;
		c.gridwidth = 3;
		add(headingQuery, c);
		c.gridy = 1;
		c.gridx = 0;
		c.gridheight = 3;
		c.ipady = 80;
		c.insets = new Insets(0, 10, 10, 10);
		add(results, c);
	}
	
	public void setResults(String text) {
		results.setText(text);
	}
	
	public void setHeadingQuery(String text) {
		headingQuery.setText(text);
	}
	
}
