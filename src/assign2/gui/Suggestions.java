package assign2.gui;

import java.awt.Dimension;




public class Suggestions {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;

	public static void main(String[] args) {
		NGramGUI gui = new NGramGUI();
		gui.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		gui.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		gui.pack();
		gui.setVisible(true);
	

	}
}
