package swing.chapter11;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

// swing.properties file in C:\Program Files\Java\jdk1.8.0_121\jre\lib
public class PlafFrame extends JFrame {

	private JPanel buttonPanel;
	
	public PlafFrame() {

		buttonPanel = new JPanel();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowIconified(WindowEvent e) {
				if (buttonPanel.getBackground() != Color.BLUE) {
					buttonPanel.setBackground(Color.BLUE);
				} else {
					buttonPanel.setBackground(Color.RED);
				}
			}
		});

		UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
		for (UIManager.LookAndFeelInfo info : infos) {
			//System.out.println(info.getClassName());
			makeButton(info.getName(), info.getClassName());
		}
		
		add(buttonPanel);
		pack();

	}

	public void makeButton(String name, String className) {
		JButton button = new JButton(name);
		buttonPanel.add(button);
		button.addActionListener(event -> {
			try {
				UIManager.setLookAndFeel(className);
				SwingUtilities.updateComponentTreeUI(this);
				pack();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			PlafFrame frame = new PlafFrame();
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
	
}
