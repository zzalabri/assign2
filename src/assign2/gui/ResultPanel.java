/**  
 * @title NGramNode.java  
 * @package assign2.gui  
 * @author khaled  
 * @version V1.0  
 * created 22/05/2014  
 */
package assign2.gui;

import javax.swing.*;

import java.awt.*;

public class ResultPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7027494397725323564L;
	private JTextArea textArea;

<<<<<<< HEAD
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
=======
	public void updateText(String text) {
		if (textArea != null) {
			textArea.setText(text);
		}
>>>>>>> 7b5cf1a780568207bdcd4f2f60592d6b3a380839
	}

	public ResultPanel() {
		textArea = new JTextArea("Please enter some text ...");
		textArea.setEditable(true);
		 //add(new JScrollPane(view));

		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout());
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
	
	public void SetResultText(String result) {
		StringBuffer resultText = new StringBuffer();

		//Display the results on the results text area
		resultText.append(result);
		resultText.append("\n");
		textArea.setText(resultText.toString());

		this.textArea.setText(result);

	}
}
