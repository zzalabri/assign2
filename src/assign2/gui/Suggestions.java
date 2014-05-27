/**  
 * @title NGramNode.java  
 * @package assign2.gui  
 * @author khaled  
 * @version V1.0  
 * created 22/05/2014  
 */
package assign2.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Suggestions {

	//private static final int MAX_WIDTH = 1600;
	//private static final int MAX_HEIGHT = 1500;
	//private static final int MIN_WIDTH = 600;
	//private static final int MIN_HEIGHT = 500;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				NGramGUI nGramGui = new NGramGUI("NGram Assignment");
				//nGramGui.setMaximumSize(new Dimension(MAX_WIDTH, MAX_HEIGHT));
				//nGramGui.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
				nGramGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//nGramGui.pack();
				nGramGui.setVisible(true);
			}
		});

	}

}