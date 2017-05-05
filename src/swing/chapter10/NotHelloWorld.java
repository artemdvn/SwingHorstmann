package swing.chapter10;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NotHelloWorld {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			HelloWorldFrameWithJComponent frameWithJComponent = new HelloWorldFrameWithJComponent();
			frameWithJComponent.setMinimumSize(new Dimension(100,100));
			frameWithJComponent.setPreferredSize(new Dimension(600, 400));
			frameWithJComponent.setTitle("Hello World Frame With JComponent");
			frameWithJComponent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frameWithJComponent.setVisible(true);
			frameWithJComponent.setBackground(Color.RED);
			frameWithJComponent.pack();
			
			HelloWorldFrameWithJPanel frameWithJPanel = new HelloWorldFrameWithJPanel();
			frameWithJPanel.setMinimumSize(new Dimension(100,100));
			frameWithJPanel.setPreferredSize(new Dimension(600, 400));
			frameWithJPanel.setTitle("Hello World Frame With JPanel");
			frameWithJPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frameWithJPanel.setVisible(true);
			frameWithJPanel.setBackground(Color.RED);
			frameWithJPanel.pack();
		});
	}

}

class HelloWorldFrameWithJComponent extends JFrame {
	public HelloWorldFrameWithJComponent() {		
		add(new NotHelloWorldComponent());
	}
}

class HelloWorldFrameWithJPanel extends JFrame {
	public HelloWorldFrameWithJPanel() {		
		add(new NotHelloWorldPanel());
	}
}

class NotHelloWorldComponent extends JComponent {
	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;

	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;

	public void paintComponent(Graphics g) {
		g.drawString("JComponent is opaque? " + isOpaque(), MESSAGE_X, MESSAGE_Y);
	}

	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}

class NotHelloWorldPanel extends JPanel {
	public static final int MESSAGE_X = 150;
	public static final int MESSAGE_Y = 200;

	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;

	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		g.drawString("JPanel is opaque? " + isOpaque(), MESSAGE_X, MESSAGE_Y);
	}

	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
