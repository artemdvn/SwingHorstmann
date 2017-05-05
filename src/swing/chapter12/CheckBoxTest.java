package swing.chapter12;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckBoxTest {
	public static void main(String[] args) {
		CheckBoxFrame frame = new CheckBoxFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

/**
 * A frame with a sample text label and check boxes for selecting font
 * attributes.
 */
class CheckBoxFrame extends JFrame {

	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;

	private JLabel label;
	private JCheckBox bold;
	private JCheckBox italic;

	private static final int FONTSIZE = 12;

	public CheckBoxFrame() {
		setTitle("CheckBoxTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		// add the sample text label
		label = new JLabel("The quick brown fox jumps over the lazy dog.");
		label.setFont(new Font("Serif", Font.PLAIN, FONTSIZE));
		add(label, BorderLayout.CENTER);

		// this listener sets the font attribute of
		// the label to the check box state
		ActionListener listener = event -> {
			int mode = 0;
			if (bold.isSelected())
				mode += Font.BOLD;
			if (italic.isSelected())
				mode += Font.ITALIC;
			label.setFont(new Font("Serif", mode, FONTSIZE));
		};

		// add the check boxes
		JPanel buttonPanel = new JPanel();

		bold = new JCheckBox("Bold");
		bold.addActionListener(listener);
		buttonPanel.add(bold);

		italic = new JCheckBox("Italic");
		italic.addActionListener(listener);
		buttonPanel.add(italic);

		add(buttonPanel, BorderLayout.SOUTH);
	}

}
