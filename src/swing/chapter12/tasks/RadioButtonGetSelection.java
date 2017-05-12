package swing.chapter12.tasks;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioButtonGetSelection {
	public static void main(String[] args) {
		RadioButtonFrame frame = new RadioButtonFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/**
 * A frame with a sample text label and radio buttons for selecting font sizes.
 */
class RadioButtonFrame extends JFrame {

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 200;

	private JPanel buttonPanel;
	private ButtonGroup group;
	private JLabel label;

	private static final int DEFAULT_SIZE = 12;

	public RadioButtonFrame() {
		setTitle("RadioButtonGetSelection");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		// add the sample text label
		label = new JLabel("The quick brown fox jumps over the lazy dog.");
		label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
		add(label, BorderLayout.CENTER);

		// add the radio buttons
		buttonPanel = new JPanel();
		group = new ButtonGroup();

		addRadioButton("Small", 8);
		addRadioButton("Medium", 12);
		addRadioButton("Large", 18);
		addRadioButton("Extra large", 36);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Adds a radio button that sets the font size of the sample text.
	 * 
	 * @param name
	 *            the string to appear on the button
	 * @param size
	 *            the font size that this button sets
	 */
	public void addRadioButton(String name, final int size) {
		boolean selected = size == DEFAULT_SIZE;
		if (selected) {
			label.setText("Current selection is: ".concat(name));
		}
		JRadioButton button = new JRadioButton(name, selected);
		button.setActionCommand(name);
		group.add(button);
		buttonPanel.add(button);

		button.addActionListener(event -> {
			label.setFont(new Font("Serif", Font.PLAIN, size));
			String choice = group.getSelection().getActionCommand();
			label.setText("Current selection is: ".concat(choice));
		});
	}

}
