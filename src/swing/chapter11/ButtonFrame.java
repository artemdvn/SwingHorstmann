package swing.chapter11;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonFrame extends JFrame {

	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;

	public ButtonFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		buttonPanel = new JPanel();

		makeButton("yellow", Color.YELLOW);
		makeButton("blue", Color.BLUE);
		makeButton("red", Color.RED);

		add(buttonPanel);

	}

	public void makeButton(String name, Color backgroundColor) {
		JButton button = new JButton(name);
		buttonPanel.add(button);
		button.addActionListener(event -> buttonPanel.setBackground(backgroundColor));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			ButtonFrame frame = new ButtonFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
