package swing.chapter12;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComboBoxTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			ComboBoxFrame frame = new ComboBoxFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

/**
 * A frame with a sample text label and a combo box for selecting font faces.
 */
class ComboBoxFrame extends JFrame {
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;

	private JComboBox<String> faceCombo;
	private JLabel label;
	private static final int DEFAULT_SIZE = 12;

	public ComboBoxFrame() {
		setTitle("ComboBoxTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		// add the sample text label
		label = new JLabel("The quick brown fox jumps over the lazy dog.");
		label.setFont(new Font("Monospaced", Font.PLAIN, DEFAULT_SIZE));
		add(label, BorderLayout.CENTER);

		// make a combo box and add face names
		faceCombo = new JComboBox<String>();
		faceCombo.setEditable(true);
		faceCombo.addItem("Serif");
		faceCombo.addItem("SansSerif");
		faceCombo.insertItemAt("Monospaced", 0);
		faceCombo.addItem("Dialog");
		faceCombo.addItem("DialogInput");
		faceCombo.setSelectedIndex(0);

		// the combo box listener changes the label font to the selected face
		// name
		faceCombo.addActionListener(event -> {
			label.setFont(new Font((String) faceCombo.getSelectedItem(), Font.PLAIN, DEFAULT_SIZE));
		});

		// add combo box to a panel at the frame's southern border
		JPanel comboPanel = new JPanel();
		comboPanel.add(faceCombo);
		add(comboPanel, BorderLayout.SOUTH);
	}

}
