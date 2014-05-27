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

	private static final int WIDTH = 1600;
	private static final int HEIGHT = 1500;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				NGramGUI2 nGramGui = new NGramGUI2("NGram Assignment");
				nGramGui.setMaximumSize(new Dimension(1200, 500));
			nGramGui.setMinimumSize(new Dimension(600, 500));
				nGramGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				nGramGui.pack();
				nGramGui.setVisible(true);
			}
		});

	}

}