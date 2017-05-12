package swing.chapter12.tasks;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class BorderLayoutDemo {
	public static boolean RIGHT_TO_LEFT = false;

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		
		// two buttons
		JButton button1 = new JButton("Button 1 (SOUTH)");
		JButton button2 = new JButton("Button 2 (SOUTH)");

		// frame with two south buttons
		JFrame frame1 = new JFrame("BorderLayout frame with two south buttons");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.add(button1, BorderLayout.SOUTH);
		frame1.add(button2, BorderLayout.SOUTH);
		frame1.pack();
		frame1.setVisible(true);
		
		// another two buttons
		JButton button3 = new JButton("Button 1 (SOUTH)");
		JButton button4 = new JButton("Button 2 (SOUTH)");
		
		// frame with JPanel
		JFrame frame2 = new JFrame("BorderLayout frame with JPanel");
		JPanel panel = new JPanel();
		panel.add(button3);
		panel.add(button4);
		frame2.add(panel, BorderLayout.SOUTH);
		frame2.pack();
		frame2.setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			createAndShowGUI();
		});
	}
}
