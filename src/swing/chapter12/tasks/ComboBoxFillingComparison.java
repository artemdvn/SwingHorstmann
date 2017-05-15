package swing.chapter12.tasks;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.Instant;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A class to compare performance of filling JComboBox through addItem() and
 * addElement()
 */
public class ComboBoxFillingComparison {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			ComboBoxToSelectSize frame = new ComboBoxToSelectSize();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

class ComboBoxToSelectSize extends JFrame {
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;

	private JComboBox<Integer> sizeCombo;
	private JComboBox<Integer> comboToUpdateWithAddItem = new JComboBox<Integer>();
	private JComboBox<Integer> comboToUpdateWithAddElement = new JComboBox<Integer>();
	private JLabel label;
	private JLabel labelWithAddItem;
	private JLabel labelWithAddElement;
	private Integer[] labels = { 10, 100, 1000, 10000, 100000, 1000000 };

	public ComboBoxToSelectSize() {
		setTitle("ComboBoxToSelectSize");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		label = new JLabel("Selected size: ".concat(labels[0].toString()));
		labelWithAddItem = new JLabel("Time with addItem(): ");
		labelWithAddElement = new JLabel("Time with addElement(): ");
		
		JPanel labelPanel = new JPanel();
		labelPanel.add(label);
		labelPanel.add(labelWithAddItem);
		labelPanel.add(labelWithAddElement);
		add(labelPanel, BorderLayout.CENTER);

		sizeCombo = new JComboBox<Integer>();
		for (Integer lbl : labels) {
			sizeCombo.addItem(lbl);
		}
		sizeCombo.setSelectedIndex(0);

		sizeCombo.addActionListener(event -> {
			Integer selectedSize = (Integer) sizeCombo.getSelectedItem();
			label.setText("Selected size: ".concat(selectedSize.toString()));
			updateComboWithAddItem(selectedSize);
			updateComboWithAddElement(selectedSize);
		});

		// add combo boxes to panels at the frame
		JPanel comboPanel1 = new JPanel();
		comboPanel1.add(sizeCombo);
		add(comboPanel1, BorderLayout.NORTH);
		
		JPanel comboPanel2 = new JPanel();
		comboPanel2.add(comboToUpdateWithAddItem);
		comboPanel2.add(comboToUpdateWithAddElement);
		add(comboPanel2, BorderLayout.SOUTH);
	}
	
	private void updateComboWithAddItem(Integer size) {
		long start = Instant.now().toEpochMilli();
		comboToUpdateWithAddItem.removeAllItems();
		Random rnd = new Random();
		for (int index = 0; index < size; index++) {
			comboToUpdateWithAddItem.addItem(rnd.nextInt(1000));
		}
		long time = Instant.now().toEpochMilli() - start;
		labelWithAddItem.setText("Time with addItem(): " + time + " ms");
	}

	private void updateComboWithAddElement(Integer size) {
		long start = Instant.now().toEpochMilli();
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>();
		Random rnd = new Random();
		for (int index = 0; index < size; index++) {
			model.addElement(rnd.nextInt(1000));
		}
		comboToUpdateWithAddElement.setModel(model);
		long time = Instant.now().toEpochMilli() - start;
		labelWithAddElement.setText("Time with addElement(): " + time + " ms");
	}

}
