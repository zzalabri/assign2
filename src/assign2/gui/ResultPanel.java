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

	public void updateText(String text) {
		if (textArea != null) {
			textArea.setText(text);
		}
	}

	public ResultPanel() {
		textArea = new JTextArea("Please enter some text ...");
		textArea.setEditable(true);

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