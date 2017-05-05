package swing.chapter10;

import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SimpleFrameTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SizedFrame frame = new SizedFrame();
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}

class SimpleFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;

	public SimpleFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}

class SizedFrame extends JFrame {

	private static final boolean IS_DEFAULT = true;

	public SizedFrame() {

		GraphicsDevice[] deviceArray = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

		int NUMBER_OF_SCREEN = 2;
		if (IS_DEFAULT || deviceArray.length > 2) {
			NUMBER_OF_SCREEN = 1;
		}

		int screenHeight = deviceArray[NUMBER_OF_SCREEN - 1].getDisplayMode().getHeight();
		int screenWidth = deviceArray[NUMBER_OF_SCREEN - 1].getDisplayMode().getWidth();
		setLocation(deviceArray[NUMBER_OF_SCREEN - 1].getDefaultConfiguration().getBounds().x + screenWidth / 2,
				deviceArray[NUMBER_OF_SCREEN - 1].getDefaultConfiguration().getBounds().y + screenHeight / 2);

		setSize(screenWidth / 2, screenHeight / 2);
		
		Image img = new ImageIcon("bomb.png").getImage();
		setIconImage(img);
		
		setTitle("Swing Horstmann");
		
	}
}
