package swing.chapter12.tasks;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EditableComboBox {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			ComboBoxFrame frame = new ComboBoxFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

class ComboBoxFrame extends JFrame {
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;

	private JComboBox<String> faceCombo;
	private JLabel label;
	private String labels[] = { "A", "B", "C", "D", "E", "F", "G" };
	private int previousIndex = 0;

	public ComboBoxFrame() {
		setTitle("ComboBoxTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		// add the sample text label
		label = new JLabel("The quick brown fox jumps over the lazy dog.");
		add(label, BorderLayout.CENTER);

		// make a combo box and add face names
		faceCombo = new JComboBox<String>(labels);
		faceCombo.setEditable(true);
		faceCombo.setSelectedIndex(0);

		faceCombo.addActionListener(event -> {
			String selectedString = (String) faceCombo.getSelectedItem();
			int selectedIndex = faceCombo.getSelectedIndex();

			if (selectedIndex == -1) {
				selectedIndex = previousIndex;
			}

			previousIndex = selectedIndex;

			label.setText(selectedString);

			labels[selectedIndex] = selectedString;
			faceCombo.removeAllItems();
			for (String lbl : labels) {
				faceCombo.addItem(lbl);
			}

			faceCombo.setSelectedIndex(selectedIndex);

		});

		// add combo box to a panel at the frame's southern border
		JPanel comboPanel = new JPanel();
		comboPanel.add(faceCombo);
		add(comboPanel, BorderLayout.SOUTH);
	}

}
